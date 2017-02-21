/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author Cortez
 */
@Entity
@Table(name = "PERSONA", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PERSONA", nullable = false)
    private Integer idPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE_PERSONA", nullable = false, length = 60)
    private String nombrePersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "APELLIDO_PERSONA", nullable = false, length = 60)
    private String apellidoPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "EMAIL_PERSONA", nullable = false, length = 30)
    private String emailPersona;
    @Size(max = 10)
    @Column(name = "DUI_PERSONA", length = 10)
    private String duiPersona;
    @Size(max = 17)
    @Column(name = "NIT_PERSONA", length = 17)
    private String nitPersona;
    @Size(max = 100)
    @Column(name = "CARGO_PERSONA", length = 100)
    private String cargoPersona;
    @Size(max = 30)
    @Column(name = "PASAPORTE", length = 30)
    private String pasaporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXTRANJERO", nullable = false)
    private boolean extranjero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVO", nullable = false)
    private boolean activo;
    @JoinColumn(name = "ID_UNIDAD", referencedColumnName = "ID_UNIDAD")
    @ManyToOne
    private Unidad idUnidad;
    @JoinColumn(name = "ID_CARRERA", referencedColumnName = "ID_CARRERA")
    @ManyToOne
    private Carrera idCarrera;
    @JoinColumn(name = "ID_ESCUELA_DEPTO", referencedColumnName = "ID_ESCUELA_DEPTO")
    @ManyToOne
    private EscuelaDepartamento idEscuelaDepto;
    @JoinColumn(name = "ID_ORGANISMO", referencedColumnName = "ID_ORGANISMO")
    @ManyToOne
    private Organismo idOrganismo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private List<PersonaPropuesta> personaPropuestaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private List<PersonaProyecto> personaProyectoList=new ArrayList<PersonaProyecto>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private List<PersonaMovilidad> personaMovilidadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private List<PersonaBeca> personaBecaList=new ArrayList<PersonaBeca>();
    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "idPersona")
    private List<Telefono> telefonoList=new ArrayList<Telefono>();

    public Persona() {
    }

    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Persona(Integer idPersona, String nombrePersona, String apellidoPersona, String emailPersona, boolean extranjero, boolean activo) {
        this.idPersona = idPersona;
        this.nombrePersona = nombrePersona;
        this.apellidoPersona = apellidoPersona;
        this.emailPersona = emailPersona;
        this.extranjero = extranjero;
        this.activo = activo;
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

    public boolean getExtranjero() {
        return extranjero;
    }

    public void setExtranjero(boolean extranjero) {
        this.extranjero = extranjero;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Unidad getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Unidad idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Carrera getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Carrera idCarrera) {
        this.idCarrera = idCarrera;
    }

    public EscuelaDepartamento getIdEscuelaDepto() {
        return idEscuelaDepto;
    }

    public void setIdEscuelaDepto(EscuelaDepartamento idEscuelaDepto) {
        this.idEscuelaDepto = idEscuelaDepto;
    }

    public Organismo getIdOrganismo() {
        return idOrganismo;
    }

    public void setIdOrganismo(Organismo idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    public List<PersonaPropuesta> getPersonaPropuestaList() {
        return personaPropuestaList;
    }

    public void setPersonaPropuestaList(List<PersonaPropuesta> personaPropuestaList) {
        this.personaPropuestaList = personaPropuestaList;
    }

    public List<PersonaProyecto> getPersonaProyectoList() {
        return personaProyectoList;
    }

    public void setPersonaProyectoList(List<PersonaProyecto> personaProyectoList) {
        this.personaProyectoList = personaProyectoList;
    }

    public List<PersonaMovilidad> getPersonaMovilidadList() {
        return personaMovilidadList;
    }

    public void setPersonaMovilidadList(List<PersonaMovilidad> personaMovilidadList) {
        this.personaMovilidadList = personaMovilidadList;
    }

    public List<PersonaBeca> getPersonaBecaList() {
        return personaBecaList;
    }

    public void setPersonaBecaList(List<PersonaBeca> personaBecaList) {
        this.personaBecaList = personaBecaList;
    }

    public List<Telefono> getTelefonoList() {
        return telefonoList;
    }

    public void setTelefonoList(List<Telefono> telefonoList) {
        this.telefonoList = telefonoList;
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
