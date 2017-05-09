/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.TipoProyecto;
import com.sisrni.service.TipoProyectoService;
import com.sisrni.utils.JsfUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Luis
 */
@Named(value = "tipoProyectoMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class TipoProyectoMB {

    /*Para Errores*/
    private final static Log log = LogFactory.getLog(TipoProyectoMB.class);

    /*Variables */
    private TipoProyecto tipoProyecto;
    private List<TipoProyecto> listTipoProyecto;
    private boolean actualizar;

    @Autowired
    @Qualifier(value = "tipoProyectoService")
    private TipoProyectoService tipoProyectoService;

    /*Constructor*/
    public TipoProyectoMB() {

    }

    /*Post Constructor*/
    @PostConstruct
    public void init() {
        cargarTipoProyecto();
    }

    /**
     * Metodo que crea instancias de 'TipoProyecto' y 'Organismo' y almacena en
     * una Lista todas las instancias de 'Organismo' que se encuentra en la
     * tabla respectiva
     */
    public void cargarTipoProyecto() {
        tipoProyecto = new TipoProyecto();
        listTipoProyecto = tipoProyectoService.getAllByIdDesc();
        actualizar = false;
    }

    /**
     * Metodo para guardar una instancia de 'TipoProyecto' en la tabla
     * correspondiente de la base de datos
     */
    public void guardarTipoProyecto() {
        String msg = "Tipo de Proyecto Almacenado Exitosamente!";
        try {
//           tipoProyecto.setIdTipoProyecto(Integer.MIN_VALUE);
            tipoProyectoService.save(tipoProyecto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado!!", msg));

        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al Guardar Tipo de Proyecto!");
            e.printStackTrace();
        }
        cargarTipoProyecto();
    }
    
    public List<TipoProyecto> listaProyectos() {
        listTipoProyecto = tipoProyectoService.getAllByNameAsc();
        TipoProyecto tipoProyectoNew1=new TipoProyecto();
        List<TipoProyecto> copy = new ArrayList<TipoProyecto>();
        for (TipoProyecto tipoProyectoNew : listTipoProyecto) {
            if(!tipoProyectoNew.getNombreTipoProyecto().equalsIgnoreCase("Agregar Nuevo"))
            {
                copy.add(tipoProyectoNew);
            }else{
                tipoProyectoNew1=tipoProyectoNew;
            }
        }
        copy.add(tipoProyectoNew1);
        listTipoProyecto.clear();
        return listTipoProyecto=copy;
    }

    /**
     * Metodo que se ocupa de precargar la instancia de 'TipoProyecto' a ser
     * actualizada
     *
     * @param tipoProyecto
     */
    public void preActualizarTipoProyecto(TipoProyecto tipoProyecto) {
        try {
            actualizar = true;
            this.tipoProyecto = tipoProyecto;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo para actualizar las propiedades de la instancia de 'TipoProyecto'
     * seleccionada
     */
    public void actualizarTipoProyecto() {
        String msg = "Tipo de Proyecto Actualizado Exitosamente!";
        try {
            //actualizando la instancia
            tipoProyectoService.merge(tipoProyecto);
            actualizar = false;
            cancelarTipoProyecto();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizacion!!", msg));
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al Actualizar Tipo de Proyecto");
            e.printStackTrace();
        }
        cargarTipoProyecto();
    }

    /**
     * Metodo de Pre- borrado, que obtiene una instancia de la Entity a Borrar y
     * despliega un 'p:dialog' donde se solicita la confirmacion de la operacion
     * de borrado
     *
     * @param tipoProyecto
     */
    public void preBorradoTipoProyecto(TipoProyecto tipoProyecto) {
        //guardando en 'tipoProyecto' la instancia de 'TipoProyecto' que se recibe como argumento
        this.tipoProyecto = tipoProyecto;
        RequestContext context = RequestContext.getCurrentInstance();
        //Desplegando el 'Dialog'
        context.execute("PF('confirmDeleteTipoProyectoDlg').show();");
    }

    /**
     * Metodo que borra una instancia de 'TipoProyecto' de la Base de datos
     */
    public void borrarTipoProyecto() {
        String msg = "Tipo de Proyecto Eliminado Exitosamente!";
        try {
            //Borrando la instancia de tipoProyecto
            tipoProyectoService.delete(tipoProyecto);
            cargarTipoProyecto();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmDeleteTipoProyectoDlg').hide();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado!!", msg));
        } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Tipo de Proyecto!"));
            e.printStackTrace();
        } finally {
            actualizar = false;
        }

    }

    /**
     * Metodo que se encarga de limpiar el formulario de creacion y
     * actualizacion de TipoProyecto
     */
    public void cancelarTipoProyecto() {
        String msg = "Tipo de Proyecto cancelado";
        try {
            tipoProyecto = null;
            tipoProyecto = new TipoProyecto();
            RequestContext.getCurrentInstance().reset(":formTipoProyecto");
            if (actualizar) {
                JsfUtil.addSuccessMessage(msg);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        cargarTipoProyecto();
    }

    /*Getters y Setters*/
    public TipoProyecto getTipoProyecto() {
        return tipoProyecto;
    }

    public void setTipoProyecto(TipoProyecto tipoProyecto) {
        this.tipoProyecto = tipoProyecto;
    }

    public List<TipoProyecto> getListTipoProyecto() {
        return listTipoProyecto;
    }

    public void setListTipoProyecto(List<TipoProyecto> listTipoProyecto) {
        this.listTipoProyecto = listTipoProyecto;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

}
