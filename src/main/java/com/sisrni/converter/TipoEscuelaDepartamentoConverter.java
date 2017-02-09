/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.converter;

import com.sisrni.config.SpringApplicationContextServiceProvider;
import com.sisrni.model.EscuelaDepartamento;
import com.sisrni.service.EscuelaDepartamentoService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Luis
 */
//@Component(value = "tipoEscuelaDepartamentoConverter")
@FacesConverter("tipoEscuelaDepartamentoConverter")
public class TipoEscuelaDepartamentoConverter implements Converter {

//    @Autowired
//   private EscuelaDepartamentoService escuelaDepartamentoService;
//    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    boolean tp = ((Boolean) value).booleanValue();
        String tipo = "";
        if (tp == true) {
            tipo = "Escuela";
        }
        if (tp == false) {
            tipo = "Departamento";
        }
        return tipo;
    }

}
