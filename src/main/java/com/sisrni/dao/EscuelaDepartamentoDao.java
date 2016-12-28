/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.EscuelaDepartamento;
import com.sisrni.model.Facultad;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lillian
 */
@Repository(value = "escuelaDepartamentoDao")
public class EscuelaDepartamentoDao extends GenericDao<EscuelaDepartamento, Integer> {

    public List<EscuelaDepartamento> getEscuelasOrDeptoByFacultadId(Integer id) {
        String query = "SELECT o FROM ESCUELA_DEPARTAMENTO o WHERE o.idFacultad.idFacultad=:idFacultad";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        q.setParameter("idFacultad", id);
        return q.list();
    }
}
