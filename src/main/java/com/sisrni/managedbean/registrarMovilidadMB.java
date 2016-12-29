package com.sisrni.managedbean;

import com.sisrni.model.CategoriaMovilidad;
import com.sisrni.model.EtapaMovilidad;
import com.sisrni.model.Facultad;
import com.sisrni.model.Movilidad;
import com.sisrni.model.Organismo;
import com.sisrni.model.Pais;
import com.sisrni.model.Persona;
import com.sisrni.model.ProgramaMovilidad;
import com.sisrni.model.Telefono;
import com.sisrni.model.TipoMovilidad;
import com.sisrni.model.Unidad;
import com.sisrni.pojo.rpt.PojoFacultadesUnidades;
import com.sisrni.service.CategoriaMovilidadService;
import com.sisrni.service.EtapaMovilidadService;
import com.sisrni.service.FacultadService;
import com.sisrni.service.MovilidadService;
import com.sisrni.service.OrganismoService;
import com.sisrni.service.PaisService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.ProgramaMovilidadService;
import com.sisrni.service.TipoMovilidadService;
import com.sisrni.service.UnidadService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Usuario
 */
@Named(value = "registrarMovilidadMB")
@ViewScoped
public class registrarMovilidadMB {

    //Variables
    private Integer tipoMovilidadSelected;
    private Integer categoriaMovilidadSelected;
    private Integer paisOrigenSelected;
    private Integer institucionOrigenSelected;
    private Integer paisDestinoSelected;
    private Integer institucionDestinoSelected;
    private Boolean entregaInformeSelected;
    private Boolean obsequioSelected;
    private Integer etapaMovilidadSelected;
    private String[] facultadesUnidadesBeneficiadasSelected; 
    private String[] facultadesBeneficiadasSelected;
    private String[] unidadesBeneficiadasSelected;

    private Boolean mostrarSaliente, mostrarEntrante;

    private Date fechaInicioSelected;
    private Date fechaFinSelected;
    private Date fechaEntregaMinedSelected;

    private ProgramaMovilidad programaMovilidadSelected;
    private ProgramaMovilidad programaMovilidad;
    private TipoMovilidad tipoMovilidad;
    private Movilidad movilidad;
    private CategoriaMovilidad categoriaMovilidad;
    private Persona personaMovilidad;
    private Persona personaMovilidadGenerico;

    private Telefono telFijoPersonaMovilidad;
    private Telefono telCelPersonaMovilidad;
    private Telefono faxPersonaMovilidad;

    private List<ProgramaMovilidad> listProgramaMovilidad;
    private List<TipoMovilidad> listTipoMovilidad;
    private List<CategoriaMovilidad> listCategoriaMovilidad;
    private List<Pais> listPaisOrigenMovilidad;
    private List<Pais> listPaisDestinoMovilidad;
    private List<Persona> listPersonaMovilidad;
    private List<Organismo> listOrganismosOrigen;
    private List<Organismo> listOrganismosDestino;
    private List<EtapaMovilidad> listEtapaMovilidad;
    private List<PojoFacultadesUnidades> listFacultadUnidad;
    //private List<Integer> listFacultadSelected;
    private List<Facultad> listFacultadAdd;
    private List<Unidad> listUnidad;
    private List<Unidad> listUnidadAdd;
    
    private List<Facultad> listFacultadBnfUes;
    private List<Unidad> listUnidadBnfUes;

    //Services
    @Autowired
    @Qualifier(value = "programaMovilidadService")
    private ProgramaMovilidadService programaMovilidadService;

    @Autowired
    @Qualifier(value = "tipoMovilidadService")
    private TipoMovilidadService tipoMovilidadService;

    @Autowired
    @Qualifier(value = "categoriaMovilidadService")
    private CategoriaMovilidadService categoriaMovilidadService;

    @Autowired
    @Qualifier(value = "paisService")
    private PaisService paisService;

    @Autowired
    @Qualifier(value = "movilidadService")
    private MovilidadService movilidadService;

    @Autowired
    @Qualifier(value = "personaService")
    private PersonaService personaService;

    @Autowired
    @Qualifier(value = "organismoService")
    private OrganismoService organismoService;

    @Autowired
    @Qualifier(value = "etapamovilidadService")
    private EtapaMovilidadService etapamovilidadService;

