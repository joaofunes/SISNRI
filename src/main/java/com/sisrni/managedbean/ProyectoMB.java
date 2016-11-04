/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.AreaConocimiento;
import com.sisrni.model.Facultad;
import com.sisrni.model.Organismo;
import com.sisrni.model.Persona;
import com.sisrni.model.Telefono;
import com.sisrni.service.AreaConocimientoService;
import com.sisrni.service.FacultadService;
import com.sisrni.service.OrganismoService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.TelefonoService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Usuario
 */
@Named(value = "proyectoMB")
@ViewScoped
public class ProyectoMB {
    
    private static final String FIJO ="FIJO";  
    private static final String FAX ="FAX";
    private String [] areaConocimientoSelected;
    
   /*Variables*/
    private List<Proyectos> listProyectos;
    private List<Facultad> listFacultad;
    private List<SelectItem> listFacultadItem;
    
    private List<Organismo> listOrganismos;
    private List<SelectItem> listOrganismosItem;
    
    private List<Persona> listInterno;
    private List<Telefono> listTelefonoInterno;
    
    private List<Persona> listAsistenteInterno;
    private List<Telefono> listTelefonoAsistenteInterno;
    
    private List<AreaConocimiento> listAreaConocimiento;
    private List<SelectItem> listAreaConocimientoItem;
    
    private Persona persona;
    private Persona personaCoordinador;
    private Persona personaAsistente;
    private Persona personaExterno;
    
    private Telefono telFijoInterno;
    private Telefono faxInterno;
    
    private Telefono telFijoAsistente;
    private Telefono faxAsistente;
    
    private Telefono telFijoExterno;
    private Telefono faxExterno;
    
    
    @Autowired
    @Qualifier(value = "facultadService")
    private FacultadService facultadService;
    
    @Autowired
    @Qualifier(value = "organismoService")
    private OrganismoService organismoService;
    
    @Autowired
    @Qualifier(value = "personaService")
    private PersonaService personaService;
    
    @Autowired
    @Qualifier(value ="telefonoService")
    private TelefonoService telefonoService;
    
    @Autowired
    @Qualifier(value ="areaConocimientoService")
    private AreaConocimientoService areaConocimientoService;
    

    /**
     * constructor
     */
    public ProyectoMB() {
    }
    
    /**
     * Post Constructor
     */
    
    @PostConstruct
    public void init(){
      personaCoordinador = new Persona();  
      personaAsistente = new Persona();
      personaExterno = new Persona();
      
      listFacultad = facultadService.findAll();
      listOrganismos = organismoService.findAll();
      listInterno = personaService.findAll();
      listAsistenteInterno =personaService.findAll();
      listAreaConocimiento = areaConocimientoService.findAll();
      
      telFijoInterno = new Telefono();
      faxInterno = new Telefono();
      telFijoAsistente = new Telefono();
      faxAsistente = new Telefono();
    }
    
    
    public void onchangeCoordinador(){
        try{
            listTelefonoInterno = telefonoService.getTelefonosByPersona(personaCoordinador);
            
            for(Telefono tlfx : listTelefonoInterno){
                if(tlfx.getIdTipoTelefono().getNombre().equals(FIJO)){
                    telFijoInterno =tlfx;
                }
                if(tlfx.getIdTipoTelefono().getNombre().equals(FAX)){
                    faxInterno = tlfx;
                }
            }
        }catch(Exception e){
            
        }
    }
    
     public void onchangeAsistenteCoordinador(){
        try{
            listTelefonoAsistenteInterno = telefonoService.getTelefonosByPersona(personaAsistente);
            
            for(Telefono tlfx : listTelefonoAsistenteInterno){
                if(tlfx.getIdTipoTelefono().getNombre().equals(FIJO)){
                    telFijoAsistente =tlfx;
                }
                if(tlfx.getIdTipoTelefono().getNombre().equals(FAX)){
                    faxAsistente = tlfx;
                }
            }
        }catch(Exception e){
            
        }
    }
    
    
     
      public void preActualizarProyecto(){
      
    }
    
   
    public void actualizarProyecto(){
        
    }
     
     
    public List<Proyectos> getListProyectos() {
        return listProyectos;
    }

    public void setListProyectos(List<Proyectos> listProyectos) {
        this.listProyectos = listProyectos;
    }

    public List<Facultad> getListFacultad() {
        return listFacultad;
    }

