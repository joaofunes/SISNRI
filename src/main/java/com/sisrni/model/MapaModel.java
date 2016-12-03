/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

/**
 *
 * @author Cortez
 */
public class MapaModel {

    public MapaModel(String pais, String montoCooperacion, String cantidadProyectos) {
        super();
        this.pais = pais;
        this.montoCooperacion = montoCooperacion;
        this.cantidadProyectos = cantidadProyectos;
    }

    private String pais;
    private String montoCooperacion;
    private String cantidadProyectos;

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getMontoCooperacion() {
        return montoCooperacion;
    }

    public void setMontoCooperacion(String montoCooperacion) {
        this.montoCooperacion = montoCooperacion;
    }

    public String getCantidadProyectos() {
        return cantidadProyectos;
    }

    public void setCantidadProyectos(String cantidadProyectos) {
        this.cantidadProyectos = cantidadProyectos;
    }

}
