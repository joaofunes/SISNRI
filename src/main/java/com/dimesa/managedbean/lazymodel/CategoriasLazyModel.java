package com.dimesa.managedbean.lazymodel;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.siapa.managedbean.lazymodel;
//
//import com.siapa.managedbean.lazymodel.generic.GenericLazyModel;
//import com.dimesa.model.Categorias;
//import com.siapa.service.generic.GenericService;
//
///**
// *
// * @author Joao
// */
//public class CategoriasLazyModel extends GenericLazyModel<Categorias, Integer> {
//
//    public CategoriasLazyModel(GenericService<Categorias, Integer> service) {
//        super(service);
//    }
//
//    @Override
//    public Categorias getRowData(String rowKey) {
//        for (Categorias categorias : getDatasource()) {
//            if (categorias.getIdCategorias().equals(Integer.valueOf(rowKey)));
//            return categorias;
//        }
//        return null;
//    }
//
//    @Override
//    public Integer getRowKey(Categorias element) {
//          return element.getIdCategorias();
//    }
//    
//}
