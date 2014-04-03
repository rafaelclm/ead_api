/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ead.model;

import br.com.ead.impl.IParseObject;
import java.util.Date;
import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;
import org.parse4j.ParseUser;

/**
 *
 * @author Rafael
 */
@ParseClassName("Student")
public class Student extends ParseObject implements IParseObject {

    public String Name;
    public Date BirthDate;
    public String Graduation;
    public String ra;
    public ParseUser user;

    public Student() {
    }

    @Override
    public void saveInstance() throws Exception {
        super.save(this);
    }

}
