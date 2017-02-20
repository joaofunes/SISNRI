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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "TIPO_BECA", catalog = "sisrni", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE_TIPO_BECA"})})
@NamedQueries({
    @NamedQuery(name = "TipoBeca.findAll", query = "SELECT t FROM TipoBeca t")})
public class TipoBeca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_BECA", nullable = false)
    private Integer idTipoBeca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE_TIPO_BECA", nullable = false, length = 200)
    private String nombreTipoBeca;
    @OneToMany(mappedBy = "idTipoBeca")
    private List<Beca> becaList;

    public TipoBeca() {
    }

    public TipoBeca(Integer idTipoBeca) {
        this.idTipoBeca = idTipoBeca;
    }

    public TipoBeca(Integer idTipoBeca, String nombreTipoBeca) {
        this.idTipoBeca = idTipoBeca;
        this.nombreTipoBeca = nombreTipoBeca;
    }

    public Integer getIdTipoBeca() {
        return idTipoBeca;
    }

    public void setIdTipoBeca(Integer idTipoBeca) {
        this.idTipoBeca = idTipoBeca;
    }

    public String getNombreTipoBeca() {
        return nombreTipoBeca;
    }

    public void setNombreTipoBeca(String nombreTipoBeca) {
        this.nombreTipoBeca = nombreTipoBeca;
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
        hash += (idTipoBeca != null ? idTipoBeca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoBeca)) {
            return false;
        }
        TipoBeca other = (TipoBeca) object;
        if ((this.idTipoBeca == null && other.idTipoBeca != null) || (this.idTipoBeca != null && !this.idTipoBeca.equals(other.idTipoBeca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.TipoBeca[ idTipoBeca=" + idTipoBeca + " ]";
    }
    
}
