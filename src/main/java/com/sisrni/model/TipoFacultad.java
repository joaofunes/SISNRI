/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "TIPO_FACULTAD", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "TipoFacultad.findAll", query = "SELECT t FROM TipoFacultad t")})
public class TipoFacultad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_FACULTAD", nullable = false)
    private Integer idTipoFacultad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_TIPO_FACULTAD", nullable = false, length = 100)
    private String nombreTipoFacultad;
    @Size(max = 100)
    @Column(name = "DESCRIPCION", length = 100)
    private String descripcion;

    public TipoFacultad() {
    }

    public TipoFacultad(Integer idTipoFacultad) {
        this.idTipoFacultad = idTipoFacultad;
    }

    public TipoFacultad(Integer idTipoFacultad, String nombreTipoFacultad) {
        this.idTipoFacultad = idTipoFacultad;
        this.nombreTipoFacultad = nombreTipoFacultad;
    }

    public Integer getIdTipoFacultad() {
        return idTipoFacultad;
    }

    public void setIdTipoFacultad(Integer idTipoFacultad) {
        this.idTipoFacultad = idTipoFacultad;
    }

    public String getNombreTipoFacultad() {
        return nombreTipoFacultad;
    }

    public void setNombreTipoFacultad(String nombreTipoFacultad) {
        this.nombreTipoFacultad = nombreTipoFacultad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoFacultad != null ? idTipoFacultad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoFacultad)) {
            return false;
        }
        TipoFacultad other = (TipoFacultad) object;
        if ((this.idTipoFacultad == null && other.idTipoFacultad != null) || (this.idTipoFacultad != null && !this.idTipoFacultad.equals(other.idTipoFacultad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.TipoFacultad[ idTipoFacultad=" + idTipoFacultad + " ]";
    }
    
}
