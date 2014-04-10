/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ead.validation;

import br.com.ead.model.Tutorial;
import org.json.JSONArray;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

/**
 *
 * @author Rafael
 */
public class TutorialValidator{
    
    public static void validate(Tutorial tutorial) throws Exception{
        
        JSONArray messages = new JSONArray();
               
        if(tutorial.Title == null){
            messages.put("O título do tutorial é obrigatório");
        }
        
        if(messages.length() > 0){
            throw new Exception(messages.toString(4));
        }
        
    }
    
}
