/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lillian
 */
@Embeddable
public class FacultadProyectoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_FACULTAD_UNIDAD", nullable = false)
    private int idFacultadUnidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROYECTO", nullable = false)
    private int idProyecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_FACULTAD", nullable = false)
    private int idTipoFacultad;

    public FacultadProyectoPK() {
    }

    public FacultadProyectoPK(int idFacultadUnidad, int idProyecto, int idTipoFacultad) {
        this.idFacultadUnidad = idFacultadUnidad;
        this.idProyecto = idProyecto;
        this.idTipoFacultad = idTipoFacultad;
    }

    public int getIdFacultadUnidad() {
        return idFacultadUnidad;
    }

    public void setIdFacultadUnidad(int idFacultadUnidad) {
        this.idFacultadUnidad = idFacultadUnidad;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdTipoFacultad() {
        return idTipoFacultad;
    }

    public void setIdTipoFacultad(int idTipoFacultad) {
        this.idTipoFacultad = idTipoFacultad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idFacultadUnidad;
        hash += (int) idProyecto;
        hash += (int) idTipoFacultad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacultadProyectoPK)) {
            return false;
        }
        FacultadProyectoPK other = (FacultadProyectoPK) object;
        if (this.idFacultadUnidad != other.idFacultadUnidad) {
            return false;
        }
        if (this.idProyecto != other.idProyecto) {
            return false;
        }
        if (this.idTipoFacultad != other.idTipoFacultad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.FacultadProyectoPK[ idFacultadUnidad=" + idFacultadUnidad + ", idProyecto=" + idProyecto + ", idTipoFacultad=" + idTipoFacultad + " ]";
    }
    
}
