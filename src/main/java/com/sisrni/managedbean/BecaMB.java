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
import com.sisrni.model.PersonaBeca;
import com.sisrni.model.PersonaBecaPK;
import com.sisrni.model.ProgramaBeca;
import com.sisrni.model.Telefono;
import com.sisrni.model.TipoBeca;
import com.sisrni.model.TipoCambio;
import com.sisrni.model.TipoModalidaBeca;
import com.sisrni.model.Unidad;
import com.sisrni.pojo.rpt.PojoBeca;
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
import com.sisrni.service.TipoBecaService;
import com.sisrni.service.TipoCambioService;
import com.sisrni.service.TipoModalidadBecaService;
import com.sisrni.service.TipoPersonaService;
import com.sisrni.service.TipoProyectoService;
import com.sisrni.service.TipoTelefonoService;
import com.sisrni.service.UnidadService;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.hibernate.SessionFactory;
import org.primefaces.context.RequestContext;
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
    private Organismo organismoCooperanteSelected;
    private TipoBeca tipoBecaSelected;
    private TipoCambio tipoCambioSelected;
    private int yearActual;
    private String anio;

    //para el referente interno
    private Persona asesorInterno;
    private Facultad facultadSelectedAsesorInterno;
    private EscuelaDepartamento escuelaDeptoInterno;
    private Telefono telefonoFijoAsesorInterno;
    private Telefono telefonoCelularAsesorInterno;
    private String facuniSelectded;

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
    private List<TipoBeca> tipoBecaList;
    private List<TipoCambio> tipoCambioList;

    private boolean mostrarmonto;

    //para buscar personas
    private String docBecarioSearch;
    private String docInternoSearch;
    private String docExternoSearch;

    private boolean existeBecario;
    private boolean existeInterno;
    private boolean existeExterno;

    //para listar becas
    private List<PojoBeca> becaTableList;

    //bandera para actualizar
    private Boolean actualizar;

    //para ocultar o mostrar tab
    private boolean tabInternoBoolean;
    private boolean mostrarTabInterno;

    private boolean tabExternoBoolean;
    private boolean mostrarTabExterno;

    private boolean noEstabaInterno;
    private boolean noEstabaExterno;

    private boolean esFacultad;

    //variables para buscadores
    private String tipoBusquedaBecario;
    private String tipoBusquedaAsesorInterno;
    private String tipoBusquedaAsesorExterno;

    private Boolean flagSearchDuiBecario;
    private Boolean flagSearchNombreBecario;
    private Boolean flagSearchEmailBecario;
    private Persona becarioAux;

    //variables para asesor interno
    private Boolean flagSearchDuiAsesorInterno;
    private Boolean flagSearchNombreAsesorInterno;
    private Boolean flagSearchEmailAsesorInterno;
    private Persona asesorInternoAux;

    private Boolean flagSearchDuiAsesorExterno;
    private Boolean flagSearchNombreAsesorExterno;
    private Boolean flagSearchEmailAsesorExterno;
    private Persona asesorExternoAux;

    private List<Persona> listAll;

    private Boolean renderNuevaPersonaBecarioButton;
    private Boolean renderActualizarPersonaBecarioButton;
    private Boolean renderNuevaPersonaInternaButton;
    private Boolean renderActualizarPersonaInternaButton;
    private Boolean renderNuevaPersonaExternaButton;
    private Boolean renderActualizarPersonaExternaButton;

    private Boolean disableBecarioInputs;
    private Boolean disableInternoInputs;
    private Boolean disableExternoInputs;

    private Boolean presionoNuevoBecario;
    private Boolean presionoActualizarBecario;
    private Boolean presionoNuevoInterno;
    private Boolean presionoActualizarInterno;
    private Boolean presionoNuevoExterno;
    private Boolean presionoActualizarExterno;

    private Boolean remplazarBecario;
    private Boolean remplazarInterno;
    private Boolean remplazarExterno;

    private Boolean desvinculoInterno;
    private Boolean desvinculoExterno;
    //Mascara de telefonos de personas externas
    private String codigoPais;
    private String mascaraTelefono;

    @Inject
    TipoBecaMB tipoBecaMB;
    @Inject
    ProgramaBecaMB programaBecaMB;
    @Inject
    OrganismoCooperanteMB organismoCooperanteMB;
    @Inject
    PaisMB paisMB;
    @Inject
    TipoCambioMB tipoCambioMB;

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

    @Autowired
    TipoCambioService tipoCambioService;

    @Autowired
    TipoBecaService tipoBecaService;

    @Autowired
    private SessionFactory sessionFactory;

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
        beca.setMontoInterno(BigDecimal.ZERO);
        beca.setMontoInterno(BigDecimal.ZERO);
        beca.setMontoTotal(BigDecimal.ZERO);
        paisCooperanteSelected = new Pais();
        programaBecaSelected = new ProgramaBeca();
        paisDestinoSelected = new Pais();
        universidadSelected = new Organismo();
        tipoModalidaBecaSelected = new TipoModalidaBeca();
        tipoBecaSelected = new TipoBeca();
        tipoCambioSelected = new TipoCambio();
        yearActual = getYearOfDate(new Date());
        anio = "";

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
//        paisList = paisService.findAll();
        paisList = paisService.getCountriesOrderByNameAsc();
        programaBecaList = programaBecaService.findAll();
        universidadList = new ArrayList<Organismo>();
        tipoModalidadBecaList = tipoModalidadBecaService.findAll();
        unidadListAsesorInterno = unidadService.findAll();
        organismoList = organismoService.findAll();
        facultadesUnidadesList = getListFacultadesUnidades(facultadList, unidadListAsesorInterno);
        escuelaDepartamentoList = new ArrayList<EscuelaDepartamento>();
        organismoCooperanteSelected = new Organismo();
        tipoBecaList = tipoBecaService.findAll();
        tipoCambioList = tipoCambioService.findAll();

        mostrarmonto = true;

        //para buscar personas
        docBecarioSearch = "";
        docInternoSearch = "";
        docExternoSearch = "";
        existeBecario = false;
        existeInterno = false;
        existeExterno = false;

        //para listar becas
        becaTableList = becaService.getBecas(0);

        //inicializar bandera para actualizar
        actualizar = Boolean.FALSE;
        tabInternoBoolean = Boolean.FALSE;
        mostrarTabInterno = Boolean.FALSE;

        tabExternoBoolean = Boolean.FALSE;
        mostrarTabExterno = Boolean.FALSE;
        esFacultad = Boolean.FALSE;
        listAll = new ArrayList<Persona>();

        //variables de buscadores
        banderasBecarioFalsas();
        becarioAux = new Persona();

        disableBecarioInputs = Boolean.TRUE;
        disableInternoInputs = Boolean.TRUE;
        disableExternoInputs = Boolean.TRUE;

        renderNuevaPersonaBecarioButton = Boolean.FALSE;
        renderActualizarPersonaBecarioButton = Boolean.FALSE;
        renderNuevaPersonaInternaButton = Boolean.FALSE;
        renderActualizarPersonaInternaButton = Boolean.FALSE;
        renderNuevaPersonaExternaButton = Boolean.FALSE;
        renderActualizarPersonaExternaButton = Boolean.FALSE;

        presionoNuevoBecario = Boolean.FALSE;
        presionoActualizarBecario = Boolean.FALSE;
        presionoNuevoInterno = Boolean.FALSE;
        presionoActualizarInterno = Boolean.FALSE;
        presionoNuevoExterno = Boolean.FALSE;
        presionoActualizarExterno = Boolean.FALSE;

        remplazarBecario = Boolean.FALSE;
        remplazarInterno = Boolean.FALSE;
        remplazarExterno = Boolean.FALSE;

        desvinculoInterno = Boolean.FALSE;
        desvinculoExterno = Boolean.FALSE;
        //Mascara de telenonos de personas externas
        codigoPais = "";
        mascaraTelefono = "";
    }

