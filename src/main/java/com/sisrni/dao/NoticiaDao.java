/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Noticia;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "noticiaDao")
public class NoticiaDao extends GenericDao<Noticia, Integer> {

    public List<Noticia> getActiveNews(Integer categoria) {

        String query = "Select n from Noticia n where n.estadoNoticia=1 ";
        if (categoria != 0) {
            query += "and n.idCategoria.idCategoria=:categoria ";
        }
        query += "order by n.fechaNoticia desc";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        if (categoria != 0) {
            q.setParameter("categoria", categoria);
        }
        return q.list();
    }

    public List<Noticia> getAllNoticiasOrderDescDate() {
        String query = "Select n from Noticia n";
        query += " order by n.fechaNoticia desc";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        return q.list();
    }

    public Long getCountNoticiasByCat(String cat, Date desde, Date hasta) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String frmDate=format.format(desde);
//       
//        String enDate = format.format(hasta);
        String query = "Select count(n.idNoticia) from Noticia n where n.estadoNoticia=1 and n.idCategoria.categoriaNoticia =:cat and n.fechaNoticia between :desde and :hasta ";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        q.setParameter("cat", cat);
        q.setParameter("desde", desde);
        q.setParameter("hasta", hasta);
        return (Long) q.uniqueResult();
    }

    public List<Noticia> getNoticiasDetalle(Integer categoria, Date desde, Date hasta) {
        String query = "Select n from Noticia n where n.fechaNoticia between :desde and :hasta and n.estadoNoticia=1";
        if (categoria != 0) {
            query += " and n.idCategoria.idCategoria =:categoria";
        }
        query += " order by n.idCategoria.categoriaNoticia asc";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        if (categoria != 0) {
            q.setParameter("categoria", categoria);
        }
        q.setParameter("desde", desde);
        q.setParameter("hasta", hasta);
        return q.list();
    }

    public Integer noticiasNoVisibles() {
        String query = "Select n From Noticia n where n.estadoNoticia=0";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        return q.list().size();
    }

}
