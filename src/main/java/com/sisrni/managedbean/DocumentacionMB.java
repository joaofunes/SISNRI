/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.Documento;
import com.sisrni.service.DocumentoService;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.sisrni.model.PropuestaConvenio;
import com.sisrni.model.TipoDocumento;
import com.sisrni.pojo.rpt.PojoPropuestaConvenio;
import com.sisrni.security.AppUserDetails;
import com.sisrni.service.PropuestaConvenioService;
import com.sisrni.service.TipoDocumentoService;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

@Named("documentacionMB")
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class DocumentacionMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private CurrentUserSessionBean user;
    private AppUserDetails usuario;

    private Documento documento;
    private List<Documento> listadoDocumentos;

    //preguntar si el nombre del convenio sera el mismo que se pone en propuesta de convenio
    private String nombreConvenio;
    private PojoPropuestaConvenio pojoPropuestaConvenio;

    @Autowired
    @Qualifier(value = "documentoService")
    private DocumentoService documentoService;

    @Autowired
    @Qualifier(value = "propuestaConvenioService")
    private PropuestaConvenioService propuestaConvenioService;

    @Autowired
    @Qualifier(value = "tipoDocumentoService")
    private TipoDocumentoService tipoDocumentoService;

    private List<PropuestaConvenio> listPropuestaConvenio;
    private List<TipoDocumento> listTipoDocumento;
    private PropuestaConvenio propuestaConvenio;
    private TipoDocumento tipoDocumento;

    private UploadedFile file;

    private StreamedContent content;

    private Boolean addDocumentoEspecifico;

    //@PostConstruct
    public void init() {
        try {
            iniciliazar();
            //searchConvenio();
        } catch (Exception e) {
        }
    }

    public void iniciliazar() {
        try {
            user = new CurrentUserSessionBean();
            usuario = user.getSessionUser();
            listPropuestaConvenio = new ArrayList<PropuestaConvenio>();
            propuestaConvenio = new PropuestaConvenio();
            listPropuestaConvenio = propuestaConvenioService.findAll();
            listTipoDocumento = tipoDocumentoService.getTipoDocumentosByCategory(1);
            addDocumentoEspecifico = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para cargar propuetsa
     */
    public void cargarPropuesta() {
        try {
            documento = new Documento();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('AddDocDialog').show();");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo para realizar busquedas de documentacion por nombre de convcenio
     */
    public void searchDocumentoConvenio(int idPropuestaConvenio) {
        try {
            listadoDocumentos = new ArrayList<Documento>();
            listadoDocumentos = documentoService.getDocumentFindCovenio(idPropuestaConvenio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo para realizar busquedas de convenio por nombre
     */
    public List<PropuestaConvenio> completePropuestaConvenio(String query) {
        List<PropuestaConvenio> filteredThemes = new ArrayList<PropuestaConvenio>();
        for (int i = 0; i < listPropuestaConvenio.size(); i++) {
            PropuestaConvenio skin = listPropuestaConvenio.get(i);
            if (skin.getNombrePropuesta().toLowerCase().startsWith(query.toLowerCase())) {
                filteredThemes.add(skin);
            }
        }
        return filteredThemes;
    }

    /**
     * metodo para cargar de convenio
     */
    public void getDataConvenio() {
        try {
            if (propuestaConvenio != null) {
                pojoPropuestaConvenio = propuestaConvenioService.getAllPropuestaConvenioSQLByID(propuestaConvenio.getIdPropuesta());
                searchDocumentoConvenio(propuestaConvenio.getIdPropuesta());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo para cagar de convenio desde otros MB
     *
     * @param idConvenio
     */
    public void getDataConvenio(int idConvenio) {
        try {
            pojoPropuestaConvenio = propuestaConvenioService.getAllPropuestaConvenioSQLByID(idConvenio);
            searchDocumentoConvenio(pojoPropuestaConvenio.getID_PROPUESTA());
            RequestContext.getCurrentInstance().update(":idDataConevnio");
        } catch (Exception e) {
            e.printStackTrace();
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
                getDataConvenio();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succesful", "Documento agregado exitosamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "Documento no ha sido agregado"));
            }

        } catch (Exception e) {
            String message = "Error Agregado documento : " + e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    /**
     * Metodo para actualizar documentacion
     */
    public void preActualizacion(Documento documento) {
        try {
            this.documento = documento;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para agregar documentos a convenio
     */
    public void actualzarDocument() {
        try {
            documento.setFechaRecibido(new Date());
            documento.setUsuarioRecibe(usuario.getUsuario().getNombreUsuario());
            documentoService.merge(documento);
            getDataConvenio();
            FacesMessage message = new FacesMessage("Succesful", " Documento actualizado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para elimar documentos asignado a convenio
     */
    public void eliminarDocument() {
        try {
            documentoService.delete(documento);
            getDataConvenio();
            FacesMessage message = new FacesMessage("Succesful", " Documento eliminado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            documento = null;
        }
    }

    private static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    /**
     * *
     * Metodo para previzualizar
     *
     * @param event preView
     */
    public void preView(Documento documento) {
        try {
            this.documento = documento;

            if (getFileExtension(documento.getNombreDocumento()).equals("pdf")) {
                content = new DefaultStreamedContent(new ByteArrayInputStream(documento.getDocumento()), "application/pdf");
            } else {
                System.out.println("EN TRABAJO TYPO::::::::::::::::::::::" + getFileExtension(documento.getNombreDocumento()));
            }

            RequestContext.getCurrentInstance().execute("PF('previewDialog').show()");
            RequestContext.getCurrentInstance().update(":idPreview");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para realizar las descargar de archivos
     *
     * @param documento
     */
    public void FileDownloadView(Documento documento) throws IOException {
        BufferedOutputStream out = null;
        try {
            this.documento = documento;
            String extension;
            String contentType = null;
            InputStream stream = new ByteArrayInputStream(this.documento.getDocumento());
            extension = getFileExtension(this.documento.getNombreDocumento());

            if (extension.equalsIgnoreCase("docx")) {
                contentType = "application/vnd.ms-word.document";
            } else if (extension.equalsIgnoreCase("pdf")) {
                contentType = "Application/pdf";
            } else if (extension.equalsIgnoreCase("xls")) {
                contentType = "application/vnd.ms-excel";
            } else if (extension.equalsIgnoreCase("xlsx")) {
                contentType = "application/vnd.ms-excel";
            } else if (extension.equalsIgnoreCase("doc")) {
                contentType = "application/ms-word";
            }

            content = new DefaultStreamedContent(stream, contentType, documento.getNombreDocumento());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        } // Gently close stream.    

    }

    public static boolean getRandomBoolean() {
        return Math.random() < 0.5;
        //I tried another approaches here, still the same result
    }

    public static int getCantidad() {
        return 2 + (int) (Math.random() * ((10 - 2) + 1));
    }

    public StreamedContent getContent() {
        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    public List<Documento> getListadoDocumentos() {
        return listadoDocumentos;
    }

    public void setListadoDocumentos(List<Documento> listadoDocumentos) {
        this.listadoDocumentos = listadoDocumentos;
    }

    public Documento getDocuemnto() {
        return documento;
    }

    public void setDocuemnto(Documento docuemnto) {
        this.documento = docuemnto;
    }

    public String getNombreConvenio() {
        return nombreConvenio;
    }

    public void setNombreConvenio(String nombreConvenio) {
        this.nombreConvenio = nombreConvenio;
    }

    public PojoPropuestaConvenio getPojoPropuestaConvenio() {
        return pojoPropuestaConvenio;
    }

    public void setPojoPropuestaConvenio(PojoPropuestaConvenio pojoPropuestaConvenio) {
        this.pojoPropuestaConvenio = pojoPropuestaConvenio;
    }

    public List<PropuestaConvenio> getListPropuestaConvenio() {
        return listPropuestaConvenio;
    }

    public void setListPropuestaConvenio(List<PropuestaConvenio> listPropuestaConvenio) {
        this.listPropuestaConvenio = listPropuestaConvenio;
    }

    public PropuestaConvenio getPropuestaConvenio() {
        return propuestaConvenio;
    }

    public void setPropuestaConvenio(PropuestaConvenio propuestaConvenio) {
        this.propuestaConvenio = propuestaConvenio;
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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public AppUserDetails getUsuario() {
        return usuario;
    }

    public void setUsuario(AppUserDetails usuario) {
        this.usuario = usuario;
    }

    public CurrentUserSessionBean getUser() {
        return user;
    }

    public void setUser(CurrentUserSessionBean user) {
        this.user = user;
    }

    public Boolean getAddDocumentoEspecifico() {
        return addDocumentoEspecifico;
    }

    public void setAddDocumentoEspecifico(Boolean addDocumentoEspecifico) {
        this.addDocumentoEspecifico = addDocumentoEspecifico;
    }

}
