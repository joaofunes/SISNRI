/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.Documento;
import com.sisrni.model.Estado;
import com.sisrni.model.PersonaPropuesta;
import com.sisrni.model.PropuestaConvenio;
import com.sisrni.model.SsRoles;
import com.sisrni.model.TipoDocumento;
import com.sisrni.pojo.rpt.PojoPropuestaConvenio;
import com.sisrni.security.AppUserDetails;
import com.sisrni.service.DocumentoService;
import com.sisrni.service.EstadoService;
import com.sisrni.service.FreeMarkerMailService;
import com.sisrni.service.PersonaPropuestaService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.PropuestaConvenioService;
import com.sisrni.service.PropuestaEstadoService;
import com.sisrni.service.TipoDocumentoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Joao
 */
@Named("consultarPropuestaConvenioMB")
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class ConsultarPropuestaConvenioMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String FIRMADO = "FIRMADO";
    private final List<String> ROL = Arrays.asList("ROL_ADM_CONV", "ROL_ADMI");

    private static final String TIPO_DOCUMENTO="Convenio Firmado";
    private SsRoles rol;

    private CurrentUserSessionBean user;
    private AppUserDetails usuario;

    @Inject
    ActualizacionPropuestaConvenioMB actualizacionPropuestaConvenioMB;

    @Inject
    DocumentacionMB documentacionMB;

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
    
    @Autowired
    @Qualifier(value = "tipoDocumentoService")
    private TipoDocumentoService tipoDocumentoService;

    private List<PojoPropuestaConvenio> listadoPropuestaConvenio;
    private PropuestaConvenio propuestaConvenio;
    private PojoPropuestaConvenio pojoPropuestaConvenio;
    private List<Estado> listadoEstados;
    private List<Estado> listadoEstadosTemp;
    private Estado estado;
    private Estado estadoTemp;
    private Documento documento;
    private List<TipoDocumento> listTipoDocumento;
    private boolean flagBanderaVigencia;
    private TipoDocumento tipoDocumento;

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
            estado = new Estado();

            if (rol != null) {
                listadoPropuestaConvenio = propuestaConvenioService.getAllPropuestaConvenioSQL();
            } else {
                listadoPropuestaConvenio = propuestaConvenioService.getAllPropuestaConvenioSQL(usuario.getUsuario().getIdPersona());
            }

            Collections.sort(listadoPropuestaConvenio, new Comparator<PojoPropuestaConvenio>() {
                @Override
                public int compare(PojoPropuestaConvenio lhs, PojoPropuestaConvenio rhs) {
                    return rhs.getID_PROPUESTA().compareTo(lhs.getID_PROPUESTA());
                }
            });

            listadoEstados = estadoService.getEstadoPropuestasConvenio();
            listTipoDocumento = tipoDocumentoService.getTipoDocumentosByCategory(1);
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

    public void preEditar(PojoPropuestaConvenio pj) {
        try {

            actualizacionPropuestaConvenioMB.postInit();

            if (pj.getID_SOLICITANTE() != null) {
                //settea solicitante
                actualizacionPropuestaConvenioMB.preCargarSolicitante(pj.getID_SOLICITANTE());
            }
            if (pj.getID_REF_INTERNO() != null) {
                actualizacionPropuestaConvenioMB.preCargarReferenteInterno(pj.getID_REF_INTERNO());
            }
//            else{
//                actualizacionPropuestaConvenioMB.setFlagEdicionInterno(Boolean.FALSE);
//            }
            if (pj.getID_REF_EXTERNO() != null) {
                actualizacionPropuestaConvenioMB.preCargarReferenteExterno(pj.getID_REF_EXTERNO());

            }
//            else{
//                actualizacionPropuestaConvenioMB.setFlagEdicionExterno(Boolean.FALSE);
//            }

            actualizacionPropuestaConvenioMB.cargarPropuestaConvenio(pj.getID_PROPUESTA());
            actualizacionPropuestaConvenioMB.onTipoConvenioChange();
            actualizacionPropuestaConvenioMB.setPrecargar(Boolean.FALSE);

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            FacesContext.getCurrentInstance().getExternalContext().redirect("actualizarPropuestaCovenio.xhtml");

            //context.redirect(context.getRequestContextPath() + "/views/convenio/propuestaCovenio.xhtml");
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
            estado = estadoService.findById(pojo.getID_ESTADO());
            estadoTemp = new Estado();
            documento= new Documento();
            estadoTemp = estado;
            flagBanderaVigencia = false;
            listadoEstadosTemp = new ArrayList<Estado>();
            int[] intArray = new int[3];
            intArray[0] = (estado.getOrdenEstado() - 1);
            intArray[1] = estado.getOrdenEstado();
            intArray[2] = (estado.getOrdenEstado() + 1);

            for (int i = 0; i < intArray.length; i++) {
                llenarListadoEstados(intArray[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para agregar estados a listado de estados
     *
     * @param orden
     */
    public void llenarListadoEstados(int orden) {
        try {
            for (Estado std : listadoEstados) {
                if (std.getTipoEstado() == 1 && std.getOrdenEstado() == orden) {
                    listadoEstadosTemp.add(std);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void preEliminar(PojoPropuestaConvenio pojo) {
        try {
            pojoPropuestaConvenio = propuestaConvenioService.getAllPropuestaConvenioSQLByID(pojo.getID_PROPUESTA());
            estado = estadoService.findById(pojo.getID_ESTADO());
            // RequestContext context = RequestContext.getCurrentInstance();              
            // context.execute("PF('dataChangeDlg').show();");
            //RequestContext.getCurrentInstance().update(":formPrincipal");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para precargar documentos incializa la propuesta
     */
    public void preCargadocumento(PojoPropuestaConvenio pojo){
        try {
             pojoPropuestaConvenio = propuestaConvenioService.getAllPropuestaConvenioSQLByID(pojo.getID_PROPUESTA());
            propuestaConvenio = propuestaConvenioService.getByID(pojoPropuestaConvenio.getID_PROPUESTA());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    /**
     * Metodo para verificar si estado es firmado el cual pasaria de ser
     * propuesta convenio a Convenio
     */
    public void confirmacionEstadoConvenio() {
        try {
            if (estado.getNombreEstado().equalsIgnoreCase(FIRMADO)) {
                flagBanderaVigencia = true;
            } else {
                flagBanderaVigencia = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * cambiar estado de propuestas de convenio
     */
    public void cambiarEstadoConvenio() {
        try {
            RequestContext context = RequestContext.getCurrentInstance();
            Date now = new Date();
            if (estado.getNombreEstado().equalsIgnoreCase(FIRMADO)) {
                if (propuestaConvenio.getVigencia().after(now)) {
                    propuestaConvenio.setActivo(Boolean.TRUE);
                    propuestaConvenioService.merge(propuestaConvenio);
                    propuestaEstadoService.updatePropuestaEstado(pojoPropuestaConvenio.getID_PROPUESTA(), estado.getIdEstado());
                    
                    documento.setIdPropuesta(propuestaConvenio);
                    documento.setIdTipoDocumento(tipoDocumentoService.getTipoDocumento(TIPO_DOCUMENTO));
                    documento.setFechaRecibido(new Date());
                    documento.setUsuarioRecibe(usuario.getUsuario().getNombreUsuario());
                    documentoService.save(documento);
                    
                    context.execute("PF('dlgEstado').hide();");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Convenio", "la propuesta pasa a ser convenio"));
                    enviarCorreoConvenio();//camabiar porq pasa a hacer conevio
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Fecha de Vigencia debe ser mayor a la actual"));

                }
            } else {
                propuestaEstadoService.updatePropuestaEstado(pojoPropuestaConvenio.getID_PROPUESTA(), estado.getIdEstado());
                context.execute("PF('dlgEstado').hide();");
                enviarCorreo();
            }

            inicializador();
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
            templateData.put("estado", estado.getNombreEstado()); //estado actual
            templateData.put("estadoTemp", estadoTemp.getNombreEstado()); // estado anterior

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
     * Metodo para envio de correo informativo para informar que ha pasado a ser
     * Convenio
     */
    public void enviarCorreoConvenio() {
        try {

            // propuestaConvenio = propuestaConvenioService.getByIDPropuestaWithPersona(propuestaConvenio.getIdPropuesta());
            propuestaConvenio = propuestaConvenioService.getByIDPropuestaWithPersona(propuestaConvenio.getIdPropuesta());

            // Create data for template
            Map<String, Object> templateData = new HashMap<String, Object>();
            templateData.put("subJect", "Propuesta a pasado ha hacer convenio");

            templateData.put("nameTemplate", "informacion_convenio_mailTemplat.xhtml");
            templateData.put("propuesta", propuestaConvenio);
            templateData.put("PersonaPropuesta", propuestaConvenio.getPersonaPropuestaList());
            templateData.put("estado", estado.getNombreEstado()); //estado actual
            templateData.put("estadoTemp", estadoTemp.getNombreEstado()); // estado anterior

            for (PersonaPropuesta p : propuestaConvenio.getPersonaPropuestaList()) {
                templateData.put("setToMail", p.getPersona().getEmailPersona());
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

    /**
     * *
     * metodo para eliminacion de una propuesta
     */
    public void eliminarConvenio() {
        try {
            //eliminacion de personas asociadas a la propuesta
            personaPropuestaService.deleteByPersonasPropuestas(propuestaConvenio.getIdPropuesta());

            //eliminar estado de la propuesta
            propuestaEstadoService.deletePropuestaEstado(propuestaConvenio.getIdPropuesta());

            //eliminar documento
            documentoService.deleteDocumentosPropuestas(propuestaConvenio.getIdPropuesta());

            //eliminar propuesta de convenio
            propuestaConvenioService.deletePropuestas(propuestaConvenio.getIdPropuesta());

            inicializador();

        } catch (Exception e) {
            String message = "Error Eliminando Propuesta : " + e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }
    
    /**
     * *
     * Metodo para cargar documento
     *
     * @param event
     */
    public void handleFileUpload(FileUploadEvent event) {
        try {
            byte[] content = IOUtils.toByteArray(event.getFile().getInputstream());
            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            if (documento == null) {
                documento = new Documento();
            }
            documento.setDocumento(content);
            documento.setNombreDocumento(event.getFile().getFileName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
    /**
     * Metodo para agregar documentos a convenio
     */
    public void addDocument() {
        try {
            
            if ((documento != null) && (documento.getDocumento().length > 0)) {
                documento.setIdPropuesta(propuestaConvenio);
                documento.setFechaRecibido(new Date());
                documento.setUsuarioRecibe(usuario.getUsuario().getNombreUsuario());
                documentoService.save(documento);
                inicializador();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succesful", "Documento agregado exitosamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "Documento no ha sido agregado"));
            }

        } catch (Exception e) {
            String message = "Error Agregado documento : " + e.getMessage();
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

    public List<Estado> getListadoEstados() {
        return listadoEstados;
    }

    public void setListadoEstados(List<Estado> listadoEstados) {
        this.listadoEstados = listadoEstados;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getListadoEstadosTemp() {
        return listadoEstadosTemp;
    }

    public void setListadoEstadosTemp(List<Estado> listadoEstadosTemp) {
        this.listadoEstadosTemp = listadoEstadosTemp;
    }

    public boolean isFlagBanderaVigencia() {
        return flagBanderaVigencia;
    }

    public void setFlagBanderaVigencia(boolean flagBanderaVigencia) {
        this.flagBanderaVigencia = flagBanderaVigencia;
    }

    public Estado getEstadoTemp() {
        return estadoTemp;
    }

    public void setEstadoTemp(Estado estadoTemp) {
        this.estadoTemp = estadoTemp;
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

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public List<TipoDocumento> getListTipoDocumento() {
        return listTipoDocumento;
    }

    public void setListTipoDocumento(List<TipoDocumento> listTipoDocumento) {
        this.listTipoDocumento = listTipoDocumento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

}
