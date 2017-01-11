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
@Table(name = "ETAPA_MOVILIDAD", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "EtapaMovilidad.findAll", query = "SELECT e FROM EtapaMovilidad e")})
public class EtapaMovilidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ETAPA", nullable = false)
    private Integer idEtapa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_ETAPA", nullable = false, length = 100)
    private String nombreEtapa;
    @Size(max = 100)
    @Column(name = "DESCRIPCION", length = 100)
    private String descripcion;
    @OneToMany(mappedBy = "idEtapaMovilidad")
    private List<Movilidad> movilidadList;

    public EtapaMovilidad() {
    }

    public EtapaMovilidad(Integer idEtapa) {
        this.idEtapa = idEtapa;
    }

    public EtapaMovilidad(Integer idEtapa, String nombreEtapa) {
        this.idEtapa = idEtapa;
        this.nombreEtapa = nombreEtapa;
    }

    public Integer getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Integer idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getNombreEtapa() {
        return nombreEtapa;
    }

    public void setNombreEtapa(String nombreEtapa) {
        this.nombreEtapa = nombreEtapa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idEtapa != null ? idEtapa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EtapaMovilidad)) {
            return false;
        }
        EtapaMovilidad other = (EtapaMovilidad) object;
        if ((this.idEtapa == null && other.idEtapa != null) || (this.idEtapa != null && !this.idEtapa.equals(other.idEtapa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.EtapaMovilidad[ idEtapa=" + idEtapa + " ]";
    }
    
}
