/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "PERSONA_MOVILIDAD", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "PersonaMovilidad.findAll", query = "SELECT p FROM PersonaMovilidad p")})
public class PersonaMovilidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonaMovilidadPK personaMovilidadPK;
    @JoinColumn(name = "ID_TIPO_PERSONA", referencedColumnName = "ID_TIPO_PERSONA")
    @ManyToOne
    private TipoPersona idTipoPersona;
    @JoinColumn(name = "ID_MOVILIDAD", referencedColumnName = "ID_MOVILIDAD", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Movilidad movilidad;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;

    public PersonaMovilidad() {
    }

    public PersonaMovilidad(PersonaMovilidadPK personaMovilidadPK) {
        this.personaMovilidadPK = personaMovilidadPK;
    }

    public PersonaMovilidad(int idMovilidad, int idPersona) {
        this.personaMovilidadPK = new PersonaMovilidadPK(idMovilidad, idPersona);
    }

    public PersonaMovilidadPK getPersonaMovilidadPK() {
        return personaMovilidadPK;
    }

    public void setPersonaMovilidadPK(PersonaMovilidadPK personaMovilidadPK) {
        this.personaMovilidadPK = personaMovilidadPK;
    }

    public TipoPersona getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(TipoPersona idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    public Movilidad getMovilidad() {
        return movilidad;
    }

    public void setMovilidad(Movilidad movilidad) {
        this.movilidad = movilidad;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaMovilidadPK != null ? personaMovilidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaMovilidad)) {
            return false;
        }
        PersonaMovilidad other = (PersonaMovilidad) object;
        if ((this.personaMovilidadPK == null && other.personaMovilidadPK != null) || (this.personaMovilidadPK != null && !this.personaMovilidadPK.equals(other.personaMovilidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.PersonaMovilidad[ personaMovilidadPK=" + personaMovilidadPK + " ]";
    }
    
}
