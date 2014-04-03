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
@ParseClassName("Teacher")
public class Teacher extends ParseObject implements IParseObject {

    public String Name;
    public Date BirthDate;
    public String Graduation;
    public ParseUser User;

    public Teacher() {
    }

    @Override
    public void saveInstance() throws Exception {
        super.save(this);
    }

}
