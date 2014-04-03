package br.com.ead.validation;

import br.com.ead.model.Director;
import org.json.JSONArray;

/**
 *
 * @author Rafael
 */
public class DirectorValidator {

    public DirectorValidator() {
    }

    public static void validate(Director director) throws Exception {

        JSONArray messages = new JSONArray();

        if (director.Name == null) {
            messages.put("O nome do diretor é obrigatório");
        }

        if (director.user != null) {
            messages.put("As informações do usuário são obrigatórias");
        }

        if (messages.length() > 0) {
            throw new Exception(messages.toString(4));
        }
    }
}
