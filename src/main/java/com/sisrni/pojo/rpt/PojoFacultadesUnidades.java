/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.pojo.rpt;

import java.io.Serializable;

/**
 *
 * @author Lillian
 */
public class PojoFacultadesUnidades implements Serializable {
    
    
    
    private Integer id;
    private Integer primary;
    private String value;
    private String label;
    private char unidadFacultad;
    

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public char getUnidadFacultad() {
        return unidadFacultad;
    }

    public void setUnidadFacultad(char unidadFacultad) {
        this.unidadFacultad = unidadFacultad;
    }

    public Integer getPrimary() {
        return primary;
    }

    public void setPrimary(Integer primary) {
        this.primary = primary;
    }

    

}
