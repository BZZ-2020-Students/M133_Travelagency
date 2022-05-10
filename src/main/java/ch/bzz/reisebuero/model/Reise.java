package ch.bzz.reisebuero.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reise {
    private LocalDate datum;
    private BigDecimal preis;
    private Ferienziel ferienziel;
    private Integer anzpers;
    private Integer bewertung;

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
