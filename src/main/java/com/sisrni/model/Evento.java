/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "evento", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e")})
public class Evento implements Serializable {

    @Size(max = 30)
    @Column(name = "Servicio", length = 30)
    private String servicio;
    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 10)
//    @Column(name = "num_sisrni", nullable = false, length = 10)
//    private String numDimesa;

    @Id
    @GeneratedValue
    @Column(name = "num_sisrni", nullable = true)
    private Integer numDimesa;

    @Size(max = 10)
    @Column(name = "Tbl_equipo_pla_sisrni", length = 10)
    private String tblequipoplasisrni;
    @Column(name = "Fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "Fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Size(max = 45)
    @Column(name = "Unidad", length = 45)
    private String unidad;
    @Size(max = 105)
    @Column(name = "Falla", length = 105)
    private String falla;
    @JoinColumn(name = "Pla_sisrni", referencedColumnName = "Pla_sisrni")
    @ManyToOne
    private Equipo plasisrni;
    @JoinColumn(name = "Id_costo_equipo", referencedColumnName = "Id_costo_equipo")
    @ManyToOne
    private CostoEquipo idcostoequipo;

    public Evento() {
    }

    public Evento(Integer numDimesa) {
        this.numDimesa = numDimesa;
    }

    public Integer getNumDimesa() {
        return numDimesa;
    }

    public void setNumDimesa(Integer numDimesa) {
        this.numDimesa = numDimesa;
    }

    public String getTblequipoplasisrni() {
        return tblequipoplasisrni;
    }

    public void setTblequipoplasisrni(String tblequipoplasisrni) {
        this.tblequipoplasisrni = tblequipoplasisrni;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getFalla() {
        return falla;
    }

    public void setFalla(String falla) {
        this.falla = falla;
    }

    public Equipo getPlasisrni() {
        return plasisrni;
    }

    public void setPlasisrni(Equipo plasisrni) {
        this.plasisrni = plasisrni;
    }

    public CostoEquipo getIdcostoequipo() {
        return idcostoequipo;
    }

    public void setIdcostoequipo(CostoEquipo idcostoequipo) {
        this.idcostoequipo = idcostoequipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numDimesa != null ? numDimesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.numDimesa == null && other.numDimesa != null) || (this.numDimesa != null && !this.numDimesa.equals(other.numDimesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.Evento[ numDimesa=" + numDimesa + " ]";
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

}
