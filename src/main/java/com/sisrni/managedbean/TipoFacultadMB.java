/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.managedbean.generic.GenericManagedBean;
import com.sisrni.managedbean.lazymodel.TipoFacultadLazyModel;
import com.sisrni.model.TipoFacultad;
import com.sisrni.service.TipoFacultadService;
import com.sisrni.service.generic.GenericService;
import com.sisrni.utils.JsfUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Luis
 */
@Named("tipoFacultadMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class TipoFacultadMB  extends GenericManagedBean<TipoFacultad, Integer> {
    
    /**para errores*/
    
    private final static Log log = LogFactory.getLog(TipoFacultadMB.class);
    
     /*dependecias  tabla a la cual se le esta haciendo crud*/
    @Autowired
    @Qualifier(value = "tipoFacultadService")
    private TipoFacultadService tipoFacultadService;
    
    /**listas a utulizar*/
    
    private TipoFacultad tipoFacultad;
    private TipoFacultad delTipoFacultad;    
    private List<TipoFacultad> listadoTipoFacultad;
    private boolean actualizar;

   /**implementacion de GenericManagedBen
    * 
    */
    @Override
    public GenericService<TipoFacultad, Integer> getService() {
        return tipoFacultadService;
    }

    @Override
    public LazyDataModel<TipoFacultad> getNewLazyModel() {
        return new TipoFacultadLazyModel(tipoFacultadService);
    }

    @PostConstruct
    public void init() {
        //inicializacion de loadLazyModels
        loadLazyModels();
        cargarTipoFacultad();
    }
    
    
    
     private void cargarTipoFacultad() {
        try {
            actualizar=false;
            tipoFacultad = new TipoFacultad();
            listadoTipoFacultad = tipoFacultadService.getAllByIdDesc();
        } catch (Exception e) {
            log.debug("Error al tratar de cargar las solicitudes listar para realizar un analisis..." + e.getStackTrace());

        }
    }

     
     /**
      * Metodo para almacenar registro en Tipo Facultad
      * 
      */
     public void guardarTipoFacultad() {
        String msg = "Tipo de Facultad Almacenado Exitosamente!";       
        try {            
            tipoFacultadService.save(tipoFacultad);                              
            cargarTipoFacultad();
            RequestContext.getCurrentInstance().update(":formPrincipal");
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado", msg));
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al guardar tipo de facultad");
            e.printStackTrace();
        }
        cargarTipoFacultad();
    } 
     
     /**
      * Metodo para Actualizar registro en Tipo Facultad
      * 
      */
     public void updateTipoFacultad() {
        String msg = "Tipo de Facultad Actualizado Exitosamente!";       
        try {            
            tipoFacultadService.merge(tipoFacultad);
            if (!isValidationFailed()) {
                items = null;
            }
            actualizar=false;
            cancelarTipoFacultad();
            cargarTipoFacultad();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!!", msg));
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al actualziar tipo de facultad");
        }
        cargarTipoFacultad();
    } 
     
    /**
     * metodo para precarga registro a actualizar
     * @param tipoFacultad 
     */
    public void preUpdate(TipoFacultad tipoFacultad){
        try {        
            this.tipoFacultad = tipoFacultad;            
            actualizar=true;      
        } catch (Exception e) {
             JsfUtil.addErrorMessage("Error al precargar registro para ser actualizado");
        }
    }
    
      /**
      * Metodo para eliminar registro en Tipo Facultad
      * 
      */
     public void deleteTipoFacultad() {
        String msg = "Tipo de Facultad Eliminado Exitosamente!";       
        try {            
            tipoFacultadService.delete(this.delTipoFacultad);                         
            listadoTipoFacultad = tipoFacultadService.getAllByIdDesc(); 
            RequestContext.getCurrentInstance().update(":formPrincipal");
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dataChangeDlg').hide();");   
             JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de tipo de facultad"));
        }finally{
             actualizar=false;
            delTipoFacultad = new TipoFacultad();
        }
       
    } 
     
     
     /**
      * Metodo para precargar eliminacion de registro tipo facultad
     * @param tipoFacultad
      */
     public void preDeleteTipoFacultad(TipoFacultad tipoFacultad){
         try {
             this.delTipoFacultad=tipoFacultad;
             RequestContext context = RequestContext.getCurrentInstance();              
             context.execute("PF('dataChangeDlg').show();");
         } catch (Exception e) {
         }
     }
     
     
      /**
      * Metodo para eliminar registro en Tipo Facultad
      * 
      */
     public void cancelarTipoFacultad() {
        String msg = "Tipo de Facultad cancelado!";       
        try {      
            tipoFacultad = null;
            tipoFacultad = new TipoFacultad();
            RequestContext.getCurrentInstance().reset(":formAdmin");            
            if(actualizar)
            JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al cancelar registro tipo de facultad");
        }
        cargarTipoFacultad();
    } 
    
    
    
     
    public TipoFacultad getTipoFacultad() {
        return tipoFacultad;
    }

    public void setTipoFacultad(TipoFacultad tipoFacultad) {
        this.tipoFacultad = tipoFacultad;
    }

    public List<TipoFacultad> getListadoTipoFacultad() {
        return listadoTipoFacultad;
    }

    public void setListadoTipoFacultad(List<TipoFacultad> listadoTipoFacultad) {
        this.listadoTipoFacultad = listadoTipoFacultad;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public TipoFacultad getDelTipoFacultad() {
        return delTipoFacultad;
    }

    public void setDelTipoFacultad(TipoFacultad delTipoFacultad) {
        this.delTipoFacultad = delTipoFacultad;
    }
}
