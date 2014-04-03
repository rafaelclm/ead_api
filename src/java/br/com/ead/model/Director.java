
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
@ParseClassName("Director")
public class Director extends ParseObject implements IParseObject{

    public String Name;
    public Date BirthDate;
    public String Graduation;
    public ParseUser User;
    
    public Director() {
    }

    @Override
    public void saveInstance() throws Exception {
        super.save(this);
    }
   
}
