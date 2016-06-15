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
public class RptExportacionDeProcesosProyeccionTazas implements Serializable {

    private Integer t;
    private String tiempoReparacion;
    private String tiempoFalla;
    private String tiempoInstalacion;

    private Integer R;
    private Integer numeroReparaciones;
    private String tiempoRequerido;

    private Integer p;
    private Integer numeroPreventivoActual;
    private Integer numeroPreventivo;

    public Integer getT() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }

    public String getTiempoReparacion() {
        return tiempoReparacion;
    }

    public void setTiempoReparacion(String tiempoReparacion) {
        this.tiempoReparacion = tiempoReparacion;
    }

    public String getTiempoFalla() {
        return tiempoFalla;
    }

    public void setTiempoFalla(String tiempoFalla) {
        this.tiempoFalla = tiempoFalla;
    }

    public String getTiempoInstalacion() {
        return tiempoInstalacion;
    }

    public void setTiempoInstalacion(String tiempoInstalacion) {
        this.tiempoInstalacion = tiempoInstalacion;
    }

    public Integer getR() {
        return R;
    }

    public void setR(Integer R) {
        this.R = R;
    }

    public Integer getNumeroReparaciones() {
        return numeroReparaciones;
    }

    public void setNumeroReparaciones(Integer numeroReparaciones) {
        this.numeroReparaciones = numeroReparaciones;
    }

    public String getTiempoRequerido() {
        return tiempoRequerido;
    }

    public void setTiempoRequerido(String tiempoRequerido) {
        this.tiempoRequerido = tiempoRequerido;
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public Integer getNumeroPreventivoActual() {
        return numeroPreventivoActual;
    }

    public void setNumeroPreventivoActual(Integer numeroPreventivoActual) {
        this.numeroPreventivoActual = numeroPreventivoActual;
    }

    public Integer getNumeroPreventivo() {
        return numeroPreventivo;
    }

    public void setNumeroPreventivo(Integer numeroPreventivo) {
        this.numeroPreventivo = numeroPreventivo;
    }
    
    
    
    

}
