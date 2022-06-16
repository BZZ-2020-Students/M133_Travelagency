package ch.bzz.reisebuero.model;

import lombok.*;

/**
 * a destination in Travelagency
 */
@Builder
public class Destination {
    private String destinationUUID;
    private String location;
    private String street;
    private String country;


    /**
     * a constructor
     */
    public Destination(String destinationUUID, String location, String street, String country) {
        this.destinationUUID = destinationUUID;
        this.location = location;
        this.street = street;
        this.country = country;
    }

    /**
     * a default constructor
     */
    public Destination() {

    }

    /**
     * gets destinationUUId
     *
     * @return value of desinationUUID
     */
    public String getDestinationUUID() {
        return destinationUUID;
    }

    /**
     * sets destinationUUID
     *
     * @param destinationUUID the value to set
     */
    public void setDestinationUUID(String destinationUUID) {
        this.destinationUUID = destinationUUID;
    }

    /**
     * gets location
     *
     * @return value of location
     */
    public String getLocation() {
        return location;
    }

    /**
     * sets location
     *
     * @param location the value to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * gets street
     *
     * @return value of street
     */
    public String getStreet() {
        return street;
    }

    /**
     * sets street
     *
     * @param street the value to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * gets country
     *
     * @return value of country
     */
    public String getCountry() {
        return country;
    }

    /**
     * sets country
     *
     * @param country the value to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
}

