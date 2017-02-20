/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "TIPO_PERSONA", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "TipoPersona.findAll", query = "SELECT t FROM TipoPersona t")})
public class TipoPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_PERSONA", nullable = false)
    private Integer idTipoPersona;
    @Size(max = 100)
    @Column(name = "NOMBRE_TIPO_PERSONA", length = 100)
    private String nombreTipoPersona;
    @Size(max = 100)
    @Column(name = "DESCRIPCION", length = 100)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoPersona")
    private List<PersonaPropuesta> personaPropuestaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPersona")
    private List<PersonaProyecto> personaProyectoList;
    @OneToMany(mappedBy = "idTipoPersona")
    private List<PersonaMovilidad> personaMovilidadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPersona")
    private List<PersonaBeca> personaBecaList;

    public TipoPersona() {
    }

    public TipoPersona(Integer idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    public Integer getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(Integer idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    public String getNombreTipoPersona() {
        return nombreTipoPersona;
    }

    public void setNombreTipoPersona(String nombreTipoPersona) {
        this.nombreTipoPersona = nombreTipoPersona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<PersonaPropuesta> getPersonaPropuestaList() {
        return personaPropuestaList;
    }

    public void setPersonaPropuestaList(List<PersonaPropuesta> personaPropuestaList) {
        this.personaPropuestaList = personaPropuestaList;
    }

    public List<PersonaProyecto> getPersonaProyectoList() {
        return personaProyectoList;
    }

    public void setPersonaProyectoList(List<PersonaProyecto> personaProyectoList) {
        this.personaProyectoList = personaProyectoList;
    }

    public List<PersonaMovilidad> getPersonaMovilidadList() {
        return personaMovilidadList;
    }

    public void setPersonaMovilidadList(List<PersonaMovilidad> personaMovilidadList) {
        this.personaMovilidadList = personaMovilidadList;
    }

    public List<PersonaBeca> getPersonaBecaList() {
        return personaBecaList;
    }

    public void setPersonaBecaList(List<PersonaBeca> personaBecaList) {
        this.personaBecaList = personaBecaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPersona != null ? idTipoPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPersona)) {
            return false;
        }
        TipoPersona other = (TipoPersona) object;
        if ((this.idTipoPersona == null && other.idTipoPersona != null) || (this.idTipoPersona != null && !this.idTipoPersona.equals(other.idTipoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.TipoPersona[ idTipoPersona=" + idTipoPersona + " ]";
    }
    
}
