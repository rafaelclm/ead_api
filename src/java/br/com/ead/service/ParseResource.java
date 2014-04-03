package br.com.ead.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.SimpleDateFormat;
import org.parse4j.Parse;

/**
 *
 * @author Rafael
 */
public class ParseResource {

    private static final String APP_ID = "8unEbVKBBSMBCRld8AS7a87PeYOVIxdzIuH1I2n3";
    private static final String APP_KEY = "Gl7Alk7kE9n6Q4OI2FBo5LWDQD6x28Iu5pPwDcd1";

    protected static final String PATTERN_DATE = "dd/MM/yyyy HH:mm:ss";
    protected SimpleDateFormat formatter;

    protected Gson gson;

    public ParseResource() {

        Parse.initialize(APP_ID, APP_KEY);
        gson = new GsonBuilder()
                .setDateFormat(PATTERN_DATE).create();
        formatter = new SimpleDateFormat(PATTERN_DATE);

    }

}
