
package br.com.ead.model;

import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;

/**
 *
 * @author Rafael
 */
@ParseClassName("Content")
public class Content extends ParseObject{

    public Content() {
    }
    
    public String getTitle(){
        return super.getString("title");
    }
    
    public void setTitle(String title){
        super.put("title", title);
    }
}
