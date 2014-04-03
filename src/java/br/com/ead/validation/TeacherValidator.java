
package br.com.ead.validation;

import br.com.ead.model.Teacher;
import org.json.JSONArray;

/**
 *
 * @author Rafael
 */
public class TeacherValidator {

    public TeacherValidator() {
    }

    public static void validate(Teacher teacher) throws Exception {

        JSONArray messages = new JSONArray();

        if (teacher.Name == null) {
            messages.put("O nome do professor é obrigatório");
        }

        if (teacher.user != null) {
            messages.put("As informações do usuário são obrigatórias");
        }

        if (messages.length() > 0) {
            throw new Exception(messages.toString(4));
        }
    }
}