//registra la informacion conserniente a una beca
    public void guardarBeca() throws Exception {
        try {
            //guardando becario
            if ((presionoNuevoBecario == false && presionoActualizarBecario == false) || (presionoActualizarBecario == true && remplazarBecario == false) || !actualizar) {
                System.out.println("no hace nada");
            }
            if ((!actualizar && presionoNuevoBecario) || (!actualizar && presionoActualizarBecario && remplazarBecario) || actualizar) {
                Carrera carrera = carreraService.findById(carreraSelected.getIdCarrera());
                becario.setIdCarrera(carrera);
                becario.setActivo(Boolean.TRUE);
                becario.setExtranjero(Boolean.FALSE);
                becario.setPasaporte("-");
                //agregando telefono fijo
                telefonoFijoBecario.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(FIJO));
                telefonoFijoBecario.setIdPersona(becario);
                becario.getTelefonoList().add(telefonoFijoBecario);
                //agregando telefono celular
                telefonoCelularBecario.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(CELULAR));
                telefonoCelularBecario.setIdPersona(becario);
                becario.getTelefonoList().add(telefonoCelularBecario);
                becario.setIdOrganismo(organismoService.findById(1));
                if (existeBecario == true || actualizar == true) {
                    personaService.merge(becario);
                } else {
                    personaService.save(becario);
                }
            }

            //guardando datos del asesor interno
            if (mostrarTabInterno == true) {
                if ((presionoNuevoInterno == false && presionoActualizarInterno == false) || (presionoActualizarInterno == true && remplazarInterno == false)) {
                    System.out.println("no hace nada");
                }
                if ((!actualizar && presionoNuevoInterno) || (!actualizar && presionoActualizarInterno && remplazarInterno) || actualizar) {
                    String partes[] = facuniSelectded.split(",");
                    if (partes[1].equals("1")) {
                        //asesorInterno.setIdUnidad(new Unidad());
                        //asesorInterno.setIdCarrera(new Carrera());
                        asesorInterno.setIdEscuelaDepto(escuelaDepartamentoService.findById(escuelaDeptoInterno.getIdEscuelaDepto()));
                        asesorInterno.setIdOrganismo(organismoService.findById(1));
                    } else {

                        asesorInterno.setIdUnidad(unidadService.findById(Integer.parseInt(partes[0])));
                        //asesorInterno.setIdCarrera(new Carrera());
                        //asesorInterno.setIdEscuelaDepto(new EscuelaDepartamento());
                        asesorInterno.setIdOrganismo(organismoService.findById(1));
                    }
                    asesorInterno.setPasaporte("-");
                    asesorInterno.setActivo(Boolean.TRUE);
                    asesorInterno.setExtranjero(Boolean.FALSE);
                    //agregando telefono fijo
                    telefonoFijoAsesorInterno.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(FIJO));
                    telefonoFijoAsesorInterno.setIdPersona(asesorInterno);
                    asesorInterno.getTelefonoList().add(telefonoFijoAsesorInterno);
                    //agregando telefono celular
                    telefonoCelularAsesorInterno.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(CELULAR));
                    telefonoCelularAsesorInterno.setIdPersona(asesorInterno);
                    asesorInterno.getTelefonoList().add(telefonoCelularAsesorInterno);
                    if (existeInterno == true || (actualizar == true && noEstabaInterno == false)) {
                        personaService.merge(asesorInterno);
                    } else {
                        personaService.save(asesorInterno);
                    }

                } //fin del else deber ir aqui
            }

            //guardando asesor externo
            if (mostrarTabExterno) {
                if ((presionoNuevoExterno == false && presionoActualizarExterno == false) || (presionoActualizarExterno == true && remplazarExterno == false)) {
                    System.out.println("no hace nada");
                }
                if ((!actualizar && presionoNuevoExterno) || (!actualizar && presionoActualizarExterno && remplazarExterno) || actualizar) {
                    asesorExterno.setIdOrganismo(organismoService.findById(entidadInstitucionSelected.getIdOrganismo()));
                    asesorExterno.setDuiPersona("-");
                    asesorExterno.setActivo(Boolean.TRUE);
                    asesorExterno.setExtranjero(Boolean.TRUE);
                    //guardando telefono fijo
                    telefonoFijoAsesorExterno.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(FIJO));
                    telefonoFijoAsesorExterno.setIdPersona(asesorExterno);
                    asesorExterno.getTelefonoList().add(telefonoFijoAsesorExterno);
                    //guardando telefono celular
                    telefonoCelularAsesorExterno.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(CELULAR));
                    telefonoCelularAsesorExterno.setIdPersona(asesorExterno);
                    asesorExterno.getTelefonoList().add(telefonoCelularAsesorExterno);
                    if (existeExterno == true || (actualizar == true && noEstabaExterno == false)) {
                        personaService.merge(asesorExterno);
                    } else {
                        personaService.save(asesorExterno);
                    }
                }
            }
            //guardando datos de la beca
            TipoBeca auxtb = tipoBecaService.findById(tipoBecaSelected.getIdTipoBeca());
            if (auxtb != null) {
                beca.setIdTipoBeca(auxtb);
            }

            Organismo aux = organismoService.findById(organismoCooperanteSelected.getIdOrganismo());
            if (aux != null) {
                beca.setIdOrganismoCooperante(aux);
                beca.setIdPaisCooperante(aux.getIdPais());
            }
            beca.setIdProgramaBeca(programaBecaService.findById(programaBecaSelected.getIdPrograma()));
            beca.setIdPaisDestino(paisDestinoSelected.getIdPais());
            beca.setIdUniversidad(organismoService.findById(universidadSelected.getIdOrganismo()));
            beca.setIdTipoModalidad(tipoModalidadBecaService.findById(tipoModalidaBecaSelected.getIdTipoModalidad()));
            beca.setAnioGestion(Integer.parseInt(anio.trim()));

            TipoCambio auxTc = tipoCambioService.findById(tipoCambioSelected.getIdTipoCambio());
            beca.setMontoExterno(auxTc.getDolaresPorUnidad().multiply(beca.getMontoExterno()));
            beca.setMontoTotal(beca.getMontoExterno().add(beca.getMontoInterno()));

            if (actualizar == true) {
                becaService.merge(beca);
            } else {
                beca.setFechaIngreso(new Date());
                becaService.save(beca);
            }
            //vinculando becario a beca
            if (actualizar != true) {
                PersonaBecaPK personaBecaPKbecario = new PersonaBecaPK();
                personaBecaPKbecario.setIdBeca(beca.getIdBeca());
                personaBecaPKbecario.setIdPersona(becario.getIdPersona());

                PersonaBeca personaBecaBecario = new PersonaBeca();
                personaBecaBecario.setPersona(becario);
                personaBecaBecario.setBeca(beca);
                personaBecaBecario.setIdTipoPersona(tipoPersonaService.getTipoPersonaByNombre("BECARIO"));
                personaBecaBecario.setPersonaBecaPK(personaBecaPKbecario);
                beca.getPersonaBecaList().add(personaBecaBecario);
            }
            //vinculando asesor interno a beca
            if ((actualizar != true && mostrarTabInterno == true) || (actualizar == true && noEstabaInterno == true && mostrarTabInterno == true)) {
                PersonaBecaPK personaBecaPKAsistenteI = new PersonaBecaPK();
                personaBecaPKAsistenteI.setIdBeca(beca.getIdBeca());
                personaBecaPKAsistenteI.setIdPersona(asesorInterno.getIdPersona());

                PersonaBeca personaBecaAsistenteI = new PersonaBeca();
                personaBecaAsistenteI.setPersona(asesorInterno);
                personaBecaAsistenteI.setBeca(beca);
                personaBecaAsistenteI.setIdTipoPersona(tipoPersonaService.getTipoPersonaByNombre("ASESOR INTERNO"));
                personaBecaAsistenteI.setPersonaBecaPK(personaBecaPKAsistenteI);
                beca.getPersonaBecaList().add(personaBecaAsistenteI);
            }
            //vinculando el asesor externo a la beca
            if ((actualizar != true && mostrarTabExterno == true) || (actualizar == true && noEstabaExterno == true && mostrarTabExterno == true)) {
                PersonaBecaPK personaBecaPKAsistenteE = new PersonaBecaPK();
                personaBecaPKAsistenteE.setIdBeca(beca.getIdBeca());
                personaBecaPKAsistenteE.setIdPersona(asesorExterno.getIdPersona());

                PersonaBeca personaBecaAsistenteE = new PersonaBeca();
                personaBecaAsistenteE.setPersona(asesorExterno);
                personaBecaAsistenteE.setBeca(beca);
                personaBecaAsistenteE.setIdTipoPersona(tipoPersonaService.getTipoPersonaByNombre("ASESOR EXTERNO"));
                personaBecaAsistenteE.setPersonaBecaPK(personaBecaPKAsistenteE);
                beca.getPersonaBecaList().add(personaBecaAsistenteE);
            }

            //actualizando beca
            becaService.merge(beca);
            if (actualizar) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Registro modificado exitosamente."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Registro guardado exitosamente"));
            }
            inicializador();
            FacesContext.getCurrentInstance().getExternalContext().redirect("becaAdm.xhtml");
        } catch (Exception e) {
            if (actualizar) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error al modificar el registro."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error al guardar el registro"));
            }
        }
    }

    //pre guardar
    public void preGuardar() {
        try {
            if (presionoNuevoBecario == true) {
                if (mostrarTabInterno == true) {
                    preGuardarInterno();
                } else if (mostrarTabExterno == true) {
                    preGuardarExterno();
                } else {
                    guardarBeca();
                }
            } else if (presionoActualizarBecario == true) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dataChangeBecarioDlg').show();");
            } else {
                if (mostrarTabInterno == true) {
                    preGuardarInterno();
                } else if (mostrarTabExterno == true) {
                    preGuardarExterno();
                } else {
                    guardarBeca();
                }
            }
        } catch (Exception e) {
            //manejo de excepciones
        }
    }

    public void preGuardarInterno() throws Exception {
        if (presionoNuevoInterno == true) {
            if (mostrarTabExterno == true) {
                preGuardarExterno();
            } else {
                guardarBeca();
            }
        } else if (presionoActualizarInterno == true) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dataChangeInternoDlg').show();");
        } else {
            if (mostrarTabExterno == true) {
                preGuardarExterno();
            } else {
                guardarBeca();
            }

        }
    }

    public void preGuardarExterno() throws Exception {
        if (presionoNuevoExterno == true) {
            guardarBeca();
        } else if (presionoActualizarExterno == true) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dataChangeExternoDlg').show();");
        } else {
            guardarBeca();
        }
    }

    public void noRemplazarBecario() throws Exception {
        existeBecario = Boolean.TRUE;
        remplazarBecario = Boolean.FALSE;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dataChangeBecarioDlg').hide();");
        if (mostrarTabInterno == true) {
            preGuardarInterno();
        } else if (mostrarTabExterno == true) {
            preGuardarExterno();
        } else {
            guardarBeca();
        }
    }

    public void noRemplazarInterno() throws Exception {
        existeInterno = Boolean.TRUE;
        remplazarInterno = Boolean.FALSE;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dataChangeInternoDlg').hide();");
        if (mostrarTabExterno == true) {
            preGuardarExterno();
        } else {
            guardarBeca();
        }
    }

    public void noRemplazarExterno() throws Exception {
        existeExterno = Boolean.TRUE;
        remplazarExterno = Boolean.FALSE;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dataChangeExternoDlg').hide();");
        guardarBeca();
    }

    public void siRemplazarBecario() throws Exception {
        existeBecario = Boolean.TRUE;
        remplazarBecario = Boolean.TRUE;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dataChangeBecarioDlg').hide();");
        if (mostrarTabInterno == true) {
            preGuardarInterno();
        } else if (mostrarTabExterno == true) {
            preGuardarExterno();
        } else {
            guardarBeca();
        }
    }

    public void siRemplazarInterno() throws Exception {
        existeInterno = Boolean.TRUE;
        remplazarInterno = Boolean.TRUE;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dataChangeInternoDlg').hide();");
        if (mostrarTabExterno == true) {
            preGuardarExterno();
        } else {
            guardarBeca();
        }
    }

    public void siRemplazarExterno() throws Exception {
        existeExterno = Boolean.TRUE;
        remplazarExterno = Boolean.TRUE;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dataChangeExternoDlg').hide();");
        guardarBeca();
    }

    public void presionoNuevoBecario() {
        disableBecarioInputs = Boolean.FALSE;
        existeBecario = Boolean.FALSE;
        presionoNuevoBecario = Boolean.TRUE;
        presionoActualizarBecario = Boolean.FALSE;
    }

    public void presionoActualizarBecario() {
        disableBecarioInputs = Boolean.FALSE;
        presionoActualizarBecario = Boolean.TRUE;
        presionoNuevoBecario = Boolean.FALSE;
    }

    public void presionoNuevoInterno() {
        disableInternoInputs = Boolean.FALSE;
        presionoNuevoInterno = Boolean.TRUE;
    }

    public void presionoActualizarInterno() {
        disableInternoInputs = Boolean.FALSE;
        presionoActualizarInterno = Boolean.TRUE;
    }

    public void presionoNuevoExterno() {
        disableExternoInputs = Boolean.FALSE;
        presionoNuevoExterno = Boolean.TRUE;
    }

    public void presionoActualizarExterno() {
        disableExternoInputs = Boolean.FALSE;
        presionoActualizarExterno = Boolean.TRUE;
    }

    public void habilitarAutoBecario() {
        banderasBecarioFalsas();
        limpiarBecario();
        renderActualizarPersonaBecarioButton = Boolean.FALSE;
        renderNuevaPersonaBecarioButton = Boolean.FALSE;
        presionoActualizarBecario = Boolean.FALSE;
        presionoNuevoBecario = Boolean.FALSE;
        disableBecarioInputs = Boolean.TRUE;

        becarioAux = new Persona();
        if (tipoBusquedaBecario.equalsIgnoreCase("doc")) {
            flagSearchDuiBecario = Boolean.TRUE;
        }
        if (tipoBusquedaBecario.equalsIgnoreCase("nombre")) {
            flagSearchNombreBecario = Boolean.TRUE;
        }
        if (tipoBusquedaBecario.equalsIgnoreCase("email")) {
            flagSearchEmailBecario = Boolean.TRUE;
        }
    }

