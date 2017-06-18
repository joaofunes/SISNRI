/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean.form;

import com.sisrni.model.Estado;
import com.sisrni.service.DocumentoService;
import com.sisrni.service.EstadoService;
import com.sisrni.service.PersonaPropuestaService;
import com.sisrni.service.PropuestaConvenioService;
import com.sisrni.service.PropuestaEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Joao
 */
public class CancelarConvenioForm {
    
    
    private static final String CANCELADO = "RECHAZADA";
    private static final String REVISION = "REVISION";
    
    private Estado estado;
    
    @Autowired
    @Qualifier(value = "propuestaEstadoService")
    private PropuestaEstadoService propuestaEstadoService;
        
    @Autowired
    @Qualifier(value = "personaPropuestaService")
    private PersonaPropuestaService personaPropuestaService;
    
    @Autowired
    @Qualifier(value = "propuestaConvenioService")
    private PropuestaConvenioService propuestaConvenioService;
    
    @Autowired
    @Qualifier(value = "documentoService")
    private DocumentoService documentoService;
    
    @Autowired
    @Qualifier(value = "estadoService")
    private EstadoService estadoService;
    
    

    /**
     * cambiar estado de propuestas de convenio a cancelada
     * @param idPropuesta
     * @return 
     */

    public int cambiarEstadoCanceladoConvenio(int idPropuesta) {
        int respuesta=0;
        try {            
            estado= estadoService.getEstadoByName(CANCELADO);                                   
            respuesta=propuestaEstadoService.updatePropuestaEstado(idPropuesta, estado.getIdEstado());   
            
            System.out.println("Respuesta :::::::::::::::  "+respuesta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    
    /**
     * cambiar estado de propuestas de convenio a revision
     * @param idPropuesta
     * @return 
     */

    public int cambiarEstadoRevisionConvenio(int idPropuesta) {
        int respuesta=0;
        try {            
            estado= estadoService.getEstadoByName(REVISION);                                   
            respuesta=propuestaEstadoService.updatePropuestaEstado(idPropuesta, estado.getIdEstado());                               
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    
    
    /**
     * *
     * metodo para eliminacion de una propuesta
     * @param idPropuesta
     * @return 
     */
    public int eliminarConvenio(int idPropuesta) {
        int i=0,l=0,j=0,m=0,respuesta=0;
        try {
            //eliminacion de personas asociadas a la propuesta
            i=personaPropuestaService.deleteByPersonasPropuestas(idPropuesta);

            //eliminar estado de la propuesta
            l=propuestaEstadoService.deletePropuestaEstado(idPropuesta);

            //eliminar documento
            j=documentoService.deleteDocumentosPropuestas(idPropuesta);

            //eliminar propuesta de convenio
            m=propuestaConvenioService.deletePropuestas(idPropuesta);

            if(i==1 && l==1 && j==1 && m==1){
                respuesta=1;
                return respuesta;
            }
            else{
                respuesta =0;
                return respuesta;
            }
            
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }


    
}
