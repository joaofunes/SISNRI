/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;


import com.sisrni.model.Facultad;
import com.sisrni.model.Organismo;
import com.sisrni.model.Pais;
import com.sisrni.service.FacultadService;
import com.sisrni.service.OrganismoService;
import com.sisrni.service.PaisService;
import com.sisrni.utils.JsfUtil;
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
@Named(value = "facultadMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class FacultadMB{
      /*Para Errores*/
    private final static Log log = LogFactory.getLog(FacultadMB.class);

    
    /*Variables */
    private Facultad facultad;
    private List<Pais> paisesList;
    private Pais paisSelected;
    private Organismo organismo;
    private List<Organismo> listOrganismo;
    private List<Facultad> listFacultad;
    private boolean actualizar;
    
    
    
    @Autowired
    @Qualifier(value = "facultadService")
    private FacultadService facultadService;
    
    
    @Autowired
    @Qualifier(value = "organismoService")
    private OrganismoService organismoService;
    
    @Autowired
    @Qualifier(value = "paisService")
    private PaisService paisService;
    
    /*Constructor*/
    public FacultadMB(){
        
    }
    
    
    /*Post Constructor*/
    @PostConstruct
    public void init(){
        cargarFacultad();
    }
    
    /** 
     * Metodo que crea instancias de 'Facultad' y 'Organismo' y almacena
     * en una Lista todas las instancias de 'Organismo' que se encuentra en la
     * tabla respectiva
     */
    
    public void cargarFacultad(){
        facultad = new Facultad();
         paisSelected = new Pais();
         organismo = new Organismo();
        paisesList = paisService.getAllByIdDesc();
        listOrganismo = organismoService.getAllByIdDesc();
        listFacultad = facultadService.getAllByIdDesc();
        actualizar = false;
    }
    
    
    /** 
     * Metodo para guardar una instancia de 'Facultad' en la tabla 
     * correspondiente de la base de datos
     */
    public void guardarFacultad(){
        String msg ="Facultad Almacenada Exitosamente!";
        try{
           facultad.setIdFacultad(Integer.MIN_VALUE);
           facultad.setIdOrganismo(organismoService.findById(organismo.getIdOrganismo()));
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
        this.paisSelected.setIdPais(facultad.getIdOrganismo().getIdPais());
        this.organismo.setIdOrganismo(facultad.getIdOrganismo().getIdOrganismo());
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
    
    /**
     * Metodo para actualizar las propiedades de la instancia de 'Facultad'
     * seleccionada
     */
    public void actualizarFacultad(){
        String msg ="Facultad Actualizada Exitosamente!";
        try{
            facultad.setIdOrganismo(organismoService.findById(organismo.getIdOrganismo()));
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
    
    public void onchangePais() {
        try {
            if (paisSelected.getIdPais()!= null && !paisSelected.getIdPais().equals("")) {
               listOrganismo  = organismoService.getOrganismosPorPaisYTipo(paisSelected.getIdPais(),1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        String msg ="Facultad Eliminada Exitosamente!";
        try{
            //Borrando la instancia de facultad
            facultadService.delete(facultad);
            cargarFacultad();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmDeleteFacultadDlg').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminado!!", msg));
        }catch(Exception e){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Facultad!"));
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
        String msg ="Facultad cancelada";
        try{
        facultad = null;
        facultad = new Facultad();
        organismo=null;
        organismo = new Organismo();
        RequestContext.getCurrentInstance().reset(":formFacultad");
        if(actualizar)
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

    
    public List<Pais> getPaisesList() {
        return paisesList;
    }

    public void setPaisesList(List<Pais> paisesList) {
        this.paisesList = paisesList;
    }

    public Pais getPaisSelected() {
        return paisSelected;
    }

    public void setPaisSelected(Pais paisSelected) {
        this.paisSelected = paisSelected;
    }
    public Organismo getOrganismo() {
        return organismo;
    }

    public void setOrganismo(Organismo organismo) {
        this.organismo = organismo;
    }

    
    public List<Organismo> getListOrganismo() {
        return listOrganismo;
    }

    public void setListOrganismo(List<Organismo> listOrganismo) {
        this.listOrganismo = listOrganismo;
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
