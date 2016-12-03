/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.pojo.rpt;

import com.sisrni.model.Proyecto;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Cortez
 */
public class PojoMapaInteractivo implements Serializable {

    public Integer idPais;
    private String codigoPais;
    private String nombrePais;
    private Double montoCooperacion;
    private Integer cantidadProyectos;
    private List<Proyecto> projectList;
    private List<PojoProyectosByTipo> series;

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

    public Integer getCantidadProyectos() {
        return cantidadProyectos;
    }

    public void setCantidadProyectos(Integer cantidadProyectos) {
        this.cantidadProyectos = cantidadProyectos;
    }

    public List<Proyecto> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Proyecto> projectList) {
        this.projectList = projectList;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public List<PojoProyectosByTipo> getSeries() {
        return series;
    }

    public void setSeries(List<PojoProyectosByTipo> series) {
        this.series = series;
    }

}
