
package br.com.ead.validation;

import br.com.ead.model.Content;
import org.json.JSONArray;

/**
 *
 * @author Rafael
 */
public class ContentValidator {
    
    public static void validate(Content content) throws Exception{
        
        JSONArray messages = new JSONArray();
        
        if(content.Title == null){
            messages.put("O título do conteúdo é obrigatório");
        }
        
        if(messages.length() > 0){
            throw new Exception(messages.toString(4));
        }
    }
}
