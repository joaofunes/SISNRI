/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.AreaConocimiento;
import com.sisrni.model.Facultad;
import com.sisrni.model.Persona;
import com.sisrni.model.PropuestaConvenio;
import com.sisrni.model.Proyecto;
import com.sisrni.model.ProyectoGenerico;
import com.sisrni.model.Telefono;
import com.sisrni.model.TipoProyecto;
import com.sisrni.model.Unidad;
import com.sisrni.service.AreaConocimientoService;
import com.sisrni.service.FacultadService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.PropuestaConvenioService;
import com.sisrni.service.ProyectoGenericoService;
import com.sisrni.service.ProyectoService;
import com.sisrni.service.TelefonoService;
import com.sisrni.service.TipoProyectoService;
import com.sisrni.service.UnidadService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Lillian
 */
@Named(value = "proyectosMB")
@Scope(WebApplicationContext.SCOPE_REQUEST)

public class ProyectosMB {
    

@Autowired
    private AreaConocimientoService areaConocimientoService;
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private PersonaService personaService;
    @Autowired
    private TipoProyectoService tipoProyectoService;
    @Autowired
    private FacultadService facultadService;
    @Autowired
    private UnidadService unidadService;
    @Autowired
    private TelefonoService telefonoService;    
    @Autowired
    private ProyectoGenericoService proyectoGenericoService;
    @Autowired
    private PropuestaConvenioService propuestaConvenioService;
    
private TipoProyecto proyectoSelected;
private List<TipoProyecto> tipoproyectolist;
private String [] areaConocimientoSelected;
private List<AreaConocimiento> areaConocimientoList;
private Facultad facultadSelected;
private List<Facultad> facultadList;
private Unidad unidadSelected;
private List<Unidad> unidadList;
private Proyecto proyecto;
private ProyectoGenerico proyectoGenerico;
private PropuestaConvenio propuestaConvenio;
private List<PropuestaConvenio> propuestaConvenioList;
private PropuestaConvenio propuestaConvenioSelected;
private Persona persona;
private Persona personaAsistente;
private Persona personaExterna;
private Facultad facultad;
private Facultad facultadSelectedSol;
private Unidad unidadProyecto;
private Unidad unidadSelectedSol;
private Facultad facultadAsistente;
private Facultad facultadExterna;
private Telefono telefono;
private Telefono telefonofax;
private Telefono telefonorefint ;
private Telefono telefonorefintfax;
private Telefono telefonorefext;
private Telefono telefonorefextfax;
    
    
    /**
     * Creates a new instance of ProyectosMB
     */
    @PostConstruct
    public void init(){
        inicializador();
    }
    
    public void inicializador(){
    proyectoSelected = new TipoProyecto();
    tipoproyectolist = tipoProyectoService.findAll();
    areaConocimientoList=areaConocimientoService.findAll();
    facultadSelected=new Facultad();
    facultadList=new ArrayList<Facultad>();
    facultadList=facultadService.findAll();
    unidadSelected=new Unidad();
    unidadList=unidadService.findAll();
    proyecto = new Proyecto();
    proyectoGenerico=new ProyectoGenerico();
    propuestaConvenio=new PropuestaConvenio();
    propuestaConvenioSelected=new PropuestaConvenio();
    propuestaConvenioList=propuestaConvenioService.findAll();
    persona = new Persona();
    facultad = new Facultad();
    facultadSelectedSol=new Facultad();
    unidadSelectedSol=new Unidad();
     facultadAsistente = new Facultad();
     facultadExterna = new Facultad();
     telefono = new Telefono();
     telefonofax = new Telefono();
     telefonorefint=new Telefono();
     telefonorefintfax=new Telefono();
     telefonorefext=new Telefono();
     telefonorefextfax=new Telefono();
     personaAsistente=new Persona();
     personaExterna=new Persona();
    
    }
    
    
    public void guardarProyecto(){
    try{
        proyecto.setIdTipoProyecto(tipoProyectoService.findById(proyectoSelected.getIdTipoProyecto()));
        proyecto.setIdFacultad(facultadSelected.getIdFacultad());
        proyecto.setIdUnidad(unidadSelected.getIdUnidad());
        PropuestaConvenio propuesta=propuestaConvenioService.findById(propuestaConvenioSelected.getIdPropuesta());
        proyecto.setIdPropuestaConvenio(propuesta);
        //guardar proyecto
        proyecto.setIdProyecto(0);
        proyectoService.save(proyecto);
        
        //guardar proyecto genérico
        proyectoGenerico.setIdProyecto(proyecto.getIdProyecto());
        proyectoGenericoService.save(proyectoGenerico);
        
        //guardar persona solicitante
        
    }catch(Exception e){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La Informacion no ha sido registrada."));
    }
    }
    public void onFacultadChange() {
        if( facultadSelectedSol.getIdFacultad()!=null && !facultadSelectedSol.getIdFacultad().equals(""))
            unidadList = unidadService.getUnidadesByFacultadId(facultadSelectedSol.getIdFacultad());
        else
            unidadList= new ArrayList<Unidad>();
    }
    public TipoProyecto getProyectoSelected() {
        return proyectoSelected;
    }

