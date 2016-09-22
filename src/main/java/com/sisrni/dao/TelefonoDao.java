/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Persona;
import com.sisrni.model.Telefono;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "telefonoDao")
public class TelefonoDao extends GenericDao<Telefono, Integer> {
    
    
    public List<Telefono> getTelefonosByPersona(Persona persona) {
        try {
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM  Telefono a JOIN FETCH  a.idTipoTelefono tipo WHERE a.idPersona.idPersona = :idPersona ");
             q.setParameter("idPersona",persona.getIdPersona());
       
             return q.list();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
