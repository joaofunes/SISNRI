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
@Table(name = "PERSONA", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_PERSONA", nullable = false)
    private Integer idPersona;
    @Size(max = 60)
    @Column(name = "NOMBRE_PERSONA", length = 60)
    private String nombrePersona;
    @Size(max = 60)
    @Column(name = "APELLIDO_PERSONA", length = 60)
    private String apellidoPersona;
    @Size(max = 30)
    @Column(name = "EMAIL_PERSONA", length = 30)
    private String emailPersona;
    @Size(max = 10)
    @Column(name = "DUI_PERSONA", length = 10)
    private String duiPersona;
    @Size(max = 17)
    @Column(name = "NIT_PERSONA", length = 17)
    private String nitPersona;
    @Size(max = 60)
    @Column(name = "CARGO_PERSONA", length = 60)
    private String cargoPersona;
    @Size(max = 60)
    @Column(name = "PASAPORTE", length = 60)
    private String pasaporte;
    @JoinColumn(name = "ID_UNIDAD", referencedColumnName = "ID_UNIDAD")
    @ManyToOne
    private Unidad idUnidad;
    @JoinColumn(name = "ID_TIPO_PERSONA", referencedColumnName = "ID_TIPO_PERSONA")
    @ManyToOne
    private TipoPersona idTipoPersona;
    @JoinColumn(name = "ID_ORGANISMO", referencedColumnName = "ID_ORGANISMO")
    @ManyToOne
    private Organismo idOrganismo;
    @OneToMany(mappedBy = "idPersona")
    private List<Telefono> telefonoList;
    @OneToMany(mappedBy = "idPersonaInterno")
    private List<Propuesta> propuestaList;
    @OneToMany(mappedBy = "idPersonaExterno")
    private List<Propuesta> propuestaList1;
    @OneToMany(mappedBy = "idPersonaSolicitante")
    private List<Propuesta> propuestaList2;
    @OneToMany(mappedBy = "idReferente")
    private List<Proyecto> proyectoList;
    @OneToMany(mappedBy = "idAsistente")
    private List<Proyecto> proyectoList1;
    @OneToMany(mappedBy = "idCordinador")
    private List<Proyecto> proyectoList2;
    
    

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

    public String getEmailPersona() {
        return emailPersona;
    }

    public void setEmailPersona(String emailPersona) {
        this.emailPersona = emailPersona;
    }

    public String getDuiPersona() {
        return duiPersona;
    }

    public void setDuiPersona(String duiPersona) {
        this.duiPersona = duiPersona;
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

    public Unidad getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Unidad idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Organismo getIdOrganismo() {
        return idOrganismo;
    }

    public void setIdOrganismo(Organismo idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    public List<Telefono> getTelefonoList() {
        return telefonoList;
    }

    public void setTelefonoList(List<Telefono> telefonoList) {
        this.telefonoList = telefonoList;
    }

    public List<Propuesta> getPropuestaList() {
        return propuestaList;
    }

    public void setPropuestaList(List<Propuesta> propuestaList) {
        this.propuestaList = propuestaList;
    }

    public List<Propuesta> getPropuestaList1() {
        return propuestaList1;
    }

    public void setPropuestaList1(List<Propuesta> propuestaList1) {
        this.propuestaList1 = propuestaList1;
    }

    public List<Propuesta> getPropuestaList2() {
        return propuestaList2;
    }

    public void setPropuestaList2(List<Propuesta> propuestaList2) {
        this.propuestaList2 = propuestaList2;
    }

    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    public List<Proyecto> getProyectoList1() {
        return proyectoList1;
    }

    public void setProyectoList1(List<Proyecto> proyectoList1) {
        this.proyectoList1 = proyectoList1;
    }

    public List<Proyecto> getProyectoList2() {
        return proyectoList2;
    }

    public void setProyectoList2(List<Proyecto> proyectoList2) {
        this.proyectoList2 = proyectoList2;
    }
    
    public TipoPersona getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(TipoPersona idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
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
