package ch.bzz.reisebuero.service;

import ch.bzz.reisebuero.data.DataHandler;
import ch.bzz.reisebuero.model.Ferienziel;
import ch.bzz.reisebuero.model.Reise;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Path("reise")
public class Reiseservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listReise(){
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
    ){
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
    ){
        int httpStatus = 200;
        if(!DataHandler.deleteReise(reiseUUID)){
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
    @FormParam("ferienzielUUID") String ferienzielUUID,
    @FormParam("datum") Date datum,
    @FormParam("preis")  Float preis,
    @FormParam("anzPers") Integer anzPers,
    @FormParam("bewertung") Integer bewertung
    ){
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
}
