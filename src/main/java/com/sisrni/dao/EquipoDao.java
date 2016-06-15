/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Equipo;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;


/**
 *
 * @author HDEZ
 */
@Repository
public class EquipoDao extends GenericDao<Equipo, Integer> {
    
    public List<Equipo> getListadoEquipos()
    {
        Query q = getSessionFactory().getCurrentSession().createQuery(
        "SELECT * FROM EQUIPO"
        );
         return q.list();
    }

}
