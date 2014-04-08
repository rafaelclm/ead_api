package br.com.ead.validation;

import br.com.ead.model.Collaborator;
import org.json.JSONArray;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

/**
 *
 * @author Rafael
 */
public class CollaboratorValidator {

    public CollaboratorValidator() {
    }

    public static void validate(Collaborator collaborator) throws Exception {

        JSONArray messages = new JSONArray();

        if (collaborator.User != null) {
            if (collaborator.User.getName() == null) {
                messages.put("O nome do colaborador é obrigatório");
            }
        } else {
            messages.put("As informações do usuário são obrigatórias");
        }

        if (collaborator.InstitutionId != null) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Institution");
            if (query.get(collaborator.InstitutionId) == null) {
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
