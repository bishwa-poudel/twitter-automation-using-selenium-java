package com.bishwa.twitter.automate.restful;

import com.bishwa.twitter.automate.core.LikeAction;
import com.bishwa.twitter.automate.core.LoginAction;

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
    private LoginAction loginAction;

    @Inject
    private LikeAction likeAction;

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login() {
        loginAction.action();
        return Response.ok("Login successful").build();
    }

    @GET
    @Path("/like")
    @Produces(MediaType.APPLICATION_JSON)
    public Response like() {
        likeAction.action();
        return Response.ok("Like successful").build();
    }

}
