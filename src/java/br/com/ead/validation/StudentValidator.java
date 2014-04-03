
package br.com.ead.validation;

import br.com.ead.model.Student;
import org.json.JSONArray;

/**
 *
 * @author Rafael
 */
public class StudentValidator {

    public StudentValidator() {
    }

    public static void validate(Student student) throws Exception {

        JSONArray messages = new JSONArray();

        if (student.Name == null) {
            messages.put("O nome do aluno é obrigatório");
        }

        if (student.user != null) {
            messages.put("As informações do usuário são obrigatórias");
        }

        if (messages.length() > 0) {
            throw new Exception(messages.toString(4));
        }
    }
}