    public void setListFacultad(List<Facultad> listFacultad) {
        this.listFacultad = listFacultad;
    }

    public List<SelectItem> getListFacultadItem() {
        this.listFacultadItem = new ArrayList<SelectItem>();
        listFacultadItem.clear();
        
        for(Facultad f :listFacultad ){
         SelectItem facultadItem = new SelectItem(f.getIdFacultad(),f.getNombreFacultad());
         this.listFacultadItem.add(facultadItem);
    }
        return listFacultadItem;
    }

    public void setListFacultadItem(List<SelectItem> listFacultadItem) {
        this.listFacultadItem = listFacultadItem;
    }

    public List<Organismo> getListOrganismos() {
        return listOrganismos;
    }

    public void setListOrganismos(List<Organismo> listOrganismos) {
        this.listOrganismos = listOrganismos;
    }

    public List<SelectItem> getListOrganismosItem() {
         this.listOrganismosItem = new ArrayList<SelectItem>();
         listOrganismosItem.clear();
        
        for(Organismo o :listOrganismos ){
          SelectItem organismoItem = new SelectItem(o.getIdOrganismo(),o.getNombreOrganismo());
          this.listOrganismosItem.add(organismoItem);
    }
        return listOrganismosItem;
    }

    public void setListOrganismosItem(List<SelectItem> listOrganismosItem) {
        this.listOrganismosItem = listOrganismosItem;
    }

    
    
    public List<Persona> getListInterno() {
        return listInterno;
    }

    public void setListInterno(List<Persona> listInterno) {
        this.listInterno = listInterno;
    }

 
    public List<Persona> getListAsistenteInterno() {
        return listAsistenteInterno;
    }

    public void setListAsistenteInterno(List<Persona> listAsistenteInterno) {
        this.listAsistenteInterno = listAsistenteInterno;
    }

    public Persona getPersonaCoordinador() {
        
        return personaCoordinador;
    }

    public void setPersonaCoordinador(Persona personaCoordinador) {
        this.personaCoordinador = personaCoordinador;
    }

    public Persona getPersonaAsistente() {
        return personaAsistente;
    }

    public void setPersonaAsistente(Persona personaAsistente) {
        this.personaAsistente = personaAsistente;
    }
    
    
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Telefono> getListTelefonoInterno() {
        return listTelefonoInterno;
    }

    public void setListTelefonoInterno(List<Telefono> listTelefonoInterno) {
        this.listTelefonoInterno = listTelefonoInterno;
    }

    public Telefono getTelFijoInterno() {
        return telFijoInterno;
    }

    public void setTelFijoInterno(Telefono telFijoInterno) {
        this.telFijoInterno = telFijoInterno;
    }

    public Telefono getFaxInterno() {
        return faxInterno;
    }

    public void setFaxInterno(Telefono faxInterno) {
        this.faxInterno = faxInterno;
    }

    public Telefono getTelFijoAsistente() {
        return telFijoAsistente;
    }

    public void setTelFijoAsistente(Telefono telFijoAsistente) {
        this.telFijoAsistente = telFijoAsistente;
    }

    public Telefono getFaxAsistente() {
        return faxAsistente;
    }

    public void setFaxAsistente(Telefono faxAsistente) {
        this.faxAsistente = faxAsistente;
    }

    public List<AreaConocimiento> getListAreaConocimiento() {
        return listAreaConocimiento;
    }

    public void setListAreaConocimiento(List<AreaConocimiento> listAreaConocimiento) {
        this.listAreaConocimiento = listAreaConocimiento;
    }

      

    public String[] getAreaConocimientoSelected() {
        return areaConocimientoSelected;
    }

    public void setAreaConocimientoSelected(String[] areaConocimientoSelected) {
        this.areaConocimientoSelected = areaConocimientoSelected;
    }

    public Persona getPersonaExterno() {
        return personaExterno;
    }

    public void setPersonaExterno(Persona personaExterno) {
        this.personaExterno = personaExterno;
    }

    public Telefono getTelFijoExterno() {
        return telFijoExterno;
    }

    public void setTelFijoExterno(Telefono telFijoExterno) {
        this.telFijoExterno = telFijoExterno;
    }

    public Telefono getFaxExterno() {
        return faxExterno;
    }

    public void setFaxExterno(Telefono faxExterno) {
        this.faxExterno = faxExterno;
    }

    
    
    
      
    
}
