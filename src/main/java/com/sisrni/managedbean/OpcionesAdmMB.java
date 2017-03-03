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

import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Joao
 */
@Named("opcionesAdmMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class OpcionesAdmMB implements Serializable {

    @Autowired
    @Qualifier(value = "ssOpcionesService")
    private SsOpcionesService opcionesService;

    @Autowired
    @Qualifier(value = "ssRolesService")
    private SsRolesService ssRolesService;

    private boolean actualizar;
    private List<SsOpciones> listadoOpciones;

    private SsOpciones ssOpciones;
    private SsRoles roles;

    private List<SsRoles> selectedlistRoles;
    private String[] selectedArrayRoles;
    private List<SsRoles> listRoles;

    private Boolean visible;

    public OpcionesAdmMB() {
    }

    private CurrentUserSessionBean user;
    private AppUserDetails usuario;

    @PostConstruct
    public void init() {
        try {
            user = new CurrentUserSessionBean();
            usuario = user.getSessionUser();
            listadoOpciones = new ArrayList<SsOpciones>();
            ssOpciones = new SsOpciones();
            listRoles = ssRolesService.findAll();
            visible = Boolean.FALSE;
            setActualizar(false);
            getMenus();
        } catch (Exception e) {
        }
    }

    public void getMenus() {
        try {
            roles = new SsRoles();
            listadoOpciones = opcionesService.getListadoOpciones();
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
    public void preEditar(SsOpciones ssOpciones) throws Exception {
        try {
            int i = 0;
            this.ssOpciones = ssOpciones;
            selectedArrayRoles = new String[ssOpciones.getSsRolesList().size()];
            for (SsRoles mn : ssOpciones.getSsRolesList()) {
                selectedArrayRoles[i] = mn.getIdRol().toString();
                i++;
            }
            
            if(ssOpciones.getVisible().equalsIgnoreCase("S")){
                visible=true;
            }else{
                visible=false;
            }
           
            RequestContext.getCurrentInstance().update("formAdmin");
            RequestContext.getCurrentInstance().update("formMenu");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error class MenusOpcionesMB - preEditar()\n" + e.getMessage(), e.getCause());
        } finally {

        }
    }

    public void preEliminar(SsOpciones ssOpciones) throws Exception {
        try {
            this.ssOpciones = ssOpciones;
            int i=0;
            selectedArrayRoles = new String[ssOpciones.getSsRolesList().size()];
            for (SsRoles mn : ssOpciones.getSsRolesList()) {
                selectedArrayRoles[i] = mn.getIdRol().toString();
                i++;
            }
            if(ssOpciones.getVisible().equalsIgnoreCase("S")){
                visible=true;
            }else{
                visible=false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error class opcinesAdmMB - preEliminar()\n" + e.getMessage(), e.getCause());
        } finally {

        }
    }

    public void editar() throws Exception {
        try {
                      
            for (SsRoles mn : ssOpciones.getSsRolesList()) {
                int deleteMenuOpciones = opcionesService.deleteOpcionesRoles(ssOpciones.getIdOpcion(), mn.getIdRol());
            }
            selectedlistRoles = new ArrayList<SsRoles>();
            for (String us : selectedArrayRoles) {
                roles = new SsRoles();
                roles = ssRolesService.findById(Integer.parseInt(us.toString()));
                selectedlistRoles.add(roles);
                opcionesService.gurdarRolesOpciones(Integer.parseInt(us.toString()), ssOpciones.getIdOpcion());
                
            }
            if (visible) {
                ssOpciones.setVisible(String.valueOf('S'));
            } else {
                ssOpciones.setVisible(String.valueOf('N'));
            }
            opcionesService.merge(ssOpciones);
            getMenus();
            RequestContext.getCurrentInstance().update("formAdmin");
            RequestContext.getCurrentInstance().update("formMenu");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error class opcinesAdmMB - Editar()\n" + e.getMessage(), e.getCause());
        } finally {
            this.ssOpciones = null;
            this.ssOpciones = new SsOpciones();
            setActualizar(false);
        }
    }

    public void preGuardar(){
       ssOpciones=new SsOpciones();
    }
    
    
    public void guardar() {
        try {
            ssOpciones.setFechaRegistro(new Date());
            ssOpciones.setUsuarioRegistro(usuario.getUsuario().getCodigoUsuario());
            ssOpciones.setSsRolesList(usuario.getUsuario().getSsRolesList());

            if (visible) {
                ssOpciones.setVisible(String.valueOf('S'));
            } else {
                ssOpciones.setVisible(String.valueOf('N'));
            }

            opcionesService.save(ssOpciones);
            
            selectedlistRoles = new ArrayList<SsRoles>();
            for (String us : selectedArrayRoles) {
                //roles = new SsRoles();
                //roles = ssRolesService.findById(Integer.parseInt(us.toString()));
                opcionesService.gurdarRolesOpciones(Integer.parseInt(us.toString()), ssOpciones.getIdOpcion());
               // selectedlistRoles.add(roles);
            }

           // ssOpciones.setSsRolesList(selectedlistRoles);
            
            getMenus();
            this.ssOpciones = null;
            this.ssOpciones = new SsOpciones();
            RequestContext.getCurrentInstance().update("formAdmin");
            RequestContext.getCurrentInstance().update("formMenu");
            JsfUtil.showFacesMsg(null, "Guardado Exitosamente", "growMessage", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(e, "Erro al Guardar--- CountryMB");
        } finally {

        }
    }

    public void cancelar() throws Exception {
        try {
            this.ssOpciones = null;
            setActualizar(false);
            RequestContext.getCurrentInstance().update(":formAdmin");
            RequestContext.getCurrentInstance().update(":formMenu");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error class MenusOpcionesMB - Editar()\n" + e.getMessage(), e.getCause());
        } finally {
            this.ssOpciones = new SsOpciones();
        }
    }

    /**
     *
     * @param ssMenus
     */
    public void addOpciones(SsMenus ssMenus) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("menuOpcionesAdm.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
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
            for (SsRoles mn : ssOpciones.getSsRolesList()) {
                int deleteMenuOpciones = opcionesService.deleteOpcionesRoles(ssOpciones.getIdOpcion(), mn.getIdRol());
            }
            opcionesService.delete(ssOpciones); 
            getMenus();
            RequestContext.getCurrentInstance().update("formAdmin");
            RequestContext.getCurrentInstance().update("formMenu");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error class MenusOpcionesMB - preEditar()\n" + e.getMessage(), e.getCause());
        } finally {

        }
    }
    
    /**
     * @return the listadoMenus
     */
    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public List<SsOpciones> getListadoOpciones() {
        return listadoOpciones;
    }

    public void setListadoOpciones(List<SsOpciones> listadoOpciones) {
        this.listadoOpciones = listadoOpciones;
    }

    public SsOpciones getSsOpciones() {
        return ssOpciones;
    }

    public void setSsOpciones(SsOpciones ssOpciones) {
        this.ssOpciones = ssOpciones;
    }

    public SsRoles getRoles() {
        return roles;
    }

    public void setRoles(SsRoles roles) {
        this.roles = roles;
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

    public List<SsRoles> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<SsRoles> listRoles) {
        this.listRoles = listRoles;
    }

    public Boolean getVisible() {
        
          return visible;  
       
    }

    public void setVisible(Boolean visible) {      
        this.visible = visible;
    }

}
