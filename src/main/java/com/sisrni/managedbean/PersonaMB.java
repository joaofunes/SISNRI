/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;


import com.sisrni.model.Organismo;
import com.sisrni.model.Persona;
import com.sisrni.model.Telefono;
import com.sisrni.model.TipoPersona;
import com.sisrni.model.TipoTelefono;
import com.sisrni.model.EscuelaDepartamento;
import com.sisrni.model.Facultad;
import com.sisrni.model.SsUsuarios;
import com.sisrni.model.Unidad;
import com.sisrni.pojo.rpt.PojoFacultadesUnidades;
import com.sisrni.pojo.rpt.PojoPersonaTelefono;
import com.sisrni.security.CustomPasswordEncoder;
import com.sisrni.service.EscuelaDepartamentoService;
import com.sisrni.service.FacultadService;
import com.sisrni.service.FreeMarkerMailService;
import com.sisrni.service.OrganismoService;
import com.sisrni.service.PaisService;
import com.sisrni.service.PersonaMovilidadService;
import com.sisrni.service.PersonaPropuestaService;
import com.sisrni.service.PersonaProyectoService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.SsUsuariosService;
import com.sisrni.service.TelefonoService;
import com.sisrni.service.TipoPersonaService;
import com.sisrni.service.TipoTelefonoService;
import com.sisrni.service.UnidadService;
import com.sisrni.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Joao
 */

