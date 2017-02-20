/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.Estado;
import com.sisrni.model.PropuestaConvenio;
import com.sisrni.pojo.rpt.PojoPropuestaConvenio;
import com.sisrni.service.EstadoService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.PropuestaConvenioService;
import com.sisrni.service.PropuestaEstadoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Joao
 */

@Named("consultarPropuestaConvenioMB")
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class ConsultarPropuestaConvenioMB implements Serializable{
    
    private static final long serialVersionUID = 1L;  
    
    @Inject
    ActualizacionPropuestaConvenioMB actualizacionPropuestaConvenioMB;
    
    
    @Autowired
    @Qualifier(value = "propuestaConvenioService")
    private PropuestaConvenioService propuestaConvenioService;
    
    @Autowired
    @Qualifier(value = "personaService")
    private PersonaService personaService;

    @Autowired
    @Qualifier(value = "estadoService")
    private EstadoService estadoService;
    
    
    @Autowired
    @Qualifier(value = "propuestaEstadoService")
    private PropuestaEstadoService propuestaEstadoService;
    
    private List<PojoPropuestaConvenio> listadoPropuestaConvenio;
    private PropuestaConvenio propuestaConvenio;
    private PojoPropuestaConvenio pojoPropuestaConvenio;
    private List<Estado> listadoEstados;
    private List<Estado> listadoEstadosTemp;
    private Estado estado;
    
    
    @PostConstruct
    public void init() {
        try {
          
           inicializador();   
           
        } catch (Exception e) {
        }
    } 

    private void inicializador() {
        try {
            propuestaConvenio = new PropuestaConvenio();    
            estado = new Estado();
            listadoPropuestaConvenio= propuestaConvenioService.getAllPropuestaConvenioSQL();    

            
            Collections.sort(listadoPropuestaConvenio, new Comparator<PojoPropuestaConvenio>() {
                @Override
                public int compare(PojoPropuestaConvenio lhs, PojoPropuestaConvenio rhs) {
                    return rhs.getFECHA_INGRESO().compareTo(lhs.getFECHA_INGRESO());
                }
            });
            
            listadoEstados = estadoService.getEstadoPropuestasConvenio();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

    
    public void preEditar(PojoPropuestaConvenio pj){
        try {
            
            actualizacionPropuestaConvenioMB.postInit();
            
            if (pj.getID_SOLICITANTE() != null) {
                //settea solicitante
                actualizacionPropuestaConvenioMB.setSolicitante(personaService.getByID(pj.getID_SOLICITANTE()));
                actualizacionPropuestaConvenioMB.cargarUnidadesFacultadesSolicitante();
                actualizacionPropuestaConvenioMB.cargarTelefonosSolicitante();
            }
            if (pj.getID_REF_INTERNO() != null) {                
                actualizacionPropuestaConvenioMB.setReferenteInterno(personaService.getByID(pj.getID_REF_INTERNO()));
                actualizacionPropuestaConvenioMB.cargarTelefonosInternos();
                actualizacionPropuestaConvenioMB.cargarUnidadesFacultadesSolicitanteInterno();
                actualizacionPropuestaConvenioMB.setTabAsisMostrar(Boolean.TRUE);
                actualizacionPropuestaConvenioMB.mostrarTab();
            }else{
                actualizacionPropuestaConvenioMB.setFlagEdicionInterno(Boolean.FALSE);
            }
            if (pj.getID_REF_EXTERNO() != null) {                
                actualizacionPropuestaConvenioMB.setReferenteExterno(personaService.getByID(pj.getID_REF_EXTERNO()));
                actualizacionPropuestaConvenioMB.cargarTelefonosExterno();
                actualizacionPropuestaConvenioMB.setTabAsisMostrarExterno(Boolean.TRUE);
                actualizacionPropuestaConvenioMB.mostrarTabExterno();
            }else{
                actualizacionPropuestaConvenioMB.setFlagEdicionExterno(Boolean.FALSE);
            }
            
            actualizacionPropuestaConvenioMB.cargarPropuestaConvenio(pj.getID_PROPUESTA());               
            actualizacionPropuestaConvenioMB.onTipoConvenioChange();
            
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();             
           // String outcome = "propuestaCovenio.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect("actualizarPropuestaCovenio.xhtml");
            
            //context.redirect(context.getRequestContextPath() + "/views/convenio/propuestaCovenio.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
    /**
     * Metodo que actualiza el listado de estado seleccionable por cada
     * propuesta convenio
     *
     * @param pojo
     */
    public void preCambiarEstado(PojoPropuestaConvenio pojo) {
        try {
            pojoPropuestaConvenio = propuestaConvenioService.getAllPropuestaConvenioSQLByID(pojo.getID_PROPUESTA());
            estado = estadoService.findById(pojo.getID_ESTADO());
            listadoEstadosTemp = new ArrayList<Estado>();
            int[] intArray = new int[3];
            intArray[0] = (estado.getOrdenEstado() - 1);
            intArray[1] = estado.getOrdenEstado();
            intArray[2] = (estado.getOrdenEstado() + 1);

            for (int i = 0; i < intArray.length; i++) {
                llenarListadoEstados(intArray[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para agregar estados a listado de estados
     *
     * @param orden
     */
    private void llenarListadoEstados(int orden) {
        try {
            for (Estado std : listadoEstados) {
                if (std.getTipoEstado() == 1 && std.getOrdenEstado() == orden) {
                    listadoEstadosTemp.add(std);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
         
     public void preEliminar(PojoPropuestaConvenio pojo){
        try {
            pojoPropuestaConvenio = propuestaConvenioService.getAllPropuestaConvenioSQLByID(pojo.getID_PROPUESTA());
            estado=estadoService.findById(pojo.getID_ESTADO());
           // RequestContext context = RequestContext.getCurrentInstance();              
           // context.execute("PF('dataChangeDlg').show();");
           //RequestContext.getCurrentInstance().update(":formPrincipal");
        } catch (Exception e) {
         e.printStackTrace();
        }
    }
    
    
    public void eliminarConvenio(){
        try {                    
            propuestaEstadoService.updatePropuestaEstado(pojoPropuestaConvenio.getID_PROPUESTA(),estado.getIdEstado());                    
            inicializador();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    public PropuestaConvenio getPropuestaConvenio() {
        return propuestaConvenio;
    }

    public void setPropuestaConvenio(PropuestaConvenio propuestaConvenio) {
        this.propuestaConvenio = propuestaConvenio;
    }

    public List<PojoPropuestaConvenio> getListadoPropuestaConvenio() {
        return listadoPropuestaConvenio;
    }

    public void setListadoPropuestaConvenio(List<PojoPropuestaConvenio> listadoPropuestaConvenio) {
        this.listadoPropuestaConvenio = listadoPropuestaConvenio;
    }

    public PojoPropuestaConvenio getPojoPropuestaConvenio() {
        return pojoPropuestaConvenio;
    }

    public void setPojoPropuestaConvenio(PojoPropuestaConvenio pojoPropuestaConvenio) {
        this.pojoPropuestaConvenio = pojoPropuestaConvenio;
    }

    public List<Estado> getListadoEstados() {
        return listadoEstados;
    }

    public void setListadoEstados(List<Estado> listadoEstados) {
        this.listadoEstados = listadoEstados;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getListadoEstadosTemp() {
        return listadoEstadosTemp;
    }

    public void setListadoEstadosTemp(List<Estado> listadoEstadosTemp) {
        this.listadoEstadosTemp = listadoEstadosTemp;
    }
}
