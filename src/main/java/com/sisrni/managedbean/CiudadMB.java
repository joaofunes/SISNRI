/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;


import com.sisrni.model.Ciudad;
import com.sisrni.model.Provincia;
import com.sisrni.service.CiudadService;
import com.sisrni.service.ProvinciaService;
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
@Named(value = "ciudadMB")
@RequestScoped
public class CiudadMB{
      /*Para Errores*/
    private final static Log log = LogFactory.getLog(CiudadMB.class);

    
    /*Variables */
    private Ciudad ciudad;
    private Provincia provincia;
    private List<Provincia> listProvincia;
    private List<Ciudad> listCiudad;
    private boolean actualizar;
    
    
    
    @Autowired
    @Qualifier(value = "ciudadService")
    private CiudadService ciudadService;
    
    
    @Autowired
    @Qualifier(value = "provinciaService")
    private ProvinciaService provinciaService;
    
    
    /*Constructor*/
    public CiudadMB(){
        
    }
    
    
    /*Post Constructor*/
    @PostConstruct
    public void init(){
        cargarCiudad();
    }
    
    /** 
     * Metodo que crea instancias de 'Ciudad' y 'Provincia' y almacena
     * en una Lista todas las instancias de 'Provincia' que se encuentra en la
     * tabla respectiva
     */
    
    public void cargarCiudad(){
        ciudad = new Ciudad();
        provincia = new Provincia();
        listProvincia = provinciaService.findAll();
        listCiudad = ciudadService.findAll();
        actualizar = false;
    }
    
    
    /** 
     * Metodo para guardar una instancia de 'Ciudad' en la tabla 
     * correspondiente de la base de datos
     */
    public void guardarCiudad(){
        String msg ="Ciudad Almacenado Exitosamente!";
        try{
           ciudad.setIdCiudad(Integer.MIN_VALUE);
           ciudad.setIdProvincia(provinciaService.findById(provincia.getIdProvincia()));
           ciudadService.save(ciudad);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado!!", msg));
           
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Guardar Ciudad!");
            e.printStackTrace();
        }
        cargarCiudad();
    }
    
    /**
     * Metodo que se ocupa de precargar la instancia de 'Ciudad' a ser actualizada
     * @param ciudad
     */
    public void preActualizarCiudad(Ciudad ciudad){
       try{ 
        actualizar = true;
        this.ciudad = ciudad;
        this.provincia.setIdProvincia(ciudad.getIdProvincia().getIdProvincia());
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
    
    /**
     * Metodo para actualizar las propiedades de la instancia de 'Ciudad'
     * seleccionada
     */
    public void actualizarCiudad(){
        String msg ="Ciudad Actualizado Exitosamente!";
        try{
            ciudad.setIdProvincia(provinciaService.findById(provincia.getIdProvincia()));
            //actualizando la instancia
            ciudadService.merge(ciudad);
            actualizar = false;
            cancelarCiudad(); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!", msg));
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Actualizar Ciudad");
            e.printStackTrace();
        }
        cargarCiudad(); 
    }
    
    
    /**
     * Metodo  de Pre- borrado, que obtiene una instancia de la Entity a Borrar
     * y despliega un 'p:dialog' donde se solicita la confirmacion de la 
     * operacion de borrado
     * @param ciudad
     */
    public void preBorradoCiudad(Ciudad ciudad){
        //guardando en 'ciudad' la instancia de 'Ciudad' que se recibe como argumento
        this.ciudad = ciudad;
        RequestContext context = RequestContext.getCurrentInstance();
        //Desplegando el 'Dialog'
        context.execute("PF('confirmDeleteCiudadDlg').show();");
    }
    
    /**
     * Metodo que borra una instancia de 'Ciudad' de la Base de datos
     */
    public void borrarCiudad(){ 
        String msg ="Ciudad Eliminado Exitosamente!";
        try{
            //Borrando la instancia de ciudad
            ciudadService.delete(ciudad);
            cargarCiudad();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmDeleteCiudadDlg').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminado!!", msg));
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Eliminar Ciudad!");
            e.printStackTrace();
        }finally{
            actualizar = false;
        }
        
        
    }
    
    
    /**
     * Metodo que se encarga de limpiar el formulario de creacion y 
     * actualizacion de Ciudad
     */
    public void cancelarCiudad(){
        String msg ="Ciudad cancelado";
        try{
        ciudad = null;
        ciudad = new Ciudad();
        provincia=null;
        provincia = new Provincia();
        RequestContext.getCurrentInstance().reset(":formCiudad");
        JsfUtil.addSuccessMessage(msg);
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
      cargarCiudad();
    }
            
    
    
  
    
    /*Getters y Setters*/

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    
    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    
    public List<Provincia> getListProvincia() {
        return listProvincia;
    }

    public void setListProvincia(List<Provincia> listProvincia) {
        this.listProvincia = listProvincia;
    }

    public List<Ciudad> getListCiudad() {
        return listCiudad;
    }

    public void setListCiudad(List<Ciudad> listCiudad) {
        this.listCiudad = listCiudad;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }
    
   
    
}