package ch.bzz.reisebuero.service;

import ch.bzz.reisebuero.data.DataHandler;
import ch.bzz.reisebuero.model.Strecke;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
@Path("strecke")
public class Streckeservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listStrecke(){
        List<Strecke> streckeList = DataHandler.getInstance().readallStrecke();
        return Response
                .status(200)
                .entity(streckeList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readReise(
            @QueryParam("uuid") String streckeUUID
    ){
        Strecke strecke = DataHandler.getInstance().readStreckebyUUID(streckeUUID);
        return Response
                .status(200)
                .entity(strecke)
                .build();
    }
}
