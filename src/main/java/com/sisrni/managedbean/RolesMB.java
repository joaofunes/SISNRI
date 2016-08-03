/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.SsMenus;
import com.sisrni.model.SsRoles;

import com.sisrni.model.managedbean.crud.util.JsfUtil;
import com.sisrni.security.AppUserDetails;
import com.sisrni.service.SsMenusService;
import com.sisrni.service.SsRolesService;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

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
                listadoRoles = rolesService.findAll();
                  
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
	
	
	public void guardar(){
		try {	
                        getSsRoles().setFechaRegistro(new Date());
                        getSsRoles().setUsuarioRegistro("UsuarioPrueba3");
                       // getSsRoles().setSsRolesSet(usuario.getUsuario().getSsRolesSet());
                
		        rolesService.getDao().save(getSsRoles());
			getRoles();
			RequestContext.getCurrentInstance().update("formAdmin");
			RequestContext.getCurrentInstance().update("formRol");
			JsfUtil.addSuccessMessage("Guardado Exitósamente");

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
