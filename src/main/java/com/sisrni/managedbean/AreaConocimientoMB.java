/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;


import com.sisrni.model.AreaConocimiento;
import com.sisrni.service.AreaConocimientoService;
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
@Named(value = "areaConocimientoMB")
@RequestScoped
public class AreaConocimientoMB{
      /*Para Errores*/
    private final static Log log = LogFactory.getLog(AreaConocimientoMB.class);

    
    /*Variables */
    private AreaConocimiento areaConocimiento;
    private List<AreaConocimiento> listAreaConocimiento;
    private boolean actualizar;
    
    
    
    @Autowired
    @Qualifier(value = "areaConocimientoService")
    private AreaConocimientoService areaConocimientoService;
    
    
    /*Constructor*/
    public AreaConocimientoMB(){
        
    }
    
    
    /*Post Constructor*/
    @PostConstruct
    public void init(){
        cargarAreaConocimiento();
    }
    
    /** 
     * Metodo que crea instancias de 'AreaConocimiento' y 'Organismo' y almacena
     * en una Lista todas las instancias de 'Organismo' que se encuentra en la
     * tabla respectiva
     */
    
    public void cargarAreaConocimiento(){
        areaConocimiento = new AreaConocimiento();
        listAreaConocimiento = areaConocimientoService.findAll();
        actualizar = false;
    }
    
    
    /** 
     * Metodo para guardar una instancia de 'AreaConocimiento' en la tabla 
     * correspondiente de la base de datos
     */
    public void guardarAreaConocimiento(){
        String msg ="AreaConocimiento Almacenado Exitosamente!";
        try{
           areaConocimiento.setIdAreaConocimiento(Integer.MIN_VALUE);
           areaConocimientoService.save(areaConocimiento);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado!!", msg));
           
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Guardar AreaConocimiento!");
            e.printStackTrace();
        }
        cargarAreaConocimiento();
    }
    
    /**
     * Metodo que se ocupa de precargar la instancia de 'AreaConocimiento' a ser actualizada
     * @param areaConocimiento
     */
    public void preActualizarAreaConocimiento(AreaConocimiento areaConocimiento){
       try{ 
        actualizar = true;
        this.areaConocimiento = areaConocimiento;
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
    
    /**
     * Metodo para actualizar las propiedades de la instancia de 'AreaConocimiento'
     * seleccionada
     */
    public void actualizarAreaConocimiento(){
        String msg ="AreaConocimiento Actualizado Exitosamente!";
        try{
            //actualizando la instancia
            areaConocimientoService.merge(areaConocimiento);
            actualizar = false;
            cancelarAreaConocimiento(); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!", msg));
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Actualizar AreaConocimiento");
            e.printStackTrace();
        }
        cargarAreaConocimiento(); 
    }
    
    
    /**
     * Metodo  de Pre- borrado, que obtiene una instancia de la Entity a Borrar
     * y despliega un 'p:dialog' donde se solicita la confirmacion de la 
     * operacion de borrado
     * @param areaConocimiento
     */
    public void preBorradoAreaConocimiento(AreaConocimiento areaConocimiento){
        //guardando en 'areaConocimiento' la instancia de 'AreaConocimiento' que se recibe como argumento
        this.areaConocimiento = areaConocimiento;
        RequestContext context = RequestContext.getCurrentInstance();
        //Desplegando el 'Dialog'
        context.execute("PF('confirmDeleteAreaConocimientoDlg').show();");
    }
    
    /**
     * Metodo que borra una instancia de 'AreaConocimiento' de la Base de datos
     */
    public void borrarAreaConocimiento(){ 
        String msg ="AreaConocimiento Eliminado Exitosamente!";
        try{
            //Borrando la instancia de areaConocimiento
            areaConocimientoService.delete(areaConocimiento);
            cargarAreaConocimiento();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmDeleteAreaConocimientoDlg').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminado!!", msg));
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Eliminar AreaConocimiento!");
            e.printStackTrace();
        }finally{
            actualizar = false;
        }
        
        
    }
    
    
    /**
     * Metodo que se encarga de limpiar el formulario de creacion y 
     * actualizacion de AreaConocimiento
     */
    public void cancelarAreaConocimiento(){
        String msg ="AreaConocimiento cancelado";
        try{
        areaConocimiento = null;
        areaConocimiento = new AreaConocimiento();
        RequestContext.getCurrentInstance().reset(":formAreaConocimiento");
        JsfUtil.addSuccessMessage(msg);
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
      cargarAreaConocimiento();
    }
            
    

    
    /*Getters y Setters*/

    public AreaConocimiento getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(AreaConocimiento areaConocimiento) {
        this.areaConocimiento = areaConocimiento;
    }

    public List<AreaConocimiento> getListAreaConocimiento() {
        return listAreaConocimiento;
    }

    public void setListAreaConocimiento(List<AreaConocimiento> listAreaConocimiento) {
        this.listAreaConocimiento = listAreaConocimiento;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }
    
   
    
}
