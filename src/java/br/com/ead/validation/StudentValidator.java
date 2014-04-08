package br.com.ead.validation;

import br.com.ead.model.Student;
import org.json.JSONArray;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

/**
 *
 * @author Rafael
 */
public class StudentValidator {

    public StudentValidator() {
    }

    public static void validate(Student student) throws Exception {

        JSONArray messages = new JSONArray();

        if (student.User != null) {
            if (student.User.getName() == null) {
                messages.put("O nome do aluno é obrigatório");
            }
        } else {
            messages.put("As informações do usuário são obrigatórias");
        }

        if (student.InstitutionId != null) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Institution");
            if (query.get(student.InstitutionId) == null) {
                messages.put("A instituição informada não foi encontrada");
            }
        } else {
            student.InstitutionId = "anonymous";
        }

        if (messages.length() > 0) {
            throw new Exception(messages.toString(4));
        }
    }
}
