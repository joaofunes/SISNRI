/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoDocumento;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "tipoDocumentoDao")
public class TipoDocumentoDao extends GenericDao<TipoDocumento, Integer> {

    public List<TipoDocumento> getTipoDocumentosByCategory(Integer idCategoria) {
        List<TipoDocumento> list = new ArrayList<TipoDocumento>();
        String query = "SELECT td FROM TipoDocumento td WHERE td.idCategoriaDoc.idCategoriaDoc=:idCategoria";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        q.setParameter("idCategoria", idCategoria);
        if (q.list().size() > 0) {
            list = q.list();
        }
        return list;
    }
}
