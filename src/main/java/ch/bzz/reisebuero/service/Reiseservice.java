package ch.bzz.reisebuero.service;

import ch.bzz.reisebuero.data.DataHandler;
import ch.bzz.reisebuero.model.Reise;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("reise")
public class Reiseservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listReise() {
        List<Reise> reiseList = DataHandler.readallReise();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(reiseList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(500)
                    .entity("Fehler beim Serialisieren der Reisen")
                    .build();
        }
    }

    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readReise(
            @PathParam("uuid") String reiseUUID
    ) {
        Reise reise = DataHandler.readReisebyUUID(reiseUUID);
        if (reise == null) {
            return Response
                    .status(404)
                    .entity("Reise nicht gefunden")
                    .build();
        }
        return Response
                .status(200)
                .entity(reise)
                .build();
    }

    @DELETE
    @Path("delete/{uuid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteReise(
            @PathParam("uuid") String reiseUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteReise(reiseUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("wurde gelöscht")
                .build();
    }

    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertReise(
            @FormParam("ferienzielUUID")
            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
                    String ferienzielUUID,

            @FormParam("datum")
            @NotEmpty
            @Pattern(regexp = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
                    + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$")
                    String datum,

            @FormParam("preis")
            @NotEmpty
            @DecimalMax(value = "99999.95")
            @DecimalMin(value = "0.05")
                    Float preis,

            @FormParam("anzPers")
            @NotEmpty
            @Min(value = 1)
            @Max(value = 9)
                    Integer anzPers,

            @FormParam("bewertung")
            @NotEmpty
            @Min(value = 1)
            @Max(value = 5)
                    //@Range(min=1,max=5)
                    Integer bewertung
    ) {
        Reise reise = new Reise();
        reise.setReiseUUID(String.valueOf(UUID.randomUUID()));
        reise.setFerienzielUUID(ferienzielUUID);
        reise.setDatum(datum);
        reise.setPreis(preis);
        reise.setAnzpers(anzPers);
        reise.setBewertung(bewertung);
        DataHandler.insertReise(reise);


        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("wurde erschaffen")
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateReise(
            @FormParam("UUID")
            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
                    String reiseUUID,

            @FormParam("ferienzielUUID")
            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
                    String ferienzielUUID,

            @FormParam("datum")
            @NotEmpty
            @Pattern(regexp = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
                    + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$")
                    String datum,

            @FormParam("preis")
            @NotEmpty
            @DecimalMax(value = "99999.95")
            @DecimalMin(value = "0.05")
                    Float preis,

            @FormParam("anzPers")
            @NotEmpty
            @Min(value = 1)
            @Max(value = 9)
                    Integer anzPers,

            @FormParam("bewertung")
            @NotEmpty
            @Min(value = 1)
            @Max(value = 5)
                    //@Range(min=1,max=5)
                    Integer bewertung


    ) {
        int httpStatus = 200;
        Reise reise = DataHandler.readReisebyUUID(reiseUUID);
        if (reise != null) {
            reise.setReiseUUID(reiseUUID);
            reise.setFerienzielUUID(ferienzielUUID);
            reise.setDatum(datum);
            reise.setPreis(preis);
            reise.setAnzpers(anzPers);
            reise.setBewertung(bewertung);
            DataHandler.updateReise();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("wurde geupdated")
                .build();

    }
}
