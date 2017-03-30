/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.pojo.rpt;

import java.io.Serializable;

/**
 *
 * @author Cortez Pojo nuevo
 */
public class PojoMapaMovilidad implements Serializable {

    private Integer idPais;
    private String codigoPais;
    private String nombrePais;
    private Double montoMovilidades;
    private Integer cantidadMovilidades;

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

    public Double getMontoMovilidades() {
        return montoMovilidades;
    }

    public void setMontoMovilidades(Double montoMovilidades) {
        this.montoMovilidades = montoMovilidades;
    }

    public Integer getCantidadMovilidades() {
        return cantidadMovilidades;
    }

    public void setCantidadMovilidades(Integer cantidadMovilidades) {
        this.cantidadMovilidades = cantidadMovilidades;
    }

}
