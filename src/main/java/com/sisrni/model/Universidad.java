/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Joao
 */
@Entity
@Table(name = "universidad", catalog = "srnibd", schema = "")
@NamedQueries({
    @NamedQuery(name = "Universidad.findAll", query = "SELECT u FROM Universidad u")})
public class Universidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UNIVERSIDAD", nullable = false)
    private Integer idUniversidad;
    @Size(max = 60)
    @Column(name = "NOMBRE_UNIVERSIDAD", length = 60)
    private String nombreUniversidad;
    @OneToMany(mappedBy = "idUniversidad")
    private List<Facultad> facultadList;
    @OneToMany(mappedBy = "idUniversidad")
    private List<Propuesta> propuestaList;

    public Universidad() {
    }

    public Universidad(Integer idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public Integer getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(Integer idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public String getNombreUniversidad() {
        return nombreUniversidad;
    }

    public void setNombreUniversidad(String nombreUniversidad) {
        this.nombreUniversidad = nombreUniversidad;
    }

    public List<Facultad> getFacultadList() {
        return facultadList;
    }

    public void setFacultadList(List<Facultad> facultadList) {
        this.facultadList = facultadList;
    }

    public List<Propuesta> getPropuestaList() {
        return propuestaList;
    }

    public void setPropuestaList(List<Propuesta> propuestaList) {
        this.propuestaList = propuestaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUniversidad != null ? idUniversidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Universidad)) {
            return false;
        }
        Universidad other = (Universidad) object;
        if ((this.idUniversidad == null && other.idUniversidad != null) || (this.idUniversidad != null && !this.idUniversidad.equals(other.idUniversidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.Universidad[ idUniversidad=" + idUniversidad + " ]";
    }
    
}
