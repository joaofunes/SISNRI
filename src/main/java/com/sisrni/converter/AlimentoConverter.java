package com.sisrni.converter;

//3/*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.siapa.model.managedbean.crud.converter;
//
//import com.sisrni.model.Alimento;
//import com.sisrni.model.Categorias;
//import com.siapa.model.managedbean.crud.util.JsfUtil;
//import com.siapa.service.AlimentoService;
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
//@Component(value = "alimentoConverter")
//public class AlimentoConverter implements Converter {
// 
//    @Autowired
//    private AlimentoService alimentoService;
//    
//     @Override
//    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
//            return null;
//        }
//        return this.alimentoService.findById(getKey(value));
//    }
//
//   @Override
//    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//        if (object == null
//                || (object instanceof String && ((String) object).length() == 0)) {
//            return null;
//        }
//        if (object instanceof Alimento) {
//            Alimento o = (Alimento) object;
//            return getStringKey(o.getIdAlimento());
//        } else {
//            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Alimento.class.getName()});
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
