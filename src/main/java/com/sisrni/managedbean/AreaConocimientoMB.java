/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;


import com.sisrni.model.AreaConocimiento;
import com.sisrni.service.AreaConocimientoService;
import com.sisrni.utils.JsfUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;


/**
 *
 * @author Luis
 */
@Named(value = "areaConocimientoMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
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
        listAreaConocimiento = areaConocimientoService.getAllByIdDesc();
        actualizar = false;
    }
    
    
    /** 
     * Metodo para guardar una instancia de 'AreaConocimiento' en la tabla 
     * correspondiente de la base de datos
     */
    public void guardarAreaConocimiento(){
        String msg ="Area de Conocimiento Almacenada Exitosamente!";
        try{
           areaConocimiento.setIdAreaConocimiento(Integer.MIN_VALUE);
           areaConocimientoService.save(areaConocimiento);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardada!!", msg));
           
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Guardar Area de Conocimiento!");
            e.printStackTrace();
        }
        cargarAreaConocimiento();
    }
    //
    public List<AreaConocimiento> listaAreas() {
        listAreaConocimiento = areaConocimientoService.getAllByNameAsc();
        AreaConocimiento areaNew1=new AreaConocimiento();
        List<AreaConocimiento> copy = new ArrayList<AreaConocimiento>();
        for (AreaConocimiento areaNew : listAreaConocimiento) {
            if(!areaNew.getNombreArea().equalsIgnoreCase("Agregar Nueva"))
            {
                copy.add(areaNew);
            }else{
                areaNew1=areaNew;
            }
        }
        copy.add(areaNew1);
        listAreaConocimiento.clear();
        return listAreaConocimiento=copy;
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
        String msg ="Area de Conocimiento Actualizada Exitosamente!";
        try{
            //actualizando la instancia
            areaConocimientoService.merge(areaConocimiento);
            actualizar = false;
            cancelarAreaConocimiento(); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!", msg));
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Actualizar Area de Conocimiento");
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
        String msg ="Area de Conocimiento Eliminada Exitosamente!";
        try{
            //Borrando la instancia de areaConocimiento
            areaConocimientoService.delete(areaConocimiento);
            cargarAreaConocimiento();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmDeleteAreaConocimientoDlg').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminada!!", msg));
        }catch(Exception e){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Area de Conocimiento!"));
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
        String msg ="Area de Conocimiento cancelada";
        try{
        areaConocimiento = null;
        areaConocimiento = new AreaConocimiento();
        RequestContext.getCurrentInstance().reset(":formAreaConocimiento");
        if(actualizar)
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
