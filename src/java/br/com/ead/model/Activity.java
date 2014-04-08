package br.com.ead.model;

import br.com.ead.impl.IParseObject;
import org.parse4j.ParseObject;

/**
 *
 * @author Rafael
 */
public class Activity extends ParseObject implements IParseObject {

    public String Title;
    public int Duration;
    public String Description;
    public String Body;

    public Activity() {
    }

    @Override
    public void saveInstance() throws Exception {
        super.save(this);
    }

}
