/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dimesa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Angel
 */
@Entity
@Table(name = "ss_usuarios", catalog = "dimesa", schema = "")
@NamedQueries({
    @NamedQuery(name = "SsUsuarios.findAll", query = "SELECT s FROM SsUsuarios s")})
public class SsUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_USUARIO", nullable = false)
    private Integer idUsuario;
    @Column(name = "CODIGO_USUARIO", length = 15)
    private String codigoUsuario;
    @Column(name = "NOMBRE_USUARIO", length = 100)
    private String nombreUsuario;
    @Column(length = 20)
    private String telefono;
    @Column(length = 100)
    private String email;
    @Column(length = 100)
    private String cargo;
    @Column(length = 150)
    private String descripcion;
    @Column(length = 1)
    private String bloqueado;
    @Column(length = 100)
    private String clave;
    @Column(name = "INTENTOS_ACCESO_FALLIDOS")
    private Short intentosAccesoFallidos;
    @Column(name = "USUARIO_REGISTRO", length = 15)
    private String usuarioRegistro;
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "USUARIO_ULTIMAMODIFICACION", length = 15)
    private String usuarioUltimamodificacion;
    @Column(name = "FECHA_ULTIMAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimamodificacion;
    @Column(name = "FECHA_ULTIMO_ACCESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimoAcceso;
    @Column(name = "DIRECCION_ACCESO", length = 100)
    private String direccionAcceso;
    @Column(name = "DETALLE_ULTIMO_ACCESO", length = 300)
    private String detalleUltimoAcceso;
    @Column(name = "FECHA_CAMBIO_CLAVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambioClave;
    @Column(name = "CODIGO_SUCURSAL", length = 2)
    private String codigoSucursal;
    @ManyToMany(mappedBy = "ssUsuariosSet", fetch = FetchType.LAZY)
    private Set<SsRoles> ssRolesSet;
    @OneToMany(mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private Set<SsHistoricoClaves> ssHistoricoClavesSet;

    public SsUsuarios() {
    }

    public SsUsuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(String bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Short getIntentosAccesoFallidos() {
        return intentosAccesoFallidos;
    }

    public void setIntentosAccesoFallidos(Short intentosAccesoFallidos) {
        this.intentosAccesoFallidos = intentosAccesoFallidos;
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

    public Date getFechaUltimoAcceso() {
        return fechaUltimoAcceso;
    }

    public void setFechaUltimoAcceso(Date fechaUltimoAcceso) {
        this.fechaUltimoAcceso = fechaUltimoAcceso;
    }

    public String getDireccionAcceso() {
        return direccionAcceso;
    }

    public void setDireccionAcceso(String direccionAcceso) {
        this.direccionAcceso = direccionAcceso;
    }

    public String getDetalleUltimoAcceso() {
        return detalleUltimoAcceso;
    }

    public void setDetalleUltimoAcceso(String detalleUltimoAcceso) {
        this.detalleUltimoAcceso = detalleUltimoAcceso;
    }

    public Date getFechaCambioClave() {
        return fechaCambioClave;
    }

    public void setFechaCambioClave(Date fechaCambioClave) {
        this.fechaCambioClave = fechaCambioClave;
    }

    public String getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(String codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public Set<SsRoles> getSsRolesSet() {
        return ssRolesSet;
    }

    public void setSsRolesSet(Set<SsRoles> ssRolesSet) {
        this.ssRolesSet = ssRolesSet;
    }

    public Set<SsHistoricoClaves> getSsHistoricoClavesSet() {
        return ssHistoricoClavesSet;
    }

    public void setSsHistoricoClavesSet(Set<SsHistoricoClaves> ssHistoricoClavesSet) {
        this.ssHistoricoClavesSet = ssHistoricoClavesSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SsUsuarios)) {
            return false;
        }
        SsUsuarios other = (SsUsuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dimesa.model.SsUsuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}
