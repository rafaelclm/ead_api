package br.com.ead.validation;

import br.com.ead.model.Administrator;
import org.json.JSONArray;

/**
 *
 * @author Rafael
 */
public class AdministratorValidator {

    public AdministratorValidator() {
    }

    public static void validate(Administrator administrator) throws Exception {

        JSONArray messages = new JSONArray();

        if (administrator.Name == null) {
            messages.put("O nome do administrador é obrigatório");
        }

        if (administrator.user != null) {
            messages.put("As informações do usuário são obrigatórias");
        }

        if (messages.length() > 0) {
            throw new Exception(messages.toString(4));
        }
    }
}
