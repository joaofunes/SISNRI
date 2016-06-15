/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.pojo.rpt;
import java.io.Serializable;

/**
 *
 * @author RAUL
 */
public class RptTasaExitoFalloReparaciones implements Serializable {
    
    
    private String sFechaInicio;
    private String sFechaFinal;
    private String areaHospitalaria;
    private String tasaExito;
    private String tasaFallo;
    private String tasaPromedio;

    public RptTasaExitoFalloReparaciones(String sFechaInicio, String sFechaFinal, String areaHospitalaria) {
        this.sFechaInicio = sFechaInicio;
        this.sFechaFinal = sFechaFinal;
        this.areaHospitalaria = areaHospitalaria;
    }
    
    

    public String getsFechaInicio() {
        return sFechaInicio;
    }

    public void setsFechaInicio(String sFechaInicio) {
        this.sFechaInicio = sFechaInicio;
    }

    public String getsFechaFinal() {
        return sFechaFinal;
    }

    public void setsFechaFinal(String sFechaFinal) {
        this.sFechaFinal = sFechaFinal;
    }

    public String getAreaHospitalaria() {
        return areaHospitalaria;
    }

    public void setAreaHospitalaria(String areaHospitalaria) {
        this.areaHospitalaria = areaHospitalaria;
    }

    public String getTasaExito() {
        return tasaExito;
    }

    public void setTasaExito(String tasaExito) {
        this.tasaExito = tasaExito;
    }

    public String getTasaFallo() {
        return tasaFallo;
    }

    public void setTasaFallo(String tasaFallo) {
        this.tasaFallo = tasaFallo;
    }

    public String getTasaPromedio() {
        return tasaPromedio;
    }

    public void setTasaPromedio(String tasaPromedio) {
        this.tasaPromedio = tasaPromedio;
    }
    
    
    
}
