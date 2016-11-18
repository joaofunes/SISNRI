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
import com.sisrni.model.PersonaProyecto;
import com.sisrni.model.PersonaProyectoPK;
import com.sisrni.model.PropuestaConvenio;
import com.sisrni.model.Proyecto;
import com.sisrni.model.ProyectoGenerico;
import com.sisrni.model.Telefono;
import com.sisrni.model.TipoPersona;
import com.sisrni.model.TipoProyecto;
import com.sisrni.model.TipoTelefono;
import com.sisrni.model.Unidad;
import com.sisrni.service.AreaConocimientoService;
import com.sisrni.service.FacultadService;
import com.sisrni.service.OrganismoService;
import com.sisrni.service.PersonaProyectoService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.PropuestaConvenioService;
import com.sisrni.service.ProyectoGenericoService;
import com.sisrni.service.ProyectoService;
import com.sisrni.service.TelefonoService;
import com.sisrni.service.TipoPersonaService;
import com.sisrni.service.TipoProyectoService;
import com.sisrni.service.TipoTelefonoService;
import com.sisrni.service.UnidadService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Lillian
 */
@Named(value = "proyectosMB")
@Scope(WebApplicationContext.SCOPE_APPLICATION)

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
    @Autowired
    private TipoTelefonoService tipoTelefonoService;
    @Autowired
    private OrganismoService organismoService;
    @Autowired
    private TipoPersonaService tipoPersonaService;
    @Autowired
    private PersonaProyectoService personaProyectoService;
    //@Autowired
    //PENDIENTE
    

//Definicion de objetos    
private Proyecto proyecto;
private ProyectoGenerico proyectoGenerico;
private PropuestaConvenio propuestaConvenio;
private Persona persona;
private Persona personaAsistente;
private Persona personaExterna;
private Facultad facultad;
private Unidad unidadProyecto;
private Organismo organismo;
private Facultad facultadAsistente;
private Facultad facultadExterna;
private PersonaProyecto personaProyecto;
private PersonaProyecto personaProyectoAsis;
private PersonaProyecto personaProyectoExt;
private PersonaProyectoPK personaProyectoPK; 
private PersonaProyectoPK personaProyectoAsisPK;
private PersonaProyectoPK personaProyectoExtPK; 
private String numDocumentoSol;
private Persona emailSol;
//Definicion de listas
private List<Facultad> facultadList;
private List<AreaConocimiento> areaConocimientoList;
private List<TipoProyecto> tipoproyectolist;
private List<Unidad> unidadList;
private List<PropuestaConvenio> propuestaConvenioList;
private List<Organismo> organismoList;
//Definicion de selected
private TipoProyecto proyectoSelected;
private String [] areaConocimientoSelected;
private List<AreaConocimiento> areasConocimiento;
private Facultad facultadSelected;
private Unidad unidadSelected;
private PropuestaConvenio propuestaConvenioSelected;
private Facultad facultadSelectedSol;
private Facultad facultadSelectedAsis;
private Facultad facultadSelectedExt;
private Unidad unidadSelectedSol;
private Unidad unidadSelectedAsis;
private Organismo organismoSelected;

//Telefono
//private Telefono telefono;
private Telefono telefonoSolFijo;
private Telefono telefonoSolFax;
private Telefono telefonoAsisFijo ;
private Telefono telefonoAsisFax;
private Telefono telefonoRefextFijo;
private Telefono telefonoRefextFax;
private TipoTelefono tipoTelefonoFax;
private TipoTelefono tipoTelefonoFijo;

