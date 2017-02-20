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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "ORGANISMO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "Organismo.findAll", query = "SELECT o FROM Organismo o")})
public class Organismo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ORGANISMO", nullable = false)
    private Integer idOrganismo;
    @Size(max = 200)
    @Column(name = "NOMBRE_ORGANISMO", length = 200)
    private String nombreOrganismo;
    @Size(max = 60)
    @Column(name = "CORREO_ORGANISMO", length = 60)
    private String correoOrganismo;
    @Size(max = 150)
    @Column(name = "DIRECCION_ORGANISMO", length = 150)
    private String direccionOrganismo;
    @Column(name = "CODIGO_POSTAL")
    private Integer codigoPostal;
    @Column(name = "ID_REGION")
    private Integer idRegion;
    @Column(name = "ID_PAIS")
    private Integer idPais;
    @Column(name = "ID_PROVINCIA")
    private Integer idProvincia;
    @Column(name = "ID_CUIDAD")
    private Integer idCuidad;
    @ManyToMany(mappedBy = "organismoList")
    private List<Proyecto> proyectoList;
    @JoinColumn(name = "ID_TIPO_ORGANISMO", referencedColumnName = "ID_TIPO_ORGANISMO")
    @ManyToOne
    private TipoOrganismo idTipoOrganismo;
    @OneToMany(mappedBy = "idOrganismo")
    private List<Persona> personaList;
    @OneToMany(mappedBy = "idOrganismo")
    private List<Unidad> unidadList;
    @OneToMany(mappedBy = "idOrganismo")
    private List<Facultad> facultadList;
    @OneToMany(mappedBy = "idOrganismo")
    private List<Telefono> telefonoList;
    @OneToMany(mappedBy = "idUniversidad")
    private List<Beca> becaList;
    @OneToMany(mappedBy = "idOrganismoCooperante")
    private List<Beca> becaList1;

    public Organismo() {
    }

    public Organismo(Integer idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    public Integer getIdOrganismo() {
        return idOrganismo;
    }

    public void setIdOrganismo(Integer idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    public String getNombreOrganismo() {
        return nombreOrganismo;
    }

    public void setNombreOrganismo(String nombreOrganismo) {
        this.nombreOrganismo = nombreOrganismo;
    }

    public String getCorreoOrganismo() {
        return correoOrganismo;
    }

    public void setCorreoOrganismo(String correoOrganismo) {
        this.correoOrganismo = correoOrganismo;
    }

    public String getDireccionOrganismo() {
        return direccionOrganismo;
    }

    public void setDireccionOrganismo(String direccionOrganismo) {
        this.direccionOrganismo = direccionOrganismo;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Integer getIdCuidad() {
        return idCuidad;
    }

    public void setIdCuidad(Integer idCuidad) {
        this.idCuidad = idCuidad;
    }

    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    public TipoOrganismo getIdTipoOrganismo() {
        return idTipoOrganismo;
    }

    public void setIdTipoOrganismo(TipoOrganismo idTipoOrganismo) {
        this.idTipoOrganismo = idTipoOrganismo;
    }

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    public List<Unidad> getUnidadList() {
        return unidadList;
    }

    public void setUnidadList(List<Unidad> unidadList) {
        this.unidadList = unidadList;
    }

    public List<Facultad> getFacultadList() {
        return facultadList;
    }

    public void setFacultadList(List<Facultad> facultadList) {
        this.facultadList = facultadList;
    }

    public List<Telefono> getTelefonoList() {
        return telefonoList;
    }

    public void setTelefonoList(List<Telefono> telefonoList) {
        this.telefonoList = telefonoList;
    }

    public List<Beca> getBecaList() {
        return becaList;
    }

    public void setBecaList(List<Beca> becaList) {
        this.becaList = becaList;
    }

    public List<Beca> getBecaList1() {
        return becaList1;
    }

    public void setBecaList1(List<Beca> becaList1) {
        this.becaList1 = becaList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrganismo != null ? idOrganismo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organismo)) {
            return false;
        }
        Organismo other = (Organismo) object;
        if ((this.idOrganismo == null && other.idOrganismo != null) || (this.idOrganismo != null && !this.idOrganismo.equals(other.idOrganismo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.Organismo[ idOrganismo=" + idOrganismo + " ]";
    }
    
}
