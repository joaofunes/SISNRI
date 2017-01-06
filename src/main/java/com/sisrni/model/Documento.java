/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "DOCUMENTO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d")})
public class Documento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DOCUMENTO", nullable = false)
    private Integer idDocumento;
    @Column(name = "FECHA_RECIBIDO")
    @Temporal(TemporalType.DATE)
    private Date fechaRecibido;
    @Lob
    @Column(name = "DOCUMENTO")
    private byte[] documento;
    @Size(max = 300)
    @Column(name = "NOMBRE_DOCUMENTO", length = 300)
    private String nombreDocumento;
    @Size(max = 30)
    @Column(name = "USUARIO_RECIBE", length = 30)
    private String usuarioRecibe;
    @JoinColumn(name = "ID_BECA", referencedColumnName = "ID_BECA")
    @ManyToOne
    private Beca idBeca;
    @JoinColumn(name = "ID_MOVILIDAD", referencedColumnName = "ID_MOVILIDAD")
    @ManyToOne
    private Movilidad idMovilidad;
    @JoinColumn(name = "ID_TIPO_DOCUMENTO", referencedColumnName = "ID_TIPO_DOCUMENTO")
    @ManyToOne
    private TipoDocumento idTipoDocumento;
    @JoinColumn(name = "ID_PROPUESTA", referencedColumnName = "ID_PROPUESTA")
    @ManyToOne
    private PropuestaConvenio idPropuesta;
    @JoinColumn(name = "ID_PROYECTO", referencedColumnName = "ID_PROYECTO")
    @ManyToOne
    private Proyecto idProyecto;

    public Documento() {
    }

    public Documento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getUsuarioRecibe() {
        return usuarioRecibe;
    }

    public void setUsuarioRecibe(String usuarioRecibe) {
        this.usuarioRecibe = usuarioRecibe;
    }

    public Beca getIdBeca() {
        return idBeca;
    }

    public void setIdBeca(Beca idBeca) {
        this.idBeca = idBeca;
    }

    public Movilidad getIdMovilidad() {
        return idMovilidad;
    }

    public void setIdMovilidad(Movilidad idMovilidad) {
        this.idMovilidad = idMovilidad;
    }

    public TipoDocumento getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(TipoDocumento idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public PropuestaConvenio getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(PropuestaConvenio idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumento != null ? idDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.idDocumento == null && other.idDocumento != null) || (this.idDocumento != null && !this.idDocumento.equals(other.idDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.Documento[ idDocumento=" + idDocumento + " ]";
    }
    
}
