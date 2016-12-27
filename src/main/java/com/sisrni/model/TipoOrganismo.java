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
import javax.validation.constraints.Size;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "TIPO_ORGANISMO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "TipoOrganismo.findAll", query = "SELECT t FROM TipoOrganismo t")})
public class TipoOrganismo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_ORGANISMO", nullable = false)
    private Integer idTipoOrganismo;
    @Size(max = 60)
    @Column(name = "NOMBRE_TIPO", length = 60)
    private String nombreTipo;
    @Size(max = 100)
    @Column(name = "DESCRIPCION_TIPO", length = 100)
    private String descripcionTipo;
    @OneToMany(mappedBy = "idTipoOrganismo")
    private List<Organismo> organismoList;

    public TipoOrganismo() {
    }

    public TipoOrganismo(Integer idTipoOrganismo) {
        this.idTipoOrganismo = idTipoOrganismo;
    }

    public Integer getIdTipoOrganismo() {
        return idTipoOrganismo;
    }

    public void setIdTipoOrganismo(Integer idTipoOrganismo) {
        this.idTipoOrganismo = idTipoOrganismo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

    public List<Organismo> getOrganismoList() {
        return organismoList;
    }

    public void setOrganismoList(List<Organismo> organismoList) {
        this.organismoList = organismoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoOrganismo != null ? idTipoOrganismo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoOrganismo)) {
            return false;
        }
        TipoOrganismo other = (TipoOrganismo) object;
        if ((this.idTipoOrganismo == null && other.idTipoOrganismo != null) || (this.idTipoOrganismo != null && !this.idTipoOrganismo.equals(other.idTipoOrganismo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.TipoOrganismo[ idTipoOrganismo=" + idTipoOrganismo + " ]";
    }
    
}
