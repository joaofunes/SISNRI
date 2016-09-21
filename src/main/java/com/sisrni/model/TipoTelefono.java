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

/**
 *
 * @author Joao
 */
@Entity
@Table(name = "TIPO_TELEFONO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "TipoTelefono.findAll", query = "SELECT t FROM TipoTelefono t")})
public class TipoTelefono implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_TELEFONO", nullable = false)
    private Integer idTipoTelefono;
    @Column(name = "NOMBRE")
    private Character nombre;
    @OneToMany(mappedBy = "idTipoTelefono")
    private List<Telefono> telefonoList;

    public TipoTelefono() {
    }

    public TipoTelefono(Integer idTipoTelefono) {
        this.idTipoTelefono = idTipoTelefono;
    }

    public Integer getIdTipoTelefono() {
        return idTipoTelefono;
    }

    public void setIdTipoTelefono(Integer idTipoTelefono) {
        this.idTipoTelefono = idTipoTelefono;
    }

    public Character getNombre() {
        return nombre;
    }

    public void setNombre(Character nombre) {
        this.nombre = nombre;
    }

    public List<Telefono> getTelefonoList() {
        return telefonoList;
    }

    public void setTelefonoList(List<Telefono> telefonoList) {
        this.telefonoList = telefonoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoTelefono != null ? idTipoTelefono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTelefono)) {
            return false;
        }
        TipoTelefono other = (TipoTelefono) object;
        if ((this.idTipoTelefono == null && other.idTipoTelefono != null) || (this.idTipoTelefono != null && !this.idTipoTelefono.equals(other.idTipoTelefono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.TipoTelefono[ idTipoTelefono=" + idTipoTelefono + " ]";
    }
    
}
