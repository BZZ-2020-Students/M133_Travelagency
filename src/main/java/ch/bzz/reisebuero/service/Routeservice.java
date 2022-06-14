package ch.bzz.reisebuero.service;

import ch.bzz.reisebuero.data.DataHandler;
import ch.bzz.reisebuero.model.Route;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("route")
public class Routeservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listRoute() {
        List<Route> routeList = DataHandler.readallRoute();
        try {
            return Response
                    .status(200)
                    .entity(new ObjectMapper().writeValueAsString(routeList))
                    .build();
        } catch (JsonProcessingException e) {
            return Response
                    .status(500)
                    .entity("Error could not serialize route")
                    .build();
        }
    }

    @GET
    @Path("read/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readRoute(
            @PathParam("uuid") String routeUUID
    ) {
        Route route = DataHandler.readRoutebyUUID(routeUUID);
        if (route == null) {
            return Response
                    .status(404)
                    .entity("route not found")
                    .build();
        }
        return Response
                .status(200)
                .entity(route)
                .build();
    }

    @DELETE
    @Path("delete/{uuid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteRoute(
            @PathParam("uuid") String routeUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteRoute(routeUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("has been deleted")
                .build();
    }

    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertRoute(
            @FormParam("distance")
            @DecimalMax(value = "99999.95")
            @DecimalMin(value = "0.05")
                    //@Range(min=1,max=5)
                    Float distance,

            @FormParam("journeyUUID")
            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
                    String journeyUUID
    ) {

        int httpStatus = 200;

        Route route = new Route();
        route.setRouteUUID(String.valueOf(UUID.randomUUID()));
        route.setDistance(distance);
        route.setJourneyUUID(journeyUUID);
        DataHandler.insertRoute(route);


        return Response
                .status(httpStatus)
                .entity("has been created")
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateReise(

            @FormParam("routeUUID")
            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
                    String routeUUID,

            @FormParam("distance")
            @NotEmpty
            @Min(value = 1)
            @Max(value = 5)
                    //@Range(min=1,max=5)
                    Float distance,

            @FormParam("journeyUUID")
            @NotEmpty
            @Pattern(regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
                    String journeyUUID
    ) {
        int httpStatus = 200;
        Route route = DataHandler.readRoutebyUUID(routeUUID);
        if (route != null) {
            route.setRouteUUID(routeUUID);
            route.setDistance(distance);
            route.setJourneyUUID(journeyUUID);
            DataHandler.updateRoute();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("has been updated")
                .build();

    }
}
