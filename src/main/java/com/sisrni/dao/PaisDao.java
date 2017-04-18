/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Pais;
import com.sisrni.pojo.rpt.PojoPais;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "paisDao")
public class PaisDao extends GenericDao<Pais, Integer> {

    //retorna la lista de paises
    public List<PojoPais> getPaises(Integer id) {
        Query q = getSessionFactory().getCurrentSession().createQuery("FROM Pais p WHERE p.idPais.idPais = :id");
        q.setParameter("id", id);
        List<Pais> lista = q.list();
        return q.list();
    }

    //retorna la lista de paises perteneciente a una regi�n
    public List<Pais> getPaisesByRegionId(Integer id) {
        Query q = getSessionFactory().getCurrentSession().createQuery("FROM Pais p WHERE p.idRegion.idRegion = :id");
        q.setParameter("id", id);
        List<Pais> lista = q.list();
        return q.list();
    }

    public List<Pais> getCountriesOrderByNameAsc() {
        Query q = getSessionFactory().getCurrentSession().createQuery("FROM Pais p ORDER BY p.nombrePais asc");
        List<Pais> lista = q.list();
        return q.list();
    }

//Retorna lista de paises ordenados alfabeticamente
    public List<Pais> getAllByNameAsc() {
        String query = "Select p from Pais p order by p.nombrePais asc";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        return q.list();
    }
     //retorna Pais por codigo pais
    public Pais getPaisCodigoPais(String codPais) {
        Query q = getSessionFactory().getCurrentSession().createQuery("FROM Pais p WHERE p.codigoPais = :id");
        q.setParameter("id", codPais);
      
        return (Pais) q.uniqueResult();
    }

       public List<Pais> getAllByIdDesc(){
       String query="Select p from Pais p order by p.idPais desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }

}
