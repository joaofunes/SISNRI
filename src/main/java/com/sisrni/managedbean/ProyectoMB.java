/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Usuario
 */
@Named(value = "proyectoMB")
@ViewScoped
public class ProyectoMB {

//    private static final int tipoCoord = 2;
//    private static final int tipoAsist = 5;
//    private static final int tipoExt = 3;
//    private static final String FIJO = "FIJO";
//    private static final String FAX = "FAX";
//    private List<Integer> areaConocimientoSelected;
//    private List<Integer> entidadesCooperantesSelected;
//    private Integer idFacultadPersona;
//
//    private int yearActual;
//    private String anio;
//
//    /*Variables*/
//    private List<Proyecto> listProyectos;
//    private List<Proyecto> listProyectosPrueba;
//
//    private List<Facultad> listFacultad;
//    private List<Unidad> listUnidad;
//
//    private List<Organismo> listOrganismos;
//    private List<Pais> listPaisesCooperantes;
//
//    private List<Persona> listInterno;
//    private List<Telefono> listTelefonoInterno;
//
//    private List<Persona> listAsistenteInterno;
//    private List<Telefono> listTelefonoAsistenteInterno;
//
//    private List<Persona> listExterno;
//    private List<Telefono> listTelefonoExterno;
//
//    private List<AreaConocimiento> listAreaConocimiento;
//    private List<AreaConocimiento> listaAreaConocimientoMerge;
//    private List<Organismo> listEntidadMerge;
//    private List<TipoProyecto> listTipoProyecto;
//    private List<PropuestaConvenio> listPropuestaConvenio;
//
//    private List<Unidad> listUnidadCoordinador;
//    private List<Unidad> listUnidadAsistente;
//    private List<Unidad> listUnidadPersona;
//
//    private Proyecto proyecto;
//    private ProyectoGenerico proyectoGenerico;
//    private Persona persona;
//    private Persona personaCoordinador;
//    private Persona personaAsistente;
//    private Persona personaExterno;
//
//    private PersonaProyecto personaProyectoCoordinador;
//    private PersonaProyectoPK personaProyectoCoordinadorPk;
//
//    private Telefono telFijoInterno;
//    private Telefono faxInterno;
//
//    private Telefono telFijoAsistente;
//    private Telefono faxAsistente;
//
//    private Telefono telFijoExterno;
//    private Telefono faxExterno;
//
//    private Telefono telFijoPersona;
//    private Telefono telCelPersona;
//    private Telefono faxPersona;
//
//    private Facultad facultadCoordinadorSelected;
//    private Unidad unidadCoordinadorSelected;
//
//    private Unidad unidadPersonaSelected;
//    private Organismo organismoPersonaSelected;
//
//    private Facultad facultadAsistenteSelected;
//    private Unidad unidadAsistenteSelected;
//
//    private TipoPersona tipoPersonaCoord;
//    private TipoPersona tipoPersonaAsis;
//    private TipoPersona tipoPersonaRefext;
//
//    private TipoTelefono tipoTelefonoFax;
//    private TipoTelefono tipoTelefonoFijo;
//    private TipoTelefono tipoTelefonoCelular;
//
//    @Autowired
//    @Qualifier(value = "facultadService")
//    private FacultadService facultadService;
//
//    @Autowired
//    @Qualifier(value = "unidadService")
//    private UnidadService unidadService;
//
//    @Autowired
//    @Qualifier(value = "organismoService")
//    private OrganismoService organismoService;
//
//    @Autowired
//    @Qualifier(value = "personaService")
//    private PersonaService personaService;
//
//    @Autowired
//    @Qualifier(value = "telefonoService")
//    private TelefonoService telefonoService;
//
//    @Autowired
//    @Qualifier(value = "areaConocimientoService")
//    private AreaConocimientoService areaConocimientoService;
//
//    @Autowired
//    @Qualifier(value = "tipoProyectoService")
//    private TipoProyectoService tipoProyectoService;
//
//    @Autowired
//    @Qualifier(value = "propuestaConvenioService")
//    private PropuestaConvenioService propuestaConvenioService;
//
//    @Autowired
//    @Qualifier(value = "proyectoService")
//    private ProyectoService proyectoService;
//
//    @Autowired
//    @Qualifier(value = "proyectoGenericoService")
//    private ProyectoGenericoService proyectoGenericoService;
//
//    @Autowired
//    @Qualifier(value = "paisService")
//    private PaisService paisService;
//
//    @Autowired
//    //@Qualifier(value = "personaProyectoService")
//    private PersonaProyectoService personaProyectoService;
//
//    @Autowired
//    private TipoPersonaService tipoPersonaService;
//
//    @Autowired
//    private TipoTelefonoService tipoTelefonoService;
//
//    /**
//     * constructor
//     */
//    public ProyectoMB() {
//    }
//
//    /**
//     * Post Constructor
//     */
//    @PostConstruct
//    public void init() {
//        try{
//          cargarProyecto();  
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        
//    }
//
//    public void cargarProyecto() {
//        proyecto = new Proyecto();
//        persona = new Persona();
//        personaCoordinador = new Persona();
//        personaAsistente = new Persona();
//        personaExterno = new Persona();
//        unidadPersonaSelected = new Unidad();
//        organismoPersonaSelected = new Organismo();
//        
//        facultadCoordinadorSelected  = new Facultad();
//        facultadAsistenteSelected = new Facultad();
//                
//
//        listFacultad = facultadService.findAll();
//        //listUnidad = unidadService.findAll();
//        listOrganismos = organismoService.findAll();
//        listInterno = personaService.findAll();
//        listExterno = personaService.findAll();
//        listAsistenteInterno = personaService.findAll();
//        listAreaConocimiento = areaConocimientoService.findAll();
//        listTipoProyecto = tipoProyectoService.findAll();
//        listPropuestaConvenio = propuestaConvenioService.findAll();
//        listProyectosPrueba = proyectoService.findAll();
//        areaConocimientoSelected = new ArrayList<Integer>();
//        entidadesCooperantesSelected = new ArrayList<Integer>();
//        listProyectos = proyectoService.findAll();
//        listPaisesCooperantes = paisService.findAll();
//
//        telFijoInterno = new Telefono();
//        faxInterno = new Telefono();
//        telFijoAsistente = new Telefono();
//        faxAsistente = new Telefono();
//        telFijoExterno = new Telefono();
//        faxExterno = new Telefono();
//
//        listaAreaConocimientoMerge = new ArrayList<AreaConocimiento>();
//        listEntidadMerge = new ArrayList<Organismo>();
//        personaProyectoCoordinador = new PersonaProyecto();
//        personaProyectoCoordinadorPk = new PersonaProyectoPK();
//
//        tipoPersonaCoord = tipoPersonaService.getTipoPersonaByNombre("REFERENTE INTERNO");
//        tipoPersonaAsis = tipoPersonaService.getTipoPersonaByNombre("ASISTENTE DE COORDINADOR");
//        tipoPersonaRefext = tipoPersonaService.getTipoPersonaByNombre("REFERENTE EXTERNO");
//
//        tipoTelefonoFijo = tipoTelefonoService.getTipoByDesc("FIJO");
//        tipoTelefonoCelular = tipoTelefonoService.getTipoByDesc("CELULAR");
//        tipoTelefonoFax = tipoTelefonoService.getTipoByDesc("FAX");
//
//        telFijoPersona = new Telefono();
//        telCelPersona = new Telefono();
//        faxPersona = new Telefono();
//
//        yearActual = getYearOfDate(new Date());
//        anio = "";
//    }
//
//    public void onchangeCoordinador() {
//        try {
//            listTelefonoInterno = telefonoService.getTelefonosByPersona(personaCoordinador);
//
//            for (Telefono tlfx : listTelefonoInterno) {
//                if (tlfx.getIdTipoTelefono().getNombre().equals(FIJO)) {
//                    telFijoInterno = tlfx;
//                }
//                if (tlfx.getIdTipoTelefono().getNombre().equals(FAX)) {
//                    faxInterno = tlfx;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void onchangeAsistenteCoordinador() {
//        try {
//            listTelefonoAsistenteInterno = telefonoService.getTelefonosByPersona(personaAsistente);
//
//            for (Telefono tlfx : listTelefonoAsistenteInterno) {
//                if (tlfx.getIdTipoTelefono().getNombre().equals(FIJO)) {
//                    telFijoAsistente = tlfx;
//                }
//                if (tlfx.getIdTipoTelefono().getNombre().equals(FAX)) {
//                    faxAsistente = tlfx;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void onchangeExterno() {
//        try {
//            listTelefonoExterno = telefonoService.getTelefonosByPersona(personaExterno);
//
//            for (Telefono tlfx : listTelefonoExterno) {
//                if (tlfx.getIdTipoTelefono().getNombre().equals(FIJO)) {
//                    telFijoExterno = tlfx;
//                }
//                if (tlfx.getIdTipoTelefono().getNombre().equals(FAX)) {
//                    faxExterno = tlfx;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void onchangeFacultad() {
//        try {
//            if (proyecto.getIdFacultad() != null && !proyecto.getIdFacultad().equals("")) {
//                listUnidad = unidadService.getUnidadesByFacultadId(proyecto.getIdFacultad());
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void onchangeFacultadPersona() {
//        try {
//            if (proyecto.getIdFacultad() != null && !proyecto.getIdFacultad().equals("")) {
//                listUnidadPersona = unidadService.getUnidadesByFacultadId(idFacultadPersona);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void onchangeFacultadCoordinador() {
//        try {
//            if (facultadCoordinadorSelected.getIdFacultad() != null && !facultadCoordinadorSelected.getIdFacultad().equals("")) {
//                //listUnidadCoordinador = unidadService.getUnidadesByFacultadId(personaCoordinador.getIdUnidad().getIdFacultad().getIdFacultad()); 
//                listUnidadCoordinador = unidadService.getUnidadesByFacultadId(facultadCoordinadorSelected.getIdFacultad());
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void onchangeFacultadAsistente() {
//        try {
//            if (facultadAsistenteSelected.getIdFacultad() != null && !facultadAsistenteSelected.getIdFacultad().equals("")) {
//                listUnidadAsistente = unidadService.getUnidadesByFacultadId(facultadAsistenteSelected.getIdFacultad());
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void preActualizarProyecto(Proyecto proyectoIn) {
//        try {
//            this.proyecto = proyectoIn;
//            proyecto = proyectoService.findById(proyecto.getIdProyecto());
//            listUnidad = unidadService.getUnidadesByFacultadId(proyecto.getIdFacultad());
//            anio = Integer.toString(proyecto.getAnioGestion())+" ";
//            
//
//            proyectoGenerico = proyectoGenericoService.findById(proyecto.getIdProyecto());
//            areaConocimientoSelected = areaConocimientoService.getAreasConocimientoProyecto(proyectoGenerico.getIdProyecto());
//            entidadesCooperantesSelected = organismoService.getOrganismosProyecto(proyectoGenerico.getIdProyecto());
//
//            
//            personaCoordinador = personaService.getPersonaByProyectoTipoPersona(proyecto.getIdProyecto(), tipoPersonaCoord.getIdTipoPersona());
//           
//            facultadCoordinadorSelected = facultadService.findById(personaCoordinador.getIdUnidad().getIdFacultad().getIdFacultad());
//            unidadCoordinadorSelected = unidadService.findById(personaCoordinador.getIdUnidad().getIdUnidad());
//            listUnidadCoordinador = unidadService.getUnidadesByFacultadId(personaCoordinador.getIdUnidad().getIdFacultad().getIdFacultad());
//            onchangeCoordinador();
//
//           
//           
//           
//            personaAsistente = personaService.getPersonaByProyectoTipoPersona(proyecto.getIdProyecto(), tipoPersonaAsis.getIdTipoPersona());
//            
//            facultadAsistenteSelected = facultadService.findById(personaAsistente.getIdUnidad().getIdFacultad().getIdFacultad());
//            unidadAsistenteSelected = unidadService.findById(personaAsistente.getIdUnidad().getIdUnidad());
//            listUnidadAsistente = unidadService.getUnidadesByFacultadId(personaAsistente.getIdUnidad().getIdFacultad().getIdFacultad());
//            onchangeAsistenteCoordinador();
//            
//
//            
//            personaExterno = personaService.getPersonaByProyectoTipoPersona(proyecto.getIdProyecto(), tipoPersonaRefext.getIdTipoPersona());
//            onchangeExterno();
//            
//
//            FacesContext.getCurrentInstance().getExternalContext().redirect("modificarProyecto.xhtml");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//       
//    }
//
//    public String preConsultarProyecto(Proyecto proyectoIn) {
//        try {
//            this.proyecto = proyectoIn;
//            proyecto = proyectoService.findById(proyecto.getIdProyecto());
//            listUnidad = unidadService.getUnidadesByFacultadId(proyecto.getIdFacultad());
//
//            proyectoGenerico = proyectoGenericoService.findById(proyecto.getIdProyecto());
//            areaConocimientoSelected = areaConocimientoService.getAreasConocimientoProyecto(proyecto.getIdProyecto());
//
//            personaCoordinador = personaService.getPersonaByProyectoTipoPersona(proyecto.getIdProyecto(), tipoPersonaCoord.getIdTipoPersona());
//            facultadCoordinadorSelected = facultadService.findById(personaCoordinador.getIdUnidad().getIdFacultad().getIdFacultad());
//            unidadCoordinadorSelected = unidadService.findById(personaCoordinador.getIdUnidad().getIdUnidad());
//            listUnidadCoordinador = unidadService.getUnidadesByFacultadId(personaCoordinador.getIdUnidad().getIdFacultad().getIdFacultad());
//            onchangeCoordinador();
//
//            personaAsistente = personaService.getPersonaByProyectoTipoPersona(proyecto.getIdProyecto(), tipoPersonaAsis.getIdTipoPersona());
//            facultadAsistenteSelected = facultadService.findById(personaAsistente.getIdUnidad().getIdFacultad().getIdFacultad());
//            unidadAsistenteSelected = unidadService.findById(personaAsistente.getIdUnidad().getIdUnidad());
//            listUnidadAsistente = unidadService.getUnidadesByFacultadId(personaAsistente.getIdUnidad().getIdFacultad().getIdFacultad());
//            onchangeAsistenteCoordinador();
//
//            personaExterno = personaService.getPersonaByProyectoTipoPersona(proyecto.getIdProyecto(), tipoPersonaRefext.getIdTipoPersona());
//            onchangeExterno();
//
//            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarProyecto.xhtml");
//
//            //return "modificarProyecto.xhtml";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public String preGuardarProyecto() {
//        try {
//
//            FacesContext.getCurrentInstance().getExternalContext().redirect("registrarProyecto.xhtml");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * Metodo para modificar los datos de un proyecto
//     */
//    public void actualizarProyecto() {
//        String msg = "Proyecto Actualizado Exitosamente!";
//        try {
//            //Integer anioGest = Integer.parseInt(anio.trim());
//            proyecto.setAnioGestion(Integer.parseInt(anio.trim()));
//            proyectoService.merge(proyecto);
//            modificarEntidades();
//            modificarArea();
//
//            proyectoGenericoService.merge(proyectoGenerico);
//
//            personaCoordinador.setIdUnidad(unidadCoordinadorSelected);
//            personaProyectoService.updatePersonaProyecto(personaCoordinador.getIdPersona(), proyectoGenerico.getIdProyecto(), tipoPersonaCoord.getIdTipoPersona());
//            personaService.merge(personaCoordinador);
//            telefonoService.merge(telFijoInterno);
//            telefonoService.merge(faxInterno);
//
//            personaAsistente.setIdUnidad(unidadAsistenteSelected);
//            personaProyectoService.updatePersonaProyecto(personaAsistente.getIdPersona(), proyectoGenerico.getIdProyecto(), tipoPersonaAsis.getIdTipoPersona());
//            personaService.merge(personaAsistente);
//            telefonoService.merge(telFijoAsistente);
//            telefonoService.merge(faxAsistente);
//
//            personaProyectoService.updatePersonaProyecto(personaExterno.getIdPersona(), proyectoGenerico.getIdProyecto(), tipoPersonaRefext.getIdTipoPersona());
//            personaService.merge(personaExterno);
//            telefonoService.merge(telFijoExterno);
//            telefonoService.merge(faxExterno);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizacion!!", msg));
//            //sleep 3 seconds
//            //Thread.sleep(5000);
//            
//            //FacesContext.getCurrentInstance().getExternalContext().redirect("proyectoAdm.xhtml");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//       // cargarProyecto();
//
//    }
//
//    public void modificarArea() {
//
//        try {
//            listaAreaConocimientoMerge.clear();
//            String[] areas = new String[areaConocimientoSelected.size()];
//            areas = areaConocimientoSelected.toArray(areas);
//
//            for (String s : areas) {
//                AreaConocimiento areaAcon = areaConocimientoService.findById(Integer.parseInt(s));
//                if (areaAcon != null) {
//                    listaAreaConocimientoMerge.add(areaAcon);
//                }
//            }
//            proyectoGenerico.setAreaConocimientoList(listaAreaConocimientoMerge);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void modificarProyectoGenericoOrganismos(){
//        
//    }
//    
//    public void modificarEntidades() {
//        try {
//            listEntidadMerge.clear();
//            String[] organismos = new String[entidadesCooperantesSelected.size()];
//            organismos = entidadesCooperantesSelected.toArray(organismos);
//
//            for (String o : organismos) {
//                Organismo organismoCoop = organismoService.findById(Integer.parseInt(o));
//                if (organismoCoop != null) {
//                    listEntidadMerge.add(organismoCoop);
//                    //proyectoGenerico.getOrganismoList().add(organismoCoop);
//                }
//            }
//            proyectoGenerico.setOrganismoList(listEntidadMerge);
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//    }
//
//    public void guardarPersona() {
//        String msg = "Persona creada exitosamente!!";
//        Unidad unidad = unidadService.findById(unidadPersonaSelected.getIdUnidad());
//        //Organismo organismo = organismoService.findById(organismoPersonaSelected.getIdOrganismo());
//        try {
//            //Seteando Persona
//            persona.setIdUnidad(unidad);
//            //persona.setIdOrganismo(organismo);
//            persona.setIdTipoPersona(tipoPersonaCoord.getIdTipoPersona());
//
//           if(telFijoPersona!=null){
//            //seteando telefonos de Persona
//            telFijoPersona.setIdTipoTelefono(tipoTelefonoFijo);
//            telFijoPersona.setIdPersona(persona);
//           }
//           
//            if(telCelPersona!=null){
//            telCelPersona.setIdTipoTelefono(tipoTelefonoCelular);
//            telCelPersona.setIdPersona(persona);
//            }
//            
//            if(faxPersona!=null){
//            faxPersona.setIdTipoTelefono(tipoTelefonoFax);
//            faxPersona.setIdPersona(persona);
//            }
//
//            //Guardando Persona
//            personaService.save(persona);
//
//            //Guardando Telefonos
//            if(telFijoPersona!=null){
//            telefonoService.save(telFijoPersona);}
//            if(telCelPersona!=null){
//            telefonoService.save(telCelPersona);}
//            if(faxPersona!=null){
//            telefonoService.save(faxPersona);}
//            
//            listInterno = personaService.findAll();
//                     
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado", msg));
//            
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            persona = new Persona();
//            telFijoPersona = new Telefono();
//            telCelPersona = new Telefono();
//            faxPersona = new Telefono();
//            unidadPersonaSelected = new Unidad();
//            organismoPersonaSelected = new Organismo();
//            listUnidadPersona.clear();
//            idFacultadPersona = null;
//
//        }
//    } 
//
//    public void guardarPersonaExterno() {
//        String msg = "Persona creada exitosamente!!";
//
//        try {
//            //JOptionPane.showMessageDialog(null, "el id del organismo es: " + organismoPersonaSelected.getIdOrganismo());
//            Organismo organismo = organismoService.findById(organismoPersonaSelected.getIdOrganismo());
//            //Seteando Persona
//            persona.setIdOrganismo(organismo);
//            persona.setIdTipoPersona(tipoPersonaRefext.getIdTipoPersona());
//
//            //seteando telefonos de Persona
//            if(telFijoPersona!=null){
//            telFijoPersona.setIdTipoTelefono(tipoTelefonoFijo);
//            telFijoPersona.setIdPersona(persona);}
//
//            if(telCelPersona!=null){
//            telCelPersona.setIdTipoTelefono(tipoTelefonoCelular);
//            telCelPersona.setIdPersona(persona);}
//
//            if(faxPersona!=null){
//            faxPersona.setIdTipoTelefono(tipoTelefonoFax);
//            faxPersona.setIdPersona(persona);}
//
//            //Guardando Persona
//            personaService.save(persona);
//
//            //Guardando Telefonos
//            if(telFijoPersona!=null){
//            telefonoService.save(telFijoPersona);}
//            if(telCelPersona!=null){
//            telefonoService.save(telCelPersona);}
//            if(faxPersona!=null){
//            telefonoService.save(faxPersona);}
//
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado", msg));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            persona = new Persona();
//            telFijoPersona = new Telefono();
//            telCelPersona = new Telefono();
//            faxPersona = new Telefono();
//            organismoPersonaSelected = new Organismo();
//
//        }
//    }
//    
//     public void back() {
//        try {
//            cargarProyecto();
//            FacesContext.getCurrentInstance().getExternalContext().redirect("proyectoAdm.xhtml");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//     private Integer getYearOfDate(Date date) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        Integer year = cal.get(Calendar.YEAR);
//        return year;
//    }
//
//    public List<Facultad> getListFacultad() {
//        return listFacultad;
//    }
//
//    public void setListFacultad(List<Facultad> listFacultad) {
//        this.listFacultad = listFacultad;
//    }
//
//    public List<Organismo> getListOrganismos() {
//        return listOrganismos;
//    }
//
//    public void setListOrganismos(List<Organismo> listOrganismos) {
//        this.listOrganismos = listOrganismos;
//    }
//
//    public List<Persona> getListInterno() {
//        return listInterno;
//    }
//
//    public void setListInterno(List<Persona> listInterno) {
//        this.listInterno = listInterno;
//    }
//
//    public List<Persona> getListAsistenteInterno() {
//        return listAsistenteInterno;
//    }
//
//    public void setListAsistenteInterno(List<Persona> listAsistenteInterno) {
//        this.listAsistenteInterno = listAsistenteInterno;
//    }
//
//    public Persona getPersonaCoordinador() {
//
//        return personaCoordinador;
//    }
//
//    public void setPersonaCoordinador(Persona personaCoordinador) {
//        this.personaCoordinador = personaCoordinador;
//    }
//
//    public Persona getPersonaAsistente() {
//        return personaAsistente;
//    }
//
//    public void setPersonaAsistente(Persona personaAsistente) {
//        this.personaAsistente = personaAsistente;
//    }
//
//    public Persona getPersona() {
//        return persona;
//    }
//
//    public void setPersona(Persona persona) {
//        this.persona = persona;
//    }
//
//    public List<Telefono> getListTelefonoInterno() {
//        return listTelefonoInterno;
//    }
//
//    public void setListTelefonoInterno(List<Telefono> listTelefonoInterno) {
//        this.listTelefonoInterno = listTelefonoInterno;
//    }
//
//    public Telefono getTelFijoInterno() {
//        return telFijoInterno;
//    }
//
//    public void setTelFijoInterno(Telefono telFijoInterno) {
//        this.telFijoInterno = telFijoInterno;
//    }
//
//    public Telefono getFaxInterno() {
//        return faxInterno;
//    }
//
//    public void setFaxInterno(Telefono faxInterno) {
//        this.faxInterno = faxInterno;
//    }
//
//    public Telefono getTelFijoAsistente() {
//        return telFijoAsistente;
//    }
//
//    public void setTelFijoAsistente(Telefono telFijoAsistente) {
//        this.telFijoAsistente = telFijoAsistente;
//    }
//
//    public Telefono getFaxAsistente() {
//        return faxAsistente;
//    }
//
//    public void setFaxAsistente(Telefono faxAsistente) {
//        this.faxAsistente = faxAsistente;
//    }
//
//    public List<AreaConocimiento> getListAreaConocimiento() {
//        return listAreaConocimiento;
//    }
//
//    public void setListAreaConocimiento(List<AreaConocimiento> listAreaConocimiento) {
//        this.listAreaConocimiento = listAreaConocimiento;
//    }
//
//    public List<Integer> getAreaConocimientoSelected() {
//        return areaConocimientoSelected;
//    }
//
//    public void setAreaConocimientoSelected(List<Integer> areaConocimientoSelected) {
//        this.areaConocimientoSelected = areaConocimientoSelected;
//    }
//
//    public Persona getPersonaExterno() {
//        return personaExterno;
//    }
//
//    public void setPersonaExterno(Persona personaExterno) {
//        this.personaExterno = personaExterno;
//    }
//
//    public Telefono getTelFijoExterno() {
//        return telFijoExterno;
//    }
//
//    public void setTelFijoExterno(Telefono telFijoExterno) {
//        this.telFijoExterno = telFijoExterno;
//    }
//
//    public Telefono getFaxExterno() {
//        return faxExterno;
//    }
//
//    public void setFaxExterno(Telefono faxExterno) {
//        this.faxExterno = faxExterno;
//    }
//
//    public List<TipoProyecto> getListTipoProyecto() {
//        return listTipoProyecto;
//    }
//
//    public void setListTipoProyecto(List<TipoProyecto> listTipoProyecto) {
//        this.listTipoProyecto = listTipoProyecto;
//    }
//
//    public List<PropuestaConvenio> getListPropuestaConvenio() {
//        return listPropuestaConvenio;
//    }
//
//    public void setListPropuestaConvenio(List<PropuestaConvenio> listPropuestaConvenio) {
//        this.listPropuestaConvenio = listPropuestaConvenio;
//    }
//
//    public List<Unidad> getListUnidad() {
//        return listUnidad;
//    }
//
//    public void setListUnidad(List<Unidad> listUnidad) {
//        this.listUnidad = listUnidad;
//    }
//
//    public Proyecto getProyecto() {
//        return proyecto;
//    }
//
//    public void setProyecto(Proyecto proyecto) {
//        this.proyecto = proyecto;
//    }
//
//    public List<Proyecto> getListProyectos() {
//        return listProyectos;
//    }
//
//    public void setListProyectos(List<Proyecto> listProyectos) {
//        this.listProyectos = listProyectos;
//    }
//
//    public ProyectoGenerico getProyectoGenerico() {
//        return proyectoGenerico;
//    }
//
//    public void setProyectoGenerico(ProyectoGenerico proyectoGenerico) {
//        this.proyectoGenerico = proyectoGenerico;
//    }
//
//    public List<Proyecto> getListProyectosPrueba() {
//        return listProyectosPrueba;
//    }
//
//    public void setListProyectosPrueba(List<Proyecto> listProyectosPrueba) {
//        this.listProyectosPrueba = listProyectosPrueba;
//    }
//
//    public List<Unidad> getListUnidadCoordinador() {
//        return listUnidadCoordinador;
//    }
//
//    public void setListUnidadCoordinador(List<Unidad> listUnidadCoordinador) {
//        this.listUnidadCoordinador = listUnidadCoordinador;
//    }
//
//    public Unidad getUnidadCoordinadorSelected() {
//        return unidadCoordinadorSelected;
//    }
//
//    public void setUnidadCoordinadorSelected(Unidad unidadCoordinadorSelected) {
//        this.unidadCoordinadorSelected = unidadCoordinadorSelected;
//    }
//
//    public Facultad getFacultadCoordinadorSelected() {
//        return facultadCoordinadorSelected;
//    }
//
//    public void setFacultadCoordinadorSelected(Facultad facultadCoordinadorSelected) {
//        this.facultadCoordinadorSelected = facultadCoordinadorSelected;
//    }
//
//    public Facultad getFacultadAsistenteSelected() {
//        return facultadAsistenteSelected;
//    }
//
//    public void setFacultadAsistenteSelected(Facultad facultadAsistenteSelected) {
//        this.facultadAsistenteSelected = facultadAsistenteSelected;
//    }
//
//    public Unidad getUnidadAsistenteSelected() {
//        return unidadAsistenteSelected;
//    }
//
//    public void setUnidadAsistenteSelected(Unidad unidadAsistenteSelected) {
//        this.unidadAsistenteSelected = unidadAsistenteSelected;
//    }
//
//    public List<Unidad> getListUnidadAsistente() {
//        return listUnidadAsistente;
//    }
//
//    public void setListUnidadAsistente(List<Unidad> listUnidadAsistente) {
//        this.listUnidadAsistente = listUnidadAsistente;
//    }
//
//    public List<Persona> getListExterno() {
//        return listExterno;
//    }
//
//    public void setListExterno(List<Persona> listExterno) {
//        this.listExterno = listExterno;
//    }
//
//    public Unidad getUnidadPersonaSelected() {
//        return unidadPersonaSelected;
//    }
//
//    public void setUnidadPersonaSelected(Unidad unidadPersonaSelected) {
//        this.unidadPersonaSelected = unidadPersonaSelected;
//    }
//
//    public List<Unidad> getListUnidadPersona() {
//        return listUnidadPersona;
//    }
//
//    public void setListUnidadPersona(List<Unidad> listUnidadPersona) {
//        this.listUnidadPersona = listUnidadPersona;
//    }
//
//    public Integer getIdFacultadPersona() {
//        return idFacultadPersona;
//    }
//
//    public void setIdFacultadPersona(Integer idFacultadPersona) {
//        this.idFacultadPersona = idFacultadPersona;
//    }
//
//    public Telefono getTelFijoPersona() {
//        return telFijoPersona;
//    }
//
//    public void setTelFijoPersona(Telefono telFijoPersona) {
//        this.telFijoPersona = telFijoPersona;
//    }
//
//    public Telefono getTelCelPersona() {
//        return telCelPersona;
//    }
//
//    public void setTelCelPersona(Telefono telCelPersona) {
//        this.telCelPersona = telCelPersona;
//    }
//
//    public Telefono getFaxPersona() {
//        return faxPersona;
//    }
//
//    public void setFaxPersona(Telefono faxPersona) {
//        this.faxPersona = faxPersona;
//    }
//
//    public Organismo getOrganismoPersonaSelected() {
//        return organismoPersonaSelected;
//    }
//
//    public void setOrganismoPersonaSelected(Organismo organismoPersonaSelected) {
//        this.organismoPersonaSelected = organismoPersonaSelected;
//    }
//
//    public int getYearActual() {
//        return yearActual;
//    }
//
//    public void setYearActual(int yearActual) {
//        this.yearActual = yearActual;
//    }
//
//    public String getAnio() {
//        return anio;
//    }
//
//    public void setAnio(String anio) {
//        this.anio = anio;
//    }
//
//    public List<Pais> getListPaisesCooperantes() {
//        return listPaisesCooperantes;
//    }
//
//    public List<Integer> getEntidadesCooperantesSelected() {
//        return entidadesCooperantesSelected;
//    }
//
//    public void setEntidadesCooperantesSelected(List<Integer> entidadesCooperantesSelected) {
//        this.entidadesCooperantesSelected = entidadesCooperantesSelected;
//    }

    
}
