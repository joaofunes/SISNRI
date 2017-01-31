/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Movilidad;
import com.sisrni.pojo.rpt.PojoMovilidadAdm;
import com.sisrni.pojo.rpt.RptMovilidadesSegunEtapaPojo;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository(value = "movilidadDao")
public class MovilidadDao extends GenericDao<Movilidad, Integer> {

    public List<PojoMovilidadAdm> getMovilidadAdm(Integer idMovSearch) {
        String query = "SELECT mv.ID_MOVILIDAD idMovilidad, pm.NOMBRE_PROGRAMA_MOVILIDAD nombrePrograma,per.NOMBRE_PERSONA nombrePersona,per.APELLIDO_PERSONA apellidoPersona,tpmv.NOMBRE_TIPO_MOVILIDAD nombreTipoMovilidad,pa.NOMBRE_PAIS paisOrigen, pai.NOMBRE_PAIS paisDestino,mv.FECHA_INICIO fechaEntrada,mv.FECHA_FIN fechaSalida   \n"
                + "FROM movilidad mv INNER JOIN programa_movilidad pm ON mv.ID_PROGRAMA_MOVILIDAD = pm.ID_PROGRAMA_MOVILIDAD\n"
                + "INNER JOIN persona_movilidad prmov  ON mv.ID_MOVILIDAD =prmov.ID_MOVILIDAD\n"
                + "INNER JOIN persona per  ON prmov.ID_PERSONA = per.ID_PERSONA\n"
                + "INNER JOIN tipo_movilidad tpmv ON mv.ID_TIPO_MOVILIDAD = tpmv.ID_TIPO_MOVILIDAD\n"
                + "INNER JOIN pais pa ON mv.ID_PAIS_ORIGEN = pa.ID_PAIS\n"
                + "INNER JOIN pais pai ON mv.ID_PAIS_DESTINO = pai.ID_PAIS   \n"
                + "WHERE prmov.ID_TIPO_PERSONA = 10  ";
        if (idMovSearch > 0) {
            query = query + " AND mv.ID_MOVILIDAD =" + idMovSearch;
        }

        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                    .addScalar("idMovilidad", new IntegerType())
                    .addScalar("nombrePrograma", new StringType())
                    .addScalar("nombrePersona", new StringType())
                    .addScalar("apellidoPersona", new StringType())
                    .addScalar("nombreTipoMovilidad", new StringType())
                    .addScalar("paisOrigen", new StringType())
                    .addScalar("paisDestino", new StringType())
                    .addScalar("fechaEntrada", new DateType())
                    .addScalar("fechaSalida", new DateType())
                    .setResultTransformer(Transformers.aliasToBean(PojoMovilidadAdm.class));

            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<RptMovilidadesSegunEtapaPojo> getCantidadMovilidadesSegunEtapa(Integer anio) {
        String query = "SELECT tm.NOMBRE_TIPO_MOVILIDAD nombreMovilidad, COUNT(mv.ID_MOVILIDAD) cantidad  \n"
                + "FROM movilidad mv INNER JOIN tipo_movilidad tm ON mv.ID_TIPO_MOVILIDAD = tm.ID_TIPO_MOVILIDAD   \n"
                + "WHERE mv.ID_ETAPA_MOVILIDAD =3 AND YEAR(mv.FECHA_INICIO)= "+anio+ " GROUP BY tm.NOMBRE_TIPO_MOVILIDAD \n"
                + "UNION \n"
                + "SELECT eta.NOMBRE_ETAPA , COUNT(mov.ID_MOVILIDAD) \n"
                + "FROM movilidad mov INNER JOIN etapa_movilidad eta ON mov.ID_ETAPA_MOVILIDAD = eta.ID_ETAPA \n"
                + "WHERE (mov.ID_ETAPA_MOVILIDAD =3 OR mov.ID_ETAPA_MOVILIDAD =4)  AND  YEAR(mov.FECHA_INICIO)= "+anio+" \n"
                + "GROUP BY eta.NOMBRE_ETAPA; ";

        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                    .addScalar("nombreMovilidad", new StringType())
                    .addScalar("cantidad", new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(RptMovilidadesSegunEtapaPojo.class));

            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
