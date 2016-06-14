/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author HDEZ
 */
@Entity
@Table(name = "servicio", catalog = "dimesa", schema = "")
@NamedQueries({
    @NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s")})
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 4)
//    @Column(name = "id_servicios", nullable = false, length = 4)
//    private String idServicios;

    @Id
    @GeneratedValue
    @Column(name = "id_servicios", nullable = true)
    private Integer idServicios;

    @Size(max = 45)
    @Column(name = "nombre", length = 45)
    private String nombre;

    public Servicio() {
    }

    public Servicio(Integer idServicios) {
        this.idServicios = idServicios;
    }

    public Integer getIdServicios() {
        return idServicios;
    }

    public void setIdServicios(Integer idServicios) {
        this.idServicios = idServicios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicios != null ? idServicios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.idServicios == null && other.idServicios != null) || (this.idServicios != null && !this.idServicios.equals(other.idServicios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dimesa.model.Servicio[ idServicios=" + idServicios + " ]";
    }

}
