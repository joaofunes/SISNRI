/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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
 * @author HDEZ
 */
@Entity
@Table(name = "equipo", catalog = "dimesa", schema = "")
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e")})
public class Equipo implements Serializable {
    @Lob
    @Column(name = "Imagen")
    private byte[] imagen;
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "Pla_dimesa", nullable = true)
    private Integer pladimesa;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Num_serie", nullable = false, length = 20)
    private String numserie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Marca_equipo", nullable = false, length = 20)
    private String marcaequipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Modelo_equipo", nullable = false, length = 20)
    private String modeloequipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Nomb_equipo", nullable = false, length = 20)
    private String nombequipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Observ_eq", nullable = false, length = 60)
    private String observeq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Empresa_responsable", nullable = false, length = 60)
    private String empresaresponsable;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @OneToMany(mappedBy = "pladimesa")
    private Set<Evento> eventoSet;

    public Equipo() {
    }

    public Equipo(Integer pladimesa) {
        this.pladimesa = pladimesa;
    }

    public Equipo(Integer pladimesa, String numserie, String marcaequipo, String modeloequipo, String nombequipo, String observeq, String empresaresponsable) {
        this.pladimesa = pladimesa;
        this.numserie = numserie;
        this.marcaequipo = marcaequipo;
        this.modeloequipo = modeloequipo;
        this.nombequipo = nombequipo;
        this.observeq = observeq;
        this.empresaresponsable = empresaresponsable;
    }

    public Integer getPladimesa() {
        return pladimesa;
    }

    public void setPladimesa(Integer pladimesa) {
        this.pladimesa = pladimesa;
    }

    public String getNumserie() {
        return numserie;
    }

    public void setNumserie(String numserie) {
        this.numserie = numserie;
    }

    public String getMarcaequipo() {
        return marcaequipo;
    }

    public void setMarcaequipo(String marcaequipo) {
        this.marcaequipo = marcaequipo;
    }

    public String getModeloequipo() {
        return modeloequipo;
    }

    public void setModeloequipo(String modeloequipo) {
        this.modeloequipo = modeloequipo;
    }

    public String getNombequipo() {
        return nombequipo;
    }

    public void setNombequipo(String nombequipo) {
        this.nombequipo = nombequipo;
    }

    public String getObserveq() {
        return observeq;
    }

    public void setObserveq(String observeq) {
        this.observeq = observeq;
    }

    public String getEmpresaresponsable() {
        return empresaresponsable;
    }

    public void setEmpresaresponsable(String empresaresponsable) {
        this.empresaresponsable = empresaresponsable;
    }


    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Set<Evento> getEventoSet() {
        return eventoSet;
    }

    public void setEventoSet(Set<Evento> eventoSet) {
        this.eventoSet = eventoSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pladimesa != null ? pladimesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.pladimesa == null && other.pladimesa != null) || (this.pladimesa != null && !this.pladimesa.equals(other.pladimesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dimesa.model.Equipo[ pladimesa=" + pladimesa + " ]";
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
}
