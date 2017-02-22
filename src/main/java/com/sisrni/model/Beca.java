/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
 * @author Cortez
 */
@Entity
@Table(name = "BECA", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "Beca.findAll", query = "SELECT b FROM Beca b")})
public class Beca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_BECA", nullable = false)
    private Integer idBeca;
    @Size(max = 200)
    @Column(name = "NOMBRE_BECA", length = 200)
    private String nombreBeca;
    @Column(name = "ID_PAIS_COOPERANTE")
    private Integer idPaisCooperante;
    @Column(name = "ID_PAIS_DESTINO")
    private Integer idPaisDestino;
    @Column(name = "ANIO_GESTION")
    private Integer anioGestion;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTO_INTERNO", precision = 13, scale = 2)
    private BigDecimal montoInterno;
    @Column(name = "MONTO_EXTERNO", precision = 13, scale = 2)
    private BigDecimal montoExterno;
    @Column(name = "MONTO_TOTAL", precision = 13, scale = 2)
    private BigDecimal montoTotal;
    @Column(name = "OTORGADA")
    private Boolean otorgada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @OneToMany(mappedBy = "idBeca")
    private List<Documento> documentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beca")
    private List<PersonaBeca> personaBecaList=new ArrayList<PersonaBeca>();
    @JoinColumn(name = "ID_UNIVERSIDAD", referencedColumnName = "ID_ORGANISMO")
    @ManyToOne
    private Organismo idUniversidad;
    @JoinColumn(name = "ID_ORGANISMO_COOPERANTE", referencedColumnName = "ID_ORGANISMO")
    @ManyToOne
    private Organismo idOrganismoCooperante;
    @JoinColumn(name = "ID_PROGRAMA_BECA", referencedColumnName = "ID_PROGRAMA")
    @ManyToOne
    private ProgramaBeca idProgramaBeca;
    @JoinColumn(name = "ID_TIPO_BECA", referencedColumnName = "ID_TIPO_BECA")
    @ManyToOne
    private TipoBeca idTipoBeca;
    @JoinColumn(name = "ID_TIPO_MODALIDAD", referencedColumnName = "ID_TIPO_MODALIDAD")
    @ManyToOne
    private TipoModalidaBeca idTipoModalidad;

    public Beca() {
    }

    public Beca(Integer idBeca) {
        this.idBeca = idBeca;
    }

    public Beca(Integer idBeca, Date fechaIngreso) {
        this.idBeca = idBeca;
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getIdBeca() {
        return idBeca;
    }

    public void setIdBeca(Integer idBeca) {
        this.idBeca = idBeca;
    }

    public String getNombreBeca() {
        return nombreBeca;
    }

    public void setNombreBeca(String nombreBeca) {
        this.nombreBeca = nombreBeca;
    }

    public Integer getIdPaisCooperante() {
        return idPaisCooperante;
    }

    public void setIdPaisCooperante(Integer idPaisCooperante) {
        this.idPaisCooperante = idPaisCooperante;
    }

    public Integer getIdPaisDestino() {
        return idPaisDestino;
    }

    public void setIdPaisDestino(Integer idPaisDestino) {
        this.idPaisDestino = idPaisDestino;
    }

    public Integer getAnioGestion() {
        return anioGestion;
    }

    public void setAnioGestion(Integer anioGestion) {
        this.anioGestion = anioGestion;
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

    public BigDecimal getMontoInterno() {
        return montoInterno;
    }

    public void setMontoInterno(BigDecimal montoInterno) {
        this.montoInterno = montoInterno;
    }

    public BigDecimal getMontoExterno() {
        return montoExterno;
    }

    public void setMontoExterno(BigDecimal montoExterno) {
        this.montoExterno = montoExterno;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Boolean getOtorgada() {
        return otorgada;
    }

    public void setOtorgada(Boolean otorgada) {
        this.otorgada = otorgada;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    public List<PersonaBeca> getPersonaBecaList() {
        return personaBecaList;
    }

    public void setPersonaBecaList(List<PersonaBeca> personaBecaList) {
        this.personaBecaList = personaBecaList;
    }

    public Organismo getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(Organismo idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public Organismo getIdOrganismoCooperante() {
        return idOrganismoCooperante;
    }

    public void setIdOrganismoCooperante(Organismo idOrganismoCooperante) {
        this.idOrganismoCooperante = idOrganismoCooperante;
    }

    public ProgramaBeca getIdProgramaBeca() {
        return idProgramaBeca;
    }

    public void setIdProgramaBeca(ProgramaBeca idProgramaBeca) {
        this.idProgramaBeca = idProgramaBeca;
    }

    public TipoBeca getIdTipoBeca() {
        return idTipoBeca;
    }

    public void setIdTipoBeca(TipoBeca idTipoBeca) {
        this.idTipoBeca = idTipoBeca;
    }

    public TipoModalidaBeca getIdTipoModalidad() {
        return idTipoModalidad;
    }

    public void setIdTipoModalidad(TipoModalidaBeca idTipoModalidad) {
        this.idTipoModalidad = idTipoModalidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBeca != null ? idBeca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Beca)) {
            return false;
        }
        Beca other = (Beca) object;
        if ((this.idBeca == null && other.idBeca != null) || (this.idBeca != null && !this.idBeca.equals(other.idBeca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.Beca[ idBeca=" + idBeca + " ]";
    }
    
}