    @Autowired
    @Qualifier(value = "facultadService")
    private FacultadService facultadService;

    @Autowired
    @Qualifier(value = "unidadService")
    private UnidadService unidadService;

    /**
     * Constructor
     */
    public registrarMovilidadMB() {
    }

    /**
     * Post constructor
     */
    @PostConstruct
    public void init() {
        cargarMovilidadPersona();
    }

    /**
     * Metodo inicializador de variables y objetos
     */
    public void cargarMovilidadPersona() {
        programaMovilidadSelected = new ProgramaMovilidad();
        movilidad = new Movilidad();
        tipoMovilidad = new TipoMovilidad();
        tipoMovilidadSelected = null;
        categoriaMovilidadSelected = null;
        categoriaMovilidad = new CategoriaMovilidad();
        paisOrigenSelected = null;
        institucionOrigenSelected = null;
        paisDestinoSelected = null;
        institucionDestinoSelected = null;
        entregaInformeSelected = null;
        obsequioSelected = null;
        personaMovilidad = new Persona();
        personaMovilidadGenerico = new Persona();

        fechaInicioSelected = null;
        fechaFinSelected = null;
        fechaEntregaMinedSelected = null;
        etapaMovilidadSelected = null;

        //mostrarEntrante = true;
        //mostrarSaliente = true;
        telFijoPersonaMovilidad = new Telefono();
        telCelPersonaMovilidad = new Telefono();
        faxPersonaMovilidad = new Telefono();

        listProgramaMovilidad = programaMovilidadService.findAll();
        listTipoMovilidad = tipoMovilidadService.findAll();
        listCategoriaMovilidad = categoriaMovilidadService.findAll();
        listPaisOrigenMovilidad = paisService.findAll();
        listPaisDestinoMovilidad = paisService.findAll();
        listPersonaMovilidad = personaService.findAll();
        listEtapaMovilidad = etapamovilidadService.findAll();
        
        listFacultadBnfUes =facultadService.findAll(); //revisar esto
        listUnidadBnfUes = unidadService.findAll();     //revisar esto
        
        listFacultadUnidad = getListFacultadesUnidades(listFacultadBnfUes,listUnidadBnfUes);
        listFacultadAdd = new ArrayList<Facultad>();

        listUnidad = unidadService.findAll();
        listUnidadAdd = new ArrayList<Unidad>();

    }

