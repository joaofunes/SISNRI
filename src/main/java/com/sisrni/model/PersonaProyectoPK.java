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
 * @author Joao
 */
@Embeddable
public class PersonaProyectoPK implements Serializable {

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
    @Column(name = "ID_PROYECTO_GENERICO", nullable = false)
    private int idProyectoGenerico;

    public PersonaProyectoPK() {
    }

    public PersonaProyectoPK(int idPersona, int idTipoPersona, int idProyectoGenerico) {
        this.idPersona = idPersona;
        this.idTipoPersona = idTipoPersona;
        this.idProyectoGenerico = idProyectoGenerico;
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

    public int getIdProyectoGenerico() {
        return idProyectoGenerico;
    }

    public void setIdProyectoGenerico(int idProyectoGenerico) {
        this.idProyectoGenerico = idProyectoGenerico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPersona;
        hash += (int) idTipoPersona;
        hash += (int) idProyectoGenerico;
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
        if (this.idTipoPersona != other.idTipoPersona) {
            return false;
        }
        if (this.idProyectoGenerico != other.idProyectoGenerico) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.PersonaProyectoPK[ idPersona=" + idPersona + ", idTipoPersona=" + idTipoPersona + ", idProyectoGenerico=" + idProyectoGenerico + " ]";
    }
    
}
