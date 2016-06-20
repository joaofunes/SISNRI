/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisrni.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 *
 * @author Angel
 */
@Entity
@Table(name = "ss_opciones", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "SsOpciones.findAll", query = "SELECT s FROM SsOpciones s")})
public class SsOpciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID_OPCION", nullable = false)
    private Integer idOpcion;
    @Column(name = "NOMBRE_OPCION", length = 100)
    private String nombreOpcion;
    @Column(length = 300)
    private String url;
    @Column(length = 1)
    private String visible;
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
    @Column(name = "IMAGEN_OPCION", length = 45)
    private String imagenOpcion;
    @ManyToMany(mappedBy = "ssOpcionesSet", fetch = FetchType.LAZY)
    private Set<SsMenus> ssMenusSet;
    @ManyToMany(mappedBy = "ssOpcionesSet", fetch = FetchType.LAZY)
    private Set<SsRoles> ssRolesSet;
    
 

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

    public boolean isVisible() {
        return "S".equals(visible);
    }

    public void setVisible(boolean visible) {
         this.visible = visible ? "S" : "N";
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

    public Set<SsMenus> getSsMenusSet() {
        return ssMenusSet;
    }

    public void setSsMenusSet(Set<SsMenus> ssMenusSet) {
        this.ssMenusSet = ssMenusSet;
    }

    public Set<SsRoles> getSsRolesSet() {
        return ssRolesSet;
    }

    public void setSsRolesSet(Set<SsRoles> ssRolesSet) {
        this.ssRolesSet = ssRolesSet;
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

//    public boolean isVisibleTemop() {
//        return getVisible().equals("S");        
//    }
//
////     if(getVisible().equals("1")){
////         return true;
////        }else{
////         return false;
////        }    
//    public void setVisibleTemop(boolean visibleTemop) {
//        this.visibleTemop = visibleTemop;
//        setVisible(this.visibleTemop ? "S" : "N");
//
//    }
    
    
}
