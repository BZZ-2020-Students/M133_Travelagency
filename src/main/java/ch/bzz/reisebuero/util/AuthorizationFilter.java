package ch.bzz.reisebuero.util;

import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;


import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;


@Provider
public class AuthorizationFilter implements ContainerRequestFilter {
    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        Map<String, Cookie> cookies = requestContext.getCookies();

        Cookie cookie = null;

        for (Map.Entry<String, Cookie> entry : cookies.entrySet()) {
            if (entry.getKey().equals("logincookie")){
                cookie = entry.getValue();
            };
        }



        Method called = resourceInfo.getResourceMethod();



        if (called.isAnnotationPresent(DenyAll.class)) {
            requestContext.abortWith(Response.status(UNAUTHORIZED).build());
            return;
        }

        if (called.isAnnotationPresent(PermitAll.class)) {
            return;
        }

        if (cookie == null) {
            requestContext.abortWith(Response.status(UNAUTHORIZED).build());
            return;
        }

        String loggedIn = AES256.decrypt(cookie.getValue()).split(";")[1];

        if (called.isAnnotationPresent(RolesAllowed.class)) {
            String[] roles = called.getAnnotation(RolesAllowed.class).value();

            if (Arrays.stream(roles).noneMatch(r -> r.equals(loggedIn))) {
                requestContext.abortWith(Response.status(UNAUTHORIZED).build());
            }
            return;
        }



        requestContext.abortWith(Response.status(UNAUTHORIZED).entity("Requested resource doesn't have any access restrictions.").build());


    }

}