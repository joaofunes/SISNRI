/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;


import com.sisrni.model.EscuelaDepartamento;
import com.sisrni.model.Facultad;
import com.sisrni.model.Organismo;
import com.sisrni.model.Pais;
import com.sisrni.service.EscuelaDepartamentoService;
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
@Named(value = "escuelaDepartamentoMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class EscuelaDepartamentoMB{
      /*Para Errores*/
    private final static Log log = LogFactory.getLog(EscuelaDepartamentoMB.class);

    
    /*Variables */
    private EscuelaDepartamento escuelaDepartamento;
    private Facultad facultad;
    private List<Facultad> listFacultad;
    private List<Organismo> organismosList;
    private List<Pais> paisesList;
    private Organismo organismoSelected;
    private Pais paisSelected;
    private List<EscuelaDepartamento> listEscuelaDepartamento;
    private boolean actualizar;
    
    
    
    @Autowired
    @Qualifier(value = "escuelaDepartamentoService")
    private EscuelaDepartamentoService escuelaDepartamentoService;

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
    public EscuelaDepartamentoMB(){
        
    }
    
    
    /*Post Constructor*/
    @PostConstruct
    public void init(){
        cargarEscuelaDepartamento();
    }
    
    /** 
     * Metodo que crea instancias de 'EscuelaDepartamento' y 'Facultad' y almacena
     * en una Lista todas las instancias de 'Facultad' que se encuentra en la
     * tabla respectiva
     */
    
    public void cargarEscuelaDepartamento(){
        escuelaDepartamento = new EscuelaDepartamento();
        facultad = new Facultad();
        organismoSelected = new Organismo();
        paisSelected = new Pais();
        paisesList = paisService.getAllByIdDesc();
        organismosList = organismoService.getAllByIdDesc();
        listFacultad = facultadService.getAllByIdDesc();
        listEscuelaDepartamento = escuelaDepartamentoService.getAllByIdDesc();
        actualizar = false;
    }
        
    
    /** 
     * Metodo para guardar una instancia de 'EscuelaDepartamento' en la tabla 
     * correspondiente de la base de datos
     */
    public void guardarEscuelaDepartamento(){
        String msg ="Registro Almacenado Exitosamente!";
        try{
           escuelaDepartamento.setIdEscuelaDepto(Integer.MIN_VALUE);
           escuelaDepartamento.setIdFacultad(facultadService.findById(facultad.getIdFacultad()));
           escuelaDepartamentoService.save(escuelaDepartamento);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado!!", msg));
           
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Guardar el Registro!");
            e.printStackTrace();
        }
        cargarEscuelaDepartamento();
    }
    
    /**
     * Metodo que se ocupa de precargar la instancia de 'EscuelaDepartamento' a ser actualizada
     * @param escuelaDepartamento
     */
    public void preActualizarEscuelaDepartamento(EscuelaDepartamento escuelaDepartamento){
       try{ 
        actualizar = true;
        this.escuelaDepartamento = escuelaDepartamento;
        this.paisSelected.setIdPais(escuelaDepartamento.getIdFacultad().getIdOrganismo().getIdPais());
        this.organismoSelected.setIdOrganismo(escuelaDepartamento.getIdFacultad().getIdOrganismo().getIdOrganismo());
        this.facultad.setIdFacultad(escuelaDepartamento.getIdFacultad().getIdFacultad());
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
    
    /**
     * Metodo para actualizar las propiedades de la instancia de 'EscuelaDepartamento'
     * seleccionada
     */
    public void actualizarEscuelaDepartamento(){
        String msg ="Escuela o Departamento Actualizado Exitosamente!";
        try{
            escuelaDepartamento.setIdFacultad(facultadService.findById(facultad.getIdFacultad()));
            //actualizando la instancia
            escuelaDepartamentoService.merge(escuelaDepartamento);
            actualizar = false;
            cancelarEscuelaDepartamento(); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!", msg));
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Actualizar Escuela o Departamento");
            e.printStackTrace();
        }
        cargarEscuelaDepartamento(); 
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
     * @param escuelaDepartamento
     */
    public void preBorradoEscuelaDepartamento(EscuelaDepartamento escuelaDepartamento){
        //guardando en 'escuelaDepartamento' la instancia de 'EscuelaDepartamento' que se recibe como argumento
        this.escuelaDepartamento = escuelaDepartamento;
        RequestContext context = RequestContext.getCurrentInstance();
        //Desplegando el 'Dialog'
        context.execute("PF('confirmDeleteEscuelaDepartamentoDlg').show();");
    }
    
    /**
     * Metodo que borra una instancia de 'EscuelaDepartamento' de la Base de datos
     */
    public void borrarEscuelaDepartamento(){ 
        String msg ="Escuela o Departamento Eliminado Exitosamente!";
        try{
            //Borrando la instancia de escuelaDepartamento
            escuelaDepartamentoService.delete(escuelaDepartamento);
            cargarEscuelaDepartamento();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmDeleteEscuelaDepartamentoDlg').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminado!!", msg));
        }catch(Exception e){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Escuela o Departamento!"));
            e.printStackTrace();
        }finally{
            actualizar = false;
        }
        
        
    }
    
    
    /**
     * Metodo que se encarga de limpiar el formulario de creacion y 
     * actualizacion de EscuelaDepartamento
     */
    public void cancelarEscuelaDepartamento(){
        String msg ="Escuela o Departamento cancelado";
        try{
        escuelaDepartamento = null;
        escuelaDepartamento = new EscuelaDepartamento();
        facultad=null;
        facultad = new Facultad();
        RequestContext.getCurrentInstance().reset(":formEscuelaDepartamento");
         if(actualizar)
        JsfUtil.addSuccessMessage(msg);
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
      cargarEscuelaDepartamento();
    }
            
    
    
  
    
    /*Getters y Setters*/

    public EscuelaDepartamento getEscuelaDepartamento() {
        return escuelaDepartamento;
    }

    public void setEscuelaDepartamento(EscuelaDepartamento escuelaDepartamento) {
        this.escuelaDepartamento = escuelaDepartamento;
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
    
    public List<Organismo> getOrganismosList() {
        return organismosList;
    }

    public void setOrganismosList(List<Organismo> organismosList) {
        this.organismosList = organismosList;
    }

    public List<Pais> getPaisesList() {
        return paisesList;
    }

    public void setPaisesList(List<Pais> paisesList) {
        this.paisesList = paisesList;
    }

    public Organismo getOrganismoSelected() {
        return organismoSelected;
    }

    public void setOrganismoSelected(Organismo organismoSelected) {
        this.organismoSelected = organismoSelected;
    }

    public Pais getPaisSelected() {
        return paisSelected;
    }

    public void setPaisSelected(Pais paisSelected) {
        this.paisSelected = paisSelected;
    }

    public List<EscuelaDepartamento> getListEscuelaDepartamento() {
        return listEscuelaDepartamento;
    }

    public void setListEscuelaDepartamento(List<EscuelaDepartamento> listEscuelaDepartamento) {
        this.listEscuelaDepartamento = listEscuelaDepartamento;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }
    
   
    
}
