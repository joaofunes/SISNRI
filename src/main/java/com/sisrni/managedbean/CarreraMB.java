/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;


import com.sisrni.model.Carrera;
import com.sisrni.model.Facultad;
import com.sisrni.model.Organismo;
import com.sisrni.model.Pais;
import com.sisrni.service.CarreraService;
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
@Named(value = "carreraMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class CarreraMB{
      /*Para Errores*/
    private final static Log log = LogFactory.getLog(CarreraMB.class);

    
    /*Variables */
    private Carrera carrera;
    private Facultad facultad;
    private List<Facultad> listFacultad;
    private List<Carrera> listCarrera;
    private List<Organismo> organismosList;
    private List<Pais> paisesList;
    private Organismo organismoSelected;
    private Pais paisSelected;
    private boolean actualizar;
    
    
    
    @Autowired
    @Qualifier(value = "carreraService")
    private CarreraService carreraService;
    
    
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
    public CarreraMB(){
        
    }
    
    
    /*Post Constructor*/
    @PostConstruct
    public void init(){
        cargarCarrera();
    }
    
    /** 
     * Metodo que crea instancias de 'Carrera' y 'Facultad' y almacena
     * en una Lista todas las instancias de 'Facultad' que se encuentra en la
     * tabla respectiva
     */
    
    public void cargarCarrera(){
        carrera = new Carrera();
        facultad = new Facultad();
        organismoSelected = new Organismo();
        paisSelected = new Pais();
        paisesList = paisService.getAllByIdDesc();
        organismosList = organismoService.getAllByIdDesc();
        listFacultad = facultadService.getAllByIdDesc();
        listCarrera = carreraService.getAllByIdDesc();
        actualizar = false;
    }
    
    
    /** 
     * Metodo para guardar una instancia de 'Carrera' en la tabla 
     * correspondiente de la base de datos
     */
    public void guardarCarrera(){
        String msg ="Carrera Almacenada Exitosamente!";
        try{
           carrera.setIdCarrera(Integer.MIN_VALUE);
           carrera.setIdFacultad(facultadService.findById(facultad.getIdFacultad()));
           carreraService.save(carrera);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado!!", msg));
           
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Guardar Carrera!");
            e.printStackTrace();
        }
        cargarCarrera();
    }
    
    /**
     * Metodo que se ocupa de precargar la instancia de 'Carrera' a ser actualizada
     * @param carrera
     */
    public void preActualizarCarrera(Carrera carrera){
       try{ 
        actualizar = true;
        this.carrera = carrera;
        this.paisSelected.setIdPais(carrera.getIdFacultad().getIdOrganismo().getIdPais());
        this.organismoSelected.setIdOrganismo(carrera.getIdFacultad().getIdOrganismo().getIdOrganismo());
        this.facultad.setIdFacultad(carrera.getIdFacultad().getIdFacultad());
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
    
    /**
     * Metodo para actualizar las propiedades de la instancia de 'Carrera'
     * seleccionada
     */
    public void actualizarCarrera(){
        String msg ="Carrera Actualizada Exitosamente!";
        try{
            carrera.setIdFacultad(facultadService.findById(facultad.getIdFacultad()));
            //actualizando la instancia
            carreraService.merge(carrera);
            actualizar = false;
            cancelarCarrera(); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!", msg));
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Actualizar Carrera");
            e.printStackTrace();
        }
        cargarCarrera(); 
    }
    
     public void onchangePais() {
        try {
            if (paisSelected.getIdPais()!= null && !paisSelected.getIdPais().equals("")) {
               organismosList = organismoService.getOrganismosPorPaisYTipo(paisSelected.getIdPais(),1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     public void onchangeOrganismo() {
        try {
            if (organismoSelected.getIdOrganismo()!= null && !organismoSelected.getIdOrganismo().equals("")) {
               listFacultad = facultadService.getFacultadesByUniversidad(organismoSelected.getIdOrganismo());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Metodo  de Pre- borrado, que obtiene una instancia de la Entity a Borrar
     * y despliega un 'p:dialog' donde se solicita la confirmacion de la 
     * operacion de borrado
     * @param carrera
     */
    public void preBorradoCarrera(Carrera carrera){
        //guardando en 'carrera' la instancia de 'Carrera' que se recibe como argumento
        this.carrera = carrera;
        RequestContext context = RequestContext.getCurrentInstance();
        //Desplegando el 'Dialog'
        context.execute("PF('confirmDeleteCarreraDlg').show();");
    }
    
    /**
     * Metodo que borra una instancia de 'Carrera' de la Base de datos
     */
    public void borrarCarrera(){ 
        String msg ="Carrera Eliminada Exitosamente!";
        try{
            //Borrando la instancia de carrera
            carreraService.delete(carrera);
            cargarCarrera();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmDeleteCarreraDlg').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminado!!", msg));
        }catch(Exception e){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Carrera!"));
            e.printStackTrace();
        }finally{
            actualizar = false;
        }
        
        
    }
    
    
    /**
     * Metodo que se encarga de limpiar el formulario de creacion y 
     * actualizacion de Carrera
     */
    public void cancelarCarrera(){
        String msg ="Carrera cancelada";
        try{
        carrera = null;
        carrera = new Carrera();
        facultad=null;
        facultad = new Facultad();
        RequestContext.getCurrentInstance().reset(":formCarrera");
        if(actualizar)
        JsfUtil.addSuccessMessage(msg); 
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
      cargarCarrera();
    }
            
    
    
  
    
    /*Getters y Setters*/
       public List<Organismo> getOrganismosList() {
        return organismosList;
    }

    public void setOrganismosList(List<Organismo> organismosList) {
        this.organismosList = organismosList;
    }
    
    public Organismo getOrganismoSelected() {
        return organismoSelected;
    }

    public void setOrganismoSelected(Organismo organismoSelected) {
        this.organismoSelected = organismoSelected;
    }
    
    
    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    
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

    
    public List<Facultad> getListFacultad() {
        return listFacultad;
    }

    public void setListFacultad(List<Facultad> listFacultad) {
        this.listFacultad = listFacultad;
    }

    public List<Carrera> getListCarrera() {
        return listCarrera;
    }

    public void setListCarrera(List<Carrera> listCarrera) {
        this.listCarrera = listCarrera;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }
    
   
    
}
