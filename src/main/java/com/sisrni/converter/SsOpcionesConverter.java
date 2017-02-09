
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.converter;


import com.sisrni.model.SsOpciones;
import com.sisrni.service.SsOpcionesService;
import com.sisrni.utils.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Joao
 */

@Component(value = "ssOpcionesConverter")
public class SsOpcionesConverter implements Converter{
  
    @Autowired
    private SsOpcionesService ssOpcionesService;
        

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

    
    
     @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        value=String.valueOf(value);
        
         if(JsfUtil.isDummySelectItem(component, value)){
                     return null;
         }
          
        if(value != null && value.trim().length() > 0 && !value.equalsIgnoreCase("null")) {
            try {
                return  this.ssOpcionesService.findById(getKey(value));                                
            } catch(NumberFormatException e) {
                e.printStackTrace();
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ssOpcionesConverter Error", "Not a valid theme."));
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
        if (object instanceof SsOpciones) {
            SsOpciones o = (SsOpciones) object;
            return getStringKey(o.getIdOpcion());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SsOpciones.class.getName()});
            return null;
        }
    }
    
    
    
  
    
}
