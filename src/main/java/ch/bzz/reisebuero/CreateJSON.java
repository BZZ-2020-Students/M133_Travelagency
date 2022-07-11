package ch.bzz.reisebuero;

import ch.bzz.reisebuero.model.Destination;
import ch.bzz.reisebuero.model.Journey;
import ch.bzz.reisebuero.model.Route;
import ch.bzz.reisebuero.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CreateJSON {


    public static void main(String[] args) {

        Destination[] destination = {
                new Destination(UUID.randomUUID().toString(), "meier", "bachweg","phillip"),
                new Destination(UUID.randomUUID().toString(), "mueller", "strasse","marc")
        };
        Journey[] journey = {
                new Journey(destination[0],UUID.randomUUID().toString(), "11-12-2022", 0.5f, 6, 5),
                new Journey(destination[1], UUID.randomUUID().toString(), "01-12-2123", 4.5f, 21, 3),
        };


        Route[] route = {
                new Route(UUID.randomUUID().toString(),34f, journey[1]),
                new Route(UUID.randomUUID().toString(),65f, journey[0])
        };

        List<String> list = Arrays.asList("test", "testteeen");
        User[] users = {
                new User("7c7ea8ed-2f4a-4943-9a76-bd19bdf984cf","admin","halllo","admin",list),
                new User("7c7ea8ed-2f4a-4943-9a76-bd19bdf984cf", "user", "hallo", "user",list)

        };


        ObjectMapper om = new ObjectMapper();
        try {
            om.writeValue(Paths.get("C:/Github/M133_Reisebuero/testing/journey.json").toFile(), journey);
            om.writeValue(Paths.get("C:/Github/M133_Reisebuero/testing/destination.json").toFile(), destination);
            om.writeValue(Paths.get("C:/Github/M133_Reisebuero/testing/route.json").toFile(), route);
            om.writeValue(Paths.get("C:/Github/M133_Reisebuero/testing/user.json").toFile(), users);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}