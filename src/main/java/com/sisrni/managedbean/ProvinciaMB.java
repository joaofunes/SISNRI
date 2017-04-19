/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;


import com.sisrni.model.Provincia;
import com.sisrni.model.Pais;
import com.sisrni.service.ProvinciaService;
import com.sisrni.service.PaisService;
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
@Named(value = "provinciaMB")
@RequestScoped
public class ProvinciaMB{
      /*Para Errores*/
    private final static Log log = LogFactory.getLog(ProvinciaMB.class);

    
    /*Variables */
    private Provincia provincia;
    private Pais pais;
    private List<Pais> listPais;
    private List<Provincia> listProvincia;
    private boolean actualizar;
    
    
    
    @Autowired
    @Qualifier(value = "provinciaService")
    private ProvinciaService provinciaService;
    
    
    @Autowired
    @Qualifier(value = "paisService")
    private PaisService paisService;
    
    
    /*Constructor*/
    public ProvinciaMB(){
        
    }
    
    
    /*Post Constructor*/
    @PostConstruct
    public void init(){
        cargarProvincia();
    }
    
    /** 
     * Metodo que crea instancias de 'Provincia' y 'Pais' y almacena
     * en una Lista todas las instancias de 'Pais' que se encuentra en la
     * tabla respectiva
     */
    
    public void cargarProvincia(){
        provincia = new Provincia();
        pais = new Pais();
        listPais = paisService.getAllByIdDesc();
        listProvincia = provinciaService.getAllByIdDesc();
        actualizar = false;
    }
    
    
    /** 
     * Metodo para guardar una instancia de 'Provincia' en la tabla 
     * correspondiente de la base de datos
     */
    public void guardarProvincia(){
        String msg ="Estado/Departamento/Provincia Almacenado Exitosamente!";
        try{
           provincia.setIdProvincia(Integer.MIN_VALUE);
           provincia.setIdPais(paisService.findById(pais.getIdPais()));
           provinciaService.save(provincia);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado!!", msg));
           
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Guardar Estado/Departamento/Provincia!");
            e.printStackTrace();
        }
        cargarProvincia();
    }
    
    /**
     * Metodo que se ocupa de precargar la instancia de 'Provincia' a ser actualizada
     * @param provincia
     */
    public void preActualizarProvincia(Provincia provincia){
       try{ 
        actualizar = true;
        this.provincia = provincia;
        this.pais.setIdPais(provincia.getIdPais().getIdPais());
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
    
    /**
     * Metodo para actualizar las propiedades de la instancia de 'Provincia'
     * seleccionada
     */
    public void actualizarProvincia(){
        String msg ="Estado/Departamento/Provincia Actualizado Exitosamente!";
        try{
            provincia.setIdPais(paisService.findById(pais.getIdPais()));
            //actualizando la instancia
            provinciaService.merge(provincia);
            actualizar = false;
            cancelarProvincia(); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!", msg));
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Actualizar Estado/Departamento/Provincia");
            e.printStackTrace();
        }
        cargarProvincia(); 
    }
    
    
    /**
     * Metodo  de Pre- borrado, que obtiene una instancia de la Entity a Borrar
     * y despliega un 'p:dialog' donde se solicita la confirmacion de la 
     * operacion de borrado
     * @param provincia
     */
    public void preBorradoProvincia(Provincia provincia){
        //guardando en 'provincia' la instancia de 'Provincia' que se recibe como argumento
        this.provincia = provincia;
        RequestContext context = RequestContext.getCurrentInstance();
        //Desplegando el 'Dialog'
        context.execute("PF('confirmDeleteProvinciaDlg').show();");
    }
    
    /**
     * Metodo que borra una instancia de 'Provincia' de la Base de datos
     */
    public void borrarProvincia(){ 
        String msg ="Estado/Departamento/Provincia Eliminado Exitosamente!";
        try{
            //Borrando la instancia de provincia
            provinciaService.delete(provincia);
            cargarProvincia();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmDeleteProvinciaDlg').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminado!!", msg));
        }catch(Exception e){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Estado/Departamento/Provincia!"));
            e.printStackTrace();
        }finally{
            actualizar = false;
        }
        
        
    }
    
    
    /**
     * Metodo que se encarga de limpiar el formulario de creacion y 
     * actualizacion de Provincia
     */
    public void cancelarProvincia(){
        String msg ="Estado/Departamento/Provincia cancelado";
        try{
        provincia = null;
        provincia = new Provincia();
        pais=null;
        pais = new Pais();
        RequestContext.getCurrentInstance().reset(":formProvincia");
        if(actualizar)
        JsfUtil.addSuccessMessage(msg);
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
      cargarProvincia();
    }
            
    
    
  
    
    /*Getters y Setters*/

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    
    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    
    public List<Pais> getListPais() {
        return listPais;
    }

    public void setListPais(List<Pais> listPais) {
        this.listPais = listPais;
    }

    public List<Provincia> getListProvincia() {
        return listProvincia;
    }

    public void setListProvincia(List<Provincia> listProvincia) {
        this.listProvincia = listProvincia;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }
    
   
    
}
