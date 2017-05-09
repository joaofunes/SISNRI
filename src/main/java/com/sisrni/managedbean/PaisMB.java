/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;


import com.sisrni.model.Pais;
import com.sisrni.model.Region;
import com.sisrni.service.PaisService;
import com.sisrni.service.RegionService;
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
 * @author Usuario
 */
@Named(value = "paisMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PaisMB{
      /*Para Errores*/
    private final static Log log = LogFactory.getLog(PaisMB.class);

    
    /*Variables */
    private Pais pais;
    private Region region;
    private List<Region> listRegion;
    private List<Pais> listPais;
    private boolean actualizar;
    
    
    
    @Autowired
    @Qualifier(value = "paisService")
    private PaisService paisService;
    
    
    @Autowired
    @Qualifier(value = "regionService")
    private RegionService regionService;
    
    
    /*Constructor*/
    public PaisMB(){
        
    }
    
    
    /*Post Constructor*/
    @PostConstruct
    public void init(){
        cargarPais();
    }
    
    /** 
     * Metodo que crea instancias de 'Pais' y 'Region' y almacena
     * en una Lista todas las instancias de 'Region' que se encuentra en la
     * tabla respectiva
     */
    
    public void cargarPais(){
        pais = new Pais();
        region = new Region();
        listRegion = regionService.findAll();
        listPais = paisService.getAllByIdDesc();
        actualizar = false;
    }
    
    
    /** 
     * Metodo para guardar una instancia de 'Pais' en la tabla 
     * correspondiente de la base de datos
     */
    public void guardarPais(){
        String msg ="Pais Almacenado Exitosamente!";
        try{
           pais.setIdPais(Integer.MIN_VALUE);
           pais.setIdRegion(regionService.findById(region.getIdRegion()));
           paisService.save(pais);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado!!", msg));
           
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Guardar Pais!");
            e.printStackTrace();
        }
        cargarPais();
    }
    
    public List<Pais> listaPaises() {
        listPais = paisService.getAllByNameAsc();
        Pais paisNew1=new Pais();
        List<Pais> copy = new ArrayList<Pais>();
        for (Pais paisNew : listPais) {
            if(!paisNew.getNombrePais().equalsIgnoreCase("Agregar Nuevo"))
            {
                copy.add(paisNew);
            }else{
                paisNew1=paisNew;
            }
        }
        copy.add(paisNew1);
        listPais.clear();
        return listPais=copy;
    }
    /**
     * Metodo que se ocupa de precargar la instancia de 'Pais' a ser actualizada
     * @param pais
     */
    public void preActualizarPais(Pais pais){
       try{ 
        actualizar = true;
        this.pais = pais;
        this.region.setIdRegion(pais.getIdRegion().getIdRegion());
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
    
    /**
     * Metodo para actualizar las propiedades de la instancia de 'Pais'
     * seleccionada
     */
    public void actualizarPais(){
        String msg ="Pais Actualizado Exitosamente!";
        try{
            pais.setIdRegion(regionService.findById(region.getIdRegion()));
            //actualizando la instancia
            paisService.merge(pais);
            actualizar = false;
            cancelarPais(); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!", msg));
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Actualizar Pais");
            e.printStackTrace();
        }
        cargarPais(); 
    }
    
    
    /**
     * Metodo  de Pre- borrado, que obtiene una instancia de la Entity a Borrar
     * y despliega un 'p:dialog' donde se solicita la confirmacion de la 
     * operacion de borrado
     * @param pais
     */
    public void preBorradoPais(Pais pais){
        //guardando en 'pais' la instancia de 'Pais' que se recibe como argumento
        this.pais = pais;
        RequestContext context = RequestContext.getCurrentInstance();
        //Desplegando el 'Dialog'
        context.execute("PF('confirmDeletePaisDlg').show();");
    }
    
    /**
     * Metodo que borra una instancia de 'Pais' de la Base de datos
     */
    public void borrarPais(){ 
        String msg ="Pais Eliminado Exitosamente!";
        try{
            //Borrando la instancia de pais
            paisService.delete(pais);
            cargarPais();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmDeletePaisDlg').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminado!!", msg));
        }catch(Exception e){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Pais!"));
            e.printStackTrace();
        }finally{
            actualizar = false;
        }
        
        
    }
    
    
    /**
     * Metodo que se encarga de limpiar el formulario de creacion y 
     * actualizacion de Pais
     */
    public void cancelarPais(){
        String msg ="Pais cancelado";
        try{
        pais = null;
        pais = new Pais();
        region=null;
        region = new Region();
        RequestContext.getCurrentInstance().reset(":formPais");
         if(actualizar)
        JsfUtil.addSuccessMessage(msg);
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
      cargarPais();
    }
            
    
    
  
    
    /*Getters y Setters*/

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    
    public List<Region> getListRegion() {
        return listRegion;
    }

    public void setListRegion(List<Region> listRegion) {
        this.listRegion = listRegion;
    }

    public List<Pais> getListPais() {
        return listPais;
    }

    public void setListPais(List<Pais> listPais) {
        this.listPais = listPais;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }
    
   
    
}
