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
@Table(name = "PROPUESTA", catalog = "sisrni", schema = "", uniqueConstraints = {
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
    @Size(max = 100)
    @Column(name = "NOMBRE_PROPUESTA", length = 100)
    private String nombrePropuesta;
    @Size(max = 300)
    @Column(name = "FINALIDAD_PROPUESTA", length = 300)
    private String finalidadPropuesta;
    @JoinTable(name = "PROPUESTA_ESTADO", joinColumns = {
        @JoinColumn(name = "ID_PROPUESTA", referencedColumnName = "ID_PROPUESTA", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO", nullable = false)})
    @ManyToMany
    private List<Estado> estadoList;
    @OneToMany(mappedBy = "idPropuesta")
    private List<Documento> documentoList;
    @JoinColumn(name = "ID_ORGANISMO", referencedColumnName = "ID_ORGANISMO")
    @ManyToOne
    private Organismo idOrganismo;
    @JoinColumn(name = "ID_PERSONA_INTERNO", referencedColumnName = "ID_PERSONA")
    @ManyToOne
    private Persona idPersonaInterno;
    @JoinColumn(name = "ID_CONVENIO", referencedColumnName = "ID_CONVENIO")
    @ManyToOne
    private Convenio idConvenio;
    @JoinColumn(name = "ID_PERSONA_EXTERNO", referencedColumnName = "ID_PERSONA")
    @ManyToOne
    private Persona idPersonaExterno;
    @JoinColumn(name = "ID_FACULTAD", referencedColumnName = "ID_FACULTAD")
    @ManyToOne
    private Facultad idFacultad;
    @JoinColumn(name = "ID_PERSONA_SOLICITANTE", referencedColumnName = "ID_PERSONA")
    @ManyToOne
    private Persona idPersonaSolicitante;

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

    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    public Organismo getIdOrganismo() {
        return idOrganismo;
    }

    public void setIdOrganismo(Organismo idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    public Persona getIdPersonaInterno() {
        return idPersonaInterno;
    }

    public void setIdPersonaInterno(Persona idPersonaInterno) {
        this.idPersonaInterno = idPersonaInterno;
    }

    public Convenio getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Convenio idConvenio) {
        this.idConvenio = idConvenio;
    }

    public Persona getIdPersonaExterno() {
        return idPersonaExterno;
    }

    public void setIdPersonaExterno(Persona idPersonaExterno) {
        this.idPersonaExterno = idPersonaExterno;
    }

    public Facultad getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Facultad idFacultad) {
        this.idFacultad = idFacultad;
    }

    public Persona getIdPersonaSolicitante() {
        return idPersonaSolicitante;
    }

    public void setIdPersonaSolicitante(Persona idPersonaSolicitante) {
        this.idPersonaSolicitante = idPersonaSolicitante;
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
