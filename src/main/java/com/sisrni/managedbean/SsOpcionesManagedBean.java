/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.managedbean.generic.GenericManagedBean;
import com.sisrni.managedbean.lazymodel.SsOpcionesLazyModel;
import com.sisrni.model.SsOpciones;
import com.sisrni.service.SsOpcionesService;
import com.sisrni.service.generic.GenericService;
import java.util.Date;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author JosueRogelio
 */
@Named("ssOpcionesManagedBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SsOpcionesManagedBean extends GenericManagedBean<SsOpciones, Integer> {

    @Autowired
    @Qualifier(value = "ssOpcionesService")
    private SsOpcionesService ssOpcionesService;

    @Override
    public GenericService<SsOpciones, Integer> getService() {
      return ssOpcionesService;
    }

    @PostConstruct
    public void init() {
        loadLazyModels();
    }
    
    @Override
    public LazyDataModel<SsOpciones> getNewLazyModel() {
        return new SsOpcionesLazyModel(ssOpcionesService);
    }

     @Override
    public void saveNew(ActionEvent event) {
       if(getUsuario()!=null){
        String msg = ResourceBundle.getBundle("/crudbundle").getString(SsOpciones.class.getSimpleName() + "Created");
        getSelected().setUsuarioRegistro(getUsuario());
        getSelected().setFechaRegistro(new Date());
        persist(PersistAction.CREATE, msg);
       }
    }

    @Override
    public void save(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/crudbundle").getString(SsOpciones.class.getSimpleName() + "Updated");
        getSelected().setUsuarioUltimamodificacion(getUsuario());
        getSelected().setFechaUltimamodificacion(new Date());
        persist(PersistAction.UPDATE, msg);
        if (!isValidationFailed()) {
           items = null; // Invalidate list of items to trigger re-query.
        }
    }
    
    
    
}
