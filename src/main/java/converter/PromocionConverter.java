///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package converter;
//
//
//import com.siacofinges.dao.PromocionDao;
//import com.siacofinges.model.Promocion;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//
///**
// *
// * @author Cristian Oswaldo Fuentes
// */
//public class PromocionConverter implements Converter {
//    
//    @Override
//    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        Object valorObject = null;
//        
//        if( value != null && !value.equals("") ) {
//            PromocionDao promocionDao=new PromocionDao();
//            valorObject = promocionDao.findById(Integer.parseInt( value ));
//        }
//        
//        return valorObject;
//    }
//
//    @Override
//    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        String valorString = "";
//        
//        if( value != null && value instanceof Promocion ) {
//            valorString = String.valueOf( ((Promocion) value).getPromocionId() );
//        }
//        
//        return valorString;
//    }
//
//}
