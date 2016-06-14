///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.dimesa.service;
//
//import com.siapa.dao.AlimentoDao;
//import com.dimesa.dao.generic.GenericDao;
//import com.dimesa.model.Alimento;
//import com.siapa.service.generic.GenericService;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// *
// * @author Joao
// */
//@Service(value = "alimentoService")
//public class AlimentoService extends GenericService<Alimento, Integer>{
//    
//     @Autowired
//    private AlimentoDao alimentoDao;
//
//     
//     
//     
//    @Override
//    public GenericDao<Alimento, Integer> getDao() {
//        return alimentoDao;
//    }
//    
//    public List<Alimento> getTypeFood(){
//         return alimentoDao.getTypeFood();
//    }
//    
//    public List<Alimento> getByIdTypeFood(Integer tipo){
//        return alimentoDao.getByIdTypeFood(tipo);
//    }
//}
