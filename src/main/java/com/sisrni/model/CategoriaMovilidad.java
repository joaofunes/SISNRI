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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "CATEGORIA_MOVILIDAD", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "CategoriaMovilidad.findAll", query = "SELECT c FROM CategoriaMovilidad c")})
public class CategoriaMovilidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CATEGORIA_MOVILIDAD", nullable = false)
    private Integer idCategoriaMovilidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_CATEGORIA", nullable = false, length = 100)
    private String nombreCategoria;
    @OneToMany(mappedBy = "idCategoria")
    private List<Movilidad> movilidadList;

    public CategoriaMovilidad() {
    }

    public CategoriaMovilidad(Integer idCategoriaMovilidad) {
        this.idCategoriaMovilidad = idCategoriaMovilidad;
    }

    public CategoriaMovilidad(Integer idCategoriaMovilidad, String nombreCategoria) {
        this.idCategoriaMovilidad = idCategoriaMovilidad;
        this.nombreCategoria = nombreCategoria;
    }

    public Integer getIdCategoriaMovilidad() {
        return idCategoriaMovilidad;
    }

    public void setIdCategoriaMovilidad(Integer idCategoriaMovilidad) {
        this.idCategoriaMovilidad = idCategoriaMovilidad;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public List<Movilidad> getMovilidadList() {
        return movilidadList;
    }

    public void setMovilidadList(List<Movilidad> movilidadList) {
        this.movilidadList = movilidadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriaMovilidad != null ? idCategoriaMovilidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaMovilidad)) {
            return false;
        }
        CategoriaMovilidad other = (CategoriaMovilidad) object;
        if ((this.idCategoriaMovilidad == null && other.idCategoriaMovilidad != null) || (this.idCategoriaMovilidad != null && !this.idCategoriaMovilidad.equals(other.idCategoriaMovilidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.CategoriaMovilidad[ idCategoriaMovilidad=" + idCategoriaMovilidad + " ]";
    }
    
}
