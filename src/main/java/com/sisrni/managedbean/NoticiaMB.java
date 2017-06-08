/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.sisrni.model.CategoriaNoticia;
import com.sisrni.model.Noticia;
import com.sisrni.service.CategoriaNoticiaService;
import com.sisrni.service.NoticiaService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ToggleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Cortez
 */
@Named("noticiaMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class NoticiaMB implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Creates a new instance of NoticiaMB
     */
    private final static Log log = LogFactory.getLog(NoticiaMB.class);
    private Noticia noticia;
    private CategoriaNoticia categoriaSelected;
    private List<CategoriaNoticia> categoriaNoticiaList;
    private List<Noticia> noticiasList;
    private List<Noticia> noticiasListPublicas;
    private Integer categoriaSelectedPublicas;
    private Boolean actualizar;
    private Boolean eliminar;
    private Noticia noticiaPopUp;
    private Boolean publicarEnFacebook;
    private Boolean renderFbButton;
    private File fileForFb;
    private FileInputStream fileToPublish;
    private static final String tokenFb = "EAADuZAEJdqZBQBAPdZBu0uy9PfZBnCquAUt5qxb46POARj7ImwGPmIrimH09LyqTBVndc3akEBCuc3UHd5jYJrRofzfyai71M9ZBr2bjEfYTU9qff3ZByZBQpUWeOUR64LYbhUHmGC5lmuZALRGWANrYJzzZBo50Jl9EMtfCt7b7jCF2k6cVQprH9";

    @Autowired
    @ManagedProperty("#{globalCounterView}")
    private GlobalCounterView globalCounter;

    @Autowired
    @Qualifier(value = "noticiaService")
    private NoticiaService noticiaService;

    @Autowired
    @Qualifier(value = "categoriaNoticiaService")
    private CategoriaNoticiaService categoriaNoticiaService;

    public NoticiaMB() {

    }

    @PostConstruct
    public void init() {
        inicializador();
    }
//inicializa las variables del MB

    public void inicializador() {
        noticia = new Noticia();
        categoriaSelected = new CategoriaNoticia();
        categoriaNoticiaList = categoriaNoticiaService.findAll();
        noticiasList = noticiaService.getAllNoticiasOrderDescDate();
        categoriaSelectedPublicas = 0;
        noticiasListPublicas = noticiaService.getActiveNews(categoriaSelectedPublicas);
        actualizar = false;
        eliminar = false;
        noticiaPopUp = new Noticia();
        renderFbButton = Boolean.FALSE;
        publicarEnFacebook = Boolean.FALSE;
        fileForFb = null;
        fileToPublish = null;

//        globalCounter = new GlobalCounterView();
    }

    public void guardarNoticia() {
        try {
            noticia.setIdNoticia(Integer.MIN_VALUE);
            noticia.setFechaNoticia(new Date());
            noticia.setIdCategoria(categoriaNoticiaService.findById(categoriaSelected.getIdCategoria()));
            noticiaService.save(noticia);
            if (publicarEnFacebook) {
                publicarNoticiaEnFb();
            }
            inicializador();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "La Informacion se ha registrado correctamente!"));
            //globalCounter.increment(noticiasNoVisibles());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La Informacion no ha sido registrada."));

        }
    }

    public void publicarChange() {
        publicarEnFacebook = publicarEnFacebook ? Boolean.TRUE : Boolean.FALSE;
    }

    public void estadoChange() {
        if (noticia.getEstadoNoticia() == true) {
            renderFbButton = Boolean.TRUE;

        } else {
            renderFbButton = Boolean.FALSE;
        }
    }

    public void publicarNoticiaEnFb() throws FileNotFoundException {

        FacebookClient fbClient = new DefaultFacebookClient(tokenFb);

        fbClient.publish("me/feed", FacebookType.class, Parameter.with("message", noticia.getTituloNoticia()),
                Parameter.with("link", "http://54.233.194.159:8080/sisrni/views/Noticias/Index.xhtml")
        );

        if (fileForFb != null) {
            fileToPublish = new FileInputStream(fileForFb);
            fbClient.publish("me/photos", FacebookType.class, BinaryAttachment.with("image.png", fileToPublish),
                    Parameter.with("message", noticia.getTituloNoticia())
            );
        }

    }

    public Integer noticiasNoVisibles() {
        return noticiaService.noticiasNoVisibles();
    }

    public void preEditar(int idnoticia) {
        try {
            this.noticia = noticiaService.findById(idnoticia);
            categoriaSelected = noticia.getIdCategoria();
            actualizar = true;
            eliminar = false;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Problemas al obtener la informacion."));
        }
    }

    public void preEliminar(int idnoticia) {
        try {
            this.noticia = noticiaService.findById(idnoticia);
            categoriaSelected = noticia.getIdCategoria();
            eliminar = true;
            actualizar = false;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Problemas al obtener la informacion."));
        }
    }

    public void eliminar() {
        try {
            noticiaService.delete(noticia);
            inicializador();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "El registro ha sido eliminado."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Problemas al eliminar la informacion."));
        }
    }

    public void updateNoticia() {
        try {
            noticia.setIdCategoria(categoriaNoticiaService.findById(categoriaSelected.getIdCategoria()));
            noticiaService.merge(noticia);
            if (publicarEnFacebook) {
                publicarNoticiaEnFb();
            }
            inicializador();
            //globalCounter.increment(noticiasNoVisibles());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Problemas al actualizar."));
        }

    }

    public void cargarNoticiaPopUp(int idNoticia) {
        try {
            noticiaPopUp = noticiaService.findById(idNoticia);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Problemas al obtener la informacion."));
        }
    }

    public void uploadListener(FileUploadEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        String fileName = FilenameUtils.getName(event.getFile().getFileName());
        String fileNamePrefix = FilenameUtils.getBaseName(fileName) + "_";
        String fileNameSuffix = "." + FilenameUtils.getExtension(fileName);

        File uploadFolder = new File("/var/webapp/uploads");

        try {
            File result = File.createTempFile(fileNamePrefix, fileNameSuffix, uploadFolder);

            if (fileNameSuffix.equalsIgnoreCase(".png") || fileNameSuffix.equalsIgnoreCase(".jpg")) {
                fileForFb = result; //para publicar en fb
            }

            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[1024];
            int bulk;

            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }

                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();

            String value = FacesContext.getCurrentInstance().
                    getExternalContext().getRequestParameterMap().get("formNoticia:editor_input");
            if (".pdf".equals(fileNameSuffix) || ".doc".equals(fileNameSuffix)) {
                if (".pdf".equals(fileNameSuffix)) {
                    noticia.setContenido(value + "<a data-toggle=\"tooltip\" title=\"" + fileName + "\" href=\"/project/images/" + result.getName() + "\">" + "<img style=\"width:50px;height:60px\" src=\"/project/images/pdf.png" + "\" />" + "</a>");
                } else {
                    noticia.setContenido(value + "<a data-toggle=\"tooltip\" title=\"" + fileName + "\" href=\"/project/images/" + result.getName() + "\">" + "<img style=\"width:50px;height:60px\" src=\"/project/images/doc.png" + "\" />" + "</a>");
                }
            } else {
                noticia.setContenido(value + "<img style=\"width:100%;max-width:1000px\" src=\"/project/images/" + result.getName() + "\" />");

            }

            RequestContext.getCurrentInstance().update("formNoticia:editor_input");

            FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " ha sido cargada.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (IOException e) {
            e.printStackTrace();
            FacesMessage error = new FacesMessage("La imagen no ha sido cargada exitosamente");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }

    }

    public void onClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
