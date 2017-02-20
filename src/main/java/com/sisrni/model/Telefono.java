/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Cortez
 */
@Entity
@Table(name = "TELEFONO", catalog = "sisrni", schema = "")
@NamedQueries({
    @NamedQuery(name = "Telefono.findAll", query = "SELECT t FROM Telefono t")})
public class Telefono implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TELEFENO", nullable = false)
    private Integer idTelefeno;
    @Size(max = 20)
    @Column(name = "NUMERO_TELEFONO", length = 20)
    private String numeroTelefono;
    @JoinColumn(name = "ID_TIPO_TELEFONO", referencedColumnName = "ID_TIPO_TELEFONO")
    @ManyToOne
    private TipoTelefono idTipoTelefono;
    @JoinColumn(name = "ID_ORGANISMO", referencedColumnName = "ID_ORGANISMO")
    @ManyToOne
    private Organismo idOrganismo;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA")
    @ManyToOne
    private Persona idPersona;

    public Telefono() {
    }

    public Telefono(Integer idTelefeno) {
        this.idTelefeno = idTelefeno;
    }

    public Integer getIdTelefeno() {
        return idTelefeno;
    }

    public void setIdTelefeno(Integer idTelefeno) {
        this.idTelefeno = idTelefeno;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public TipoTelefono getIdTipoTelefono() {
        return idTipoTelefono;
    }

    public void setIdTipoTelefono(TipoTelefono idTipoTelefono) {
        this.idTipoTelefono = idTipoTelefono;
    }

    public Organismo getIdOrganismo() {
        return idOrganismo;
    }

    public void setIdOrganismo(Organismo idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTelefeno != null ? idTelefeno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefono)) {
            return false;
        }
        Telefono other = (Telefono) object;
        if ((this.idTelefeno == null && other.idTelefeno != null) || (this.idTelefeno != null && !this.idTelefeno.equals(other.idTelefeno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisrni.model.Telefono[ idTelefeno=" + idTelefeno + " ]";
    }
    
}
