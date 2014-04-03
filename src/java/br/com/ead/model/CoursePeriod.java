/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ead.model;

import java.util.Date;
import java.util.List;
import org.parse4j.ParseClassName;
import org.parse4j.ParseObject;

/**
 *
 * @author Rafael
 */
@ParseClassName("CoursePeriod")
public class CoursePeriod extends ParseObject{

    public CoursePeriod() {
    }    
    
    public Date getStartdate(){
        return super.getDate("startdate");
    }
    
    public void setStartdate(Date startdate){
        super.put("startdate", startdate);
    }
    
    public Date getEnddate(){
        return super.getDate("enddate");
    }
    
    public void setEnddate(Date enddate){
        super.put("enddate", enddate);
    }
    
    public List<Discipline> getDisciplines(){
        return super.getList("disciplines");
    }
    
    public void setDisciplines(List disciplines){
        super.put("displines", disciplines);
    }
}
