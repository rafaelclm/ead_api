/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ead.service;

import br.com.ead.model.Content;
import br.com.ead.model.Discipline;
import br.com.ead.validation.ContentValidator;
import br.com.ead.validation.DisciplineValidator;
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
@Path("content")
public class ContentResource extends ParseResource{

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ContentResource
     */
    public ContentResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{objectId}")
    public Response getContent(@PathParam("objectId") String objectId) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Period");

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
    public Response postContent(String _content) {

        try {

            Content content = gson.fromJson(_content, Content.class);
            ContentValidator.validate(content);
            content.saveInstance();

            return Response.status(Response.Status.CREATED).entity(gson.toJson(content)).build();

        } catch (Exception ex) {
            return Response.ok().entity(ex.getMessage()).build();
        }

    }
}
