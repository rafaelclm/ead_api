/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ead.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Rafael
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.com.ead.service.AdministratorResource.class);
        resources.add(br.com.ead.service.AssistentResource.class);
        resources.add(br.com.ead.service.DirectorResource.class);
        resources.add(br.com.ead.service.InstitutionResource.class);
        resources.add(br.com.ead.service.StudentResource.class);
        resources.add(br.com.ead.service.TeacherResource.class);
    }
    
}
