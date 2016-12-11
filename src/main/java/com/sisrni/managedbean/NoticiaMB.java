/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.CategoriaNoticia;
import com.sisrni.model.Noticia;
import com.sisrni.service.CategoriaNoticiaService;
import com.sisrni.service.NoticiaService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.CloseEvent;
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
@Scope(WebApplicationContext.SCOPE_APPLICATION)
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
    private Boolean actualizar;
    private Noticia noticiaPopUp;

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
        noticiasList = noticiaService.findAll();
        actualizar = false;
        noticiaPopUp = new Noticia();
    }

    public void guardarNoticia() {
        try {
            noticia.setIdNoticia(Integer.MIN_VALUE);
            noticia.setFechaNoticia(new Date());
            noticia.setIdCategoria(categoriaNoticiaService.findById(categoriaSelected.getIdCategoria()));
            noticiaService.save(noticia);
            inicializador();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "La Informacion se ha registrado correctamente!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La Informacion no ha sido registrada."));

        }
    }

    public void preEditar(int idnoticia) {
        try {
            this.noticia = noticiaService.findById(idnoticia);
            categoriaSelected = noticia.getIdCategoria();
            actualizar = true;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Problemas al obtener la informacion."));
        }
    }

    public void updateNoticia() {
        try {
            noticia.setIdCategoria(categoriaNoticiaService.findById(categoriaSelected.getIdCategoria()));
            noticiaService.merge(noticia);
            inicializador();
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

    public List<Noticia> listar() {
        return noticiaService.getActiveNews();
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

}
