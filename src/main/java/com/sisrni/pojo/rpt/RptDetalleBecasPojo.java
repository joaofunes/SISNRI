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
public class RptDetalleBecasPojo {
    private String nombre;
    private String apellido;
    private String facultad;
    private String programaBeca;
    private Integer anio;
    private String paisDestino;
    private String universidadDestino;
    private Double montoBeca;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getProgramaBeca() {
        return programaBeca;
    }

    public void setProgramaBeca(String programaBeca) {
        this.programaBeca = programaBeca;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public String getUniversidadDestino() {
        return universidadDestino;
    }

    public void setUniversidadDestino(String universidadDestino) {
        this.universidadDestino = universidadDestino;
    }

    public Double getMontoBeca() {
        return montoBeca;
    }

    public void setMontoBeca(Double montoBeca) {
        this.montoBeca = montoBeca;
    }
    
}
