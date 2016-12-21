/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.FacultadProyecto;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "facultadproyectoDao")
public class FacultadProyectoDao extends GenericDao<FacultadProyecto, Integer> {

    public int updateFacultadProyecto(int facultad, int proyecto, int tipoFacultad) {
        try {
            String sql = "UPDATE FACULTAD_PROYECTO SET ID_FACULTAD = " + facultad + " WHERE ID_FACULTAD = " + proyecto + " AND ID_TIPO_FACULTAD =" + tipoFacultad;
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql);
            //Ejecutando la consulta
            int executeUpdate = q.executeUpdate();
            return executeUpdate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
