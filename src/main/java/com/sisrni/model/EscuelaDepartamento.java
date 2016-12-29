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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "ESCUELA_DEPARTAMENTO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "EscuelaDepartamento.findAll", query = "SELECT e FROM EscuelaDepartamento e")})
public class EscuelaDepartamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESCUELA_DEPTO", nullable = false)
    private Integer idEscuelaDepto;
    @Size(max = 60)
    @Column(name = "NOMBRE_ESCUELA_DEPTO", length = 60)
    private String nombreEscuelaDepto;
    @Size(max = 100)
    @Column(name = "DESCRIPCION", length = 100)
    private String descripcion;
    @Column(name = "IDENTIFICADOR")
    private Boolean identificador;
    @OneToMany(mappedBy = "idEscuelaDepto")
    private List<Persona> personaList;
    @JoinColumn(name = "ID_FACULTAD", referencedColumnName = "ID_FACULTAD")
    @ManyToOne
    private Facultad idFacultad;

    public EscuelaDepartamento() {
    }

    public EscuelaDepartamento(Integer idEscuelaDepto) {
        this.idEscuelaDepto = idEscuelaDepto;
    }

    public Integer getIdEscuelaDepto() {
        return idEscuelaDepto;
    }

    public void setIdEscuelaDepto(Integer idEscuelaDepto) {
        this.idEscuelaDepto = idEscuelaDepto;
    }

    public String getNombreEscuelaDepto() {
        return nombreEscuelaDepto;
    }

    public void setNombreEscuelaDepto(String nombreEscuelaDepto) {
        this.nombreEscuelaDepto = nombreEscuelaDepto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Boolean identificador) {
        this.identificador = identificador;
    }

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    public Facultad getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Facultad idFacultad) {
        this.idFacultad = idFacultad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEscuelaDepto != null ? idEscuelaDepto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EscuelaDepartamento)) {
            return false;
        }
        EscuelaDepartamento other = (EscuelaDepartamento) object;
        if ((this.idEscuelaDepto == null && other.idEscuelaDepto != null) || (this.idEscuelaDepto != null && !this.idEscuelaDepto.equals(other.idEscuelaDepto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.EscuelaDepartamento[ idEscuelaDepto=" + idEscuelaDepto + " ]";
    }
    
}
