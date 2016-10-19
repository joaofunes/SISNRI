/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.managedbean.generic.GenericManagedBean;
import com.sisrni.managedbean.lazymodel.TipoOrganismoLazyModel;
import com.sisrni.model.TipoOrganismo;
import com.sisrni.service.TipoOrganismoService;
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
 * @author Joao
 */
@Named("tipoOrganismoMB")
@ViewScoped
public class TipoOrganismoMB  extends GenericManagedBean<TipoOrganismo, Integer> {
    
    /**para errores*/
    
    private final static Log log = LogFactory.getLog(TipoOrganismoMB.class);
    
     /*dependecias  tabla a la cual se le esta haciendo crud*/
    @Autowired
    @Qualifier(value = "tipoOrganismoService")
    private TipoOrganismoService tipoOrganismoService;
    
    /**listas a utulizar*/
    
    private TipoOrganismo tipoOrganismo;
    private TipoOrganismo delTipoOrganismo;    
    private List<TipoOrganismo> listadoTipoOrganismo;
    private boolean actualizar;

   /**implementacion de GenericManagedBen
    * 
    */
    @Override
    public GenericService<TipoOrganismo, Integer> getService() {
        return tipoOrganismoService;
    }

    @Override
    public LazyDataModel<TipoOrganismo> getNewLazyModel() {
        return new TipoOrganismoLazyModel(tipoOrganismoService);
    }

    @PostConstruct
    public void init() {
        //inicializacion de loadLazyModels
        loadLazyModels();
        cargarTipoOrganismo();
    }
    
    
    
     private void cargarTipoOrganismo() {
        try {
            actualizar=false;
            tipoOrganismo = new TipoOrganismo();
            listadoTipoOrganismo = tipoOrganismoService.findAll();
        } catch (Exception e) {
            log.debug("Error al tratar de cargar las solicitudes listar para realizar un analisis..." + e.getStackTrace());

        }
    }

     
     /**
      * Metodo para almacenar registro en Tipo Organismo
      * 
      */
     public void guardarTipoOrganismo() {
        String msg = "Tipo Organismo Almacenado Exitosamente!";       
        try {            
            tipoOrganismoService.save(tipoOrganismo);                              
            cargarTipoOrganismo();
            RequestContext.getCurrentInstance().update(":formPrincipal");
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado", msg));
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al guardar tipo de organismo");
            e.printStackTrace();
        }
        cargarTipoOrganismo();
    } 
     
     /**
      * Metodo para Actualizar registro en Tipo Organismo
      * 
      */
     public void updateTipoOrganismo() {
        String msg = "Tipo Organismo Actualizado Exitosamente!";       
        try {            
            tipoOrganismoService.merge(tipoOrganismo);
            if (!isValidationFailed()) {
                items = null;
            }
            actualizar=false;
            cancelarTipoOrganismo();
            cargarTipoOrganismo();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!!", msg));
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al actualziar tipo de organismo");
        }
        cargarTipoOrganismo();
    } 
     
    /**
     * metodo para precarga registro a actualizar
     * @param tipoOrganismo 
     */
    public void preUpdate(TipoOrganismo tipoOrganismo){
        try {        
            this.tipoOrganismo = tipoOrganismo;            
            actualizar=true;      
        } catch (Exception e) {
             JsfUtil.addErrorMessage("Error al precargar registro para ser actualizado");
        }
    }
    
      /**
      * Metodo para eliminar registro en Tipo Organismo
      * 
      */
     public void deleteTipoOrganismo() {
        String msg = "Tipo Organismo Eliminado Exitosamente!";       
        try {            
            tipoOrganismoService.delete(this.delTipoOrganismo);                         
            listadoTipoOrganismo = tipoOrganismoService.findAll(); 
            RequestContext.getCurrentInstance().update(":formPrincipal");
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dataChangeDlg').hide();");   
             JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al eliminar registro tipo de organismo");
        }finally{
             actualizar=false;
            delTipoOrganismo = new TipoOrganismo();
        }
       
    } 
     
     
     /**
      * Metodo para precargar eliminacion de registro tipo organismo
     * @param tipoOrganismo
      */
     public void preDeleteTipoOrganismo(TipoOrganismo tipoOrganismo){
         try {
             this.delTipoOrganismo=tipoOrganismo;
             RequestContext context = RequestContext.getCurrentInstance();              
             context.execute("PF('dataChangeDlg').show();");
         } catch (Exception e) {
         }
     }
     
     
      /**
      * Metodo para eliminar registro en Tipo Organismo
      * 
      */
     public void cancelarTipoOrganismo() {
        String msg = "Tipo Organismo cancelado!";       
        try {      
            tipoOrganismo = null;
            tipoOrganismo = new TipoOrganismo();
            RequestContext.getCurrentInstance().reset(":formAdmin");            
            JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al cancelar registro tipo de organismo");
        }
        cargarTipoOrganismo();
    } 
    
    
    
     
    public TipoOrganismo getTipoOrganismo() {
        return tipoOrganismo;
    }

    public void setTipoOrganismo(TipoOrganismo tipoOrganismo) {
        this.tipoOrganismo = tipoOrganismo;
    }

    public List<TipoOrganismo> getListadoTipoOrganismo() {
        return listadoTipoOrganismo;
    }

    public void setListadoTipoOrganismo(List<TipoOrganismo> listadoTipoOrganismo) {
        this.listadoTipoOrganismo = listadoTipoOrganismo;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public TipoOrganismo getDelTipoOrganismo() {
        return delTipoOrganismo;
    }

    public void setDelTipoOrganismo(TipoOrganismo delTipoOrganismo) {
        this.delTipoOrganismo = delTipoOrganismo;
    }
}
