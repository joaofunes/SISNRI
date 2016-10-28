/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.pojo.rpt;

import java.io.Serializable;

/**
 *
 * @author Joao
 */
public class PojoPropuestaConvenio implements Serializable{
    
    
    private String NOMBRE_PROPUESTA;
    private String TIPO_CONVENIO;
    private String NOMBRE_ESTADO;
    private String ID_PROPUESTA;
    private String SOLICITANTE;
    private String INTERNO;
    private String EXTERNO;
    private String PROPUESTA;
    private String FINALIDAD_PROPUESTA;
    private String VIGENCIA;

    public String getNOMBRE_PROPUESTA() {
        return NOMBRE_PROPUESTA;
    }

    public void setNOMBRE_PROPUESTA(String NOMBRE_PROPUESTA) {
        this.NOMBRE_PROPUESTA = NOMBRE_PROPUESTA;
    }

    public String getTIPO_CONVENIO() {
        return TIPO_CONVENIO;
    }

    public void setTIPO_CONVENIO(String TIPO_CONVENIO) {
        this.TIPO_CONVENIO = TIPO_CONVENIO;
    }

    public String getNOMBRE_ESTADO() {
        return NOMBRE_ESTADO;
    }

    public void setNOMBRE_ESTADO(String NOMBRE_ESTADO) {
        this.NOMBRE_ESTADO = NOMBRE_ESTADO;
    }

    public String getID_PROPUESTA() {
        return ID_PROPUESTA;
    }

    public void setID_PROPUESTA(String ID_PROPUESTA) {
        this.ID_PROPUESTA = ID_PROPUESTA;
    }

    public String getSOLICITANTE() {
        return SOLICITANTE;
    }

    public void setSOLICITANTE(String SOLICITANTE) {
        this.SOLICITANTE = SOLICITANTE;
    }

    public String getINTERNO() {
        return INTERNO;
    }

    public void setINTERNO(String INTERNO) {
        this.INTERNO = INTERNO;
    }

    public String getEXTERNO() {
        return EXTERNO;
    }

    public void setEXTERNO(String EXTERNO) {
        this.EXTERNO = EXTERNO;
    }

    public String getPROPUESTA() {
        return PROPUESTA;
    }

    public void setPROPUESTA(String PROPUESTA) {
        this.PROPUESTA = PROPUESTA;
    }

    public String getFINALIDAD_PROPUESTA() {
        return FINALIDAD_PROPUESTA;
    }

    public void setFINALIDAD_PROPUESTA(String FINALIDAD_PROPUESTA) {
        this.FINALIDAD_PROPUESTA = FINALIDAD_PROPUESTA;
    }

    public String getVIGENCIA() {
        return VIGENCIA;
    }

    public void setVIGENCIA(String VIGENCIA) {
        this.VIGENCIA = VIGENCIA;
    }

   
}
