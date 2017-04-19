/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Facultad;
import com.sisrni.model.Movilidad;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "facultadDao")
public class FacultadDao extends GenericDao<Facultad, Integer> {

    //recibe un idOrganismo perteneciente a un organismo que tienen un tipo organismo universidad y retorna las facultades de esa universidad
    public List<Facultad> getFacultadesByUniversidad(Integer idOrganismo) {
        String query = "SELECT o FROM Facultad o WHERE o.idOrganismo.idOrganismo=:idOrganismo";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        q.setParameter("idOrganismo", idOrganismo);
        return q.list();
    }

    public List<Integer> getFacultadesMovilidad(Integer idmov) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT f.idFacultad FROM Facultad f JOIN f.movilidadList mv WHERE mv.idMovilidad=:idmov");
            q.setParameter("idmov", idmov);
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

   
    public Facultad getFacultadById(Integer idFac) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT f FROM Facultad f WHERE f.idFacultad =:idFac");
            q.setParameter("idFac", idFac);
            return (Facultad) q.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }
    
    
    /**
     * Elimina la relacion entre movilidad y facultad
     * @param mov
     */
    public void eliminarIntermediaMovilidadFacultad(Movilidad mov){
        try{
        String query ="DELETE FROM MOVILIDAD_FACULTAD WHERE ID_MOVILIDAD = "+ mov.getIdMovilidad();  
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query);
        q.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public List<Facultad> getAllByIdDesc(){
       String query="Select f from Facultad f order by f.idFacultad desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
    

}
