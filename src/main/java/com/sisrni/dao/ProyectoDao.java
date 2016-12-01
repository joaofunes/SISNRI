/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Proyecto;
import com.sisrni.pojo.rpt.PojoMapaInteractivo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "proyectoDao")
public class ProyectoDao extends GenericDao<Proyecto, Integer> {

    public List<PojoMapaInteractivo> getProjectListToCharts(List<String> paisSelected, List<String> tipoProyectoSelected, String desde, String hasta) {
        String wherePais = "";
        String whereTipoProyecto = "";
        String groupBy = " GROUP BY pr.ID_PAIS_COOPERANTE";
        String limite = "";
        
          if (paisSelected.size() > 0) {
            wherePais = wherePais + " AND pa.ID_PAIS IN (" + String.join(",", paisSelected) + ")";
        } else {
            limite += " LIMIT 3";
        }

        if (tipoProyectoSelected.size() > 0 ) {
            whereTipoProyecto += " AND pr.ID_TIPO_PROYECTO IN (" + String.join(",", tipoProyectoSelected) + ")";
        }
        
        String query = "SELECT pa.CODIGO_PAIS as codigoPais, pa.NOMBRE_PAIS as nombrePais, SUM(pr.MONTO_PROYECTO) as montoCooperacion,COUNT(pr.ID_PROYECTO) as cantidadProyectos\n"
                + " FROM PROYECTO pr INNER JOIN PAIS pa ON pr.ID_PAIS_COOPERANTE = pa.ID_PAIS\n"
                + " WHERE pr.ANIO_GESTION BETWEEN " + Integer.parseInt(desde) + " AND " + Integer.parseInt(hasta)+"\n"
                +  wherePais + whereTipoProyecto+ groupBy + limite;
                
        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                    .addScalar("codigoPais", new StringType())
                    .addScalar("nombrePais", new StringType())
                    .addScalar("montoCooperacion", new DoubleType())
                    .addScalar("cantidadProyectos", new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(PojoMapaInteractivo.class));
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
