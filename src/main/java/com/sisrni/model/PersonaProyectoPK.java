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
public class PersonaProyectoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERSONA", nullable = false)
    private int idPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROYECTO", nullable = false)
    private int idProyecto;

    public PersonaProyectoPK() {
    }

    public PersonaProyectoPK(int idPersona, int idProyecto) {
        this.idPersona = idPersona;
        this.idProyecto = idProyecto;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPersona;
        hash += (int) idProyecto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaProyectoPK)) {
            return false;
        }
        PersonaProyectoPK other = (PersonaProyectoPK) object;
        if (this.idPersona != other.idPersona) {
            return false;
        }
        if (this.idProyecto != other.idProyecto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.PersonaProyectoPK[ idPersona=" + idPersona + ", idProyecto=" + idProyecto + " ]";
    }
    
}
