/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.pojo.rpt;

import java.io.Serializable;

/**
 *
 * @author Joao
 */
public class PojoPropuestaConvenio implements Serializable{
    
    
    private String nombreConvenio;
    private String solicitante;
    private String referenteInterno;
    private String referenteExterno;
    private String estado;

    public String getNombreConvenio() {
        return nombreConvenio;
    }

    public void setNombreConvenio(String nombreConvenio) {
        this.nombreConvenio = nombreConvenio;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getReferenteInterno() {
        return referenteInterno;
    }

    public void setReferenteInterno(String referenteInterno) {
        this.referenteInterno = referenteInterno;
    }

    public String getReferenteExterno() {
        return referenteExterno;
    }

    public void setReferenteExterno(String referenteExterno) {
        this.referenteExterno = referenteExterno;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
   
   
   
}
