/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ead.service;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

/**
 * REST Web Service
 *
 * @author Rafael
 */
@Path("connection")
public class ConnectionResource extends ParseResource{

    @Context
    private HttpServletRequest request;

    /**
     * Creates a new instance of ConnectionResource
     */
    public ConnectionResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response connect(@QueryParam("apiKey") String apiKey, @QueryParam("institutionId") String institutionId) {

        try {

            if (apiKey == null || institutionId == null) {
                throw new Exception("The apiKey or insitutionId not found");
            }

            ParseQuery<ParseObject> query = ParseQuery.getQuery("Institution");
            query
                    .whereEqualTo("objectId", institutionId)
                    .whereEqualTo("apiKey", apiKey);

            if (query.find() == null) {
                throw new Exception("The apiKey or insitutionId is invalid");
            }

            UUID token = UUID.randomUUID();
            request.setAttribute("token", token.toString());

            JSONObject ok = new JSONObject();
            ok.put("token", token);
            return Response.ok(ok.toString(4)).build();

        } catch (Exception ex) {
            JSONObject error = new JSONObject();
            error.put("error", ex.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(error.toString(4)).build();
        }

    }
}
