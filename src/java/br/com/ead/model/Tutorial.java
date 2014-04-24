package br.com.ead.model;

import br.com.ead.impl.IParseObject;
import org.parse4j.ParseObject;

/**
 *
 * @author Rafael
 */
public class Tutorial extends ParseObject implements IParseObject {

    public String Title;
    public String teacherAuthor;
    public String Category;
    
    public Tutorial() {
        
    }

    @Override
    public void saveInstance() throws Exception {
        super.save(this);
    }

}
