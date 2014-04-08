
package br.com.ead.model;

import br.com.ead.impl.IParseObject;
import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;

/**
 *
 * @author Rafael
 */
@ParseClassName("Discipline")
public class Discipline extends ParseObject implements IParseObject {
    
    public String Title;
    public int WeeklyWorkLoad;
    public int SemesterWorkLoad;
    public String Abbr;
    public String StudentClassId;
    
    public Discipline() {
    }
    
    @Override
    public void saveInstance() throws Exception {
        super.save(this);
    }
    
}
