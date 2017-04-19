/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Carrera;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cortez
 */
@Repository(value = "carreraDao")
public class CarreraDao extends GenericDao<Carrera, Integer> {

    public List<Carrera> getCarrerasByFacultad(int idFacultad){
    
        String query= "Select c FROM Carrera c Where c.idFacultad.idFacultad=:idFacultad";
        Query q= getSessionFactory().getCurrentSession().createQuery(query);
        q.setParameter("idFacultad", idFacultad);
        return q.list();
       // return null;
    }
    
       public List<Carrera> getAllByIdDesc(){
       String query="Select c from Carrera c order by c.idCarrera desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
}
