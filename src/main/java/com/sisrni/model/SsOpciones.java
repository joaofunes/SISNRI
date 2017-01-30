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
@Table(name = "ss_opciones", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "SsOpciones.findAll", query = "SELECT s FROM SsOpciones s")})
public class SsOpciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_OPCION", nullable = false)
    private Integer idOpcion;
    @Size(max = 100)
    @Column(name = "NOMBRE_OPCION", length = 100)
    private String nombreOpcion;
    @Size(max = 300)
    @Column(name = "URL", length = 300)
    private String url;
    @Size(max = 1)
    @Column(name = "VISIBLE", length = 1)
    private String visible;
    @Size(max = 15)
    @Column(name = "USUARIO_REGISTRO", length = 15)
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
    @Size(max = 45)
    @Column(name = "IMAGEN_OPCION", length = 45)
    private String imagenOpcion;
    @ManyToMany(mappedBy = "ssOpcionesList")
    private List<SsMenus> ssMenusList;
    @ManyToMany(mappedBy = "ssOpcionesList")
    private List<SsRoles> ssRolesList;

    public SsOpciones() {
    }

    public SsOpciones(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public Integer getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getNombreOpcion() {
        return nombreOpcion;
    }

    public void setNombreOpcion(String nombreOpcion) {
        this.nombreOpcion = nombreOpcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
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

    public String getImagenOpcion() {
        return imagenOpcion;
    }

    public void setImagenOpcion(String imagenOpcion) {
        this.imagenOpcion = imagenOpcion;
    }

    public List<SsMenus> getSsMenusList() {
        return ssMenusList;
    }

    public void setSsMenusList(List<SsMenus> ssMenusList) {
        this.ssMenusList = ssMenusList;
    }

    public List<SsRoles> getSsRolesList() {
        return ssRolesList;
    }

    public void setSsRolesList(List<SsRoles> ssRolesList) {
        this.ssRolesList = ssRolesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpcion != null ? idOpcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SsOpciones)) {
            return false;
        }
        SsOpciones other = (SsOpciones) object;
        if ((this.idOpcion == null && other.idOpcion != null) || (this.idOpcion != null && !this.idOpcion.equals(other.idOpcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.SsOpciones[ idOpcion=" + idOpcion + " ]";
    }
    
}
