package com.sisrni.validator;

import java.util.Date;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.validate.ClientValidator;

/**
 *
 * @author Usuario
 */

@FacesValidator("fechaMayorValidator")
public class FechaMayorValidator implements Validator, ClientValidator{

    @Override
    public void validate(FacesContext facesContex, UIComponent uiComponent, Object value) throws ValidatorException {
       if(value == null){
           return ;
       }
        
        Object fechaInicial = uiComponent.getAttributes().get("desdeMovilidad");
        
        if(fechaInicial == null){
            return;
        }
        
        //Vonvirtiendo el Object a Date
        Date startDate = (Date)fechaInicial;
        Date endDate = (Date)value;
        
        if(endDate.before(startDate)){
            FacesMessage mensaje = new FacesMessage("La fecha de finalizacion no puede ser anterior a la fecha de inicio");
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(mensaje);
        }
    }

    @Override
    public Map<String, Object> getMetadata() {
       return null;
    }

    @Override
    public String getValidatorId() {
       return "fechaMayorValidator"; 
    }
    
}
