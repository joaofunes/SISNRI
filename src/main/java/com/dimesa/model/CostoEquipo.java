/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author HDEZ
 */
@Entity
@Table(name = "costo_equipo", catalog = "dimesa", schema = "")
@NamedQueries({
    @NamedQuery(name = "CostoEquipo.findAll", query = "SELECT c FROM CostoEquipo c")})
public class CostoEquipo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "Id_costo_equipo", nullable = true)
    private Integer idcostoequipo;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costo", precision = 12)
    private Float costo;
    @OneToMany(mappedBy = "idcostoequipo")
    private Set<Evento> eventoSet;

    public CostoEquipo() {
    }

    public CostoEquipo(Integer idcostoequipo) {
        this.idcostoequipo = idcostoequipo;
    }

    public Integer getIdcostoequipo() {
        return idcostoequipo;
    }

    public void setIdcostoequipo(Integer idcostoequipo) {
        this.idcostoequipo = idcostoequipo;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public Set<Evento> getEventoSet() {
        return eventoSet;
    }

    public void setEventoSet(Set<Evento> eventoSet) {
        this.eventoSet = eventoSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcostoequipo != null ? idcostoequipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CostoEquipo)) {
            return false;
        }
        CostoEquipo other = (CostoEquipo) object;
        if ((this.idcostoequipo == null && other.idcostoequipo != null) || (this.idcostoequipo != null && !this.idcostoequipo.equals(other.idcostoequipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dimesa.model.CostoEquipo[ idcostoequipo=" + idcostoequipo + " ]";
    }

}
