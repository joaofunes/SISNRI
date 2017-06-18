/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.managedbean.form.CancelarConvenioForm;
import com.sisrni.model.PersonaPropuesta;
import com.sisrni.model.PropuestaConvenio;
import com.sisrni.model.SsRoles;
import com.sisrni.pojo.rpt.PojoPropuestaConvenio;
import com.sisrni.security.AppUserDetails;
import com.sisrni.service.DocumentoService;
import com.sisrni.service.EstadoService;
import com.sisrni.service.FreeMarkerMailService;
import com.sisrni.service.PersonaPropuestaService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.PropuestaConvenioService;
import com.sisrni.service.PropuestaEstadoService;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Joao
 */
@Named("consultarPropuestaConvenioCanceladasMB")
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class ConsultarPropuestaConvenioCanceladasMB extends CancelarConvenioForm implements Serializable {

    private static final long serialVersionUID = 1L;

   
    private final List<String> ROL = Arrays.asList("ROL_ADM_CONV", "ROL_ADMI");

    private SsRoles rol;

    private CurrentUserSessionBean user;
    private AppUserDetails usuario;

    

    @Autowired
    FreeMarkerMailService mailService;

    @Autowired
    @Qualifier(value = "propuestaConvenioService")
    private PropuestaConvenioService propuestaConvenioService;

    @Autowired
    @Qualifier(value = "personaService")
    private PersonaService personaService;

    @Autowired
    @Qualifier(value = "estadoService")
    private EstadoService estadoService;

    @Autowired
    @Qualifier(value = "propuestaEstadoService")
    private PropuestaEstadoService propuestaEstadoService;

    @Autowired
    @Qualifier(value = "documentoService")
    private DocumentoService documentoService;

    @Autowired
    @Qualifier(value = "personaPropuestaService")
    private PersonaPropuestaService personaPropuestaService;

    @Autowired
    @ManagedProperty("#{globalCounterView}")
    private GlobalCounterView globalCounter;
   
    private List<PojoPropuestaConvenio> listadoPropuestaConvenio;
    private PropuestaConvenio propuestaConvenio;
    private PojoPropuestaConvenio pojoPropuestaConvenio;
    private boolean flagBanderaVigencia;
   
    //@PostConstruct
    public void init() {
        try {

            inicializador();

        } catch (Exception e) {
        }
    }

    private void inicializador() {
        try {
            usuario = null;
            user = new CurrentUserSessionBean();
            usuario = user.getSessionUser();
            cargarUsuario();

            propuestaConvenio = new PropuestaConvenio();
            
            if (rol != null) {
                listadoPropuestaConvenio = propuestaConvenioService.getAllPropuestaCanceladasConvenioSQL();
            } else {
                listadoPropuestaConvenio = propuestaConvenioService.getAllPropuestaConveniCanceladasSQL(usuario.getUsuario().getIdPersona());
            }

            Collections.sort(listadoPropuestaConvenio, new Comparator<PojoPropuestaConvenio>() {
                @Override
                public int compare(PojoPropuestaConvenio lhs, PojoPropuestaConvenio rhs) {
                    return rhs.getID_PROPUESTA().compareTo(lhs.getID_PROPUESTA());
                }
            });

            globalCounter.increment(propuetasEnRevision());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * cargar datos de usuario logeado
     */
    private void cargarUsuario() {
        try {
            rol = null;
            if (usuario != null && usuario.getUsuario() != null) {
                for (SsRoles rols : usuario.getUsuario().getSsRolesList()) {
                    for (String rl : ROL) {
                        if (rols.getCodigoRol().equalsIgnoreCase(rl)) {
                            rol = new SsRoles();
                            rol = rols;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Metodo que actualiza el listado de estado seleccionable por cada
     * propuesta convenio
     *
     * @param pojo
     */
    public void preCambiarEstado(PojoPropuestaConvenio pojo) {
        try {
            pojoPropuestaConvenio = propuestaConvenioService.getAllPropuestaConvenioSQLByID(pojo.getID_PROPUESTA());
            propuestaConvenio = propuestaConvenioService.getByID(pojoPropuestaConvenio.getID_PROPUESTA());
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    public void preEliminar(PojoPropuestaConvenio pojo) {
        try {
            pojoPropuestaConvenio = propuestaConvenioService.getAllPropuestaConvenioSQLByID(pojo.getID_PROPUESTA());
            
            // RequestContext context = RequestContext.getCurrentInstance();              
            // context.execute("PF('dataChangeDlg').show();");
            //RequestContext.getCurrentInstance().update(":formPrincipal");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    /**
     * Metodo para envio de correo informativo de creacion de propuesta
     */
    public void enviarCorreo() {
        try {

            // propuestaConvenio = propuestaConvenioService.getByIDPropuestaWithPersona(propuestaConvenio.getIdPropuesta());
            propuestaConvenio = propuestaConvenioService.getByIDPropuestaWithPersona(propuestaConvenio.getIdPropuesta());

            // Create data for template
            Map<String, Object> templateData = new HashMap<String, Object>();
            templateData.put("subJect", "Cambio de estado propuesta de convenio");

            //templateData.put("nameTemplate", "propuesta_convenio_mailTemplat.txt");
            templateData.put("nameTemplate", "estado_propuesta_convenio_mailTemplat.xhtml");
            templateData.put("propuesta", propuestaConvenio);
            templateData.put("PersonaPropuesta", propuestaConvenio.getPersonaPropuestaList());
            
            for (PersonaPropuesta p : propuestaConvenio.getPersonaPropuestaList()) {
                templateData.put("setToMail", p.getPersona().getEmailPersona());

                //mailService.sendEmail(propuestaConvenio, "Creacion de propuesta de convenio", "joao.hfunes@gmail.com", "propuesta_convenio_mailTemplat.txt");
                mailService.sendEmailMap(templateData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    /**
     * metodo para pre eliminar convenio
     *
     * @param pojo
     */
    public void preEliminarConvenio(PojoPropuestaConvenio pojo) {
        try {
            pojoPropuestaConvenio = propuestaConvenioService.getAllPropuestaConvenioSQLByID(pojo.getID_PROPUESTA());
            propuestaConvenio = propuestaConvenioService.getByID(pojoPropuestaConvenio.getID_PROPUESTA());

        } catch (Exception e) {
            String message = "Error Seleccinando Convenio : " + e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    
    public Integer propuetasEnRevision() {
        return propuestaConvenioService.conteoPropuestasEnRevision();
    }

    public PropuestaConvenio getPropuestaConvenio() {
        return propuestaConvenio;
    }

    public void setPropuestaConvenio(PropuestaConvenio propuestaConvenio) {
        this.propuestaConvenio = propuestaConvenio;
    }

    public List<PojoPropuestaConvenio> getListadoPropuestaConvenio() {
        return listadoPropuestaConvenio;
    }

    public void setListadoPropuestaConvenio(List<PojoPropuestaConvenio> listadoPropuestaConvenio) {
        this.listadoPropuestaConvenio = listadoPropuestaConvenio;
    }

    public PojoPropuestaConvenio getPojoPropuestaConvenio() {
        return pojoPropuestaConvenio;
    }

    public void setPojoPropuestaConvenio(PojoPropuestaConvenio pojoPropuestaConvenio) {
        this.pojoPropuestaConvenio = pojoPropuestaConvenio;
    }

    public boolean isFlagBanderaVigencia() {
        return flagBanderaVigencia;
    }

    public void setFlagBanderaVigencia(boolean flagBanderaVigencia) {
        this.flagBanderaVigencia = flagBanderaVigencia;
    }
    
    public SsRoles getRol() {
        return rol;
    }

    public void setRol(SsRoles rol) {
        this.rol = rol;
    }

    public CurrentUserSessionBean getUser() {
        return user;
    }

    public void setUser(CurrentUserSessionBean user) {
        this.user = user;
    }

    public AppUserDetails getUsuario() {
        return usuario;
    }

    public void setUsuario(AppUserDetails usuario) {
        this.usuario = usuario;
    }

    public GlobalCounterView getGlobalCounter() {
        return globalCounter;
    }

    public void setGlobalCounter(GlobalCounterView globalCounter) {
        this.globalCounter = globalCounter;
    }

    
}
