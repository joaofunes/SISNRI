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
import com.sisrni.model.Unidad;
import com.sisrni.service.OrganismoService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.TelefonoService;
import com.sisrni.service.TipoPersonaService;
import com.sisrni.service.TipoTelefonoService;
import com.sisrni.service.UnidadService;
import java.io.Serializable;
import java.util.List;
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
    private List<Unidad> listadoUnidad;
    private List<TipoPersona> listadoTipoPersona;
    
    private List<Persona> listaPersona;
    private List<Persona> listaPersonaExtrajera;
    private List<Persona> listaPersonaUsuario;
    private Persona selected;
    
    public Persona persona;
    private TipoPersona tipoPersona;
    private Telefono telefonoFijo;
    private Telefono telefonoCell;
    private TipoTelefono tipoTelefono;
    public String nDocumento;
    
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
    
    //declaracion de listas
    @PostConstruct
    public void init() {
        inicializador();
        inicializarListas();
    }

    private void inicializador() {
        try {
            persona = new Persona();
            telefonoFijo = new Telefono();
            telefonoCell = new Telefono();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     private void inicializarListas() {
         try {
             listadoOrganismo = organismoService.findAll();
             listadoUnidad = unidadService.findAll();
             listadoTipoPersona = tipoPersonaService.findAll();
             listaPersona=personaService.getPersonaList(false);
             listaPersonaExtrajera=personaService.getPersonaList(true);
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
            
            tipoTelefono=tipoTelefonoService.getTipoByDesc(FIJO);
            telefonoFijo.setIdTipoTelefono(tipoTelefono);
            telefonoService.save(telefonoFijo);
                    
            tipoTelefono=tipoTelefonoService.getTipoByDesc(CELULAR);
            telefonoCell.setIdTipoTelefono(tipoTelefono);
            telefonoService.save(telefonoCell);
            persona.setExtranjero(false);
            personaService.save(persona);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado", msg));
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
            
            tipoTelefono=tipoTelefonoService.getTipoByDesc(FIJO);
            telefonoFijo.setIdTipoTelefono(tipoTelefono);
            telefonoService.save(telefonoFijo);
                    
            tipoTelefono=tipoTelefonoService.getTipoByDesc(CELULAR);
            telefonoCell.setIdTipoTelefono(tipoTelefono);
            telefonoService.save(telefonoCell);
            
            persona.setExtranjero(true);
            personaService.save(persona);
            
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
            List<Telefono> telefonosByPersona = telefonoService.getTelefonosByPersona(persona);
            
            for(Telefono tel: telefonosByPersona){
                if(tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(FIJO)){
                     telefonoFijo=tel;
                }
                if(tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(CELULAR)){
                     telefonoCell=tel;
                }
            }
           
        } catch (Exception e) {
        }
    }
    
    
    /**
     * Metodo para almacenar una nueva persona
     */ 
    public void editar(){
        try {
            String msg = "Persona Editada Exitosamente!";  
            telefonoService.saveOrUpdate(telefonoFijo);
            telefonoService.saveOrUpdate(telefonoCell);
            personaService.merge(persona);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Editado", msg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    
    /**
     * Metodo para eliminar una persona
     */ 
    public void eliminar(){
        try {
            String msg = "Persona Eliminada Exitosamente!";  
            if(telefonoFijo != null && telefonoFijo.getIdOrganismo()!= null){
                telefonoService.delete(telefonoFijo);
            }
            if(telefonoCell != null && telefonoCell.getIdOrganismo()!= null){
                telefonoService.delete(telefonoCell);
            }      
            
            if(persona.getIdOrganismo() != null && persona.getIdOrganismo().getIdOrganismo() != null){
             organismoService.delete(persona.getIdOrganismo());
            }
                       
            personaService.delete(persona);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Editado", msg));
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
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
             this.persona  = personaService.getReferenteInternoByDocumento(nDocumento);
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
             this.persona  = personaService.getReferenteExternoByDoccumento(nDocumento);
             RequestContext context = RequestContext.getCurrentInstance();              
             context.execute("PF('PersonaEditDialog').show();");
            }     
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
     
    public List<Organismo> getListadoOrganismo() {
        return listadoOrganismo;
    }

    public void setListadoOrganismo(List<Organismo> listadoOrganismo) {
        this.listadoOrganismo = listadoOrganismo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Unidad> getListadoUnidad() {
        return listadoUnidad;
    }

    public void setListadoUnidad(List<Unidad> listadoUnidad) {
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

    
}
