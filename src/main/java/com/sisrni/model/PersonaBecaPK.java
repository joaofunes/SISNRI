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
public class PersonaBecaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERSONA", nullable = false)
    private int idPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_BECA", nullable = false)
    private int idBeca;

    public PersonaBecaPK() {
    }

    public PersonaBecaPK(int idPersona, int idBeca) {
        this.idPersona = idPersona;
        this.idBeca = idBeca;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdBeca() {
        return idBeca;
    }

    public void setIdBeca(int idBeca) {
        this.idBeca = idBeca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPersona;
        hash += (int) idBeca;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaBecaPK)) {
            return false;
        }
        PersonaBecaPK other = (PersonaBecaPK) object;
        if (this.idPersona != other.idPersona) {
            return false;
        }
        if (this.idBeca != other.idBeca) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.PersonaBecaPK[ idPersona=" + idPersona + ", idBeca=" + idBeca + " ]";
    }
    
}
