package br.com.ead.model;

import br.com.ead.impl.IParseObject;
import java.util.Date;
import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;
import org.parse4j.ParseUser;

/**
 *
 * @author Rafael
 */
@ParseClassName("Administrator")
public class Administrator extends ParseObject implements IParseObject {

    public String Name;
    public Date BirthDate;
    public String graduation;
    public ParseUser user;

    public Administrator() {
    }

    @Override
    public void saveInstance() throws Exception {
        super.save(this);
    }

}
