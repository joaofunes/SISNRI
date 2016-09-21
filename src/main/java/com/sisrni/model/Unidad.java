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

/**
 *
 * @author Joao
 */
@Entity
@Table(name = "UNIDAD", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "Unidad.findAll", query = "SELECT u FROM Unidad u")})
public class Unidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_UNIDAD", nullable = false)
    private Integer idUnidad;
    @Column(name = "NOMBRE")
    private Character nombre;
    @Column(name = "DESCRIPCION")
    private Character descripcion;
    @OneToMany(mappedBy = "idUnidad")
    private List<Persona> personaList;
    @JoinColumn(name = "ID_FACULTAD", referencedColumnName = "ID_FACULTAD")
    @ManyToOne
    private Facultad idFacultad;

    public Unidad() {
    }

    public Unidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Character getNombre() {
        return nombre;
    }

    public void setNombre(Character nombre) {
        this.nombre = nombre;
    }

    public Character getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Character descripcion) {
        this.descripcion = descripcion;
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
        hash += (idUnidad != null ? idUnidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidad)) {
            return false;
        }
        Unidad other = (Unidad) object;
        if ((this.idUnidad == null && other.idUnidad != null) || (this.idUnidad != null && !this.idUnidad.equals(other.idUnidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.Unidad[ idUnidad=" + idUnidad + " ]";
    }
    
}
