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
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Cortez
 */
@Named(value = "noticiaMB")
@RequestScoped
public class NoticiaMB {

    public CategoriaNoticia getCategoriaSelected() {
        return categoriaSelected;
    }

    public void setCategoriaSelected(CategoriaNoticia categoriaSelected) {
        this.categoriaSelected = categoriaSelected;
    }

    public List<CategoriaNoticia> getCategoriaNoticiaList() {
        return categoriaNoticiaList;
    }

    public void setCategoriaNoticiaList(List<CategoriaNoticia> categoriaNoticiaList) {
        this.categoriaNoticiaList = categoriaNoticiaList;
    }

    /**
     * Creates a new instance of NoticiaMB
     */
    private final static Log log = LogFactory.getLog(NoticiaMB.class);
    private Noticia noticia;
    private CategoriaNoticia categoriaSelected;
    private List<CategoriaNoticia> categoriaNoticiaList;

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
        cargarNoticia();
    }

    public void cargarNoticia() {
        noticia = new Noticia();
        categoriaSelected = new CategoriaNoticia();
        categoriaNoticiaList = categoriaNoticiaService.findAll();
    }

    public void guardarNoticia() {
        try {
            noticia.setIdNoticia(Integer.MIN_VALUE);
            noticia.setFechaNoticia(new Date());
            noticia.setIdCategoria(categoriaNoticiaService.findById(categoriaSelected.getIdCategoria()));
            noticiaService.save(noticia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

}
