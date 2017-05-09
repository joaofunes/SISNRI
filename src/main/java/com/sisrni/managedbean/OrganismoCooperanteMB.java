/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.Organismo;
import com.sisrni.model.Pais;
import com.sisrni.model.Region;
import com.sisrni.model.Telefono;
import com.sisrni.model.TipoOrganismo;
import com.sisrni.model.TipoTelefono;
import com.sisrni.pojo.rpt.PojoOrganismo;
import com.sisrni.pojo.rpt.PojoPais;
import com.sisrni.service.OrganismoService;
import com.sisrni.service.PaisService;
import com.sisrni.service.RegionService;
import com.sisrni.service.TelefonoService;
import com.sisrni.service.TipoOrganismoService;
import com.sisrni.service.TipoTelefonoService;
import com.sisrni.utils.JsfUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Luis
 */
@Named(value = "organismoCooperanteMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class OrganismoCooperanteMB {

    /**
     * Creates a new instance of OrganismoCooperanteMB
     */
    @Inject
    TipoOrganismoMB tipoOrganismoMB;
    private Organismo organismoCooperante ;
    @Autowired
    TipoOrganismoService tipoOrganismoService;
    @Autowired
    PaisService paisService;
    @Autowired
    RegionService regionService;
    @Autowired
    OrganismoService organismoService;
    @Autowired
    TipoTelefonoService tipoTelefonoService;
    @Autowired
    TelefonoService telefonoService;
    
    private static final String FIJO="FIJO";
    
    private List<TipoOrganismo> tipoOrganismoList;
    private TipoOrganismo organismoSelected;
    private List<Pais> paisList;
    private Pais paisSelected;
    private List<Region> regionList;
    private Region regionSelected;
    private List<Organismo> organismosList;
    private boolean actualizar;
    private Telefono telefonoFijo;
    private TipoTelefono tipoTelefono;
     //listas de pais
    private PojoPais pojoPaisSelected;
    private PojoPais pojoToShow;
    private List<PojoPais> paisPojoList;
    private List<PojoOrganismo> organismoPojoList;
    private Integer tipoSelected;
    private Integer nPaisSelected;
    private PojoOrganismo pojoOrganismo;
    private String mascaraTelefono;
    private String codigoPais;

    

    public OrganismoCooperanteMB() {    
    }
    @PostConstruct
    public void init(){
    inicializarVariables();
    }
    public void inicializarVariables(){
    organismoCooperante =new Organismo();
    telefonoFijo = new Telefono();
    pojoOrganismo = new PojoOrganismo();
    tipoOrganismoList = tipoOrganismoService.getAllByIdDesc();
    organismoSelected=new TipoOrganismo();
    organismosList=organismoService.getAllByIdDesc();
    paisList = paisService.getCountriesOrderByNameAsc();
    regionSelected = new Region();
    paisSelected = new Pais();
    regionList = regionService.getAllByIdDesc();
    paisPojoList = paisService.getPaises(0);
    organismoPojoList=organismoService.getOrganismos();
    pojoPaisSelected = new PojoPais();
    pojoToShow = new PojoPais();
    actualizar=false;
    tipoSelected = 0;
    nPaisSelected =0;
    mascaraTelefono="";
    codigoPais="";
    }
    
    public void guardarOrganismo(){
        try {
            //seteamos el tipo organismo seleccionado el cual buscamos en la base para ver si existe utilizando el tipoorganismoservice
            organismoCooperante.setIdTipoOrganismo(tipoOrganismoService.findById(organismoSelected.getIdTipoOrganismo()));
            organismoCooperante.setIdRegion(regionSelected.getIdRegion());
            organismoCooperante.setIdPais(paisSelected.getIdPais());
            organismoCooperante.setIdOrganismo(Integer.MIN_VALUE);
            organismoService.save(organismoCooperante);
            tipoTelefono=tipoTelefonoService.getTipoByDesc(FIJO);
            telefonoFijo.setIdOrganismo(organismoCooperante);
            telefonoFijo.setIdTipoTelefono(tipoTelefono);
            telefonoService.save(telefonoFijo);
            inicializarVariables();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "La Informacion se ha registrado correctamente!"));
            
                    
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "el organismo no se puede ingresar."));
        }
    }
    public List<Organismo> listaOrganismos() {
        organismosList = organismoService.getAllByNameAsc();
        Organismo organismoNew1=new Organismo();
        List<Organismo> copy = new ArrayList<Organismo>();
        for (Organismo organismoNew : organismosList) {
            if(!organismoNew.getNombreOrganismo().equalsIgnoreCase("Agregar Nuevo"))
            {
                copy.add(organismoNew);
            }else{
                organismoNew1=organismoNew;
            }
        }
        copy.add(organismoNew1);
        organismosList.clear();
        return organismosList=copy;
    }
    public void updateorganismo() {
        String msg = "Organismo Actualizado Exitosamente!";       
        try { 
            organismoCooperante.setIdTipoOrganismo(tipoOrganismoService.findById(organismoSelected.getIdTipoOrganismo()));
            organismoCooperante.setIdRegion(regionSelected.getIdRegion());
            organismoCooperante.setIdPais(paisSelected.getIdPais());
            organismoService.merge(organismoCooperante);
            telefonoFijo.setIdOrganismo(organismoCooperante);            
            telefonoFijo.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(FIJO));           
            telefonoService.saveOrUpdate(telefonoFijo);
            actualizar=false;
            cancelarOrganismo();
            inicializarVariables();
            RequestContext.getCurrentInstance().update(":formAdmin");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", msg));
            
        } catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "el organismo no se puede actualizar."));
        }
        inicializarVariables();
    }
   
    public void preUpdate(PojoOrganismo pojoOrganismoCooperante){
        try {
            this.pojoOrganismo = pojoOrganismoCooperante;
            this.organismoCooperante = organismoService.findById(pojoOrganismoCooperante.getIdOrg()); 
            this.organismoSelected.setIdTipoOrganismo(this.organismoCooperante.getIdTipoOrganismo().getIdTipoOrganismo());
            this.regionSelected.setIdRegion(this.organismoCooperante.getIdRegion());
            this.paisSelected.setIdPais(this.organismoCooperante.getIdPais());
            telefonoFijo = new Telefono();
            List<Telefono> telefonosByOrganismo = telefonoService.getTelefonosByOrganismo(this.organismoCooperante);
            
            for(Telefono tel: telefonosByOrganismo){
                if(tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(FIJO)){
                     telefonoFijo=tel;
                }
            }
            
            actualizar=true;      
        } catch (Exception e) {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Al precargar registro para ser actualizado"));
        }
    }
    
       /**
     * Metodo  de Pre- borrado, que obtiene una instancia de la Entity a Borrar
     * y despliega un 'p:dialog' donde se solicita la confirmacion de la 
     * operacion de borrado
     * @param Organismo
     */
    public void preBorrar(PojoOrganismo pojoOrganismoCooperante){
        this.pojoOrganismo = pojoOrganismoCooperante;
        this.organismoCooperante = organismoService.findById(pojoOrganismoCooperante.getIdOrg());
        telefonoFijo = new Telefono();
            List<Telefono> telefonosByOrganismo = telefonoService.getTelefonosByOrganismo(this.organismoCooperante);
            
            for(Telefono tel: telefonosByOrganismo){
                if(tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(FIJO)){
                     telefonoFijo=tel;
                }
            }
        RequestContext context = RequestContext.getCurrentInstance();
        //Desplegando el 'Dialog'
        context.execute("PF('confirmDeleteOrganismoDlg').show();");
    }
    
      public void onchangeRegion() {
        try {
            if (regionSelected.getIdRegion()!= null && !regionSelected.getIdRegion().equals("")) {
                paisList = paisService.getPaisesByRegionId(regionSelected.getIdRegion());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      
      
     /**
     * Metodo que borra una instancia de 'Carrera' de la Base de datos
     */
    public void borrar(){ 
        String msg ="Organismo Cooperante Eliminado Exitosamente!";
        try{
            //Borrando la instancia del Organismo Cooperante
            telefonoService.delete(telefonoFijo);
            organismoService.delete(organismoCooperante);
            init();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('PersonaDeleteDialog').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminado!!", msg));
        }catch(Exception e){
              tipoTelefono=tipoTelefonoService.getTipoByDesc(FIJO);
            telefonoFijo.setIdOrganismo(organismoCooperante);
            telefonoFijo.setIdTipoTelefono(tipoTelefono);
            telefonoService.save(telefonoFijo);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Organismo Cooperante! "));
            e.printStackTrace();
        }finally{
            actualizar = false;
        }
        
        
    } 
      
     public void cancelarOrganismo(){
        String msg ="Organismo cancelado";
        try{
        organismoCooperante = null;
        organismoCooperante = new Organismo();
        RequestContext.getCurrentInstance().reset(":formOrganismo");
         if(actualizar)
        JsfUtil.addSuccessMessage(msg);
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
      inicializarVariables();
    }
     
     public void listarOnCatChange() {
        try {
            organismoPojoList = organismoService.getOrganismosPorTipoYPais(tipoSelected, nPaisSelected);
        } catch (Exception e) {
        }

    }
     //metodo para crear un nuevo elemento en el combo box tipo de organismo
     public void addNewTipoOrganismo() {
         TipoOrganismo tipoorganismo = tipoOrganismoService.findById(organismoSelected.getIdTipoOrganismo());
        if (tipoorganismo.getNombreTipo().equals("Agregar Nuevo")) {
            tipoOrganismoMB.init();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('tipoorganismoDialog').show()");
            organismoSelected = new TipoOrganismo();
        }
    }
     
         //Metodo que se encarga de mostrar mascara en los telefonos
    public void onchangePais() {
         codigoPais = paisService.findById(paisSelected.getIdPais()).getCodigoPais();
        mascaraTelefono = telefonoService.getMask(codigoPais);
       // mascaraTelefono = telefonoService.getMask(paisSelected.getCodigoPais());
    }
     
    public Integer getTipoSelected() {
        return tipoSelected;
    }

    public void setTipoSelected(Integer tipoSelected) {
        this.tipoSelected = tipoSelected;
    } 
    public List<TipoOrganismo> getTipoOrganismoList() {
        return tipoOrganismoList;
    }

    public void setTipoOrganismoList(List<TipoOrganismo> tipoOrganismoList) {
        this.tipoOrganismoList = tipoOrganismoList;
    }

        public Organismo getOrganismoCooperante() {
        return organismoCooperante;
    }

    public void setOrganismoCooperante(Organismo organismoCooperante) {
        this.organismoCooperante = organismoCooperante;
    }
    public TipoOrganismo getOrganismoSelected() {
        return organismoSelected;
    }

    public void setOrganismoSelected(TipoOrganismo organismoSelected) {
        this.organismoSelected = organismoSelected;
    }

    public List<Organismo> getOrganismosList() {
        return organismosList;
    }

    public void setOrganismosList(List<Organismo> organismosList) {
        this.organismosList = organismosList;
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
     
    public List<Region> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<Region> regionList) {
        this.regionList = regionList;
    }

    public Region getRegionSelected() {
        return regionSelected;
    }

    public void setRegionSelected(Region regionSelected) {
        this.regionSelected = regionSelected;
    }
    
    public Integer getnPaisSelected() {
        return nPaisSelected;
    }

    public void setnPaisSelected(Integer nPaisSelected) {
        this.nPaisSelected = nPaisSelected;
    }
    
    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }
    public List<PojoPais> getPaisPojoList() {
        return paisPojoList;
    }

    public void setPaisPojoList(List<PojoPais> paisPojoList) {
        this.paisPojoList = paisPojoList;
    }

    public PojoPais getPojoPaisSelected() {
        return pojoPaisSelected;
    }
    
    public PojoOrganismo getPojoOrganismo() {
        return pojoOrganismo;
    }

    public void setPojoOrganismo(PojoOrganismo pojoOrganismo) {
        this.pojoOrganismo = pojoOrganismo;
    }   
    
       public List<PojoOrganismo> getOrganismoPojoList() {
        return organismoPojoList;
    }

    public void setOrganismoPojoList(List<PojoOrganismo> organismoPojoList) {
        this.organismoPojoList = organismoPojoList;
    }
 

    public void setPojoPaisSelected(PojoPais pojoPaisSelected) {
        this.pojoPaisSelected = pojoPaisSelected;
    }

    public PojoPais getPojoToShow() {
        return pojoToShow;
    }

    public void setPojoToShow(PojoPais pojoToShow) {
        this.pojoToShow = pojoToShow;
    }

     public Telefono getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(Telefono telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public TipoTelefono getTipoTelefono() {
        return tipoTelefono;
    }

    public void setTipoTelefono(TipoTelefono tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    public String getMascaraTelefono() {
        return mascaraTelefono;
    }

    public void setMascaraTelefono(String mascaraTelefono) {
        this.mascaraTelefono = mascaraTelefono;
    }
}
