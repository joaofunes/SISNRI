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
import javax.persistence.JoinTable;
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
@Table(name = "MOVILIDAD", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "Movilidad.findAll", query = "SELECT m FROM Movilidad m")})
public class Movilidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MOVILIDAD", nullable = false)
    private Integer idMovilidad;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "ID_PAIS_ORIGEN")
    private Integer idPaisOrigen;
    @Column(name = "ID_UNIVERSIDAD_ORIGEN")
    private Integer idUniversidadOrigen;
    @Column(name = "ID_PAIS_DESTINO")
    private Integer idPaisDestino;
    @Column(name = "ID_UNIVERSIDAD_DESTINO")
    private Integer idUniversidadDestino;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VIATICOS", precision = 13, scale = 2)
    private BigDecimal viaticos;
    @Size(max = 300)
    @Column(name = "OTROS_BENEFICIADOS", length = 300)
    private String otrosBeneficiados;
    @Column(name = "ENTREGA_DE_INFORME")
    private Boolean entregaDeInforme;
    @Column(name = "PAGO_DE_CURSO", precision = 13, scale = 2)
    private BigDecimal pagoDeCurso;
    @Column(name = "VOLETO_AEREO", precision = 13, scale = 2)
    private BigDecimal voletoAereo;
    @Column(name = "FECHA_ENTREGA_MINED")
    @Temporal(TemporalType.DATE)
    private Date fechaEntregaMined;
    @Column(name = "OBSEQUIO")
    private Boolean obsequio;
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Column(name = "COSTO_CONSULTORIA", precision = 13, scale = 2)
    private BigDecimal costoConsultoria;
    @JoinTable(name = "MOVILIDAD_UNIDAD", joinColumns = {
        @JoinColumn(name = "ID_MOVILIDAD", referencedColumnName = "ID_MOVILIDAD", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ID_UNIDAD", referencedColumnName = "ID_UNIDAD", nullable = false)})
    @ManyToMany
    private List<Unidad> unidadList;
    @JoinTable(name = "MOVILIDAD_FACULTAD", joinColumns = {
        @JoinColumn(name = "ID_MOVILIDAD", referencedColumnName = "ID_MOVILIDAD", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ID_FACULTAD", referencedColumnName = "ID_FACULTAD", nullable = false)})
    @ManyToMany
    private List<Facultad> facultadList;
    @OneToMany(mappedBy = "idMovilidad")
    private List<Documento> documentoList;
    @JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID_CATEGORIA_MOVILIDAD")
    @ManyToOne
    private CategoriaMovilidad idCategoria;
    @JoinColumn(name = "ID_ETAPA_MOVILIDAD", referencedColumnName = "ID_ETAPA")
    @ManyToOne
    private EtapaMovilidad idEtapaMovilidad;
    @JoinColumn(name = "ID_PROGRAMA_MOVILIDAD", referencedColumnName = "ID_PROGRAMA_MOVILIDAD")
    @ManyToOne
    private ProgramaMovilidad idProgramaMovilidad;
    @JoinColumn(name = "ID_TIPO_MOVILIDAD", referencedColumnName = "ID_TIPO_MOVILIDAD")
    @ManyToOne
    private TipoMovilidad idTipoMovilidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movilidad")
    private List<PersonaMovilidad> personaMovilidadList = new ArrayList<PersonaMovilidad>() ;

    public Movilidad() {
    }

    public Movilidad(Integer idMovilidad) {
        this.idMovilidad = idMovilidad;
    }

    public Integer getIdMovilidad() {
        return idMovilidad;
    }

    public void setIdMovilidad(Integer idMovilidad) {
        this.idMovilidad = idMovilidad;
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

    public Integer getIdPaisOrigen() {
        return idPaisOrigen;
    }

    public void setIdPaisOrigen(Integer idPaisOrigen) {
        this.idPaisOrigen = idPaisOrigen;
    }

    public Integer getIdUniversidadOrigen() {
        return idUniversidadOrigen;
    }

    public void setIdUniversidadOrigen(Integer idUniversidadOrigen) {
        this.idUniversidadOrigen = idUniversidadOrigen;
    }

    public Integer getIdPaisDestino() {
        return idPaisDestino;
    }

    public void setIdPaisDestino(Integer idPaisDestino) {
        this.idPaisDestino = idPaisDestino;
    }

    public Integer getIdUniversidadDestino() {
        return idUniversidadDestino;
    }

    public void setIdUniversidadDestino(Integer idUniversidadDestino) {
        this.idUniversidadDestino = idUniversidadDestino;
    }

    public BigDecimal getViaticos() {
        return viaticos;
    }

    public void setViaticos(BigDecimal viaticos) {
        this.viaticos = viaticos;
    }

    public String getOtrosBeneficiados() {
        return otrosBeneficiados;
    }

    public void setOtrosBeneficiados(String otrosBeneficiados) {
        this.otrosBeneficiados = otrosBeneficiados;
    }

    public Boolean getEntregaDeInforme() {
        return entregaDeInforme;
    }

    public void setEntregaDeInforme(Boolean entregaDeInforme) {
        this.entregaDeInforme = entregaDeInforme;
    }

    public BigDecimal getPagoDeCurso() {
        return pagoDeCurso;
    }

    public void setPagoDeCurso(BigDecimal pagoDeCurso) {
        this.pagoDeCurso = pagoDeCurso;
    }

    public BigDecimal getVoletoAereo() {
        return voletoAereo;
    }

    public void setVoletoAereo(BigDecimal voletoAereo) {
        this.voletoAereo = voletoAereo;
    }

    public Date getFechaEntregaMined() {
        return fechaEntregaMined;
    }

    public void setFechaEntregaMined(Date fechaEntregaMined) {
        this.fechaEntregaMined = fechaEntregaMined;
    }

    public Boolean getObsequio() {
        return obsequio;
    }

    public void setObsequio(Boolean obsequio) {
        this.obsequio = obsequio;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public List<Unidad> getUnidadList() {
        return unidadList;
    }

    public void setUnidadList(List<Unidad> unidadList) {
        this.unidadList = unidadList;
    }

    public List<Facultad> getFacultadList() {
        return facultadList;
    }

    public void setFacultadList(List<Facultad> facultadList) {
        this.facultadList = facultadList;
    }

    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    public CategoriaMovilidad getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CategoriaMovilidad idCategoria) {
        this.idCategoria = idCategoria;
    }

    public EtapaMovilidad getIdEtapaMovilidad() {
        return idEtapaMovilidad;
    }

    public void setIdEtapaMovilidad(EtapaMovilidad idEtapaMovilidad) {
        this.idEtapaMovilidad = idEtapaMovilidad;
    }

    public ProgramaMovilidad getIdProgramaMovilidad() {
        return idProgramaMovilidad;
    }

    public void setIdProgramaMovilidad(ProgramaMovilidad idProgramaMovilidad) {
        this.idProgramaMovilidad = idProgramaMovilidad;
    }

    public TipoMovilidad getIdTipoMovilidad() {
        return idTipoMovilidad;
    }

    public void setIdTipoMovilidad(TipoMovilidad idTipoMovilidad) {
        this.idTipoMovilidad = idTipoMovilidad;
    }

    public List<PersonaMovilidad> getPersonaMovilidadList() {
        return personaMovilidadList;
    }

    public void setPersonaMovilidadList(List<PersonaMovilidad> personaMovilidadList) {
        this.personaMovilidadList = personaMovilidadList;
    }

    public BigDecimal getCostoConsultoria() {
        return costoConsultoria;
    }

    public void setCostoConsultoria(BigDecimal costoConsultoria) {
        this.costoConsultoria = costoConsultoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovilidad != null ? idMovilidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movilidad)) {
            return false;
        }
        Movilidad other = (Movilidad) object;
        if ((this.idMovilidad == null && other.idMovilidad != null) || (this.idMovilidad != null && !this.idMovilidad.equals(other.idMovilidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.Movilidad[ idMovilidad=" + idMovilidad + " ]";
    }
    
}
