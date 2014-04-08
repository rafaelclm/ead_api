package br.com.ead.validation;

import br.com.ead.model.StudentClass;
import org.json.JSONArray;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

/**
 *
 * @author Rafael
 */
public class StudentClassValidator {

    public StudentClassValidator() {
    }

    public static void validate(StudentClass studentClass) throws Exception {

        JSONArray messages = new JSONArray();

        if (studentClass.StartDate == null) {
            messages.put("A data inicial da turma é obrigatória");
        }
        
        if (studentClass.EndDate == null) {
            messages.put("A data de finalização da turma é obrigatória");
        }

        if (studentClass.PeriodId != null) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Period");
            if(query.get(studentClass.PeriodId) == null){
                messages.put("O período informado não existe");
            }
        } else {
            messages.put("O ID do período não foi informado");
        }

        if (messages.length() > 0) {
            throw new Exception(messages.toString(4));
        }
    }
}
