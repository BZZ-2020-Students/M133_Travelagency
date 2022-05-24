package ch.bzz.reisebuero.model;

import lombok.Getter;
import lombok.Setter;

/**
 *a ferienziel in reisebüro
 * */
@Setter
@Getter
public class Ferienziel {
    private String ferienzielUUID;
    private String ferienziel;

    private String ort;
    private String strasse;
    private String land;

    /**
     *constructor
     * */

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


}
