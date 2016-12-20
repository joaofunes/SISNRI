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
@Table(name = "PERSONA_BECA", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "PersonaBeca.findAll", query = "SELECT p FROM PersonaBeca p")})
public class PersonaBeca implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonaBecaPK personaBecaPK;
    @JoinColumn(name = "ID_BECA", referencedColumnName = "ID_BECA", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Beca beca;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "ID_TIPO_PERSONA", referencedColumnName = "ID_TIPO_PERSONA", nullable = false)
    @ManyToOne(optional = false)
    private TipoPersona idTipoPersona;

    public PersonaBeca() {
    }

    public PersonaBeca(PersonaBecaPK personaBecaPK) {
        this.personaBecaPK = personaBecaPK;
    }

    public PersonaBeca(int idPersona, int idBeca) {
        this.personaBecaPK = new PersonaBecaPK(idPersona, idBeca);
    }

    public PersonaBecaPK getPersonaBecaPK() {
        return personaBecaPK;
    }

    public void setPersonaBecaPK(PersonaBecaPK personaBecaPK) {
        this.personaBecaPK = personaBecaPK;
    }

    public Beca getBeca() {
        return beca;
    }

    public void setBeca(Beca beca) {
        this.beca = beca;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TipoPersona getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(TipoPersona idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaBecaPK != null ? personaBecaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaBeca)) {
            return false;
        }
        PersonaBeca other = (PersonaBeca) object;
        if ((this.personaBecaPK == null && other.personaBecaPK != null) || (this.personaBecaPK != null && !this.personaBecaPK.equals(other.personaBecaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.PersonaBeca[ personaBecaPK=" + personaBecaPK + " ]";
    }
    
}
