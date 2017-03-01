/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.SsMenus;
import com.sisrni.model.SsOpciones;
import com.sisrni.model.SsRoles;

import com.sisrni.security.AppUserDetails;
import com.sisrni.service.SsMenusService;
import com.sisrni.service.SsOpcionesService;
import com.sisrni.service.SsRolesService;
import com.sisrni.utils.JsfUtil;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Joao
 */
@Named("menusOpcionesMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MenusOpcionesMB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired
    @Qualifier(value = "ssMenuService")
    private SsMenusService menusService;

    @Autowired
    @Qualifier(value = "ssRolesService")
    private SsRolesService ssRolesService;

    @Autowired
    @Qualifier(value = "ssOpcionesService")
    private SsOpcionesService opcionesService;

    private List<SsMenus> listadoMenus;
    private List<SsOpciones> listadOpciones;

    private DualListModel<SsOpciones> opciones;

    private List<SsOpciones> opcionesSource;
    private List<SsOpciones> opcionesTarget;
    private List<SsOpciones> opcionesTargetTemp;

    private SsMenus ssMenus;
    private SsRoles roles;

    private CurrentUserSessionBean user;
    private AppUserDetails usuario;

    @PostConstruct
    public void init() {
        try {
            user = new CurrentUserSessionBean();
            ssMenus = new SsMenus();
            listadoMenus = menusService.getAllMenus();
            opcionesSource = new ArrayList<SsOpciones>();
            opcionesTarget = new ArrayList<SsOpciones>();
            opciones = new DualListModel<SsOpciones>(opcionesSource, opcionesTarget);
        } catch (Exception e) {
        }
    }

    public void llenarOpciones(SsMenus ssMenus) {
        try {

            this.ssMenus = ssMenus;
            opcionesSource = new ArrayList<SsOpciones>();
            opcionesTarget = new ArrayList<SsOpciones>();
            opcionesTargetTemp = new ArrayList<SsOpciones>();
            opcionesSource = opcionesService.findAll();

            for (SsMenus us : listadoMenus) {
                if (us.getIdMenu() == ssMenus.getIdMenu()) {
                    opcionesTarget = us.getSsOpcionesList();
                    opcionesTargetTemp = us.getSsOpcionesList();
                }
            }

            opcionesSource.removeAll(opcionesTarget);//elimina las opciones ya seleccionadas para menu             
            opciones = new DualListModel<SsOpciones>(opcionesSource, opcionesTarget);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo encargado de eliminar las opciones en primer lugar y luego agrega
     * las nuevas selecciones
     */
    public void guardar() {
        try {

            for (SsOpciones us : opcionesTargetTemp) {
                int deleteMenuOpciones = menusService.deleteMenuOpciones(ssMenus.getIdMenu(), us.getIdOpcion());
            }

            List<SsOpciones> target = opciones.getTarget();

            for (SsOpciones it : target) {
                menusService.guardarMenuOpciones(ssMenus.getIdMenu(), it.getIdOpcion());
            }

            listadoMenus = menusService.getAllMenus();
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("formMenu");
            JsfUtil.addSuccessMessage("Guardado Exitosamente");

        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(e, "Erro al Guardar--- CountryMB");
        } finally {
            this.ssMenus = null;
            this.ssMenus = new SsMenus();
            opciones = new DualListModel<SsOpciones>();
        }
    }

    /**
     * Metodo para redireccionar a opciones
     */
    public void redirectOpciones() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/views/menus/opcionesAdm.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MenusOpcionesMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo para redireccionar a menus
     */
    public void redirectMenu() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/views/menus/menuAdm.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MenusOpcionesMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the listadoMenus
     */
    public List<SsMenus> getListadoMenus() {
        return listadoMenus;
    }

    /**
     * @param listadoMenus the listadoMenus to set
     */
    public void setListadoMenus(List<SsMenus> listadoMenus) {
        this.listadoMenus = listadoMenus;
    }

    /**
     * @return the ssMenus
     */
    public SsMenus getSsMenus() {
        return ssMenus;
    }

    /**
     * @param ssMenus the ssMenus to set
     */
    public void setSsMenus(SsMenus ssMenus) {
        this.ssMenus = ssMenus;
    }

    public List<SsOpciones> getListadOpciones() {
        return listadOpciones;
    }

    public void setListadOpciones(List<SsOpciones> listadOpciones) {
        this.listadOpciones = listadOpciones;
    }

    public DualListModel<SsOpciones> getOpciones() {
        return opciones;
    }

    public void setOpciones(DualListModel<SsOpciones> opciones) {
        this.opciones = opciones;
    }

    public List<SsOpciones> getOpcionesSource() {
        return opcionesSource;
    }

    public void setOpcionesSource(List<SsOpciones> opcionesSource) {
        this.opcionesSource = opcionesSource;
    }

    public List<SsOpciones> getOpcionesTarget() {
        return opcionesTarget;
    }

    public void setOpcionesTarget(List<SsOpciones> opcionesTarget) {
        this.opcionesTarget = opcionesTarget;
    }

    public List<SsOpciones> getOpcionesTargetTemp() {
        return opcionesTargetTemp;
    }

    public void setOpcionesTargetTemp(List<SsOpciones> opcionesTargetTemp) {
        this.opcionesTargetTemp = opcionesTargetTemp;
    }

}
