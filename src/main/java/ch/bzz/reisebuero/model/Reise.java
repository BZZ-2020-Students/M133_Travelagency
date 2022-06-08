package ch.bzz.reisebuero.model;


import ch.bzz.reisebuero.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;



/**
 *a reise in reisebüro
 * */
@Setter
@Getter
public class Reise {
    private String reiseUUID;
    private String datum;
    private Float preis;
    private Integer anzpers;
    private Integer bewertung;
    @JsonIgnore
    private Ferienziel ferienziel;

    /**
    *constructor
    * */

    public Reise(Ferienziel ferienziel, String reiseUUID, String datum, Float preis, Integer anzpers, Integer bewertung) {
        this.ferienziel = ferienziel;
        this.reiseUUID = reiseUUID;
        this.datum = datum;
        this.preis = preis;
        this.anzpers = anzpers;
        this.bewertung = bewertung;
    }
    /**
     * empty constructor
     * */
    public Reise(){

    }
    /**
     * gets ferienzielUUID
     * @return value of ferienziel
     */

    public String getFerienzielUUID() {
        if (getFerienziel()== null) return null;
        return getFerienziel().getFerienzielUUID();
    }

    /**
     * creates a ferienziel-object without the reiseList
     * @param ferienzielUUID
     */
    public void setFerienzielUUID(String ferienzielUUID) {
        setFerienziel(new Ferienziel());
        Ferienziel ferienziel = DataHandler.readFerienzielbyUUID(ferienzielUUID);
        getFerienziel().setFerienzielUUID(ferienziel.getFerienzielUUID());
        getFerienziel().setLand(ferienziel.getLand());
        getFerienziel().setStrasse(ferienziel.getStrasse());
        getFerienziel().setOrt(ferienziel.getOrt());
    }

    public String getReiseUUID() {
        return reiseUUID;
    }

    public void setReiseUUID(String reiseUUID) {
        this.reiseUUID = reiseUUID;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Float getPreis() {
        return preis;
    }

    public void setPreis(Float preis) {
        this.preis = preis;
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

    public Ferienziel getFerienziel() {
        return ferienziel;
    }

    public void setFerienziel(Ferienziel ferienziel) {
        this.ferienziel = ferienziel;
    }
}
