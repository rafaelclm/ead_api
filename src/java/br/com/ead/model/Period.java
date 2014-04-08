/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ead.model;

import br.com.ead.impl.IParseObject;
import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;

/**
 *
 * @author Rafael
 */
@ParseClassName("Period")
public class Period extends ParseObject implements IParseObject {
    
    public int PeriodNumber;
    public String CourseId;

    public Period() {
    }

    @Override
    public void saveInstance() throws Exception {
        super.save(this);
    }

}
