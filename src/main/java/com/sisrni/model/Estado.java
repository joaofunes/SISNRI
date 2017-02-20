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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "ESTADO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e")})
public class Estado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESTADO", nullable = false)
    private Integer idEstado;
    @Size(max = 60)
    @Column(name = "NOMBRE_ESTADO", length = 60)
    private String nombreEstado;
    @Column(name = "FECHA_INGRESO_ESTADO")
    @Temporal(TemporalType.DATE)
    private Date fechaIngresoEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_ESTADO", nullable = false)
    private int tipoEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN_ESTADO", nullable = false)
    private int ordenEstado;
    @JoinTable(name = "PROYECTO_ESTADO", joinColumns = {
        @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PROYECTO", referencedColumnName = "ID_PROYECTO", nullable = false)})
    @ManyToMany
    private List<Proyecto> proyectoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private List<PropuestaEstado> propuestaEstadoList;

    public Estado() {
    }

    public Estado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Estado(Integer idEstado, int tipoEstado, int ordenEstado) {
        this.idEstado = idEstado;
        this.tipoEstado = tipoEstado;
        this.ordenEstado = ordenEstado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public Date getFechaIngresoEstado() {
        return fechaIngresoEstado;
    }

    public void setFechaIngresoEstado(Date fechaIngresoEstado) {
        this.fechaIngresoEstado = fechaIngresoEstado;
    }

    public int getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(int tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    public int getOrdenEstado() {
        return ordenEstado;
    }

    public void setOrdenEstado(int ordenEstado) {
        this.ordenEstado = ordenEstado;
    }

    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    public List<PropuestaEstado> getPropuestaEstadoList() {
        return propuestaEstadoList;
    }

    public void setPropuestaEstadoList(List<PropuestaEstado> propuestaEstadoList) {
        this.propuestaEstadoList = propuestaEstadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.Estado[ idEstado=" + idEstado + " ]";
    }
    
}
