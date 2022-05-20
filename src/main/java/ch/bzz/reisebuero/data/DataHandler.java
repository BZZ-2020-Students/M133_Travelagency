package ch.bzz.reisebuero.data;



import ch.bzz.reisebuero.model.Ferienziel;
import ch.bzz.reisebuero.model.Reise;
import ch.bzz.reisebuero.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Ferienziel> ferienzielList;
    private List<Reise> reiseList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setFerienzielList(new ArrayList<>());
        readFerienzielJSON();
        setReiseList(new ArrayList<>());
        readReiseJSON();
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
     * reads all books
     * @return list of books
     */
    public List<Reise> readallReise() {
        return getReiseList();
    }

    /**
     * reads a book by its uuid
     * @param reiseUUID
     * @return the Book (null=not found)
     */
    public Reise readReisebyUUID(String reiseUUID) {
        Reise reise = null;
        for (Reise entry : getReiseList()) {
            if (entry.getReiseUUID().equals(reiseUUID)) {
                reise = entry;
            }
        }
        return reise;
    }

    /**
     * reads all Publishers
     * @return list of publishers
     */
    public List<Ferienziel> readAllFerienziel() {

        return getFerienzielList();
    }

    /**
     * reads a publisher by its uuid
     * @param ferienzielUUID
     * @return the ferienziel (null=not found)
     */
    public Ferienziel readferienzielbyUUID(String ferienzielUUID) {
        Ferienziel ferienziel = null;
        for (Ferienziel entry : getFerienzielList()) {
            if (entry.getFerienzielUUID().equals(ferienzielUUID)) {
                ferienziel = entry;
            }
        }
        return ferienziel;
    }

    /**
     * reads the books from the JSON-file
     */
    private void readReiseJSON() {
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
     * reads the publishers from the JSON-file
     */
    private void readFerienzielJSON() {
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
     * gets bookList
     *
     * @return value of bookList
     */
    private List<Reise> getReiseList() {
        return reiseList;
    }

    /**
     * sets bookList
     *
     * @param reiseList the value to set
     */
    private void setReiseList(List<Reise> reiseList) {
        this.reiseList = reiseList;
    }

    /**
     * gets publisherList
     *
     * @return value of publisherList
     */
    private List<Ferienziel> getFerienzielList() {
        return ferienzielList;
    }

    /**
     * sets publisherList
     *
     * @param ferienzielList the value to set
     */
    private void setFerienzielList(List<Ferienziel> ferienzielList) {
        this.ferienzielList = ferienzielList;
    }


}