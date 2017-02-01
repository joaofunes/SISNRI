/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.pojo.rpt;

/**
 *
 * @author Cortez
 */
public class BecasGestionadasPojo {

    private Integer anio;
    private String nombrePais;
    private String nombreFacultad;
    private String organismo;
    private Integer gestionadas;
    private Integer becasOtorgadas;
    private Integer becasDenegadas;
    private Double montoOtorgadas;
    private Double montoDenegadas;

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getGestionadas() {
        return gestionadas;
    }

    public void setGestionadas(Integer gestionadas) {
        this.gestionadas = gestionadas;
    }

    public Integer getBecasOtorgadas() {
        return becasOtorgadas;
    }

    public void setBecasOtorgadas(Integer becasOtorgadas) {
        this.becasOtorgadas = becasOtorgadas;
    }

    public Double getMontoOtorgadas() {
        return montoOtorgadas;
    }

    public void setMontoOtorgadas(Double montoOtorgadas) {
        this.montoOtorgadas = montoOtorgadas;
    }

    public Double getMontoDenegadas() {
        return montoDenegadas;
    }

    public void setMontoDenegadas(Double montoDenegadas) {
        this.montoDenegadas = montoDenegadas;
    }

    public Integer getBecasDenegadas() {
        return becasDenegadas;
    }

    public void setBecasDenegadas(Integer becasDenegadas) {
        this.becasDenegadas = becasDenegadas;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getNombreFacultad() {
        return nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }

    public String getOrganismo() {
        return organismo;
    }

    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

   
}
