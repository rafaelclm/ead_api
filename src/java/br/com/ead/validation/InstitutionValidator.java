package br.com.ead.validation;

import br.com.ead.model.Institution;
import org.json.JSONArray;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

/**
 *
 * @author Rafael
 */
public class InstitutionValidator {

    public static void validate(Institution institution) throws Exception {

        JSONArray messages = new JSONArray();

        if (institution.FantasyName == null) {
            messages.put("O nome fantásia da instituição é obrigatório");
        }

        if (institution.InstitutionName == null) {
            messages.put("A razão social da instituição é obrigatório");
        }

        if (institution.LegalPersonID != null) {

            ParseQuery<ParseObject> query = ParseQuery.getQuery("Institution");
            query.whereEqualTo("LegalPersonID", institution.LegalPersonID);
            if (query.find() != null) {
                messages.put("Já existe uma instituição registrada com o LegalPersonID informado - " + institution.LegalPersonID);
            }
            
            if (institution.LegalPersonID.length() > 14) {
                messages.put("O tamanho máximo do campo LegalPersonID é 14");
            }
            
        } else {
            messages.put("O CNPJ da instituição é obrigatório");
        }

        if (institution.PostCode == null) {
            messages.put("O CEP da instituição é obrigatório");
        }

        if (institution.PhoneNumber == null) {
            messages.put("O telefone da instituição é obrigatório");
        }

        if (messages.length() > 0) {
            throw new Exception(messages.toString(4));
        }

    }
}
