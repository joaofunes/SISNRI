/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.Beca;
import com.sisrni.model.Carrera;
import com.sisrni.model.EscuelaDepartamento;
import com.sisrni.model.Facultad;
import com.sisrni.model.Organismo;
import com.sisrni.model.Pais;
import com.sisrni.model.Persona;
import com.sisrni.model.ProgramaBeca;
import com.sisrni.model.Telefono;
import com.sisrni.model.TipoModalidaBeca;
import com.sisrni.model.Unidad;
import com.sisrni.pojo.rpt.PojoFacultadesUnidades;
import com.sisrni.service.BecaService;
import com.sisrni.service.CarreraService;
import com.sisrni.service.EscuelaDepartamentoService;
import com.sisrni.service.FacultadService;
import com.sisrni.service.OrganismoService;
import com.sisrni.service.PaisService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.ProgramaBecaService;
import com.sisrni.service.ProyectoService;
import com.sisrni.service.TelefonoService;
import com.sisrni.service.TipoModalidadBecaService;
import com.sisrni.service.TipoPersonaService;
import com.sisrni.service.TipoProyectoService;
import com.sisrni.service.TipoTelefonoService;
import com.sisrni.service.UnidadService;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Cortez
 */
@Named(value = "becaMB")
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class BecaMB implements Serializable {

    /**
     * Creates a new instance of BecaMB
     */
    private static final String FIJO = "FIJO";
    private static final String FAX = "FAX";
    private static final String CELULAR = "CELULAR";

    //para el becario
    private Persona becario;
    private Facultad facultadSelectedBecario;
    private Telefono telefonoFijoBecario;
    private Telefono telefonoCelularBecario;
    private Carrera carreraSelected;

    //para la beca
    private Beca beca;
    private Pais paisCooperanteSelected;
    private ProgramaBeca programaBecaSelected;
    private Pais paisDestinoSelected;
    private Organismo universidadSelected;
    private TipoModalidaBeca tipoModalidaBecaSelected;
    private int yearActual;
    private String anio;

    //para el referente interno
    private Persona asesorInterno;
    private Facultad facultadSelectedAsesorInterno;
    private EscuelaDepartamento escuelaDeptoInterno;
    private Telefono telefonoFijoAsesorInterno;
    private Telefono telefonoCelularAsesorInterno;

    //para el asesor externo
    private Persona asesorExterno;
    private Organismo entidadInstitucionSelected;
    private Telefono telefonoFijoAsesorExterno;
    private Telefono telefonoCelularAsesorExterno;

    //para los listados
    private List<Facultad> facultadList;
    private List<Carrera> carreraList;
    private List<Pais> paisList;
    private List<ProgramaBeca> programaBecaList;
    private List<Organismo> universidadList;
    private List<TipoModalidaBeca> tipoModalidadBecaList;
    private List<Unidad> unidadListAsesorInterno;
    private List<Organismo> organismoList;
    private List<PojoFacultadesUnidades> facultadesUnidadesList;
    private List<EscuelaDepartamento> escuelaDepartamentoList;
    private String facuniSelectded;

    private boolean mostrarmonto;

    @Autowired
    FacultadService facultadService;

    @Autowired
    PaisService paisService;

    @Autowired
    ProgramaBecaService programaBecaService;

    @Autowired
    OrganismoService organismoService;

    @Autowired
    TipoModalidadBecaService tipoModalidadBecaService;

    @Autowired
    TipoProyectoService tipoProyectoService;

    @Autowired
    PersonaService personaService;

    @Autowired
    ProyectoService proyectoService;

    @Autowired
    CarreraService carreraService;

    @Autowired
    TipoTelefonoService tipoTelefonoService;

    @Autowired
    UnidadService unidadService;

    @Autowired
    TelefonoService telefonoService;

    @Autowired
    TipoPersonaService tipoPersonaService;

    @Autowired
    BecaService becaService;

    @Autowired
    EscuelaDepartamentoService escuelaDepartamentoService;

    public BecaMB() {
    }

    @PostConstruct
    public void init() {
        inicializador();
    }

    public void inicializador() {
        //para el becario
        becario = new Persona();
        facultadSelectedBecario = new Facultad();
        telefonoFijoBecario = new Telefono();
        telefonoCelularBecario = new Telefono();
        carreraSelected = new Carrera();

        //para la beca
        beca = new Beca();
        paisCooperanteSelected = new Pais();
        programaBecaSelected = new ProgramaBeca();
        paisDestinoSelected = new Pais();
        universidadSelected = new Organismo();
        tipoModalidaBecaSelected = new TipoModalidaBeca();
        yearActual = getYearOfDate(new Date());
        //anio=yearActual;

        //para el referente interno
        asesorInterno = new Persona();
        facultadSelectedAsesorInterno = new Facultad();
        escuelaDeptoInterno = new EscuelaDepartamento();
        telefonoFijoAsesorInterno = new Telefono();
        telefonoCelularAsesorInterno = new Telefono();

        //para el asesor externo
        asesorExterno = new Persona();
        entidadInstitucionSelected = new Organismo();
        telefonoFijoAsesorExterno = new Telefono();
        telefonoCelularAsesorExterno = new Telefono();

        //para los listados
        facultadList = facultadService.getFacultadesByUniversidad(1);
        carreraList = new ArrayList<Carrera>();
        paisList = paisService.findAll();
        programaBecaList = programaBecaService.findAll();
        universidadList = new ArrayList<Organismo>();
        tipoModalidadBecaList = tipoModalidadBecaService.findAll();
        unidadListAsesorInterno = unidadService.findAll();
        organismoList = organismoService.findAll();
        facultadesUnidadesList = getListFacultadesUnidades(facultadList, unidadListAsesorInterno);
        escuelaDepartamentoList = new ArrayList<EscuelaDepartamento>();

        mostrarmonto = true;
    }

    //registra la informacion conserniente a una beca
    public void guardarBeca() {
//        try {
//
//            Persona p = personaService.getBecarioByDoc(becario.getDuiPersona());
//            if (p == null) {
//                becario.setIdFacultad(facultadSelectedBecario.getIdFacultad());
//                becario.setIdCarrera(carreraSelected.getIdCarrera());
//                personaService.save(becario);
//
//                telefonoFijoBecario.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(FIJO));
//                telefonoFijoBecario.setIdPersona(becario);
//                telefonoService.save(telefonoFijoBecario);
//
//                telefonoCelularBecario.setIdPersona(becario);
//                telefonoCelularBecario.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(CELULAR));
//                telefonoService.save(telefonoCelularBecario);
//            } else {
//                becario.setIdPersona(p.getIdPersona());
//                becario.setIdCarrera(carreraSelected.getIdCarrera());
//                becario.setIdFacultad(facultadSelectedBecario.getIdFacultad());
//                personaService.merge(becario);
//
//                Telefono telFijo = telefonoService.getTelefonoByPersonaAndTipo(becario.getIdPersona(), 1);//fijo
//                if (telFijo != null) {
//                    telFijo.setNumeroTelefono(telefonoFijoBecario.getNumeroTelefono());
//                    telefonoService.merge(telFijo);
//                } else {
//                    telefonoFijoBecario.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(FIJO));
//                    telefonoFijoBecario.setIdPersona(becario);
//                    telefonoService.save(telefonoFijoBecario);
//                }
//
//                Telefono telCel = telefonoService.getTelefonoByPersonaAndTipo(becario.getIdPersona(), 2);//fijo
//                if (telCel != null) {
//                    telCel.setNumeroTelefono(telefonoCelularBecario.getNumeroTelefono());
//                    telefonoService.merge(telCel);
//                } else {
//                    telefonoCelularBecario.setIdPersona(becario);
//                    telefonoCelularBecario.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(CELULAR));
//                    telefonoService.save(telefonoCelularBecario);
//                }
//            }
//
//            asesorInterno.setIdFacultad(facultadSelectedAsesorInterno.getIdFacultad());
//            asesorInterno.setIdUnidad(unidadService.findById(unidadSelectedAsesorInterno.getIdUnidad()));
//            personaService.save(asesorInterno);
//
//            telefonoFijoAsesorInterno.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(FIJO));
//            telefonoFijoAsesorInterno.setIdPersona(asesorInterno);
//            telefonoService.save(telefonoFijoAsesorInterno);
//
//            telefonoCelularAsesorInterno.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(CELULAR));
//            telefonoCelularAsesorInterno.setIdPersona(asesorInterno);
//            telefonoService.save(telefonoCelularAsesorInterno);
//
//            asesorExterno.setIdOrganismo(organismoService.findById(entidadInstitucionSelected.getIdOrganismo()));
//            personaService.save(asesorExterno);
//
//            telefonoFijoAsesorExterno.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(FIJO));
//            telefonoFijoAsesorInterno.setIdPersona(asesorExterno);
//            telefonoService.save(telefonoFijoAsesorExterno);
//
//            telefonoCelularAsesorExterno.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(CELULAR));
//            telefonoCelularAsesorExterno.setIdPersona(asesorExterno);
//            telefonoService.save(telefonoCelularAsesorExterno);
//
//            beca.setIdPaisCooperante(paisCooperanteSelected.getIdPais());
//            beca.setIdProgramaBeca(programaBecaService.findById(programaBecaSelected.getIdPrograma()));
//            beca.setIdPaisDestino(paisDestinoSelected.getIdPais());
//            beca.setIdUniversidad(organismoService.findById(universidadSelected.getIdOrganismo()));
//            beca.setIdTipoModalidad(tipoModalidadBecaService.findById(tipoModalidaBecaSelected.getIdTipoModalidad()));
//            beca.setAnioGestion(Integer.parseInt(anio.trim()));
//            becaService.save(beca);
//
//            PersonaBecaPK personaBecaPKbecario = new PersonaBecaPK();
//            personaBecaPKbecario.setIdBeca(beca.getIdBeca());
//            personaBecaPKbecario.setIdPersona(becario.getIdPersona());
//
//            PersonaBeca personaBecaBecario = new PersonaBeca();
//            personaBecaBecario.setPersona(becario);
//            personaBecaBecario.setBeca(beca);
//            personaBecaBecario.setIdTipoPersona(tipoPersonaService.getTipoPersonaByNombre("BECARIO"));
//            personaBecaBecario.setPersonaBecaPK(personaBecaPKbecario);
//            beca.getPersonaBecaList().add(personaBecaBecario);
//
//            PersonaBecaPK personaBecaPKAsistenteI = new PersonaBecaPK();
//            personaBecaPKAsistenteI.setIdBeca(beca.getIdBeca());
//            personaBecaPKAsistenteI.setIdPersona(asesorInterno.getIdPersona());
//
//            PersonaBeca personaBecaAsistenteI = new PersonaBeca();
//            personaBecaAsistenteI.setPersona(asesorInterno);
//            personaBecaAsistenteI.setBeca(beca);
//            personaBecaAsistenteI.setIdTipoPersona(tipoPersonaService.getTipoPersonaByNombre("ASESOR INTERNO"));
//            personaBecaAsistenteI.setPersonaBecaPK(personaBecaPKAsistenteI);
//            beca.getPersonaBecaList().add(personaBecaAsistenteI);
//
//            PersonaBecaPK personaBecaPKAsistenteE = new PersonaBecaPK();
//            personaBecaPKAsistenteE.setIdBeca(beca.getIdBeca());
//            personaBecaPKAsistenteE.setIdPersona(asesorExterno.getIdPersona());
//
//            PersonaBeca personaBecaAsistenteE = new PersonaBeca();
//            personaBecaAsistenteE.setPersona(asesorExterno);
//            personaBecaAsistenteE.setBeca(beca);
//            personaBecaAsistenteE.setIdTipoPersona(tipoPersonaService.getTipoPersonaByNombre("ASESOR EXTERNO"));
//            personaBecaAsistenteE.setPersonaBecaPK(personaBecaPKAsistenteE);
//            beca.getPersonaBecaList().add(personaBecaAsistenteE);
//
//            becaService.merge(beca);
//
//            int becaid = beca.getIdBeca();
//
//            Beca becah = becaService.findById(becaid);
//            becah.getIdBeca();
//
//        } catch (Exception e) {
//            // FacesContext.getCurrentInstance().addMessage(, null);
//        }
    }

    public void mostrarCampo() {
        if (tipoModalidaBecaSelected.getIdTipoModalidad() == 1) {
            mostrarmonto = false;
        } else {
            mostrarmonto = true;
        }
    }

    public void getCarrerasByFacultad() {
        try {
            carreraList = carreraService.getCarrerasByFacultad(facultadSelectedBecario.getIdFacultad());
        } catch (Exception e) {
            carreraList = new ArrayList<Carrera>();
        }

    }

    public void getUniversidadesPorPais() {
        try {
            universidadList = organismoService.getOrganismosPorPaisYTipo(paisDestinoSelected.getIdPais(), 1);
        } catch (Exception e) {
            universidadList = new ArrayList<Organismo>();
        }

    }

    private Integer getYearOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer year = cal.get(Calendar.YEAR);
        return year;
    }

    private List<PojoFacultadesUnidades> getListFacultadesUnidades(List<Facultad> facs, List<Unidad> unidades) {

        List<PojoFacultadesUnidades> lista = new ArrayList<PojoFacultadesUnidades>();
        for (Facultad fac : facs) {
            PojoFacultadesUnidades pojo = new PojoFacultadesUnidades();
            pojo.setValue(fac.getIdFacultad() + ",1");
            pojo.setLabel(fac.getNombreFacultad());
            lista.add(pojo);
        }
        for (Unidad uni : unidades) {
            PojoFacultadesUnidades pojo = new PojoFacultadesUnidades();
            pojo.setValue(uni.getIdUnidad() + ",2");
            pojo.setLabel(uni.getNombreUnidad());
            lista.add(pojo);
        }
        return lista;
    }

    public void onFacUniChange() {
        if (facuniSelectded != "") {
            String[] partes = facuniSelectded.split(",");
            if ("1".equals(partes[1])) {
                escuelaDepartamentoList = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(Integer.parseInt(partes[0]));
            } else {
                escuelaDepartamentoList = new ArrayList<EscuelaDepartamento>();
            }
        } else {
            escuelaDepartamentoList = new ArrayList<EscuelaDepartamento>();
        }

    }

    public Persona getBecario() {
        return becario;
    }

    public void setBecario(Persona becario) {
        this.becario = becario;
    }

    public Persona getAsesorExterno() {
        return asesorExterno;
    }

    public void setAsesorExterno(Persona asesorExterno) {
        this.asesorExterno = asesorExterno;
    }

    public Persona getAsesorInterno() {
        return asesorInterno;
    }

    public void setAsesorInterno(Persona asesorInterno) {
        this.asesorInterno = asesorInterno;
    }

    public Facultad getFacultadSelectedBecario() {
        return facultadSelectedBecario;
    }

    public void setFacultadSelectedBecario(Facultad facultadSelectedBecario) {
        this.facultadSelectedBecario = facultadSelectedBecario;
    }

    public Telefono getTelefonoFijoBecario() {
        return telefonoFijoBecario;
    }

    public void setTelefonoFijoBecario(Telefono telefonoFijoBecario) {
        this.telefonoFijoBecario = telefonoFijoBecario;
    }

    public Telefono getTelefonoCelularBecario() {
        return telefonoCelularBecario;
    }

    public void setTelefonoCelularBecario(Telefono telefonoCelularBecario) {
        this.telefonoCelularBecario = telefonoCelularBecario;
    }

    public ProgramaBeca getProgramaBecaSelected() {
        return programaBecaSelected;
    }

    public void setProgramaBecaSelected(ProgramaBeca programaBecaSelected) {
        this.programaBecaSelected = programaBecaSelected;
    }

    public Pais getPaisCooperanteSelected() {
        return paisCooperanteSelected;
    }

    public void setPaisCooperanteSelected(Pais paisCooperanteSelected) {
        this.paisCooperanteSelected = paisCooperanteSelected;
    }

    public Pais getPaisDestinoSelected() {
        return paisDestinoSelected;
    }

    public void setPaisDestinoSelected(Pais paisDestinoSelected) {
        this.paisDestinoSelected = paisDestinoSelected;
    }

    public Organismo getUniversidadSelected() {
        return universidadSelected;
    }

    public void setUniversidadSelected(Organismo universidadSelected) {
        this.universidadSelected = universidadSelected;
    }

    public Organismo getEntidadInstitucionSelected() {
        return entidadInstitucionSelected;
    }

    public void setEntidadInstitucionSelected(Organismo entidadInstitucionSelected) {
        this.entidadInstitucionSelected = entidadInstitucionSelected;
    }

    public List<Facultad> getFacultadList() {
        return facultadList;
    }

    public void setFacultadList(List<Facultad> facultadList) {
        this.facultadList = facultadList;
    }

    public Beca getBeca() {
        return beca;
    }

    public void setBeca(Beca beca) {
        this.beca = beca;
    }

    public List<Pais> getPaisList() {
        return paisList;
    }

    public void setPaisList(List<Pais> paisList) {
        this.paisList = paisList;
    }

    public List<ProgramaBeca> getProgramaBecaList() {
        return programaBecaList;
    }

    public void setProgramaBecaList(List<ProgramaBeca> programaBecaList) {
        this.programaBecaList = programaBecaList;
    }

    public List<Organismo> getUniversidadList() {
        return universidadList;
    }

    public void setUniversidadList(List<Organismo> universidadList) {
        this.universidadList = universidadList;
    }

    public List<Organismo> getOrganismoList() {
        return organismoList;
    }

    public void setOrganismoList(List<Organismo> organismoList) {
        this.organismoList = organismoList;
    }

    public TipoModalidaBeca getTipoModalidaBecaSelected() {
        return tipoModalidaBecaSelected;
    }

    public void setTipoModalidaBecaSelected(TipoModalidaBeca tipoModalidaBecaSelected) {
        this.tipoModalidaBecaSelected = tipoModalidaBecaSelected;
    }

    public List<TipoModalidaBeca> getTipoModalidadBecaList() {
        return tipoModalidadBecaList;
    }

    public void setTipoModalidadBecaList(List<TipoModalidaBeca> tipoModalidadBecaList) {
        this.tipoModalidadBecaList = tipoModalidadBecaList;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public int getYearActual() {
        return yearActual;
    }

    public void setYearActual(int yearActual) {
        this.yearActual = yearActual;
    }

    public Facultad getFacultadSelectedAsesorInterno() {
        return facultadSelectedAsesorInterno;
    }

    public void setFacultadSelectedAsesorInterno(Facultad facultadSelectedAsesorInterno) {
        this.facultadSelectedAsesorInterno = facultadSelectedAsesorInterno;
    }

    public EscuelaDepartamento getEscuelaDeptoInterno() {
        return escuelaDeptoInterno;
    }

    public void setEscuelaDeptoInterno(EscuelaDepartamento escuelaDeptoInterno) {
        this.escuelaDeptoInterno = escuelaDeptoInterno;
    }

  
    public List<Unidad> getUnidadListAsesorInterno() {
        return unidadListAsesorInterno;
    }

    public void setUnidadListAsesorInterno(List<Unidad> unidadListAsesorInterno) {
        this.unidadListAsesorInterno = unidadListAsesorInterno;
    }

    public Telefono getTelefonoFijoAsesorInterno() {
        return telefonoFijoAsesorInterno;
    }

    public void setTelefonoFijoAsesorInterno(Telefono telefonoFijoAsesorInterno) {
        this.telefonoFijoAsesorInterno = telefonoFijoAsesorInterno;
    }

    public Telefono getTelefonoCelularAsesorInterno() {
        return telefonoCelularAsesorInterno;
    }

    public void setTelefonoCelularAsesorInterno(Telefono telefonoCelularAsesorInterno) {
        this.telefonoCelularAsesorInterno = telefonoCelularAsesorInterno;
    }

    public Telefono getTelefonoFijoAsesorExterno() {
        return telefonoFijoAsesorExterno;
    }

    public void setTelefonoFijoAsesorExterno(Telefono telefonoFijoAsesorExterno) {
        this.telefonoFijoAsesorExterno = telefonoFijoAsesorExterno;
    }

    public Telefono getTelefonoCelularAsesorExterno() {
        return telefonoCelularAsesorExterno;
    }

    public void setTelefonoCelularAsesorExterno(Telefono telefonoCelularAsesorExterno) {
        this.telefonoCelularAsesorExterno = telefonoCelularAsesorExterno;
    }

    public boolean isMostrarmonto() {
        return mostrarmonto;
    }

    public void setMostrarmonto(boolean mostrarmonto) {
        this.mostrarmonto = mostrarmonto;
    }

    public List<Carrera> getCarreraList() {
        return carreraList;
    }

    public void setCarreraList(List<Carrera> carreraList) {
        this.carreraList = carreraList;
    }

    public Carrera getCarreraSelected() {
        return carreraSelected;
    }

    public void setCarreraSelected(Carrera carreraSelected) {
        this.carreraSelected = carreraSelected;
    }

    public List<PojoFacultadesUnidades> getFacultadesUnidadesList() {
        return facultadesUnidadesList;
    }

    public void setFacultadesUnidadesList(List<PojoFacultadesUnidades> facultadesUnidadesList) {
        this.facultadesUnidadesList = facultadesUnidadesList;
    }

    public String getFacuniSelectded() {
        return facuniSelectded;
    }

    public void setFacuniSelectded(String facuniSelectded) {
        this.facuniSelectded = facuniSelectded;
    }

    public List<EscuelaDepartamento> getEscuelaDepartamentoList() {
        return escuelaDepartamentoList;
    }

    public void setEscuelaDepartamentoList(List<EscuelaDepartamento> escuelaDepartamentoList) {
        this.escuelaDepartamentoList = escuelaDepartamentoList;
    }

}
