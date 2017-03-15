/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.pojo.rpt;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Joao
 */
public class PojoPropuestaConvenio implements Serializable{
    
    private static final long serialVersionUID = 1113799434508676095L;
    private String NOMBRE_PROPUESTA;
    private String TIPO_CONVENIO;
    private String NOMBRE_ESTADO;
    private Integer ID_PROPUESTA;
    private String SOLICITANTE;
    private String INTERNO;
    private String EXTERNO;
    private Integer PROPUESTA;
    private String FINALIDAD_PROPUESTA;
    private Date VIGENCIA;
    private Integer ID_SOLICITANTE;
    private Integer ID_REF_INTERNO;
    private Integer ID_REF_EXTERNO;
    private Integer ID_ESTADO;
    private Date FECHA_INGRESO;
    private boolean ACTIVO;
    
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

    public Integer getPROPUESTA() {
        return PROPUESTA;
    }

    public void setPROPUESTA(Integer PROPUESTA) {
        this.PROPUESTA = PROPUESTA;
    }

    

    public String getFINALIDAD_PROPUESTA() {
        return FINALIDAD_PROPUESTA;
    }

    public void setFINALIDAD_PROPUESTA(String FINALIDAD_PROPUESTA) {
        this.FINALIDAD_PROPUESTA = FINALIDAD_PROPUESTA;
    }
    
    public Integer getID_ESTADO() {
        return ID_ESTADO;
    }

    public void setID_ESTADO(Integer ID_ESTADO) {
        this.ID_ESTADO = ID_ESTADO;
    }

    public Integer getID_PROPUESTA() {
        return ID_PROPUESTA;
    }

    public void setID_PROPUESTA(Integer ID_PROPUESTA) {
        this.ID_PROPUESTA = ID_PROPUESTA;
    }

    public Integer getID_SOLICITANTE() {
        return ID_SOLICITANTE;
    }

    public void setID_SOLICITANTE(Integer ID_SOLICITANTE) {
        this.ID_SOLICITANTE = ID_SOLICITANTE;
    }

    public Integer getID_REF_INTERNO() {
        return ID_REF_INTERNO;
    }

    public void setID_REF_INTERNO(Integer ID_REF_INTERNO) {
        this.ID_REF_INTERNO = ID_REF_INTERNO;
    }

    public Integer getID_REF_EXTERNO() {
        return ID_REF_EXTERNO;
    }

    public void setID_REF_EXTERNO(Integer ID_REF_EXTERNO) {
        this.ID_REF_EXTERNO = ID_REF_EXTERNO;
    }

    public Date getFECHA_INGRESO() {
        return FECHA_INGRESO;
    }

    public void setFECHA_INGRESO(Date FECHA_INGRESO) {
        this.FECHA_INGRESO = FECHA_INGRESO;
    }

    public boolean isACTIVO() {
        return ACTIVO;
    }

    public void setACTIVO(boolean ACTIVO) {
        this.ACTIVO = ACTIVO;
    }

    public Date getVIGENCIA() {
        return VIGENCIA;
    }

    public void setVIGENCIA(Date VIGENCIA) {
        this.VIGENCIA = VIGENCIA;
    }
    
   
}
