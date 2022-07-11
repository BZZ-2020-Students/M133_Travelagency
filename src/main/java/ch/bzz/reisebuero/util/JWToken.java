package ch.bzz.reisebuero.util;


import ch.bzz.reisebuero.service.Config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.*;


/**
 * a JSON WebToken
 * @author Marcel Suter BZZ
 */
public class JWToken {

    /**
     * builds the token
     * @param subject the token subject
     * @param duration the duration of this token in minutes
     * @param claims a map of claims
     * @return JSON web token as String
     */
    public static String buildToken(
            String subject,
            int duration,
            Map<String, Object> claims) {
        String salt = Config.getProperty("salt");
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            String value = entry.getValue().toString();
            entry.setValue(encrypt(
                    value,
                    getJwtEncrypt(),
                    salt
            ));
        }

        try {
            byte[] keyBytes = Config.getProperty("jwtSecret").getBytes(StandardCharsets.UTF_8);
            SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);
            Date now = new Date();
            Date expiration = new Date(now.getTime() + duration * 60000);

            return Jwts.builder()
                    .setIssuer("BookUltimate")
                    .setSubject(encrypt(
                            subject,
                            getJwtEncrypt(),
                            salt
                    ))
                    .setClaims(claims)
                    .setExpiration(expiration)
                    .setIssuedAt(now)
                    .signWith(secretKey)
                    .compact();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * reads all claims from the token
     * @param token
     * @return
     */
    public static Map<String,String> readClaims(String token) {
        Map<String,String> claimMap = new HashMap<>();
        Jws<Claims> jwsClaims;
        byte[] keyBytes = Config.getProperty("jwtSecret").getBytes(StandardCharsets.UTF_8);
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);
        String jwtKey = Config.getProperty("jwtKey");
        String salt = Config.getProperty("salt");
        try {
            jwsClaims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            String subject = decrypt(
                    jwsClaims.getBody().getSubject(),
                    jwtKey,
                    salt
            );
            claimMap.put("subject",subject         );

            for (Map.Entry<String, Object> entry : jwsClaims.getBody().entrySet()) {
                String value = decrypt(
                        entry.getValue().toString(),
                        jwtKey,
                        salt
                );
                claimMap.put(entry.getKey(), value);

            }

        } catch (JwtException ex) {
            ex.printStackTrace();
        }
        return claimMap;
    }

    /**
     * encrypts the string
     * @author Lokesh Gupta (https://howtodoinjava.com/java/java-security/aes-256-encryption-decryption/)
     * @param strToEncrypt  string to be encrypted
     * @return encrypted string
     */
    public static String encrypt(
            String strToEncrypt,
            String secret,
            String salt) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    /**
     * decrypts the string
     * @author Lokesh Gupta (https://howtodoinjava.com/java/java-security/aes-256-encryption-decryption/)
     * @param strToDecrypt  string to be dencrypted
     * @param secret  the secret key
     * @return decrypted string
     */
    public static String decrypt(
            String strToDecrypt,
            String secret,
            String salt) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    /**
     * gets the jwtkey from the propierties
     * @return the jwtKey
     */
    private static String getJwtEncrypt() {
        String rawKey = Config.getProperty("jwtKey");
        int multi8 = rawKey.length() / 8;
        return rawKey.substring(0, (multi8 * 8));
    }

}