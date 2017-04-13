/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.CategoriaNoticia;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "categoriaNoticiaDao")
public class CategoriaNoticiaDao extends GenericDao<CategoriaNoticia, Integer> {

    public List<String> getCategoriaNoticiaName() {
        String query = "Select cn.categoriaNoticia from CategoriaNoticia cn order by cn.categoriaNoticia asc";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        return q.list();
    }
        public List<CategoriaNoticia> getAllByIdDesc(){
       String query="Select c from CategoriaNoticia c order by c.idCategoria desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
}
