/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.converter;

import com.sisrni.managedbean.PropuestaConvenioMB;
import com.sisrni.model.Facultad;
import com.sisrni.model.Unidad;
import com.sisrni.pojo.rpt.PojoFacultadesUnidades;
import com.sisrni.service.FacultadService;
import com.sisrni.service.UnidadService;
import java.util.ArrayList;
import java.util.List;
import javax.el.ELContext;
import javax.faces.convert.FacesConverter;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
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
@Component(value = "facultadUnidadConverter")
@FacesConverter("facultadUnidadConverter")
public class FacultadUnidadConverter implements Converter{
    
    
    
    
    private List<PojoFacultadesUnidades> listaFacultadUnidad;
   

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String submittedValue) {
         if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                ELContext elContext = FacesContext.getCurrentInstance().getELContext();
                PropuestaConvenioMB firstBean = (PropuestaConvenioMB) elContext.getELResolver().getValue(elContext, null, "propuestaConvenioMB");
                    
                listaFacultadUnidad=firstBean.getListaFacultadUnidad();
                for (PojoFacultadesUnidades s : listaFacultadUnidad) {
                    if (Integer.parseInt(s.getValue()) == number) {
                        return s;
                    }
                }

            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
       if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((PojoFacultadesUnidades) value).getId());
        }
    }

    public List<PojoFacultadesUnidades> getListaFacultadUnidad() {
        return listaFacultadUnidad;
    }

    public void setListaFacultadUnidad(List<PojoFacultadesUnidades> listaFacultadUnidad) {
        this.listaFacultadUnidad = listaFacultadUnidad;
    }

   
    
    
       
}
