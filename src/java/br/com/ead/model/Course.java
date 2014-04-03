
package br.com.ead.model;

import java.util.List;
import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;

/**
 *
 * @author Rafael
 */
@ParseClassName("Course")
public class Course extends ParseObject{

    public Course() {
    }
    
    public String getName(){
        return super.getString("name");
    }
    
    public void setName(String name){
        super.put("name", name);
    }
    
    public List<StudentClass> getStudentClasses(){
        return super.getList("studentClasses");
    }
    
    public void setStudentClasses(List studentClasses){
        super.put("studentClasses", studentClasses);
    }
    
}
