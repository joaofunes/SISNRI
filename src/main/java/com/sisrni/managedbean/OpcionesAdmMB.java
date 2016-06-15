/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.SsOpciones;
import com.sisrni.model.SsRoles;
import com.sisrni.model.managedbean.crud.util.JsfUtil;
import com.sisrni.security.AppUserDetails;
import com.sisrni.service.SsOpcionesService;
import com.sisrni.service.SsRolesService;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

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
public class OpcionesAdmMB implements Serializable{
    
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
            setActualizar(false);
            getMenus();
        } catch (Exception e) {
        }
    }
    
    
    public void getMenus(){
        try { 
            roles = new SsRoles();
            listadoOpciones = null;
            listadoOpciones = opcionesService.getDao().findAll();           
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

	public void preEditar(SsOpciones ssOpciones) throws Exception {
		try {
			this.ssOpciones = ssOpciones;
                        setActualizar(true);
                        RequestContext.getCurrentInstance().update("formAdmin");
			RequestContext.getCurrentInstance().update("formMenu");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error class MenusOpcionesMB - preEditar()\n" + e.getMessage(), e.getCause());
		}finally{
			
			
		}
	}
	
	public void preEliminar(SsOpciones ssOpciones) throws Exception  {
		try {
			this.ssOpciones = ssOpciones;                       
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
			opcionesService.getDao().merge(ssOpciones);
			getMenus();
			RequestContext.getCurrentInstance().update("formAdmin");
			RequestContext.getCurrentInstance().update("formMenu");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error class MenusOpcionesMB - Editar()\n" + e.getMessage(), e.getCause());
		}finally{
			this.ssOpciones = null;
                        this.ssOpciones = new SsOpciones();
                        setActualizar(false);
		}
	}
	
	
	public void guardar(){
		try {	
                        ssOpciones.setFechaRegistro(new Date());
                        ssOpciones.setUsuarioRegistro(usuario.getUsuario().getCodigoUsuario());                        
                        ssOpciones.setSsRolesSet(usuario.getUsuario().getSsRolesSet());
                
		        opcionesService.getDao().save(ssOpciones);
			getMenus();
                        this.ssOpciones = null;
                        this.ssOpciones = new SsOpciones();
			RequestContext.getCurrentInstance().update("formAdmin");
			RequestContext.getCurrentInstance().update("formMenu");
			JsfUtil.addSuccessMessage("Guardado Exitosamente");
                        JsfUtil.showFacesMsg(null,"Guardado Exitosamente","growMessage",FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			JsfUtil.addErrorMessage(e,"Erro al Guardar--- CountryMB");
		}finally {
			
		}
	}
	
    
        public void cancelar() throws Exception{
		try {
			this.ssOpciones = null;                        
                        setActualizar(false);
                        RequestContext.getCurrentInstance().update(":formAdmin");
			RequestContext.getCurrentInstance().update(":formMenu");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error class MenusOpcionesMB - Editar()\n" + e.getMessage(), e.getCause());
		}finally{
			this.ssOpciones = new SsOpciones();
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

    

  

   
}
