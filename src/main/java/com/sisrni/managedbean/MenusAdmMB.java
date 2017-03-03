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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Joao
 */
@Named("menusAdmMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MenusAdmMB implements Serializable {

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

    @Inject
    MenusOpcionesMB menusOpcionesMB;

    //listado ListPicker
    private DualListModel<SsOpciones> options;
    private List<SsOpciones> optionsSource;
    private List<SsOpciones> optionsTarget;

    
    private List<SsMenus> listadoMenus;

    private List<SsRoles> selectedlistRoles;
    private String[] selectedArrayRoles;
    private List<SsRoles> listRoles;
    private List<SsRoles> listRolesTemp;

    private SsMenus ssMenus;

    private SsRoles roles;

    private CurrentUserSessionBean user;
    private AppUserDetails usuario;

    @PostConstruct
    public void init() {
        try {

            listadoMenus = new ArrayList<SsMenus>();
            ssMenus = new SsMenus();

            user = new CurrentUserSessionBean();
            usuario = user.getSessionUser();

            options = new DualListModel<SsOpciones>();

            listRoles = ssRolesService.findAll();
            getMenus();
        } catch (Exception e) {
        }
    }

    public void getMenus() {
        try {
            roles = new SsRoles();
            listadoMenus = null;
            listadoMenus = menusService.getDao().findAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * *
     *
     * @param country
     * @throws Exception
     */
    public void preEditar(SsMenus ssMenus) throws Exception {
        try {
            int i = 0;
            this.ssMenus = ssMenus;
            selectedArrayRoles = new String[ssMenus.getSsRolesList().size()];
            listRolesTemp = ssMenus.getSsRolesList();
            for (SsRoles mn : ssMenus.getSsRolesList()) {
                selectedArrayRoles[i] = mn.getIdRol().toString();
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error class MenusOpcionesMB - preEditar()\n" + e.getMessage(), e.getCause());
        } finally {

        }
    }

    public void editar() throws Exception {
        try {
            for (SsRoles mn : ssMenus.getSsRolesList()) {
                int deleteMenuOpciones = menusService.deleteMenuRoles(ssMenus.getIdMenu(), mn.getIdRol());
            }

            selectedlistRoles = new ArrayList<SsRoles>();
            for (String us : selectedArrayRoles) {
                roles = new SsRoles();
                roles = ssRolesService.findById(Integer.parseInt(us.toString()));
                selectedlistRoles.add(roles);
            }
            ssMenus.setSsRolesList(selectedlistRoles);
            menusService.getDao().merge(ssMenus);
            getMenus();
            RequestContext.getCurrentInstance().update("formAdmin");
            RequestContext.getCurrentInstance().update("formMenu");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error class MenusOpcionesMB - Editar()\n" + e.getMessage(), e.getCause());
        } finally {
            this.ssMenus = null;
            this.ssMenus = new SsMenus();
        }
    }

    public void guardar() {
        try {
            getSsMenus().setFechaRegistro(new Date());
            getSsMenus().setUsuarioRegistro(usuario.getUsuario().getCodigoUsuario());
            getSsMenus().setSsRolesList(usuario.getUsuario().getSsRolesList());
            selectedlistRoles = new ArrayList<SsRoles>();
            for (String us : selectedArrayRoles) {
                roles = new SsRoles();
                roles = ssRolesService.findById(Integer.parseInt(us.toString()));
                selectedlistRoles.add(roles);
            }
            ssMenus.setSsRolesList(selectedlistRoles);
            menusService.save(getSsMenus());
            getMenus();
            this.ssMenus = null;
            this.ssMenus = new SsMenus();
            RequestContext.getCurrentInstance().update("formAdmin");
            RequestContext.getCurrentInstance().update("formMenu");
            JsfUtil.addSuccessMessage("Guardado Exitosamente");
            JsfUtil.showFacesMsg(null, "Guardado Exitosamente", "growMessage", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(e, "Erro al Guardar--- CountryMB");
        } finally {

        }
    }
    
    
    /**
     * *
     *
     * @param 
     * @throws Exception
     */
    public void preEliminar(SsMenus ssMenus) throws Exception {
        try {
            this.ssMenus = ssMenus;
            int i=0;
            selectedArrayRoles = new String[ssMenus.getSsRolesList().size()];
            listRolesTemp = ssMenus.getSsRolesList();
            for (SsRoles mn : ssMenus.getSsRolesList()) {
                selectedArrayRoles[i] = mn.getIdRol().toString();
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error class MenusOpcionesMB - preEditar()\n" + e.getMessage(), e.getCause());
        } finally {

        }
    }
    /**
     * *
     * metodo para eliminar menus que no esten amarrados opciones
     * @param 
     * @throws Exception
     */
    public void eliminar() throws Exception {
        try {
            this.ssMenus = ssMenus;
            for (SsRoles mn : ssMenus.getSsRolesList()) {
                int deleteMenuOpciones = menusService.deleteMenuRoles(ssMenus.getIdMenu(), mn.getIdRol());
            }
            menusService.delete(ssMenus); 
            getMenus();
            RequestContext.getCurrentInstance().update("formAdmin");
            RequestContext.getCurrentInstance().update("formMenu");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error class MenusOpcionesMB - preEditar()\n" + e.getMessage(), e.getCause());
        } finally {

        }
    }
    

    public void cancelar() throws Exception {
        try {
            this.ssMenus = null;            
            RequestContext.getCurrentInstance().update(":formAdmin");
            RequestContext.getCurrentInstance().update(":formMenu");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error class MenusOpcionesMB - Editar()\n" + e.getMessage(), e.getCause());
        } finally {
            this.ssMenus = new SsMenus();
        }
    }

    public void addOpciones(SsMenus ssMenus) {
        try {

            FacesContext.getCurrentInstance().getExternalContext().redirect("menuOpcionesAdm.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
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

    

    public DualListModel<SsOpciones> getOptions() {
        return options;
    }

    public void setOptions(DualListModel<SsOpciones> options) {
        this.options = options;
    }

    public List<SsOpciones> getOptionsSource() {
        return optionsSource;
    }

    public void setOptionsSource(List<SsOpciones> optionsSource) {
        this.optionsSource = optionsSource;
    }

    public List<SsOpciones> getOptionsTarget() {
        return optionsTarget;
    }

    public void setOptionsTarget(List<SsOpciones> optionsTarget) {
        this.optionsTarget = optionsTarget;
    }

    public List<SsRoles> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<SsRoles> listRoles) {
        this.listRoles = listRoles;
    }

    public List<SsRoles> getSelectedlistRoles() {
        return selectedlistRoles;
    }

    public void setSelectedlistRoles(List<SsRoles> selectedlistRoles) {
        this.selectedlistRoles = selectedlistRoles;
    }

    public String[] getSelectedArrayRoles() {
        return selectedArrayRoles;
    }

    public void setSelectedArrayRoles(String[] selectedArrayRoles) {
        this.selectedArrayRoles = selectedArrayRoles;
    }

    public List<SsRoles> getListRolesTemp() {
        return listRolesTemp;
    }

    public void setListRolesTemp(List<SsRoles> listRolesTemp) {
        this.listRolesTemp = listRolesTemp;
    }

}
