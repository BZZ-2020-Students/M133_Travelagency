package ch.bzz.reisebuero.model;


import ch.bzz.reisebuero.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


/**
 * a journey in reisebüro
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
     * constructor
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
     * empty constructor
     */
    public Journey() {

    }

    /**
     * gets ferienzielUUID
     *
     * @return value of ferienziel
     */

    public String getDestinationUUID() {
        if (getDestination() == null) return null;
        return getDestination().getDestinationUUID();
    }

    /**
     * creates a ferienziel-object without the reiseList
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

    public String getJourneyUUID() {
        return journeyUUID;
    }

    public void setJourneyUUID(String journeyUUID) {
        this.journeyUUID = journeyUUID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getNumbPers() {
        return numbPers;
    }

    public void setNumbPers(Integer numbPers) {
        this.numbPers = numbPers;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
