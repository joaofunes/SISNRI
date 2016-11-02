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
    private String ID_SOLICITANTE;
    private String ID_REF_INTERNO;
    private String ID_REF_EXTERNO;

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

    public String getID_SOLICITANTE() {
        return ID_SOLICITANTE;
    }

    public void setID_SOLICITANTE(String ID_SOLICITANTE) {
        this.ID_SOLICITANTE = ID_SOLICITANTE;
    }

    public String getID_REF_INTERNO() {
        return ID_REF_INTERNO;
    }

    public void setID_REF_INTERNO(String ID_REF_INTERNO) {
        this.ID_REF_INTERNO = ID_REF_INTERNO;
    }

    public String getID_REF_EXTERNO() {
        return ID_REF_EXTERNO;
    }

    public void setID_REF_EXTERNO(String ID_REF_EXTERNO) {
        this.ID_REF_EXTERNO = ID_REF_EXTERNO;
    }

   
}
