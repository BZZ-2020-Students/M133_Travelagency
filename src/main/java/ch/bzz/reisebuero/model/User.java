package ch.bzz.reisebuero.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * a user for authentication / authorization
 */

public class User {
    private String userUUID;
    private String username;
    private String password;
    private String role;
    private List<String> words;

    public User() {
        setUsername("guest");
        setRole("guest");
    }

    public User(String userUUID, String username, String password, String role, List<String> words) {
        this.userUUID = userUUID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.words = words;
    }


    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
