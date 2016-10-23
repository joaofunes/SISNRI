/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Joao
 */
@Entity
@Table(name = "PROPUESTA_CONVENIO", catalog = "sisrni", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID_PROPUESTA"})})
@NamedQueries({
    @NamedQuery(name = "PropuestaConvenio.findAll", query = "SELECT p FROM PropuestaConvenio p")})
public class PropuestaConvenio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROPUESTA", nullable = false)
    private Integer idPropuesta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_PROPUESTA", nullable = false, length = 100)
    private String nombrePropuesta;
    @Size(max = 300)
    @Column(name = "FINALIDAD_PROPUESTA", length = 300)
    private String finalidadPropuesta;
    @Column(name = "VIGENCIA")
    @Temporal(TemporalType.DATE)
    private Date vigencia;
    @JoinColumn(name = "ID_TIPO_PROPUESTA_CONVENIO", referencedColumnName = "ID_TIPO_PROPUESTA")
    @ManyToOne
    private TipoPropuestaConvenio idTipoPropuestaConvenio;
    @OneToMany(mappedBy = "idPropuesta")
    private List<Documento> documentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propuestaConvenio")
    private List<PersonaPropuesta> personaPropuestaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propuestaConvenio")
    private List<PropuestaEstado> propuestaEstadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPropuestaConvenio")
    private List<Proyecto> proyectoList;

    public PropuestaConvenio() {
    }

    public PropuestaConvenio(Integer idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public PropuestaConvenio(Integer idPropuesta, String nombrePropuesta) {
        this.idPropuesta = idPropuesta;
        this.nombrePropuesta = nombrePropuesta;
    }

    public Integer getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(Integer idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public String getNombrePropuesta() {
        return nombrePropuesta;
    }

    public void setNombrePropuesta(String nombrePropuesta) {
        this.nombrePropuesta = nombrePropuesta;
    }

    public String getFinalidadPropuesta() {
        return finalidadPropuesta;
    }

    public void setFinalidadPropuesta(String finalidadPropuesta) {
        this.finalidadPropuesta = finalidadPropuesta;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public TipoPropuestaConvenio getIdTipoPropuestaConvenio() {
        return idTipoPropuestaConvenio;
    }

    public void setIdTipoPropuestaConvenio(TipoPropuestaConvenio idTipoPropuestaConvenio) {
        this.idTipoPropuestaConvenio = idTipoPropuestaConvenio;
    }

    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    public List<PersonaPropuesta> getPersonaPropuestaList() {
        return personaPropuestaList;
    }

    public void setPersonaPropuestaList(List<PersonaPropuesta> personaPropuestaList) {
        this.personaPropuestaList = personaPropuestaList;
    }

    public List<PropuestaEstado> getPropuestaEstadoList() {
        return propuestaEstadoList;
    }

    public void setPropuestaEstadoList(List<PropuestaEstado> propuestaEstadoList) {
        this.propuestaEstadoList = propuestaEstadoList;
    }

    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPropuesta != null ? idPropuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropuestaConvenio)) {
            return false;
        }
        PropuestaConvenio other = (PropuestaConvenio) object;
        if ((this.idPropuesta == null && other.idPropuesta != null) || (this.idPropuesta != null && !this.idPropuesta.equals(other.idPropuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.PropuestaConvenio[ idPropuesta=" + idPropuesta + " ]";
    }
    
}