/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.AreaConocimiento;
import com.sisrni.model.Facultad;
import com.sisrni.model.FacultadProyecto;
import com.sisrni.model.FacultadProyectoPK;
import com.sisrni.model.Organismo;
import com.sisrni.model.Pais;
import com.sisrni.model.Persona;
import com.sisrni.model.PersonaProyecto;
import com.sisrni.model.PersonaProyectoPK;
import com.sisrni.model.PropuestaConvenio;
import com.sisrni.model.Proyecto;
import com.sisrni.model.Telefono;
import com.sisrni.model.TipoFacultad;
import com.sisrni.model.TipoPersona;
import com.sisrni.model.TipoProyecto;
import com.sisrni.model.TipoTelefono;
import com.sisrni.model.Unidad;
import com.sisrni.service.AreaConocimientoService;
import com.sisrni.service.FacultadProyectoService;
import com.sisrni.service.FacultadService;
import com.sisrni.service.OrganismoService;
import com.sisrni.service.PaisService;
import com.sisrni.service.PersonaProyectoService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.PropuestaConvenioService;
import com.sisrni.service.ProyectoService;
import com.sisrni.service.RegionService;
import com.sisrni.service.TelefonoService;
import com.sisrni.service.TipoFacultadService;
import com.sisrni.service.TipoPersonaService;
import com.sisrni.service.TipoProyectoService;
import com.sisrni.service.TipoTelefonoService;
import com.sisrni.service.UnidadService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
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

    @Inject
    ProyectoMB proyectoMB;
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
    private PropuestaConvenioService propuestaConvenioService;
    @Autowired
    private TipoTelefonoService tipoTelefonoService;
    @Autowired
    private OrganismoService organismoService;
    @Autowired
    private TipoPersonaService tipoPersonaService;
    @Autowired
    private PersonaProyectoService personaProyectoService;
    @Autowired
    private PaisService paisService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private TipoFacultadService tipoFacultadService;
    @Autowired
    private FacultadProyectoService facultadProyectoService;

//Definicion de objetos    
    private Proyecto proyecto;
    private PropuestaConvenio propuestaConvenio;
    private Persona persona;
    private Persona personaAsistente;
    private Persona personaExterna;
    private Persona personaBecario;
    private Facultad facultad;
    private Facultad facultadB;
    private Unidad unidadProyecto;
    private Organismo organismo;
    private Facultad facultadAsistente;
    private Facultad facultadCoordinador;
    private PersonaProyecto personaProyecto;
    private PersonaProyectoPK personaProyectoExtPK;
    private String numDocumentoSol;
    private String numDocumentoAsis;
    private String numDocumentoRefExt;
    private String numDocumentoBecario;
    private int yearActual;
    private String anio;
    private FacultadProyectoPK facultadProyectoPK;
    private FacultadProyectoPK facultadProyectoBeneficiadaPK;
    private FacultadProyecto facultadProyecto;

//Definicion de listas
    private List<Facultad> facultadList;
    private List<AreaConocimiento> areaConocimientoList;
    private List<Organismo> organismoProyList;
    private List<TipoProyecto> tipoproyectolist;
    private List<Unidad> unidadList;
    private List<Unidad> unidadSolList;
    private List<Unidad> unidadAsisList;
    private List<PropuestaConvenio> propuestaConvenioList;
    private List<Organismo> organismoList;
    private List<Telefono> listadoTelefonoSol;
    private List<Telefono> listadoTelefonoAsis;
    private List<Telefono> listadoTelefonoRefExt;
    private List<Pais> paisList;
    private List<Pais> paisCooperanteList;
    private List<Facultad> facultadBeneficiada;
    private List<Facultad> facultadBeneficiadaList;
//Definicion de selected
    private TipoProyecto proyectoSelected;
    private String[] areaConocimientoSelected;
    private String[] organismoProySelected;
    private List<AreaConocimiento> areasConocimiento;
    private List<Organismo> organismosProyecto;
    private Facultad facultadSelected;
    private String[] facultadBeneficiadaSelected;
    private Unidad unidadSelected;
    private PersonaProyecto personaProyectoAsis;
    private PersonaProyecto personaProyectoExt;
    private PersonaProyectoPK personaProyectoPK;
    private PersonaProyectoPK personaProyectoAsisPK;
    private PropuestaConvenio propuestaConvenioSelected;
    private Facultad facultadSelectedSol;
    private Facultad facultadSelectedAsis;
    private Facultad facultadSelectedExt;
    private Facultad facultadSelectedBecario;
    private Unidad unidadSelectedSol;
    private Unidad unidadSelectedAsis;
    private Unidad unidadSelectedBecario;
    private Organismo organismoSelected;
    private Pais paisSelected;
    private Pais paisCooperanteSelected;
