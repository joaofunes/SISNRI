/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Noticia;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "noticiaDao")
public class NoticiaDao extends GenericDao<Noticia, Integer> {

    public List<Noticia> getActiveNews() {
        String query = "Select n from Noticia n where n.estadoNoticia=1";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        return q.list();
    }
}
