
package br.com.ead.model;

import br.com.ead.impl.IParseObject;
import org.parse4j.ParseClassName;
import org.parse4j.ParseFile;
import org.parse4j.ParseObject;
import org.parse4j.ParseUser;

/**
 *
 * @author Rafael
 */
@ParseClassName("Collaborator")
public class Collaborator extends ParseObject implements IParseObject{

    public String Graduation;
    public ParseUser User;
    public ParseFile Photo;
    public String InstitutionId;
    
    public Collaborator() {
    }

    @Override
    public void saveInstance() throws Exception {
        super.save(this);
    }
    
    
}
