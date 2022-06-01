package ch.bzz.reisebuero.service;

import ch.bzz.reisebuero.data.DataHandler;
import ch.bzz.reisebuero.model.Ferienziel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
@Path("ferienziel")
public class Ferienzielservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFerienziel(){
        List<Ferienziel> ferienzielList = DataHandler.readAllFerienziel();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(ferienzielList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(500)
                    .entity("Fehler beim Serialisieren der Ferienziele")
                    .build();
        }
    }

    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readFerienziel(
            @PathParam("uuid") String ferienzielUUID
    ){
        int httpStatus = 200;
        Ferienziel ferienziel = DataHandler.readFerienzielbyUUID(ferienzielUUID);
        if(ferienziel== null){
            return Response
                    .status(404)
                    .entity("ferienziel nicht gefunden")
                    .build();
        }
        return Response
                .status(200)
                .entity(ferienziel)
                .build();
    }
    @DELETE
    @Path("delete/{uuid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteFerienziel(
            @PathParam("uuid") String ferienzielUUID
    ){
        int httpStatus = 200;
        if(!DataHandler.deleteFerienziel(ferienzielUUID)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("Wurde gelöst")
                .build();
    }
}
