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
public class PojoMapaInteractivoBecas implements Serializable {

    public Integer idPais;
    private String codigoPais;
    private String nombrePais;
    private Double montoCooperacion;
    private Integer cantidadBecas;
    //private List<Proyecto> projectList;
    //private List<PojoProyectosByTipo> series;

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public Double getMontoCooperacion() {
        return montoCooperacion;
    }

    public void setMontoCooperacion(Double montoCooperacion) {
        this.montoCooperacion = montoCooperacion;
    }



    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public Integer getCantidadBecas() {
        return cantidadBecas;
    }

    public void setCantidadBecas(Integer cantidadBecas) {
        this.cantidadBecas = cantidadBecas;
    }

}
