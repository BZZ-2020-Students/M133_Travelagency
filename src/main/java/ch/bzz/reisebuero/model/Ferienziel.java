package ch.bzz.reisebuero.model;

public class Ferienziel {
    private String ferienzielUUID;
    private String ferienziel;

    private String ort;
    private String strasse;
    private String land;

    public String getFerienzielUUID() {
        return ferienzielUUID;
    }

    public void setFerienzielUUID(String ferienzielUUID) {
        this.ferienzielUUID = ferienzielUUID;
    }
    public String getFerienziel() {
        return ferienziel;
    }

    public void setFerienziel(String ferienziel) {
        this.ferienziel = ferienziel;
    }

    public String getLandand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }
}
