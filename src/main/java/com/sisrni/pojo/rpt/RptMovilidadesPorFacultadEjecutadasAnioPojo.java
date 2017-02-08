package com.sisrni.pojo.rpt;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class RptMovilidadesPorFacultadEjecutadasAnioPojo implements Serializable{
    private String nombreFacultad;
    private Integer cantidad;
    
    //GETTER Y SETTER

    public String getNombreFacultad() {
        return nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
}
