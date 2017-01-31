package com.sisrni.pojo.rpt;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class RptMovilidadesSegunEtapaPojo implements Serializable {
    
    private String nombreMovilidad;
    private Integer cantidad;

    public String getNombreMovilidad() {
        return nombreMovilidad;
    }

    public void setNombreMovilidad(String nombreMovilidad) {
        this.nombreMovilidad = nombreMovilidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    

   
    
    
}
