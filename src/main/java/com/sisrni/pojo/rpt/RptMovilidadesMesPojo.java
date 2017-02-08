package com.sisrni.pojo.rpt;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class RptMovilidadesMesPojo implements Serializable{
    private String nombreMes;
    private Integer cantidad;
    
    
   //Getter y Setter
    public String getNombreMes() {
        return nombreMes;
    }

    public void setNombreMes(String nombreMes) {
        this.nombreMes = nombreMes;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
