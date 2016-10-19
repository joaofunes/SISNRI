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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Joao
 */
@Entity
@Table(name = "PROYECTO_GENERICO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "ProyectoGenerico.findAll", query = "SELECT p FROM ProyectoGenerico p")})
public class ProyectoGenerico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROYECTO", nullable = false)
    private Integer idProyecto;
    @Column(name = "LUGAR_PROYECTO")
    private Integer lugarProyecto;
    @Column(name = "OBJETIVO")
    private Integer objetivo;
    @JoinTable(name = "PROYECTO_GENERICO_AREA", joinColumns = {
        @JoinColumn(name = "ID_PROYECTO", referencedColumnName = "ID_PROYECTO", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ID_AREA_CONOCIMIENTO", referencedColumnName = "ID_AREA_CONOCIMIENTO", nullable = false)})
    @ManyToMany
    private List<AreaConocimiento> areaConocimientoList;
    @JoinColumn(name = "ID_PROYECTO", referencedColumnName = "ID_PROYECTO", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Proyecto proyecto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyectoGenerico")
    private List<PersonaProyecto> personaProyectoList;

    public ProyectoGenerico() {
    }

    public ProyectoGenerico(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getLugarProyecto() {
        return lugarProyecto;
    }

    public void setLugarProyecto(Integer lugarProyecto) {
        this.lugarProyecto = lugarProyecto;
    }

    public Integer getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Integer objetivo) {
        this.objetivo = objetivo;
    }

    public List<AreaConocimiento> getAreaConocimientoList() {
        return areaConocimientoList;
    }

    public void setAreaConocimientoList(List<AreaConocimiento> areaConocimientoList) {
        this.areaConocimientoList = areaConocimientoList;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public List<PersonaProyecto> getPersonaProyectoList() {
        return personaProyectoList;
    }

    public void setPersonaProyectoList(List<PersonaProyecto> personaProyectoList) {
        this.personaProyectoList = personaProyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyectoGenerico)) {
            return false;
        }
        ProyectoGenerico other = (ProyectoGenerico) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.ProyectoGenerico[ idProyecto=" + idProyecto + " ]";
    }
    
}
