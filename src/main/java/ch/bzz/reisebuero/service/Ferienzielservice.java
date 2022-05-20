package ch.bzz.reisebuero.service;

import ch.bzz.reisebuero.data.DataHandler;
import ch.bzz.reisebuero.model.Ferienziel;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class Ferienzielservice {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFerienziel(){
        List<Ferienziel> ferienzielList = DataHandler.getInstance().readAllFerienziel();
        return Response
                .status(200)
                .entity(ferienzielList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readFerienziel(
            @QueryParam("uuid") String ferienzielUUID
    ){
        int httpStatus = 200;
        Ferienziel ferienziel = DataHandler.getInstance().readferienzielbyUUID(ferienzielUUID);
        if(ferienziel== null){
            httpStatus=410;
        }
        return Response
                .status(200)
                .entity(ferienziel)
                .build();
    }
}
