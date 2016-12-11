/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cortez
 */
@Component(value = "estadoNoticiaConverter")
@FacesConverter("estadoNoticiaConverter")
public class EstadoNoticiaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        boolean di = ((Boolean) value).booleanValue();
        String estado = "";
        if (di == true) {
            estado = "Activa";
        }
        if (di == false) {
            estado = "Inactiva";
        }
        return estado;
    }

}
