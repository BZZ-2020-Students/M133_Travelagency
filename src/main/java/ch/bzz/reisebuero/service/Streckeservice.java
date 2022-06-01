package ch.bzz.reisebuero.service;

import ch.bzz.reisebuero.data.DataHandler;
import ch.bzz.reisebuero.model.Ferienziel;
import ch.bzz.reisebuero.model.Strecke;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
@Path("strecke")
public class Streckeservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listStrecke(){
        List<Strecke> streckeList = DataHandler.readallStrecke();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(streckeList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(500)
                    .entity("Fehler beim Serialisieren der Strecken")
                    .build();
        }
    }

    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readReise(
            @PathParam("uuid") String streckeUUID
    ){
        Strecke strecke = DataHandler.readStreckebyUUID(streckeUUID);
        if(strecke== null){
            return Response
                    .status(404)
                    .entity("Strecke nicht gefunden")
                    .build();
        }
        return Response
                .status(200)
                .entity(strecke)
                .build();
    }
    @DELETE
    @Path("delete/{uuid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteStrecke(
            @PathParam("uuid") String streckeUUID
    ){
        int httpStatus = 200;
        if(!DataHandler.deleteStrecke(streckeUUID)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
