/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ead.model;

import br.com.ead.impl.IParseObject;
import java.util.ArrayList;
import java.util.List;
import org.parse4j.ParseClassName;
import org.parse4j.ParseException;
import org.parse4j.ParseFile;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;
import org.parse4j.ParseUser;

/**
 *
 * @author Rafael
 */
@ParseClassName("Student")
public class Student extends ParseObject implements IParseObject {

    public String Graduation;
    public String AReg;
    public ParseUser User;
    public ParseFile Photo;
    public String InstitutionId;
    public List<String> MyClasses;

    public Student() {
        this.MyClasses = new ArrayList<>();
    }

    @Override
    public void saveInstance() throws Exception {
        super.save(this);
    }

    public void generateAReg() throws ParseException {
        ParseObject counters = ParseQuery.getQuery("Counters").find().get(0);
        counters.increment("AReg");
        counters.saveInBackground();
        this.AReg = String.format("RA%08d", counters.getInt("AReg"));
    }


}
