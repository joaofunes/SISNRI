/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Persona;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "personaDao")
public class PersonaDao extends GenericDao<Persona, Integer> {
    
    
    public List<Persona> getReferenteInternoByName(String query) {
        try {
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a JOIN FETCH a.idTipoPersona tipo WHERE tipo.nombre='B' AND lower(a.nombrePersona) LIKE :name OR lower(a.apellidoPersona) LIKE :name");
//             SELECT a FROM  Persona a JOIN FETCH  a.idTipoPersona tipo WHERE tipo.nombre='B' AND (a.nombrePersona nombrePersona LIKE CONCAT('%', :query, '%'))
             q.setParameter("name", '%' + query.toLowerCase() + '%');
       
             return q.list();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