private TipoPersona tipoPersonaSol;
private TipoPersona tipoPersonaAsis;
private TipoPersona tipoPersonaRefext;

    /**
     * Creates a new instance of ProyectosMB
     */
    @PostConstruct
    public void init(){
        inicializador();
    }
    
    public void inicializador(){
    //selected
    proyectoSelected = new TipoProyecto();
    facultadSelected=new Facultad();
    unidadSelected=new Unidad();
    propuestaConvenioSelected=new PropuestaConvenio();
    facultadSelectedSol=new Facultad();
    facultadSelectedAsis=new Facultad();
    facultadSelectedExt=new Facultad();
    unidadSelectedSol=new Unidad();
    unidadSelectedAsis=new Unidad();
    organismoSelected=new Organismo();
    areasConocimiento=new ArrayList<AreaConocimiento>();
    //Listas
    tipoproyectolist = tipoProyectoService.findAll();
    areaConocimientoList=areaConocimientoService.findAll();
    facultadList=new ArrayList<Facultad>();
    facultadList=facultadService.findAll();
    propuestaConvenioList=propuestaConvenioService.findAll();
    organismoList=organismoService.findAll();
    //unidadList=unidadService.findAll();
    proyecto = new Proyecto();
    proyectoGenerico=new ProyectoGenerico();
    propuestaConvenio=new PropuestaConvenio();
    facultad = new Facultad();
    facultadAsistente = new Facultad();
    facultadExterna = new Facultad();
    //telefono = new Telefono();
    telefonoSolFijo=new Telefono();
    telefonoSolFax = new Telefono();
    telefonoAsisFijo=new Telefono();
    telefonoAsisFax=new Telefono();
    telefonoRefextFijo=new Telefono();
    telefonoRefextFax=new Telefono();
    persona = new Persona();
    personaAsistente=new Persona();
    personaExterna=new Persona();
    organismo = new Organismo();
    personaProyecto=new PersonaProyecto();
    personaProyectoAsis=new PersonaProyecto();
    personaProyectoExt=new PersonaProyecto();
    personaProyectoPK = new PersonaProyectoPK();
    personaProyectoAsisPK=new PersonaProyectoPK();
    personaProyectoExtPK=new PersonaProyectoPK();
  
    // tipos telefonos
       tipoTelefonoFax= tipoTelefonoService.getTipoByDesc("FAX");
       tipoTelefonoFijo= tipoTelefonoService.getTipoByDesc("FIJO");
    // tipo persona
       tipoPersonaSol=tipoPersonaService.getTipoPersonaByNombre("REFERENTE INTERNO");
       tipoPersonaAsis=tipoPersonaService.getTipoPersonaByNombre("ASISTENTE DE COORDINADOR");
       tipoPersonaRefext=tipoPersonaService.getTipoPersonaByNombre("REFERENTE EXTERNO");
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
        
        //guardar proyecto gen�rico
        //proyectoGenerico.setAreaConocimientoList(areaConocimientoList);
        proyectoGenerico.setIdProyecto(proyecto.getIdProyecto());
        //Intermedia de proyecto y area de conocimiento
        for(int i=0;i<areaConocimientoSelected.length;i++){
        AreaConocimiento area=areaConocimientoService.findById(Integer.parseInt(areaConocimientoSelected[i]));
        if (area !=null){
        areasConocimiento.add(area);
        }
        }
        proyectoGenerico.setAreaConocimientoList(areasConocimiento);
        proyectoGenericoService.save(proyectoGenerico);
        //guardar persona solicitante
        Unidad unidadSolicitante=unidadService.findById(unidadSelectedSol.getIdUnidad());
        persona.setIdUnidad(unidadSolicitante);
        persona.setIdTipoPersona(tipoPersonaSol.getIdTipoPersona());
        personaService.save(persona);
        //guardar telefono fijo solicitante
        telefonoSolFijo.setIdPersona(persona);
        telefonoSolFijo.setIdTipoTelefono(tipoTelefonoFijo);
        telefonoService.save(telefonoSolFijo);
        // guardar fax solicitante
        telefonoSolFax.setIdPersona(persona);
        telefonoSolFax.setIdTipoTelefono(tipoTelefonoFax);
        telefonoService.save(telefonoSolFax);
        //guardar en tabla intermedia persona_proyecto de un solicitante 
                    
         personaProyectoPK.setIdPersona(persona.getIdPersona());
         personaProyectoPK.setIdProyectoGenerico(proyectoGenerico.getIdProyecto());
         personaProyectoPK.setIdTipoPersona(tipoPersonaSol.getIdTipoPersona());            
                   
        /////////////        
        personaProyecto.setProyectoGenerico(proyectoGenerico);
        personaProyecto.setPersona(persona);
        personaProyecto.setTipoPersona(tipoPersonaSol);
        personaProyecto.setPersonaProyectoPK(personaProyectoPK);
        personaProyectoService.save(personaProyecto);
   
        //guardar persona Asistente
        Unidad unidadAsistente=unidadService.findById(unidadSelectedAsis.getIdUnidad());
        personaAsistente.setIdUnidad(unidadAsistente);
        personaAsistente.setIdTipoPersona(tipoPersonaAsis.getIdTipoPersona());
        personaService.save(personaAsistente);
        //guardar telefono fijo asistente
        telefonoAsisFijo.setIdPersona(personaAsistente);
        telefonoAsisFijo.setIdTipoTelefono(tipoTelefonoFijo);
        telefonoService.save(telefonoAsisFijo);
        // guardar fax Asistente
        telefonoAsisFax.setIdPersona(personaAsistente);
        telefonoAsisFax.setIdTipoTelefono(tipoTelefonoFax);
        telefonoService.save(telefonoAsisFax);
        //guardar en tabla intermedia persona_proyecto Asistente
        //PersonaProyectoPK personaProyectoPK = new PersonaProyectoPK();            
         personaProyectoAsisPK.setIdPersona(personaAsistente.getIdPersona());
         personaProyectoAsisPK.setIdProyectoGenerico(proyectoGenerico.getIdProyecto());
         personaProyectoAsisPK.setIdTipoPersona(tipoPersonaAsis.getIdTipoPersona());       
        /////////////        
        personaProyectoAsis.setProyectoGenerico(proyectoGenerico);
        personaProyectoAsis.setPersona(personaAsistente);
        personaProyectoAsis.setTipoPersona(tipoPersonaAsis);
        personaProyectoAsis.setPersonaProyectoPK(personaProyectoAsisPK);
        personaProyectoService.save(personaProyectoAsis);
        
        //Guardar informacion de referente externo
        personaExterna.setIdTipoPersona(tipoPersonaRefext.getIdTipoPersona());
        Organismo organismoExt=organismoService.findById(organismoSelected.getIdOrganismo());
        personaExterna.setIdOrganismo(organismoExt);
        personaService.save(personaExterna);
        //guardar telefono fijo persona externa
        telefonoRefextFijo.setIdPersona(personaExterna);
        telefonoRefextFijo.setIdTipoTelefono(tipoTelefonoFijo);
        telefonoService.save(telefonoRefextFijo);
        //guardar fax persona externa
        telefonoRefextFax.setIdPersona(personaExterna);
        telefonoRefextFax.setIdTipoTelefono(tipoTelefonoFax);
        telefonoService.save(telefonoRefextFax);
        //guardar en tabla intermedia persona_proyecto Referente externo
        personaProyectoExtPK.setIdPersona(personaExterna.getIdPersona());
        personaProyectoExtPK.setIdProyectoGenerico(proyectoGenerico.getIdProyecto());
        personaProyectoExtPK.setIdTipoPersona(tipoPersonaRefext.getIdTipoPersona());
        //////
        personaProyectoExt.setProyectoGenerico(proyectoGenerico);
        personaProyectoExt.setPersona(personaExterna);
        personaProyectoExt.setTipoPersona(tipoPersonaRefext);
        personaProyectoExt.setPersonaProyectoPK(personaProyectoExtPK);
        personaProyectoService.save(personaProyectoExt);
    }catch(Exception e){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La Informacion no ha sido registrada."));
    }
    }
    public void onFacultadChange() {
        if( facultadSelected.getIdFacultad()!=null && !facultadSelected.getIdFacultad().equals(""))
            unidadList = unidadService.getUnidadesByFacultadId(facultadSelected.getIdFacultad());
        else
            unidadList= new ArrayList<Unidad>();
    }
    public void onFacultadSolicitanteChange() {
        if( facultadSelectedSol.getIdFacultad()!=null && !facultadSelectedSol.getIdFacultad().equals(""))
            unidadList = unidadService.getUnidadesByFacultadId(facultadSelectedSol.getIdFacultad());
        else
            unidadList= new ArrayList<Unidad>();
    }
    public void onFacultadAsistenteChange() {
        if( facultadSelectedAsis.getIdFacultad()!=null && !facultadSelectedAsis.getIdFacultad().equals(""))
            unidadList = unidadService.getUnidadesByFacultadId(facultadSelectedAsis.getIdFacultad());
        else
            unidadList= new ArrayList<Unidad>();
    }
    public void searchByDocEmailInterno(){
        try {
            
            if(numDocumentoSol!=null && persona!= null && !numDocumentoSol.equals("") &&  !persona.getEmailPersona().equals("")){
            persona = personaService.getReferenteInternoByDocEmail(numDocumentoSol, persona);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public Telefono getTelefonoSolFijo() {
        return telefonoSolFijo;
    }

    public void setTelefonoSolFijo(Telefono telefonoSolFijo) {
        this.telefonoSolFijo = telefonoSolFijo;
    }

    public Telefono getTelefonoSolFax() {
        return telefonoSolFax;
    }

    public void setTelefonoSolFax(Telefono telefonoSolFax) {
        this.telefonoSolFax = telefonoSolFax;
    }

    public Telefono getTelefonoAsisFijo() {
        return telefonoAsisFijo;
    }

    public void setTelefonoAsisFijo(Telefono telefonoAsisFijo) {
        this.telefonoAsisFijo = telefonoAsisFijo;
    }

    public Telefono getTelefonoAsisFax() {
        return telefonoAsisFax;
    }

    public void setTelefonoAsisFax(Telefono telefonoAsisFax) {
        this.telefonoAsisFax = telefonoAsisFax;
    }

    public Telefono getTelefonoRefextFijo() {
        return telefonoRefextFijo;
    }

    public void setTelefonoRefextFijo(Telefono telefonoRefextFijo) {
        this.telefonoRefextFijo = telefonoRefextFijo;
    }

    public Telefono getTelefonoRefextFax() {
        return telefonoRefextFax;
    }

    public void setTelefonoRefextFax(Telefono telefonoRefextFax) {
        this.telefonoRefextFax = telefonoRefextFax;
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
    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
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

    public Facultad getFacultadSelectedAsis() {
        return facultadSelectedAsis;
    }

    public void setFacultadSelectedAsis(Facultad facultadSelectedAsis) {
        this.facultadSelectedAsis = facultadSelectedAsis;
    }

    public Facultad getFacultadSelectedExt() {
        return facultadSelectedExt;
    }

    public void setFacultadSelectedExt(Facultad facultadSelectedExt) {
        this.facultadSelectedExt = facultadSelectedExt;
    }
    
    public Unidad getUnidadSelectedSol() {
        return unidadSelectedSol;
    }

    public void setUnidadSelectedSol(Unidad unidadSelectedSol) {
        this.unidadSelectedSol = unidadSelectedSol;
    }

    public Unidad getUnidadSelectedAsis() {
        return unidadSelectedAsis;
    }

    public void setUnidadSelectedAsis(Unidad unidadSelectedAsis) {
        this.unidadSelectedAsis = unidadSelectedAsis;
    }

    public Organismo getOrganismo() {
        return organismo;
    }

    public void setOrganismo(Organismo organismo) {
        this.organismo = organismo;
    }

    public TipoTelefono getTipoTelefonoFax() {
        return tipoTelefonoFax;
    }

    public void setTipoTelefonoFax(TipoTelefono tipoTelefonoFax) {
        this.tipoTelefonoFax = tipoTelefonoFax;
    }

    public TipoTelefono getTipoTelefonoFijo() {
        return tipoTelefonoFijo;
    }

    public void setTipoTelefonoFijo(TipoTelefono tipoTelefonoFijo) {
        this.tipoTelefonoFijo = tipoTelefonoFijo;
    }

    public PersonaProyecto getPersonaProyecto() {
        return personaProyecto;
    }

    public void setPersonaProyecto(PersonaProyecto personaProyecto) {
        this.personaProyecto = personaProyecto;
    }

    public TipoPersona getTipoPersonaSol() {
        return tipoPersonaSol;
    }

    public void setTipoPersonaSol(TipoPersona tipoPersonaSol) {
        this.tipoPersonaSol = tipoPersonaSol;
    }

    public TipoPersona getTipoPersonaAsis() {
        return tipoPersonaAsis;
    }

    public void setTipoPersonaAsis(TipoPersona tipoPersonaAsis) {
        this.tipoPersonaAsis = tipoPersonaAsis;
    }

    public TipoPersona getTipoPersonaRefext() {
        return tipoPersonaRefext;
    }

    public void setTipoPersonaRefext(TipoPersona tipoPersonaRefext) {
        this.tipoPersonaRefext = tipoPersonaRefext;
    }
    
    public List<Organismo> getOrganismoList() {
        return organismoList;
    }

    public void setOrganismoList(List<Organismo> organismoList) {
        this.organismoList = organismoList;
    }

    public Organismo getOrganismoSelected() {
        return organismoSelected;
    }

    public void setOrganismoSelected(Organismo organismoSelected) {
        this.organismoSelected = organismoSelected;
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

    public List<AreaConocimiento> getAreasConocimiento() {
        return areasConocimiento;
    }

    public void setAreasConocimiento(List<AreaConocimiento> areasConocimiento) {
        this.areasConocimiento = areasConocimiento;
    }

    public String getNumDocumentoSol() {
        return numDocumentoSol;
    }

    public void setNumDocumentoSol(String numDocumentoSol) {
        this.numDocumentoSol = numDocumentoSol;
    }

    public Persona getEmailSol() {
        return emailSol;
    }

    public void setEmailSol(Persona emailSol) {
        this.emailSol = emailSol;
    }

    

   
    
}
