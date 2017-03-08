package com.sisrni.converter;

import static com.sisrni.converter.EtapaMovilidadConverter.isNumeric;
import com.sisrni.model.EtapaMovilidad;
import com.sisrni.model.Organismo;
import com.sisrni.service.EtapaMovilidadService;
import com.sisrni.service.OrganismoService;
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
 * @author Usuario
 */
@Component(value = "institucionDocenteMovilidadConverter")
public class InstitucionDocenteMovilidadConverter implements Converter{
    
    @Autowired
    @Qualifier(value = "organismoService")
    private OrganismoService organismoService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
      value=String.valueOf(value);
        
      
         if(JsfUtil.isDummySelectItem(component, value)){
                     return null;
         }
        if (isNumeric(value)) {  
            if(value != null && value.trim().length() > 0 && !value.equalsIgnoreCase("null")) {
                try {
                    Integer key = getKey(value);
                    Organismo findById = this.organismoService.getByID(key); 

                   return findById;
                } catch(NumberFormatException e) {
                    e.printStackTrace();
                    throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
                }
            }
        }
        else {
            return null;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
         if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Organismo) {
            Organismo o = (Organismo) object;
            return getStringKey(o.getIdOrganismo());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Organismo.class.getName()});
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
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && str.equals("")==false);
    }    
    
    
}
