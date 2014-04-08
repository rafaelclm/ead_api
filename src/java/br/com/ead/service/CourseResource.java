/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ead.service;

import br.com.ead.model.Course;
import br.com.ead.validation.CourseValidator;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
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
@Path("course")
public class CourseResource extends ParseResource {

    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{objectId}")
    public Response getCourse(@PathParam("objectId") String ObjectId) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Course");

        try {
            ParseObject course = query.get(ObjectId);
            return Response.ok(gson.toJson(course)).build();
        } catch (ParseException pe) {
            return Response.status(Response.Status.NOT_FOUND).entity(pe.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCourse(String content) {
        try {

            Course course = gson.fromJson(content, Course.class);
            CourseValidator.validate(course);
            course.saveInstance();

            return Response.status(Response.Status.CREATED).entity(gson.toJson(course)).build();

        } catch (Exception ex) {
            return Response.ok().entity(ex.getMessage()).build();
        }

    }

}
