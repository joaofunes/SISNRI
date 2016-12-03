/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Joao
 */
@Entity
@Table(name = "BECA", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "Beca.findAll", query = "SELECT b FROM Beca b")})
public class Beca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_BECAS", nullable = false)
    private Integer idBecas;
    @Basic(optional = false)
    @Column(name = "ID_REGION_DESTINO", nullable = false)
    private int idRegionDestino;
    @Basic(optional = false)
    @Column(name = "ID_PAIS_DESTINO", nullable = false)
    private int idPaisDestino;
    @Basic(optional = false)
    @Column(name = "CARRERA", nullable = false, length = 100)
    private String carrera;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTO_BECARIO", precision = 22)
    private Double montoBecario;
    @Column(name = "OTORGADO")
    private Boolean otorgado;
    @JoinColumn(name = "ID_MODALIDAD", referencedColumnName = "ID_TIPO_MODALIDAD")
    @ManyToOne
    private TipoModalidaBeca idModalidad;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA")
    @ManyToOne
    private Persona idPersona;
    @JoinColumn(name = "ID_BECAS", referencedColumnName = "ID_PROYECTO", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Proyecto proyecto;

    public Beca() {
    }

    public Beca(Integer idBecas) {
        this.idBecas = idBecas;
    }

    public Beca(Integer idBecas, int idRegionDestino, int idPaisDestino, String carrera) {
        this.idBecas = idBecas;
        this.idRegionDestino = idRegionDestino;
        this.idPaisDestino = idPaisDestino;
        this.carrera = carrera;
    }

    public Integer getIdBecas() {
        return idBecas;
    }

    public void setIdBecas(Integer idBecas) {
        this.idBecas = idBecas;
    }

    public int getIdRegionDestino() {
        return idRegionDestino;
    }

    public void setIdRegionDestino(int idRegionDestino) {
        this.idRegionDestino = idRegionDestino;
    }

    public int getIdPaisDestino() {
        return idPaisDestino;
    }

    public void setIdPaisDestino(int idPaisDestino) {
        this.idPaisDestino = idPaisDestino;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Double getMontoBecario() {
        return montoBecario;
    }

    public void setMontoBecario(Double montoBecario) {
        this.montoBecario = montoBecario;
    }

    public Boolean getOtorgado() {
        return otorgado;
    }

    public void setOtorgado(Boolean otorgado) {
        this.otorgado = otorgado;
    }

    public TipoModalidaBeca getIdModalidad() {
        return idModalidad;
    }

    public void setIdModalidad(TipoModalidaBeca idModalidad) {
        this.idModalidad = idModalidad;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBecas != null ? idBecas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Beca)) {
            return false;
        }
        Beca other = (Beca) object;
        if ((this.idBecas == null && other.idBecas != null) || (this.idBecas != null && !this.idBecas.equals(other.idBecas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Beca[ idBecas=" + idBecas + " ]";
    }
    
}
