package ch.bzz.reisebuero.model;


import ch.bzz.reisebuero.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

/**
 *a reise in reisebüro
 * */
public class Reise {
    @JsonIgnore
    private Ferienziel ferienziel;
    private String reiseUUID;
    private Date datum;
    private Float preis;
    private Integer anzpers;
    private Integer bewertung;

    /**
    *
    * */

    public Reise(Ferienziel ferienziel, String reiseUUID, Date datum, Float preis, Integer anzpers, Integer bewertung) {
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

    public String getferienzielUUID() {
        return getFerienziel().getFerienzielUUID();
    }

    /**
     * creates a ferienziel-object without the reiseList
     * @param ferienzielUUID
     */
    public void setFerienzielUUID(String ferienzielUUID) {
        setFerienziel( new Ferienziel());
        Ferienziel ferienziel = DataHandler.getInstance().readferienzielbyUUID(ferienzielUUID);
        getFerienziel().setFerienzielUUID(ferienzielUUID);
        getFerienziel().setFerienziel(ferienziel.getFerienziel());

        public void setFahrzeugUUID(Vector<String> fahrzeugUUIDs) {
            fahrzeugliste = new Vector<>();
            if(fahrzeugUUIDs != null) {
                for (String fahrzeugUUID : fahrzeugUUIDs) {
                    Fahrzeug fahrzeug = DataHandler.getInstance().readFahrzeugByUUID(fahrzeugUUID);
                    fahrzeugliste.add(fahrzeug);
                }
            }


        }
    }
    /**
     * gets reiseUUID
     * @return value of reiseUUID
     */
    public String getReiseUUID() {
        return reiseUUID;
    }


    /**
     * sets reiseUUID
     * @param reiseUUID the value to set
     */
    public void setreiseUUID(String reiseUUID) {
        this.reiseUUID = reiseUUID;
    }

    /**
     * gets datum
     * @return value of datum
     */
    public Date getDatum() {
        return datum;
    }

    /**
     * sets datum
     * @param datum the value to set
     */
    public void setDatum(Date datum) {
        this.datum = datum;
    }

    /**
     * gets preis
     * @return value of preis
     */
    public Float getPreis() {
        return preis;
    }

    /**
     * sets preis
     * @param preis the value to set
     */
    public void setPreis(Float preis) {
        this.preis = preis;
    }

    /**
     * gets ferienziel
     * @return value of ferienziel
     */
    public Ferienziel getFerienziel() {
        return ferienziel;
    }

    /**
     * sets ferienziel
     * @param ferienziel the value to set
     */
    public void setFerienziel(Ferienziel ferienziel) {
        this.ferienziel = ferienziel;
    }

    /**
     * gets anzahl Personen
     * @return value of anzahl Personen
     */
    public Integer getAnzpers() {
        return anzpers;
    }
    /**
     * sets anzahl Personen
     * @param anzpers the value to set
     */
    public void setAnzpers(Integer anzpers) {
        this.anzpers = anzpers;
    }

    /**
     * gets bewertung
     * @return value of bewertung
     */
    public Integer getBewertung() {
        return bewertung;
    }
    /**
     * sets bewertung
     * @param bewertung the value to set
     */
    public void setBewertung(Integer bewertung) {
        this.bewertung = bewertung;
    }
}
