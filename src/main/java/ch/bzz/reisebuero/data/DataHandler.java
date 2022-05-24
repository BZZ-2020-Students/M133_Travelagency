package ch.bzz.reisebuero.data;



import ch.bzz.reisebuero.model.Ferienziel;
import ch.bzz.reisebuero.model.Reise;
import ch.bzz.reisebuero.model.Strecke;
import ch.bzz.reisebuero.service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private static List<Reise> reiseList;
    private static List<Ferienziel> ferienzielList;
    private static List<Strecke> streckeList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setFerienzielList(new ArrayList<>());
        readFerienzielJSON();
        setReiseList(new ArrayList<>());
        readReiseJSON();
        setStreckeList(new ArrayList<>());
        readStreckeJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all reise
     * @return list of books
     */
    public List<Reise> readallReise() {
        return getReiseList();
    }

    /**
     * reads a reise by its uuid
     * @param reiseUUID
     * @return the reise (null=not found)
     */
    public static Reise readReisebyUUID(String reiseUUID) {
        Reise reise = null;
        for (Reise entry : getReiseList()) {
            if (entry.getReiseUUID().equals(reiseUUID)) {
                reise = entry;
            }
        }
        return reise;
    }

    /**
     * inserts a new book into the reiselist
     *
     * @param reise the reise to be saved
     */
    public static void insertReise(Reise reise) {
        getReiseList().add(reise);
        writeReiseJSON();
    }

    /**
     * updates the reiseList
     */
    public static void updateReise() {
        writeReiseJSON();
    }

    /**
     * deletes a book identified by the reiseUUID
     * @param reiseUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteReise(String reiseUUID) {
        Reise reise = readReisebyUUID(reiseUUID);
        if (reise != null) {
            getReiseList().remove(reise);
            writeReiseJSON();
            return true;
        } else {
            return false;
        }
    }
    /**
     * inserts a new publisher into the reiseList
     *
     * @param ferienziel the ferienziel to be saved
     */
    public static void insertFerienziel(Ferienziel ferienziel) {
        getFerienzielList().add(ferienziel);
        writeFerienzielJSON();
    }
    /**
     * updates the ferienzielList
     */
    public static void updateFerienziel() {
        writeFerienzielJSON();
    }

    /**
     * deletes a publisher identified by the ferienzielUUID
     * @param ferienzielUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteFerienziel(String ferienzielUUID) {
        Ferienziel ferienziel = readFerienzielbyUUID(ferienzielUUID);
        if (ferienziel != null) {
            getFerienzielList().remove(ferienziel);
            writeFerienzielJSON();
            return true;
        } else {
            return false;
        }
    }
    public static void insertStrecke(Strecke strecke) {
        getStreckeList().add(strecke);
        writeStreckeJSON();
    }
    /**
     * updates the StreckeList
     */
    public static void updateStrecke() {
        writeStreckeJSON();
    }

    /**
     * deletes a publisher identified by the ferienzielUUID
     * @param ferienzielUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteStrecke(String streckeUUID) {
        Strecke strecke = readStreckebyUUID(streckeUUID);
        if (strecke != null) {
            getFerienzielList().remove(strecke);
            writeStreckeJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads all ferienziele
     * @return list of ferienziele
     */
    public List<Ferienziel> readAllFerienziel() {

        return getFerienzielList();
    }

    /**
     * reads a ferienziele by its uuid
     * @param ferienzielUUID
     * @return the ferienziel (null=not found)
     */
    public static Ferienziel readFerienzielbyUUID(String ferienzielUUID) {
        Ferienziel ferienziel = null;
        for (Ferienziel entry : getFerienzielList()) {
            if (entry.getFerienzielUUID().equals(ferienzielUUID)) {
                ferienziel = entry;
            }
        }
        return ferienziel;
    }

    /**
     * reads the ferienziel from the JSON-file
     */
    private static void readReiseJSON() {
        try {
            String path = Config.getProperty("reiseJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Reise[] reisen = objectMapper.readValue(jsonData, Reise[].class);
            for (Reise reise : reisen) {
                getReiseList().add(reise);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * writes the bookList to the JSON-file
     */
    private static void writeReiseJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String reisePath = Config.getProperty("reiseJSON");
        try {
            fileOutputStream = new FileOutputStream(reisePath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getReiseList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the ferienziel from the JSON-file
     */
    private static void readFerienzielJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("ferienzielJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Ferienziel[] ferienziele = objectMapper.readValue(jsonData, Ferienziel[].class);
            for (Ferienziel ferienziel : ferienziele) {
                getFerienzielList().add(ferienziel);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * writes the publisherList to the JSON-file
     */
    private static void writeFerienzielJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String ferienzielPath = Config.getProperty("ferienzielJSON");
        try {
            fileOutputStream = new FileOutputStream(ferienzielPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getFerienzielList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void writeStreckeJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String streckePath = Config.getProperty("streckeJSON");
        try {
            fileOutputStream = new FileOutputStream(streckePath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getStreckeList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads all strecken
     * @return list of strecken
     */
    public List<Strecke> readallStrecke() {
        return getStreckeList();
    }

    /**
     * reads the strecke from the JSON-file
     */
    public static Strecke readStreckebyUUID(String streckeUUID) {
        Strecke strecke = null;
        for (Strecke entry : getStreckeList()) {
            if (entry.getStreckeUUID().equals(streckeUUID)) {
                strecke = entry;
            }
        }
        return strecke;
    }

    /**
     * reads the strecke from the JSON-file
     */
    private static void readStreckeJSON() {
        try {
            String path = Config.getProperty("streckeJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Strecke[] strecken = objectMapper.readValue(jsonData, Strecke[].class);
            for (Strecke strecke : strecken) {
                getStreckeList().add(strecke);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
           /**
         * gets reiselist
         *
         * @return value of bookList
         */
    private static List<Reise> getReiseList() {
        if (reiseList == null) {
            setReiseList(new ArrayList<>());
            readReiseJSON();
        }
        return reiseList;
    }

    /**
     * sets reiselist
     *
     * @param reiseList the value to set
     */
    private static void setReiseList(List<Reise> reiseList) {
        DataHandler.reiseList = reiseList;
    }

    /**
     * gets ferienziellist
     *
     * @return value of ferienzielList
     */
    private static List<Ferienziel> getFerienzielList() {
        if (ferienzielList == null) {
            setFerienzielList(new ArrayList<>());
            readFerienzielJSON();
            readReiseJSON();
        }

        return ferienzielList;
    }

    /**
     * sets ferienziellist
     *
     * @param ferienzielList the value to set
     */
    private static void setFerienzielList(List<Ferienziel> ferienzielList) {
        DataHandler.ferienzielList = ferienzielList;
    }
    /**
     * gets streckenlist
     *
     * @return value of streckenlist
     */

    public static List<Strecke> getStreckeList() {
        if (streckeList == null) {
            setStreckeList(new ArrayList<>());
            readStreckeJSON();
            readReiseJSON();
        }

        return streckeList;
    }


    /**
     * sets streckeList
     *
     * @param streckeList the value to set
     */
    public static void setStreckeList(List<Strecke> streckeList) {
        DataHandler.streckeList = streckeList;
    }
}