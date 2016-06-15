/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.SsMenus;
import com.sisrni.model.SsRoles;
import com.sisrni.model.SsUsuarios;
import com.sisrni.model.managedbean.crud.util.JsfUtil;
import com.sisrni.security.AppUserDetails;
import com.sisrni.service.SsMenusService;
import com.sisrni.service.SsRolesService;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;


/**
 *
 * @author Joao
 */
@Named("menusOpcionesMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MenusOpcionesMB implements Serializable{
    
    @Autowired
    @Qualifier(value = "ssMenuService")
    private SsMenusService menusService;
    
    @Autowired
    @Qualifier(value = "ssRolesService")
    private SsRolesService ssRolesService;
    
    
    
    private boolean actualizar;
    private List<SsMenus> listadoMenus;
   
    private SsMenus ssMenus;
    private SsRoles roles;
    
    private CurrentUserSessionBean user;
    private AppUserDetails usuario;
     

    
    @PostConstruct
    public void init() {
        try {
            user = new CurrentUserSessionBean();
            ssMenus = new SsMenus();
            setActualizar(false);
            getMenus();
        } catch (Exception e) {
        }
    }
    
    
    public void getMenus(){
        try { 
            roles = new SsRoles();
            
              usuario = user.getSessionUser();
            
             for(SsRoles iter: usuario.getUsuario().getSsRolesSet()){
                listadoMenus = menusService.getMenusByrol(iter);
                  
             }
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

	public void preEditar(SsMenus ssMenus) throws Exception {
		try {
			this.ssMenus = ssMenus;
                        setActualizar(true);
                        RequestContext.getCurrentInstance().update("formAdmin");
			RequestContext.getCurrentInstance().update("formMenu");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error class MenusOpcionesMB - preEditar()\n" + e.getMessage(), e.getCause());
		}finally{
			
			
		}
	}
	
	public void preEliminar(SsMenus ssMenus) throws Exception  {
		try {
			this.ssMenus = ssMenus;                       
                        RequestContext.getCurrentInstance().update("formAdmin");
			RequestContext.getCurrentInstance().update("formMenu");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error class MenusOpcionesMB - preEliminar()\n" + e.getMessage(), e.getCause());
		}finally{
		      
            }
	}
	
	
	public void editar() throws Exception{
		try {
			menusService.getDao().merge(ssMenus);
			getMenus();
			RequestContext.getCurrentInstance().update("formAdmin");
			RequestContext.getCurrentInstance().update("formMenu");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error class MenusOpcionesMB - Editar()\n" + e.getMessage(), e.getCause());
		}finally{
			this.ssMenus = null;
                        this.ssMenus = new SsMenus();
                        setActualizar(false);
		}
	}
	
	
	public void guardar(){
		try {	
                        getSsMenus().setFechaRegistro(new Date());
                        getSsMenus().setUsuarioRegistro("UsuarioPrueba");
                        getSsMenus().setSsRolesSet(usuario.getUsuario().getSsRolesSet());
                
		        menusService.getDao().save(getSsMenus());
			getMenus();
			RequestContext.getCurrentInstance().update("formAdmin");
			RequestContext.getCurrentInstance().update("formMenu");
			JsfUtil.addSuccessMessage("Guardado Exitosamente");

		} catch (Exception e) {
			e.printStackTrace();
			JsfUtil.addErrorMessage(e,"Erro al Guardar--- CountryMB");
		}finally {
			this.ssMenus = null;
                        this.ssMenus = new SsMenus();
		}
	}
	
    
        public void cancelar() throws Exception{
		try {
			this.ssMenus = null;                        
                        setActualizar(false);
                        RequestContext.getCurrentInstance().update("formAdmin");
			RequestContext.getCurrentInstance().update("formMenu");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error class MenusOpcionesMB - Editar()\n" + e.getMessage(), e.getCause());
		}finally{
			this.ssMenus = new SsMenus();
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

    
    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

  

   
}
