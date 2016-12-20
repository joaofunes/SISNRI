/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "PROYECTO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p")})
public class Proyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROYECTO", nullable = false)
    private Integer idProyecto;
    @Size(max = 300)
    @Column(name = "NOMBRE_PROYECTO", length = 300)
    private String nombreProyecto;
    @Size(max = 300)
    @Column(name = "OBJETIVO", length = 300)
    private String objetivo;
    @Size(max = 300)
    @Column(name = "LUGAR_PROYECTO", length = 300)
    private String lugarProyecto;
    @Column(name = "MONTO_PROYECTO")
    private Integer montoProyecto;
    @Column(name = "ANIO_GESTION")
    private Integer anioGestion;
    @Column(name = "ID_UNIDAD")
    private Integer idUnidad;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @ManyToMany(mappedBy = "proyectoList")
    private List<AreaConocimiento> areaConocimientoList;
    @ManyToMany(mappedBy = "proyectoList")
    private List<Estado> estadoList;
    @ManyToMany(mappedBy = "proyectoList")
    private List<Organismo> organismoList;
    @OneToMany(mappedBy = "idProyecto")
    private List<Documento> documentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private List<FacultadProyecto> facultadProyectoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private List<PersonaProyecto> personaProyectoList;
    @JoinColumn(name = "ID_PROPUESTA_CONVENIO", referencedColumnName = "ID_PROPUESTA", nullable = false)
    @ManyToOne(optional = false)
    private PropuestaConvenio idPropuestaConvenio;
    @JoinColumn(name = "ID_TIPO_PROYECTO", referencedColumnName = "ID_TIPO_PROYECTO")
    @ManyToOne
    private TipoProyecto idTipoProyecto;
    @JoinColumn(name = "ID_PAIS_COOPERANTE", referencedColumnName = "ID_PAIS")
    @ManyToOne
    private Pais idPaisCooperante;

    public Proyecto() {
    }

    public Proyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getLugarProyecto() {
        return lugarProyecto;
    }

    public void setLugarProyecto(String lugarProyecto) {
        this.lugarProyecto = lugarProyecto;
    }

    public Integer getMontoProyecto() {
        return montoProyecto;
    }

    public void setMontoProyecto(Integer montoProyecto) {
        this.montoProyecto = montoProyecto;
    }

    public Integer getAnioGestion() {
        return anioGestion;
    }

    public void setAnioGestion(Integer anioGestion) {
        this.anioGestion = anioGestion;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<AreaConocimiento> getAreaConocimientoList() {
        return areaConocimientoList;
    }

    public void setAreaConocimientoList(List<AreaConocimiento> areaConocimientoList) {
        this.areaConocimientoList = areaConocimientoList;
    }

    public List<Estado> getEstadoList() {
        return estadoList;
    }

    public void setEstadoList(List<Estado> estadoList) {
        this.estadoList = estadoList;
    }

    public List<Organismo> getOrganismoList() {
        return organismoList;
    }

    public void setOrganismoList(List<Organismo> organismoList) {
        this.organismoList = organismoList;
    }

    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    public List<FacultadProyecto> getFacultadProyectoList() {
        return facultadProyectoList;
    }

    public void setFacultadProyectoList(List<FacultadProyecto> facultadProyectoList) {
        this.facultadProyectoList = facultadProyectoList;
    }

    public List<PersonaProyecto> getPersonaProyectoList() {
        return personaProyectoList;
    }

    public void setPersonaProyectoList(List<PersonaProyecto> personaProyectoList) {
        this.personaProyectoList = personaProyectoList;
    }

    public PropuestaConvenio getIdPropuestaConvenio() {
        return idPropuestaConvenio;
    }

    public void setIdPropuestaConvenio(PropuestaConvenio idPropuestaConvenio) {
        this.idPropuestaConvenio = idPropuestaConvenio;
    }

    public TipoProyecto getIdTipoProyecto() {
        return idTipoProyecto;
    }

    public void setIdTipoProyecto(TipoProyecto idTipoProyecto) {
        this.idTipoProyecto = idTipoProyecto;
    }

    public Pais getIdPaisCooperante() {
        return idPaisCooperante;
    }

    public void setIdPaisCooperante(Pais idPaisCooperante) {
        this.idPaisCooperante = idPaisCooperante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.Proyecto[ idProyecto=" + idProyecto + " ]";
    }
    
}
