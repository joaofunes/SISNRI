/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.Noticia;
import com.sisrni.service.NoticiaService;
import java.util.Date;
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

    /**
     * Creates a new instance of NoticiaMB
     */
    private final static Log log = LogFactory.getLog(NoticiaMB.class);
    private Noticia noticia;

    @Autowired
    @Qualifier(value = "noticiaService")
    private NoticiaService noticiaService;

    public NoticiaMB() {

    }

    @PostConstruct
    public void init() {
        cargarNoticia();
    }

    public void cargarNoticia() {
        noticia = new Noticia();
    }
    
    public void guardarNoticia(){
        try {
            noticia.setIdNoticia(Integer.MIN_VALUE);
            noticia.setFechaNoticia(new Date());
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
