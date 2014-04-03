
package br.com.ead.model;

import java.util.List;
import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;

/**
 *
 * @author Rafael
 */
@ParseClassName("StudentClass")
public class StudentClass extends ParseObject{

    public StudentClass() {
    }
    
     public List<Student> getStudents(){
        return super.getList("students");
    }
    
    public void setStudents(List students){
        super.put("students", students);
    }
    
}
