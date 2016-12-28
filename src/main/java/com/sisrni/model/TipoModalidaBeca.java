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
@Table(name = "TIPO_MODALIDA_BECA", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "TipoModalidaBeca.findAll", query = "SELECT t FROM TipoModalidaBeca t")})
public class TipoModalidaBeca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_MODALIDAD", nullable = false)
    private Integer idTipoModalidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MODALIDAD", nullable = false, length = 50)
    private String modalidad;
    @OneToMany(mappedBy = "idTipoModalidad")
    private List<Beca> becaList;

    public TipoModalidaBeca() {
    }

    public TipoModalidaBeca(Integer idTipoModalidad) {
        this.idTipoModalidad = idTipoModalidad;
    }

    public TipoModalidaBeca(Integer idTipoModalidad, String modalidad) {
        this.idTipoModalidad = idTipoModalidad;
        this.modalidad = modalidad;
    }

    public Integer getIdTipoModalidad() {
        return idTipoModalidad;
    }

    public void setIdTipoModalidad(Integer idTipoModalidad) {
        this.idTipoModalidad = idTipoModalidad;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public List<Beca> getBecaList() {
        return becaList;
    }

    public void setBecaList(List<Beca> becaList) {
        this.becaList = becaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoModalidad != null ? idTipoModalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoModalidaBeca)) {
            return false;
        }
        TipoModalidaBeca other = (TipoModalidaBeca) object;
        if ((this.idTipoModalidad == null && other.idTipoModalidad != null) || (this.idTipoModalidad != null && !this.idTipoModalidad.equals(other.idTipoModalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.TipoModalidaBeca[ idTipoModalidad=" + idTipoModalidad + " ]";
    }
    
}
