/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;


import com.sisrni.model.TipoBeca;
import com.sisrni.service.TipoBecaService;
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
@Named(value = "tipoBecaMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class TipoBecaMB{
      /*Para Errores*/
    private final static Log log = LogFactory.getLog(TipoBecaMB.class);

    
    /*Variables */
    private TipoBeca tipoBeca;
    private List<TipoBeca> listTipoBeca;
    private boolean actualizar;
    
    
    
    @Autowired
    @Qualifier(value = "tipoBecaService")
    private TipoBecaService tipoBecaService;
    
    
    /*Constructor*/
    public TipoBecaMB(){
        
    }
    
    
    /*Post Constructor*/
    @PostConstruct
    public void init(){
        cargarTipoBeca();
    }
    
    /** 
     * Metodo que crea instancias de 'TipoBeca' y 'Organismo' y almacena
     * en una Lista todas las instancias de 'Organismo' que se encuentra en la
     * tabla respectiva
     */
    
    public void cargarTipoBeca(){
        tipoBeca = new TipoBeca();
        listTipoBeca = tipoBecaService.getAllByIdDesc();
        actualizar = false;
    }
    
    
    /** 
     * Metodo para guardar una instancia de 'TipoBeca' en la tabla 
     * correspondiente de la base de datos
     */
    public void guardarTipoBeca(){
        String msg ="Tipo de Beca Almacenado Exitosamente!";
        try{
           tipoBeca.setIdTipoBeca(Integer.MIN_VALUE);
           tipoBecaService.save(tipoBeca);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado!!", msg));
           
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Guardar Tipo de Beca!");
            e.printStackTrace();
        }
        cargarTipoBeca();
    }
    public List<TipoBeca> listaBecas() {
        listTipoBeca = tipoBecaService.getAllByNameAsc();
        TipoBeca tipoBecaNew1=new TipoBeca();
        List<TipoBeca> copy = new ArrayList<TipoBeca>();
        for (TipoBeca tipoBecaNew : listTipoBeca) {
            if(!tipoBecaNew.getNombreTipoBeca().equalsIgnoreCase("Agregar Nuevo"))
            {
                copy.add(tipoBecaNew);
            }else{
                tipoBecaNew1=tipoBecaNew;
            }
        }
        copy.add(tipoBecaNew1);
        listTipoBeca.clear();
        return listTipoBeca=copy;
    }
    
    /**
     * Metodo que se ocupa de precargar la instancia de 'TipoBeca' a ser actualizada
     * @param tipoBeca
     */
    public void preActualizarTipoBeca(TipoBeca tipoBeca){
       try{ 
        actualizar = true;
        this.tipoBeca = tipoBeca;
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
    
    /**
     * Metodo para actualizar las propiedades de la instancia de 'TipoBeca'
     * seleccionada
     */
    public void actualizarTipoBeca(){
        String msg ="Tipo de Beca Actualizado Exitosamente!";
        try{
            //actualizando la instancia
            tipoBecaService.merge(tipoBeca);
            actualizar = false;
            cancelarTipoBeca(); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!", msg));
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Actualizar el Tipo de Beca");
            e.printStackTrace();
        }
        cargarTipoBeca(); 
    }
    
    
    /**
     * Metodo  de Pre- borrado, que obtiene una instancia de la Entity a Borrar
     * y despliega un 'p:dialog' donde se solicita la confirmacion de la 
     * operacion de borrado
     * @param tipoBeca
     */
    public void preBorradoTipoBeca(TipoBeca tipoBeca){
        //guardando en 'tipoBeca' la instancia de 'TipoBeca' que se recibe como argumento
        this.tipoBeca = tipoBeca;
        RequestContext context = RequestContext.getCurrentInstance();
        //Desplegando el 'Dialog'
        context.execute("PF('confirmDeleteTipoBecaDlg').show();");
    }
    
    /**
     * Metodo que borra una instancia de 'TipoBeca' de la Base de datos
     */
    public void borrarTipoBeca(){ 
        String msg ="Tipo de Beca Eliminado Exitosamente!";
        try{
            //Borrando la instancia de tipoBeca
            tipoBecaService.delete(tipoBeca);
            cargarTipoBeca();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmDeleteTipoBecaDlg').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminado!!", msg));
        }catch(Exception e){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Tipo de Beca!"));
            e.printStackTrace();
        }finally{
            actualizar = false;
        }
        
        
    }
    
    
    /**
     * Metodo que se encarga de limpiar el formulario de creacion y 
     * actualizacion de TipoBeca
     */
    public void cancelarTipoBeca(){
        String msg ="Tipo de Beca cancelado";
        try{
        tipoBeca = null;
        tipoBeca = new TipoBeca();
        RequestContext.getCurrentInstance().reset(":formTipoBeca");
        if(actualizar)
        JsfUtil.addSuccessMessage(msg);
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
      cargarTipoBeca();
    }
            
    

    
    /*Getters y Setters*/

    public TipoBeca getTipoBeca() {
        return tipoBeca;
    }

    public void setTipoBeca(TipoBeca tipoBeca) {
        this.tipoBeca = tipoBeca;
    }

    public List<TipoBeca> getListTipoBeca() {
        return listTipoBeca;
    }

    public void setListTipoBeca(List<TipoBeca> listTipoBeca) {
        this.listTipoBeca = listTipoBeca;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }
    
   
    
}
