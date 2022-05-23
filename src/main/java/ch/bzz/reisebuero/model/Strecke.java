package ch.bzz.reisebuero.model;

import ch.bzz.reisebuero.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Strecke {
    private String streckeUUID;
    private Float distanz;
    @JsonIgnore
    private Reise reise;

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
    
    public String getReiseUUID() {
        return getReise().getReiseUUID();
    }

    public void setReiseUUID(String reiseUUID) {
        setReise( new Reise());
        Reise reise = DataHandler.getInstance().readReisebyUUID(reiseUUID);
        getReise().setReiseUUID(reiseUUID);
        setReise(reise);
    }
}
