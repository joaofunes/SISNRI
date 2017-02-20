/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.pojo.rpt;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class PojoMovilidadAdm implements Serializable {

    private Integer idMovilidad;
    private String nombrePrograma;
    private String nombrePersona;
    private String apellidoPersona;
    private String nombreTipoMovilidad;
    private String paisOrigen;
    private String paisDestino;
    private Date fechaEntrada;
    private Date fechaSalida;
    private String nombreEtapa;
    
    
    

    public Integer getIdMovilidad() {
        return idMovilidad;
    }

    public void setIdMovilidad(Integer idMovilidad) {
        this.idMovilidad = idMovilidad;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    public String getNombreTipoMovilidad() {
        return nombreTipoMovilidad;
    }

    public void setNombreTipoMovilidad(String nombreTipoMovilidad) {
        this.nombreTipoMovilidad = nombreTipoMovilidad;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    //Se formatea la fecha de entrada de la movilidad
    public String getFechaEntrada() {
        //return fechaEntrada;
        return new SimpleDateFormat("dd/MM/yyyy").format(fechaEntrada);
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getNombreEtapa() {
        return nombreEtapa;
    }

    public void setNombreEtapa(String nombreEtapa) {
        this.nombreEtapa = nombreEtapa;
    }
    
    
}
