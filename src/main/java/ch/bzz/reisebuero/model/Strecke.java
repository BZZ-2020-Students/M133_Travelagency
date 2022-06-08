package ch.bzz.reisebuero.model;

import ch.bzz.reisebuero.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Strecke {
    private String streckeUUID;
    private Float distanz;
    @JsonIgnore
    private Reise reise;

    /**
     * constructor
     * */
    public Strecke(String streckeUUID, Float distanz, Reise reise) {
        this.streckeUUID = streckeUUID;
        this.distanz = distanz;
        this.reise = reise;
    }
    /**
     * empty constructor
     * */
    public Strecke(){

    }
    /**
     * gets reiseuuid
     * @return value of reiseuuid
     */
    public String getReiseUUID() {
        return getReise().getReiseUUID();
    }
    /**
     * creates a reise-object without the reiseList
     * @param reiseUUID
     */
    public void setReiseUUID(String reiseUUID) {
        setReise( new Reise());
        Reise reise = DataHandler.readReisebyUUID(reiseUUID);
        getReise().setReiseUUID(reiseUUID);
        setReise(reise);
    }

    public String getStreckeUUID() {
        return streckeUUID;
    }

    public void setStreckeUUID(String streckeUUID) {
        this.streckeUUID = streckeUUID;
    }

    public Float getDistanz() {
        return distanz;
    }

    public void setDistanz(Float distanz) {
        this.distanz = distanz;
    }

    public ch.bzz.reisebuero.model.Reise getReise() {
        return reise;
    }

    public void setReise(ch.bzz.reisebuero.model.Reise reise) {
        this.reise = reise;
    }
}