//Busca de persona interna
    public void habilitarAutoAsesorInterno() {
        limpiarAsesorInterno();
        banderasAsesorInternoFalsas();
        renderActualizarPersonaInternaButton = Boolean.FALSE;
        renderNuevaPersonaInternaButton = Boolean.FALSE;
        presionoActualizarInterno = Boolean.FALSE;
        presionoNuevoInterno = Boolean.FALSE;
        disableInternoInputs = Boolean.TRUE;

        asesorInternoAux = new Persona();
        if (tipoBusquedaAsesorInterno.equalsIgnoreCase("doc")) {
            flagSearchDuiAsesorInterno = Boolean.TRUE;
        }
        if (tipoBusquedaAsesorInterno.equalsIgnoreCase("nombre")) {
            flagSearchNombreAsesorInterno = Boolean.TRUE;
        }
        if (tipoBusquedaAsesorInterno.equalsIgnoreCase("email")) {
            flagSearchEmailAsesorInterno = Boolean.TRUE;
        }
    }

    public void habilitarAutoAsesorExterno() {
        limpiarAsesorExterno();
        banderasAsesorExternoFalsas();
        renderActualizarPersonaExternaButton = Boolean.FALSE;
        renderNuevaPersonaExternaButton = Boolean.FALSE;
        presionoActualizarExterno = Boolean.FALSE;
        presionoNuevoExterno = Boolean.FALSE;
        disableExternoInputs = Boolean.TRUE;

        asesorExternoAux = new Persona();
        if (tipoBusquedaAsesorExterno.equalsIgnoreCase("doc")) {
            flagSearchDuiAsesorExterno = Boolean.TRUE;
        }
        if (tipoBusquedaAsesorExterno.equalsIgnoreCase("nombre")) {
            flagSearchNombreAsesorExterno = Boolean.TRUE;
        }
        if (tipoBusquedaAsesorExterno.equalsIgnoreCase("email")) {
            flagSearchEmailAsesorExterno = Boolean.TRUE;
        }
    }

    public void banderasBecarioFalsas() {
        flagSearchDuiBecario = Boolean.FALSE;
        flagSearchNombreBecario = Boolean.FALSE;
        flagSearchEmailBecario = Boolean.FALSE;
    }

    public void banderasAsesorInternoFalsas() {
        flagSearchDuiAsesorInterno = Boolean.FALSE;
        flagSearchNombreAsesorInterno = Boolean.FALSE;
        flagSearchEmailAsesorInterno = Boolean.FALSE;
    }

    public void banderasAsesorExternoFalsas() {
        flagSearchDuiAsesorExterno = Boolean.FALSE;
        flagSearchNombreAsesorExterno = Boolean.FALSE;
        flagSearchEmailAsesorExterno = Boolean.FALSE;
    }

    public void changeInterno() {
        mostrarTabInterno = tabInternoBoolean ? Boolean.TRUE : Boolean.FALSE;
    }

    public void changeExterno() {
        mostrarTabExterno = tabExternoBoolean ? Boolean.TRUE : Boolean.FALSE;
    }

    public void cancelar() throws IOException {
        inicializador();
        FacesContext.getCurrentInstance().getExternalContext().redirect("becaAdm.xhtml");
    }

    public void crearNuevo() throws IOException {
        inicializador();
        FacesContext.getCurrentInstance().getExternalContext().redirect("registrarBeca.xhtml");
    }

    //Metodo que se encarga de mostrar mascara de personas externas en los telefonos
    public void onchangeListInstitucionPersona() {
        Organismo org = organismoService.findById(entidadInstitucionSelected.getIdOrganismo());
        codigoPais = paisService.findById(org.getIdPais()).getCodigoPais();
        mascaraTelefono = telefonoService.getMask(codigoPais);
    }

    public void preUpdate(Integer id) {
        try {
            Beca aux = becaService.findById(id);
            if (aux != null) {
                beca = aux;
                becario = getPersonaBeca(beca.getPersonaBecaList(), "BECARIO");
                asesorInterno = getPersonaBeca(beca.getPersonaBecaList(), "ASESOR INTERNO");
                if (asesorInterno == null) {
                    tabInternoBoolean = Boolean.FALSE;
                    mostrarTabInterno = Boolean.FALSE;
                    noEstabaInterno = true;
                } else {
                    tabInternoBoolean = Boolean.TRUE;
                    mostrarTabInterno = Boolean.TRUE;
                    disableInternoInputs = Boolean.FALSE;
                    noEstabaInterno = false;
                }
                asesorExterno = getPersonaBeca(beca.getPersonaBecaList(), "ASESOR EXTERNO");
                if (asesorExterno == null) {
                    tabExternoBoolean = Boolean.FALSE;
                    mostrarTabExterno = Boolean.FALSE;
                    noEstabaExterno = true;
                } else {
                    tabExternoBoolean = Boolean.TRUE;
                    mostrarTabExterno = Boolean.TRUE;
                    noEstabaExterno = false;
                    disableExternoInputs = Boolean.FALSE;
                }
                buscarBecario(becario.getEmailPersona());
                if (asesorInterno != null) {
                    buscarInterno(asesorInterno.getEmailPersona());
                } else {
                    asesorInterno = new Persona();
                }

                if (asesorExterno != null) {
                    buscarExterno(asesorExterno.getEmailPersona());
                } else {
                    asesorExterno = new Persona();
                }

                organismoCooperanteSelected = beca.getIdOrganismoCooperante();
                tipoBecaSelected = beca.getIdTipoBeca();
                tipoCambioSelected = tipoCambioService.findById(2);
                paisCooperanteSelected = paisService.findById(beca.getIdPaisCooperante());
                programaBecaSelected.setIdPrograma(beca.getIdProgramaBeca().getIdPrograma());
                paisDestinoSelected.setIdPais(beca.getIdPaisDestino());
                universidadSelected = beca.getIdUniversidad();
                getUniversidadesPorPais(beca.getIdPaisDestino());
                tipoModalidaBecaSelected = beca.getIdTipoModalidad();
                anio = beca.getAnioGestion().toString() + " ";
                if (tipoModalidaBecaSelected.getIdTipoModalidad() == 1) {
                    mostrarmonto = Boolean.FALSE;
                } else {
                    mostrarmonto = Boolean.TRUE;
                }

                actualizar = Boolean.TRUE;
                FacesContext.getCurrentInstance().getExternalContext().redirect("registrarBeca.xhtml");
            }
        } catch (Exception e) {
        }

    }

    public Persona getPersonaBeca(List<PersonaBeca> lista, String tipo) {
        Persona p = null;
        for (PersonaBeca per : lista) {
            if (per.getIdTipoPersona().getNombreTipoPersona().equalsIgnoreCase(tipo)) {
                return personaService.getByID(per.getPersona().getIdPersona());

            }
        }
        return p;
    }

    public void mostrarCampo() {
        if (tipoModalidaBecaSelected.getIdTipoModalidad() == 1) {
            mostrarmonto = false;
        } else {
            mostrarmonto = true;
            this.beca.setMontoInterno(BigDecimal.ZERO);
        }
    }

    public void getCarrerasByFacultad() {
        try {
            carreraList = carreraService.getCarrerasByFacultad(facultadSelectedBecario.getIdFacultad());
        } catch (Exception e) {
            carreraList = new ArrayList<Carrera>();
        }

    }

    public void desvincularInterno() {
        try {
            becaService.desvincularInterno(beca.getIdBeca(), asesorInterno.getIdPersona());
            tabInternoBoolean = Boolean.FALSE;
            mostrarTabInterno = Boolean.FALSE;
            noEstabaInterno = true;
            disableInternoInputs = Boolean.TRUE;
            limpiarAsesorInterno();
            banderasAsesorInternoFalsas();
            desvinculoInterno = Boolean.TRUE;
            asesorInternoAux = new Persona();
            preUpdate(beca.getIdBeca());

        } catch (Exception e) {

        }
    }

    public void desvincularExterno() {
        try {
            becaService.desvincularInterno(beca.getIdBeca(), asesorExterno.getIdPersona());
            tabExternoBoolean = Boolean.FALSE;
            mostrarTabExterno = Boolean.FALSE;
            noEstabaExterno = true;
            disableExternoInputs = Boolean.TRUE;
            limpiarAsesorExterno();
            banderasAsesorExternoFalsas();
            desvinculoExterno = Boolean.TRUE;
            asesorExternoAux = new Persona();
            preUpdate(beca.getIdBeca());
        } catch (Exception e) {

        }
    }

    public void confirmarDesvincularInterno() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dataDesvincularInternoDlg').show();");
    }

    public void confirmarDesvincularExterno() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dataDesvincularExternoDlg').show();");
    }

    public void getUniversidadesPorPais(Integer idPais) {
        try {
            universidadList = organismoService.getOrganismosPorPaisYTipo(idPais, 1);
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
                esFacultad = Boolean.TRUE;
                escuelaDepartamentoList = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(Integer.parseInt(partes[0]));
            } else {
                esFacultad = Boolean.FALSE;
                escuelaDepartamentoList = new ArrayList<EscuelaDepartamento>();
            }
        } else {
            escuelaDepartamentoList = new ArrayList<EscuelaDepartamento>();
        }

    }

    public void cambiarMontoBeca() {
        BigDecimal montoInterno = this.beca.getMontoInterno();
        BigDecimal montoExterno = this.beca.getMontoExterno();
        this.beca.setMontoTotal(montoInterno.add(montoExterno));

    }

    public void buscarBecario(String valior) {
        try {
            if (!valior.equalsIgnoreCase("")) {
                Persona aux = personaService.getBecarioByEmail(valior).get(0);
                if (aux != null) {
                    becario = aux;
                    telefonoFijoBecario = getTelefono(becario.getTelefonoList(), FIJO);
                    telefonoCelularBecario = getTelefono(becario.getTelefonoList(), CELULAR);
                    facultadSelectedBecario = becario.getIdCarrera().getIdFacultad();
                    carreraList = carreraService.getCarrerasByFacultad(facultadSelectedBecario.getIdFacultad());
                    carreraSelected = becario.getIdCarrera();
                    this.existeBecario = Boolean.TRUE;
                } else {
                    becario = new Persona();
                    telefonoFijoBecario = new Telefono();
                    telefonoCelularBecario = new Telefono();
                    facultadSelectedBecario = new Facultad();
                    carreraList = new ArrayList<Carrera>();
                    carreraSelected = new Carrera();
                    this.existeBecario = Boolean.FALSE;
                }
            }
        } catch (Exception e) {
        }
    }

    public void buscarBecarioSinParametro() {
        try {
            renderActualizarPersonaBecarioButton = Boolean.TRUE;
            limpiarBecario();
            if (becarioAux != null) {
                becario = becarioAux;
                telefonoFijoBecario = getTelefono(becario.getTelefonoList(), FIJO);
                telefonoCelularBecario = getTelefono(becario.getTelefonoList(), CELULAR);
                facultadSelectedBecario = becario.getIdCarrera().getIdFacultad();
                carreraList = carreraService.getCarrerasByFacultad(facultadSelectedBecario.getIdFacultad());
                carreraSelected = becario.getIdCarrera();
                this.existeBecario = Boolean.TRUE;
            } else {
                becario = new Persona();
                telefonoFijoBecario = new Telefono();
                telefonoCelularBecario = new Telefono();
                facultadSelectedBecario = new Facultad();
                carreraList = new ArrayList<Carrera>();
                carreraSelected = new Carrera();
                this.existeBecario = Boolean.FALSE;
            }

        } catch (Exception e) {
        }
    }

    public void buscarInternoSinParametro() {
        try {
            if (actualizar) {
                disableInternoInputs = Boolean.FALSE;
                renderActualizarPersonaInternaButton = Boolean.FALSE;
            } else {
                renderActualizarPersonaInternaButton = Boolean.TRUE;
            }
            limpiarAsesorInterno();
            if (asesorInternoAux != null) {
                asesorInterno = asesorInternoAux;
                telefonoFijoAsesorInterno = getTelefono(asesorInterno.getTelefonoList(), FIJO);
                telefonoCelularAsesorInterno = getTelefono(asesorInterno.getTelefonoList(), CELULAR);
                if (asesorInterno.getIdUnidad() == null || asesorInterno.getIdEscuelaDepto() == null) {
                    facuniSelectded = "";
                    escuelaDeptoInterno = new EscuelaDepartamento();
                    escuelaDepartamentoList = new ArrayList<EscuelaDepartamento>();
                }
                if (asesorInterno.getIdEscuelaDepto() != null) {
                    facuniSelectded = asesorInterno.getIdEscuelaDepto().getIdFacultad().getIdFacultad() + ",1";
                    escuelaDeptoInterno = asesorInterno.getIdEscuelaDepto();
                    escuelaDepartamentoList = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(asesorInterno.getIdEscuelaDepto().getIdFacultad().getIdFacultad());
                }
                if (asesorInterno.getIdUnidad() != null) {
                    facuniSelectded = asesorInterno.getIdUnidad().getIdUnidad() + ",2";
                    escuelaDeptoInterno = new EscuelaDepartamento();
                    escuelaDepartamentoList = new ArrayList<EscuelaDepartamento>();
                }
                existeInterno = Boolean.TRUE;
            } else {
                asesorInterno = new Persona();
                telefonoFijoAsesorInterno = new Telefono();
                telefonoCelularAsesorInterno = new Telefono();
                facuniSelectded = "";
                escuelaDeptoInterno = new EscuelaDepartamento();
                escuelaDepartamentoList = new ArrayList<EscuelaDepartamento>();
                this.existeBecario = Boolean.FALSE;
            }
        } catch (Exception e) {
        }
    }

    public void buscarExternoSinParametro() {
        try {
            renderActualizarPersonaExternaButton = Boolean.TRUE;
            limpiarAsesorExterno();
            if (asesorExternoAux != null) {
                asesorExterno = asesorExternoAux;
                telefonoFijoAsesorExterno = getTelefono(asesorExterno.getTelefonoList(), FIJO);
                telefonoCelularAsesorExterno = getTelefono(asesorExterno.getTelefonoList(), CELULAR);
                entidadInstitucionSelected = asesorExterno.getIdOrganismo();
                existeExterno = Boolean.TRUE;

            } else {
                asesorExterno = new Persona();
                telefonoFijoAsesorExterno = new Telefono();
                telefonoCelularAsesorExterno = new Telefono();
                entidadInstitucionSelected = new Organismo();
                existeExterno = Boolean.FALSE;
            }
        } catch (Exception e) {
        }
    }

    public void buscarExterno(String valior) {
        try {
            if (!valior.equalsIgnoreCase("")) {
                Persona aux = personaService.getReferenteExternoByEmail(valior).get(0);
                if (aux != null) {
                    asesorExterno = aux;
                    telefonoFijoAsesorExterno = getTelefono(asesorExterno.getTelefonoList(), FIJO);
                    telefonoCelularAsesorExterno = getTelefono(asesorExterno.getTelefonoList(), CELULAR);
                    entidadInstitucionSelected = asesorExterno.getIdOrganismo();
                    existeExterno = Boolean.TRUE;

                } else {
                    asesorExterno = new Persona();
                    telefonoFijoAsesorExterno = new Telefono();
                    telefonoCelularAsesorExterno = new Telefono();
                    entidadInstitucionSelected = new Organismo();
                    existeExterno = Boolean.FALSE;
                }
            }
        } catch (Exception e) {
        }
    }

    public void buscarInterno(String valior) {
        try {
            if (!valior.equalsIgnoreCase("")) {
                Persona aux = personaService.getReferenteInternoByEmail(valior).get(0);
                if (aux != null) {
                    asesorInterno = aux;
                    telefonoFijoAsesorInterno = getTelefono(asesorInterno.getTelefonoList(), FIJO);
                    telefonoCelularAsesorInterno = getTelefono(asesorInterno.getTelefonoList(), CELULAR);
                    if (asesorInterno.getIdUnidad() == null || asesorInterno.getIdEscuelaDepto() == null) {
                        facuniSelectded = "";
                        escuelaDeptoInterno = new EscuelaDepartamento();
                        escuelaDepartamentoList = new ArrayList<EscuelaDepartamento>();
                    }
                    if (asesorInterno.getIdEscuelaDepto() != null) {
                        facuniSelectded = asesorInterno.getIdEscuelaDepto().getIdFacultad().getIdFacultad() + ",1";
                        escuelaDeptoInterno = asesorInterno.getIdEscuelaDepto();
                        escuelaDepartamentoList = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(asesorInterno.getIdEscuelaDepto().getIdFacultad().getIdFacultad());
                    }
                    if (asesorInterno.getIdUnidad() != null) {
                        facuniSelectded = asesorInterno.getIdUnidad().getIdUnidad() + ",2";
                        escuelaDeptoInterno = new EscuelaDepartamento();
                        escuelaDepartamentoList = new ArrayList<EscuelaDepartamento>();
                    }
                    existeInterno = Boolean.TRUE;

                } else {
                    asesorInterno = new Persona();
                    telefonoFijoAsesorInterno = new Telefono();
                    telefonoCelularAsesorInterno = new Telefono();
                    facuniSelectded = "";
                    escuelaDeptoInterno = new EscuelaDepartamento();
                    escuelaDepartamentoList = new ArrayList<EscuelaDepartamento>();
                    this.existeBecario = Boolean.FALSE;
                }
            }
        } catch (Exception e) {
        }
    }

    //buscar persona becario
    public List<Persona> methodSearch(String query) {
        try {
            limpiarBecario();
            List<Persona> list = new ArrayList<Persona>();
            if (tipoBusquedaBecario.equalsIgnoreCase("nombre")) {
                listAll = personaService.getReferenteInternoByName(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    renderActualizarPersonaBecarioButton = Boolean.FALSE;
                    renderNuevaPersonaBecarioButton = Boolean.TRUE;
                    disableBecarioInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botones");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelBecario");
                } else {
                    renderNuevaPersonaBecarioButton = Boolean.FALSE;
                    disableBecarioInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botones");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelBecario");
                }
                return list;
            } else if (tipoBusquedaBecario.equalsIgnoreCase("email")) {
                listAll = personaService.getReferenteInternoByEmail(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    renderActualizarPersonaBecarioButton = Boolean.FALSE;
                    renderNuevaPersonaBecarioButton = Boolean.TRUE;
                    disableBecarioInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botones");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelBecario");
                } else {
                    renderNuevaPersonaBecarioButton = Boolean.FALSE;
                    disableBecarioInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botones");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelBecario");
                }
                return list;
            } else if (tipoBusquedaBecario.equalsIgnoreCase("doc")) {
                query = query.substring(0, 8) + "-" + query.substring(9);
                becarioAux = personaService.getPersonaByDui(query);
                if (becarioAux == null) {
                    limpiarBecario();
                } else {
                    boolean add = list.add(becarioAux);
                }
                if (list.isEmpty()) {
                    renderActualizarPersonaBecarioButton = Boolean.FALSE;
                    renderNuevaPersonaBecarioButton = Boolean.TRUE;
                    disableBecarioInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botones");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelBecario");
                } else {
                    renderNuevaPersonaBecarioButton = Boolean.FALSE;
                    disableBecarioInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botones");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelBecario");
                }
                return list;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //buscar asesor interno
    public List<Persona> methodSearchAsesorInterno(String query) {
        try {
            limpiarAsesorInterno();
            List<Persona> list = new ArrayList<Persona>();
            if (tipoBusquedaAsesorInterno.equalsIgnoreCase("nombre")) {
                listAll = personaService.getReferenteInternoByName(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    renderActualizarPersonaInternaButton = Boolean.FALSE;
                    renderNuevaPersonaInternaButton = Boolean.TRUE;
                    disableInternoInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botonesInterno");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelInterno");
                } else {
                    renderNuevaPersonaInternaButton = Boolean.FALSE;
                    disableInternoInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botonesInterno");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelInterno");
                }
                return list;
            } else if (tipoBusquedaAsesorInterno.equalsIgnoreCase("email")) {
                listAll = personaService.getReferenteInternoByEmail(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    renderActualizarPersonaInternaButton = Boolean.FALSE;
                    renderNuevaPersonaInternaButton = Boolean.TRUE;
                    disableInternoInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botonesInterno");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelInterno");
                } else {
                    renderNuevaPersonaInternaButton = Boolean.FALSE;
                    disableInternoInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botonesInterno");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelInterno");
                }
                return list;
            } else if (tipoBusquedaAsesorInterno.equalsIgnoreCase("doc")) {
                query = query.substring(0, 8) + "-" + query.substring(9);
                asesorInternoAux = personaService.getPersonaByDui(query);
                if (asesorInternoAux == null) {
                    limpiarAsesorInterno();
                } else {
                    boolean add = list.add(asesorInternoAux);
                }
                if (list.isEmpty()) {
                    renderActualizarPersonaInternaButton = Boolean.FALSE;
                    renderNuevaPersonaInternaButton = Boolean.TRUE;
                    disableInternoInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botonesInterno");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelInterno");
                } else {
                    renderNuevaPersonaInternaButton = Boolean.FALSE;
                    disableInternoInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botonesInterno");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelInterno");
                }
                return list;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //buscar asesor interno
    public List<Persona> methodSearchAsesorExterno(String query) {
        try {
            limpiarAsesorExterno();
            List<Persona> list = new ArrayList<Persona>();
            if (tipoBusquedaAsesorExterno.equalsIgnoreCase("nombre")) {
                listAll = personaService.getReferenteExternoByName(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    renderActualizarPersonaExternaButton = Boolean.FALSE;
                    renderNuevaPersonaExternaButton = Boolean.TRUE;
                    disableExternoInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botonesExterno");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelExterno");
                } else {
                    renderNuevaPersonaExternaButton = Boolean.FALSE;
                    disableExternoInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botonesExterno");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelExterno");
                }
                return list;
            } else if (tipoBusquedaAsesorExterno.equalsIgnoreCase("email")) {
                listAll = personaService.getReferenteInternoByEmail(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    renderActualizarPersonaExternaButton = Boolean.FALSE;
                    renderNuevaPersonaExternaButton = Boolean.TRUE;
                    disableExternoInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botonesExterno");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelExterno");
                } else {
                    renderNuevaPersonaExternaButton = Boolean.FALSE;
                    disableExternoInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botonesExterno");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelExterno");
                }
                return list;
            } else if (tipoBusquedaAsesorExterno.equalsIgnoreCase("doc")) {
                query = query.substring(0, 8) + "-" + query.substring(9);
                asesorExternoAux = personaService.getPersonaByDui(query);
                if (asesorExternoAux == null) {
                    limpiarAsesorInterno();
                } else {
                    boolean add = list.add(asesorExternoAux);
                }
                if (list.isEmpty()) {
                    renderActualizarPersonaExternaButton = Boolean.FALSE;
                    renderNuevaPersonaExternaButton = Boolean.TRUE;
                    disableExternoInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botonesExterno");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelExterno");
                } else {
                    renderNuevaPersonaExternaButton = Boolean.FALSE;
                    disableExternoInputs = Boolean.TRUE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:botonesExterno");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:panelExterno");
                }
                return list;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Telefono getTelefono(List<Telefono> lista, String tipo) {
        Telefono r = new Telefono();
        for (Telefono tel : lista) {
            if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(tipo)) {
                return tel;
            }
        }
        return r;
    }

    public void limpiarBecario() {
        becario = new Persona();
        telefonoFijoBecario = new Telefono();
        telefonoCelularBecario = new Telefono();
        facultadSelectedBecario = new Facultad();
        carreraSelected = new Carrera();
    }

    public void limpiarAsesorInterno() {
        asesorInterno = new Persona();
        telefonoFijoAsesorInterno = new Telefono();
        telefonoCelularAsesorInterno = new Telefono();
        facultadSelectedAsesorInterno = new Facultad();
        facuniSelectded = "";
        escuelaDeptoInterno = new EscuelaDepartamento();

    }

    public void limpiarAsesorExterno() {
        asesorExterno = new Persona();
        telefonoFijoAsesorExterno = new Telefono();
        telefonoCelularAsesorExterno = new Telefono();
        entidadInstitucionSelected = new Organismo();

    }

    //Metodos para agregar elementos nuevos en cada combobox
    public void addNewTipoBeca() {
        TipoBeca tipobeca = tipoBecaService.findById(tipoBecaSelected.getIdTipoBeca());
        if (tipobeca.getNombreTipoBeca().equals("Agregar Nuevo")) {
            tipoBecaMB.init();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('tipobecaDialog').show()");
            tipoBecaSelected = new TipoBeca();
        }
    }

    public void addNewProgramaBeca() {
        ProgramaBeca programabeca = programaBecaService.findById(programaBecaSelected.getIdPrograma());
        if (programabeca.getNombrePrograma().equals("Agregar Nuevo")) {
            programaBecaMB.init();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('programaBecaDialog').show()");
            programaBecaSelected = new ProgramaBeca();
        }
    }

    public void addNewOrganismo() {
        Organismo organismo = organismoService.findById(organismoCooperanteSelected.getIdOrganismo());
        if (organismo.getNombreOrganismo().equals("Agregar Nuevo")) {
            organismoCooperanteMB.init();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('organismoDialog').show()");
            organismoCooperanteSelected = new Organismo();
        }
    }

    public void addNewPais() {
        Pais pais = paisService.findById(paisDestinoSelected.getIdPais());
        if (pais.getNombrePais().equals("Agregar Nuevo")) {
            paisMB.init();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('paisDialog').show()");
            paisDestinoSelected = new Pais();
        } else {
            getUniversidadesPorPais(paisDestinoSelected.getIdPais());
            RequestContext.getCurrentInstance().update("formAdmin:acordion:universidadDestino");
        }
    }

    public void addNewTipoMoneda() {
        TipoCambio tipocambio = tipoCambioService.findById(tipoCambioSelected.getIdTipoCambio());
        if (tipocambio.getNombreDivisa().equals("Agregar Nuevo")) {
            tipoCambioMB.init();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('tipocambioDialog').show()");
            tipoCambioSelected = new TipoCambio();
        }
    }

    public void preEliminar(Integer becaId) {
        try {
            beca = becaService.findById(becaId);
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('eliminarBecaDialogo').show()");
        } catch (Exception e) {
        }
    }

    public void eliminarBeca() {
        try {
            becaService.eliminarIntermedias(beca);
            becaService.delete(beca);
            inicializador();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('eliminarBecaDialogo').hide()");

        } catch (Exception e) {
        }
    }

    public void cancelareEiminarBeca() {
        try {
            inicializador();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('eliminarBecaDialogo').hide()");
        } catch (Exception e) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Sets and Getters">
    public Persona getBecario() {
        return becario;
    }

    public boolean isNoEstabaInterno() {
        return noEstabaInterno;
    }

    public void setNoEstabaInterno(boolean noEstabaInterno) {
        this.noEstabaInterno = noEstabaInterno;
    }

    public boolean isNoEstabaExterno() {
        return noEstabaExterno;
    }

    public void setNoEstabaExterno(boolean noEstabaExterno) {
        this.noEstabaExterno = noEstabaExterno;
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

    public String getDocBecarioSearch() {
        return docBecarioSearch;
    }

    public void setDocBecarioSearch(String docBecarioSearch) {
        this.docBecarioSearch = docBecarioSearch;
    }

    public String getDocInternoSearch() {
        return docInternoSearch;
    }

    public void setDocInternoSearch(String docInternoSearch) {
        this.docInternoSearch = docInternoSearch;
    }

    public String getDocExternoSearch() {
        return docExternoSearch;
    }

    public void setDocExternoSearch(String docExternoSearch) {
        this.docExternoSearch = docExternoSearch;
    }

    public boolean isExisteBecario() {
        return existeBecario;
    }

    public void setExisteBecario(boolean existeBecario) {
        this.existeBecario = existeBecario;
    }

    public boolean isExisteInterno() {
        return existeInterno;
    }

    public void setExisteInterno(boolean existeInterno) {
        this.existeInterno = existeInterno;
    }

    public boolean isExisteExterno() {
        return existeExterno;
    }

    public void setExisteExterno(boolean existeExterno) {
        this.existeExterno = existeExterno;
    }

    public List<PojoBeca> getBecaTableList() {
        return becaTableList;
    }

    public void setBecaTableList(List<PojoBeca> becaTableList) {
        this.becaTableList = becaTableList;
    }

    public Boolean getActualizar() {
        return actualizar;
    }

    public void setActualizar(Boolean actualizar) {
        this.actualizar = actualizar;
    }

    public boolean isTabInternoBoolean() {
        return tabInternoBoolean;
    }

    public void setTabInternoBoolean(boolean tabInternoBoolean) {
        this.tabInternoBoolean = tabInternoBoolean;
    }

    public boolean isMostrarTabInterno() {
        return mostrarTabInterno;
    }

    public void setMostrarTabInterno(boolean mostrarTabInterno) {
        this.mostrarTabInterno = mostrarTabInterno;
    }

    public boolean isTabExternoBoolean() {
        return tabExternoBoolean;
    }

    public void setTabExternoBoolean(boolean tabExternoBoolean) {
        this.tabExternoBoolean = tabExternoBoolean;
    }

    public boolean isMostrarTabExterno() {
        return mostrarTabExterno;
    }

    public void setMostrarTabExterno(boolean mostrarTabExterno) {
        this.mostrarTabExterno = mostrarTabExterno;
    }

    public Organismo getOrganismoCooperanteSelected() {
        return organismoCooperanteSelected;
    }

    public void setOrganismoCooperanteSelected(Organismo organismoCooperanteSelected) {
        this.organismoCooperanteSelected = organismoCooperanteSelected;
    }

    public TipoCambio getTipoCambioSelected() {
        return tipoCambioSelected;
    }

    public void setTipoCambioSelected(TipoCambio tipoCambioSelected) {
        this.tipoCambioSelected = tipoCambioSelected;
    }

    public TipoBeca getTipoBecaSelected() {
        return tipoBecaSelected;
    }

    public void setTipoBecaSelected(TipoBeca tipoBecaSelected) {
        this.tipoBecaSelected = tipoBecaSelected;
    }

    public List<TipoBeca> getTipoBecaList() {
        return tipoBecaList;
    }

    public void setTipoBecaList(List<TipoBeca> tipoBecaList) {
        this.tipoBecaList = tipoBecaList;
    }

    public List<TipoCambio> getTipoCambioList() {
        return tipoCambioList;
    }

    public void setTipoCambioList(List<TipoCambio> tipoCambioList) {
        this.tipoCambioList = tipoCambioList;
    }

    public boolean isEsFacultad() {
        return esFacultad;
    }

    public void setEsFacultad(boolean esFacultad) {
        this.esFacultad = esFacultad;
    }

    public String getTipoBusquedaBecario() {
        return tipoBusquedaBecario;
    }

    public void setTipoBusquedaBecario(String tipoBusquedaBecario) {
        this.tipoBusquedaBecario = tipoBusquedaBecario;
    }

    public String getTipoBusquedaAsesorInterno() {
        return tipoBusquedaAsesorInterno;
    }

    public void setTipoBusquedaAsesorInterno(String tipoBusquedaAsesorInterno) {
        this.tipoBusquedaAsesorInterno = tipoBusquedaAsesorInterno;
    }

    public String getTipoBusquedaAsesorExterno() {
        return tipoBusquedaAsesorExterno;
    }

    public void setTipoBusquedaAsesorExterno(String tipoBusquedaAsesorExterno) {
        this.tipoBusquedaAsesorExterno = tipoBusquedaAsesorExterno;
    }

    public boolean isFlagSearchDuiBecario() {
        return flagSearchDuiBecario;
    }

    public void setFlagSearchDuiBecario(boolean flagSearchDuiBecario) {
        this.flagSearchDuiBecario = flagSearchDuiBecario;
    }

    public boolean isFlagSearchNombreBecario() {
        return flagSearchNombreBecario;
    }

    public void setFlagSearchNombreBecario(boolean flagSearchNombreBecario) {
        this.flagSearchNombreBecario = flagSearchNombreBecario;
    }

    public boolean isFlagSearchEmailBecario() {
        return flagSearchEmailBecario;
    }

    public void setFlagSearchEmailBecario(boolean flagSearchEmailBecario) {
        this.flagSearchEmailBecario = flagSearchEmailBecario;
    }

    public Persona getBecarioAux() {
        return becarioAux;
    }

    public void setBecarioAux(Persona becarioAux) {
        this.becarioAux = becarioAux;
    }

    public Boolean getFlagSearchDuiAsesorInterno() {
        return flagSearchDuiAsesorInterno;
    }

    public void setFlagSearchDuiAsesorInterno(Boolean flagSearchDuiAsesorInterno) {
        this.flagSearchDuiAsesorInterno = flagSearchDuiAsesorInterno;
    }

    public Boolean getFlagSearchNombreAsesorInterno() {
        return flagSearchNombreAsesorInterno;
    }

    public void setFlagSearchNombreAsesorInterno(Boolean flagSearchNombreAsesorInterno) {
        this.flagSearchNombreAsesorInterno = flagSearchNombreAsesorInterno;
    }

    public Boolean getFlagSearchEmailAsesorInterno() {
        return flagSearchEmailAsesorInterno;
    }

    public void setFlagSearchEmailAsesorInterno(Boolean flagSearchEmailAsesorInterno) {
        this.flagSearchEmailAsesorInterno = flagSearchEmailAsesorInterno;
    }

    public Persona getAsesorInternoAux() {
        return asesorInternoAux;
    }

    public void setAsesorInternoAux(Persona asesorInternoAux) {
        this.asesorInternoAux = asesorInternoAux;
    }

    public Boolean getFlagSearchDuiAsesorExterno() {
        return flagSearchDuiAsesorExterno;
    }

    public void setFlagSearchDuiAsesorExterno(Boolean flagSearchDuiAsesorExterno) {
        this.flagSearchDuiAsesorExterno = flagSearchDuiAsesorExterno;
    }

    public Boolean getFlagSearchNombreAsesorExterno() {
        return flagSearchNombreAsesorExterno;
    }

    public void setFlagSearchNombreAsesorExterno(Boolean flagSearchNombreAsesorExterno) {
        this.flagSearchNombreAsesorExterno = flagSearchNombreAsesorExterno;
    }

    public Boolean getFlagSearchEmailAsesorExterno() {
        return flagSearchEmailAsesorExterno;
    }

    public void setFlagSearchEmailAsesorExterno(Boolean flagSearchEmailAsesorExterno) {
        this.flagSearchEmailAsesorExterno = flagSearchEmailAsesorExterno;
    }

    public Persona getAsesorExternoAux() {
        return asesorExternoAux;
    }

    public void setAsesorExternoAux(Persona asesorExternoAux) {
        this.asesorExternoAux = asesorExternoAux;
    }

// </editor-fold>
    public Boolean getRenderNuevaPersonaBecarioButton() {
        return renderNuevaPersonaBecarioButton;
    }

    public void setRenderNuevaPersonaBecarioButton(Boolean renderNuevaPersonaBecarioButton) {
        this.renderNuevaPersonaBecarioButton = renderNuevaPersonaBecarioButton;
    }

    public Boolean getRenderActualizarPersonaBecarioButton() {
        return renderActualizarPersonaBecarioButton;
    }

    public void setRenderActualizarPersonaBecarioButton(Boolean renderActualizarPersonaBecarioButton) {
        this.renderActualizarPersonaBecarioButton = renderActualizarPersonaBecarioButton;
    }

    public Boolean getDisableBecarioInputs() {
        return disableBecarioInputs;
    }

    public void setDisableBecarioInputs(Boolean disableBecarioInputs) {
        this.disableBecarioInputs = disableBecarioInputs;
    }

    public Boolean getPresionoNuevoBecario() {
        return presionoNuevoBecario;
    }

    public void setPresionoNuevoBecario(Boolean presionoNuevoBecario) {
        this.presionoNuevoBecario = presionoNuevoBecario;
    }

    public Boolean getPresionoActualizarBecario() {
        return presionoActualizarBecario;
    }

    public void setPresionoActualizarBecario(Boolean presionoActualizarBecario) {
        this.presionoActualizarBecario = presionoActualizarBecario;
    }

    public Boolean getRemplazarBecario() {
        return remplazarBecario;
    }

    public void setRemplazarBecario(Boolean remplazarBecario) {
        this.remplazarBecario = remplazarBecario;
    }

    public Boolean getFlagSearchDuiBecario() {
        return flagSearchDuiBecario;
    }

    public void setFlagSearchDuiBecario(Boolean flagSearchDuiBecario) {
        this.flagSearchDuiBecario = flagSearchDuiBecario;
    }

    public Boolean getFlagSearchNombreBecario() {
        return flagSearchNombreBecario;
    }

    public void setFlagSearchNombreBecario(Boolean flagSearchNombreBecario) {
        this.flagSearchNombreBecario = flagSearchNombreBecario;
    }

    public Boolean getFlagSearchEmailBecario() {
        return flagSearchEmailBecario;
    }

    public void setFlagSearchEmailBecario(Boolean flagSearchEmailBecario) {
        this.flagSearchEmailBecario = flagSearchEmailBecario;
    }

    public Boolean getRenderNuevaPersonaInternaButton() {
        return renderNuevaPersonaInternaButton;
    }

    public void setRenderNuevaPersonaInternaButton(Boolean renderNuevaPersonaInternaButton) {
        this.renderNuevaPersonaInternaButton = renderNuevaPersonaInternaButton;
    }

    public Boolean getRenderActualizarPersonaInternaButton() {
        return renderActualizarPersonaInternaButton;
    }

    public void setRenderActualizarPersonaInternaButton(Boolean renderActualizarPersonaInternaButton) {
        this.renderActualizarPersonaInternaButton = renderActualizarPersonaInternaButton;
    }

    public Boolean getRenderNuevaPersonaExternaButton() {
        return renderNuevaPersonaExternaButton;
    }

    public void setRenderNuevaPersonaExternaButton(Boolean renderNuevaPersonaExternaButton) {
        this.renderNuevaPersonaExternaButton = renderNuevaPersonaExternaButton;
    }

    public Boolean getRenderActualizarPersonaExternaButton() {
        return renderActualizarPersonaExternaButton;
    }

    public void setRenderActualizarPersonaExternaButton(Boolean renderActualizarPersonaExternaButton) {
        this.renderActualizarPersonaExternaButton = renderActualizarPersonaExternaButton;
    }

    public Boolean getDisableInternoInputs() {
        return disableInternoInputs;
    }

    public void setDisableInternoInputs(Boolean disableInternoInputs) {
        this.disableInternoInputs = disableInternoInputs;
    }

    public Boolean getDisableExternoInputs() {
        return disableExternoInputs;
    }

    public void setDisableExternoInputs(Boolean disableExternoInputs) {
        this.disableExternoInputs = disableExternoInputs;
    }

    public Boolean getPresionoNuevoInterno() {
        return presionoNuevoInterno;
    }

    public void setPresionoNuevoInterno(Boolean presionoNuevoInterno) {
        this.presionoNuevoInterno = presionoNuevoInterno;
    }

    public Boolean getPresionoActualizarInterno() {
        return presionoActualizarInterno;
    }

    public void setPresionoActualizarInterno(Boolean presionoActualizarInterno) {
        this.presionoActualizarInterno = presionoActualizarInterno;
    }

    public Boolean getPresionoNuevoExterno() {
        return presionoNuevoExterno;
    }

    public void setPresionoNuevoExterno(Boolean presionoNuevoExterno) {
        this.presionoNuevoExterno = presionoNuevoExterno;
    }

    public Boolean getPresionoActualizarExterno() {
        return presionoActualizarExterno;
    }

    public void setPresionoActualizarExterno(Boolean presionoActualizarExterno) {
        this.presionoActualizarExterno = presionoActualizarExterno;
    }

    public Boolean getRemplazarInterno() {
        return remplazarInterno;
    }

    public void setRemplazarInterno(Boolean remplazarInterno) {
        this.remplazarInterno = remplazarInterno;
    }

    public Boolean getRemplazarExterno() {
        return remplazarExterno;
    }

    public void setRemplazarExterno(Boolean remplazarExterno) {
        this.remplazarExterno = remplazarExterno;
    }

    public Boolean getDesvinculoInterno() {
        return desvinculoInterno;
    }

    public void setDesvinculoInterno(Boolean desvinculoInterno) {
        this.desvinculoInterno = desvinculoInterno;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getMascaraTelefono() {
        return mascaraTelefono;
    }

    public void setMascaraTelefono(String mascaraTelefono) {
        this.mascaraTelefono = mascaraTelefono;
    }

}
