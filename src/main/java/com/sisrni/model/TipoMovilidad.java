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
@Table(name = "TIPO_MOVILIDAD", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "TipoMovilidad.findAll", query = "SELECT t FROM TipoMovilidad t")})
public class TipoMovilidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_MOVILIDAD", nullable = false)
    private Integer idTipoMovilidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NOMBRE_TIPO_MOVILIDAD", nullable = false, length = 25)
    private String nombreTipoMovilidad;
    @OneToMany(mappedBy = "idTipoMovilidad")
    private List<Movilidad> movilidadList;

    public TipoMovilidad() {
    }

    public TipoMovilidad(Integer idTipoMovilidad) {
        this.idTipoMovilidad = idTipoMovilidad;
    }

    public TipoMovilidad(Integer idTipoMovilidad, String nombreTipoMovilidad) {
        this.idTipoMovilidad = idTipoMovilidad;
        this.nombreTipoMovilidad = nombreTipoMovilidad;
    }

    public Integer getIdTipoMovilidad() {
        return idTipoMovilidad;
    }

    public void setIdTipoMovilidad(Integer idTipoMovilidad) {
        this.idTipoMovilidad = idTipoMovilidad;
    }

    public String getNombreTipoMovilidad() {
        return nombreTipoMovilidad;
    }

    public void setNombreTipoMovilidad(String nombreTipoMovilidad) {
        this.nombreTipoMovilidad = nombreTipoMovilidad;
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
        hash += (idTipoMovilidad != null ? idTipoMovilidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMovilidad)) {
            return false;
        }
        TipoMovilidad other = (TipoMovilidad) object;
        if ((this.idTipoMovilidad == null && other.idTipoMovilidad != null) || (this.idTipoMovilidad != null && !this.idTipoMovilidad.equals(other.idTipoMovilidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.TipoMovilidad[ idTipoMovilidad=" + idTipoMovilidad + " ]";
    }
    
}