    public void onchangeProgramaMovilidadList() {
        try {
            if (programaMovilidadSelected != null) {
                programaMovilidad = programaMovilidadService.findById(programaMovilidadSelected.getIdProgramaMovilidad());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onchangeTipoMovilidadList() {
        try {
            if (tipoMovilidadSelected != null) {
                tipoMovilidad = tipoMovilidadService.findById(tipoMovilidadSelected);

                if (tipoMovilidadSelected == 1) {

                    mostrarEntrante = true;
                    mostrarSaliente = false;
                } else {
                    mostrarEntrante = false;
                    mostrarSaliente = true;
                }

            } else {
                tipoMovilidad = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onchangeCategoriaMovilidadList() {
        try {
            if (categoriaMovilidadSelected != null) {
                categoriaMovilidad = categoriaMovilidadService.findById(categoriaMovilidadSelected);
            } else {
                categoriaMovilidad = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onchangeListPersonaMovilidad() {
        try {
            if (personaMovilidad != null) {
                personaMovilidadGenerico = personaMovilidad;
                // personaMovilidad = new Persona();
            }
        } catch (Exception e) {

        }
    }

    public void onchangeListPaisOrigen() {
        try {
            if (paisOrigenSelected != null) {
                 listOrganismosOrigen = organismoService.getOrganismosPorPaisYTipo(paisOrigenSelected,1);
            }
        } catch (Exception e) {

        }
    }

    public void onchangeListPaisDestino() {
        try {
            if (paisOrigenSelected != null) { 
                //nota: validar si no se recibe ningun organismo
                listOrganismosDestino = organismoService.getOrganismosPorPaisYTipo(paisDestinoSelected,1);
            }
        } catch (Exception e) {

        }
    }

   
    
    /**
     * Metodo para  guardar en arreglos las facultades y unidades beneficiadas
     */
    public void getArreglosFacultadesUnidadesBeneficiadas(){
        int result;
        for(int i = 0 ; i< facultadesUnidadesBeneficiadasSelected.length;i++){
            if((result = facultadesUnidadesBeneficiadasSelected[i].indexOf(",1"))>-1){
                
            }else if((result = facultadesUnidadesBeneficiadasSelected[i].indexOf(",2"))>-1){
                
            }else{
                
            }
        }
        
    }
    
    
    /**
     * Metodo para agregar las facultades beneficiadas a la instancia de
     * Movilidad
     */
    public void addFacultadesBeneficiadas() {
        try {
            for (int i = 0; i < facultadesBeneficiadasSelected.length; i++) {

                Facultad facultadBnf = facultadService.findById(Integer.parseInt(facultadesBeneficiadasSelected[i]));
                if (facultadBnf != null) {
                    listFacultadAdd.add(facultadBnf);
                }
            }
            movilidad.setFacultadList(listFacultadAdd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para agregar las facultades beneficiadas a la instancia de
     * Movilidad
     */
    public void addUnidadesBeneficiadas() {
        try {
            for (int i = 0; i < unidadesBeneficiadasSelected.length; i++) {

                Unidad unidadBnf = unidadService.findById(Integer.parseInt(unidadesBeneficiadasSelected[i]));
                if (unidadBnf != null) {
                    listUnidadAdd.add(unidadBnf);
                }
            }
            movilidad.setUnidadList(listUnidadAdd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Metodo para unir en una lista las Sub listas de facultad y unidad
     */
    private List<PojoFacultadesUnidades> getListFacultadesUnidades(List<Facultad> facs, List<Unidad> unidades) {
        
        List<PojoFacultadesUnidades> lista =new ArrayList<PojoFacultadesUnidades>();
        for (Facultad fac : facs) {
            PojoFacultadesUnidades pojo=new PojoFacultadesUnidades();
            pojo.setValue(fac.getIdFacultad() + ",1");
            pojo.setLabel(fac.getNombreFacultad());
            lista.add(pojo);
        }
        for (Unidad uni : unidades) {
            PojoFacultadesUnidades pojo=new PojoFacultadesUnidades();
            pojo.setValue(uni.getIdUnidad() + ",2");
            pojo.setLabel(uni.getNombreUnidad());
            lista.add(pojo);
        }
        return lista;
    }
    
    
    public void crearMovilidad() {
        try {
            movilidad.setIdProgramaMovilidad(programaMovilidad);
            movilidad.setIdTipoMovilidad(tipoMovilidad);
            movilidad.setIdCategoria(categoriaMovilidad);
            movilidad.setIdPaisOrigen(paisOrigenSelected);
            movilidad.setIdUniversidadOrigen(institucionOrigenSelected);
            movilidad.setIdUniversidadDestino(institucionDestinoSelected);
            movilidad.setIdPaisDestino(paisDestinoSelected);
            movilidad.setFechaInicio(fechaInicioSelected);
            movilidad.setFechaFin(fechaFinSelected);
            movilidad.setFechaEntregaMined(fechaEntregaMinedSelected);
            movilidad.setIdEtapaMovilidad(etapamovilidadService.findById(etapaMovilidadSelected));
            movilidad.setEntregaDeInforme(entregaInformeSelected);
            movilidad.setObsequio(obsequioSelected);
            addFacultadesBeneficiadas();
            addUnidadesBeneficiadas();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void guardarMovilidadPersona() {
        try {
            crearMovilidad();

            movilidadService.save(movilidad);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //GETTER Y SETTER
    public List<ProgramaMovilidad> getListProgramaMovilidad() {
        return listProgramaMovilidad;
    }

    public void setListProgramaMovilidad(List<ProgramaMovilidad> listProgramaMovilidad) {
        this.listProgramaMovilidad = listProgramaMovilidad;
    }

    public ProgramaMovilidad getProgramaMovilidadSelected() {
        return programaMovilidadSelected;
    }

    public void setProgramaMovilidadSelected(ProgramaMovilidad programaMovilidadSelected) {
        this.programaMovilidadSelected = programaMovilidadSelected;
    }

    public ProgramaMovilidad getProgramaMovilidad() {
        return programaMovilidad;
    }

    public void setProgramaMovilidad(ProgramaMovilidad programaMovilidad) {
        this.programaMovilidad = programaMovilidad;
    }

    public Movilidad getMovilidad() {
        return movilidad;
    }

    public void setMovilidad(Movilidad movilidad) {
        this.movilidad = movilidad;
    }

    public List<TipoMovilidad> getListTipoMovilidad() {
        return listTipoMovilidad;
    }

    public void setListTipoMovilidad(List<TipoMovilidad> listTipoMovilidad) {
        this.listTipoMovilidad = listTipoMovilidad;
    }

    public List<CategoriaMovilidad> getListCategoriaMovilidad() {
        return listCategoriaMovilidad;
    }

    public void setListCategoriaMovilidad(List<CategoriaMovilidad> listCategoriaMovilidad) {
        this.listCategoriaMovilidad = listCategoriaMovilidad;
    }

    public Integer getTipoMovilidadSelected() {
        return tipoMovilidadSelected;
    }

    public void setTipoMovilidadSelected(Integer tipoMovilidadSelected) {
        this.tipoMovilidadSelected = tipoMovilidadSelected;
    }

    public Integer getCategoriaMovilidadSelected() {
        return categoriaMovilidadSelected;
    }

    public void setCategoriaMovilidadSelected(Integer categoriaMovilidadSelected) {
        this.categoriaMovilidadSelected = categoriaMovilidadSelected;
    }

    public List<Pais> getListPaisOrigenMovilidad() {
        return listPaisOrigenMovilidad;
    }

    public void setListPaisOrigenMovilidad(List<Pais> listPaisOrigenMovilidad) {
        this.listPaisOrigenMovilidad = listPaisOrigenMovilidad;
    }

    public Integer getPaisOrigenSelected() {
        return paisOrigenSelected;
    }

    public void setPaisOrigenSelected(Integer paisOrigenSelected) {
        this.paisOrigenSelected = paisOrigenSelected;
    }

    public Integer getInstitucionOrigenSelected() {
        return institucionOrigenSelected;
    }

    public void setInstitucionOrigenSelected(Integer institucionOrigenSelected) {
        this.institucionOrigenSelected = institucionOrigenSelected;
    }

    public Integer getPaisDestinoSelected() {
        return paisDestinoSelected;
    }

    public void setPaisDestinoSelected(Integer paisDestinoSelected) {
        this.paisDestinoSelected = paisDestinoSelected;
    }

    public Integer getInstitucionDestinoSelected() {
        return institucionDestinoSelected;
    }

    public void setInstitucionDestinoSelected(Integer institucionDestinoSelected) {
        this.institucionDestinoSelected = institucionDestinoSelected;
    }

    public List<Pais> getListPaisDestinoMovilidad() {
        return listPaisDestinoMovilidad;
    }

    public void setListPaisDestinoMovilidad(List<Pais> listPaisDestinoMovilidad) {
        this.listPaisDestinoMovilidad = listPaisDestinoMovilidad;
    }

    public Date getFechaInicioSelected() {
        return fechaInicioSelected;
    }

    public void setFechaInicioSelected(Date fechaInicioSelected) {
        this.fechaInicioSelected = fechaInicioSelected;
    }

    public Date getFechaFinSelected() {
        return fechaFinSelected;
    }

    public void setFechaFinSelected(Date fechaFinSelected) {
        this.fechaFinSelected = fechaFinSelected;
    }

    public Boolean getEntregaInformeSelected() {
        return entregaInformeSelected;
    }

    public void setEntregaInformeSelected(Boolean entregaInformeSelected) {
        this.entregaInformeSelected = entregaInformeSelected;
    }

    public List<Persona> getListPersonaMovilidad() {
        return listPersonaMovilidad;
    }

    public void setListPersonaMovilidad(List<Persona> listPersonaMovilidad) {
        this.listPersonaMovilidad = listPersonaMovilidad;
    }

    public Boolean getMostrarSaliente() {
        return mostrarSaliente;
    }

    public void setMostrarSaliente(Boolean mostrarSaliente) {
        this.mostrarSaliente = mostrarSaliente;
    }

    public Boolean getMostrarEntrante() {
        return mostrarEntrante;
    }

    public void setMostrarEntrante(Boolean mostrarEntrante) {
        this.mostrarEntrante = mostrarEntrante;
    }

    public Persona getPersonaMovilidad() {
        return personaMovilidad;
    }

    public void setPersonaMovilidad(Persona personaMovilidad) {
        this.personaMovilidad = personaMovilidad;
    }

    public Persona getPersonaMovilidadGenerico() {
        return personaMovilidadGenerico;
    }

    public void setPersonaMovilidadGenerico(Persona personaMovilidadGenerico) {
        this.personaMovilidadGenerico = personaMovilidadGenerico;
    }

    public Telefono getTelFijoPersonaMovilidad() {
        return telFijoPersonaMovilidad;
    }

    public void setTelFijoPersonaMovilidad(Telefono telFijoPersonaMovilidad) {
        this.telFijoPersonaMovilidad = telFijoPersonaMovilidad;
    }

    public Telefono getTelCelPersonaMovilidad() {
        return telCelPersonaMovilidad;
    }

    public void setTelCelPersonaMovilidad(Telefono telCelPersonaMovilidad) {
        this.telCelPersonaMovilidad = telCelPersonaMovilidad;
    }

    public Telefono getFaxPersonaMovilidad() {
        return faxPersonaMovilidad;
    }

    public void setFaxPersonaMovilidad(Telefono faxPersonaMovilidad) {
        this.faxPersonaMovilidad = faxPersonaMovilidad;
    }

    public List<Organismo> getListOrganismosOrigen() {
        return listOrganismosOrigen;
    }

    public void setListOrganismosOrigen(List<Organismo> listOrganismosOrigen) {
        this.listOrganismosOrigen = listOrganismosOrigen;
    }

    public List<Organismo> getListOrganismosDestino() {
        return listOrganismosDestino;
    }

    public void setListOrganismosDestino(List<Organismo> listOrganismosDestino) {
        this.listOrganismosDestino = listOrganismosDestino;
    }

    public Date getFechaEntregaMinedSelected() {
        return fechaEntregaMinedSelected;
    }

    public void setFechaEntregaMinedSelected(Date fechaEntregaMinedSelected) {
        this.fechaEntregaMinedSelected = fechaEntregaMinedSelected;
    }

    public List<EtapaMovilidad> getListEtapaMovilidad() {
        return listEtapaMovilidad;
    }

    public void setListEtapaMovilidad(List<EtapaMovilidad> listEtapaMovilidad) {
        this.listEtapaMovilidad = listEtapaMovilidad;
    }

    public Boolean getObsequioSelected() {
        return obsequioSelected;
    }

    public void setObsequioSelected(Boolean obsequioSelected) {
        this.obsequioSelected = obsequioSelected;
    }

    public List<PojoFacultadesUnidades> getListFacultadUnidad() {
        return listFacultadUnidad;
    }

    public void setListFacultadUnidad(List<PojoFacultadesUnidades> listFacultadUnidad) {
        this.listFacultadUnidad = listFacultadUnidad;
    }    

    public Integer getEtapaMovilidadSelected() {
        return etapaMovilidadSelected;
    }

    public void setEtapaMovilidadSelected(Integer etapaMovilidadSelected) {
        this.etapaMovilidadSelected = etapaMovilidadSelected;
    }

    public String[] getFacultadesBeneficiadasSelected() {
        return facultadesBeneficiadasSelected;
    }

    public void setFacultadesBeneficiadasSelected(String[] facultadesBeneficiadasSelected) {
        this.facultadesBeneficiadasSelected = facultadesBeneficiadasSelected;
    }

    public List<Unidad> getListUnidad() {
        return listUnidad;
    }

    public void setListUnidad(List<Unidad> listUnidad) {
        this.listUnidad = listUnidad;
    }

    public String[] getUnidadesBeneficiadasSelected() {
        return unidadesBeneficiadasSelected;
    }

    public void setUnidadesBeneficiadasSelected(String[] unidadesBeneficiadasSelected) {
        this.unidadesBeneficiadasSelected = unidadesBeneficiadasSelected;
    }

    public String[] getFacultadesUnidadesBeneficiadasSelected() {
        return facultadesUnidadesBeneficiadasSelected;
    }

    public void setFacultadesUnidadesBeneficiadasSelected(String[] facultadesUnidadesBeneficiadasSelected) {
        this.facultadesUnidadesBeneficiadasSelected = facultadesUnidadesBeneficiadasSelected;
    }
    
    

}
