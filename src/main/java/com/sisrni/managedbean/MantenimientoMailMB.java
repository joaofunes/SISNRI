/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.Parametros;
import com.sisrni.service.ParametrosService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
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
@Named("mantenimientoMailMB")
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class MantenimientoMailMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    @Qualifier(value = "parametrosService")
    private ParametrosService parametrosService;

    private List<Parametros> listParametros;
    private Parametros parametros;

    public void init() {
        try {
            inicializador();
        } catch (Exception e) {
        }
    }

    private void inicializador() {
        try {
            parametros = new Parametros();
            listParametros = new ArrayList<Parametros>();
            listParametros = parametrosService.findAll();

        } catch (Exception e) {
            String message = "Error en inicializar : " + e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            e.printStackTrace();
        }
    }

    /**
     * prepara la eliminacion de parametro
     */
    public void preDelete(Parametros param) {
        try {
            parametros = new Parametros();
            parametros = param;
        } catch (Exception e) {
            String message = "Error al eliminar correo electronico : " + e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    /**
     * eliminacion de parametro
     */
    public void delete() {
        try {
            if (parametros.getActivo()) {
                String message = "Parametro Activo: ";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
            } else {
                parametrosService.delete(parametros);
                inicializador();
            }

        } catch (Exception e) {
            String message = "Error al eliminar correo electronico : " + e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    /**
     * prepara la edicion de parametro
     */
    public void preEdit(Parametros param) {
        try {
            parametros = new Parametros();
            parametros = param;
        } catch (Exception e) {
            String message = "Error al guardar correo electronico : " + e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    /**
     * realiza la edicion
     */
    public void edit() {
        try {

            if (parametros.getActivo()) {
                parametrosService.updateParametrosMail();
            }
            parametrosService.merge(parametros);
            inicializador();
        } catch (Exception e) {
            String message = "Error al editar correo electronico : " + e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    /**
     * prepara la creacion de nuevo parametro
     */
    public void preSave() {
        try {
            parametros = new Parametros();
        } catch (Exception e) {
            String message = "Error al guardar correo electronico : " + e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    /**
     * alamcena nuevo correo en parametros
     */
    public void save() {
        try {

            if (parametros.getActivo()) {
                parametrosService.updateParametrosMail();
            }

            parametrosService.merge(parametros);
            inicializador();
        } catch (Exception e) {
            String message = "Error al guardar correo electronico : " + e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    public List<Parametros> getListParametros() {
        return listParametros;
    }

    public void setListParametros(List<Parametros> listParametros) {
        this.listParametros = listParametros;
    }

    public Parametros getParametros() {
        return parametros;
    }

    public void setParametros(Parametros parametros) {
        this.parametros = parametros;
    }

}
