//package com.sisrni.managedbean;
//
//import com.sisrni.model.CategoriaMovilidad;
//import com.sisrni.model.Movilidad;
//import com.sisrni.model.Pais;
//import com.sisrni.model.Persona;
//import com.sisrni.model.ProgramaMovilidad;
//import com.sisrni.model.Telefono;
//import com.sisrni.model.TipoMovilidad;
//import com.sisrni.model.Universidad;
//import com.sisrni.service.CategoriaMovilidadService;
//import com.sisrni.service.MovilidadService;
//import com.sisrni.service.PaisService;
//import com.sisrni.service.PersonaService;
//import com.sisrni.service.ProgramaMovilidadService;
//import com.sisrni.service.TipoMovilidadService;
//import com.sisrni.service.UniversidadService;
//import java.util.Date;
//import java.util.List;
//import javax.annotation.PostConstruct;
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;
//import javax.swing.JOptionPane;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//
///**
// *
// * @author Usuario
// */
//@Named(value = "registrarMovilidadMB")
//@ViewScoped
//public class registrarMovilidadMB {
//
//    //Variables
//    private Integer tipoMovilidadSelected;
//    private Integer categoriaMovilidadSelected;
//    private Integer paisOrigenSelected;
//    private Integer institucionOrigenSelected;
//    private Integer paisDestinoSelected;
//    private Integer institucionDestinoSelected;
//    private Boolean entregaInformeSelected;
//    private Boolean obsequioSelected;
//    private Boolean mostrarSaliente, mostrarEntrante;
//
//    private Date fechaInicioSelected;
//    private Date fechaFinSelected;
//
//    private ProgramaMovilidad programaMovilidadSelected;
//    private ProgramaMovilidad programaMovilidad;
//    private TipoMovilidad tipoMovilidad;
//    private Movilidad movilidad;
//    private CategoriaMovilidad categoriaMovilidad;
//    private Persona personaMovilidad;
//    private Persona personaMovilidadGenerico;
//    
//    private Telefono telFijoPersonaMovilidad;
//    private Telefono telCelPersonaMovilidad;
//    private Telefono faxPersonaMovilidad;
//
//    private List<ProgramaMovilidad> listProgramaMovilidad;
//    private List<TipoMovilidad> listTipoMovilidad;
//    private List<CategoriaMovilidad> listCategoriaMovilidad;
//    private List<Pais> listPaisOrigenMovilidad;
//    private List<Universidad> listUniversidad;
//    private List<Pais> listPaisDestinoMovilidad;
//    private List<Universidad> listUniversidadDestino;
//    private List<Persona> listPersonaMovilidad;
//
//    //Services
//    @Autowired
//    @Qualifier(value = "programaMovilidadService")
//    private ProgramaMovilidadService programaMovilidadService;
//
//    @Autowired
//    @Qualifier(value = "tipoMovilidadService")
//    private TipoMovilidadService tipoMovilidadService;
//
//    @Autowired
//    @Qualifier(value = "categoriaMovilidadService")
//    private CategoriaMovilidadService categoriaMovilidadService;
//
//    @Autowired
//    @Qualifier(value = "paisService")
//    private PaisService paisService;
//
//    @Autowired
//    @Qualifier(value = "universidadService")
//    private UniversidadService universidadService;
//
//    @Autowired
//    @Qualifier(value = "movilidadService")
//    private MovilidadService movilidadService;
//    
//    @Autowired
//    @Qualifier(value = "personaService")
//    private PersonaService personaService;
//
//    /**
//     * Constructor
//     */
//    public registrarMovilidadMB() {
//    }
//
//    /**
//     * Post constructor
//     */
//    @PostConstruct
//    public void init() {
//        cargarMovilidadPersona();
//    }
//
//    /**
//     * Metodo inicializador de variables y objetos
//     */
//    public void cargarMovilidadPersona() {
//        programaMovilidadSelected = new ProgramaMovilidad();
//        movilidad = new Movilidad();
//        tipoMovilidad = new TipoMovilidad();
//        tipoMovilidadSelected = null;
//        categoriaMovilidadSelected = null;
//        categoriaMovilidad = new CategoriaMovilidad();
//        paisOrigenSelected = null;
//        institucionOrigenSelected = null;
//        paisDestinoSelected = null;
//        institucionDestinoSelected = null;
//        entregaInformeSelected = null;
//        obsequioSelected = null;
//        personaMovilidad = new Persona();
//        personaMovilidadGenerico = new Persona();
//
//        fechaInicioSelected = null;
//        fechaFinSelected = null;
//        //mostrarEntrante = false;
//        //mostrarSaliente = false;
//        
//        telFijoPersonaMovilidad = new Telefono();
//        telCelPersonaMovilidad = new Telefono();
//        faxPersonaMovilidad = new Telefono();
//
//        listProgramaMovilidad = programaMovilidadService.findAll();
//        listTipoMovilidad = tipoMovilidadService.findAll();
//        listCategoriaMovilidad = categoriaMovilidadService.findAll();
//        listPaisOrigenMovilidad = paisService.findAll();
//        listUniversidad = universidadService.findAll();
//        listPaisDestinoMovilidad = paisService.findAll();
//        listUniversidadDestino = universidadService.findAll();
//        listPersonaMovilidad = personaService.findAll();
//
//    }
//
//    public void onchangeProgramaMovilidadList() {
//        try {
//            if (programaMovilidadSelected != null) {
//                programaMovilidad = programaMovilidadService.findById(programaMovilidadSelected.getIdProgramaMovilidad());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void onchangeTipoMovilidadList() {
//        try {
//            
//            if (tipoMovilidadSelected != null) {
//                tipoMovilidad = tipoMovilidadService.findById(tipoMovilidadSelected);
//                if(tipoMovilidadSelected ==1){
//                   
//                    mostrarEntrante = true;
//                    mostrarSaliente = false;
//                }else{
//                    mostrarEntrante = false;
//                    mostrarSaliente = true;
//                }
//                
//            }else {
//                tipoMovilidad = null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void onchangeCategoriaMovilidadList() {
//        try {
//            if (categoriaMovilidadSelected != null) {
//                categoriaMovilidad = categoriaMovilidadService.findById(categoriaMovilidadSelected);
//            }else{
//               categoriaMovilidad = null; 
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public void onchangeListPersonaMovilidad(){
//        try{
//            if(personaMovilidad != null){
//                personaMovilidadGenerico = personaMovilidad;
//                personaMovilidad = new Persona();
//            }
//        }catch(Exception e){
//            
//        }
//    }
//
//    public void crearMovilidad() {
//        try {
//            movilidad.setIdProgramaMovilidad(programaMovilidad);
//            movilidad.setIdTipoMovilidad(tipoMovilidad);
//            movilidad.setIdCategoria(categoriaMovilidad);
//            movilidad.setIdPaisOrigen(paisOrigenSelected);
//            movilidad.setIdUniversidadOrigen(institucionOrigenSelected);
//            movilidad.setIdPaisDestino(paisDestinoSelected);
//            movilidad.setFechaInicio(fechaInicioSelected);
//            movilidad.setFechaFin(fechaFinSelected);
//            
//            movilidad.setEntregaDeInforme(entregaInformeSelected);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void guardarMovilidadPersona() {
//        try {
//            crearMovilidad();
//            movilidadService.save(movilidad);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    //GETTER Y SETTER
//    public List<ProgramaMovilidad> getListProgramaMovilidad() {
//        return listProgramaMovilidad;
//    }
//
//    public void setListProgramaMovilidad(List<ProgramaMovilidad> listProgramaMovilidad) {
//        this.listProgramaMovilidad = listProgramaMovilidad;
//    }
//
//    public ProgramaMovilidad getProgramaMovilidadSelected() {
//        return programaMovilidadSelected;
//    }
//
//    public void setProgramaMovilidadSelected(ProgramaMovilidad programaMovilidadSelected) {
//        this.programaMovilidadSelected = programaMovilidadSelected;
//    }
//
//    public ProgramaMovilidad getProgramaMovilidad() {
//        return programaMovilidad;
//    }
//
//    public void setProgramaMovilidad(ProgramaMovilidad programaMovilidad) {
//        this.programaMovilidad = programaMovilidad;
//    }
//
//    public Movilidad getMovilidad() {
//        return movilidad;
//    }
//
//    public void setMovilidad(Movilidad movilidad) {
//        this.movilidad = movilidad;
//    }
//
//    public List<TipoMovilidad> getListTipoMovilidad() {
//        return listTipoMovilidad;
//    }
//
//    public void setListTipoMovilidad(List<TipoMovilidad> listTipoMovilidad) {
//        this.listTipoMovilidad = listTipoMovilidad;
//    }
//
//    public List<CategoriaMovilidad> getListCategoriaMovilidad() {
//        return listCategoriaMovilidad;
//    }
//
//    public void setListCategoriaMovilidad(List<CategoriaMovilidad> listCategoriaMovilidad) {
//        this.listCategoriaMovilidad = listCategoriaMovilidad;
//    }
//
//    public Integer getTipoMovilidadSelected() {
//        return tipoMovilidadSelected;
//    }
//
//    public void setTipoMovilidadSelected(Integer tipoMovilidadSelected) {
//        this.tipoMovilidadSelected = tipoMovilidadSelected;
//    }
//
//    public Integer getCategoriaMovilidadSelected() {
//        return categoriaMovilidadSelected;
//    }
//
//    public void setCategoriaMovilidadSelected(Integer categoriaMovilidadSelected) {
//        this.categoriaMovilidadSelected = categoriaMovilidadSelected;
//    }
//
//    public List<Pais> getListPaisOrigenMovilidad() {
//        return listPaisOrigenMovilidad;
//    }
//
//    public void setListPaisOrigenMovilidad(List<Pais> listPaisOrigenMovilidad) {
//        this.listPaisOrigenMovilidad = listPaisOrigenMovilidad;
//    }
//
//    public Integer getPaisOrigenSelected() {
//        return paisOrigenSelected;
//    }
//
//    public void setPaisOrigenSelected(Integer paisOrigenSelected) {
//        this.paisOrigenSelected = paisOrigenSelected;
//    }
//
//    public List<Universidad> getListUniversidad() {
//        return listUniversidad;
//    }
//
//    public void setListUniversidad(List<Universidad> listUniversidad) {
//        this.listUniversidad = listUniversidad;
//    }
//
//    public Integer getInstitucionOrigenSelected() {
//        return institucionOrigenSelected;
//    }
//
//    public void setInstitucionOrigenSelected(Integer institucionOrigenSelected) {
//        this.institucionOrigenSelected = institucionOrigenSelected;
//    }
//
//    public Integer getPaisDestinoSelected() {
//        return paisDestinoSelected;
//    }
//
//    public void setPaisDestinoSelected(Integer paisDestinoSelected) {
//        this.paisDestinoSelected = paisDestinoSelected;
//    }
//
//    public Integer getInstitucionDestinoSelected() {
//        return institucionDestinoSelected;
//    }
//
//    public void setInstitucionDestinoSelected(Integer institucionDestinoSelected) {
//        this.institucionDestinoSelected = institucionDestinoSelected;
//    }
//
//    public List<Pais> getListPaisDestinoMovilidad() {
//        return listPaisDestinoMovilidad;
//    }
//
//    public void setListPaisDestinoMovilidad(List<Pais> listPaisDestinoMovilidad) {
//        this.listPaisDestinoMovilidad = listPaisDestinoMovilidad;
//    }
//
//    public List<Universidad> getListUniversidadDestino() {
//        return listUniversidadDestino;
//    }
//
//    public void setListUniversidadDestino(List<Universidad> listUniversidadDestino) {
//        this.listUniversidadDestino = listUniversidadDestino;
//    }
//
//    public Date getFechaInicioSelected() {
//        return fechaInicioSelected;
//    }
//
//    public void setFechaInicioSelected(Date fechaInicioSelected) {
//        this.fechaInicioSelected = fechaInicioSelected;
//    }
//
//    public Date getFechaFinSelected() {
//        return fechaFinSelected;
//    }
//
//    public void setFechaFinSelected(Date fechaFinSelected) {
//        this.fechaFinSelected = fechaFinSelected;
//    }
//
//    public Boolean getEntregaInformeSelected() {
//        return entregaInformeSelected;
//    }
//
//    public void setEntregaInformeSelected(Boolean entregaInformeSelected) {
//        this.entregaInformeSelected = entregaInformeSelected;
//    }
//
//    public List<Persona> getListPersonaMovilidad() {
//        return listPersonaMovilidad;
//    }
//
//    public void setListPersonaMovilidad(List<Persona> listPersonaMovilidad) {
//        this.listPersonaMovilidad = listPersonaMovilidad;
//    }
//
//    public Boolean getMostrarSaliente() {
//        return mostrarSaliente;
//    }
//
//    public void setMostrarSaliente(Boolean mostrarSaliente) {
//        this.mostrarSaliente = mostrarSaliente;
//    }
//
//    public Boolean getMostrarEntrante() {
//        return mostrarEntrante;
//    }
//
//    public void setMostrarEntrante(Boolean mostrarEntrante) {
//        this.mostrarEntrante = mostrarEntrante;
//    }
//
//    public Persona getPersonaMovilidad() {
//        return personaMovilidad;
//    }
//
//    public void setPersonaMovilidad(Persona personaMovilidad) {
//        this.personaMovilidad = personaMovilidad;
//    }
//
//    public Persona getPersonaMovilidadGenerico() {
//        return personaMovilidadGenerico;
//    }
//
//    public void setPersonaMovilidadGenerico(Persona personaMovilidadGenerico) {
//        this.personaMovilidadGenerico = personaMovilidadGenerico;
//    }
//
//    public Telefono getTelFijoPersonaMovilidad() {
//        return telFijoPersonaMovilidad;
//    }
//
//    public void setTelFijoPersonaMovilidad(Telefono telFijoPersonaMovilidad) {
//        this.telFijoPersonaMovilidad = telFijoPersonaMovilidad;
//    }
//
//    public Telefono getTelCelPersonaMovilidad() {
//        return telCelPersonaMovilidad;
//    }
//
//    public void setTelCelPersonaMovilidad(Telefono telCelPersonaMovilidad) {
//        this.telCelPersonaMovilidad = telCelPersonaMovilidad;
//    }
//
//    public Telefono getFaxPersonaMovilidad() {
//        return faxPersonaMovilidad;
//    }
//
//    public void setFaxPersonaMovilidad(Telefono faxPersonaMovilidad) {
//        this.faxPersonaMovilidad = faxPersonaMovilidad;
//    }
//
//    
//    
//}
