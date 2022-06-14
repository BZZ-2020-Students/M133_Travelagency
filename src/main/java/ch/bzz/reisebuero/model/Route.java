package ch.bzz.reisebuero.model;

import ch.bzz.reisebuero.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Route {
    @JsonIgnore
    private String routeUUID;
    private Float distance;
    private Journey journey;

    /**
     * constructor
     * */
    public Route(String routeUUID, Float distance, Journey journey) {
        this.routeUUID = routeUUID;
        this.distance = distance;
        this.journey = journey;
    }
    /**
     * empty constructor
     * */
    public Route(){

    }
    /**
     * gets reiseuuid
     * @return value of reiseuuid
     */
    public String getJourneyUUID() {
        return getJourney().getJourneyUUID();
    }
    /**
     * creates a reise-object without the reiseList
     * @param journeyUUID
     */
    public void setJourneyUUID(String journeyUUID) {
        setJourney( new Journey());
        Journey journey = DataHandler.readJourneyByUUID(journeyUUID);
        getJourney().setJourneyUUID(journeyUUID);
        setJourney(journey);
    }

    public String getRouteUUID() {
        return routeUUID;
    }

    public void setRouteUUID(String routeUUID) {
        this.routeUUID = routeUUID;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Journey getReise() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }
}
