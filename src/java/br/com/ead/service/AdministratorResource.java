/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ead.service;

import br.com.ead.model.Administrator;
import br.com.ead.validation.AdministratorValidator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
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
@Path("administrator")
public class AdministratorResource extends ParseResource {

    @Context
    private HttpServletRequest request;

    /**
     * Creates a new instance of AdministratorResource
     */
    public AdministratorResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{objectId}")
    public Response getAdministrator(@PathParam("objectId") String objectId) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Administrator");

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
    public Response postAdministrator(String content) {
        try {

            Administrator administrator = gson.fromJson(content, Administrator.class);
            administrator.InstitutionId = request.getHeader("institutionId");
            AdministratorValidator.validate(administrator);
            administrator.User.signUp();
            administrator.saveInstance();

            return Response.status(Response.Status.CREATED).entity(gson.toJson(administrator)).build();

        } catch (Exception ex) {
            Logger.getLogger(AdministratorResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.ok().entity(gson.toJson(ex.getMessage())).build();
        }

    }

}
