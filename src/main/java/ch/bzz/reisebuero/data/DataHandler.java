package ch.bzz.reisebuero.data;


import ch.bzz.reisebuero.model.Destination;
import ch.bzz.reisebuero.model.Journey;
import ch.bzz.reisebuero.model.Route;

import ch.bzz.reisebuero.model.User;
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
    private static List<Journey> journeyList;
    private static List<Destination> destinationList;
    private static List<Route> routeList;
    private static List<User> userList;

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
        setUserList(new ArrayList<>());
        readUserJSON();
    }





    /**
     * reads all jounrey
     *
     * @return list of journeys
     */
    public static List<Journey> readallJourney() {
        return getJourneyList();
    }

    /**
     * reads a journey by its uuid
     *
     * @return the journey (null=not found)
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
     * inserts a new journey into the journeyList
     *
     * @param journey the journey to be saved
     */
    public static void insertJourney(Journey journey) {
        getJourneyList().add(journey);
        writeJourneyJSON();
    }

    /**
     * updates the journeyList
     */
    public static void updateJourney() {
        writeJourneyJSON();
    }

    /**
     * deletes a journey identified by the journeyUUID
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
     * reads all destination
     *
     * @return list of destination
     */
    public static List<Destination> readAllDestination() {

        return getDestinationList();
    }

    /**
     * reads a destination by its uuid
     * @param destinationUUID
     * @return the destination (null=not found)
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
     * inserts a new destination into the journeyList
     *
     * @param destination the destination to be saved
     */
    public static void insertDestination(Destination destination) {
        getDestinationList().add(destination);
        writeDestinationJSON();
    }

    /**
     * updates the destination
     */
    public static void updateDestination() {
        writeDestinationJSON();
    }

    /**
     * deletes a destination identified by the destinationUUID
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
     * reads all routes
     * @return list of routes
     */
    public static List<Route> readallRoute() {
        return getRouteList();
    }

    /**
     * reads the route from the JSON-file
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

    /**
     * inserts a new route into the routelist
     * @param route the route to be saved
     * */
    public static void insertRoute(Route route) {
        getRouteList().add(route);
        writeRouteJSON();
    }

    /**
     * updates the RouteList
     */
    public static void updateRoute() {
        writeRouteJSON();
    }

    /**
     * deletes a route identified by the routeUUID
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
     * reads the journey from the JSON-file
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
     * writes the journeyList to the JSON-file
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
     * reads the destination from the JSON-file
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
     * writes the destinationList to the JSON-file
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

    /**
     * writes the routeList to the JSON-File
     * */
    private static void writeRouteJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String routePath = Config.getProperty("routeJSON");
        try {
            fileOutputStream = new FileOutputStream(routePath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getRouteList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * reads the route from the JSON-file
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
     * gets journeyList
     *
     * @return value of journeyList
     */
    private static List<Journey> getJourneyList() {
        if (journeyList == null) {
            setJourneyList(new ArrayList<>());
            readJourneyJSON();
        }
        return journeyList;
    }

    /**
     * sets journeyList
     *
     * @param journeyList the value to set
     */
    private static void setJourneyList(List<Journey> journeyList) {
        DataHandler.journeyList = journeyList;
    }

    /**
     * gets destinationList
     *
     * @return value of destinationList
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
     * sets destinationList
     *
     * @param destinationList the value to set
     */
    private static void setDestinationList(List<Destination> destinationList) {
        DataHandler.destinationList = destinationList;
    }

    /**
     * gets routeList
     *
     * @return value of routeList
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
     * sets routeList
     *
     * @param routeList the value to set
     */
    public static void setRouteList(List<Route> routeList) {
        DataHandler.routeList = routeList;
    }

    public static String readUserRole(String username, String password) {
        for (User user : getUserList()) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                return user.getUsername();
            }
        }
        return "guest";
    }

    /**
     * reads the users from the JSON-file
     */
    private static void readUserJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("userJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonData, User[].class);
            for (User user : users) {
                getUserList().add(user);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets userList
     *
     * @return value of userList
     */

    public static List<User> getUserList() {
        if (DataHandler.userList == null) {
            DataHandler.setUserList(new ArrayList<>());
            readUserJSON();
        }
        return userList;
    }

    /**
     * reads a user by the username/password provided
     *
     * @param username
     * @param password
     * @return user-object
     */
    public static User readUser(String username, String password) {
        User user = new User();
        for (User entry : getUserList()) {
            if (entry.getUsername().equals(username) &&
                    entry.getPassword().equals(password)) {
                user = entry;
            }
        }
        return user;
    }

    /**
     * sets userList
     *
     * @param userList the value to set
     */

    public static void setUserList(List<User> userList) {
        DataHandler.userList = userList;
    }


}