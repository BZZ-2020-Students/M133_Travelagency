package ch.bzz.reisebuero.service;

import ch.bzz.reisebuero.data.DataHandler;
import ch.bzz.reisebuero.model.Destination;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("destination")
public class Destinationservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listDestination() {
        List<Destination> destinationList = DataHandler.readAllDestination();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(destinationList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(500)
                    .entity("Error could not serialize Destination")
                    .build();
        }
    }

    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readDestination(
            @PathParam("uuid") String destinationUUID
    ) {
        Destination destination = DataHandler.readDestinationbyUUID(destinationUUID);
        if (destination == null) {
            return Response
                    .status(404)
                    .entity("destination not found")
                    .build();
        }
        return Response
                .status(200)
                .entity(destination)
                .build();
    }

    @DELETE
    @Path("delete/{uuid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteDestination(
            @PathParam("uuid") String destinationUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteDestination(destinationUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("Got deleted")
                .build();
    }

    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertReise(
            @FormParam("location")
            @NotEmpty
            @Size(min = 2, max = 40)
                    String location,

            @FormParam("street")
            @NotEmpty
            @Size(min = 2, max = 40)
                    String street,

            @FormParam("country")
            @NotEmpty
            @Size(min = 2, max = 40)
                    String country
    ) {
        Destination destination = new Destination();
        destination.setDestinationUUID(String.valueOf(UUID.randomUUID()));
        destination.setLocation(location);
        destination.setStreet(street);
        destination.setCountry(country);
        DataHandler.insertDestination(destination);


        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("created")
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateReise(
            @FormParam("destinationUUID")
            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
                    String destinationUUID,

            @FormParam("location")
            @NotEmpty
            @Size(min = 2, max = 40)
                    String location,

            @FormParam("street")
            @NotEmpty
            @Size(min = 2, max = 40)
                    String street,

            @FormParam("country")
            @NotEmpty
            @Size(min = 2, max = 40)
                    String country


    ) {
        int httpStatus = 200;
        Destination destination = DataHandler.readDestinationbyUUID(destinationUUID);
        if (destination != null) {
            destination.setDestinationUUID(destinationUUID);
            destination.setLocation(location);
            destination.setStreet(street);
            destination.setCountry(country);
            DataHandler.updateDestination();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("has been updated")
                .build();

    }


}
