
package br.com.ead.model;

import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;

/**
 *
 * @author Rafael
 */
@ParseClassName("Discipline")
public class Discipline extends ParseObject{

    public Discipline() {
        super(null);
    }
    
    public String getTitle(){
        return super.getString("title");
    }
    
    public void setTitle(String title){
        super.put("title", title);
    }
    
}
