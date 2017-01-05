/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Beca;
import com.sisrni.pojo.rpt.PojoBeca;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lillian
 */
@Repository(value = "becaDao")
public class BecaDao extends GenericDao<Beca, Integer> {

    public List<PojoBeca> getBecas() {
        String query = "SELECT bec.ID_BECA idBeca, bec.ANIO_GESTION anioGestion,  prb.NOMBRE_PROGRAMA as programaBeca, per.NOMBRE_PERSONA nombreBecario, per.APELLIDO_PERSONA apellidoBecario,\n"
                + "pai.NOMBRE_PAIS paisDestino, org.NOMBRE_ORGANISMO universidadDestino, bec.MONTO_TOTAL montoBeca,IF(bec.OTORGADA = 1, 'SI','NO') as  otorgada\n"
                + "FROM beca bec\n"
                + "INNER JOIN programa_beca prb\n"
                + "ON bec.ID_PROGRAMA_BECA = prb.ID_PROGRAMA\n"
                + "INNER JOIN persona_beca peb\n"
                + "ON bec.ID_BECA = peb.ID_BECA\n"
                + "INNER JOIN persona per\n"
                + "ON peb.ID_PERSONA = per.ID_PERSONA\n"
                + "INNER JOIN organismo org\n"
                + "  ON bec.ID_UNIVERSIDAD = org.ID_ORGANISMO\n"
                + "INNER JOIN pais pai\n"
                + "ON bec.ID_PAIS_DESTINO = pai.ID_PAIS\n"
                + "WHERE peb.ID_TIPO_PERSONA=6";

        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                    .addScalar("idBeca", new IntegerType())
                    .addScalar("anioGestion",new IntegerType())
                    .addScalar("programaBeca", new StringType())
                    .addScalar("nombreBecario", new StringType())
                    .addScalar("apellidoBecario", new StringType())
                    .addScalar("paisDestino", new StringType())
                    .addScalar("universidadDestino", new StringType())
                    .addScalar("montoBeca", new DoubleType())
                    .addScalar("otorgada", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(PojoBeca.class));

            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