    public void setProyectoSelected(TipoProyecto proyectoSelected) {
        this.proyectoSelected = proyectoSelected;
    }

    public List<TipoProyecto> getTipoproyectolist() {
        return tipoproyectolist;
    }

    public void setTipoproyectolist(List<TipoProyecto> tipoproyectolist) {
        this.tipoproyectolist = tipoproyectolist;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Unidad getUnidadSelectedSol() {
        return unidadSelectedSol;
    }

    public void setUnidadSelectedSol(Unidad unidadSelectedSol) {
        this.unidadSelectedSol = unidadSelectedSol;
    }
    
    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public Telefono getTelefonofax() {
        return telefonofax;
    }

    public void setTelefonofax(Telefono telefonofax) {
        this.telefonofax = telefonofax;
    }

    public Telefono getTelefonorefint() {
        return telefonorefint;
    }

    public void setTelefonorefint(Telefono telefonorefint) {
        this.telefonorefint = telefonorefint;
    }

    public Telefono getTelefonorefintfax() {
        return telefonorefintfax;
    }

    public void setTelefonorefintfax(Telefono telefonorefintfax) {
        this.telefonorefintfax = telefonorefintfax;
    }

    public Telefono getTelefonorefext() {
        return telefonorefext;
    }

    public void setTelefonorefext(Telefono telefonorefext) {
        this.telefonorefext = telefonorefext;
    }

    public Telefono getTelefonorefextfax() {
        return telefonorefextfax;
    }

    public void setTelefonorefextfax(Telefono telefonorefextfax) {
        this.telefonorefextfax = telefonorefextfax;
    }

    public Persona getPersonaAsistente() {
        return personaAsistente;
    }

    public void setPersonaAsistente(Persona personaAsistente) {
        this.personaAsistente = personaAsistente;
    }

    public Persona getPersonaExterna() {
        return personaExterna;
    }

    public void setPersonaExterna(Persona personaExterna) {
        this.personaExterna = personaExterna;
    }

    public Facultad getFacultadAsistente() {
        return facultadAsistente;
    }

    public void setFacultadAsistente(Facultad facultadAsistente) {
        this.facultadAsistente = facultadAsistente;
    }

    public Facultad getFacultadExterna() {
        return facultadExterna;
    }

    public void setFacultadExterna(Facultad facultadExterna) {
        this.facultadExterna = facultadExterna;
    }

    public String[] getAreaConocimientoSelected() {
        return areaConocimientoSelected;
    }

    public void setAreaConocimientoSelected(String[] areaConocimientoSelected) {
        this.areaConocimientoSelected = areaConocimientoSelected;
    }

    public List<AreaConocimiento> getAreaConocimientoList() {
        return areaConocimientoList;
    }

    public void setAreaConocimientoList(List<AreaConocimiento> areaConocimientoList) {
        this.areaConocimientoList = areaConocimientoList;
    }

    public Facultad getFacultadSelected() {
        return facultadSelected;
    }

    public void setFacultadSelected(Facultad facultadSelected) {
        this.facultadSelected = facultadSelected;
    }

    public List<Facultad> getFacultadList() {
        return facultadList;
    }

    public void setFacultadList(List<Facultad> facultadList) {
        this.facultadList = facultadList;
    }

    public Facultad getFacultadSelectedSol() {
        return facultadSelectedSol;
    }

    public void setFacultadSelectedSol(Facultad facultadSelectedSol) {
        this.facultadSelectedSol = facultadSelectedSol;
    }

    public Unidad getUnidadProyecto() {
        return unidadProyecto;
    }

    public void setUnidadProyecto(Unidad unidadProyecto) {
        this.unidadProyecto = unidadProyecto;
    }

    public Unidad getUnidadSelected() {
        return unidadSelected;
    }

    public void setUnidadSelected(Unidad unidadSelected) {
        this.unidadSelected = unidadSelected;
    }

    public List<Unidad> getUnidadList() {
        return unidadList;
    }

    public void setUnidadList(List<Unidad> unidadList) {
        this.unidadList = unidadList;
    }

    public ProyectoGenerico getProyectoGenerico() {
        return proyectoGenerico;
    }

    public void setProyectoGenerico(ProyectoGenerico proyectoGenerico) {
        this.proyectoGenerico = proyectoGenerico;
    }

    public PropuestaConvenio getPropuestaConvenio() {
        return propuestaConvenio;
    }

    public void setPropuestaConvenio(PropuestaConvenio propuestaConvenio) {
        this.propuestaConvenio = propuestaConvenio;
    }

    public List<PropuestaConvenio> getPropuestaConvenioList() {
        return propuestaConvenioList;
    }

    public void setPropuestaConvenioList(List<PropuestaConvenio> propuestaConvenioList) {
        this.propuestaConvenioList = propuestaConvenioList;
    }

    public PropuestaConvenio getPropuestaConvenioSelected() {
        return propuestaConvenioSelected;
    }

    public void setPropuestaConvenioSelected(PropuestaConvenio propuestaConvenioSelected) {
        this.propuestaConvenioSelected = propuestaConvenioSelected;
    }

   
    
}
