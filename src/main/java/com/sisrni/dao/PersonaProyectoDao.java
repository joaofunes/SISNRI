/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.PersonaProyecto;
import com.sisrni.model.PersonaProyectoPK;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "personaproyectoDao")
public class PersonaProyectoDao extends GenericDao<PersonaProyecto, Integer> {

    public int updatePersonaProyecto(int persona, int proyecto, int tipoPersona) {
        try {
            String sql = "UPDATE PERSONA_PROYECTO SET ID_PERSONA = " + persona + " WHERE ID_PROYECTO_GENERICO = " + proyecto + " AND ID_TIPO_PERSONA =" + tipoPersona;
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql);
            //Ejecutando la consulta
            int executeUpdate = q.executeUpdate();
            return executeUpdate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    
    public Integer getCount(Integer persona) {
        try {
            String query = "Select p From PersonaProyecto p where p.personaProyectoPK.idPersona="+ persona;
            Query q = getSessionFactory().getCurrentSession().createQuery(query);
            return q.list().size();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
