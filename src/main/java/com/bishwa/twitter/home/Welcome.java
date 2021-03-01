package com.bishwa.twitter.home;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Author: Bishwa
 * Date: 28/02/2021
 * Time: 19:59
 */
@Path("/")
public class Welcome {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response home() {
        return Response.ok("Welcome to Twitter Automation v1.0").build();
    }
}
