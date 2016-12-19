/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;


import com.sisrni.model.Unidad;
import com.sisrni.model.Facultad;
import com.sisrni.service.UnidadService;
import com.sisrni.service.FacultadService;
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
 * @author Usuario
 */
@Named(value = "unidadMB")
@RequestScoped
public class UnidadMB{
      /*Para Errores*/
    private final static Log log = LogFactory.getLog(UnidadMB.class);

    
    /*Variables */
    private Unidad unidad;
    private Facultad facultad;
    private List<Facultad> listFacultad;
    private List<Unidad> listUnidad;
    private boolean actualizar;
    
    
    
    @Autowired
    @Qualifier(value = "unidadService")
    private UnidadService unidadService;
    
    
    @Autowired
    @Qualifier(value = "facultadService")
    private FacultadService facultadService;
    
    
    /*Constructor*/
    public UnidadMB(){
        
    }
    
    
    /*Post Constructor*/
    @PostConstruct
    public void init(){
        cargarUnidad();
    }
    
    /** 
     * Metodo que crea instancias de 'Unidad' y 'Facultad' y almacena
     * en una Lista todas las instancias de 'Facultad' que se encuentra en la
     * tabla respectiva
     */
    
    public void cargarUnidad(){
        unidad = new Unidad();
        facultad = new Facultad();
        listFacultad = facultadService.findAll();
        listUnidad = unidadService.findAll();
        actualizar = false;
    }
    
    
    /** 
     * Metodo para guardar una instancia de 'Unidad' en la tabla 
     * correspondiente de la base de datos
     */
    public void guardarUnidad(){
        String msg ="Unidad Almacenado Exitosamente!";
        try{
           unidad.setIdUnidad(Integer.MIN_VALUE);
           unidad.setIdFacultad(facultadService.findById(facultad.getIdFacultad()));
           unidadService.save(unidad);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado!!", msg));
           
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Guardar Unidad!");
            e.printStackTrace();
        }
        cargarUnidad();
    }
    
    /**
     * Metodo que se ocupa de precargar la instancia de 'Unidad' a ser actualizada
     * @param unidad
     */
    public void preActualizarUnidad(Unidad unidad){
       try{ 
        actualizar = true;
        this.unidad = unidad;
        this.facultad.setIdFacultad(unidad.getIdFacultad().getIdFacultad());
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
    
    /**
     * Metodo para actualizar las propiedades de la instancia de 'Unidad'
     * seleccionada
     */
    public void actualizarUnidad(){
        String msg ="Unidad Actualizado Exitosamente!";
        try{
            unidad.setIdFacultad(facultadService.findById(facultad.getIdFacultad()));
            //actualizando la instancia
            unidadService.merge(unidad);
            actualizar = false;
            cancelarUnidad(); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!", msg));
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Actualizar Unidad");
            e.printStackTrace();
        }
        cargarUnidad(); 
    }
    
    
    /**
     * Metodo  de Pre- borrado, que obtiene una instancia de la Entity a Borrar
     * y despliega un 'p:dialog' donde se solicita la confirmacion de la 
     * operacion de borrado
     * @param unidad
     */
    public void preBorradoUnidad(Unidad unidad){
        //guardando en 'unidad' la instancia de 'Unidad' que se recibe como argumento
        this.unidad = unidad;
        RequestContext context = RequestContext.getCurrentInstance();
        //Desplegando el 'Dialog'
        context.execute("PF('confirmDeleteUnidadDlg').show();");
    }
    
    /**
     * Metodo que borra una instancia de 'Unidad' de la Base de datos
     */
    public void borrarUnidad(){ 
        String msg ="Unidad Eliminado Exitosamente!";
        try{
            //Borrando la instancia de unidad
            unidadService.delete(unidad);
            cargarUnidad();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmDeleteUnidadDlg').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminado!!", msg));
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Eliminar Unidad!");
            e.printStackTrace();
        }finally{
            actualizar = false;
        }
        
        
    }
    
    
    /**
     * Metodo que se encarga de limpiar el formulario de creacion y 
     * actualizacion de Unidad
     */
    public void cancelarUnidad(){
        String msg ="Unidad cancelado";
        try{
        unidad = null;
        unidad = new Unidad();
        facultad=null;
        facultad = new Facultad();
        RequestContext.getCurrentInstance().reset(":formUnidad");
        JsfUtil.addSuccessMessage(msg);
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
      cargarUnidad();
    }
            
    
    
  
    
    /*Getters y Setters*/

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    
    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    
    public List<Facultad> getListFacultad() {
        return listFacultad;
    }

    public void setListFacultad(List<Facultad> listFacultad) {
        this.listFacultad = listFacultad;
    }

    public List<Unidad> getListUnidad() {
        return listUnidad;
    }

    public void setListUnidad(List<Unidad> listUnidad) {
        this.listUnidad = listUnidad;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }
    
   
    
}
