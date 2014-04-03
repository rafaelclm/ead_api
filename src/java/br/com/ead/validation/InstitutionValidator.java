package br.com.ead.validation;

import br.com.ead.model.Institution;
import org.json.JSONArray;

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

        if (institution.LegalPersonID == null) {
            messages.put("O CNPJ da instituição é obrigatório");
        } else if (institution.LegalPersonID.length() > 14) {
            messages.put("O tamanho máximo do campo CNPJ é 14");
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
