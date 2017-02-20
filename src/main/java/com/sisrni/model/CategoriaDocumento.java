/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "CATEGORIA_DOCUMENTO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "CategoriaDocumento.findAll", query = "SELECT c FROM CategoriaDocumento c")})
public class CategoriaDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CATEGORIA_DOC", nullable = false)
    private Integer idCategoriaDoc;
    @Size(max = 25)
    @Column(name = "NOMBRE_CATEGORIA", length = 25)
    private String nombreCategoria;
    @OneToMany(mappedBy = "idCategoriaDoc")
    private List<TipoDocumento> tipoDocumentoList;

    public CategoriaDocumento() {
    }

    public CategoriaDocumento(Integer idCategoriaDoc) {
        this.idCategoriaDoc = idCategoriaDoc;
    }

    public Integer getIdCategoriaDoc() {
        return idCategoriaDoc;
    }

    public void setIdCategoriaDoc(Integer idCategoriaDoc) {
        this.idCategoriaDoc = idCategoriaDoc;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public List<TipoDocumento> getTipoDocumentoList() {
        return tipoDocumentoList;
    }

    public void setTipoDocumentoList(List<TipoDocumento> tipoDocumentoList) {
        this.tipoDocumentoList = tipoDocumentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriaDoc != null ? idCategoriaDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaDocumento)) {
            return false;
        }
        CategoriaDocumento other = (CategoriaDocumento) object;
        if ((this.idCategoriaDoc == null && other.idCategoriaDoc != null) || (this.idCategoriaDoc != null && !this.idCategoriaDoc.equals(other.idCategoriaDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.CategoriaDocumento[ idCategoriaDoc=" + idCategoriaDoc + " ]";
    }
    
}
