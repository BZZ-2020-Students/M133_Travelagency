package ch.bzz.reisebuero.model;

import ch.bzz.reisebuero.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reise {
    @JsonIgnore
    private Ferienziel ferienziel;

    private String reiseUUID;
    private LocalDate datum;
    private BigDecimal preis;
    private Integer anzpers;
    private Integer bewertung;

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

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public void setPreis(BigDecimal preis) {
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
