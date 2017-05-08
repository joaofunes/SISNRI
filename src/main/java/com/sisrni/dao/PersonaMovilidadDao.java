package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Movilidad;
import com.sisrni.model.PersonaMovilidad;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository(value = "personamovilidadDao")
public class PersonaMovilidadDao extends GenericDao<PersonaMovilidad, Integer>{
    /**
     * Metodo para eliminar relacion entre Movilidad y Persona
     * @param mov 
     */
    public void eliminarIntemediaPersonaMovilidad(Movilidad mov){
        try{
            String query = "DELETE FROM PERSONA_MOVILIDAD WHERE ID_MOVILIDAD = "+ mov.getIdMovilidad();
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query);
            q.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
        
        public PersonaMovilidad getPersonaMovilidadById(int persona){
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT p FROM PersonaMovilidad p WHERE p.persona=:persona");
            q.setParameter("persona", persona);
            return (PersonaMovilidad) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();        
        }
     return null;
    }
    
}
