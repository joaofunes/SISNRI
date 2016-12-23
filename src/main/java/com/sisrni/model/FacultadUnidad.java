/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Lillian
 */
@Entity
@Table(name = "FACULTAD_UNIDAD", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "FacultadUnidad.findAll", query = "SELECT f FROM FacultadUnidad f")})
public class FacultadUnidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FACULTAD_UNIDAD", nullable = false)
    private Integer idFacultadUnidad;
    @Size(max = 100)
    @Column(name = "NOMBRE_FACULTAD_UNIDAD", length = 100)
    private String nombreFacultadUnidad;
    @Column(name = "IDENTIFICADOR")
    private Boolean identificador;
    @JoinTable(name = "MOVILIDAD_FACULTAD", joinColumns = {
        @JoinColumn(name = "ID_FACULTAD_UNIDAD", referencedColumnName = "ID_FACULTAD_UNIDAD", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ID_MOVILIDAD", referencedColumnName = "ID_MOVILIDAD", nullable = false)})
    @ManyToMany
    private List<Movilidad> movilidadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFacultadUnidad")
    private List<Carrera> carreraList;
    @OneToMany(mappedBy = "idFacultadUnidad")
    private List<Persona> personaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultadUnidad")
    private List<FacultadProyecto> facultadProyectoList;
    @JoinColumn(name = "ID_UNIVERSIDAD", referencedColumnName = "ID_UNIVERSIDAD")
    @ManyToOne
    private Universidad idUniversidad;
    @OneToMany(mappedBy = "idFacultadUnidad")
    private List<EscuelaDepartamento> escuelaDepartamentoList;

    public FacultadUnidad() {
    }

    public FacultadUnidad(Integer idFacultadUnidad) {
        this.idFacultadUnidad = idFacultadUnidad;
    }

    public Integer getIdFacultadUnidad() {
        return idFacultadUnidad;
    }

    public void setIdFacultadUnidad(Integer idFacultadUnidad) {
        this.idFacultadUnidad = idFacultadUnidad;
    }

    public String getNombreFacultadUnidad() {
        return nombreFacultadUnidad;
    }

    public void setNombreFacultadUnidad(String nombreFacultadUnidad) {
        this.nombreFacultadUnidad = nombreFacultadUnidad;
    }

    public Boolean getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Boolean identificador) {
        this.identificador = identificador;
    }

    public List<Movilidad> getMovilidadList() {
        return movilidadList;
    }

    public void setMovilidadList(List<Movilidad> movilidadList) {
        this.movilidadList = movilidadList;
    }

    public List<Carrera> getCarreraList() {
        return carreraList;
    }

    public void setCarreraList(List<Carrera> carreraList) {
        this.carreraList = carreraList;
    }

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    public List<FacultadProyecto> getFacultadProyectoList() {
        return facultadProyectoList;
    }

    public void setFacultadProyectoList(List<FacultadProyecto> facultadProyectoList) {
        this.facultadProyectoList = facultadProyectoList;
    }

    public Universidad getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(Universidad idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public List<EscuelaDepartamento> getEscuelaDepartamentoList() {
        return escuelaDepartamentoList;
    }

    public void setEscuelaDepartamentoList(List<EscuelaDepartamento> escuelaDepartamentoList) {
        this.escuelaDepartamentoList = escuelaDepartamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFacultadUnidad != null ? idFacultadUnidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacultadUnidad)) {
            return false;
        }
        FacultadUnidad other = (FacultadUnidad) object;
        if ((this.idFacultadUnidad == null && other.idFacultadUnidad != null) || (this.idFacultadUnidad != null && !this.idFacultadUnidad.equals(other.idFacultadUnidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.FacultadUnidad[ idFacultadUnidad=" + idFacultadUnidad + " ]";
    }
    
}
