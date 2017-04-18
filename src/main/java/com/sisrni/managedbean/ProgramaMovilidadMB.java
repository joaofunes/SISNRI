/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.managedbean.generic.GenericManagedBean;
import com.sisrni.managedbean.lazymodel.ProgramaMovilidadLazyModel;
import com.sisrni.model.ProgramaMovilidad;
import com.sisrni.service.ProgramaMovilidadService;
import com.sisrni.service.generic.GenericService;
import com.sisrni.utils.JsfUtil;
import java.util.ArrayList;
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
@Named("programaMovilidadMB")
@ViewScoped
public class ProgramaMovilidadMB  extends GenericManagedBean<ProgramaMovilidad, Integer> {
    
    /**para errores*/
    
    private final static Log log = LogFactory.getLog(ProgramaMovilidadMB.class);
    
     /*dependecias  tabla a la cual se le esta haciendo crud*/
    @Autowired
    @Qualifier(value = "programaMovilidadService")
    private ProgramaMovilidadService programaMovilidadService;
    
    /**listas a utulizar*/
    
    private ProgramaMovilidad programaMovilidad;
    private ProgramaMovilidad delProgramaMovilidad;    
    private List<ProgramaMovilidad> listadoProgramaMovilidad;
    private boolean actualizar;

   /**implementacion de GenericManagedBen
    * 
    */
    @Override
    public GenericService<ProgramaMovilidad, Integer> getService() {
        return programaMovilidadService;
    }

    @Override
    public LazyDataModel<ProgramaMovilidad> getNewLazyModel() {
        return new ProgramaMovilidadLazyModel(programaMovilidadService);
    }

    @PostConstruct
    public void init() {
        //inicializacion de loadLazyModels
        loadLazyModels();
        cargarProgramaMovilidad();
    }
    
    
    
     private void cargarProgramaMovilidad() {
        try {
            actualizar=false;
            programaMovilidad = new ProgramaMovilidad();
            listadoProgramaMovilidad = programaMovilidadService.getAllByIdDesc();
        } catch (Exception e) {
            log.debug("Error al tratar de cargar las solicitudes listar para realizar un analisis..." + e.getStackTrace());

        }
    }

     
     /**
      * Metodo para almacenar registro en Tipo Persona
      * 
      */
     public void guardarProgramaMovilidad() {
        String msg = "Programa de Movilidad Almacenado Exitosamente!";       
        try {            
            programaMovilidadService.save(programaMovilidad);                              
            cargarProgramaMovilidad();
            RequestContext.getCurrentInstance().update(":formPrincipal");
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado", msg));
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al guardar Programa de Movilidad");
            e.printStackTrace();
        }
        cargarProgramaMovilidad();
    } 
     
     /**
      * Metodo para Actualizar registro en Tipo Persona
      * 
      */
     public void updateProgramaMovilidad() {
        String msg = "Programa de Movilidad Actualizado Exitosamente!";       
        try {            
            programaMovilidadService.merge(programaMovilidad);
            if (!isValidationFailed()) {
                items = null;
            }
            actualizar=false;
            cancelarProgramaMovilidad();
            cargarProgramaMovilidad();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!!", msg));
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al actualziar Programa de Movilidad");
        }
        cargarProgramaMovilidad();
    } 
     
    /**
     * metodo para precarga registro a actualizar
     * @param programaMovilidad 
     */
    public void preUpdate(ProgramaMovilidad programaMovilidad){
        try {        
            this.programaMovilidad = programaMovilidad;            
            actualizar=true;      
        } catch (Exception e) {
             JsfUtil.addErrorMessage("Error al precargar registro para ser actualizado");
        }
    }
    
      /**
      * Metodo para eliminar registro en Tipo Persona
      * 
      */
     public void deleteProgramaMovilidad() {
        String msg = "Programa de Movilidad Eliminado Exitosamente!";       
        try {            
            programaMovilidadService.delete(this.delProgramaMovilidad);                         
            listadoProgramaMovilidad = programaMovilidadService.getAllByIdDesc(); 
            RequestContext.getCurrentInstance().update(":formPrincipal");
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dataChangeDlg').hide();");   
             JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Programa de Movilidad"));
        }finally{
             actualizar=false;
            delProgramaMovilidad = new ProgramaMovilidad();
        }
       
    } 
     
     
     /**
      * Metodo para precargar eliminacion de registro tipo persona
     * @param programaMovilidad
      */
     public void preDeleteProgramaMovilidad(ProgramaMovilidad programaMovilidad){
         try {
             this.delProgramaMovilidad=programaMovilidad;
             RequestContext context = RequestContext.getCurrentInstance();              
             context.execute("PF('dataChangeDlg').show();");
         } catch (Exception e) {
         }
     }
     
     
      /**
      * Metodo para eliminar registro en Tipo Persona
      * 
      */
     public void cancelarProgramaMovilidad() {
        String msg = "Programa de Movilidad cancelado!";       
        try {      
            programaMovilidad = null;
            programaMovilidad = new ProgramaMovilidad();
            RequestContext.getCurrentInstance().reset(":formAdmin");            
            if(actualizar)
            JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al cancelar registro Programa de Movilidad");
        }
        cargarProgramaMovilidad();
    } 
     
     
     public List<ProgramaMovilidad> listarProgramasMovilidad(){
         listadoProgramaMovilidad = programaMovilidadService.geyAllProgramaMovilidadByNameAsc();
         ProgramaMovilidad programaMovilidadNuevo = new ProgramaMovilidad();
         List<ProgramaMovilidad> listProgramaMovilidadCopy = new ArrayList<ProgramaMovilidad>();
         for(ProgramaMovilidad prog : listadoProgramaMovilidad){
             if(!prog.getNombreProgramaMovilidad().equalsIgnoreCase("Agregar Nuevo")){
                 listProgramaMovilidadCopy.add(prog);
             }else{
                 programaMovilidadNuevo= prog;
             }
         }
         listProgramaMovilidadCopy.add(programaMovilidadNuevo);
         listadoProgramaMovilidad.clear();
         return listadoProgramaMovilidad = listProgramaMovilidadCopy;
     }
    
    
    
     
    public ProgramaMovilidad getProgramaMovilidad() {
        return programaMovilidad;
    }

    public void setProgramaMovilidad(ProgramaMovilidad programaMovilidad) {
        this.programaMovilidad = programaMovilidad;
    }

    public List<ProgramaMovilidad> getListadoProgramaMovilidad() {
        return listadoProgramaMovilidad;
    }

    public void setListadoProgramaMovilidad(List<ProgramaMovilidad> listadoProgramaMovilidad) {
        this.listadoProgramaMovilidad = listadoProgramaMovilidad;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public ProgramaMovilidad getDelProgramaMovilidad() {
        return delProgramaMovilidad;
    }

    public void setDelProgramaMovilidad(ProgramaMovilidad delProgramaMovilidad) {
        this.delProgramaMovilidad = delProgramaMovilidad;
    }
}
