package com.bishwa.twitter.automate.restful;

import com.bishwa.twitter.automate.core.IAutomate;
import com.bishwa.twitter.automate.core.handlers.AutomateHandler;
import com.bishwa.twitter.automate.core.handlers.LikeRequestHandler;
import com.bishwa.twitter.automate.core.handlers.LoginRequestHandler;
import com.bishwa.twitter.automate.core.handlers.LogoutRequestHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Author: Bishwa
 * Date: 01/03/2021
 * Time: 16:21
 */
@Path("/action")
public class AutomateRestfulService {
    private final IAutomate iAutomate = new AutomateHandler();

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login() {
        iAutomate.next(new LoginRequestHandler()).handleRequest();

        return Response.ok("Login successful").build();
    }

    @GET
    @Path("/like")
    @Produces(MediaType.APPLICATION_JSON)
    public Response like() {
        iAutomate.next(new LoginRequestHandler())
                .next(new LikeRequestHandler())
                .next(new LogoutRequestHandler())
                .handleRequest();

        return Response.ok("Like successful").build();
    }

    @GET
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout() {
        iAutomate.next(new LogoutRequestHandler()).handleRequest();

        return Response.ok("Logout successful").build();
    }

}
