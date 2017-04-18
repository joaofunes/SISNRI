/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.SsMenus;
import com.sisrni.model.SsRoles;

import com.sisrni.security.AppUserDetails;
import com.sisrni.service.SsMenusService;
import com.sisrni.service.SsRolesService;
import com.sisrni.utils.JsfUtil;


import java.io.Serializable;
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
 * @author Luis
 */
@Named("rolesMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class RolesMB implements Serializable{
    
    @Autowired
    @Qualifier(value = "ssRolesService")
    private SsRolesService rolesService;
 
      
    private boolean actualizar;
    private List<SsRoles> listadoRoles;
   
    private SsRoles ssRoles;
    private SsRoles roles;
    
    private CurrentUserSessionBean user;
    private AppUserDetails usuario;
     

    
    @PostConstruct
    public void init() {
        try {
            user = new CurrentUserSessionBean();
            ssRoles = new SsRoles();
            setActualizar(false);
            getRoles();
        } catch (Exception e) {
        }
    }
    
    
    public void getRoles(){
        try { 
            ssRoles = new SsRoles();
            
              usuario = user.getSessionUser();
            
//             for(SsRoles iter: usuario.getUsuario().getSsRolesSet()){
                listadoRoles = rolesService.getAllByIdDesc();
                  
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
        }
    }
    
    
    /***
	 * 
	 * @param country
	 * @throws Exception
	 */

	public void preEditar(SsRoles ssRoles) throws Exception {
		try {
			this.ssRoles = ssRoles;
                        setActualizar(true);
                        RequestContext.getCurrentInstance().update("formAdmin");
			RequestContext.getCurrentInstance().update("formMenu");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error class RolesOpcionesMB - preEditar()\n" + e.getMessage(), e.getCause());
		}finally{
			
			
		}
	}
	
	public void preEliminar(SsRoles ssRoles) throws Exception  {
		try {
			this.ssRoles = ssRoles;                       
                        RequestContext.getCurrentInstance().update("formAdmin");
			RequestContext.getCurrentInstance().update("formRol");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error class RolesOpcionesMB - preEliminar()\n" + e.getMessage(), e.getCause());
		}finally{
		      
            }
	}
        
        public void preBorradoRol(SsRoles ssRoles){
        //guardando en 'pais' la instancia de 'Pais' que se recibe como argumento
        this.ssRoles = ssRoles; 
        RequestContext context = RequestContext.getCurrentInstance();
        //Desplegando el 'Dialog'
        context.execute("PF('confirmDeleteRolDlg').show();");
    }
	
	
	public void editar() throws Exception{
		try {
			rolesService.getDao().merge(ssRoles);
			getRoles();
			RequestContext.getCurrentInstance().update("formAdmin");
			RequestContext.getCurrentInstance().update("formRol");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error class RolesOpcionesMB - Editar()\n" + e.getMessage(), e.getCause());
		}finally{
			this.ssRoles = null;
                        this.ssRoles = new SsRoles();
                        setActualizar(false);
		}
	}
	
           /**
     * Metodo que borra una instancia de 'Rol' de la Base de datos
     */
    public void borrarRol(){ 
        String msg ="Rol Eliminado Exitosamente!";
        try{
            //Borrando la instancia de rol
            rolesService.delete(ssRoles);
            init();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmDeleteRolDlg').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminado!!", msg));
        }catch(Exception e){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Rol!"));
           //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Rol!");
            e.printStackTrace();
        }finally{
            actualizar = false;
        }
        
        
    }
	
	public void guardar(){
		try {	
                      usuario = user.getSessionUser();
                      String username;
                      username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
                        getSsRoles().setFechaRegistro(new Date());
                        getSsRoles().setUsuarioRegistro(usuario.getUsuario().getCodigoUsuario());
                       // getSsRoles().setSsRolesSet(usuario.getUsuario().getSsRolesSet());
                
		        rolesService.getDao().save(getSsRoles());
			getRoles();
			RequestContext.getCurrentInstance().update("formAdmin");
			RequestContext.getCurrentInstance().update("formRol");
                        JsfUtil.addSuccessMessage(username);
			//JsfUtil.addSuccessMessage("Guardado Exitosamente");

		} catch (Exception e) {
			e.printStackTrace();
			JsfUtil.addErrorMessage(e,"Erro al Guardar--- CountryMB");
		}finally {
			this.ssRoles = null;
                        this.ssRoles = new SsRoles();
		}
	}
	
    
        public void cancelar() throws Exception{
		try {
			this.ssRoles = null;                        
                        setActualizar(false);
                        RequestContext.getCurrentInstance().update("formAdmin");
			RequestContext.getCurrentInstance().update("formRol");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error class RoleMB - Editar()\n" + e.getMessage(), e.getCause());
		}finally{
			this.ssRoles = new SsRoles();
		}
	}
    
    
    
    
    
    
    
    /**
     * @return the listadoRoles
     */
    public List<SsRoles> getListadoRoles() {
        return listadoRoles;
    }

    /**
     * @param listadoRoles the listadoRoles to set
     */
    public void setListadoRoles(List<SsRoles> listadoRoles) {
        this.listadoRoles = listadoRoles;
    }

    /**
     * @return the ssRoles
     */
    public SsRoles getSsRoles() {
        return ssRoles;
    }

    /**
     * @param ssRoles the ssRoles to set
     */
    public void setSsRoles(SsRoles ssRoles) {
        this.ssRoles = ssRoles;
    }

    
    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

  

   
}
