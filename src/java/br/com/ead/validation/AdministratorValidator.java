package br.com.ead.validation;

import br.com.ead.model.Administrator;
import org.json.JSONArray;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

/**
 *
 * @author Rafael
 */
public class AdministratorValidator {

    public AdministratorValidator() {
    }

    public static void validate(Administrator administrator) throws Exception {

        JSONArray messages = new JSONArray();

        if (administrator.InstitutionId != null) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Institution");
            if (query.get(administrator.InstitutionId) == null) {
                messages.put("A instituição informada não foi encontrada");
            }
        } else {
            messages.put("O ID da instituição não foi informado");
        }

        if (administrator.User != null) {
            if (administrator.User.getName() == null) {
                messages.put("O nome do administrador é obrigatório");
            }
        } else {
            messages.put("As informações do usuário são obrigatórias");
        }

        if (messages.length() > 0) {
            throw new Exception(messages.toString(4));
        }
    }
}
