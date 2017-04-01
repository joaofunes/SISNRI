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
public class RptBitacoraEstadosPojo implements Serializable{
    
    private String Nombre;
    private Date Fiscalia;
    private Date CSU;
    private Date AGU;
    private Date Firmado;
    private String Finalidad;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Date getFiscalia() {
        return Fiscalia;
    }

    public void setFiscalia(Date Fiscalia) {
        this.Fiscalia = Fiscalia;
    }

    public Date getCSU() {
        return CSU;
    }

    public void setCSU(Date CSU) {
        this.CSU = CSU;
    }

    public Date getAGU() {
        return AGU;
    }

    public void setAGU(Date AGU) {
        this.AGU = AGU;
    }

    public Date getFirmado() {
        return Firmado;
    }

    public void setFirmado(Date Firmado) {
        this.Firmado = Firmado;
    }

    public String getFinalidad() {
        return Finalidad;
    }

    public void setFinalidad(String Finalidad) {
        this.Finalidad = Finalidad;
    }

}
