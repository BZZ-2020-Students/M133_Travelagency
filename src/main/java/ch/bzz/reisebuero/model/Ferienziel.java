package ch.bzz.reisebuero.model;

import lombok.*;

/**
 *a ferienziel in reisebüro
 * */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ferienziel {
    private String ferienzielUUID;
    private String ferienziel;

    private String ort;
    private String strasse;
    private String land;
}
