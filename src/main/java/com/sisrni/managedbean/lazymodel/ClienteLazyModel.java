package com.sisrni.managedbean.lazymodel;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.siapa.managedbean.lazymodel;
//
//import com.siapa.managedbean.lazymodel.generic.GenericLazyModel;
//import com.siapa.model.Cliente;
//import com.siapa.service.generic.GenericService;
//
///**
// *
// * @author Joao
// */
//public class ClienteLazyModel extends GenericLazyModel<Cliente,Integer>{
//
//    public ClienteLazyModel(GenericService<Cliente, Integer> service) {
//        super(service);
//    }
//    
//    @Override
//    public Cliente getRowData(String rowKey) {
//       for (Cliente cliente : getDatasource()) {
//            if (cliente.getIdCliente().equals(Integer.valueOf(rowKey)));
//            return cliente;
//        }
//        return null;
//    }
//
//    @Override
//    public Integer getRowKey(Cliente element) {
//        return element.getIdCliente();
//    }
//    
//}
