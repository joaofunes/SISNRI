/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.model;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author HDEZ
 */
@Entity
@Table(name = "supervisor", catalog = "dimesa", schema = "")
@NamedQueries({
    @NamedQuery(name = "Supervisor.findAll", query = "SELECT s FROM Supervisor s")})
public class Supervisor implements Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 4)
//    @Column(name = "Id_supervisor", nullable = false, length = 4)
//    private String idsupervisor;

    @Id
    @GeneratedValue
    @Column(name = "Id_supervisor", nullable = true)
    private Integer idsupervisor;
    
    @Size(max = 45)
    @Column(name = "nombre", length = 45)
    private String nombre;
    @Size(max = 45)
    @Column(name = "apellido", length = 45)
    private String apellido;
    @JoinColumn(name = "IdEmpleado", referencedColumnName = "IdEmpleado")
    @ManyToOne
    private Empleado idEmpleado;

    public Supervisor() {
    }

    public Supervisor(Integer idsupervisor) {
        this.idsupervisor = idsupervisor;
    }

    public Integer getIdsupervisor() {
        return idsupervisor;
    }

    public void setIdsupervisor(Integer idsupervisor) {
        this.idsupervisor = idsupervisor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsupervisor != null ? idsupervisor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supervisor)) {
            return false;
        }
        Supervisor other = (Supervisor) object;
        if ((this.idsupervisor == null && other.idsupervisor != null) || (this.idsupervisor != null && !this.idsupervisor.equals(other.idsupervisor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dimesa.model.Supervisor[ idsupervisor=" + idsupervisor + " ]";
    }

}
