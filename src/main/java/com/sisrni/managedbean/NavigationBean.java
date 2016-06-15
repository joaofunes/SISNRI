/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import java.io.Serializable;
 
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
 
/**
 * navigation bean
 * @author ALVARADO
 *
 */
@Named("navigationBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class NavigationBean implements Serializable {
 
    private static final long serialVersionUID = 1520318172495977648L;
 
    /**
     * Redirect  a login page.
     * @return nombre pag Login.
     */
    public String redirectToLogin() {
        return "/auth/login.xhtml?faces-redirect=true";
    }
     
    /**
     * ir a login page.
     * @return nombre pag Login.
     */
    public String toLogin() {
        return "/auth/login.xhtml?faces-redirect=true";
    }
         
   
    /**
     * Redirect a pagina de inicio
     * @return  nombre pag inicio.
     */
    public String redirectToIndex() {
        return "/views/index.xhtml?faces-redirect=true";
    }
     
    /**
     * Ir a pagina de inicio
     * @return nombre pag inicio.
     */
    public String toIndex() {
        return "/views/index.xhtml";
    }
     
}
