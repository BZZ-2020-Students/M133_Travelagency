package ch.bzz.reisebuero.model;

import jakarta.activation.DataHandler;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reise {
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
     * @param publisherUUID
     */
    public void setferienzielUUID(String publisherUUID) {
        setFerienziel( new Ferienziel());
        Ferienziel ferienziel = DataHandler.getInstance().readPublisherByUUID(publisherUUID);
        getFerienziel().setFerienzielUUID(ferienzielUUID);
        getFerienziel().setFerienziel(Ferienziel.getferienziel());

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
