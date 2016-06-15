package com.sisrni.managedbean.lazymodel;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.siapa.managedbean.lazymodel;
//
//import com.siapa.managedbean.lazymodel.generic.GenericLazyModel;
//import com.siapa.model.IngresoProducto;
//import com.siapa.service.generic.GenericService;
//
///**
// *
// * @author Jarvis
// */
//public class IngresoProductoLazyModel extends GenericLazyModel<IngresoProducto,Integer>{
//
//    public IngresoProductoLazyModel(GenericService<IngresoProducto, Integer> service) {
//        super(service);
//    }
//
//    @Override
//    public IngresoProducto getRowData(String rowKey) {
//        for (IngresoProducto ingresoProducto : getDatasource()) {
//            if(ingresoProducto.getIdIngresoProducto().equals(Integer.valueOf(rowKey)))
//            return ingresoProducto;
//        }
//        return null;
//    }
//
//    @Override
//    public Integer getRowKey(IngresoProducto element) {
//        return element.getIdIngresoProducto();
//    }
//    
//}
