package ch.bzz.reisebuero.model;

import ch.bzz.reisebuero.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


/**
 * a route in Travelagency
 */
@Getter
@Setter
public class Route {
    private String routeUUID;
    private Float distance;
    @JsonIgnore
    private Journey journey;

    /**
     * a constructor
     * */
    public Route(String routeUUID, Float distance, Journey journey) {
        this.routeUUID = routeUUID;
        this.distance = distance;
        this.journey = journey;
    }
    /**
     *default constructor
     * */
    public Route(){

    }

    /**
     * gets journeyUUID
     * @return value of journeyUUID
     */
    public String getJourneyUUID() {
        return getJourney().getJourneyUUID();
    }

    /**
     * creates a journey-object without the journeyList
     * @param journeyUUID
     */
    public void setJourneyUUID(String journeyUUID) {
        setJourney( new Journey());
        Journey journey = DataHandler.readJourneyByUUID(journeyUUID);
        getJourney().setJourneyUUID(journeyUUID);
        setJourney(journey);
    }

    /**
     * gets routUUID
     *
     * @return value of routUUID
     */
    public String getRouteUUID() {
        return routeUUID;
    }

    /**
     * sets routeUUID
     *
     * @param routeUUID the value to set
     */
    public void setRouteUUID(String routeUUID) {
        this.routeUUID = routeUUID;
    }

    /**
     * gets distance
     *
     * @return value of distance
     */
    public Float getDistance() {
        return distance;
    }

    /**
     * sets distance
     *
     * @param distance the value to set
     */
    public void setDistance(Float distance) {
        this.distance = distance;
    }

    /**
     * gets journey
     *
     * @return value of journey
     */
    public Journey getJourney() {
        return journey;
    }

    /**
     * sets journey
     *
     * @param journey the value to set
     */
    public void setJourney(Journey journey) {
        this.journey = journey;
    }
}
