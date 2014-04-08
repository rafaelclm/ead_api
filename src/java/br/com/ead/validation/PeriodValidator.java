/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ead.validation;

import br.com.ead.model.Period;
import org.json.JSONArray;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

/**
 *
 * @author Rafael
 */
public class PeriodValidator {
    
    public static void validate(Period period) throws Exception{
        
        JSONArray messages = new JSONArray();
        
        if(period.PeriodNumber <= 0){
            messages.put("O número do período é obrigatório");
        }
        
        if (period.CourseId != null) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Course");
            if(query.get(period.CourseId) == null){
                messages.put("O curso informado não foi encontrado");
            }
        } else {
            messages.put("O ID do curso não foi informado");
        }

        
        if(messages.length() > 0){
            throw new Exception(messages.toString(4));
        }
    }
}
