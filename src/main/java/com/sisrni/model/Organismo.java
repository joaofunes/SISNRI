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
@Table(name = "ORGANISMO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "Organismo.findAll", query = "SELECT o FROM Organismo o")})
public class Organismo implements Serializable {

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
    @OneToMany(mappedBy = "idOrganismo")
    private List<Propuesta> propuestaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ORGANISMO", nullable = false)
    private Integer idOrganismo;
    @Size(max = 100)
    @Column(name = "NOMBRE_ORGANISMO", length = 100)
    private String nombreOrganismo;
    @Size(max = 60)
    @Column(name = "TELEFONO_ORGANISMO", length = 60)
    private String telefonoOrganismo;
    @Size(max = 60)
    @Column(name = "FAX_ORANISMO", length = 60)
    private String faxOranismo;
    @Size(max = 60)
    @Column(name = "CORREO_ORGANISMO", length = 60)
    private String correoOrganismo;
    @Size(max = 100)
    @Column(name = "DIRECCION_ORGANISMO", length = 100)
    private String direccionOrganismo;
    @OneToMany(mappedBy = "idOrganismo")
    private List<Persona> personaList;
    @JoinColumn(name = "ID_TIPO_ORGANISMO", referencedColumnName = "ID_TIPO_ORGANISMO")
    @ManyToOne
    private TipoOrganismo idTipoOrganismo;
    @JoinColumn(name = "ID_UBICACION", referencedColumnName = "ID_UBICACION")
    @ManyToOne
    private Ubicacion idUbicacion;

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

    public String getTelefonoOrganismo() {
        return telefonoOrganismo;
    }

    public void setTelefonoOrganismo(String telefonoOrganismo) {
        this.telefonoOrganismo = telefonoOrganismo;
    }

    public String getFaxOranismo() {
        return faxOranismo;
    }

    public void setFaxOranismo(String faxOranismo) {
        this.faxOranismo = faxOranismo;
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

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    public TipoOrganismo getIdTipoOrganismo() {
        return idTipoOrganismo;
    }

    public void setIdTipoOrganismo(TipoOrganismo idTipoOrganismo) {
        this.idTipoOrganismo = idTipoOrganismo;
    }

    public Ubicacion getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Ubicacion idUbicacion) {
        this.idUbicacion = idUbicacion;
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

    public List<Propuesta> getPropuestaList() {
        return propuestaList;
    }

    public void setPropuestaList(List<Propuesta> propuestaList) {
        this.propuestaList = propuestaList;
    }

}
