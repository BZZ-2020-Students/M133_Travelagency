package ch.bzz.reisebuero.model;

import java.math.BigDecimal;

public class Strecke {
    private String streckeUUID;
    private BigDecimal distanz;
    private Reise reise;

    public String getStreckeUUID() {
        return streckeUUID;
    }

    public void setStreckeUUID(String streckeUUID) {
        this.streckeUUID = streckeUUID;
    }

    public BigDecimal getDistanz() {
        return distanz;
    }

    public void setDistanz(BigDecimal distanz) {
        this.distanz = distanz;
    }

    public Reise getReise() {
        return reise;
    }

    public void setReise(Reise reise) {
        this.reise = reise;
    }
}
