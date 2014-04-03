/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ead.service;

import br.com.ead.model.Director;
import br.com.ead.model.Director;
import br.com.ead.validation.DirectorValidator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

/**
 * REST Web Service
 *
 * @author Rafael
 */
@Path("director")
public class DirectorResource extends ParseResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DirectorResource
     */
    public DirectorResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{objectId}")
    public Response getDirector(@PathParam("objectId") String objectId) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Director");

        try {
            ParseObject director = query.get(objectId);
            return Response.ok(gson.toJson(director)).build();
        } catch (ParseException pe) {
            return Response.status(Response.Status.NOT_FOUND).entity(pe.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postDirector(String content) {
        
        try {

            Director director = gson.fromJson(content, Director.class);
            DirectorValidator.validate(director);
            director.user.signUp();
            director.saveInstance();

            return Response.status(Response.Status.CREATED).entity(gson.toJson(director)).build();

        } catch (Exception ex) {
            Logger.getLogger(DirectorResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.ok().entity(gson.toJson(ex.getMessage())).build();
        }

    }
}