//Telefono
//private Telefono telefono;
    private Telefono telefonoSolFijo;
    private Telefono telefonoSolFax;
    private Telefono telefonoSolCel;
    private Telefono telefonoAsisFijo;
    private Telefono telefonoAsisFax;
    private Telefono telefonoAsisCel;
    private Telefono telefonoRefextFijo;
    private Telefono telefonoRefextFax;
    private Telefono telefonoRefextCel;
    private TipoTelefono tipoTelefonoFax;
    private TipoTelefono tipoTelefonoFijo;
    private TipoTelefono tipoTelefonoCel;

    private TipoPersona tipoPersonaSol;
    private TipoPersona tipoPersonaAsis;
    private TipoPersona tipoPersonaRefext;

    private TipoFacultad tipoFacultad;
    private TipoFacultad tipoFacultadB;

    private static final String FIJO = "FIJO";
    private static final String FAX = "FAX";
    private int existeSol;
    private int existeAsis;
    private int existeRefExt;
    Pais regiones;
    public boolean mostrarmonto;

    /**
     * Creates a new instance of ProyectosMB
     */
    @PostConstruct
    public void init() {
        inicializador();
    }

    public void inicializador() {
        //selected
        proyectoSelected = new TipoProyecto();
        facultadSelected = new Facultad();
        unidadSelected = new Unidad();
        propuestaConvenioSelected = new PropuestaConvenio();
        facultadSelectedSol = new Facultad();
        facultadSelectedAsis = new Facultad();
        facultadSelectedExt = new Facultad();
        facultadSelectedBecario = new Facultad();
        unidadSelectedSol = new Unidad();
        unidadSelectedAsis = new Unidad();
        unidadSelectedBecario = new Unidad();
        organismoSelected = new Organismo();
        areasConocimiento = new ArrayList<AreaConocimiento>();
        organismosProyecto = new ArrayList<Organismo>();
        paisSelected = new Pais();
        paisCooperanteSelected = new Pais();
        //Listas
        tipoproyectolist = tipoProyectoService.findAll();
        areaConocimientoList = areaConocimientoService.findAll();
        organismoProyList = organismoService.findAll();
        //facultadList = new ArrayList<Facultad>();
        facultadList = facultadService.findAll();
        propuestaConvenioList = propuestaConvenioService.findAll();
        organismoList = organismoService.findAll();
        listadoTelefonoSol = telefonoService.findAll();
        listadoTelefonoAsis = telefonoService.findAll();
        listadoTelefonoRefExt = telefonoService.findAll();
        paisList = paisService.findAll();
        paisCooperanteList = paisService.findAll();
        facultadBeneficiadaList = facultadService.findAll();
        facultadBeneficiada = new ArrayList<Facultad>();
        //unidadList=unidadService.findAll();
        proyecto = new Proyecto();
        propuestaConvenio = new PropuestaConvenio();
        facultad = new Facultad();
        facultadB = new Facultad();
        facultadAsistente = new Facultad();
        facultadCoordinador = new Facultad();
        //telefono = new Telefono();
        telefonoSolFijo = new Telefono();
        telefonoSolFax = new Telefono();
        telefonoSolCel = new Telefono();
        telefonoAsisFijo = new Telefono();
        telefonoAsisFax = new Telefono();
        telefonoAsisCel = new Telefono();
        telefonoRefextFijo = new Telefono();
        telefonoRefextFax = new Telefono();
        telefonoRefextCel = new Telefono();
        persona = new Persona();
        personaAsistente = new Persona();
        personaExterna = new Persona();
        personaBecario = new Persona();
        organismo = new Organismo();
        personaProyecto = new PersonaProyecto();
        personaProyectoAsis = new PersonaProyecto();
        personaProyectoExt = new PersonaProyecto();
        personaProyectoPK = new PersonaProyectoPK();
        personaProyectoAsisPK = new PersonaProyectoPK();
        personaProyectoExtPK = new PersonaProyectoPK();
        regiones = new Pais();
        numDocumentoAsis = "";
        numDocumentoSol = "";
        numDocumentoRefExt = "";
        numDocumentoBecario = "";
        facultadProyecto = new FacultadProyecto();
        facultadProyectoPK = new FacultadProyectoPK();
        facultadProyectoBeneficiadaPK = new FacultadProyectoPK();

        // tipos telefonos
        tipoTelefonoFax = tipoTelefonoService.getTipoByDesc("FAX");
        tipoTelefonoFijo = tipoTelefonoService.getTipoByDesc("FIJO");
        tipoTelefonoCel = tipoTelefonoService.getTipoByDesc("CELULAR");
        // tipo persona
        tipoPersonaSol = tipoPersonaService.getTipoPersonaByNombre("REFERENTE INTERNO");
        tipoPersonaAsis = tipoPersonaService.getTipoPersonaByNombre("ASISTENTE DE COORDINADOR");
        tipoPersonaRefext = tipoPersonaService.getTipoPersonaByNombre("REFERENTE EXTERNO");
        //tipo facultad
        tipoFacultad = tipoFacultadService.getTipoFacultadByNombre("INICIATICA");
        tipoFacultadB = tipoFacultadService.getTipoFacultadByNombre("BENEFICIADA");
        //bandera
        existeSol = 0;
        existeAsis = 0;
        existeRefExt = 0;
        yearActual = getYearOfDate(new Date());
        anio = "";
    }

