package ch.bzz.reisebuero.model;


import ch.bzz.reisebuero.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Reise {
    @JsonIgnore
    private Ferienziel ferienziel;

    private String reiseUUID;

    private Date datum;

    private Float preis;
    private Integer anzpers;
    private Integer bewertung;

    public Reise(Ferienziel ferienziel, String reiseUUID, Date datum, Float preis, Integer anzpers, Integer bewertung) {
        this.ferienziel = ferienziel;
        this.reiseUUID = reiseUUID;
        this.datum = datum;
        this.preis = preis;
        this.anzpers = anzpers;
        this.bewertung = bewertung;
    }
    public Reise(){

    }

    public String getferienzielUUID() {
        return getFerienziel().getFerienzielUUID();
    }

    /**
     * creates a Publisher-object without the booklist
     * @param ferienzielUUID
     */
    public void setFerienzielUUID(String ferienzielUUID) {
        setFerienziel( new Ferienziel());
        Ferienziel ferienziel = DataHandler.getInstance().readferienzielbyUUID(ferienzielUUID);
        getFerienziel().setFerienzielUUID(ferienzielUUID);
        getFerienziel().setFerienziel(ferienziel.getFerienziel());

    }

    public String getReiseUUID() {
        return reiseUUID;
    }

    public void setreiseUUID(String reiseUUID) {
        this.reiseUUID = reiseUUID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Float getPreis() {
        return preis;
    }

    public void setPreis(Float preis) {
        this.preis = preis;
    }

    public Ferienziel getFerienziel() {
        return ferienziel;
    }

    public void setFerienziel(Ferienziel ferienziel) {
        this.ferienziel = ferienziel;
    }

    public Integer getAnzpers() {
        return anzpers;
    }

    public void setAnzpers(Integer anzpers) {
        this.anzpers = anzpers;
    }

    public Integer getBewertung() {
        return bewertung;
    }

    public void setBewertung(Integer bewertung) {
        this.bewertung = bewertung;
    }
}
