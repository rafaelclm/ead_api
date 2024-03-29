package br.com.ead.validation;

import br.com.ead.model.Teacher;
import org.json.JSONArray;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

/**
 *
 * @author Rafael
 */
public class TeacherValidator {

    public TeacherValidator() {
    }

    public static void validate(Teacher teacher) throws Exception {

        JSONArray messages = new JSONArray();

        if (teacher.User != null) {
            if (teacher.User.getName() == null) {
                messages.put("O nome do professor é obrigatório");
            }
        } else {
            messages.put("As informações do usuário são obrigatórias");
        }
        
        if (teacher.InstitutionId != null) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Institution");
            if (query.get(teacher.InstitutionId) == null) {
                messages.put("A instituição informada não foi encontrada");
            }
        } else {
            teacher.InstitutionId = "openTeacher";
        }

        if (messages.length() > 0) {
            throw new Exception(messages.toString(4));
        }
    }
}
