/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ead.validation;

import br.com.ead.model.Discipline;
import org.json.JSONArray;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

/**
 *
 * @author Rafael
 */
public class DisciplineValidator{
    
    public static void validate(Discipline discipline) throws Exception{
        
        JSONArray messages = new JSONArray();
        
        if(discipline.Abbr == null){
            messages.put("A SIGLA da disciplina é obrigatória");
        }
        
        if(discipline.Title == null){
            messages.put("O título da disciplina é obrigatório");
        }
        
        if(discipline.StudentClassId != null){
             ParseQuery<ParseObject> query = ParseQuery.getQuery("Institution");
            if (query.get(discipline.StudentClassId) == null) {
                messages.put("A turma informada não foi encontrada");
            }
        }
        else{
            messages.put("A turma para essa disciplina não foi informada");
        }
        
        if(messages.length() > 0){
            throw new Exception(messages.toString(4));
        }
        
    }
    
}
