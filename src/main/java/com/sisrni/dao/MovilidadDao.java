/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Movilidad;
import com.sisrni.pojo.rpt.PojoMovilidadAdm;
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
public class MovilidadDao extends GenericDao<Movilidad, Integer>{
    
    
    public List<PojoMovilidadAdm> getMovilidadAdm(){
        String query ="SELECT mv.ID_MOVILIDAD idMovilidad, pm.NOMBRE_PROGRAMA_MOVILIDAD nombrePrograma,per.NOMBRE_PERSONA nombrePersona,per.APELLIDO_PERSONA apellidoPersona,tpmv.NOMBRE_TIPO_MOVILIDAD nombreTipoMovilidad,pa.NOMBRE_PAIS paisOrigen, pai.NOMBRE_PAIS paisDestino,mv.FECHA_INICIO fechaEntrada,mv.FECHA_FIN fechaSalida   \n" +
                      "FROM movilidad mv INNER JOIN programa_movilidad pm ON mv.ID_PROGRAMA_MOVILIDAD = pm.ID_PROGRAMA_MOVILIDAD\n" +
                                     "INNER JOIN persona_movilidad prmov  ON mv.ID_MOVILIDAD =prmov.ID_MOVILIDAD\n" +
                                     "INNER JOIN persona per  ON prmov.ID_PERSONA = per.ID_PERSONA\n" +
                                     "INNER JOIN tipo_movilidad tpmv ON mv.ID_TIPO_MOVILIDAD = tpmv.ID_TIPO_MOVILIDAD\n" +
                                     "INNER JOIN pais pa ON mv.ID_PAIS_ORIGEN = pa.ID_PAIS\n" +
                                     "INNER JOIN pais pai ON mv.ID_PAIS_DESTINO = pai.ID_PAIS   \n" +
                      "WHERE prmov.ID_TIPO_PERSONA = 10;  ";
        
        try{
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
