package com.sisrni.managedbean;

import com.sisrni.managedbean.generic.GenericManagedBean;
import com.sisrni.managedbean.lazymodel.UsuarioLazyModel;
import com.sisrni.model.SsRoles;
import com.sisrni.model.SsUsuarios;
import com.sisrni.service.SsRolesService;
import com.sisrni.service.SsUsuariosService;
import com.sisrni.service.generic.GenericService;
import com.sisrni.utils.JsfUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

@Named("usuarioMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class UsuarioMB extends GenericManagedBean<SsUsuarios, Integer> {

    @Autowired
    @Qualifier(value = "ssUsuariosService")
    private SsUsuariosService ssUsuarioService;
    @Autowired
    @Qualifier(value = "ssRolesService")
    private SsRolesService ssRolesService;

    private List<SsUsuarios> listadoUsuarios;
    private SsUsuarios ssUsuariosRol;
    private List<SsRoles> listadoRoles;

    private DualListModel<SsRoles> roles;

    private List<SsRoles> rolesSource;
    private List<SsRoles> rolesTarget;
    private List<SsRoles> rolesTargetTemp;

    @PostConstruct
    public void init() {
        ssUsuariosRol = new SsUsuarios();
        rolesSource = new ArrayList<SsRoles>();
        rolesTarget = new ArrayList<SsRoles>();
        roles = new DualListModel<SsRoles>(rolesSource, rolesTarget);
        listadoUsuarios=ssUsuarioService.getAllUser();
        loadLazyModels();
    }

    @Override
    public GenericService<SsUsuarios, Integer> getService() {
        return ssUsuarioService;
    }

    @Override
    public LazyDataModel<SsUsuarios> getNewLazyModel() {
        return new UsuarioLazyModel(listadoUsuarios, ssUsuarioService);
    }

    @Override
    public void saveNew(ActionEvent event) {
        if (getUsuario() != null) {
            String msg = ResourceBundle.getBundle("/crudbundle").getString(SsUsuarios.class.getSimpleName() + "Usuario creado con exito");
            getSelected().setUsuarioRegistro(getUsuario());
            getSelected().setFechaRegistro(new Date());
            persist(PersistAction.CREATE, msg);
        }
    }
    
    
     public void guardarUsuario(){
        String msg ="";
        try{
          getSelected().setUsuarioRegistro(getUsuario());
          getSelected().setFechaRegistro(new Date());
          getSelected().setIdUsuario(Integer.MIN_VALUE);
          ssUsuariosRol=  ssUsuarioService.findByUser(getSelected().getCodigoUsuario());
          if(ssUsuariosRol==null){
              msg ="Usuario Creado Exitosamente!";
          ssUsuarioService.save(getSelected());
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado!!", msg));
          }else{
            msg ="Ya existe este nombre de Usuario!";  
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Duplicado!!", msg));  
          }
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Guardar Usuario!");
            e.printStackTrace();
        }
        init();
    } 

    @Override
    public void save(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/crudbundle").getString(SsUsuarios.class.getSimpleName() + "Usuario actualizado con exito");
        getSelected().setUsuarioUltimamodificacion(getUsuario());
        getSelected().setFechaUltimamodificacion(new Date());
        persist(PersistAction.UPDATE, msg);
        if (!isValidationFailed()) {
            items = null; // Invalidate list of items to trigger re-query.
        }
    }
            

    public void preEditarRol() {
        try {
            rolesSource = new ArrayList<SsRoles>();
            rolesTarget = new ArrayList<SsRoles>();
            rolesTargetTemp = new ArrayList<SsRoles>();

            ssUsuariosRol = ssUsuarioService.findByUser(getSelected().getCodigoUsuario());

            rolesTarget = ssUsuariosRol.getSsRolesList();
            rolesTargetTemp = ssUsuariosRol.getSsRolesList();
            rolesSource = ssRolesService.findAll();

            rolesSource.removeAll(rolesTarget);//elimina las roles  ya seleccionadas para usuario             
            roles = new DualListModel<SsRoles>(rolesSource, rolesTarget);

        } catch (Exception e) {
        }
    }

    
    public void guardarWithRol(){
        try {
             for (SsRoles us : rolesTargetTemp) {
                int deleteMenuOpciones = ssUsuarioService.deleteUserRoles(ssUsuariosRol.getIdUsuario(), us.getIdRol());
            }
            List<SsRoles> target = roles.getTarget();
            for (SsRoles it : target) {
                ssUsuarioService.guardarUserRol(ssUsuariosRol.getIdUsuario(), it.getIdRol());
            }
            listadoUsuarios=ssUsuarioService.getAllUser();
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("formMenu");
            JsfUtil.addSuccessMessage("Guardado Exitosamente");
            //ssUsuarioService.merge(ssUsuariosRol)
        } catch (Exception e) {
         e.printStackTrace();
        }
    }
    
    public SsUsuarios getSsUsuariosRol() {
        return ssUsuariosRol;
    }

    public void setSsUsuariosRol(SsUsuarios ssUsuariosRol) {
        this.ssUsuariosRol = ssUsuariosRol;
    }

    public List<SsRoles> getListadoRoles() {
        return listadoRoles;
    }

    public void setListadoRoles(List<SsRoles> listadoRoles) {
        this.listadoRoles = listadoRoles;
    }

    public DualListModel<SsRoles> getRoles() {
        return roles;
    }

    public void setRoles(DualListModel<SsRoles> roles) {
        this.roles = roles;
    }

    public List<SsRoles> getRolesSource() {
        return rolesSource;
    }

    public void setRolesSource(List<SsRoles> rolesSource) {
        this.rolesSource = rolesSource;
    }

    public List<SsRoles> getRolesTarget() {
        return rolesTarget;
    }

    public void setRolesTarget(List<SsRoles> rolesTarget) {
        this.rolesTarget = rolesTarget;
    }

    public List<SsRoles> getRolesTargetTemp() {
        return rolesTargetTemp;
    }

    public void setRolesTargetTemp(List<SsRoles> rolesTargetTemp) {
        this.rolesTargetTemp = rolesTargetTemp;
    }

    public List<SsUsuarios> getListadoUsuarios() {
        return listadoUsuarios;
    }

    public void setListadoUsuarios(List<SsUsuarios> listadoUsuarios) {
        this.listadoUsuarios = listadoUsuarios;
    }

}
