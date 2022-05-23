package ch.bzz.reisebuero.model;

/**
 *
 * */
public class Ferienziel {
    private String ferienzielUUID;
    private String ferienziel;

    private String ort;
    private String strasse;
    private String land;

    public Ferienziel(String ferienzielUUID, String ferienziel, String ort, String strasse, String land) {
        this.ferienzielUUID = ferienzielUUID;
        this.ferienziel = ferienziel;
        this.ort = ort;
        this.strasse = strasse;
        this.land = land;
    }
    /**
     * empty constructor
     * */
    public Ferienziel()
    {

    }

    /**
     * gets ferienzielUUID
     * @return value of ferienzielUUID
     */
    public String getFerienzielUUID() {
        return ferienzielUUID;
    }
    /**
     * sets ferienzielUUID
     * @param ferienzielUUID the value to set
     */
    public void setFerienzielUUID(String ferienzielUUID) {
        this.ferienzielUUID = ferienzielUUID;
    }

    /**
     * gets ferienziel
     * @return value of ferienziel
     */
    public String getFerienziel() {
        return ferienziel;
    }

    /**
     * sets ferienziel
     * @param ferienziel the value to set
     */
    public void setFerienziel(String ferienziel) {
        this.ferienziel = ferienziel;
    }


    /**
     * gets land
     * @return value of land
     */
    public String getLand() {
        return land;
    }

    /**
     * sets land
     * @param land the value to set
     */
    public void setLand(String land) {
        this.land = land;
    }

    /**
     * gets ort
     * @return value of ort
     */
    public String getOrt() {
        return ort;
    }

    /**
     * sets ort
     * @param ort the value to set
     */
    public void setOrt(String ort) {
        this.ort = ort;
    }

    /**
     * gets strasse
     * @return value of strasse
     */
    public String getStrasse() {
        return strasse;
    }

    /**
     * sets strasse
     * @param strasse the value to set
     */
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }
}
