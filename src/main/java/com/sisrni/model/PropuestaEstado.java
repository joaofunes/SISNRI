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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "PROPUESTA_ESTADO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "PropuestaEstado.findAll", query = "SELECT p FROM PropuestaEstado p")})
public class PropuestaEstado implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PropuestaEstadoPK propuestaEstadoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "ID_PROPUESTA", referencedColumnName = "ID_PROPUESTA", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PropuestaConvenio propuestaConvenio;
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estado estado;

    public PropuestaEstado() {
    }

    public PropuestaEstado(PropuestaEstadoPK propuestaEstadoPK) {
        this.propuestaEstadoPK = propuestaEstadoPK;
    }

    public PropuestaEstado(PropuestaEstadoPK propuestaEstadoPK, Date fecha) {
        this.propuestaEstadoPK = propuestaEstadoPK;
        this.fecha = fecha;
    }

    public PropuestaEstado(int idPropuesta, int idEstado) {
        this.propuestaEstadoPK = new PropuestaEstadoPK(idPropuesta, idEstado);
    }

    public PropuestaEstadoPK getPropuestaEstadoPK() {
        return propuestaEstadoPK;
    }

    public void setPropuestaEstadoPK(PropuestaEstadoPK propuestaEstadoPK) {
        this.propuestaEstadoPK = propuestaEstadoPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public PropuestaConvenio getPropuestaConvenio() {
        return propuestaConvenio;
    }

    public void setPropuestaConvenio(PropuestaConvenio propuestaConvenio) {
        this.propuestaConvenio = propuestaConvenio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (propuestaEstadoPK != null ? propuestaEstadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropuestaEstado)) {
            return false;
        }
        PropuestaEstado other = (PropuestaEstado) object;
        if ((this.propuestaEstadoPK == null && other.propuestaEstadoPK != null) || (this.propuestaEstadoPK != null && !this.propuestaEstadoPK.equals(other.propuestaEstadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.PropuestaEstado[ propuestaEstadoPK=" + propuestaEstadoPK + " ]";
    }
    
}
