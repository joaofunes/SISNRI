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
@Table(name = "organismo", catalog = "srnibd", schema = "")
@NamedQueries({
    @NamedQuery(name = "Organismo.findAll", query = "SELECT o FROM Organismo o")})
public class Organismo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
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
    @JoinColumn(name = "ID_UBICACION", referencedColumnName = "ID_UBICACION")
    @ManyToOne
    private Ubicacion idUbicacion;
    @JoinColumn(name = "ID_TIPO_ORGANISMO", referencedColumnName = "ID_TIPO_ORGANISMO")
    @ManyToOne
    private TipoOrganismo idTipoOrganismo;
    @OneToMany(mappedBy = "idOrganismo")
    private List<Persona> personaList;

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

    public Ubicacion getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Ubicacion idUbicacion) {
        this.idUbicacion = idUbicacion;
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
