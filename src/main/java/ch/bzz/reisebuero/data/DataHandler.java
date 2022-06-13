package ch.bzz.reisebuero.data;


import ch.bzz.reisebuero.model.Destination;
import ch.bzz.reisebuero.model.Journey;
import ch.bzz.reisebuero.model.Route;

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
    private static List<Journey> journeyList;
    private static List<Destination> destinationList;
    private static List<Route> routeList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setDestinationList(new ArrayList<>());
        readDestinationJSON();
        setJourneyList(new ArrayList<>());
        readJourneyJSON();
        setRouteList(new ArrayList<>());
        readRouteJSON();
    }

    /**
     * gets the only instance of this class
     *
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all reise
     *
     * @return list of books
     */
    public static List<Journey> readallJourney() {
        return getJourneyList();
    }

    /**
     * reads a reise by its uuid
     *
     * @return the reise (null=not found)
     */
    public static Journey readJourneyByUUID(String journeyUUID) {
        Journey journey = null;
        for (Journey entry : getJourneyList()) {
            if (entry.getJourneyUUID().equals(journeyUUID)) {
                journey = entry;
            }
        }
        return journey;
    }

    /**
     * inserts a new book into the reiselist
     *
     * @param journey the reise to be saved
     */
    public static void insertJourney(Journey journey) {
        getJourneyList().add(journey);
        writeJourneyJSON();
    }

    /**
     * updates the reiseList
     */
    public static void updateJourney() {
        writeJourneyJSON();
    }

    /**
     * deletes a book identified by the reiseUUID
     *
     * @param journeyUUID the key
     * @return success=true/false
     */
    public static boolean deleteJourney(String journeyUUID) {
        Journey journey = readJourneyByUUID(journeyUUID);
        if (journey != null) {
            getJourneyList().remove(journey);
            writeJourneyJSON();
            return true;
        } else {
            return false;
        }
    }


    /**
     * reads all ferienziele
     *
     * @return list of ferienziele
     */
    public static List<Destination> readAllDestination() {

        return getDestinationList();
    }

    /**
     * reads a ferienziele by its uuid
     *
     * @return the ferienziel (null=not found)
     */
    public static Destination readDestinationbyUUID(String destinationUUID) {
        Destination destination = null;
        for (Destination entry : getDestinationList()) {
            if (entry.getDestinationUUID().equals(destinationUUID)) {
                destination = entry;
            }
        }
        return destination;
    }

    /**
     * inserts a new publisher into the reiseList
     *
     * @param destination the ferienziel to be saved
     */
    public static void insertDestination(Destination destination) {
        getDestinationList().add(destination);
        writeDestinationJSON();
    }

    /**
     * updates the ferienzielList
     */
    public static void updateDestination() {
        writeDestinationJSON();
    }

    /**
     * deletes a publisher identified by the ferienzielUUID
     *
     * @param destinationUUID the key
     * @return success=true/false
     */
    public static boolean deleteDestination(String destinationUUID) {
        Destination destination = readDestinationbyUUID(destinationUUID);
        if (destination != null) {
            getDestinationList().remove(destination);
            writeDestinationJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads all strecken
     *
     * @return list of strecken
     */
    public static List<Route> readallRoute() {
        return getRouteList();
    }

    /**
     * reads the strecke from the JSON-file
     */
    public static Route readRoutebyUUID(String routeUUID) {
        Route route = null;
        for (Route entry : getRouteList()) {
            if (entry.getRouteUUID().equals(routeUUID)) {
                route = entry;
            }
        }
        return route;
    }

    public static void insertRoute(Route route) {
        getRouteList().add(route);
        writeRouteJSON();
    }

    /**
     * updates the StreckeList
     */
    public static void updateRoute() {
        writeRouteJSON();
    }

    /**
     * deletes a publisher identified by the ferienzielUUID
     *
     * @param routeUUID the key
     * @return success=true/false
     */
    public static boolean deleteRoute(String routeUUID) {
        Route route = readRoutebyUUID(routeUUID);
        if (route != null) {
            getRouteList().remove(route);
            writeRouteJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads the ferienziel from the JSON-file
     */
    private static void readJourneyJSON() {
        try {
            String path = Config.getProperty("journeyJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Journey[] journeys = objectMapper.readValue(jsonData, Journey[].class);
            for (Journey journey : journeys) {
                getJourneyList().add(journey);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the bookList to the JSON-file
     */
    private static void writeJourneyJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String journeyPath = Config.getProperty("journeyJSON");
        try {
            fileOutputStream = new FileOutputStream(journeyPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getJourneyList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the ferienziel from the JSON-file
     */
    private static void readDestinationJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("destinationJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Destination[] destinations = objectMapper.readValue(jsonData, Destination[].class);
            for (Destination destination : destinations) {
                getDestinationList().add(destination);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * writes the publisherList to the JSON-file
     */
    private static void writeDestinationJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String destinationPath = Config.getProperty("destinationJSON");
        try {
            fileOutputStream = new FileOutputStream(destinationPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getDestinationList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void writeRouteJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String routePath = Config.getProperty("streckeJSON");
        try {
            fileOutputStream = new FileOutputStream(routePath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getRouteList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * reads the strecke from the JSON-file
     */
    private static void readRouteJSON() {
        try {
            String path = Config.getProperty("routeJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Route[] routes = objectMapper.readValue(jsonData, Route[].class);
            for (Route route : routes) {
                getRouteList().add(route);
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
    private static List<Journey> getJourneyList() {
        if (journeyList == null) {
            setJourneyList(new ArrayList<>());
            readJourneyJSON();
        }
        return journeyList;
    }

    /**
     * sets reiselist
     *
     * @param journeyList the value to set
     */
    private static void setJourneyList(List<Journey> journeyList) {
        DataHandler.journeyList = journeyList;
    }

    /**
     * gets ferienziellist
     *
     * @return value of ferienzielList
     */
    private static List<Destination> getDestinationList() {
        if (destinationList == null) {
            setDestinationList(new ArrayList<>());
            readDestinationJSON();
            readJourneyJSON();
        }

        return destinationList;
    }

    /**
     * sets ferienziellist
     *
     * @param destinationList the value to set
     */
    private static void setDestinationList(List<Destination> destinationList) {
        DataHandler.destinationList = destinationList;
    }

    /**
     * gets streckenlist
     *
     * @return value of streckenlist
     */

    public static List<Route> getRouteList() {
        if (routeList == null) {
            setRouteList(new ArrayList<>());
            readRouteJSON();
            readJourneyJSON();
        }

        return routeList;
    }


    /**
     * sets streckeList
     *
     * @param routeList the value to set
     */
    public static void setRouteList(List<Route> routeList) {
        DataHandler.routeList = routeList;
    }
}