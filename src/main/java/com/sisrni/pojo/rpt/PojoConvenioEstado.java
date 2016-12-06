/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.pojo.rpt;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Joao
 */
public class PojoConvenioEstado implements Serializable{
    private static final long serialVersionUID = 1113799434508676095L;
    
    private Integer id_propuesta;
    private Integer id_estado;
    private Integer tipo_estado;
    private String nombre_propuesta;
    private String nombre_estado;
    private Date vigencia;
    private Date fecha_cambio_estado;

    public Integer getId_propuesta() {
        return id_propuesta;
    }

    public void setId_propuesta(Integer id_propuesta) {
        this.id_propuesta = id_propuesta;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getTipo_estado() {
        return tipo_estado;
    }

    public void setTipo_estado(Integer tipo_estado) {
        this.tipo_estado = tipo_estado;
    }

    public String getNombre_propuesta() {
        return nombre_propuesta;
    }

    public void setNombre_propuesta(String nombre_propuesta) {
        this.nombre_propuesta = nombre_propuesta;
    }

    public String getNombre_estado() {
        return nombre_estado;
    }

    public void setNombre_estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public Date getFecha_cambio_estado() {
        return fecha_cambio_estado;
    }

    public void setFecha_cambio_estado(Date fecha_cambio_estado) {
        this.fecha_cambio_estado = fecha_cambio_estado;
    }

    
}
