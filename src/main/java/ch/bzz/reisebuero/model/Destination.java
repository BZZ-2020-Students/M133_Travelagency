package ch.bzz.reisebuero.model;

import lombok.*;

/**
 * a ferienziel in reisebüro
 */

@Builder
public class Destination {
    private String destinationUUID;
    private String location;
    private String street;
    private String country;

    public Destination(String destinationUUID, String location, String street, String country) {
        this.destinationUUID = destinationUUID;
        this.location = location;
        this.street = street;
        this.country = country;
    }

    public Destination() {

    }

    public String getDestinationUUID() {
        return destinationUUID;
    }

    public void setDestinationUUID(String destinationUUID) {
        this.destinationUUID = destinationUUID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

