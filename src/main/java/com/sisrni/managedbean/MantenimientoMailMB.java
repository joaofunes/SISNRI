/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.Parametros;
import com.sisrni.service.ParametrosService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Joao
 */

@Named("mantenimientoMailMB")
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class MantenimientoMailMB implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Autowired
    @Qualifier(value = "parametrosService")
    private ParametrosService parametrosService;
    
    private List<Parametros> listParametros;
    private Parametros parametros;
    
    
     public void init() {
        try {
            inicializador();
        } catch (Exception e) {
        }
    }
     
      private void inicializador() {
          try {
              parametros = new Parametros();
              listParametros = new ArrayList<Parametros>();
              listParametros = parametrosService.findAll();
              
          } catch (Exception e) {
            String message = "Error en inicializar : " + e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            e.printStackTrace();
          }
    }

    public List<Parametros> getListParametros() {
        return listParametros;
    }

    public void setListParametros(List<Parametros> listParametros) {
        this.listParametros = listParametros;
    }

    public Parametros getParametros() {
        return parametros;
    }

    public void setParametros(Parametros parametros) {
        this.parametros = parametros;
    }

   
    
}
