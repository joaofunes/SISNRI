package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.EtapaMovilidad;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository(value = "etapamovilidadDao")
public class EtapaMovilidadDao extends GenericDao<EtapaMovilidad, Integer> {
    public List<EtapaMovilidad> getAllEtapasByNameAsc(){
        try{
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT et FROM EtapaMovilidad et ORDER BY et.nombreEtapa ASC");
            return q.list();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public List<EtapaMovilidad> getAllByIdDesc(){
       String query="Select e from EtapaMovilidad e order by e.idEtapa desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
    
}
