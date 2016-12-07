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
 * @author Joao
 */
@Entity
@Table(name = "ORGANISMO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "Organismo.findAll", query = "SELECT o FROM Organismo o")})
public class Organismo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idOrganismo;
    @Size(max = 100)
    private String nombreOrganismo;
    @Size(max = 60)
    private String correoOrganismo;
    @Size(max = 100)
    private String direccionOrganismo;
    private Integer codigoPostal;
    private Integer idRegion;
    private Integer idPais;
    private Integer idProvincia;
    private Integer idCuidad;

    private List<ProyectoGenerico> proyectoGenericoList;

    private List<Persona> personaList;
    private TipoOrganismo idTipoOrganismo;
    private List<Telefono> telefonoList;

    public Organismo() {
    }

    public Organismo(Integer idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ORGANISMO", nullable = false)
    public Integer getIdOrganismo() {
        return idOrganismo;
    }

    public void setIdOrganismo(Integer idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

@Column(name = "NOMBRE_ORGANISMO", length = 100)
    public String getNombreOrganismo() {
        return nombreOrganismo;
    }

    public void setNombreOrganismo(String nombreOrganismo) {
        this.nombreOrganismo = nombreOrganismo;
    }

@Column(name = "CORREO_ORGANISMO", length = 60)
    public String getCorreoOrganismo() {
        return correoOrganismo;
    }

    public void setCorreoOrganismo(String correoOrganismo) {
        this.correoOrganismo = correoOrganismo;
    }

@Column(name = "DIRECCION_ORGANISMO", length = 100)
    public String getDireccionOrganismo() {
        return direccionOrganismo;
    }

    public void setDireccionOrganismo(String direccionOrganismo) {
        this.direccionOrganismo = direccionOrganismo;
    }

@Column(name = "CODIGO_POSTAL")
    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

@Column(name = "ID_REGION")
    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

@Column(name = "ID_PAIS")
    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

@Column(name = "ID_PROVINCIA")
    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

@Column(name = "ID_CUIDAD")
    public Integer getIdCuidad() {
        return idCuidad;
    }

    public void setIdCuidad(Integer idCuidad) {
        this.idCuidad = idCuidad;
    }

    @ManyToMany(mappedBy = "organismoList")
    public List<ProyectoGenerico> getProyectoGenericoList() {
        return proyectoGenericoList;
    }

    public void setProyectoGenericoList(List<ProyectoGenerico> proyectoGenericoList) {
        this.proyectoGenericoList = proyectoGenericoList;
    }

@OneToMany(mappedBy = "idOrganismo")
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

@JoinColumn(name = "ID_TIPO_ORGANISMO", referencedColumnName = "ID_TIPO_ORGANISMO")
    @ManyToOne
    public TipoOrganismo getIdTipoOrganismo() {
        return idTipoOrganismo;
    }

    public void setIdTipoOrganismo(TipoOrganismo idTipoOrganismo) {
        this.idTipoOrganismo = idTipoOrganismo;
    }

@OneToMany(mappedBy = "idOrganismo")
    public List<Telefono> getTelefonoList() {
        return telefonoList;
    }

    public void setTelefonoList(List<Telefono> telefonoList) {
        this.telefonoList = telefonoList;
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
