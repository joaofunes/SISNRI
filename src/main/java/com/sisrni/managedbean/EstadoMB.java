/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.managedbean.generic.GenericManagedBean;
import com.sisrni.managedbean.lazymodel.EstadoLazyModel;
import com.sisrni.model.Estado;
import com.sisrni.service.EstadoService;
import com.sisrni.service.generic.GenericService;
import com.sisrni.utils.JsfUtil;
import java.util.Date;
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
@Named("estadoMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class EstadoMB  extends GenericManagedBean<Estado, Integer> {
    
    /**para errores*/
    
    private final static Log log = LogFactory.getLog(EstadoMB.class);
    
     /*dependecias  tabla a la cual se le esta haciendo crud*/
    @Autowired
    @Qualifier(value = "estadoService")
    private EstadoService estadoService;
    
    /**listas a utulizar*/
    
    private Estado estado;
    private Estado delEstado;    
    private List<Estado> listadoEstado;
    private boolean actualizar;

   /**implementacion de GenericManagedBen
    * 
    */
    @Override
    public GenericService<Estado, Integer> getService() {
        return estadoService;
    }

    @Override
    public LazyDataModel<Estado> getNewLazyModel() {
        return new EstadoLazyModel(estadoService);
    }

    @PostConstruct
    public void init() {
        //inicializacion de loadLazyModels
        loadLazyModels();
        cargarEstado();
    }
    
    
    
     private void cargarEstado() {
        try {
            actualizar=false;
            estado = new Estado();
            listadoEstado = estadoService.getAllByIdDesc();
        } catch (Exception e) {
            log.debug("Error al tratar de cargar las solicitudes listar para realizar un analisis..." + e.getStackTrace());

        }
    }

     
     /**
      * Metodo para almacenar registro en Estado
      * 
      */
     public void guardarEstado() {
        String msg = "Estado Almacenado Exitosamente!";       
        try {  
            Date date = new Date();
            estado.setFechaIngresoEstado(date);
            estadoService.save(estado);                              
            cargarEstado();
            RequestContext.getCurrentInstance().update(":formPrincipal");
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado", msg));
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al guardar Estado");
            e.printStackTrace();
        }
        cargarEstado();
    } 
     
     /**
      * Metodo para Actualizar registro en Estado
      * 
      */
     public void updateEstado() {
        String msg = "Estado Actualizado Exitosamente!";       
        try {            
            estadoService.merge(estado);
            if (!isValidationFailed()) {
                items = null;
            }
            actualizar=false;
            cancelarEstado();
            cargarEstado();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!!", msg));
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al actualziar Estado");
        }
        cargarEstado();
    } 
     
    /**
     * metodo para precarga registro a actualizar
     * @param estado 
     */
    public void preUpdate(Estado estado){
        try {        
            this.estado = estado;            
            actualizar=true;      
        } catch (Exception e) {
             JsfUtil.addErrorMessage("Error al precargar registro para ser actualizado");
        }
    }
    
      /**
      * Metodo para eliminar registro en Estado
      * 
      */
     public void deleteEstado() {
        String msg = "Estado Eliminado Exitosamente!";       
        try {            
            estadoService.delete(this.delEstado);                         
            listadoEstado = estadoService.getAllByIdDesc(); 
            RequestContext.getCurrentInstance().update(":formPrincipal");
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dataChangeDlg').hide();");   
             JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de registro de Estado"));
        }finally{
             actualizar=false;
            delEstado = new Estado();
        }
       
    } 
     
     
     /**
      * Metodo para precargar eliminacion de registro Estado
     * @param estado
      */
     public void preDeleteEstado(Estado estado){
         try {
             this.delEstado=estado;
             RequestContext context = RequestContext.getCurrentInstance();              
             context.execute("PF('dataChangeDlg').show();");
         } catch (Exception e) {
         }
     }
     
     
      /**
      * Metodo para eliminar registro en Estado
      * 
      */
     public void cancelarEstado() {
        String msg = "Estado cancelado!";       
        try {      
            estado = null;
            estado = new Estado();
            RequestContext.getCurrentInstance().reset(":formAdmin");            
            if(actualizar)
            JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al cancelar registro de Estado");
        }
        cargarEstado();
    } 
    
    
    
     
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getListadoEstado() {
        return listadoEstado;
    }

    public void setListadoEstado(List<Estado> listadoEstado) {
        this.listadoEstado = listadoEstado;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public Estado getDelEstado() {
        return delEstado;
    }

    public void setDelEstado(Estado delEstado) {
        this.delEstado = delEstado;
    }
}
