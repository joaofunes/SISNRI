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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Joao
 */
@Entity
@Table(name = "persona", catalog = "srnibd", schema = "")
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERSONA", nullable = false)
    private Integer idPersona;
    @Size(max = 60)
    @Column(name = "NOMBRE_PERSONA", length = 60)
    private String nombrePersona;
    @Size(max = 60)
    @Column(name = "APELLIDO_PERSONA", length = 60)
    private String apellidoPersona;
    @Size(max = 60)
    @Column(name = "TELEFONO_PERSONA", length = 60)
    private String telefonoPersona;
    @Size(max = 30)
    @Column(name = "CORREO_PERSONA", length = 30)
    private String correoPersona;
    @Size(max = 10)
    @Column(name = "DUI_PERSONA", length = 10)
    private String duiPersona;
    @Size(max = 60)
    @Column(name = "TIPO_PERSONA", length = 60)
    private String tipoPersona;
    @Size(max = 17)
    @Column(name = "NIT_PERSONA", length = 17)
    private String nitPersona;
    @Size(max = 60)
    @Column(name = "CARGO_PERSONA", length = 60)
    private String cargoPersona;
    @Size(max = 60)
    @Column(name = "PASAPORTE", length = 60)
    private String pasaporte;
    @OneToMany(mappedBy = "idPersona")
    private List<Propuesta> propuestaList;
    @OneToMany(mappedBy = "idPersona")
    private List<Proyecto> proyectoList;
    @JoinColumn(name = "ID_ORGANISMO", referencedColumnName = "ID_ORGANISMO")
    @ManyToOne
    private Organismo idOrganismo;

    public Persona() {
    }

    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    public String getTelefonoPersona() {
        return telefonoPersona;
    }

    public void setTelefonoPersona(String telefonoPersona) {
        this.telefonoPersona = telefonoPersona;
    }

    public String getCorreoPersona() {
        return correoPersona;
    }

    public void setCorreoPersona(String correoPersona) {
        this.correoPersona = correoPersona;
    }

    public String getDuiPersona() {
        return duiPersona;
    }

    public void setDuiPersona(String duiPersona) {
        this.duiPersona = duiPersona;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getNitPersona() {
        return nitPersona;
    }

    public void setNitPersona(String nitPersona) {
        this.nitPersona = nitPersona;
    }

    public String getCargoPersona() {
        return cargoPersona;
    }

    public void setCargoPersona(String cargoPersona) {
        this.cargoPersona = cargoPersona;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public List<Propuesta> getPropuestaList() {
        return propuestaList;
    }

    public void setPropuestaList(List<Propuesta> propuestaList) {
        this.propuestaList = propuestaList;
    }

    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    public Organismo getIdOrganismo() {
        return idOrganismo;
    }

    public void setIdOrganismo(Organismo idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.Persona[ idPersona=" + idPersona + " ]";
    }
    
}
