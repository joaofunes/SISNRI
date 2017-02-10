/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.pojo.rpt;

import com.sisrni.model.Beca;
import java.util.List;

import java.io.Serializable;

/**
 *
 * @author Cortez
 */
public class PojoMapaInteractivoBecas implements Serializable {

    private Integer idPais;
    private String codigoPais;
    private String nombrePais;
    private Double montoCooperacion;
    private Integer cantidadBecas;
    private List<Beca> becastList;
    private List<PojoBecasByTipo> series;

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

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

    public Integer getCantidadBecas() {
        return cantidadBecas;
    }

    public void setCantidadBecas(Integer cantidadBecas) {
        this.cantidadBecas = cantidadBecas;
    }

    public List<Beca> getBecastList() {
        return becastList;
    }

    public void setBecastList(List<Beca> becastList) {
        this.becastList = becastList;
    }

    public List<PojoBecasByTipo> getSeries() {
        return series;
    }

    public void setSeries(List<PojoBecasByTipo> series) {
        this.series = series;
    }
}
