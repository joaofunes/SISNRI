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
import javax.validation.constraints.Size;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "PROGRAMA_MOVILIDAD", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "ProgramaMovilidad.findAll", query = "SELECT p FROM ProgramaMovilidad p")})
public class ProgramaMovilidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROGRAMA_MOVILIDAD", nullable = false)
    private Integer idProgramaMovilidad;
    @Size(max = 300)
    @Column(name = "NOMBRE_PROGRAMA_MOVILIDAD", length = 300)
    private String nombreProgramaMovilidad;
    @Size(max = 300)
    @Column(name = "DESCRIPCION", length = 300)
    private String descripcion;
    @OneToMany(mappedBy = "idProgramaMovilidad")
    private List<Movilidad> movilidadList;

    public ProgramaMovilidad() {
    }

    public ProgramaMovilidad(Integer idProgramaMovilidad) {
        this.idProgramaMovilidad = idProgramaMovilidad;
    }

    public Integer getIdProgramaMovilidad() {
        return idProgramaMovilidad;
    }

    public void setIdProgramaMovilidad(Integer idProgramaMovilidad) {
        this.idProgramaMovilidad = idProgramaMovilidad;
    }

    public String getNombreProgramaMovilidad() {
        return nombreProgramaMovilidad;
    }

    public void setNombreProgramaMovilidad(String nombreProgramaMovilidad) {
        this.nombreProgramaMovilidad = nombreProgramaMovilidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Movilidad> getMovilidadList() {
        return movilidadList;
    }

    public void setMovilidadList(List<Movilidad> movilidadList) {
        this.movilidadList = movilidadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProgramaMovilidad != null ? idProgramaMovilidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramaMovilidad)) {
            return false;
        }
        ProgramaMovilidad other = (ProgramaMovilidad) object;
        if ((this.idProgramaMovilidad == null && other.idProgramaMovilidad != null) || (this.idProgramaMovilidad != null && !this.idProgramaMovilidad.equals(other.idProgramaMovilidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.ProgramaMovilidad[ idProgramaMovilidad=" + idProgramaMovilidad + " ]";
    }
    
}
