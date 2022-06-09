package ch.bzz.reisebuero.service;

import ch.bzz.reisebuero.data.DataHandler;
import ch.bzz.reisebuero.model.Ferienziel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("ferienziel")
public class Ferienzielservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFerienziel() {
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
    ) {
        Ferienziel ferienziel = DataHandler.readFerienzielbyUUID(ferienzielUUID);
        if (ferienziel == null) {
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
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteFerienziel(ferienzielUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("Wurde gelöst")
                .build();
    }

    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertReise(
            @FormParam("ort")
            @NotEmpty
            @Size(min = 2, max = 40)
                    String ort,

            @FormParam("strasse")
            @NotEmpty
            @Size(min = 2, max = 40)
                    String strasse,

            @FormParam("land")
            @NotEmpty
            @Size(min = 2, max = 40)
                    String land
    ) {
        Ferienziel ferienziel = new Ferienziel();
        ferienziel.setFerienzielUUID(String.valueOf(UUID.randomUUID()));
        ferienziel.setOrt(ort);
        ferienziel.setStrasse(strasse);
        ferienziel.setLand(land);
        DataHandler.insertFerienziel(ferienziel);


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
            @FormParam("ferienzielUUID")
            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
                    String ferienzielUUID,

            @FormParam("ort")
            @NotEmpty
            @Size(min = 2, max = 40)
                    String ort,

            @FormParam("strasse")
            @NotEmpty
            @Size(min = 2, max = 40)
                    String strasse,

            @FormParam("land")
            @NotEmpty
            @Size(min = 2, max = 40)
                    String land


    ) {
        int httpStatus = 200;
        Ferienziel ferienziel = DataHandler.readFerienzielbyUUID(ferienzielUUID);
        if (ferienziel != null) {
            ferienziel.setFerienzielUUID(ferienzielUUID);
            ferienziel.setOrt(ort);
            ferienziel.setStrasse(strasse);
            ferienziel.setLand(land);
            DataHandler.updateFerienziel();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("wurde geupdated")
                .build();

    }


}
