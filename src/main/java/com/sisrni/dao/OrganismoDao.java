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
    
     public List<PojoOrganismo> getOrganismosPorPaisYTipo2(Integer idPais, Integer idTipo) {
        String query = "SELECT org.ID_ORGANISMO AS idOrg, org.NOMBRE_ORGANISMO as nombre, "
                + "tipoOrg.NOMBRE_TIPO AS tipo, pais.NOMBRE_PAIS as nPais, "
                + "reg.NOMBRE_REGION AS nRegion, org.DIRECCION_ORGANISMO AS direccion, "
                + "org.CORREO_ORGANISMO as correo, telefon.NUMERO_TELEFONO as tel, "
                + "org.CODIGO_POSTAL as codigo FROM organismo AS org "
                + "INNER JOIN tipo_organismo AS tipoOrg ON org.ID_TIPO_ORGANISMO = "
                + "tipoOrg.ID_TIPO_ORGANISMO INNER JOIN telefono AS telefon ON org.ID_ORGANISMO = "
                + "telefon.ID_ORGANISMO INNER JOIN pais as pais ON org.ID_PAIS = pais.ID_PAIS INNER JOIN "
                + "region AS reg ON org.ID_REGION = reg.ID_REGION ";
               // + "WHERE pais.ID_PAIS = "
                //+ idPais + " AND tipoOrg.ID_TIPO_ORGANISMO = " + idTipo;
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
    
   
}
