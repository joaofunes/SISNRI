/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Facultad;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "facultadDao")
public class FacultadDao extends GenericDao<Facultad, Integer> {

    //recibe un idOrganismo perteneciente a un organismo que tienen un tipo organismo universidad y retorna las facultades de esa universidad
    public List<Facultad> getFacultadesByUniversidad(Integer idOrganismo) {
        String query = "SELECT o FROM Facultad o WHERE o.idOrganismo.idOrganismo=:idOrganismo";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        q.setParameter("idOrganismo", idOrganismo);
        return q.list();
    }
}
