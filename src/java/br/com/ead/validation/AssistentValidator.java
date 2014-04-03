
package br.com.ead.validation;

import br.com.ead.model.Assistent;
import org.json.JSONArray;

/**
 *
 * @author Rafael
 */
public class AssistentValidator {

    public AssistentValidator() {
    }

    public static void validate(Assistent assistent) throws Exception {

        JSONArray messages = new JSONArray();

        if (assistent.Name == null) {
            messages.put("O nome do assistente é obrigatório");
        }

        if (assistent.User == null) {
            messages.put("As informações do usuário são obrigatórias");
        }

        if (messages.length() > 0) {
            throw new Exception(messages.toString(4));
        }
    }
}
