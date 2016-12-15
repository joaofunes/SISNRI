/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author Joao
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_MOVILIDAD", nullable = false, length = 100)
    private String nombreMovilidad;
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
    @Size(max = 300)
    @Column(name = "DESCRIPCION_MOVILIDAD", length = 300)
    private String descripcionMovilidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VIATICOS", precision = 13, scale = 2)
    private BigDecimal viaticos;
    @Size(max = 100)
    @Column(name = "OTROS_BENEFICIADOS", length = 100)
    private String otrosBeneficiados;
    @Size(max = 50)
    @Column(name = "ETAPA_MOVILIDAD", length = 50)
    private String etapaMovilidad;
    @Column(name = "ENTREGA_DE_INFORME")
    private Boolean entregaDeInforme;
    @JoinColumn(name = "ID_TIPO_MOVILIDAD", referencedColumnName = "ID_TIPO_MOVILIDAD")
    @ManyToOne
    private TipoMovilidad idTipoMovilidad;
    @JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID_CATEGORIA_MOVILIDAD")
    @ManyToOne
    private CategoriaMovilidad idCategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movilidad")
    private List<PersonaMovilidad> personaMovilidadList;

    public Movilidad() {
    }

    public Movilidad(Integer idMovilidad) {
        this.idMovilidad = idMovilidad;
    }

    public Movilidad(Integer idMovilidad, String nombreMovilidad) {
        this.idMovilidad = idMovilidad;
        this.nombreMovilidad = nombreMovilidad;
    }

    public Integer getIdMovilidad() {
        return idMovilidad;
    }

    public void setIdMovilidad(Integer idMovilidad) {
        this.idMovilidad = idMovilidad;
    }

    public String getNombreMovilidad() {
        return nombreMovilidad;
    }

    public void setNombreMovilidad(String nombreMovilidad) {
        this.nombreMovilidad = nombreMovilidad;
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

    public String getDescripcionMovilidad() {
        return descripcionMovilidad;
    }

    public void setDescripcionMovilidad(String descripcionMovilidad) {
        this.descripcionMovilidad = descripcionMovilidad;
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

    public String getEtapaMovilidad() {
        return etapaMovilidad;
    }

    public void setEtapaMovilidad(String etapaMovilidad) {
        this.etapaMovilidad = etapaMovilidad;
    }

    public Boolean getEntregaDeInforme() {
        return entregaDeInforme;
    }

    public void setEntregaDeInforme(Boolean entregaDeInforme) {
        this.entregaDeInforme = entregaDeInforme;
    }

    public TipoMovilidad getIdTipoMovilidad() {
        return idTipoMovilidad;
    }

    public void setIdTipoMovilidad(TipoMovilidad idTipoMovilidad) {
        this.idTipoMovilidad = idTipoMovilidad;
    }

    public CategoriaMovilidad getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CategoriaMovilidad idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<PersonaMovilidad> getPersonaMovilidadList() {
        return personaMovilidadList;
    }

    public void setPersonaMovilidadList(List<PersonaMovilidad> personaMovilidadList) {
        this.personaMovilidadList = personaMovilidadList;
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
