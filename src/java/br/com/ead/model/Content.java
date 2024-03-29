
package br.com.ead.model;

import br.com.ead.impl.IParseObject;
import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;

/**
 *
 * @author Rafael
 */
@ParseClassName("Content")
public class Content extends ParseObject implements IParseObject{

    public String Title;
    public String Body;
    
    public Content() {
    }

    @Override
    public void saveInstance() throws Exception {
        super.save(this);
    }
    
}
