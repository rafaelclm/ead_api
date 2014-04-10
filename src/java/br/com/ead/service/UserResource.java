/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ead.service;

import com.google.gson.JsonSyntaxException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.parse4j.ParseException;
import org.parse4j.ParseUser;

/**
 * REST Web Service
 *
 * @author Rafael
 */
@Path("user")
public class UserResource extends ParseResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {
        try {
            ParseUser user = ParseUser.login(username, password);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(user)).build();
        } catch (Exception ex) {
            return Response.ok().entity(ex.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(String content) {
        try {
            ParseUser user = gson.fromJson(content, ParseUser.class);
            user.signUp();
            return Response.status(Response.Status.CREATED).entity(gson.toJson(user)).build();
        } catch (JsonSyntaxException | ParseException ex) {
            return Response.ok().entity(ex.getMessage()).build();
        }
    }
}
