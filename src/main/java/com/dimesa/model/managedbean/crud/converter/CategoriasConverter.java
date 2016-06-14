package com.dimesa.model.managedbean.crud.converter;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.siapa.model.managedbean.crud.converter;
//
//import com.dimesa.model.Categorias;
//import com.siapa.model.managedbean.crud.util.JsfUtil;
//import com.siapa.service.CategoriasService;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// *
// * @author Joao
// */
//
//@Component(value = "categoriasConverter")
//public class CategoriasConverter implements Converter {
//
//    @Autowired
//    private CategoriasService categoriasService;
//    
//    @Override
//    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
//            return null;
//        }
//        return this.categoriasService.findById(getKey(value));
//    }
//
//   @Override
//    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//        if (object == null
//                || (object instanceof String && ((String) object).length() == 0)) {
//            return null;
//        }
//        if (object instanceof Categorias) {
//            Categorias o = (Categorias) object;
//            return getStringKey(o.getIdCategorias());
//        } else {
//            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Categorias.class.getName()});
//            return null;
//        }
//    }
//    
//    java.lang.Integer getKey(String value) {
//        java.lang.Integer key;
//        key = Integer.valueOf(value);
//        return key;
//    }
//
//    String getStringKey(java.lang.Integer value) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(value);
//        return sb.toString();
//    }
//}
