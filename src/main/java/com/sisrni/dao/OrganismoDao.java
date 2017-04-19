/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Organismo;
import com.sisrni.pojo.rpt.PojoOrganismo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "organismoDao")
public class OrganismoDao extends GenericDao<Organismo, Integer> {

    public List<Integer> getOrganismosProyecto(Integer idProy) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT org.idOrganismo FROM Proyecto p JOIN p.proyectoGenerico pg JOIN pg.organismoList org  WHERE p.idProyecto =:proy");
            q.setParameter("proy", idProy);
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Organismo> getOrganismosPorPaisYTipo(Integer idPais, Integer idTipo) {
        String query = "Select o FROM Organismo o Where o.idPais=:idPais and o.idTipoOrganismo.idTipoOrganismo=:idTipo";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        q.setParameter("idPais", idPais);
        q.setParameter("idTipo", idTipo);
        return q.list();
    }

    public List<PojoOrganismo> getOrganismos() {
        String query = "SELECT org.ID_ORGANISMO AS idOrg, org.NOMBRE_ORGANISMO as nombre, "
                + "tipoOrg.NOMBRE_TIPO AS tipo, pais.NOMBRE_PAIS as nPais, "
                + "reg.NOMBRE_REGION AS nRegion, org.DIRECCION_ORGANISMO AS direccion, "
                + "org.CORREO_ORGANISMO as correo, telefon.NUMERO_TELEFONO as tel, "
                + "org.CODIGO_POSTAL as codigo FROM ORGANISMO AS org "
                + "INNER JOIN TIPO_ORGANISMO AS tipoOrg ON org.ID_TIPO_ORGANISMO = "
                + "tipoOrg.ID_TIPO_ORGANISMO INNER JOIN TELEFONO AS telefon ON org.ID_ORGANISMO = "
                + "telefon.ID_ORGANISMO INNER JOIN PAIS as pais ON org.ID_PAIS = pais.ID_PAIS INNER JOIN "
                + "REGION AS reg ON org.ID_REGION = reg.ID_REGION order by org.ID_ORGANISMO desc";
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                .addScalar("idOrg", new IntegerType())
                .addScalar("nombre", new StringType())
                .addScalar("tipo", new StringType())
                .addScalar("nPais", new StringType())
                .addScalar("nRegion", new StringType())
                .addScalar("direccion", new StringType())
                .addScalar("correo", new StringType())
                .addScalar("codigo", new IntegerType())
                .addScalar("tel", new StringType())
                .setResultTransformer(Transformers.aliasToBean(PojoOrganismo.class));
        return q.list();
    }

    public List<PojoOrganismo> getOrganismosPorTipoYPais(Integer idTipo, Integer idPais) {
        String query = "SELECT org.ID_ORGANISMO AS idOrg, org.NOMBRE_ORGANISMO as nombre, "
                + "tipoOrg.NOMBRE_TIPO AS tipo, pais.NOMBRE_PAIS as nPais, "
                + "reg.NOMBRE_REGION AS nRegion, org.DIRECCION_ORGANISMO AS direccion, "
                + "org.CORREO_ORGANISMO as correo, telefon.NUMERO_TELEFONO as tel, "
                + "org.CODIGO_POSTAL as codigo FROM ORGANISMO AS org "
                + "INNER JOIN TIPO_ORGANISMO AS tipoOrg ON org.ID_TIPO_ORGANISMO = "
                + "tipoOrg.ID_TIPO_ORGANISMO INNER JOIN TELEFONO AS telefon ON org.ID_ORGANISMO = "
                + "telefon.ID_ORGANISMO INNER JOIN PAIS as pais ON org.ID_PAIS = pais.ID_PAIS INNER JOIN "
                + "REGION AS reg ON org.ID_REGION = reg.ID_REGION ";
        if (idTipo != 0) {
            query += "WHERE tipoOrg.ID_TIPO_ORGANISMO = " + idTipo;
        }
        if (idPais != 0) {
            query += " AND pais.ID_PAIS = " + idPais;
        }

        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                .addScalar("idOrg", new IntegerType())
                .addScalar("nombre", new StringType())
                .addScalar("tipo", new StringType())
                .addScalar("nPais", new StringType())
                .addScalar("nRegion", new StringType())
                .addScalar("direccion", new StringType())
                .addScalar("correo", new StringType())
                .addScalar("codigo", new IntegerType())
                .addScalar("tel", new StringType())
                .setResultTransformer(Transformers.aliasToBean(PojoOrganismo.class));
        return q.list();
    }

    public List<Organismo> getAllByNameAsc() {
        String query = "Select o from Organismo o order by o.nombreOrganismo asc";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        return q.list();
    }
    
    public List<Organismo> getAllByIdDesc(){
       String query="Select o from Organismo o order by o.idOrganismo desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
  
}
