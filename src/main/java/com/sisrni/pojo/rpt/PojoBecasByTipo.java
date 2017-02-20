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
public class PojoBecasByTipo implements Serializable {

    private Integer idTipoBeca;
    private String nombreTipoBeca;
    private Integer cantidad;

    public Integer getIdTipoBeca() {
        return idTipoBeca;
    }

    public void setIdTipoBeca(Integer idTipoBeca) {
        this.idTipoBeca = idTipoBeca;
    }

    public String getNombreTipoBeca() {
        return nombreTipoBeca;
    }

    public void setNombreTipoBeca(String nombreTipoBeca) {
        this.nombreTipoBeca = nombreTipoBeca;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

 
}
