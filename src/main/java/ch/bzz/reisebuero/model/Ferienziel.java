package ch.bzz.reisebuero.model;

import lombok.*;

/**
 *a ferienziel in reisebüro
 * */

@Builder
public class Ferienziel {
    private String ferienzielUUID;
    private String ort;
    private String strasse;
    private String land;

    public Ferienziel(String ferienzielUUID, String ort, String strasse, String land) {
        this.ferienzielUUID = ferienzielUUID;
        this.ort = ort;
        this.strasse = strasse;
        this.land = land;
    }

    public Ferienziel() {

    }

    public String getFerienzielUUID() {
        return ferienzielUUID;
    }

    public void setFerienzielUUID(String ferienzielUUID) {
        this.ferienzielUUID = ferienzielUUID;
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

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }
}

