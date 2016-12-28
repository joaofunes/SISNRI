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
 * @author Cortez
 */
@Embeddable
public class PropuestaEstadoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROPUESTA", nullable = false)
    private int idPropuesta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTADO", nullable = false)
    private int idEstado;

    public PropuestaEstadoPK() {
    }

    public PropuestaEstadoPK(int idPropuesta, int idEstado) {
        this.idPropuesta = idPropuesta;
        this.idEstado = idEstado;
    }

    public int getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(int idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPropuesta;
        hash += (int) idEstado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropuestaEstadoPK)) {
            return false;
        }
        PropuestaEstadoPK other = (PropuestaEstadoPK) object;
        if (this.idPropuesta != other.idPropuesta) {
            return false;
        }
        if (this.idEstado != other.idEstado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.PropuestaEstadoPK[ idPropuesta=" + idPropuesta + ", idEstado=" + idEstado + " ]";
    }
    
}
