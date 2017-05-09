/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.managedbean.generic.GenericManagedBean;
import com.sisrni.managedbean.lazymodel.EtapaMovilidadLazyModel;
import com.sisrni.model.EtapaMovilidad;
import com.sisrni.service.EtapaMovilidadService;
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
@Named("etapaMovilidadMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class EtapaMovilidadMB  extends GenericManagedBean<EtapaMovilidad, Integer> {
    
    /**para errores*/
    
    private final static Log log = LogFactory.getLog(EtapaMovilidadMB.class);
    
     /*dependecias  tabla a la cual se le esta haciendo crud*/
    @Autowired
    @Qualifier(value = "etapamovilidadService")
    private EtapaMovilidadService etapaMovilidadService;
    
    /**listas a utulizar*/
    
    private EtapaMovilidad etapaMovilidad;
    private EtapaMovilidad delEtapaMovilidad;    
    private List<EtapaMovilidad> listadoEtapaMovilidad;
    private boolean actualizar;

   /**implementacion de GenericManagedBen
    * 
    */
    @Override
    public GenericService<EtapaMovilidad, Integer> getService() {
        return etapaMovilidadService;
    }

    @Override
    public LazyDataModel<EtapaMovilidad> getNewLazyModel() {
        return new EtapaMovilidadLazyModel(etapaMovilidadService);
    }

    @PostConstruct
    public void init() {
        //inicializacion de loadLazyModels
        loadLazyModels();
        cargarEtapaMovilidad();
    }
    
    
    
     private void cargarEtapaMovilidad() {
        try {
            actualizar=false;
            etapaMovilidad = new EtapaMovilidad();
            listadoEtapaMovilidad = etapaMovilidadService.getAllByIdDesc();
        } catch (Exception e) {
            log.debug("Error al tratar de cargar las solicitudes listar para realizar un analisis..." + e.getStackTrace());

        }
    }

     
     /**
      * Metodo para almacenar registro en Etapa Movilidad
      * 
      */
     public void guardarEtapaMovilidad() {
        String msg = "Etapa Movilidad Almacenada Exitosamente!";       
        try {            
            etapaMovilidadService.save(etapaMovilidad);                              
            cargarEtapaMovilidad();
            RequestContext.getCurrentInstance().update(":formPrincipal");
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardada", msg));
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al guardar etapa de movilidad");
            e.printStackTrace();
        }
        cargarEtapaMovilidad();
    } 
     
     /**
      * Metodo para Actualizar registro en Etapa Movilidad
      * 
      */
     public void updateEtapaMovilidad() {
        String msg = "Etapa Movilidad Actualizada Exitosamente!";       
        try {            
            etapaMovilidadService.merge(etapaMovilidad);
            if (!isValidationFailed()) {
                items = null;
            }
            actualizar=false;
            cancelarEtapaMovilidad();
            cargarEtapaMovilidad();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!!", msg));
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al actualziar etapa de movilidad");
        }
        cargarEtapaMovilidad();
    } 
     
    /**
     * metodo para precarga registro a actualizar
     * @param etapaMovilidad 
     */
    public void preUpdate(EtapaMovilidad etapaMovilidad){
        try {        
            this.etapaMovilidad = etapaMovilidad;            
            actualizar=true;      
        } catch (Exception e) {
             JsfUtil.addErrorMessage("Error al precargar registro para ser actualizado");
        }
    }
    
      /**
      * Metodo para eliminar registro en Etapa Movilidad
      * 
      */
     public void deleteEtapaMovilidad() {
        String msg = "Etapa Movilidad Eliminada Exitosamente!";       
        try {            
            etapaMovilidadService.delete(this.delEtapaMovilidad);                         
            listadoEtapaMovilidad = etapaMovilidadService.getAllByIdDesc(); 
            RequestContext.getCurrentInstance().update(":formPrincipal");
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dataChangeDlg').hide();");   
             JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de registro Etapa de movilidad"));
        }finally{
             actualizar=false;
            delEtapaMovilidad = new EtapaMovilidad();
        }
       
    } 
     
     
     /**
      * Metodo para precargar eliminacion de registro etapa movilidad
     * @param etapaMovilidad
      */
     public void preDeleteEtapaMovilidad(EtapaMovilidad etapaMovilidad){
         try {
             this.delEtapaMovilidad=etapaMovilidad;
             RequestContext context = RequestContext.getCurrentInstance();              
             context.execute("PF('dataChangeDlg').show();");
         } catch (Exception e) {
         }
     }
     
     
      /**
      * Metodo para eliminar registro en Etapa Movilidad
      * 
      */
     public void cancelarEtapaMovilidad() {
        String msg = "Etapa Movilidad cancelado!";       
        try {      
            etapaMovilidad = null;
            etapaMovilidad = new EtapaMovilidad();
            RequestContext.getCurrentInstance().reset(":formAdmin");            
            if(actualizar)
            JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al cancelar registro etapa de movilidad");
        }
        cargarEtapaMovilidad();
    } 
    
    public List<EtapaMovilidad> listarEtapasMovilidad(){
        listadoEtapaMovilidad = etapaMovilidadService.getAllEtapasByNameAsc();
        EtapaMovilidad etapaMovilidadNuevo = new EtapaMovilidad();
        List<EtapaMovilidad> listEtapaMovilidadCopy = new ArrayList<EtapaMovilidad>();
        
        for(EtapaMovilidad etp : listadoEtapaMovilidad){
            if(!etp.getNombreEtapa().equalsIgnoreCase("Agregar Nuevo")){
                listEtapaMovilidadCopy.add(etp);
            }else{
                etapaMovilidadNuevo = etp;
            }
        }
        listEtapaMovilidadCopy.add(etapaMovilidadNuevo);
        listadoEtapaMovilidad.clear();
        return listadoEtapaMovilidad = listEtapaMovilidadCopy;
    }
    
     
    public EtapaMovilidad getEtapaMovilidad() {
        return etapaMovilidad;
    }

    public void setEtapaMovilidad(EtapaMovilidad etapaMovilidad) {
        this.etapaMovilidad = etapaMovilidad;
    }

    public List<EtapaMovilidad> getListadoEtapaMovilidad() {
        return listadoEtapaMovilidad;
    }

    public void setListadoEtapaMovilidad(List<EtapaMovilidad> listadoEtapaMovilidad) {
        this.listadoEtapaMovilidad = listadoEtapaMovilidad;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public EtapaMovilidad getDelEtapaMovilidad() {
        return delEtapaMovilidad;
    }

    public void setDelEtapaMovilidad(EtapaMovilidad delEtapaMovilidad) {
        this.delEtapaMovilidad = delEtapaMovilidad;
    }
}
