package com.bishwa.twitter.automate.restful;

import com.bishwa.twitter.automate.service.AutomateService;

import javax.inject.Inject;
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
    @Inject
    private AutomateService automateService;

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login() {
        automateService.login();
        return Response.ok("Login successful").build();
    }

    @GET
    @Path("/like")
    @Produces(MediaType.APPLICATION_JSON)
    public Response like() {
        automateService.like();
        return Response.ok("Like successful").build();
    }

    @GET
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout() {
        automateService.logout();
        return Response.ok("Logout successful").build();
    }

}
