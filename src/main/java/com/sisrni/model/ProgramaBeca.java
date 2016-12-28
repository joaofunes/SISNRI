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
@Table(name = "PROGRAMA_BECA", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "ProgramaBeca.findAll", query = "SELECT p FROM ProgramaBeca p")})
public class ProgramaBeca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROGRAMA", nullable = false)
    private Integer idPrograma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "NOMBRE_PROGRAMA", nullable = false, length = 300)
    private String nombrePrograma;
    @Size(max = 300)
    @Column(name = "DESCRIPCION", length = 300)
    private String descripcion;
    @OneToMany(mappedBy = "idProgramaBeca")
    private List<Beca> becaList;

    public ProgramaBeca() {
    }

    public ProgramaBeca(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public ProgramaBeca(Integer idPrograma, String nombrePrograma) {
        this.idPrograma = idPrograma;
        this.nombrePrograma = nombrePrograma;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idPrograma != null ? idPrograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramaBeca)) {
            return false;
        }
        ProgramaBeca other = (ProgramaBeca) object;
        if ((this.idPrograma == null && other.idPrograma != null) || (this.idPrograma != null && !this.idPrograma.equals(other.idPrograma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.ProgramaBeca[ idPrograma=" + idPrograma + " ]";
    }
    
}
