package ch.bzz.reisebuero.service;

import ch.bzz.reisebuero.data.DataHandler;
import ch.bzz.reisebuero.model.Strecke;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

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
    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertReise(
            @FormParam("reiseUUID") String reiseUUID,
            @FormParam("distanz") Float distanz
    ){

        int httpStatus = 200;
        
        Strecke strecke = new Strecke();
        strecke.setStreckeUUID(String.valueOf(UUID.randomUUID()));
        strecke.setDistanz(distanz);
        strecke.setReiseUUID(reiseUUID);
        DataHandler.insertStrecke(strecke);



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
                    String streckeUUID,

            @FormParam("distanz")
            @NotEmpty
            @Min(value = 1)
            @Max(value = 5)
                    //@Range(min=1,max=5)
                    Float distanz,

            @FormParam("UUID")
            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
                    String reiseUUID





    ) {
        int httpStatus = 200;
        Strecke strecke = DataHandler.readStreckebyUUID(streckeUUID);
        if (strecke != null) {
            strecke.setStreckeUUID(streckeUUID);
            strecke.setDistanz(distanz);
            strecke.setReiseUUID(reiseUUID);
            DataHandler.updateStrecke();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("wurde geupdated")
                .build();

    }
}
