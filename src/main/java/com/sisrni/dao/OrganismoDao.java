/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Organismo;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "organismoDao")
public class OrganismoDao extends GenericDao<Organismo, Integer> {
    public List<Integer> getOrganismosProyecto(Integer idProy){
        try{
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT org.idOrganismo FROM Proyecto p JOIN p.proyectoGenerico pg JOIN pg.organismoList org  WHERE p.idProyecto =:proy");
            q.setParameter("proy", idProy);
            return q.list();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
}
