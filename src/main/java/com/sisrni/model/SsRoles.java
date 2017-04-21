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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "ss_roles", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "SsRoles.findAll", query = "SELECT s FROM SsRoles s")})
public class SsRoles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ROL", nullable = false)
    private Integer idRol;
    @Size(max = 50)
    @Column(name = "CODIGO_ROL", length = 50)
    private String codigoRol;
    @Size(max = 50)
    @Column(name = "NOMBRE_ROL", length = 50)
    private String nombreRol;
    @Size(max = 150)
    @Column(name = "DESCRIPCION", length = 150)
    private String descripcion;
    @Size(max = 15)
    @Column(name = "USUARIO_REGISTRO", length = 60)
    private String usuarioRegistro;
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Size(max = 15)
    @Column(name = "USUARIO_ULTIMAMODIFICACION", length = 15)
    private String usuarioUltimamodificacion;
    @Column(name = "FECHA_ULTIMAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimamodificacion;
    @ManyToMany(mappedBy = "ssRolesList")
    private List<SsMenus> ssMenusList;
    @JoinTable(name = "ss_roles_usuarios", joinColumns = {
        @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)})
    @ManyToMany
    private List<SsUsuarios> ssUsuariosList;
    @JoinTable(name = "ss_roles_opciones", joinColumns = {
        @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ID_OPCION", referencedColumnName = "ID_OPCION", nullable = false)})
    @ManyToMany
    private List<SsOpciones> ssOpcionesList;

    public SsRoles() {
    }

    public SsRoles(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUsuarioUltimamodificacion() {
        return usuarioUltimamodificacion;
    }

    public void setUsuarioUltimamodificacion(String usuarioUltimamodificacion) {
        this.usuarioUltimamodificacion = usuarioUltimamodificacion;
    }

    public Date getFechaUltimamodificacion() {
        return fechaUltimamodificacion;
    }

    public void setFechaUltimamodificacion(Date fechaUltimamodificacion) {
        this.fechaUltimamodificacion = fechaUltimamodificacion;
    }

    public List<SsMenus> getSsMenusList() {
        return ssMenusList;
    }

    public void setSsMenusList(List<SsMenus> ssMenusList) {
        this.ssMenusList = ssMenusList;
    }

    public List<SsUsuarios> getSsUsuariosList() {
        return ssUsuariosList;
    }

    public void setSsUsuariosList(List<SsUsuarios> ssUsuariosList) {
        this.ssUsuariosList = ssUsuariosList;
    }

    public List<SsOpciones> getSsOpcionesList() {
        return ssOpcionesList;
    }

    public void setSsOpcionesList(List<SsOpciones> ssOpcionesList) {
        this.ssOpcionesList = ssOpcionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRol != null ? idRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SsRoles)) {
            return false;
        }
        SsRoles other = (SsRoles) object;
        if ((this.idRol == null && other.idRol != null) || (this.idRol != null && !this.idRol.equals(other.idRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.SsRoles[ idRol=" + idRol + " ]";
    }
    
}
