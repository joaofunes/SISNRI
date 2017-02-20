/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "BITACORA_ESTADO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "BitacoraEstado.findAll", query = "SELECT b FROM BitacoraEstado b")})
public class BitacoraEstado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_BITACORA_ESTADO", nullable = false)
    private Integer idBitacoraEstado;
    @Column(name = "ID_PROPUESTA")
    private Integer idPropuesta;
    @Column(name = "ID_ESTADO")
    private Integer idEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_DE_CAMBIO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaDeCambio;

    public BitacoraEstado() {
    }

    public BitacoraEstado(Integer idBitacoraEstado) {
        this.idBitacoraEstado = idBitacoraEstado;
    }

    public BitacoraEstado(Integer idBitacoraEstado, Date fechaDeCambio) {
        this.idBitacoraEstado = idBitacoraEstado;
        this.fechaDeCambio = fechaDeCambio;
    }

    public Integer getIdBitacoraEstado() {
        return idBitacoraEstado;
    }

    public void setIdBitacoraEstado(Integer idBitacoraEstado) {
        this.idBitacoraEstado = idBitacoraEstado;
    }

    public Integer getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(Integer idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Date getFechaDeCambio() {
        return fechaDeCambio;
    }

    public void setFechaDeCambio(Date fechaDeCambio) {
        this.fechaDeCambio = fechaDeCambio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBitacoraEstado != null ? idBitacoraEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BitacoraEstado)) {
            return false;
        }
        BitacoraEstado other = (BitacoraEstado) object;
        if ((this.idBitacoraEstado == null && other.idBitacoraEstado != null) || (this.idBitacoraEstado != null && !this.idBitacoraEstado.equals(other.idBitacoraEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.BitacoraEstado[ idBitacoraEstado=" + idBitacoraEstado + " ]";
    }
    
}
