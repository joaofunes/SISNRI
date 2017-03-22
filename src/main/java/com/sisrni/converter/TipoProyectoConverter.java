/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.converter;

import com.sisrni.model.TipoProyecto;
import com.sisrni.service.TipoProyectoService;
import com.sisrni.utils.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lillian
 */

@Component(value = "tipoProyectoConverter")
public class TipoProyectoConverter implements Converter {
    
    @Autowired
    @Qualifier(value = "tipoProyectoService")
    private TipoProyectoService tipoProyectoService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        value = String.valueOf(value);

        if (JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        if (isNumeric(value)) {
            if (value != null && value.trim().length() > 0 && !value.equalsIgnoreCase("null")) {
                try {
                    Integer key = getKey(value);
                    TipoProyecto findById = tipoProyectoService.findById(key);

                    return findById;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
                }
            }
        } else {
            return new TipoProyecto();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof TipoProyecto) {
            TipoProyecto o = (TipoProyecto) object;
            return getStringKey(o.getIdTipoProyecto());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoProyecto.class.getName()});
            return null;
        }
    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Integer value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    public static boolean isNumeric(String str) {
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && str.equals("") == false);
    }

    
}
