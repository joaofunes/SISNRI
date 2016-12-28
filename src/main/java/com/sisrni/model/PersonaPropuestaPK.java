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
public class PersonaPropuestaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERSONA", nullable = false)
    private int idPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_PERSONA", nullable = false)
    private int idTipoPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROPUESTA", nullable = false)
    private int idPropuesta;

    public PersonaPropuestaPK() {
    }

    public PersonaPropuestaPK(int idPersona, int idTipoPersona, int idPropuesta) {
        this.idPersona = idPersona;
        this.idTipoPersona = idTipoPersona;
        this.idPropuesta = idPropuesta;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(int idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    public int getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(int idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPersona;
        hash += (int) idTipoPersona;
        hash += (int) idPropuesta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaPropuestaPK)) {
            return false;
        }
        PersonaPropuestaPK other = (PersonaPropuestaPK) object;
        if (this.idPersona != other.idPersona) {
            return false;
        }
        if (this.idTipoPersona != other.idTipoPersona) {
            return false;
        }
        if (this.idPropuesta != other.idPropuesta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.PersonaPropuestaPK[ idPersona=" + idPersona + ", idTipoPersona=" + idTipoPersona + ", idPropuesta=" + idPropuesta + " ]";
    }
    
}
