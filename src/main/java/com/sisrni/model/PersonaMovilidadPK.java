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
public class PersonaMovilidadPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MOVILIDAD", nullable = false)
    private int idMovilidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERSONA", nullable = false)
    private int idPersona;

    public PersonaMovilidadPK() {
    }

    public PersonaMovilidadPK(int idMovilidad, int idPersona) {
        this.idMovilidad = idMovilidad;
        this.idPersona = idPersona;
    }

    public int getIdMovilidad() {
        return idMovilidad;
    }

    public void setIdMovilidad(int idMovilidad) {
        this.idMovilidad = idMovilidad;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMovilidad;
        hash += (int) idPersona;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaMovilidadPK)) {
            return false;
        }
        PersonaMovilidadPK other = (PersonaMovilidadPK) object;
        if (this.idMovilidad != other.idMovilidad) {
            return false;
        }
        if (this.idPersona != other.idPersona) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.PersonaMovilidadPK[ idMovilidad=" + idMovilidad + ", idPersona=" + idPersona + " ]";
    }
    
}
