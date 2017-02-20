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
@Table(name = "CATEGORIA_NOTICIA", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "CategoriaNoticia.findAll", query = "SELECT c FROM CategoriaNoticia c")})
public class CategoriaNoticia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CATEGORIA", nullable = false)
    private Integer idCategoria;
    @Size(max = 100)
    @Column(name = "CATEGORIA_NOTICIA", length = 100)
    private String categoriaNoticia;
    @OneToMany(mappedBy = "idCategoria")
    private List<Noticia> noticiaList;

    public CategoriaNoticia() {
    }

    public CategoriaNoticia(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoriaNoticia() {
        return categoriaNoticia;
    }

    public void setCategoriaNoticia(String categoriaNoticia) {
        this.categoriaNoticia = categoriaNoticia;
    }

    public List<Noticia> getNoticiaList() {
        return noticiaList;
    }

    public void setNoticiaList(List<Noticia> noticiaList) {
        this.noticiaList = noticiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaNoticia)) {
            return false;
        }
        CategoriaNoticia other = (CategoriaNoticia) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.CategoriaNoticia[ idCategoria=" + idCategoria + " ]";
    }
    
}
