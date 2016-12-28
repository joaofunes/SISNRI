/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.converter;

import com.sisrni.model.EscuelaDepartamento;
import com.sisrni.model.Unidad;
import com.sisrni.service.UnidadService;
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
 * @author Joao
 */

@Component(value = "unidadConverter")
public class UnidadConverter implements Converter {
    
    @Autowired
    @Qualifier(value = "unidadService")
    private UnidadService unidadService;
    
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        value=String.valueOf(value);
        
         if(JsfUtil.isDummySelectItem(component, value)){
                     return null;
         }
          
        if(value != null && value.trim().length() > 0 && !value.equalsIgnoreCase("null")) {
            try {
                Integer key = getKey(value);
                Unidad findById = this.unidadService.findById(key);                 
               return findById;
            } catch(NumberFormatException e) {
                e.printStackTrace();
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            } 
        }
        else {
            return null;
        }
          
           
        
    }

   @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof EscuelaDepartamento) {
            EscuelaDepartamento o = (EscuelaDepartamento) object;
            return getStringKey(o.getIdEscuelaDepto());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EscuelaDepartamento.class.getName()});
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
    
}
