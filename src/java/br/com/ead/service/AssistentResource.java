/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ead.service;

import br.com.ead.model.Assistent;
import br.com.ead.validation.AssistentValidator;
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
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

/**
 * REST Web Service
 *
 * @author Rafael
 */
@Path("assistent")
public class AssistentResource extends ParseResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AssistentResource
     */
    public AssistentResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{objectId}")
    public Response getAssistent(@PathParam("objectId") String objectId) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Assistent");

        try {
            ParseObject assistent = query.get(objectId);
            return Response.ok(gson.toJson(assistent)).build();
        } catch (ParseException pe) {
            return Response.status(Response.Status.NOT_FOUND).entity(pe.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postAssistent(String content) {
        
         try {

            Assistent assistent = gson.fromJson(content, Assistent.class);
            AssistentValidator.validate(assistent);
            assistent.user.signUp();
            assistent.saveInstance();

            return Response.status(Response.Status.CREATED).entity(gson.toJson(assistent)).build();

        } catch (Exception ex) {
            Logger.getLogger(AssistentResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.ok().entity(gson.toJson(ex.getMessage())).build();
        }

    }
    
}
