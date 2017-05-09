/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.CategoriaMovilidad;
import com.sisrni.service.CategoriaMovilidadService;
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
@Named(value = "categoriaMovilidadMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class CategoriaMovilidadMB {
    /*Para Errores*/

    private final static Log log = LogFactory.getLog(CategoriaMovilidadMB.class);

    /*Variables */
    private CategoriaMovilidad categoriaMovilidad;
    private List<CategoriaMovilidad> listCategoriaMovilidad;
    private boolean actualizar;

    @Autowired
    @Qualifier(value = "categoriaMovilidadService")
    private CategoriaMovilidadService categoriaMovilidadService;

    /*Constructor*/
    public CategoriaMovilidadMB() {

    }

    /*Post Constructor*/
    @PostConstruct
    public void init() {
        cargarCategoriaMovilidad();
    }

    /**
     * Metodo que crea instancias de 'CategoriaMovilidad' y 'Organismo' y
     * almacena en una Lista todas las instancias de 'Organismo' que se
     * encuentra en la tabla respectiva
     */
    public void cargarCategoriaMovilidad() {
        categoriaMovilidad = new CategoriaMovilidad();
        listCategoriaMovilidad = categoriaMovilidadService.getAllByIdDesc();
        actualizar = false;
    }

    /**
     * Metodo para guardar una instancia de 'CategoriaMovilidad' en la tabla
     * correspondiente de la base de datos
     */
    public void guardarCategoriaMovilidad() {
        String msg = "Categoria de Movilidad Almacenada Exitosamente!";
        try {
            categoriaMovilidad.setIdCategoriaMovilidad(Integer.MIN_VALUE);
            categoriaMovilidadService.save(categoriaMovilidad);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardada!!", msg));

        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al Guardar Categoria de Movilidad!");
            e.printStackTrace();
        }
        cargarCategoriaMovilidad();
    }

    /**
     * Metodo que se ocupa de precargar la instancia de 'CategoriaMovilidad' a
     * ser actualizada
     *
     * @param categoriaMovilidad
     */
    public void preActualizarCategoriaMovilidad(CategoriaMovilidad categoriaMovilidad) {
        try {
            actualizar = true;
            this.categoriaMovilidad = categoriaMovilidad;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo para actualizar las propiedades de la instancia de
     * 'CategoriaMovilidad' seleccionada
     */
    public void actualizarCategoriaMovilidad() {
        String msg = "Categoria de Movilidad Actualizada Exitosamente!";
        try {
            //actualizando la instancia
            categoriaMovilidadService.merge(categoriaMovilidad);
            actualizar = false;
            cancelarCategoriaMovilidad();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizacion!!", msg));
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al Actualizar Categoria de Movilidad");
            e.printStackTrace();
        }
        cargarCategoriaMovilidad();
    }

    /**
     * Metodo de Pre- borrado, que obtiene una instancia de la Entity a Borrar y
     * despliega un 'p:dialog' donde se solicita la confirmacion de la operacion
     * de borrado
     *
     * @param categoriaMovilidad
     */
    public void preBorradoCategoriaMovilidad(CategoriaMovilidad categoriaMovilidad) {
        //guardando en 'categoriaMovilidad' la instancia de 'CategoriaMovilidad' que se recibe como argumento
        this.categoriaMovilidad = categoriaMovilidad;
        RequestContext context = RequestContext.getCurrentInstance();
        //Desplegando el 'Dialog'
        context.execute("PF('confirmDeleteCategoriaMovilidadDlg').show();");
    }

    /**
     * Metodo que borra una instancia de 'CategoriaMovilidad' de la Base de
     * datos
     */
    public void borrarCategoriaMovilidad() {
        String msg = "Categoria de Movilidad Eliminada Exitosamente!";
        try {
            //Borrando la instancia de categoriaMovilidad
            categoriaMovilidadService.delete(categoriaMovilidad);
            cargarCategoriaMovilidad();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmDeleteCategoriaMovilidadDlg').hide();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminada!!", msg));
        } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Categoria de Movilidad!"));
            e.printStackTrace();
        } finally {
            actualizar = false;
        }

    }

    /**
     * Metodo que se encarga de limpiar el formulario de creacion y
     * actualizacion de CategoriaMovilidad
     */
    public void cancelarCategoriaMovilidad() {
        String msg = "Categoria de Movilidad cancelado";
        try {
            categoriaMovilidad = null;
            categoriaMovilidad = new CategoriaMovilidad();
            RequestContext.getCurrentInstance().reset(":formCategoriaMovilidad");
            if (actualizar) {
                JsfUtil.addSuccessMessage(msg);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        cargarCategoriaMovilidad();
    }

    public List<CategoriaMovilidad> listarCategoriasMovilidad() {
        //obteniendo la lista ascendente de categorias
        listCategoriaMovilidad = categoriaMovilidadService.getAllCategoriasByNameAsc();
        CategoriaMovilidad categoriaMovilidadNuevo = new CategoriaMovilidad();
        List<CategoriaMovilidad> listCategoriaMovilidadCopy = new ArrayList<CategoriaMovilidad>();
        for (CategoriaMovilidad cat : listCategoriaMovilidad) {
            if (!cat.getNombreCategoria().equalsIgnoreCase("Agregar Nuevo")) {
                //agregando item a la lista copia
                listCategoriaMovilidadCopy.add(cat);

            } else {
                categoriaMovilidadNuevo = cat;
            }
        }
        listCategoriaMovilidadCopy.add(categoriaMovilidadNuevo);
        listCategoriaMovilidad.clear();
        return listCategoriaMovilidad = listCategoriaMovilidadCopy;

    }

    /*Getters y Setters*/
    public CategoriaMovilidad getCategoriaMovilidad() {
        return categoriaMovilidad;
    }

    public void setCategoriaMovilidad(CategoriaMovilidad categoriaMovilidad) {
        this.categoriaMovilidad = categoriaMovilidad;
    }

    public List<CategoriaMovilidad> getListCategoriaMovilidad() {
        return listCategoriaMovilidad;
    }

    public void setListCategoriaMovilidad(List<CategoriaMovilidad> listCategoriaMovilidad) {
        this.listCategoriaMovilidad = listCategoriaMovilidad;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

}
