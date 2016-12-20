/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "FACULTAD_PROYECTO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "FacultadProyecto.findAll", query = "SELECT f FROM FacultadProyecto f")})
public class FacultadProyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FacultadProyectoPK facultadProyectoPK;
    @JoinColumn(name = "ID_FACULTAD", referencedColumnName = "ID_FACULTAD", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Facultad facultad;
    @JoinColumn(name = "ID_PROYECTO", referencedColumnName = "ID_PROYECTO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyecto proyecto;
    @JoinColumn(name = "ID_TIPO_FACULTAD", referencedColumnName = "ID_TIPO_FACULTAD")
    @ManyToOne
    private TipoFacultad idTipoFacultad;

    public FacultadProyecto() {
    }

    public FacultadProyecto(FacultadProyectoPK facultadProyectoPK) {
        this.facultadProyectoPK = facultadProyectoPK;
    }

    public FacultadProyecto(int idFacultad, int idProyecto) {
        this.facultadProyectoPK = new FacultadProyectoPK(idFacultad, idProyecto);
    }

    public FacultadProyectoPK getFacultadProyectoPK() {
        return facultadProyectoPK;
    }

    public void setFacultadProyectoPK(FacultadProyectoPK facultadProyectoPK) {
        this.facultadProyectoPK = facultadProyectoPK;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public TipoFacultad getIdTipoFacultad() {
        return idTipoFacultad;
    }

    public void setIdTipoFacultad(TipoFacultad idTipoFacultad) {
        this.idTipoFacultad = idTipoFacultad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facultadProyectoPK != null ? facultadProyectoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacultadProyecto)) {
            return false;
        }
        FacultadProyecto other = (FacultadProyecto) object;
        if ((this.facultadProyectoPK == null && other.facultadProyectoPK != null) || (this.facultadProyectoPK != null && !this.facultadProyectoPK.equals(other.facultadProyectoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.FacultadProyecto[ facultadProyectoPK=" + facultadProyectoPK + " ]";
    }
    
}
