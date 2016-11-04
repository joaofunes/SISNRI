/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoTelefono;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "tipoTelefonoDao")
public class TipoTelefonoDao extends GenericDao<TipoTelefono, Integer>{
    public TipoTelefono getTipoByDesc (String nombretelefono){
        Query q =getSessionFactory().getCurrentSession().createQuery("FROM TipoTelefono tp WHERE tp.nombre = :nombretelefono");
        q.setParameter("nombretelefono",nombretelefono);
    return (TipoTelefono) q.uniqueResult();
    }
}
