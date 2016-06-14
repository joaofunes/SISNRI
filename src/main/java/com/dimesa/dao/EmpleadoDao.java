/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.dao;

import com.dimesa.dao.generic.GenericDao;
import com.dimesa.model.Empleado;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HDEZ
 */
@Repository
public class EmpleadoDao extends GenericDao<Empleado, Integer> {
    
      public List<Empleado> getTecnicosExterno() {
        Query q = getSessionFactory().getCurrentSession().createQuery("SELECT e FROM Empleado e WHERE e.cargo like 'Externo' ");
        return q.list();
    }
//     public List<Cuentacontable> findAllActives() {
//        Query q = getSessionFactory().getCurrentSession().createQuery("SELECT c  FROM Cuentacontable c  WHERE c.activo='SI'");
//        return q.list();
//    }
      public List<Empleado> getTecnicos() {
        Query q = getSessionFactory().getCurrentSession().createQuery("SLECT e FROM Empleado e WHERE e.cargo like 'Tecnico' ");
        return q.list();
    }
    
    
}
