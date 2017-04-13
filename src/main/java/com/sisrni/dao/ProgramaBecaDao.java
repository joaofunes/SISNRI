/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.ProgramaBeca;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cortez
 */
@Repository(value = "programaBecaDao")
public class ProgramaBecaDao extends GenericDao<ProgramaBeca, Integer> {

    public List<ProgramaBeca> getAllByNameAsc() {
        String query = "Select pg from ProgramaBeca pg order by pg.nombrePrograma asc";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        return q.list();
    }
    
     public List<ProgramaBeca> getAllByIdDesc(){
       String query="Select pg from ProgramaBeca pg order by pg.idPrograma desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
}
