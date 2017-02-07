/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.pojo.rpt;

import java.io.Serializable;

/**
 *
 * @author Cortez
 */
public class PojoBeca implements Serializable {

    private static final long serialVersionUID = 1113799434508676095L;
    private Integer idBeca;
    private Integer anioGestion;
    private String programaBeca;
    private String nombreBecario;
    private String apellidoBecario;
    private String correoBecario;
    private String facultad;
    private String paisDestino;
    private String universidadDestino;
    private Double montoBeca;
    private String otorgada;

    public Integer getIdBeca() {
        return idBeca;
    }

    public void setIdBeca(Integer idBeca) {
        this.idBeca = idBeca;
    }

    public String getProgramaBeca() {
        return programaBeca;
    }

    public void setProgramaBeca(String programaBeca) {
        this.programaBeca = programaBeca;
    }

    public String getNombreBecario() {
        return nombreBecario;
    }

    public void setNombreBecario(String nombreBecario) {
        this.nombreBecario = nombreBecario;
    }

    public String getApellidoBecario() {
        return apellidoBecario;
    }

    public void setApellidoBecario(String apellidoBecario) {
        this.apellidoBecario = apellidoBecario;
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

    public String getOtorgada() {
        return otorgada;
    }

    public void setOtorgada(String otorgada) {
        this.otorgada = otorgada;
    }

    public Integer getAnioGestion() {
        return anioGestion;
    }

    public void setAnioGestion(Integer anioGestion) {
        this.anioGestion = anioGestion;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getCorreoBecario() {
        return correoBecario;
    }

    public void setCorreoBecario(String correoBecario) {
        this.correoBecario = correoBecario;
    }

}
