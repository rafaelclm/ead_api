package br.com.ead.model;

import br.com.ead.impl.IParseObject;
import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;

/**
 *
 * @author Rafael
 */
@ParseClassName("Institution")
public class Institution extends ParseObject implements IParseObject {

    public String FantasyName;
    public String InstitutionName;
    public String LegalPersonID;
    public String Address;
    public String District;
    public String City;
    public String State;
    public String Country;
    public String PostCode;
    public String PhoneNumber;
    public String Domain;

    public Institution() {
    }

    @Override
    public void saveInstance() throws Exception {
        super.save(this);
    }

}
