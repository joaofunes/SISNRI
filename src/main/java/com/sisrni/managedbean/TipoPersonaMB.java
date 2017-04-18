/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.managedbean.generic.GenericManagedBean;
import com.sisrni.managedbean.lazymodel.TipoPersonaLazyModel;
import com.sisrni.model.TipoPersona;
import com.sisrni.service.TipoPersonaService;
import com.sisrni.service.generic.GenericService;
import com.sisrni.utils.JsfUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Luis
 */
@Named("tipoPersonaMB")
@ViewScoped
public class TipoPersonaMB  extends GenericManagedBean<TipoPersona, Integer> {
    
    /**para errores*/
    
    private final static Log log = LogFactory.getLog(TipoPersonaMB.class);
    
     /*dependecias  tabla a la cual se le esta haciendo crud*/
    @Autowired
    @Qualifier(value = "tipoPersonaService")
    private TipoPersonaService tipoPersonaService;
    
    /**listas a utulizar*/
    
    private TipoPersona tipoPersona;
    private TipoPersona delTipoPersona;    
    private List<TipoPersona> listadoTipoPersona;
    private boolean actualizar;

   /**implementacion de GenericManagedBen
    * 
    */
    @Override
    public GenericService<TipoPersona, Integer> getService() {
        return tipoPersonaService;
    }

    @Override
    public LazyDataModel<TipoPersona> getNewLazyModel() {
        return new TipoPersonaLazyModel(tipoPersonaService);
    }

    @PostConstruct
    public void init() {
        //inicializacion de loadLazyModels
        loadLazyModels();
        cargarTipoPersona();
    }
    
    
    
     private void cargarTipoPersona() {
        try {
            actualizar=false;
            tipoPersona = new TipoPersona();
            listadoTipoPersona = tipoPersonaService.getAllByIdDesc();
        } catch (Exception e) {
            log.debug("Error al tratar de cargar las solicitudes listar para realizar un analisis..." + e.getStackTrace());

        }
    }

     
     /**
      * Metodo para almacenar registro en Tipo Persona
      * 
      */
     public void guardarTipoPersona() {
        String msg = "Tipo Persona Almacenado Exitosamente!";       
        try {            
            tipoPersonaService.save(tipoPersona);                              
            cargarTipoPersona();
            RequestContext.getCurrentInstance().update(":formPrincipal");
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado", msg));
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al guardar tipo de persona");
            e.printStackTrace();
        }
        cargarTipoPersona();
    } 
     
     /**
      * Metodo para Actualizar registro en Tipo Persona
      * 
      */
     public void updateTipoPersona() {
        String msg = "Tipo Persona Actualizado Exitosamente!";       
        try {            
            tipoPersonaService.merge(tipoPersona);
            if (!isValidationFailed()) {
                items = null;
            }
            actualizar=false;
            cancelarTipoPersona();
            cargarTipoPersona();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!!", msg));
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al actualziar tipo de persona");
        }
        cargarTipoPersona();
    } 
     
    /**
     * metodo para precarga registro a actualizar
     * @param tipoPersona 
     */
    public void preUpdate(TipoPersona tipoPersona){
        try {        
            this.tipoPersona = tipoPersona;            
            actualizar=true;      
        } catch (Exception e) {
             JsfUtil.addErrorMessage("Error al precargar registro para ser actualizado");
        }
    }
    
      /**
      * Metodo para eliminar registro en Tipo Persona
      * 
      */
     public void deleteTipoPersona() {
        String msg = "Tipo Persona Eliminado Exitosamente!";       
        try {            
            tipoPersonaService.delete(this.delTipoPersona);                         
            listadoTipoPersona = tipoPersonaService.getAllByIdDesc(); 
            RequestContext.getCurrentInstance().update(":formPrincipal");
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dataChangeDlg').hide();");   
             JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de tipo de persona"));
        }finally{
             actualizar=false;
            delTipoPersona = new TipoPersona();
        }
       
    } 
     
     
     /**
      * Metodo para precargar eliminacion de registro tipo persona
     * @param tipoPersona
      */
     public void preDeleteTipoPersona(TipoPersona tipoPersona){
         try {
             this.delTipoPersona=tipoPersona;
             RequestContext context = RequestContext.getCurrentInstance();              
             context.execute("PF('dataChangeDlg').show();");
         } catch (Exception e) {
         }
     }
     
     
      /**
      * Metodo para eliminar registro en Tipo Persona
      * 
      */
     public void cancelarTipoPersona() {
        String msg = "Tipo Persona cancelado!";       
        try {      
            tipoPersona = null;
            tipoPersona = new TipoPersona();
            RequestContext.getCurrentInstance().reset(":formAdmin");            
            JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al cancelar registro tipo de persona");
        }
        cargarTipoPersona();
    } 
    
    
    
     
    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public List<TipoPersona> getListadoTipoPersona() {
        return listadoTipoPersona;
    }

    public void setListadoTipoPersona(List<TipoPersona> listadoTipoPersona) {
        this.listadoTipoPersona = listadoTipoPersona;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public TipoPersona getDelTipoPersona() {
        return delTipoPersona;
    }

    public void setDelTipoPersona(TipoPersona delTipoPersona) {
        this.delTipoPersona = delTipoPersona;
    }
}
