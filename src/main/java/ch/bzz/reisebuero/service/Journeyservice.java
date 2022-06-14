package ch.bzz.reisebuero.service;

import ch.bzz.reisebuero.data.DataHandler;
import ch.bzz.reisebuero.model.Journey;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("journey")
public class Journeyservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listJourney() {
        List<Journey> journeyList = DataHandler.readallJourney();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(journeyList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(500)
                    .entity("Error could not serialize Journey")
                    .build();
        }
    }

    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readJourney(
            @PathParam("uuid") String journeyUUID
    ) {
        Journey journey = DataHandler.readJourneyByUUID(journeyUUID);
        if (journey == null) {
            return Response
                    .status(404)
                    .entity("journey was not found")
                    .build();
        }
        return Response
                .status(200)
                .entity(journey)
                .build();
    }

    @DELETE
    @Path("delete/{uuid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteJourney(
            @PathParam("uuid") String journeyUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteJourney(journeyUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("deleted")
                .build();
    }

    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertJourney(
            @FormParam("destinationUUID")
            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
                    String destinationUUID,

            @FormParam("date")
            @NotEmpty

            /**@Pattern(regexp = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
                    + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$")*/
                    String date,

            @FormParam("price")
            @NotNull
            @DecimalMax(value = "99999.95")
            @DecimalMin(value = "0.05")
                    Float price,

            @FormParam("numbPers")
            @NotNull
            @Min(value = 1)
            @Max(value = 9)
                    Integer numbPers,

            @FormParam("rating")
            @NotNull
            @Min(value = 1)
            @Max(value = 5)
                    //@Range(min=1,max=5)
                    Integer rating
    ) {
        Journey journey = new Journey();
        journey.setJourneyUUID(String.valueOf(UUID.randomUUID()));
        journey.setDestinationUUID(destinationUUID);
        journey.setDate(date);
        journey.setPrice(price);
        journey.setNumbPers(numbPers);
        journey.setRating(rating);
        DataHandler.insertJourney(journey);


        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("has been created")
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateReise(
            @FormParam("UUID")
            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
                    String journeyUUID,

            @FormParam("destinationUUID")
            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
                    String destinationUUID,

            @FormParam("date")
            @NotEmpty
            /**@Pattern(regexp = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
                    + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$")*/
                    String date,


            @FormParam("price")
            @NotNull
            @DecimalMax(value = "99999.95")
            @DecimalMin(value = "0.05")
                    Float price,

            @FormParam("numbPers")
            @NotNull
            @Min(value = 1)
            @Max(value = 9)
                    Integer numbPers,

            @FormParam("rating")
            @NotNull
            @Min(value = 1)
            @Max(value = 5)
                    //@Range(min=1,max=5)
                    Integer rating
    ) {
        int httpStatus = 200;
        Journey journey = DataHandler.readJourneyByUUID(journeyUUID);
        if (journey != null) {
            journey.setJourneyUUID(journeyUUID);
            journey.setDestinationUUID(destinationUUID);
            journey.setDate(date);
            journey.setPrice(price);
            journey.setNumbPers(numbPers);
            journey.setRating(rating);
            DataHandler.updateJourney();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("has been updated")
                .build();
    }
}
