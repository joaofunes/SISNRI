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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
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
@Named(value = "paisMB")
@RequestScoped
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
        listPais = paisService.findAll();
    }
    
    
    /** 
     * Metodo para guardar una instancia de 'Pais' en la tabla 
     * correspondiente de la base de datos
     */
    public void guardarPais(){
        try{
           pais.setIdPais(Integer.MIN_VALUE);
           pais.setIdRegion(regionService.findById(region.getIdRegion()));
           paisService.save(pais);
           cargarPais();
        }catch(Exception e){
            System.out.println( e.getMessage());
        }
    }
    
    /**
     * Metodo que se ocupa de precargar la instancia de 'Pais' a ser actualizada
     * @param pais
     */
    public void preActualizarPais(Pais pais){
       try{ 
        actualizar = true;
        this.pais = pais;
        this.region.setIdRegion(getPais().getIdRegion().getIdRegion());
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
    
    /**
     * Metodo para actualizar las propiedades de la instancia de 'Pais'
     * seleccionada
     */
    public void actualizarPais(){
        try{
            //actualizando la instancia
            paisService.merge(pais);
            actualizar = false;
            cancelarPais();
            cargarPais();
                
        }catch(Exception e){
            
            System.out.println(e.getMessage());
        }
        
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
     * Metodo que borra una instancia de 'Pais' de la Base de datos (PENDIENTE)
     */
    public void borrarPais(){
        try{
            paisService.delete(pais);
          
        }catch(Exception e){
            System.out.println( e.getMessage());
        }
         cargarPais();
    }
    
    
    /**
     * Metodo que se encarga de limpiar el formulario de creacion y 
     * actualizacion de Pais
     */
    public void cancelarPais(){
        pais = null;
        pais = new Pais();
        
        RequestContext.getCurrentInstance().reset("form:formPais");
      
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
    
   
    
}
