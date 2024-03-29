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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;
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
    public Response login(String content) {
        JSONObject credentials = gson.fromJson(content, JSONObject.class);
        try {
            ParseUser user = ParseUser.login(credentials.getString("username"), credentials.getString("password"));
            return Response.status(Response.Status.CREATED).entity(gson.toJson(user)).build();
        } catch (JSONException | ParseException ex) {
            return Response.ok().entity(ex.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("me/{sessionToken}")
    public Response me(@PathParam("sessionToken") String SessionToken) {
        try {

            Client client = ClientBuilder.newClient();

            String output = client
                                .target("https://api.parse.com/")
                                .path("1/users/me")
                                .request().header("X-Parse-Application-Id", APP_ID)
                                .header("X-Parse-REST-API-Key", APP_KEY)
                                .header("X-Parse-Session-Token", SessionToken)
                                .accept(MediaType.APPLICATION_JSON)
                                .get(String.class);

            return Response.status(Response.Status.CREATED).entity(output).build();
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
