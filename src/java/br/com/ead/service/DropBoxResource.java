package br.com.ead.service;

import br.com.ead.model.DropBox;
import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxPath;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
@Path("dropbox")
public class DropBoxResource extends ParseResource {

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    /**
     * Creates a new instance of StorageResource
     */
    public DropBoxResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/user/{userId}/file")
    @SuppressWarnings("null")
    public Response getFile(@PathParam("userId") String userId, @QueryParam("path") String path) {

        JSONObject error = new JSONObject();

        try {

            DbxClient dbxClient = new DbxClient(getRequestConfig(request), getToken(userId));
            dbxClient.getFile(path, null, response.getOutputStream());

            return Response.ok().build();

        } catch (Exception ex) {

            error.put("error", ex.getMessage());
            return Response.ok().entity(error.toString(4)).build();

        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/authorizeUrl")
    public Response getAuthorizeURL() {

        try {
            DbxWebAuthNoRedirect webAuth = getWebAuth();

            // Have the user sign in and authorize your app.
            String authorizeUrl = webAuth.start();

            JSONObject object = new JSONObject();
            object.put("authorizeURL", authorizeUrl);

            return Response.ok().entity(object.toString(4)).build();

        } catch (Exception ex) {

            JSONObject object = new JSONObject();
            object.put("error", ex.getMessage());
            return Response.ok().entity(gson.toJson(object)).build();
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{userId}")
    public Response getPath(@PathParam("userId") String userId, @QueryParam("path") String path) {

        JSONObject error = new JSONObject();

        try {
            if (path == null) {

                path = "/";

            } else {

                String pathError = DbxPath.findError(path);
                if (pathError != null) {
                    throw new Exception("O path não está formatado corretamente");
                }
            }

            String token = getToken(userId);
            
            if(token == null){
                throw new Exception("Token não encontrado");
            }
            
            DbxClient dbxClient = new DbxClient(getRequestConfig(request), token);

            DbxEntry.WithChildren listing;
            listing = dbxClient.getMetadataWithChildren(path);

            return Response.ok().entity(gson.toJson(listing)).build();

        } catch (Exception ex) {
            error.put("error", ex.getMessage());
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(ex.getMessage()).build();
        }

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/user")
    public Response linkUserInDropBox(String content) {

        try {

            JSONObject params = new JSONObject(gson.fromJson(content, Map.class));

            DbxWebAuthNoRedirect webAuth = getWebAuth();

            DbxAuthFinish authFinish = webAuth.finish(params.getString("code"));
            String accessToken = authFinish.accessToken;

            DropBox dropBox = new DropBox();
            dropBox.UserId = params.getString("userId");
            dropBox.AccessToken = accessToken;

            dropBox.saveInstance();

            return Response.status(Response.Status.CREATED).build();

        } catch (Exception ex) {
            return Response.ok().entity(ex.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{userId}/file")
    public Response postFile(@PathParam("userId") String userId, 
            @QueryParam("path") String path, InputStream inputStream) {

        JSONObject error = new JSONObject();

        try {

            DbxClient dbxClient = new DbxClient(getRequestConfig(request), getToken(userId));

            try {
                
                dbxClient.uploadFile(path,
                        DbxWriteMode.add(), request.getContentLength(), inputStream);
                
            } finally {
                inputStream.close();
            }

            return Response.ok().build();

        } catch (Exception ex) {
            error.put("error", ex.getMessage());
            return Response.ok().entity(error.toString(4)).build();
        }
    }

    private DbxWebAuthNoRedirect getWebAuth() {

        final String DROPBOX_APP_KEY = "8q6ap39ipefwg8m";
        final String DROPBOX_APP_SECRET = "y5qoyr762e37qch";

        DbxAppInfo appInfo = new DbxAppInfo(DROPBOX_APP_KEY, DROPBOX_APP_SECRET);
        DbxRequestConfig config = new DbxRequestConfig("OpenEdu/1.0",
                Locale.getDefault().toString());

        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
        return webAuth;
    }

    private DbxRequestConfig getRequestConfig(HttpServletRequest request) {
        return new DbxRequestConfig("OpenEdu/1.0", request.getLocale().toString());
    }

    private String getToken(String userId) throws ParseException, Exception {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("DropBox");

        query.whereEqualTo("UserId", userId);
        List<ParseObject> finded = query.find();
        ParseObject dropBox = (query.find() == null ? null : finded.get(0));

        if (dropBox == null) {
            throw new Exception("Usuário não foi encontrado");
        }

        return dropBox.getString("AccessToken");
    }

}
