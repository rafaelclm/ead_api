
package br.com.ead.model;

import br.com.ead.impl.IParseObject;
import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;

/**
 *
 * @author Rafael
 */
@ParseClassName("Course")
public class Course extends ParseObject implements IParseObject{

    public String Name;
    public String Description;
    public String Interval;
    public String InstitutionId;
    public Teacher CourseCoordinator;
    
    public Course() {
    }

    @Override
    public void saveInstance() throws Exception {
        super.save(this);
    }
    
}
