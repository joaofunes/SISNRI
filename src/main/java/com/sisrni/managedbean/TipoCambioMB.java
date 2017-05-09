/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.managedbean.generic.GenericManagedBean;
import com.sisrni.managedbean.lazymodel.TipoCambioLazyModel;
import com.sisrni.model.TipoCambio;
import com.sisrni.service.TipoCambioService;
import com.sisrni.service.generic.GenericService;
import com.sisrni.utils.JsfUtil;
import java.util.ArrayList;
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
@Named("tipoCambioMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class TipoCambioMB  extends GenericManagedBean<TipoCambio, Integer> {
    
    /**para errores*/
    
    private final static Log log = LogFactory.getLog(TipoCambioMB.class);
    
     /*dependecias  tabla a la cual se le esta haciendo crud*/
    @Autowired
    @Qualifier(value = "tipoCambioService")
    private TipoCambioService tipoCambioService;
    
    /**listas a utulizar*/
    
    private TipoCambio tipoCambio;
    private TipoCambio delTipoCambio;    
    private List<TipoCambio> listadoTipoCambio;
    private boolean actualizar;

   /**implementacion de GenericManagedBen
    * 
    */
    @Override
    public GenericService<TipoCambio, Integer> getService() {
        return tipoCambioService;
    }

    @Override
    public LazyDataModel<TipoCambio> getNewLazyModel() {
        return new TipoCambioLazyModel(tipoCambioService);
    }

    @PostConstruct
    public void init() {
        //inicializacion de loadLazyModels
        loadLazyModels();
        cargarTipoCambio();
    }
    
    
    
     private void cargarTipoCambio() {
        try {
            actualizar=false;
            tipoCambio = new TipoCambio();
            listadoTipoCambio = tipoCambioService.getAllByIdDesc();
        } catch (Exception e) {
            log.debug("Error al tratar de cargar las solicitudes listar para realizar un analisis..." + e.getStackTrace());

        }
    }

     
     /**
      * Metodo para almacenar registro en Tipo Cambio
      * 
      */
     public void guardarTipoCambio() {
        String msg = "Tipo Cambio Almacenado Exitosamente!";       
        try {            
            tipoCambioService.save(tipoCambio);                              
            cargarTipoCambio();
            RequestContext.getCurrentInstance().update(":formPrincipal");
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado", msg));
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al guardar tipo de cambio");
            e.printStackTrace();
        }
        cargarTipoCambio();
    } 
     
     // metodo para ordenar lista de tipos de cambio
     public List<TipoCambio> listaTipoCambio() {
        listadoTipoCambio = tipoCambioService.getAllByNameAsc();
        TipoCambio tipoCambioNew1=new TipoCambio();
        List<TipoCambio> copy = new ArrayList<TipoCambio>();
        for (TipoCambio tipoCambioNew : listadoTipoCambio) {
            if(!tipoCambioNew.getNombreDivisa().equalsIgnoreCase("Agregar Nuevo"))
            {
                copy.add(tipoCambioNew);
            }else{
                tipoCambioNew1=tipoCambioNew;
            }
        }
        copy.add(tipoCambioNew1);
        listadoTipoCambio.clear();
        return listadoTipoCambio=copy;
    }
     
     /**
      * Metodo para Actualizar registro en Tipo Cambio
      * 
      */
     public void updateTipoCambio() {
        String msg = "Tipo Cambio Actualizado Exitosamente!";       
        try {            
            tipoCambioService.merge(tipoCambio);
            if (!isValidationFailed()) {
                items = null;
            }
            actualizar=false;
            cancelarTipoCambio();
            cargarTipoCambio();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!!", msg));
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al actualizar tipo de cambio");
        }
        cargarTipoCambio();
    } 
     
    /**
     * metodo para precarga registro a actualizar
     * @param tipoCambio 
     */
    public void preUpdate(TipoCambio tipoCambio){
        try {        
            this.tipoCambio = tipoCambio;            
            actualizar=true;      
        } catch (Exception e) {
             JsfUtil.addErrorMessage("Error al precargar registro para ser actualizado");
        }
    }
    
      /**
      * Metodo para eliminar registro en Tipo Cambio
      * 
      */
     public void deleteTipoCambio() {
        String msg = "Tipo Cambio Eliminado Exitosamente!";       
        try {            
            tipoCambioService.delete(this.delTipoCambio);                         
            listadoTipoCambio = tipoCambioService.getAllByIdDesc(); 
            RequestContext.getCurrentInstance().update(":formPrincipal");
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dataChangeDlg').hide();");   
             JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de tipo de cambio"));
        }finally{
             actualizar=false;
            delTipoCambio = new TipoCambio();
        }
       
    } 
     
     
     /**
      * Metodo para precargar eliminacion de registro tipo cambio
     * @param tipoCambio
      */
     public void preDeleteTipoCambio(TipoCambio tipoCambio){
         try {
             this.delTipoCambio=tipoCambio;
             RequestContext context = RequestContext.getCurrentInstance();              
             context.execute("PF('dataChangeDlg').show();");
         } catch (Exception e) {
         }
     }
     
     
      /**
      * Metodo para eliminar registro en Tipo Cambio
      * 
      */
     public void cancelarTipoCambio() {
        String msg = "Tipo Cambio cancelado!";       
        try {      
            tipoCambio = null;
            tipoCambio = new TipoCambio();
            RequestContext.getCurrentInstance().reset(":formAdmin");            
            if(actualizar)
            JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al cancelar registro tipo de cambio");
        }
        cargarTipoCambio();
    } 
    
    
    
     
    public TipoCambio getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(TipoCambio tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public List<TipoCambio> getListadoTipoCambio() {
        return listadoTipoCambio;
    }

    public void setListadoTipoCambio(List<TipoCambio> listadoTipoCambio) {
        this.listadoTipoCambio = listadoTipoCambio;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public TipoCambio getDelTipoCambio() {
        return delTipoCambio;
    }

    public void setDelTipoCambio(TipoCambio delTipoCambio) {
        this.delTipoCambio = delTipoCambio;
    }
}
