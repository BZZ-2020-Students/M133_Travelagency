package ch.bzz.reisebuero.service;

import ch.bzz.reisebuero.data.DataHandler;
import ch.bzz.reisebuero.model.Reise;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
@Path("reise")
public class Reiseservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listReise(){
        List<Reise> reiseList = DataHandler.getInstance().readallReise();
        return Response
                .status(200)
                .entity(reiseList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readReise(
            @QueryParam("uuid") String reiseUUID
    ){
        Reise reise = DataHandler.getInstance().readReisebyUUID(reiseUUID);
        return Response
                .status(200)
                .entity(reise)
                .build();
    }
}
