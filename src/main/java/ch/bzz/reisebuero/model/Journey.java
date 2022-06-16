package ch.bzz.reisebuero.model;


import ch.bzz.reisebuero.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


/**
 * a journey in Travelagency
 */
@Setter
@Getter
public class Journey {
    private String journeyUUID;
    private String date;
    private Float price;
    private Integer numbPers;
    private Integer rating;
    @JsonIgnore
    private Destination destination;

    /**
     * a constructor
     */
    public Journey(Destination destination, String journeyUUID, String date, Float price, Integer numbPers, Integer rating) {
        this.destination = destination;
        this.journeyUUID = journeyUUID;
        this.date = date;
        this.price = price;
        this.numbPers = numbPers;
        this.rating = rating;
    }

    /**
     * default constructor
     */
    public Journey() {

    }

    /**
     * gets destinationUUID
     *
     * @return value of destinationUUID
     */
    public String getDestinationUUID() {
        if (getDestination() == null) return null;
        return getDestination().getDestinationUUID();
    }

    /**
     * creates a destination-object without the journeyList
     *
     * @param destinationUUID
     */
    public void setDestinationUUID(String destinationUUID) {
        setDestination(new Destination());
        Destination destination = DataHandler.readDestinationbyUUID(destinationUUID);
        getDestination().setDestinationUUID(destination.getDestinationUUID());
        getDestination().setCountry(destination.getCountry());
        getDestination().setStreet(destination.getStreet());
        getDestination().setLocation(destination.getLocation());
    }

    /**
     * gets journeyUUID
     *
     * @return value of journeyUUID
     */
    public String getJourneyUUID() {
        return journeyUUID;
    }

    /**
     * sets journeyUUID
     *
     * @param journeyUUID the value to set
     */
    public void setJourneyUUID(String journeyUUID) {
        this.journeyUUID = journeyUUID;
    }

    /**
     * gets date
     *
     * @return value of date
     */
    public String getDate() {
        return date;
    }

    /**
     * sets date
     *
     * @param date the value to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * gets price
     *
     * @return value of price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * sets price
     *
     * @param price the value to set
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * gets numbPers
     *
     * @return value of numbPers
     */
    public Integer getNumbPers() {
        return numbPers;
    }

    /**
     * sets numbPers
     *
     * @param numbPers the value to set
     */
    public void setNumbPers(Integer numbPers) {
        this.numbPers = numbPers;
    }

    /**
     * gets rating
     *
     * @return value of rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * sets rating
     *
     * @param rating the value to set
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * gets destination
     *
     * @return value of destination
     */
    public Destination getDestination() {
        return destination;
    }

    /**
     * sets destination
     *
     * @param destination the value to set
     */
    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
