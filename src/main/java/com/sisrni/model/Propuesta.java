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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Joao
 */
@Entity
@Table(name = "propuesta", catalog = "srnibd", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID_PROPUESTA"})})
@NamedQueries({
    @NamedQuery(name = "Propuesta.findAll", query = "SELECT p FROM Propuesta p")})
public class Propuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROPUESTA", nullable = false)
    private Integer idPropuesta;
    @Column(name = "PER_ID_PERSONA")
    private Integer perIdPersona;
    @Column(name = "PER_ID_PERSONA2")
    private Integer perIdPersona2;
    @Size(max = 100)
    @Column(name = "NOMBRE_PROPUESTA", length = 100)
    private String nombrePropuesta;
    @Size(max = 300)
    @Column(name = "FINALIDAD_PROPUESTA", length = 300)
    private String finalidadPropuesta;
    @JoinTable(name = "propuesta_estado", joinColumns = {
        @JoinColumn(name = "ID_PROPUESTA", referencedColumnName = "ID_PROPUESTA", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO", nullable = false)})
    @ManyToMany
    private List<Estado> estadoList;
    @JoinColumn(name = "ID_CONVENIO", referencedColumnName = "ID_CONVENIO")
    @ManyToOne
    private Convenio idConvenio;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA")
    @ManyToOne
    private Persona idPersona;
    @JoinColumn(name = "ID_UNIVERSIDAD", referencedColumnName = "ID_UNIVERSIDAD")
    @ManyToOne
    private Universidad idUniversidad;
    @OneToMany(mappedBy = "idPropuesta")
    private List<Documento> documentoList;

    public Propuesta() {
    }

    public Propuesta(Integer idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public Integer getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(Integer idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public Integer getPerIdPersona() {
        return perIdPersona;
    }

    public void setPerIdPersona(Integer perIdPersona) {
        this.perIdPersona = perIdPersona;
    }

    public Integer getPerIdPersona2() {
        return perIdPersona2;
    }

    public void setPerIdPersona2(Integer perIdPersona2) {
        this.perIdPersona2 = perIdPersona2;
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

    public List<Estado> getEstadoList() {
        return estadoList;
    }

    public void setEstadoList(List<Estado> estadoList) {
        this.estadoList = estadoList;
    }

    public Convenio getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Convenio idConvenio) {
        this.idConvenio = idConvenio;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public Universidad getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(Universidad idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
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
        if (!(object instanceof Propuesta)) {
            return false;
        }
        Propuesta other = (Propuesta) object;
        if ((this.idPropuesta == null && other.idPropuesta != null) || (this.idPropuesta != null && !this.idPropuesta.equals(other.idPropuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.Propuesta[ idPropuesta=" + idPropuesta + " ]";
    }
    
}
