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
public class RptIndiceDeEncarrilamiento  implements Serializable {
 
   
    
    private String equipo;
    private String area;
    private Double tasaRep;
    private String tiempoRe;
    private Double gastoRepa;
    private Double gastoDept;

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getTasaRep() {
        return tasaRep;
    }

    public void setTasaRep(Double tasaRep) {
        this.tasaRep = tasaRep;
    }

    public String getTiempoRe() {
        return tiempoRe;
    }

    public void setTiempoRe(String tiempoRe) {
        this.tiempoRe = tiempoRe;
    }

    public Double getGastoRepa() {
        return gastoRepa;
    }

    public void setGastoRepa(Double gastoRepa) {
        this.gastoRepa = gastoRepa;
    }

    public Double getGastoDept() {
        return gastoDept;
    }

    public void setGastoDept(Double gastoDept) {
        this.gastoDept = gastoDept;
    }

 
    
    
    
    
}
