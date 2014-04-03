package br.com.ead.service;

import br.com.ead.model.Teacher;
import br.com.ead.model.Teacher;
import br.com.ead.validation.TeacherValidator;
import com.google.gson.JsonSyntaxException;
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
import org.json.JSONException;
import org.json.JSONObject;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

/**
 * REST Web Service
 *
 * @author Rafael
 */
@Path("teacher")
public class TeacherResource extends ParseResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TeacherResource
     */
    public TeacherResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{objectId}")
    public Response getTeacher(@PathParam("objectId") String objectId) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Teacher");

        try {
            ParseObject administrator = query.get(objectId);
            return Response.ok(gson.toJson(administrator)).build();
        } catch (ParseException pe) {
            return Response.status(Response.Status.NOT_FOUND).entity(pe.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postTeacher(String content) {
        
       try {

            Teacher teacher = gson.fromJson(content, Teacher.class);
            TeacherValidator.validate(teacher);
            teacher.user.signUp();
            teacher.saveInstance();

            return Response.status(Response.Status.CREATED).entity(gson.toJson(teacher)).build();

        } catch (Exception ex) {
            Logger.getLogger(TeacherResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.ok().entity(gson.toJson(ex.getMessage())).build();
        }

    }
}
