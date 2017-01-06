/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "AREA_CONOCIMIENTO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "AreaConocimiento.findAll", query = "SELECT a FROM AreaConocimiento a")})
public class AreaConocimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_AREA_CONOCIMIENTO", nullable = false)
    private Integer idAreaConocimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_AREA", nullable = false, length = 100)
    private String nombreArea;
    @ManyToMany(mappedBy = "areaConocimientoList")
    private List<Proyecto> proyectoList;

    public AreaConocimiento() {
    }

    public AreaConocimiento(Integer idAreaConocimiento) {
        this.idAreaConocimiento = idAreaConocimiento;
    }

    public AreaConocimiento(Integer idAreaConocimiento, String nombreArea) {
        this.idAreaConocimiento = idAreaConocimiento;
        this.nombreArea = nombreArea;
    }

    public Integer getIdAreaConocimiento() {
        return idAreaConocimiento;
    }

    public void setIdAreaConocimiento(Integer idAreaConocimiento) {
        this.idAreaConocimiento = idAreaConocimiento;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAreaConocimiento != null ? idAreaConocimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreaConocimiento)) {
            return false;
        }
        AreaConocimiento other = (AreaConocimiento) object;
        if ((this.idAreaConocimiento == null && other.idAreaConocimiento != null) || (this.idAreaConocimiento != null && !this.idAreaConocimiento.equals(other.idAreaConocimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.AreaConocimiento[ idAreaConocimiento=" + idAreaConocimiento + " ]";
    }
    
}
