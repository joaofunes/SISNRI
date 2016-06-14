///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.dimesa.model;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Set;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
///**
// *
// * @author Angel
// */
//@Entity
//@Table(catalog = "siapa", schema = "")
//@NamedQueries({
//    @NamedQuery(name = "Alimento.findAll", query = "SELECT a FROM Alimento a")})
//public class Alimento implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue
//    @Column(name = "ID_ALIMENTO", nullable = true)
//    private Integer idAlimento;
//    @Column(name = "MARCA_ALIMENTO", length = 50)
//    private String marcaAlimento;
//    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
//    @Basic(optional = false)
//    @Column(name = "EXISTENCIA_ALIMENTO", nullable = false, precision = 10, scale = 2)
//    private BigDecimal existenciaAlimento;
//    @OneToMany(mappedBy = "idAlimento", fetch = FetchType.LAZY)
//    private Set<DetalleCompraAlimento> detalleCompraAlimentoSet;
//    @JoinColumn(name = "ID_TIPO_ALIMENTO", referencedColumnName = "ID_TIPO_ALIMENTO")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private TipoAlimento idTipoAlimento;
//    @OneToMany(mappedBy = "idAlimento", fetch = FetchType.LAZY)
//    private Set<RegistroAlimentacion> registroAlimentacionSet;
//
//    public Alimento() {
//    }
//
//    public Alimento(Integer idAlimento) {
//        this.idAlimento = idAlimento;
//    }
//
//    public Alimento(Integer idAlimento, BigDecimal existenciaAlimento) {
//        this.idAlimento = idAlimento;
//        this.existenciaAlimento = existenciaAlimento;
//    }
//
//    public Integer getIdAlimento() {
//        return idAlimento;
//    }
//
//    public void setIdAlimento(Integer idAlimento) {
//        this.idAlimento = idAlimento;
//    }
//
//    public String getMarcaAlimento() {
//        return marcaAlimento;
//    }
//
//    public void setMarcaAlimento(String marcaAlimento) {
//        this.marcaAlimento = marcaAlimento;
//    }
//
//    public BigDecimal getExistenciaAlimento() {
//        return existenciaAlimento;
//    }
//
//    public void setExistenciaAlimento(BigDecimal existenciaAlimento) {
//        this.existenciaAlimento = existenciaAlimento;
//    }
//
//    public Set<DetalleCompraAlimento> getDetalleCompraAlimentoSet() {
//        return detalleCompraAlimentoSet;
//    }
//
//    public void setDetalleCompraAlimentoSet(Set<DetalleCompraAlimento> detalleCompraAlimentoSet) {
//        this.detalleCompraAlimentoSet = detalleCompraAlimentoSet;
//    }
//
//    public TipoAlimento getIdTipoAlimento() {
//        return idTipoAlimento;
//    }
//
//    public void setIdTipoAlimento(TipoAlimento idTipoAlimento) {
//        this.idTipoAlimento = idTipoAlimento;
//    }
//
//    public Set<RegistroAlimentacion> getRegistroAlimentacionSet() {
//        return registroAlimentacionSet;
//    }
//
//    public void setRegistroAlimentacionSet(Set<RegistroAlimentacion> registroAlimentacionSet) {
//        this.registroAlimentacionSet = registroAlimentacionSet;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (idAlimento != null ? idAlimento.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Alimento)) {
//            return false;
//        }
//        Alimento other = (Alimento) object;
//        if ((this.idAlimento == null && other.idAlimento != null) || (this.idAlimento != null && !this.idAlimento.equals(other.idAlimento))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.siapa.model.Alimento[ idAlimento=" + idAlimento + " ]";
//    }
//
//}
