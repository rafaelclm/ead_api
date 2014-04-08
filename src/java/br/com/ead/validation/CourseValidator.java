package br.com.ead.validation;

import br.com.ead.model.Course;
import org.json.JSONArray;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

/**
 *
 * @author Rafael
 */
public class CourseValidator {

    public CourseValidator() {
    }

    public static void validate(Course course) throws Exception {

        JSONArray messages = new JSONArray();

        if (course.Name == null) {
            messages.put("O nome do curso é obrigatório");
        }

        if (course.InstitutionId != null) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Institution");
            if(query.get(course.InstitutionId) == null){
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