//gets and setters

    public CategoriaNoticia getCategoriaSelected() {
        return categoriaSelected;
    }

    public void setCategoriaSelected(CategoriaNoticia categoriaSelected) {
        this.categoriaSelected = categoriaSelected;
    }

    public List<CategoriaNoticia> getCategoriaNoticiaList() {
        return categoriaNoticiaList;
    }

    public List<Noticia> getNoticiasList() {
        return noticiasList;
    }

    public void listarOnCatChange() {
        try {
            noticiasListPublicas = noticiaService.getActiveNews(categoriaSelectedPublicas);
        } catch (Exception e) {
        }

    }

    public void setNoticiasList(List<Noticia> noticiasList) {
        this.noticiasList = noticiasList;
    }

    public void setCategoriaNoticiaList(List<CategoriaNoticia> categoriaNoticiaList) {
        this.categoriaNoticiaList = categoriaNoticiaList;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public Boolean getActualizar() {
        return actualizar;
    }

    public void setActualizar(Boolean actualizar) {
        this.actualizar = actualizar;
    }

    public Noticia getNoticiaPopUp() {
        return noticiaPopUp;
    }

    public void setNoticiaPopUp(Noticia noticiaPopUp) {
        this.noticiaPopUp = noticiaPopUp;
    }

    public List<Noticia> getNoticiasListPublicas() {
        return noticiasListPublicas;
    }

    public void setNoticiasListPublicas(List<Noticia> noticiasListPublicas) {
        this.noticiasListPublicas = noticiasListPublicas;
    }

    public Integer getCategoriaSelectedPublicas() {
        return categoriaSelectedPublicas;
    }

    public void setCategoriaSelectedPublicas(Integer categoriaSelectedPublicas) {
        this.categoriaSelectedPublicas = categoriaSelectedPublicas;
    }

    public GlobalCounterView getGlobalCounter() {
        return globalCounter;
    }

    public void setGlobalCounter(GlobalCounterView globalCounter) {
        this.globalCounter = globalCounter;
    }

    public Boolean getPublicarEnFacebook() {
        return publicarEnFacebook;
    }

    public void setPublicarEnFacebook(Boolean publicarEnFacebook) {
        this.publicarEnFacebook = publicarEnFacebook;
    }

    public Boolean getRenderFbButton() {
        return renderFbButton;
    }

    public void setRenderFbButton(Boolean renderFbButton) {
        this.renderFbButton = renderFbButton;
    }

    public File getFileForFb() {
        return fileForFb;
    }

    public void setFileForFb(File fileForFb) {
        this.fileForFb = fileForFb;
    }

    public FileInputStream getFileToPublish() {
        return fileToPublish;
    }

    public void setFileToPublish(FileInputStream fileToPublish) {
        this.fileToPublish = fileToPublish;
    }

    public Boolean getEliminar() {
        return eliminar;
    }

    public void setEliminar(Boolean eliminar) {
        this.eliminar = eliminar;
    }

}
