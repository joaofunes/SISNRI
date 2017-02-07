/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.converter;

import com.sisrni.config.SpringApplicationContextServiceProvider;
import com.sisrni.model.Pais;
import com.sisrni.service.PaisService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Luis
 */
//@Component(value = "nombrePaisConverter")
@FacesConverter("nombrePaisConverter")
public class NombrePaisConverter implements Converter {

//    @Autowired
//   private PaisService paisService;
//    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
   PaisService paisService=(PaisService) SpringApplicationContextServiceProvider.getBean("paisService");
        Pais findById;
        findById = paisService.findById(Integer.parseInt(value.toString()));
        return findById.getNombrePais();
    }

}
