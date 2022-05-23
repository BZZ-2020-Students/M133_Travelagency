package ch.bzz.reisebuero.model;


import ch.bzz.reisebuero.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;



/**
 *a reise in reisebüro
 * */
@Setter
@Getter
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

    public String getFerienzielUUID() {
        return getFerienziel().getFerienzielUUID();
    }

    /**
     * creates a ferienziel-object without the reiseList
     * @param ferienzielUUID
     */
    public void setFerienzielUUID(String ferienzielUUID) {
        setFerienziel( new Ferienziel());
        Ferienziel ferienziel = DataHandler.getInstance().readFerienzielbyUUID(ferienzielUUID);
        if(ferienziel != null){
        getFerienziel().setFerienzielUUID(ferienzielUUID);
        setFerienziel(ferienziel);
        }
        //System.out.println("ferienziel = " + ferienziel);
        //setFerienziel(ferienziel);
    }



}
