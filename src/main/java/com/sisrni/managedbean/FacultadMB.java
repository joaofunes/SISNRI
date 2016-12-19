/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;


import com.sisrni.model.Facultad;
import com.sisrni.model.Universidad;
import com.sisrni.service.FacultadService;
import com.sisrni.service.UniversidadService;
import com.sisrni.utils.JsfUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


/**
 *
 * @author Luis
 */
@Named(value = "facultadMB")
@RequestScoped
public class FacultadMB{
      /*Para Errores*/
    private final static Log log = LogFactory.getLog(FacultadMB.class);

    
    /*Variables */
    private Facultad facultad;
    private Universidad universidad;
    private List<Universidad> listUniversidad;
    private List<Facultad> listFacultad;
    private boolean actualizar;
    
    
    
    @Autowired
    @Qualifier(value = "facultadService")
    private FacultadService facultadService;
    
    
    @Autowired
    @Qualifier(value = "universidadService")
    private UniversidadService universidadService;
    
    
    /*Constructor*/
    public FacultadMB(){
        
    }
    
    
    /*Post Constructor*/
    @PostConstruct
    public void init(){
        cargarFacultad();
    }
    
    /** 
     * Metodo que crea instancias de 'Facultad' y 'Universidad' y almacena
     * en una Lista todas las instancias de 'Universidad' que se encuentra en la
     * tabla respectiva
     */
    
    public void cargarFacultad(){
        facultad = new Facultad();
        universidad = new Universidad();
        listUniversidad = universidadService.findAll();
        listFacultad = facultadService.findAll();
        actualizar = false;
    }
    
    
    /** 
     * Metodo para guardar una instancia de 'Facultad' en la tabla 
     * correspondiente de la base de datos
     */
    public void guardarFacultad(){
        String msg ="Facultad Almacenado Exitosamente!";
        try{
           facultad.setIdFacultad(Integer.MIN_VALUE);
           facultad.setIdUniversidad(universidadService.findById(universidad.getIdUniversidad()));
           facultadService.save(facultad);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado!!", msg));
           
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Guardar Facultad!");
            e.printStackTrace();
        }
        cargarFacultad();
    }
    
    /**
     * Metodo que se ocupa de precargar la instancia de 'Facultad' a ser actualizada
     * @param facultad
     */
    public void preActualizarFacultad(Facultad facultad){
       try{ 
        actualizar = true;
        this.facultad = facultad;
        this.universidad.setIdUniversidad(facultad.getIdUniversidad().getIdUniversidad());
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
    
    /**
     * Metodo para actualizar las propiedades de la instancia de 'Facultad'
     * seleccionada
     */
    public void actualizarFacultad(){
        String msg ="Facultad Actualizado Exitosamente!";
        try{
            facultad.setIdUniversidad(universidadService.findById(universidad.getIdUniversidad()));
            //actualizando la instancia
            facultadService.merge(facultad);
            actualizar = false;
            cancelarFacultad(); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!", msg));
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Actualizar Facultad");
            e.printStackTrace();
        }
        cargarFacultad(); 
    }
    
    
    /**
     * Metodo  de Pre- borrado, que obtiene una instancia de la Entity a Borrar
     * y despliega un 'p:dialog' donde se solicita la confirmacion de la 
     * operacion de borrado
     * @param facultad
     */
    public void preBorradoFacultad(Facultad facultad){
        //guardando en 'facultad' la instancia de 'Facultad' que se recibe como argumento
        this.facultad = facultad;
        RequestContext context = RequestContext.getCurrentInstance();
        //Desplegando el 'Dialog'
        context.execute("PF('confirmDeleteFacultadDlg').show();");
    }
    
    /**
     * Metodo que borra una instancia de 'Facultad' de la Base de datos
     */
    public void borrarFacultad(){ 
        String msg ="Facultad Eliminado Exitosamente!";
        try{
            //Borrando la instancia de facultad
            facultadService.delete(facultad);
            cargarFacultad();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmDeleteFacultadDlg').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminado!!", msg));
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Eliminar Facultad!");
            e.printStackTrace();
        }finally{
            actualizar = false;
        }
        
        
    }
    
    
    /**
     * Metodo que se encarga de limpiar el formulario de creacion y 
     * actualizacion de Facultad
     */
    public void cancelarFacultad(){
        String msg ="Facultad cancelado";
        try{
        facultad = null;
        facultad = new Facultad();
        universidad=null;
        universidad = new Universidad();
        RequestContext.getCurrentInstance().reset(":formFacultad");
        JsfUtil.addSuccessMessage(msg);
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
      cargarFacultad();
    }
            
    
    
  
    
    /*Getters y Setters*/

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    
    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    
    public List<Universidad> getListUniversidad() {
        return listUniversidad;
    }

    public void setListUniversidad(List<Universidad> listUniversidad) {
        this.listUniversidad = listUniversidad;
    }

    public List<Facultad> getListFacultad() {
        return listFacultad;
    }

    public void setListFacultad(List<Facultad> listFacultad) {
        this.listFacultad = listFacultad;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }
    
   
    
}
