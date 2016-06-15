/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.pojo.rpt;

import java.io.Serializable;

/**
 *
 * @author HDEZ
 */
public class RptIndicePromedioDeGastoReparacionEquipo implements Serializable{
    
    private String area;
    private String equipo;
    private String fechaRep;
    private Double gasto;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getFechaRep() {
        return fechaRep;
    }

    public void setFechaRep(String fechaRep) {
        this.fechaRep = fechaRep;
    }

    public Double getGasto() {
        return gasto;
    }

    public void setGasto(Double gasto) {
        this.gasto = gasto;
    }
    
    
    
}
