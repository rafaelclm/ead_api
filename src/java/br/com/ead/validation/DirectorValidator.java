package br.com.ead.validation;

import br.com.ead.model.Director;
import org.json.JSONArray;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

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

        if (director.User == null) {
            messages.put("As informações do usuário são obrigatórias");
        }
        
        if (director.InstitutionId != null) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Institution");
            if (query.get(director.InstitutionId) == null) {
                messages.put("A instituição informada não foi encontrada");
            }
        } else {
            messages.put("O ID da instituição não foi informado");
        }

        if (messages.length() > 0) {
            throw new Exception(messages.toString(4));
        }
    }
}
