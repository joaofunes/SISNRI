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
public class PojoProyectosByTipo implements Serializable {

    private Integer idTipoProyecto;
    private String nombreTipoProyecto;
    private Integer cantidadProyectos;

    public Integer getIdTipoProyecto() {
        return idTipoProyecto;
    }

    public void setIdTipoProyecto(Integer idTipoProyecto) {
        this.idTipoProyecto = idTipoProyecto;
    }

    public String getNombreTipoProyecto() {
        return nombreTipoProyecto;
    }

    public void setNombreTipoProyecto(String nombreTipoProyecto) {
        this.nombreTipoProyecto = nombreTipoProyecto;
    }

    public Integer getCantidadProyectos() {
        return cantidadProyectos;
    }

    public void setCantidadProyectos(Integer cantidadProyectos) {
        this.cantidadProyectos = cantidadProyectos;
    }

}