//    public void mostrarCampo() {
//        if (tipoModalidadSelected.getIdTipoModalidad() == 1) {
//            mostrarmonto = true;
//        } else {
//            mostrarmonto = false;
//        }
//    }
    public void guardarProyecto() {
        try {
            proyecto.setIdTipoProyecto(tipoProyectoService.findById(proyectoSelected.getIdTipoProyecto()));
//                proyecto.setIdFacultad(facultadSelected.getIdFacultad());
            proyecto.setIdUnidad(unidadSelected.getIdUnidad());
            PropuestaConvenio propuesta = propuestaConvenioService.findById(propuestaConvenioSelected.getIdPropuesta());
            if (propuesta == null) {
            } else {
                proyecto.setIdPropuestaConvenio(propuesta);
            }
            proyecto.setAnioGestion(Integer.parseInt(anio.trim()));
            //guardar proyecto
            proyecto.setIdProyecto(0);
            Pais paiscooperante = paisService.findById(paisCooperanteSelected.getIdPais());
            proyecto.setIdPaisCooperante(paiscooperante);
            
            //Intermedia de proyecto y area de conocimiento
            for (int i = 0; i < areaConocimientoSelected.length; i++) {
                AreaConocimiento area = areaConocimientoService.findById(Integer.parseInt(areaConocimientoSelected[i]));
                if (area != null) {
                    areasConocimiento.add(area);
                }
            }
            proyecto.setAreaConocimientoList(areasConocimiento);

            // guardar organismo
            for (int i = 0; i < organismoProySelected.length; i++) {
                Organismo organismo = organismoService.findById(Integer.parseInt(organismoProySelected[i]));
                if (organismo != null) {
                    organismosProyecto.add(organismo);
                }
            }
            proyecto.setOrganismoList(organismosProyecto);
            proyectoService.save(proyecto);
            //guardar en tabla intermedia Facultad_Proyecto
            facultadProyectoPK.setIdFacultad(facultadSelected.getIdFacultad());
            facultadProyectoPK.setIdProyecto(proyecto.getIdProyecto());
            //////
            facultadProyecto.setProyecto(proyecto);
            facultadProyecto.setFacultad(facultadSelected);
            facultadProyecto.setIdTipoFacultad(tipoFacultad);
            facultadProyecto.setFacultadProyectoPK(facultadProyectoPK);
            facultadProyectoService.save(facultadProyecto);
            //guardar en tabla intermedia Facultad_Proyecto facultad beneficiada
            for (int i = 0; i < facultadBeneficiadaSelected.length; i++) {
                FacultadProyectoPK facultadBenPK = new FacultadProyectoPK();
                FacultadProyecto facultadBen = new FacultadProyecto();
                Facultad fac = facultadService.findById(Integer.parseInt(facultadBeneficiadaSelected[i]));
                if (fac != null) {
                    facultadBenPK.setIdFacultad(fac.getIdFacultad());
                    facultadBenPK.setIdProyecto(proyecto.getIdProyecto());
                    facultadBen.setFacultadProyectoPK(facultadBenPK);
                    facultadBen.setIdTipoFacultad(tipoFacultadB);
                    proyecto.getFacultadProyectoList().add(facultadBen);
                    facultadProyectoService.save(facultadBen);
                }
            }
            //guardar persona solicitante
            if (existeSol != 1) {
                Unidad unidadSolicitante = unidadService.findById(unidadSelectedSol.getIdUnidad());
                persona.setIdUnidad(unidadSolicitante);
//                    persona.setIdTipoPersona(tipoPersonaSol.getIdTipoPersona());
                persona.setDuiPersona(numDocumentoSol);
                personaService.save(persona);
                //guardar telefono fijo solicitante
                telefonoSolFijo.setIdPersona(persona);
                telefonoSolFijo.setIdTipoTelefono(tipoTelefonoFijo);
                telefonoService.save(telefonoSolFijo);
                //guardar telefono celular solicitante
                telefonoSolCel.setIdPersona(persona);
                telefonoSolCel.setIdTipoTelefono(tipoTelefonoCel);
                telefonoService.save(telefonoSolCel);
                //guardar telefono fax solicitante
                telefonoSolFax.setIdPersona(persona);
                telefonoSolFax.setIdTipoTelefono(tipoTelefonoFax);
                telefonoService.save(telefonoSolFax);
                //guardar en tabla intermedia persona_proyecto de un solicitante 
                personaProyectoPK.setIdPersona(persona.getIdPersona());
                personaProyectoPK.setIdProyecto(proyecto.getIdProyecto());
//                    personaProyectoPK.setIdTipoPersona(tipoPersonaSol.getIdTipoPersona());

                /////////////        
                personaProyecto.setProyecto(proyecto);
                personaProyecto.setPersona(persona);
//                    personaProyecto.setTipoPersona(tipoPersonaSol);
                personaProyecto.setPersonaProyectoPK(personaProyectoPK);
                personaProyectoService.save(personaProyecto);
            }
            //guardar persona Asistente
            if (existeAsis != 1) {
                if (personaAsistente.getIdPersona() == null) {
                } else {
                    Unidad unidadAsistente = unidadService.findById(unidadSelectedAsis.getIdUnidad());
                    personaAsistente.setIdUnidad(unidadAsistente);
//                    personaAsistente.setIdTipoPersona(tipoPersonaAsis.getIdTipoPersona());
                    personaAsistente.setDuiPersona(numDocumentoAsis);
                    personaService.save(personaAsistente);
                    //guardar telefono fijo asistente
                    telefonoAsisFijo.setIdPersona(personaAsistente);
                    telefonoAsisFijo.setIdTipoTelefono(tipoTelefonoFijo);
                    telefonoService.save(telefonoAsisFijo);
                    //guardar telefono celular asistente
                    telefonoAsisCel.setIdPersona(personaAsistente);
                    telefonoAsisCel.setIdTipoTelefono(tipoTelefonoCel);
                    telefonoService.save(telefonoAsisCel);
                    // guardar fax Asistente
                    telefonoAsisFax.setIdPersona(personaAsistente);
                    telefonoAsisFax.setIdTipoTelefono(tipoTelefonoFax);
                    telefonoService.save(telefonoAsisFax);
                    //guardar en tabla intermedia persona_proyecto Asistente
                    //PersonaProyectoPK personaProyectoPK = new PersonaProyectoPK();            
                    personaProyectoAsisPK.setIdPersona(personaAsistente.getIdPersona());
                    personaProyectoAsisPK.setIdProyecto(proyecto.getIdProyecto());
//                    personaProyectoAsisPK.setIdTipoPersona(tipoPersonaAsis.getIdTipoPersona());
                    /////////////        
                    personaProyectoAsis.setProyecto(proyecto);
                    personaProyectoAsis.setPersona(personaAsistente);
                    personaProyectoAsis.setIdTipoPersona(tipoPersonaAsis);
                    personaProyectoAsis.setPersonaProyectoPK(personaProyectoAsisPK);
                    personaProyectoService.save(personaProyectoAsis);
                }
            }
            //Guardar informacion de referente externo
            if (existeRefExt != 1) {
//                    personaExterna.setIdTipoPersona(tipoPersonaRefext.getIdTipoPersona());
                Organismo organismoExt = organismoService.findById(organismoSelected.getIdOrganismo());
                personaExterna.setIdOrganismo(organismoExt);
                personaExterna.setPasaporte(numDocumentoRefExt);
                personaService.save(personaExterna);
                //guardar telefono fijo persona externa
                telefonoRefextFijo.setIdPersona(personaExterna);
                telefonoRefextFijo.setIdTipoTelefono(tipoTelefonoFijo);
                telefonoService.save(telefonoRefextFijo);
                //guardar telefono fijo persona externa
                telefonoRefextCel.setIdPersona(personaExterna);
                telefonoRefextCel.setIdTipoTelefono(tipoTelefonoCel);
                telefonoService.save(telefonoRefextCel);
                //guardar fax persona externa
                telefonoRefextFax.setIdPersona(personaExterna);
                telefonoRefextFax.setIdTipoTelefono(tipoTelefonoFax);
                telefonoService.save(telefonoRefextFax);
                //guardar en tabla intermedia persona_proyecto Referente externo
                personaProyectoExtPK.setIdPersona(personaExterna.getIdPersona());
                personaProyectoExtPK.setIdProyecto(proyecto.getIdProyecto());
//                    personaProyectoExtPK.setIdTipoPersona(tipoPersonaRefext.getIdTipoPersona());
                //////
                personaProyectoExt.setProyecto(proyecto);
                personaProyectoExt.setPersona(personaExterna);
                personaProyectoExt.setIdTipoPersona(tipoPersonaRefext);
                personaProyectoExt.setPersonaProyectoPK(personaProyectoExtPK);
                personaProyectoService.save(personaProyectoExt);
            }
        } catch (Exception  e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La Informacion no ha sido registrada."));
        }
        inicializador();
    }

    public void onFacultadChange() {
        if (facultadSelected.getIdFacultad() != null && !facultadSelected.getIdFacultad().toString().equals("")) {
            unidadList = unidadService.getUnidadesByFacultadId(facultadSelected.getIdFacultad());
        } else {
            unidadList = new ArrayList<Unidad>();
        }
    }

    public void onFacultadSolicitanteChange() {
        if (facultadSelectedSol.getIdFacultad() != null && !facultadSelectedSol.getIdFacultad().toString().equals("")) {
            unidadSolList = unidadService.getUnidadesByFacultadId(facultadSelectedSol.getIdFacultad());
        } else {
            unidadSolList = new ArrayList<Unidad>();
        }
    }

    public void onFacultadAsistenteChange() {
        if (facultadSelectedAsis.getIdFacultad() != null && !facultadSelectedAsis.getIdFacultad().toString().equals("")) {
            unidadAsisList = unidadService.getUnidadesByFacultadId(facultadSelectedAsis.getIdFacultad());
        } else {
            unidadAsisList = new ArrayList<Unidad>();
        }
    }

