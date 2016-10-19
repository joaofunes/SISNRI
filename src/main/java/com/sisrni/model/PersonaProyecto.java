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
 * @author Joao
 */
@Entity
@Table(name = "PERSONA_PROYECTO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "PersonaProyecto.findAll", query = "SELECT p FROM PersonaProyecto p")})
public class PersonaProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonaProyectoPK personaProyectoPK;
    @JoinColumn(name = "ID_PROYECTO_GENERICO", referencedColumnName = "ID_PROYECTO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ProyectoGenerico proyectoGenerico;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "ID_TIPO_PERSONA", referencedColumnName = "ID_TIPO_PERSONA", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoPersona tipoPersona;

    public PersonaProyecto() {
    }

    public PersonaProyecto(PersonaProyectoPK personaProyectoPK) {
        this.personaProyectoPK = personaProyectoPK;
    }

    public PersonaProyecto(int idPersona, int idTipoPersona, int idProyectoGenerico) {
        this.personaProyectoPK = new PersonaProyectoPK(idPersona, idTipoPersona, idProyectoGenerico);
    }

    public PersonaProyectoPK getPersonaProyectoPK() {
        return personaProyectoPK;
    }

    public void setPersonaProyectoPK(PersonaProyectoPK personaProyectoPK) {
        this.personaProyectoPK = personaProyectoPK;
    }

    public ProyectoGenerico getProyectoGenerico() {
        return proyectoGenerico;
    }

    public void setProyectoGenerico(ProyectoGenerico proyectoGenerico) {
        this.proyectoGenerico = proyectoGenerico;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaProyectoPK != null ? personaProyectoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaProyecto)) {
            return false;
        }
        PersonaProyecto other = (PersonaProyecto) object;
        if ((this.personaProyectoPK == null && other.personaProyectoPK != null) || (this.personaProyectoPK != null && !this.personaProyectoPK.equals(other.personaProyectoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.PersonaProyecto[ personaProyectoPK=" + personaProyectoPK + " ]";
    }
    
}
