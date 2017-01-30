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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "ss_historico_claves", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "SsHistoricoClaves.findAll", query = "SELECT s FROM SsHistoricoClaves s")})
public class SsHistoricoClaves implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_HISTORICO_CLAVE", nullable = false)
    private Integer idHistoricoClave;
    @Column(name = "ID_USUARIO2")
    private Integer idUsuario2;
    @Column(name = "FECHA_CLAVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaClave;
    @Size(max = 100)
    @Column(name = "CLAVE2", length = 100)
    private String clave2;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private SsUsuarios idUsuario;

    public SsHistoricoClaves() {
    }

    public SsHistoricoClaves(Integer idHistoricoClave) {
        this.idHistoricoClave = idHistoricoClave;
    }

    public Integer getIdHistoricoClave() {
        return idHistoricoClave;
    }

    public void setIdHistoricoClave(Integer idHistoricoClave) {
        this.idHistoricoClave = idHistoricoClave;
    }

    public Integer getIdUsuario2() {
        return idUsuario2;
    }

    public void setIdUsuario2(Integer idUsuario2) {
        this.idUsuario2 = idUsuario2;
    }

    public Date getFechaClave() {
        return fechaClave;
    }

    public void setFechaClave(Date fechaClave) {
        this.fechaClave = fechaClave;
    }

    public String getClave2() {
        return clave2;
    }

    public void setClave2(String clave2) {
        this.clave2 = clave2;
    }

    public SsUsuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(SsUsuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistoricoClave != null ? idHistoricoClave.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SsHistoricoClaves)) {
            return false;
        }
        SsHistoricoClaves other = (SsHistoricoClaves) object;
        if ((this.idHistoricoClave == null && other.idHistoricoClave != null) || (this.idHistoricoClave != null && !this.idHistoricoClave.equals(other.idHistoricoClave))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.SsHistoricoClaves[ idHistoricoClave=" + idHistoricoClave + " ]";
    }
    
}
