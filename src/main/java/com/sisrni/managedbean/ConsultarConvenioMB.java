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
import com.sisrni.pojo.rpt.PojoPropuestaConvenio;
import com.sisrni.service.DocumentoService;
import com.sisrni.service.EstadoService;
import com.sisrni.service.FreeMarkerMailService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.PropuestaConvenioService;
import com.sisrni.service.PropuestaEstadoService;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Joao
 */
@Named("consultarConvenioMB")
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class ConsultarConvenioMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    PropuestaConvenioMB propuestaConvenioMB;

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

    private List<PojoPropuestaConvenio> listadoPropuestaConvenio;
    private PropuestaConvenio propuestaConvenio;
    private PropuestaConvenio propuestaConvenioTemp;
    private PojoPropuestaConvenio pojoPropuestaConvenio;
    private List<Estado> listadoEstados;
    private Estado estado;
    private List<Documento> listadoDocumento;

    private StreamedContent content;

    private static final String TIPO_DOCUMENTO = "Convenio Firmado";

    // @PostConstruct
    public void init() {
        try {
            inicializador();
        } catch (Exception e) {
        }
    }

    private void inicializador() {
        try {
            propuestaConvenio = new PropuestaConvenio();
            propuestaConvenioTemp = new PropuestaConvenio();
            estado = new Estado();
            listadoPropuestaConvenio = propuestaConvenioService.getAllConvenioSQL();

            Collections.sort(listadoPropuestaConvenio, new Comparator<PojoPropuestaConvenio>() {
                @Override
                public int compare(PojoPropuestaConvenio lhs, PojoPropuestaConvenio rhs) {
                    return rhs.getID_PROPUESTA().compareTo(lhs.getID_PROPUESTA());
                }
            });

            listadoEstados = estadoService.getEstadoPropuestasConvenio();
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

    public void preCambiarVigencia(PojoPropuestaConvenio pojo) {
        try {
            pojoPropuestaConvenio = propuestaConvenioService.getAllPropuestaConvenioSQLByID(pojo.getID_PROPUESTA());
            estado = estadoService.findById(pojo.getID_ESTADO());
            propuestaConvenio = propuestaConvenioService.findById(pojo.getID_PROPUESTA());
            propuestaConvenioTemp = propuestaConvenio;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void preView(PojoPropuestaConvenio pojo) {
        try {
            pojoPropuestaConvenio = propuestaConvenioService.getAllPropuestaConvenioSQLByID(pojo.getID_PROPUESTA());
            estado = estadoService.findById(pojo.getID_ESTADO());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void preEditar(PojoPropuestaConvenio pj) {
        try {
            propuestaConvenioMB.inicializador();
            propuestaConvenioMB.setSolicitante(personaService.getByID(pj.getID_SOLICITANTE()));
            propuestaConvenioMB.setReferenteInterno(personaService.getByID(pj.getID_REF_INTERNO()));
            propuestaConvenioMB.setReferenteExterno(personaService.getByID(pj.getID_REF_EXTERNO()));
            propuestaConvenioMB.postInit();
            propuestaConvenioMB.cargarPropuestaConvenio(pj.getID_PROPUESTA());

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

            String outcome = "propuestaCovenio.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect("propuestaCovenio.xhtml");

            //context.redirect(context.getRequestContextPath() + "/views/convenio/propuestaCovenio.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void actualizarConvenio() {
        try {
            Date now = new Date();
            if (propuestaConvenio.getVigencia().after(now)) {
                propuestaConvenioService.merge(propuestaConvenio);
                propuestaEstadoService.updatePropuestaEstado(pojoPropuestaConvenio.getID_PROPUESTA(), estado.getIdEstado());
                inicializador();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Convenio", "la propuesta ha cambiado Vigencia"));

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Fecha de Vigencia debe ser mayor a la actual"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarConvenio() {
        try {
            propuestaEstadoService.updatePropuestaEstado(pojoPropuestaConvenio.getID_PROPUESTA(), estado.getIdEstado());
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
            templateData.put("subJect", "Cambio Vigencia de Convenio");

            //templateData.put("nameTemplate", "propuesta_convenio_mailTemplat.txt");
            templateData.put("nameTemplate", "vigencia_convenio_mailTemplat.xhtml");
            templateData.put("propuestaConvenio", propuestaConvenio);
            templateData.put("propuestaConvenioTemp", propuestaConvenioTemp);

            for (PersonaPropuesta p : propuestaConvenio.getPersonaPropuestaList()) {
                templateData.put("setToMail", p.getPersona().getEmailPersona());
                mailService.sendEmailMap(templateData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Metodo para realizar las descargar de archivos
     *
     * @param documento
     */
    public void FileDownloadView(PojoPropuestaConvenio pojo) throws IOException {
        BufferedOutputStream out = null;
        try {
            String extension = null;
            String nombre = null;
            String contentType = null;
            InputStream stream = null;
            listadoDocumento = documentoService.getDocumentFindCovenio(pojo.getID_PROPUESTA());

            for (Documento doc : listadoDocumento) {
                if (doc.getIdTipoDocumento().getNombreDocumento().equalsIgnoreCase(TIPO_DOCUMENTO)) {
                    if (getFileExtension(doc.getNombreDocumento()).equalsIgnoreCase("pdf")) {
                        stream = new ByteArrayInputStream(doc.getDocumento());
                        nombre = doc.getNombreDocumento();
                    }
                }
            }

            if (extension != null) {
                if (extension.equalsIgnoreCase("pdf")) {
                    contentType = "Application/pdf";
                }
                content = new DefaultStreamedContent(stream, contentType, nombre);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Documento", "No se cuenta con documento firmado para descargar"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }

    private static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
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

    public PropuestaConvenio getPropuestaConvenioTemp() {
        return propuestaConvenioTemp;
    }

    public void setPropuestaConvenioTemp(PropuestaConvenio propuestaConvenioTemp) {
        this.propuestaConvenioTemp = propuestaConvenioTemp;
    }

    public StreamedContent getContent() {
        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    public List<Documento> getListadoDocumento() {
        return listadoDocumento;
    }

    public void setListadoDocumento(List<Documento> listadoDocumento) {
        this.listadoDocumento = listadoDocumento;
    }
}
