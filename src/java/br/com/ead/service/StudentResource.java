/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ead.service;

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
import br.com.ead.model.Student;
import br.com.ead.validation.StudentValidator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * REST Web Service
 *
 * @author Rafael
 */
@Path("student")
public class StudentResource extends ParseResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of StudentResource
     */
    public StudentResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{ra}")
    public Response getStudent(@PathParam("ra") String ra) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student");

        try {
            ParseObject student = query.get(ra);
            return Response.ok(gson.toJson(student)).build();
        } catch (ParseException pe) {
            return Response.status(Response.Status.NOT_FOUND).entity(pe.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postStudent(String content) {

        try {

            Student student = gson.fromJson(content, Student.class);
            StudentValidator.validate(student);

            ParseObject counters = ParseQuery.getQuery("Counters").get("mrB3siS8Je");
            counters.increment("ra");
            counters.saveInBackground();
            student.ra = String.format("RA%08d", counters.getInt("ra"));

            student.user.signUp();
            student.saveInstance();

            return Response.status(Response.Status.CREATED).entity(gson.toJson(student)).build();

        } catch (Exception ex) {
            Logger.getLogger(StudentResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.ok().entity(gson.toJson(ex.getMessage())).build();
        }
    }
}
