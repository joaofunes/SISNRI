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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Joao
 */
@Entity
@Table(name = "PROYECTO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p")})
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROYECTO", nullable = false)
    private Integer idProyecto;
    @Size(max = 300)
    @Column(name = "NOMBRE_PROYECTO", length = 300)
    private String nombreProyecto;
    @Size(max = 300)
    @Column(name = "LUGAR_PROYECTO", length = 300)
    private String lugarProyecto;
    @Column(name = "MONTO_PROYECTO")
    private Integer montoProyecto;
    @Size(max = 300)
    @Column(name = "OBJETIVO_PROYECTO", length = 300)
    private String objetivoProyecto;
    @Column(name = "FECHA_GESTION")
    @Temporal(TemporalType.DATE)
    private Date fechaGestion;
    @ManyToMany(mappedBy = "proyectoList")
    private List<Estado> estadoList;
    @OneToMany(mappedBy = "idProyecto")
    private List<Documento> documentoList;
    @JoinColumn(name = "ID_TIPO_PROYECTO", referencedColumnName = "ID_TIPO_PROYECTO")
    @ManyToOne
    private TipoProyecto idTipoProyecto;
    @JoinColumn(name = "ID_REFERENTE", referencedColumnName = "ID_PERSONA")
    @ManyToOne
    private Persona idReferente;
    @JoinColumn(name = "ID_ASISTENTE", referencedColumnName = "ID_PERSONA")
    @ManyToOne
    private Persona idAsistente;
    @JoinColumn(name = "ID_CORDINADOR", referencedColumnName = "ID_PERSONA")
    @ManyToOne
    private Persona idCordinador;
    @JoinColumn(name = "ID_CONVENIO", referencedColumnName = "ID_CONVENIO")
    @ManyToOne
    private Convenio idConvenio;

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

    public String getObjetivoProyecto() {
        return objetivoProyecto;
    }

    public void setObjetivoProyecto(String objetivoProyecto) {
        this.objetivoProyecto = objetivoProyecto;
    }

    public Date getFechaGestion() {
        return fechaGestion;
    }

    public void setFechaGestion(Date fechaGestion) {
        this.fechaGestion = fechaGestion;
    }

    public List<Estado> getEstadoList() {
        return estadoList;
    }

    public void setEstadoList(List<Estado> estadoList) {
        this.estadoList = estadoList;
    }

    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    public TipoProyecto getIdTipoProyecto() {
        return idTipoProyecto;
    }

    public void setIdTipoProyecto(TipoProyecto idTipoProyecto) {
        this.idTipoProyecto = idTipoProyecto;
    }

    public Persona getIdReferente() {
        return idReferente;
    }

    public void setIdReferente(Persona idReferente) {
        this.idReferente = idReferente;
    }

    public Persona getIdAsistente() {
        return idAsistente;
    }

    public void setIdAsistente(Persona idAsistente) {
        this.idAsistente = idAsistente;
    }

    public Persona getIdCordinador() {
        return idCordinador;
    }

    public void setIdCordinador(Persona idCordinador) {
        this.idCordinador = idCordinador;
    }

    public Convenio getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Convenio idConvenio) {
        this.idConvenio = idConvenio;
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
