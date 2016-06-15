package com.sisrni.dao;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.siapa.dao;
//
//import com.siapa.dao.generic.GenericDao;
//import com.siapa.model.Alimento;
//import java.util.List;
//import org.hibernate.Query;
//import org.springframework.stereotype.Repository;
//
///**
// *
// * @author Joao
// */
//
//@Repository
//public class AlimentoDao extends GenericDao<Alimento, Integer> {
//    
//    
//    
//    public List <Alimento>getTypeFood(){
//        
//        Query q=getSessionFactory().getCurrentSession().createQuery("SELECT food FROM Alimento food JOIN FETCH food.idTipoAlimento");
//        return q.list();
//        
//    }
//    
//    public List <Alimento>getByIdTypeFood(Integer tipo){
//        
//        Query q=getSessionFactory().getCurrentSession().createQuery("SELECT food FROM Alimento food WHERE food.idTipoAlimento.idTipoAlimento = :tipo");
//        q.setParameter("tipo",tipo);
//        return q.list();
//        
//    }
//    
//}
