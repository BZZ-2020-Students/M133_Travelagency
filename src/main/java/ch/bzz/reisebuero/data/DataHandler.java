package ch.bzz.reisebuero.data;



import ch.bzz.reisebuero.model.Ferienziel;
import ch.bzz.reisebuero.model.Reise;
import ch.bzz.reisebuero.model.Strecke;
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
    private List<Reise> reiseList;
    private List<Ferienziel> ferienzielList;
    private List<Strecke> streckeList;

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
    public Ferienziel readFerienzielbyUUID(String ferienzielUUID) {
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
     * reads the ferienziel from the JSON-file
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
     * reads all strecken
     * @return list of strecken
     */
    public List<Strecke> readallStrecke() {
        return getStreckeList();
    }

    /**
     * reads the strecke from the JSON-file
     */
    public Strecke readStreckebyUUID(String streckeUUID) {
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
    private void readStreckeJSON() {
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
    private List<Reise> getReiseList() {
        return reiseList;
    }

    /**
     * sets reiselist
     *
     * @param reiseList the value to set
     */
    private void setReiseList(List<Reise> reiseList) {
        this.reiseList = reiseList;
    }

    /**
     * gets ferienziellist
     *
     * @return value of ferienzielList
     */
    private List<Ferienziel> getFerienzielList() {
        return ferienzielList;
    }

    /**
     * sets ferienziellist
     *
     * @param ferienzielList the value to set
     */
    private void setFerienzielList(List<Ferienziel> ferienzielList) {
        this.ferienzielList = ferienzielList;
    }
    /**
     * gets streckenlist
     *
     * @return value of streckenlist
     */

    public List<Strecke> getStreckeList() {
        return streckeList;
    }


    /**
     * sets streckeList
     *
     * @param streckeList the value to set
     */
    public void setStreckeList(List<Strecke> streckeList) {
        this.streckeList = streckeList;
    }
}