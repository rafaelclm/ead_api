package br.com.ead.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.parse4j.Parse;

/**
 *
 * @author Rafael
 */
public class ParseResource {

    private static final String APP_ID = "8unEbVKBBSMBCRld8AS7a87PeYOVIxdzIuH1I2n3";
    private static final String APP_KEY = "Gl7Alk7kE9n6Q4OI2FBo5LWDQD6x28Iu5pPwDcd1";

    protected static final String PATTERN_DATE = "dd/MM/yyyy";
    protected String _corsHeaders;

    protected Gson gson;

    public ParseResource() {

        Parse.initialize(APP_ID, APP_KEY);
        gson = new GsonBuilder().setDateFormat(PATTERN_DATE).create();

    }

    protected Map<String, String> getParameters(HttpServletRequest request) throws UnsupportedEncodingException {

        Map<String, String> parameters = new HashMap<>();

        String queryString = request.getQueryString();
        if (queryString != null) {
            String decoded = URLDecoder.decode(queryString, "UTF-8");
            String[] pares = decoded.split("&");
            for (String pare : pares) {
                String[] nameAndValue = pare.split("=");
                if (nameAndValue.length == 2) {
                    parameters.put(nameAndValue[0], nameAndValue[1]);
                }
            }

        }

        return parameters;
    }

}
