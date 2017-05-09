/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.managedbean.generic.GenericManagedBean;
import com.sisrni.managedbean.lazymodel.TipoPropuestaConvenioLazyModel;
import com.sisrni.model.TipoPropuestaConvenio;
import com.sisrni.service.TipoPropuestaConvenioService;
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
@Named("tipoPropuestaConvenioMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class TipoPropuestaConvenioMB  extends GenericManagedBean<TipoPropuestaConvenio, Integer> {
    
    /**para errores*/
    
    private final static Log log = LogFactory.getLog(TipoPropuestaConvenioMB.class);
    
     /*dependecias  tabla a la cual se le esta haciendo crud*/
    @Autowired
    @Qualifier(value = "tipoPropuestaConvenioService")
    private TipoPropuestaConvenioService tipoPropuestaConvenioService;
    
    /**listas a utulizar*/
    
    private TipoPropuestaConvenio tipoPropuestaConvenio;
    private TipoPropuestaConvenio delTipoPropuestaConvenio;    
    private List<TipoPropuestaConvenio> listadoTipoPropuestaConvenio;
    private boolean actualizar;

   /**implementacion de GenericManagedBen
    * 
    */
    @Override
    public GenericService<TipoPropuestaConvenio, Integer> getService() {
        return tipoPropuestaConvenioService;
    }

    @Override
    public LazyDataModel<TipoPropuestaConvenio> getNewLazyModel() {
        return new TipoPropuestaConvenioLazyModel(tipoPropuestaConvenioService);
    }

    @PostConstruct
    public void init() {
        //inicializacion de loadLazyModels
        loadLazyModels();
        cargarTipoPropuestaConvenio();
    }
    
    
    
     private void cargarTipoPropuestaConvenio() {
        try {
            actualizar=false;
            tipoPropuestaConvenio = new TipoPropuestaConvenio();
            listadoTipoPropuestaConvenio = tipoPropuestaConvenioService.getAllByIdDesc();
        } catch (Exception e) {
            log.debug("Error al tratar de cargar las solicitudes listar para realizar un analisis..." + e.getStackTrace());

        }
    }

     
     /**
      * Metodo para almacenar registro en Tipo PropuestaConvenio
      * 
      */
     public void guardarTipoPropuestaConvenio() {
        String msg = "Tipo de Propuesta de Convenio Almacenado Exitosamente!";       
        try {            
            tipoPropuestaConvenioService.save(tipoPropuestaConvenio);                              
            cargarTipoPropuestaConvenio();
            RequestContext.getCurrentInstance().update(":formPrincipal");            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado", msg));
             cargarTipoPropuestaConvenio();
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al guardar tipo de Propuesta de Convenio");
            e.printStackTrace();
        }
       
    } 
     /**
      * Metodo para almacenar registro en Tipo PropuestaConvenio
      * 
      */
     public void guardarTipoPropuestaConvenioOthearBean() {
        String msg = "Tipo de Propuesta de Convenio Almacenado Exitosamente!";       
        try {            
            tipoPropuestaConvenioService.save(tipoPropuestaConvenio);                              
            cargarTipoPropuestaConvenio();
            RequestContext.getCurrentInstance().update(":formPrincipal");            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado", msg));
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al guardar tipo de Propuesta de Convenio");
            e.printStackTrace();
        }
       
    } 
     
     /**
      * Metodo para Actualizar registro en Tipo PropuestaConvenio
      * 
      */
     public void updateTipoPropuestaConvenio() {
        String msg = "Tipo de Propuesta de Convenio Actualizado Exitosamente!";       
        try {            
            tipoPropuestaConvenioService.merge(tipoPropuestaConvenio);
            if (!isValidationFailed()) {
                items = null;
            }
            actualizar=false;
            cancelarTipoPropuestaConvenio();
            cargarTipoPropuestaConvenio();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!!", msg));
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al actualziar tipo de Propuesta de Convenio");
        }
        cargarTipoPropuestaConvenio();
    } 
     
    /**
     * metodo para precarga registro a actualizar
     * @param tipoPropuestaConvenio 
     */
    public void preUpdate(TipoPropuestaConvenio tipoPropuestaConvenio){
        try {        
            this.tipoPropuestaConvenio = tipoPropuestaConvenio;            
            actualizar=true;      
        } catch (Exception e) {
             JsfUtil.addErrorMessage("Error al precargar registro para ser actualizado");
        }
    }
    
      /**
      * Metodo para eliminar registro en Tipo PropuestaConvenio
      * 
      */
     public void deleteTipoPropuestaConvenio() {
        String msg = "Tipo de Propuesta de Convenio Eliminado Exitosamente!";       
        try {            
            tipoPropuestaConvenioService.delete(this.delTipoPropuestaConvenio);                         
            listadoTipoPropuestaConvenio = tipoPropuestaConvenioService.getAllByIdDesc(); 
            RequestContext.getCurrentInstance().update(":formPrincipal");
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dataChangeDlg').hide();");   
             JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de tipo de Propuesta de Convenio"));
        }finally{
             actualizar=false;
            delTipoPropuestaConvenio = new TipoPropuestaConvenio();
        }
       
    } 
     
     
     /**
      * Metodo para precargar eliminacion de registro tipo Propuesta de Convenio
     * @param tipoPropuestaConvenio
      */
     public void preDeleteTipoPropuestaConvenio(TipoPropuestaConvenio tipoPropuestaConvenio){
         try {
             this.delTipoPropuestaConvenio=tipoPropuestaConvenio;
             RequestContext context = RequestContext.getCurrentInstance();              
             context.execute("PF('dataChangeDlg').show();");
         } catch (Exception e) {
         }
     }
     
     
      /**
      * Metodo para eliminar registro en Tipo PropuestaConvenio
      * 
      */
     public void cancelarTipoPropuestaConvenio() {
        String msg = "Tipo de Propuesta de Convenio cancelado!";       
        try {      
            tipoPropuestaConvenio = null;
            tipoPropuestaConvenio = new TipoPropuestaConvenio();
            RequestContext.getCurrentInstance().reset(":formAdmin");            
            if(actualizar)
            JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al cancelar registro tipo de Propuesta de Convenio");
        }
        cargarTipoPropuestaConvenio();
    } 
    
    
    
     
    public TipoPropuestaConvenio getTipoPropuestaConvenio() {
        return tipoPropuestaConvenio;
    }

    public void setTipoPropuestaConvenio(TipoPropuestaConvenio tipoPropuestaConvenio) {
        this.tipoPropuestaConvenio = tipoPropuestaConvenio;
    }

    public List<TipoPropuestaConvenio> getListadoTipoPropuestaConvenio() {
        return listadoTipoPropuestaConvenio;
    }

    public void setListadoTipoPropuestaConvenio(List<TipoPropuestaConvenio> listadoTipoPropuestaConvenio) {
        this.listadoTipoPropuestaConvenio = listadoTipoPropuestaConvenio;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public TipoPropuestaConvenio getDelTipoPropuestaConvenio() {
        return delTipoPropuestaConvenio;
    }

    public void setDelTipoPropuestaConvenio(TipoPropuestaConvenio delTipoPropuestaConvenio) {
        this.delTipoPropuestaConvenio = delTipoPropuestaConvenio;
    }
}
