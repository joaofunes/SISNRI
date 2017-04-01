/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.pojo.rpt;

import java.math.BigDecimal;

/**
 *
 * @author Joao
 */
public class RptProyectoPojo{
    
    private String nombre;
    private String objetivo;
    private Integer anioGestion;
    private String orgamismo;
    private String paisCooperante;
    private String contraparteExterna;
    private String beneficiadoUES;
    private BigDecimal monto;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Integer getAnioGestion() {
        return anioGestion;
    }

    public void setAnioGestion(Integer anioGestion) {
        this.anioGestion = anioGestion;
    }

    public String getOrgamismo() {
        return orgamismo;
    }

    public void setOrgamismo(String orgamismo) {
        this.orgamismo = orgamismo;
    }

    public String getPaisCooperante() {
        return paisCooperante;
    }

    public void setPaisCooperante(String paisCooperante) {
        this.paisCooperante = paisCooperante;
    }

    public String getContraparteExterna() {
        return contraparteExterna;
    }

    public void setContraparteExterna(String contraparteExterna) {
        this.contraparteExterna = contraparteExterna;
    }

    public String getBeneficiadoUES() {
        return beneficiadoUES;
    }

    public void setBeneficiadoUES(String beneficiadoUES) {
        this.beneficiadoUES = beneficiadoUES;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    

}
