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
@Table(name = "PERSONA_PROPUESTA", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "PersonaPropuesta.findAll", query = "SELECT p FROM PersonaPropuesta p")})
public class PersonaPropuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonaPropuestaPK personaPropuestaPK;
    @JoinColumn(name = "ID_TIPO_PERSONA", referencedColumnName = "ID_TIPO_PERSONA", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoPersona tipoPersona;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "ID_PROPUESTA", referencedColumnName = "ID_PROPUESTA", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PropuestaConvenio propuestaConvenio;

    public PersonaPropuesta() {
    }

    public PersonaPropuesta(PersonaPropuestaPK personaPropuestaPK) {
        this.personaPropuestaPK = personaPropuestaPK;
    }

    public PersonaPropuesta(int idPersona, int idTipoPersona, int idPropuesta) {
        this.personaPropuestaPK = new PersonaPropuestaPK(idPersona, idTipoPersona, idPropuesta);
    }

    public PersonaPropuestaPK getPersonaPropuestaPK() {
        return personaPropuestaPK;
    }

    public void setPersonaPropuestaPK(PersonaPropuestaPK personaPropuestaPK) {
        this.personaPropuestaPK = personaPropuestaPK;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public PropuestaConvenio getPropuestaConvenio() {
        return propuestaConvenio;
    }

    public void setPropuestaConvenio(PropuestaConvenio propuestaConvenio) {
        this.propuestaConvenio = propuestaConvenio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaPropuestaPK != null ? personaPropuestaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaPropuesta)) {
            return false;
        }
        PersonaPropuesta other = (PersonaPropuesta) object;
        if ((this.personaPropuestaPK == null && other.personaPropuestaPK != null) || (this.personaPropuestaPK != null && !this.personaPropuestaPK.equals(other.personaPropuestaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.PersonaPropuesta[ personaPropuestaPK=" + personaPropuestaPK + " ]";
    }
    
}
