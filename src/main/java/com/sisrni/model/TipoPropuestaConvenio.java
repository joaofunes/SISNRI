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
@Table(name = "TIPO_PROPUESTA_CONVENIO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "TipoPropuestaConvenio.findAll", query = "SELECT t FROM TipoPropuestaConvenio t")})
public class TipoPropuestaConvenio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_PROPUESTA", nullable = false)
    private Integer idTipoPropuesta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_PROPUESTA_CONVENIO", nullable = false, length = 50)
    private String nombrePropuestaConvenio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION", nullable = false, length = 100)
    private String descripcion;
    @OneToMany(mappedBy = "idTipoPropuestaConvenio")
    private List<PropuestaConvenio> propuestaConvenioList;

    public TipoPropuestaConvenio() {
    }

    public TipoPropuestaConvenio(Integer idTipoPropuesta) {
        this.idTipoPropuesta = idTipoPropuesta;
    }

    public TipoPropuestaConvenio(Integer idTipoPropuesta, String nombrePropuestaConvenio, String descripcion) {
        this.idTipoPropuesta = idTipoPropuesta;
        this.nombrePropuestaConvenio = nombrePropuestaConvenio;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoPropuesta() {
        return idTipoPropuesta;
    }

    public void setIdTipoPropuesta(Integer idTipoPropuesta) {
        this.idTipoPropuesta = idTipoPropuesta;
    }

    public String getNombrePropuestaConvenio() {
        return nombrePropuestaConvenio;
    }

    public void setNombrePropuestaConvenio(String nombrePropuestaConvenio) {
        this.nombrePropuestaConvenio = nombrePropuestaConvenio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<PropuestaConvenio> getPropuestaConvenioList() {
        return propuestaConvenioList;
    }

    public void setPropuestaConvenioList(List<PropuestaConvenio> propuestaConvenioList) {
        this.propuestaConvenioList = propuestaConvenioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPropuesta != null ? idTipoPropuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPropuestaConvenio)) {
            return false;
        }
        TipoPropuestaConvenio other = (TipoPropuestaConvenio) object;
        if ((this.idTipoPropuesta == null && other.idTipoPropuesta != null) || (this.idTipoPropuesta != null && !this.idTipoPropuesta.equals(other.idTipoPropuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.TipoPropuestaConvenio[ idTipoPropuesta=" + idTipoPropuesta + " ]";
    }
    
}
