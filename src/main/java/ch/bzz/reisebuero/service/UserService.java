package ch.bzz.reisebuero.service;


import ch.bzz.reisebuero.data.DataHandler;
import ch.bzz.reisebuero.model.User;
import ch.bzz.reisebuero.util.AES256;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

/**
 * services for authentication and authorization of users
 */
@Path("user")
public class UserService {

    /**
     * login-service
     *
     * @param username the username
     * @param password the password
     * @return Response
     */
    @PermitAll
    @Path("login")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response loginUser(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        User loggedInUser = DataHandler.readUser(username, password);
        String userRole = loggedInUser.getRole();
        String token = username + ";" + userRole;

        NewCookie cookie = new NewCookie(
                "logincookie",
                AES256.encrypt(token),
                "/",
                "",
                "Auth-Token",
                600,
                false
        );

        return Response.ok().cookie(cookie)
                .entity(loggedInUser.getUsername() + " successfully logged in!").build();
    }

    @PermitAll
    @Path("logout")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response logoutUser() {

        NewCookie cookie = new NewCookie(
                "logincookie",
                "",
                "/",
                "",
                "Auth-Token",
                1,
                false
        );

        return Response.ok().cookie(cookie).build();

    }
}
