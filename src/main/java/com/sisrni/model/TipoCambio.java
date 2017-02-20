/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "TIPO_CAMBIO", catalog = "sisrni", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODIGO_DIVISA"}),
    @UniqueConstraint(columnNames = {"NOMBRE_DIVISA"})})
@NamedQueries({
    @NamedQuery(name = "TipoCambio.findAll", query = "SELECT t FROM TipoCambio t")})
public class TipoCambio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_CAMBIO", nullable = false)
    private Integer idTipoCambio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CODIGO_DIVISA", nullable = false, length = 3)
    private String codigoDivisa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_DIVISA", nullable = false, length = 100)
    private String nombreDivisa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOLARES_POR_UNIDAD", nullable = false, precision = 10, scale = 2)
    private BigDecimal dolaresPorUnidad;

    public TipoCambio() {
    }

    public TipoCambio(Integer idTipoCambio) {
        this.idTipoCambio = idTipoCambio;
    }

    public TipoCambio(Integer idTipoCambio, String codigoDivisa, String nombreDivisa, BigDecimal dolaresPorUnidad) {
        this.idTipoCambio = idTipoCambio;
        this.codigoDivisa = codigoDivisa;
        this.nombreDivisa = nombreDivisa;
        this.dolaresPorUnidad = dolaresPorUnidad;
    }

    public Integer getIdTipoCambio() {
        return idTipoCambio;
    }

    public void setIdTipoCambio(Integer idTipoCambio) {
        this.idTipoCambio = idTipoCambio;
    }

    public String getCodigoDivisa() {
        return codigoDivisa;
    }

    public void setCodigoDivisa(String codigoDivisa) {
        this.codigoDivisa = codigoDivisa;
    }

    public String getNombreDivisa() {
        return nombreDivisa;
    }

    public void setNombreDivisa(String nombreDivisa) {
        this.nombreDivisa = nombreDivisa;
    }

    public BigDecimal getDolaresPorUnidad() {
        return dolaresPorUnidad;
    }

    public void setDolaresPorUnidad(BigDecimal dolaresPorUnidad) {
        this.dolaresPorUnidad = dolaresPorUnidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoCambio != null ? idTipoCambio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCambio)) {
            return false;
        }
        TipoCambio other = (TipoCambio) object;
        if ((this.idTipoCambio == null && other.idTipoCambio != null) || (this.idTipoCambio != null && !this.idTipoCambio.equals(other.idTipoCambio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.TipoCambio[ idTipoCambio=" + idTipoCambio + " ]";
    }
    
}
