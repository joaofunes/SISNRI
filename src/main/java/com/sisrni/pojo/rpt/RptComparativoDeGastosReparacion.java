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
public class RptComparativoDeGastosReparacion implements Serializable{
    
    private Double equipox;
    private Double equipoy;
    private String tipoReparacion;

    public Double getEquipox() {
        return equipox;
    }

    public void setEquipox(Double equipox) {
        this.equipox = equipox;
    }

    public Double getEquipoy() {
        return equipoy;
    }

    public void setEquipoy(Double equipoy) {
        this.equipoy = equipoy;
    }

    public String getTipoReparacion() {
        return tipoReparacion;
    }

    public void setTipoReparacion(String tipoReparacion) {
        this.tipoReparacion = tipoReparacion;
    }
    
    
    
    
    
}
