/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.EscuelaDepartamento;
import com.sisrni.model.Movilidad;
import com.sisrni.model.Unidad;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "unidadDao")
public class UnidadDao extends GenericDao<Unidad, Integer> {

    //retorna la lista de unidades perteneciente a una facultad --comentado por si lo utilizamos despues
//    public List<EscuelaDepartamento> getUnidadesByFacultadId(Integer id){
//        Query q =getSessionFactory().getCurrentSession().createQuery("FROM EscuelaDepartamento u WHERE u.idFacultadUnidad.idFacultadUnidad = :id");
//        q.setParameter("id",id);
//        List<EscuelaDepartamento> lista= q.list();
//        return q.list();        
//   }
    /**
     * Retorna una lista de las unidades que pertenecen a un determinado
     * organismo recibe como parametro el id de un organismo especificado
     */
    public List<Unidad> getUnidadesByUniversidad(Integer idUnidad) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT u FROM Unidad u WHERE u.idOrganismo.idOrganismo=:idUnidad");
            q.setParameter("idUnidad", idUnidad);
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Integer> getUnidadesMovilidad(Integer idMov) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT u.idUnidad FROM Unidad u JOIN u.movilidadList m WHERE m.idMovilidad=:idmov");
            q.setParameter("idmov", idMov);
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Unidad getUnidadById(Integer idUni) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT u FROM Unidad u WHERE u.idUnidad=:idUni");
            q.setParameter("idUni", idUni);
            return (Unidad) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;  
    }
    
    public void eliminarIntermediaMovilidadUnidad(Movilidad mov){
        try{
            String query = "DELETE FROM MOVILIDAD_UNIDAD WHERE ID_MOVILIDAD = "+ mov.getIdMovilidad();
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query);
            q.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public List<Unidad> getAllByIdDesc(){
       String query="Select u from Unidad u order by u.idUnidad desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
     }

}