//Buscando a persona  Solicitante existente
    public void searchByDocEmailInterno() {
        try {

            if (numDocumentoSol != null && persona != null && !numDocumentoSol.equals("") && !persona.getEmailPersona().equals("")) {
                persona = personaService.getReferenteInternoByDocEmail(numDocumentoSol, persona);
                if (persona != null) {
                    existeSol = 1;
                }
                facultadSelectedSol = persona.getIdUnidad().getIdFacultad();
                unidadSelectedSol = persona.getIdUnidad();
                onChangeInterno();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onChangeInterno() {
        try {
            listadoTelefonoSol = telefonoService.getTelefonosByPersona(persona);

            for (Telefono us : listadoTelefonoSol) {

                if (us.getIdTipoTelefono().getNombre().equals(FIJO)) {
                    telefonoSolFijo = us;
                }
                if (us.getIdTipoTelefono().getNombre().equals(FAX)) {
                    telefonoSolFax = us;
                }
            }
        } catch (Exception e) {
        }
    }//Fin Buscando a persona  Solicitante existente 

//Buscando si existe persona asistente
    public void searchByDocEmailAsistente() {
        try {

            if (numDocumentoAsis != null && personaAsistente != null && !numDocumentoAsis.equals("") && !personaAsistente.getEmailPersona().equals("")) {
                personaAsistente = personaService.getReferenteInternoByDocEmail(numDocumentoAsis, personaAsistente);
                if (personaAsistente != null) {
                    existeAsis = 1;
                }
                facultadSelectedAsis = personaAsistente.getIdUnidad().getIdFacultad();
                unidadSelectedAsis = personaAsistente.getIdUnidad();
                onChangeAsistente();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onChangeAsistente() {
        try {
            listadoTelefonoAsis = telefonoService.getTelefonosByPersona(personaAsistente);

            for (Telefono us : listadoTelefonoAsis) {

                if (us.getIdTipoTelefono().getNombre().equals(FIJO)) {
                    telefonoAsisFijo = us;
                }
                if (us.getIdTipoTelefono().getNombre().equals(FAX)) {
                    telefonoAsisFax = us;
                }
            }
        } catch (Exception e) {
        }
    }
    //Fin busca de persona asistente

//Buscando si existe persona Referente externo
    public void searchByDocEmailReferenteExterno() {
        try {

            if (numDocumentoRefExt != null && personaExterna != null && !numDocumentoRefExt.equals("") && !personaExterna.getEmailPersona().equals("")) {
                personaExterna = personaService.getReferenteInternoByDocEmail(numDocumentoRefExt, personaExterna);
                if (personaExterna != null) {
                    existeRefExt = 1;
                }
                facultadSelectedExt = personaExterna.getIdUnidad().getIdFacultad();
                organismoSelected = personaExterna.getIdOrganismo();
                onChangeReferenteExterno();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onChangeReferenteExterno() {
        try {
            listadoTelefonoRefExt = telefonoService.getTelefonosByPersona(personaExterna);

            for (Telefono us : listadoTelefonoRefExt) {

                if (us.getIdTipoTelefono().getNombre().equals(FIJO)) {
                    telefonoRefextFijo = us;
                }
                if (us.getIdTipoTelefono().getNombre().equals(FAX)) {
                    telefonoRefextFax = us;
                }
            }
        } catch (Exception e) {
        }
    }
    //Fin busca de persona Referente externo

    private Integer getYearOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer year = cal.get(Calendar.YEAR);
        return year;
    }
//    public void regresar(){
//        try {
//            proyectoMB.cargarProyecto();
//            
//            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();  
//           
//            String outcome = "proyectoAdm.xhtml";
//            FacesContext.getCurrentInstance().getExternalContext().redirect("proyectoAdm.xhtml");
//        } catch (Exception e) {
//        }
//    }

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

    public List<Unidad> getUnidadSolList() {
        return unidadSolList;
    }

    public void setUnidadSolList(List<Unidad> unidadSolList) {
        this.unidadSolList = unidadSolList;
    }

    public List<Unidad> getUnidadAsisList() {
        return unidadAsisList;
    }

    public void setUnidadAsisList(List<Unidad> unidadAsisList) {
        this.unidadAsisList = unidadAsisList;
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

    public String getNumDocumentoAsis() {
        return numDocumentoAsis;
    }

    public void setNumDocumentoAsis(String numDocumentoAsis) {
        this.numDocumentoAsis = numDocumentoAsis;
    }

    public String getNumDocumentoRefExt() {
        return numDocumentoRefExt;
    }

    public void setNumDocumentoRefExt(String numDocumentoRefExt) {
        this.numDocumentoRefExt = numDocumentoRefExt;
    }

    public List<Telefono> getListadoTelefonoSol() {
        return listadoTelefonoSol;
    }

    public void setListadoTelefonoSol(List<Telefono> listadoTelefonoSol) {
        this.listadoTelefonoSol = listadoTelefonoSol;
    }

    public List<Pais> getPaisList() {
        return paisList;
    }

    public void setPaisList(List<Pais> paisList) {
        this.paisList = paisList;
    }

    public Pais getPaisSelected() {
        return paisSelected;
    }

    public void setPaisSelected(Pais paisSelected) {
        this.paisSelected = paisSelected;
    }

    public Persona getPersonaBecario() {
        return personaBecario;
    }

    public void setPersonaBecario(Persona personaBecario) {
        this.personaBecario = personaBecario;
    }

    public Facultad getFacultadSelectedBecario() {
        return facultadSelectedBecario;
    }

    public void setFacultadSelectedBecario(Facultad facultadSelectedBecario) {
        this.facultadSelectedBecario = facultadSelectedBecario;
    }

    public String getNumDocumentoBecario() {
        return numDocumentoBecario;
    }

    public void setNumDocumentoBecario(String numDocumentoBecario) {
        this.numDocumentoBecario = numDocumentoBecario;
    }

    public Unidad getUnidadSelectedBecario() {
        return unidadSelectedBecario;
    }

    public void setUnidadSelectedBecario(Unidad unidadSelectedBecario) {
        this.unidadSelectedBecario = unidadSelectedBecario;
    }

    public int getYearActual() {
        return yearActual;
    }

    public void setYearActual(int yearActual) {
        this.yearActual = yearActual;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public Pais getRegiones() {
        return regiones;
    }

    public void setRegiones(Pais regiones) {
        this.regiones = regiones;
    }

    public List<Organismo> getOrganismoProyList() {
        return organismoProyList;
    }

    public void setOrganismoProyList(List<Organismo> organismoProyList) {
        this.organismoProyList = organismoProyList;
    }

    public String[] getOrganismoProySelected() {
        return organismoProySelected;
    }

    public void setOrganismoProySelected(String[] organismoProySelected) {
        this.organismoProySelected = organismoProySelected;
    }

    public List<Organismo> getOrganismosProyecto() {
        return organismosProyecto;
    }

    public void setOrganismosProyecto(List<Organismo> organismosProyecto) {
        this.organismosProyecto = organismosProyecto;
    }

    public List<Pais> getPaisCooperanteList() {
        return paisCooperanteList;
    }

    public void setPaisCooperanteList(List<Pais> paisCooperanteList) {
        this.paisCooperanteList = paisCooperanteList;
    }

    public Pais getPaisCooperanteSelected() {
        return paisCooperanteSelected;
    }

    public void setPaisCooperanteSelected(Pais paisCooperanteSelected) {
        this.paisCooperanteSelected = paisCooperanteSelected;
    }

    public boolean isMostrarmonto() {
        return mostrarmonto;
    }

    public void setMostrarmonto(boolean mostrarmonto) {
        this.mostrarmonto = mostrarmonto;
    }

    public ProyectoMB getProyectoMB() {
        return proyectoMB;
    }

    public void setProyectoMB(ProyectoMB proyectoMB) {
        this.proyectoMB = proyectoMB;
    }

    public List<Facultad> getFacultadBeneficiada() {
        return facultadBeneficiada;
    }

    public void setFacultadBeneficiada(List<Facultad> facultadBeneficiada) {
        this.facultadBeneficiada = facultadBeneficiada;
    }

    public List<Facultad> getFacultadBeneficiadaList() {
        return facultadBeneficiadaList;
    }

    public void setFacultadBeneficiadaList(List<Facultad> facultadBeneficiadaList) {
        this.facultadBeneficiadaList = facultadBeneficiadaList;
    }

    public String[] getFacultadBeneficiadaSelected() {
        return facultadBeneficiadaSelected;
    }

    public void setFacultadBeneficiadaSelected(String[] facultadBeneficiadaSelected) {
        this.facultadBeneficiadaSelected = facultadBeneficiadaSelected;
    }

    public Telefono getTelefonoSolCel() {
        return telefonoSolCel;
    }

    public void setTelefonoSolCel(Telefono telefonoSolCel) {
        this.telefonoSolCel = telefonoSolCel;
    }

    public Telefono getTelefonoAsisCel() {
        return telefonoAsisCel;
    }

    public void setTelefonoAsisCel(Telefono telefonoAsisCel) {
        this.telefonoAsisCel = telefonoAsisCel;
    }

    public Telefono getTelefonoRefextCel() {
        return telefonoRefextCel;
    }

    public void setTelefonoRefextCel(Telefono telefonoRefextCel) {
        this.telefonoRefextCel = telefonoRefextCel;
    }

    public Facultad getFacultadB() {
        return facultadB;
    }

    public void setFacultadB(Facultad facultadB) {
        this.facultadB = facultadB;
    }

    public Facultad getFacultadCoordinador() {
        return facultadCoordinador;
    }

    public void setFacultadCoordinador(Facultad facultadCoordinador) {
        this.facultadCoordinador = facultadCoordinador;
    }

}
