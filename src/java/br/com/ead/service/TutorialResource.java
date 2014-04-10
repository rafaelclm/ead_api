/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ead.service;

import br.com.ead.model.Tutorial;
import br.com.ead.validation.TutorialValidator;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
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
@Path("tutorial")
public class TutorialResource extends ParseResource{

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TutorialResource
     */
    public TutorialResource() {
    }

   @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{objectId}")
    public Response getTutorial(@PathParam("objectId") String objectId) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tutorial");

        try {
            ParseObject discipline = query.get(objectId);
            return Response.ok(gson.toJson(discipline)).build();
        } catch (ParseException pe) {
            return Response.status(Response.Status.NOT_FOUND).entity(pe.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postTutorial(String content) {

        try {

            Tutorial tutorial = gson.fromJson(content, Tutorial.class);
            TutorialValidator.validate(tutorial);
            tutorial.saveInstance();

            return Response.status(Response.Status.CREATED).entity(gson.toJson(tutorial)).build();

        } catch (Exception ex) {
            return Response.ok().entity(ex.getMessage()).build();
        }

    }
}
