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
 * @author Joao
 */
@Entity
@Table(name = "MOBILIDAD", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "Mobilidad.findAll", query = "SELECT m FROM Mobilidad m")})
public class Mobilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MOBILIDAD", nullable = false)
    private Integer idMobilidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_MOBILIDAD", nullable = false, length = 100)
    private String nombreMobilidad;

    public Mobilidad() {
    }

    public Mobilidad(Integer idMobilidad) {
        this.idMobilidad = idMobilidad;
    }

    public Mobilidad(Integer idMobilidad, String nombreMobilidad) {
        this.idMobilidad = idMobilidad;
        this.nombreMobilidad = nombreMobilidad;
    }

    public Integer getIdMobilidad() {
        return idMobilidad;
    }

    public void setIdMobilidad(Integer idMobilidad) {
        this.idMobilidad = idMobilidad;
    }

    public String getNombreMobilidad() {
        return nombreMobilidad;
    }

    public void setNombreMobilidad(String nombreMobilidad) {
        this.nombreMobilidad = nombreMobilidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMobilidad != null ? idMobilidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mobilidad)) {
            return false;
        }
        Mobilidad other = (Mobilidad) object;
        if ((this.idMobilidad == null && other.idMobilidad != null) || (this.idMobilidad != null && !this.idMobilidad.equals(other.idMobilidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.Mobilidad[ idMobilidad=" + idMobilidad + " ]";
    }
    
}
