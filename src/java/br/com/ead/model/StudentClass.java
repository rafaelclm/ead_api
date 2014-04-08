package br.com.ead.model;

import br.com.ead.impl.IParseObject;
import java.util.Date;
import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;

/**
 *
 * @author Rafael
 */
@ParseClassName("StudentClass")
public class StudentClass extends ParseObject implements IParseObject{

    public Date StartDate;
    public Date EndDate;
    public String PeriodId;

    public StudentClass() {
    }

    @Override
    public void saveInstance() throws Exception {
        super.save(this);
    }
      

}
