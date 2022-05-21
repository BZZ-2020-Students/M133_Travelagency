package ch.bzz.reisebuero.model;

import java.math.BigDecimal;

public class Strecke {
    private String streckeUUID;
    private Float distanz;
    private Reise reise;

    public Strecke(String streckeUUID, Float distanz, Reise reise) {
        this.streckeUUID = streckeUUID;
        this.distanz = distanz;
        this.reise = reise;
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

    public Reise getReise() {
        return reise;
    }

    public void setReise(Reise reise) {
        this.reise = reise;
    }
}