@Named("personaMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PersonaMB implements Serializable{
    
    private static final long serialVersionUID = 1L;  
    
    private static final String FIJO="FIJO";
    private static final String CELULAR="CELULAR";
    
    private List<Organismo> listadoOrganismo;
    private List<EscuelaDepartamento> listadoUnidad;
    private List<TipoPersona> listadoTipoPersona;
    
    private List<Persona> listaPersona;
    private List<Persona> listaPersonaExtrajera;
    private List<Persona> listaPersonaUsuario;
    private Persona selected;
    private Organismo organismo;
    
    
    private PojoPersonaTelefono pojoPersona;
    private List<PojoPersonaTelefono> listPojoPersona;
    
    private PojoPersonaTelefono pojoPersonaExtranjera;
    private List<PojoPersonaTelefono> listPojoPersonaExtranjera;
    private List<PojoFacultadesUnidades> listFacultadUnidad;
    private List<Facultad> listFacultadBnfUes;
    private List<Unidad> listUnidadBnfUes;
    private registrarMovilidadMB registrarMovilidadMB;
    private List<Telefono> listadoTelefono;
    private String clave;
    private String codigo;
    public SsUsuarios usuario;
    public SsUsuarios usuario2;
    public Persona persona;
    private TipoPersona tipoPersona;
    private Telefono telefonoFijo;
    private Telefono telefonoCell;
    private TipoTelefono tipoTelefono;
    private CharSequence charSequence;
    public String nDocumento;
    private String facultadDeReferente;
    private List<PojoFacultadesUnidades> listFacultadUnidadReferenteFactBnf;
    private Boolean mostrarEscuelaReferente;
    private Boolean escuelaReferenteRequerido;
    private List<EscuelaDepartamento> listEscuelaDepartamentoRefFact;
    private Persona personaFacultadGenerico;
    //Mascara de telefonos de personas externas
    private String codigoPais;
    private String mascaraTelefono;
    
     
    @Autowired
    @Qualifier(value = "organismoService")
    private OrganismoService organismoService;
    
    @Autowired
    @Qualifier(value = "telefonoService")
    private TelefonoService telefonoService;
    
    @Autowired
    @Qualifier(value = "unidadService")
    private UnidadService unidadService;
    
    @Autowired
    @Qualifier(value = "personaService")
    private PersonaService personaService;
    
    @Autowired
    @Qualifier(value = "tipoPersonaService")
    private TipoPersonaService tipoPersonaService;
    
    @Autowired
    @Qualifier(value = "tipoTelefonoService")
    private TipoTelefonoService tipoTelefonoService;
    
    @Autowired
    @Qualifier(value = "ssUsuariosService")
    private SsUsuariosService ssUsuariosService;
    
    @Autowired
    private CustomPasswordEncoder passwordEncoder;
    
    @Autowired
    FreeMarkerMailService mailService;
    
    @Autowired
    private FacultadService facultadService;
    
    @Autowired
    private EscuelaDepartamentoService escuelaDepartamentoService;
    
    @Autowired
    private PaisService paisService;
    
   @Autowired
   private PersonaMovilidadService personaMovilidadService;
    
   @Autowired
   private PersonaProyectoService personaProyectoService;
   
   @Autowired
   private PersonaPropuestaService personaPropuestaService;
    
    //declaracion de listas
    @PostConstruct
    public void init() {
        inicializador();
        inicializarListas();
    }

    private void inicializador() {
        try {
            usuario = new SsUsuarios();
            usuario2 = new SsUsuarios();
            persona = new Persona();
            telefonoFijo = new Telefono();
            telefonoCell = new Telefono();
            personaFacultadGenerico = new Persona();
            facultadDeReferente = "";
            clave = "";
            codigo = "";
            //Mascara de telenonos de personas externas
            codigoPais = "";
            mascaraTelefono = "";
            mostrarEscuelaReferente = false;
            escuelaReferenteRequerido = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     private void inicializarListas() {
         try {
             listadoOrganismo = organismoService.getAllByIdDesc();
             //listadoUnidad = unidadService.getAllByIdDesc();
             listadoTipoPersona = tipoPersonaService.getAllByIdDesc();            
             listadoTelefono = telefonoService.findAll();
             listaPersona = personaService.getAllByIdDesc();
             listFacultadBnfUes = facultadService.getFacultadesByUniversidad(1); //revisar esto
             listUnidadBnfUes = unidadService.getUnidadesByUniversidad(1);     //revisar esto
             listFacultadUnidadReferenteFactBnf = getListFacultadesUnidades(listFacultadBnfUes, listUnidadBnfUes);//revisar esto
             llenarPojoPersona(); 
             llenarPojoPersonaExtranjera(); 
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    /**
     * Metodo para llenar pojo para personas
     */ 
    private void llenarPojoPersona() {
        try {
            listPojoPersona = new ArrayList<PojoPersonaTelefono>();
            listaPersona=personaService.getPersonaList2(false);
            for (Persona prs : listaPersona) {
                pojoPersona = new PojoPersonaTelefono();
                pojoPersona.setPersona(prs);

                listadoTelefono = telefonoService.getTelefonosByPersona(prs);

                for (Telefono tel : listadoTelefono) {
                    if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(FIJO)) {
                        pojoPersona.setTelefonoFijo(tel);                      
                    }
                    if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(CELULAR)) {
                        pojoPersona.setTelefonoCelular(tel); 
                    }
                }
                if(pojoPersona.getPersona().getExtranjero()==false){
                   listPojoPersona.add(pojoPersona);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Metodo para llenar pojo para personas
     */ 
    private void llenarPojoPersonaExtranjera() {
        try {
            listPojoPersonaExtranjera = new ArrayList<PojoPersonaTelefono>();
            listaPersonaExtrajera=personaService.getPersonaList2(true);
            for (Persona prs : listaPersonaExtrajera) {
                pojoPersona = new PojoPersonaTelefono();
                pojoPersona.setPersona(prs);

                listadoTelefono = telefonoService.getTelefonosByPersona(prs);

                for (Telefono tel : listadoTelefono) {
                    if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(FIJO)) {
                        pojoPersona.setTelefonoFijo(tel);                      
                    }
                    if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(CELULAR)) {
                        pojoPersona.setTelefonoCelular(tel); 
                    }
                }

                listPojoPersonaExtranjera.add(pojoPersona);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     public void crearPersona(){
         try {
              persona = new Persona();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
     
    /**
     * Metodo para almacenar una nueva persona
     */ 
    public void guardar(){
        try {
            String msg = "Persona Almacenado Exitosamente!";  
            personaFacultadGenerico = new Persona();
            personaFacultadGenerico= personaService.getPersonaByEmail(persona.getEmailPersona());
            if(personaFacultadGenerico==null){
            organismo= organismoService.findById(1);
            
            persona.setExtranjero(false);
            persona.setIdOrganismo(organismo);
            personaService.save(persona);
            
            tipoTelefono=tipoTelefonoService.getTipoByDesc(FIJO);
            telefonoFijo.setIdTipoTelefono(tipoTelefono);
            telefonoFijo.setIdPersona(persona);
            telefonoService.save(telefonoFijo);
                    
            tipoTelefono=tipoTelefonoService.getTipoByDesc(CELULAR);
            telefonoCell.setIdTipoTelefono(tipoTelefono);
            telefonoCell.setIdPersona(persona);
            telefonoService.save(telefonoCell);
             RequestContext context = RequestContext.getCurrentInstance();    
             context.execute("PF('PersonaCreateDialog').hide();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado", msg));
            init();        
            FacesContext.getCurrentInstance().getExternalContext().redirect("List.xhtml");
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Duplicado", "Ya hay informacion vinculada a este correo electronico"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
        
    /**
     * Metodo para almacenar una nueva persona extranjera
     */ 
    public void guardarExtranjero(){
        try {
            String msg = "Persona Almacenado Exitosamente!";   
            personaFacultadGenerico = new Persona();
            personaFacultadGenerico= personaService.getPersonaByEmail(persona.getEmailPersona());
            if(personaFacultadGenerico==null){
            persona.setExtranjero(true);
            personaService.save(persona);
            
            tipoTelefono=tipoTelefonoService.getTipoByDesc(FIJO);
            telefonoFijo.setIdTipoTelefono(tipoTelefono);
            telefonoFijo.setIdPersona(persona);
            telefonoService.save(telefonoFijo);
                    
            tipoTelefono=tipoTelefonoService.getTipoByDesc(CELULAR);
            telefonoCell.setIdTipoTelefono(tipoTelefono);
            telefonoCell.setIdPersona(persona);
            telefonoService.save(telefonoCell);
       
            RequestContext context = RequestContext.getCurrentInstance();    
            context.execute("PF('PersonaCreateDialog').hide();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado", msg));
            init();        
            FacesContext.getCurrentInstance().getExternalContext().redirect("ListExtranjera.xhtml");
             }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Duplicado", "Ya hay informacion vinculada a este correo electronico"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    /**
     * Metodo para almacenar una nueva persona y un Usuario
     */ 
    public void guardarUsuarioPersona(){
        try {
            String msg = "Persona Almacenado Exitosamente!";   
            
            tipoTelefono=tipoTelefonoService.getTipoByDesc(FIJO);
            telefonoFijo.setIdTipoTelefono(tipoTelefono);
            telefonoService.save(telefonoFijo);
                    
            tipoTelefono=tipoTelefonoService.getTipoByDesc(CELULAR);
            telefonoCell.setIdTipoTelefono(tipoTelefono);
            telefonoService.save(telefonoCell);
            persona.setExtranjero(false);
            personaService.save(persona);
            
            String encode = passwordEncoder.encode(clave);
            
            //usuario.setClave(passwordEncoder.encode(clave));
            usuario.setClave(encode);
            
            usuario.setFechaRegistro(new Date());
            usuario.setIdPersona(persona.getIdPersona());
            usuario.setNombreUsuario(persona.getNombrePersona()+ " " + persona.getApellidoPersona());
            usuario.setCargo(persona.getCargoPersona());
            ssUsuariosService.save(usuario);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado", msg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

    
    
    /**
     * Metodo para setear persona a ser editada
     * @param persona 
     */
    public void preEditar(Persona persona){
        try {
            this.persona=persona;
            telefonoFijo = new Telefono();
            telefonoCell = new Telefono();
            Unidad unidadRft = new Unidad();
            Persona personaFacultadSelected = new Persona();
            List<Telefono> telefonosByPersona = telefonoService.getTelefonosByPersona(persona);
            
            for(Telefono tel: telefonosByPersona){
                if(tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(FIJO)){
                     telefonoFijo=tel;
                }
                if(tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(CELULAR)){
                     telefonoCell=tel;
                }
            }
            
             EscuelaDepartamento escuelaDepartamentoRft = new EscuelaDepartamento();   
            //personaFacultadGenerico = persona;
               

                    //Obteniendo escuela_departamento y facultad de la persona seleccionada
                    if ((escuelaDepartamentoRft = persona.getIdEscuelaDepto()) != null) {
                        listEscuelaDepartamentoRefFact = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(escuelaDepartamentoRft.getIdFacultad().getIdFacultad());
                        //escuelaDepartamentoReferenteFactBnfSelected = escuelaDepartamentoRft.getIdEscuelaDepto();
                        facultadDeReferente = escuelaDepartamentoRft.getIdFacultad().getIdFacultad().toString() + ",1";
                        mostrarEscuelaReferente = false;
                        escuelaReferenteRequerido = true;
                    }
                    if ((unidadRft = persona.getIdUnidad()) != null) {
                        facultadDeReferente = unidadRft.getIdUnidad().toString() + ",2";
                        listEscuelaDepartamentoRefFact = new ArrayList<EscuelaDepartamento>();
                        mostrarEscuelaReferente = true;
                        escuelaReferenteRequerido = false;
                    }

           
        } catch (Exception e) {
        }
    }
    
     /**
     * Metodo para editar una persona Interna
     */ 
    public void editar(){
        try {
            String msg = "Persona Editada Exitosamente!";  
             boolean guardar = false;
            personaFacultadGenerico= personaService.getPersonaByEmail(persona.getEmailPersona());
            if(personaFacultadGenerico!=null){
                if( personaFacultadGenerico.getIdPersona()==persona.getIdPersona())
                  guardar = true;
                else
                   guardar = false;
            }else
                guardar = true;
            
                if( guardar){
            //Telefonos
            persona.getTelefonoList().clear();
            //Fijo
             telefonoFijo.setIdPersona(persona);
             telefonoFijo.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(FIJO));
             persona.getTelefonoList().add(telefonoFijo);
            //Cel
            telefonoCell.setIdPersona(persona);
            telefonoCell.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(CELULAR));
            persona.getTelefonoList().add(telefonoCell);
                
            personaService.merge(persona);   
            llenarPojoPersona(); 
            llenarPojoPersonaExtranjera(); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Editado", msg));
            RequestContext context = RequestContext.getCurrentInstance(); 
            context.execute("PF('PersonaEditDialog').hide()");
            init();        
            FacesContext.getCurrentInstance().getExternalContext().redirect("List.xhtml");
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Duplicado", "Ya hay informacion vinculada a este correo electronico"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
        
    
    /**
     * Metodo para setear persona extranjera a ser editada
     * @param persona 
     */
    public void preEditarExtranjero(Persona persona){
        try {
            this.persona=persona;
            telefonoFijo = new Telefono();
            telefonoCell = new Telefono();
            List<Telefono> telefonosByPersona = telefonoService.getTelefonosByPersona(persona);
            
            for(Telefono tel: telefonosByPersona){
                if(tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(FIJO)){
                     telefonoFijo=tel;
                }
                if(tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(CELULAR)){
                     telefonoCell=tel;
                }
            }
           onchangeListInstitucionPersona();
        } catch (Exception e) {
        }
    }
    
    
      /**
     * Metodo para editar una persona Externa
     */ 
    public void editarExterna(){
        try {
            String msg = "Persona Editada Exitosamente!"; 
            boolean guardar = false;
            personaFacultadGenerico= personaService.getPersonaByEmail(persona.getEmailPersona());
            if(personaFacultadGenerico!=null){
                if( personaFacultadGenerico.getIdPersona()==persona.getIdPersona())
                  guardar = true;
                else
                   guardar = false;
            }else
                guardar = true;
            
                if( guardar){
                    persona.getTelefonoList().clear();
                    //Fijo
                    telefonoFijo.setIdPersona(persona);
                    telefonoFijo.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(FIJO));
                    persona.getTelefonoList().add(telefonoFijo);
                    //Cel
                    telefonoCell.setIdPersona(persona);
                    telefonoCell.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(CELULAR));
                    persona.getTelefonoList().add(telefonoCell);
                    
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Editado", msg));
                    personaService.merge(persona);
                    RequestContext context = RequestContext.getCurrentInstance(); 
                    context.execute("PF('PersonaEditDialog').hide()");
                    init();        
                    FacesContext.getCurrentInstance().getExternalContext().redirect("ListExtranjera.xhtml");
                }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Duplicado", "Ya hay informacion vinculada a este correo electronico"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    /**
     * Metodo  de Pre- borrado, que obtiene una instancia de la Entity a Borrar
     * y despliega un 'p:dialog' donde se solicita la confirmacion de la 
     * operacion de borrado
     * @param persona
     */
    public void preBorradoPersona(Persona persona){
    try {
                this.persona = persona;
             RequestContext context = RequestContext.getCurrentInstance();              
             //context.execute("PF('dataChangeDlg').show();");
         } catch (Exception e) {
         }
    }
    
    /**
     * Metodo que borra una instancia de 'Persona' de la Base de datos
     */
    public void borrarPersona(){ 
        Integer movilidad = 0;
        Integer proyecto = 0;
        Integer propuesta = 0;
        usuario = new SsUsuarios();
        String msg ="Persona Eliminada Exitosamente!";
        try{
            movilidad = personaMovilidadService.getCount(persona.getIdPersona());
            if(movilidad==0){
                proyecto = personaProyectoService.getCount(persona.getIdPersona());
                if(proyecto==0){
                    propuesta = personaPropuestaService.getCount(persona.getIdPersona());
                    if(propuesta==0){
                         usuario = ssUsuariosService.findByIdPersona(persona.getIdPersona());
                         if(usuario==null){
                          //Borrando la instancia de persona
                        personaService.delete(persona);
                        init();
                        RequestContext context = RequestContext.getCurrentInstance();
                        context.execute("PF('PersonaDeleteDialog').hide();"); 
                        FacesContext.getCurrentInstance().getExternalContext().redirect("List.xhtml");
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminado!!", msg));
                         }else{
                      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error!!", "La persona no se puede eliminar porque esta vinculada a un Usuario"));
                     }
                   }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error!!", "La persona no se puede eliminar porque esta vinculada a un Convenio"));
                  }
                 }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error!!", "La persona no se puede eliminar porque esta vinculada a un Proyecto"));
                  }
            }  else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error!!", "La persona no se puede eliminar porque esta vinculada a una Movilidad"));
                  }
        }catch(Exception e){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Persona!"));
            e.printStackTrace();
        }
        
        
    }
    
    /**
     * Metodo que borra una instancia de 'Persona Externa' de la Base de datos
     */
    public void borrarPersonaExterna(){ 
        Integer movilidad = 0;
        Integer proyecto = 0;
        Integer propuesta = 0;
        usuario = new SsUsuarios();
        String msg ="Persona Eliminada Exitosamente!";
        try{
            movilidad = personaMovilidadService.getCount(persona.getIdPersona());
            if(movilidad==0){
                proyecto = personaProyectoService.getCount(persona.getIdPersona());
                if(proyecto==0){
                    propuesta = personaPropuestaService.getCount(persona.getIdPersona());
                    if(propuesta==0){
                         usuario = ssUsuariosService.findByIdPersona(persona.getIdPersona());
                         if(usuario==null){
                          //Borrando la instancia de persona
                        personaService.delete(persona);
                        init();
                        RequestContext context = RequestContext.getCurrentInstance();
                        context.execute("PF('PersonaDeleteDialog').hide();"); 
                        FacesContext.getCurrentInstance().getExternalContext().redirect("ListExtranjera.xhtml");
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminado!!", msg));
                         }else{
                      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error!!", "La persona no se puede eliminar porque esta vinculada a un Usuario"));
                     }
                   }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error!!", "La persona no se puede eliminar porque esta vinculada a un Convenio"));
                  }
                 }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error!!", "La persona no se puede eliminar porque esta vinculada a un Proyecto"));
                  }
            }  else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error!!", "La persona no se puede eliminar porque esta vinculada a una Movilidad"));
                  }
        }catch(Exception e){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Persona!"));
            e.printStackTrace();
        }
        
        
    }
        /**
     * Metodo que se encarga de limpiar el formulario de creacion y 
     * actualizacion de Ciudad
     */
    public void cancelarPersona(){
        String msg ="Accion Cancelada";
        try{
        persona = null;
        persona = new Persona();
        RequestContext.getCurrentInstance().reset(":formNewPersona");
        RequestContext context = RequestContext.getCurrentInstance();    
        context.execute("PF('PersonaEditDialog').hide()");
        context.execute("PF('PersonaDeleteDialog').hide()"); 
         //if(actualizar)
        JsfUtil.addSuccessMessage(msg);
        //FacesContext.getCurrentInstance().getExternalContext().redirect("List.xhtml");
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
       init();
    }
    
    public void mostrarEscuelaReferenteFact() {
        int result = -1;
        if ((result = facultadDeReferente.indexOf(",2")) > -1) {
            mostrarEscuelaReferente = true;
            escuelaReferenteRequerido = false;
            onchangeListFacultadReferente();
        } else {
            mostrarEscuelaReferente = false;
            escuelaReferenteRequerido = true;
            onchangeListFacultadReferente();
        }
    }
    
    
     public void onchangeListFacultadReferente() {
     int result = -1;
     Integer id;
     if ((result = facultadDeReferente.indexOf(",1")) > -1) {
         id = Integer.parseInt(facultadDeReferente.substring(0, result));
         listEscuelaDepartamentoRefFact = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(id);
         persona.setIdUnidad(null);
     } else if ((result = facultadDeReferente.indexOf(",2")) > -1) {
         id = Integer.parseInt(facultadDeReferente.substring(0, result));
         //Afrefando la unidad seleccionada a una variable temporal
         //unidadRftFactTmp = unidadService.findById(id);
         persona.setIdUnidad(unidadService.findById(id));
         persona.setIdEscuelaDepto(null);
         listEscuelaDepartamentoRefFact = new ArrayList<EscuelaDepartamento>();

     }
 }
    
     /**
     * Metodo  de Pre- borrado, que obtiene una instancia de la Entity a Borrar
     * y despliega un 'p:dialog' donde se solicita la confirmacion de la 
     * operacion de borrado
     * @param Persona
     */
        public void preEliminar(Persona persona){
            this.persona=persona;
            telefonoFijo = new Telefono();
            telefonoCell = new Telefono();
            Unidad unidadRft = new Unidad();
            Persona personaFacultadSelected = new Persona();
            List<Telefono> telefonosByPersona = telefonoService.getTelefonosByPersona(persona);
            
            for(Telefono tel: telefonosByPersona){
                if(tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(FIJO)){
                     telefonoFijo=tel;
                }
                if(tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(CELULAR)){
                     telefonoCell=tel;
                }
            }
            
             EscuelaDepartamento escuelaDepartamentoRft = new EscuelaDepartamento();   
            //personaFacultadGenerico = persona;
               

                    //Obteniendo escuela_departamento y facultad de la persona seleccionada
                    if ((escuelaDepartamentoRft = persona.getIdEscuelaDepto()) != null) {
                        listEscuelaDepartamentoRefFact = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(escuelaDepartamentoRft.getIdFacultad().getIdFacultad());
                        //escuelaDepartamentoReferenteFactBnfSelected = escuelaDepartamentoRft.getIdEscuelaDepto();
                        facultadDeReferente = escuelaDepartamentoRft.getIdFacultad().getIdFacultad().toString() + ",1";
                        mostrarEscuelaReferente = false;
                        escuelaReferenteRequerido = true;
                    }
                    if ((unidadRft = persona.getIdUnidad()) != null) {
                        facultadDeReferente = unidadRft.getIdUnidad().toString() + ",2";
                        listEscuelaDepartamentoRefFact = new ArrayList<EscuelaDepartamento>();
                        mostrarEscuelaReferente = true;
                        escuelaReferenteRequerido = false;
                    }
    }
    
    /**
     * Metodo para eliminar una persona
     */ 
    public void eliminar(){
           String msg ="Persona Eliminada exitosamente!";
        try{
            //Borrando la instancia de la persona
            telefonoService.delete(telefonoFijo);
            telefonoService.delete(telefonoCell);
            personaService.delete(persona);
            init();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('PersonaDeleteDialog').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminada!!", msg));
        }catch(Exception e){
             tipoTelefono=tipoTelefonoService.getTipoByDesc(FIJO);
            telefonoFijo.setIdPersona(persona);
            telefonoFijo.setIdTipoTelefono(tipoTelefono);
            telefonoService.save(telefonoFijo);
            
            tipoTelefono=tipoTelefonoService.getTipoByDesc(CELULAR);
            telefonoCell.setIdTipoTelefono(tipoTelefono);
            telefonoCell.setIdPersona(persona);
            telefonoService.save(telefonoCell);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Persona! "));
            e.printStackTrace();
        }finally{
            telefonoFijo = null;
            telefonoCell = null;
            persona= new Persona();
        }
    } 
    
        /**
     * Metodo para eliminar una persona Externa
     */ 
    public void eliminarExterno(){
           String msg ="Persona Eliminada exitosamente!";
        try{
            //Borrando la instancia de la persona
            telefonoService.delete(telefonoFijo);
            telefonoService.delete(telefonoCell);
            personaService.delete(persona);
            init();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('PersonaDeleteDialog').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminada!!", msg));
        }catch(Exception e){
             tipoTelefono=tipoTelefonoService.getTipoByDesc(FIJO);
            telefonoFijo.setIdPersona(persona);
            telefonoFijo.setIdTipoTelefono(tipoTelefono);
            telefonoService.save(telefonoFijo);
            
            tipoTelefono=tipoTelefonoService.getTipoByDesc(CELULAR);
            telefonoCell.setIdTipoTelefono(tipoTelefono);
            telefonoCell.setIdPersona(persona);
            telefonoService.save(telefonoCell);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Persona! "));
            e.printStackTrace();
        }finally{
            telefonoFijo = null;
            telefonoCell = null;
            persona= new Persona();
        }
    }
    
    
    /**
     * Metodo para almacenar una nueva persona
     */ 
    public void preEditarPropuestaInterno(String nDoc){
        try {
            
            if(nDocumento!=null && !nDocumento.equals("")){
            // this.persona  = personaService.getReferenteInternoByDocumento(nDocumento);
             RequestContext context = RequestContext.getCurrentInstance();              
             context.execute("PF('PersonaEditDialog').show();");
            }     
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    /**
     * Metodo para almacenar una nueva persona
     */ 
    public void preEditarPropuestaExterno(){
        try {
            
            if(nDocumento!=null && !nDocumento.equals("")){
           //  this.persona  = personaService.getReferenteExternoByDoccumento(nDocumento);
             RequestContext context = RequestContext.getCurrentInstance();              
             context.execute("PF('PersonaEditDialog').show();");
            }     
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
       //Metodo que se encarga de mostrar mascara de personas externas en los telefonos
    public void onchangeListInstitucionPersona() {
        Organismo org = organismoService.findById(persona.getIdOrganismo().getIdOrganismo());
        codigoPais = paisService.findById(org.getIdPais()).getCodigoPais();
        mascaraTelefono = telefonoService.getMask(codigoPais);
    }
    
           /**
     * Metodo para setear persona a ser crear Usuario a persona
     * @param persona 
     */
    public void preUsuario(Persona persona){
        try {
            
            this.persona=persona;
            codigo = persona.getNombrePersona().substring(0,3)+ persona.getApellidoPersona().substring(0,2)+persona.getIdPersona().toString();
            clave = getCadenaAlfanumAleatoria (9);
           
        } catch (Exception e) {
        }
    }
    
     /**
     * Metodo para almacenar una nueva persona y un Usuario
     */ 
    public void asociarUsuario(){
        try {
            String msg = "";   
            usuario2=  ssUsuariosService.findByUser(codigo);
          if(usuario2==null){
             msg ="Usuario Creado Exitosamente!";
             usuario.setCodigoUsuario(codigo);
             clave = getCadenaAlfanumAleatoria (9); 
            String encode = passwordEncoder.encode(clave);
            usuario.setClave(encode);
             
            usuario.setFechaRegistro(new Date());
            usuario.setFechaUltimamodificacion(new Date());
            usuario.setIdPersona(persona.getIdPersona());
            usuario.setNombreUsuario(persona.getNombrePersona()+ " " + persona.getApellidoPersona());
            usuario.setCargo(persona.getCargoPersona());
            ssUsuariosService.save(usuario);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado!!", msg));
           RequestContext context = RequestContext.getCurrentInstance();
         
            Map<String, Object> templateData = new HashMap<String, Object>();
            templateData.put("subJect", "Usaurio Creado");
            templateData.put("nameTemplate", "usuario_mailTemplat.xhtml");
            templateData.put("persona", persona);
            templateData.put("usuario", usuario);
            templateData.put("clave", clave);
            templateData.put("setToMail", "marroquin-7@hotmail.com");
            mailService.sendEmailMap(templateData);
           

           context.execute("PF('UsuarioCreateDialog').close();");
           //context.update("RegistrogarantiarealListForm");
          }else{
            msg ="Ya existe este nombre de Usuario!";  
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Duplicado!!", msg));  
          }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    /**
     * Metodo para unir en una lista las Sub listas de facultad y unidad
     */
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
    
    String getCadenaAlfanumAleatoria (int longitud){
    String cadenaAleatoria = "";
    long milis = new java.util.GregorianCalendar().getTimeInMillis();
    Random r = new Random(milis);
    int i = 0;
    while ( i < longitud){
    char c = (char)r.nextInt(255);
    if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
    cadenaAleatoria += c;
        i ++;
    }
    }
    return cadenaAleatoria;
    }
    

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Organismo> getListadoOrganismo() {
        return listadoOrganismo;
    }

    public void setListadoOrganismo(List<Organismo> listadoOrganismo) {
        this.listadoOrganismo = listadoOrganismo;
    }
    
    public Organismo getOrganismo() {
        return organismo;
    }

    public void setOrganismo(Organismo organismo) {
        this.organismo = organismo;
    }
    
    public registrarMovilidadMB getRegistrarMovilidadMB() {
        return registrarMovilidadMB;
    }

    public void setRegistrarMovilidadMB(registrarMovilidadMB registrarMovilidadMB) {
        this.registrarMovilidadMB = registrarMovilidadMB;
    }
    
    public Persona getPersona() {
        return persona;
    }
    
    public SsUsuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(SsUsuarios usuario) {
        this.usuario = usuario;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<EscuelaDepartamento> getListadoUnidad() {
        return listadoUnidad;
    }

    public void setListadoUnidad(List<EscuelaDepartamento> listadoUnidad) {
        this.listadoUnidad = listadoUnidad;
    }

    public PersonaService getPersonaService() {
        return personaService;
    }

    public void setPersonaService(PersonaService personaService) {
        this.personaService = personaService;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public List<TipoPersona> getListadoTipoPersona() {
        return listadoTipoPersona;
    }

    public void setListadoTipoPersona(List<TipoPersona> listadoTipoPersona) {
        this.listadoTipoPersona = listadoTipoPersona;
    }

    public String getnDocumento() {
        return nDocumento;
    }

    public void setnDocumento(String nDocumento) {
        this.nDocumento = nDocumento;
    }

    public Telefono getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(Telefono telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public Telefono getTelefonoCell() {
        return telefonoCell;
    }

    public void setTelefonoCell(Telefono telefonoCell) {
        this.telefonoCell = telefonoCell;
    }

    public TipoTelefono getTipoTelefono() {
        return tipoTelefono;
    }

    public void setTipoTelefono(TipoTelefono tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    public List<Persona> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(List<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }

    public List<Persona> getListaPersonaExtrajera() {
        return listaPersonaExtrajera;
    }

    public void setListaPersonaExtrajera(List<Persona> listaPersonaExtrajera) {
        this.listaPersonaExtrajera = listaPersonaExtrajera;
    }

    public List<Persona> getListaPersonaUsuario() {
        return listaPersonaUsuario;
    }

    public void setListaPersonaUsuario(List<Persona> listaPersonaUsuario) {
        this.listaPersonaUsuario = listaPersonaUsuario;
    }

    public Persona getSelected() {
        return selected;
    }

    public void setSelected(Persona selected) {
        this.selected = selected;
    }

    
    public PojoPersonaTelefono getPojoPersona() {
        return pojoPersona;
    }

    public void setPojoPersona(PojoPersonaTelefono pojoPersona) {
        this.pojoPersona = pojoPersona;
    }

    public PojoPersonaTelefono getPojoPersonaExtranjera() {
        return pojoPersonaExtranjera;
    }

    public void setPojoPersonaExtranjera(PojoPersonaTelefono pojoPersonaExtranjera) {
        this.pojoPersonaExtranjera = pojoPersonaExtranjera;
    }

    public List<Telefono> getListadoTelefono() {
        return listadoTelefono;
    }

    public void setListadoTelefono(List<Telefono> listadoTelefono) {
        this.listadoTelefono = listadoTelefono;
    }

    public List<PojoPersonaTelefono> getListPojoPersona() {
        return listPojoPersona;
    }

    public void setListPojoPersona(List<PojoPersonaTelefono> listPojoPersona) {
        this.listPojoPersona = listPojoPersona;
    }

    public List<PojoPersonaTelefono> getListPojoPersonaExtranjera() {
        return listPojoPersonaExtranjera;
    }

    public void setListPojoPersonaExtranjera(List<PojoPersonaTelefono> listPojoPersonaExtranjera) {
        this.listPojoPersonaExtranjera = listPojoPersonaExtranjera;
    }


    public List<PojoFacultadesUnidades> getListFacultadUnidad() {
        return listFacultadUnidad;
    }

    public void setListFacultadUnidad(List<PojoFacultadesUnidades> listFacultadUnidad) {
        this.listFacultadUnidad = listFacultadUnidad;
    }

     public String getFacultadDeReferente() {
        return facultadDeReferente;
    }

    public void setFacultadDeReferente(String facultadDeReferente) {
        this.facultadDeReferente = facultadDeReferente;
    }

    public List<PojoFacultadesUnidades> getListFacultadUnidadReferenteFactBnf() {
        return listFacultadUnidadReferenteFactBnf;
    }

    public void setListFacultadUnidadReferenteFactBnf(List<PojoFacultadesUnidades> listFacultadUnidadReferenteFactBnf) {
        this.listFacultadUnidadReferenteFactBnf = listFacultadUnidadReferenteFactBnf;
    }

    public Boolean getMostrarEscuelaReferente() {
        return mostrarEscuelaReferente;
    }

    public void setMostrarEscuelaReferente(Boolean mostrarEscuelaReferente) {
        this.mostrarEscuelaReferente = mostrarEscuelaReferente;
    }

    public Boolean getEscuelaReferenteRequerido() {
        return escuelaReferenteRequerido;
    }

    public void setEscuelaReferenteRequerido(Boolean escuelaReferenteRequerido) {
        this.escuelaReferenteRequerido = escuelaReferenteRequerido;
    }
    

    public List<EscuelaDepartamento> getListEscuelaDepartamentoRefFact() {
        return listEscuelaDepartamentoRefFact;
    }

    public void setListEscuelaDepartamentoRefFact(List<EscuelaDepartamento> listEscuelaDepartamentoRefFact) {
        this.listEscuelaDepartamentoRefFact = listEscuelaDepartamentoRefFact;
    }

    public Persona getPersonaFacultadGenerico() {
        return personaFacultadGenerico;
    }

    public void setPersonaFacultadGenerico(Persona personaFacultadGenerico) {
        this.personaFacultadGenerico = personaFacultadGenerico;
    }
    
    public String getMascaraTelefono() {
        return mascaraTelefono;
    }

    public void setMascaraTelefono(String mascaraTelefono) {
        this.mascaraTelefono = mascaraTelefono;
    }
}
