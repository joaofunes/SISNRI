/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Movilidad;
import com.sisrni.model.PersonaMovilidad;
import com.sisrni.pojo.rpt.PojoMapaMovilidad;
import com.sisrni.pojo.rpt.PojoMovilidadAdm;
import com.sisrni.pojo.rpt.PojoMovilidadDocumentacion;
import com.sisrni.pojo.rpt.PojoMovilidadMapaCategoria;
import com.sisrni.pojo.rpt.RptMovilidadEntranteFactBeneficiadaPojo;
import com.sisrni.pojo.rpt.RptMovilidadEntranteMesEjecucionPojo;
import com.sisrni.pojo.rpt.RptMovilidadEntrantePaisPojo;
import com.sisrni.pojo.rpt.RptMovilidadSalienteFactBeneficiadaPojo;
import com.sisrni.pojo.rpt.RptMovilidadSalienteMesPojo;
import com.sisrni.pojo.rpt.RptMovilidadSalientePaisDestinoPojo;
import com.sisrni.pojo.rpt.RptMovilidadesMesPojo;
import com.sisrni.pojo.rpt.RptMovilidadesPorFacultadEjecutadasAnioPojo;
import com.sisrni.pojo.rpt.RptMovilidadesSegunEtapaPojo;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
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
        String query = "SELECT mv.ID_MOVILIDAD idMovilidad, pm.NOMBRE_PROGRAMA_MOVILIDAD nombrePrograma,per.NOMBRE_PERSONA nombrePersona,per.APELLIDO_PERSONA apellidoPersona,tpmv.NOMBRE_TIPO_MOVILIDAD nombreTipoMovilidad,pa.NOMBRE_PAIS paisOrigen, pai.NOMBRE_PAIS paisDestino,mv.FECHA_INICIO fechaEntrada,mv.FECHA_FIN fechaSalida ,etm.NOMBRE_ETAPA nombreEtapa  \n"
                + "FROM MOVILIDAD mv INNER JOIN PROGRAMA_MOVILIDAD pm ON mv.ID_PROGRAMA_MOVILIDAD = pm.ID_PROGRAMA_MOVILIDAD\n"
                + "INNER JOIN PERSONA_MOVILIDAD prmov  ON mv.ID_MOVILIDAD =prmov.ID_MOVILIDAD\n"
                + "INNER JOIN PERSONA per  ON prmov.ID_PERSONA = per.ID_PERSONA\n"
                + "INNER JOIN TIPO_MOVILIDAD tpmv ON mv.ID_TIPO_MOVILIDAD = tpmv.ID_TIPO_MOVILIDAD\n"
                + "INNER JOIN PAIS pa ON mv.ID_PAIS_ORIGEN = pa.ID_PAIS\n"
                + "INNER JOIN PAIS pai ON mv.ID_PAIS_DESTINO = pai.ID_PAIS "
                + "INNER JOIN ETAPA_MOVILIDAD etm ON mv.ID_ETAPA_MOVILIDAD = etm.ID_ETAPA   \n"
                + "WHERE prmov.ID_TIPO_PERSONA = 10  ";
        if (idMovSearch > 0) {
            query = query + " AND mv.ID_MOVILIDAD =" + idMovSearch;
        }
        //query = query + " ORDER BY mv.FECHA_INGRESO DESC";
        query = query + "ORDER BY mv.ID_MOVILIDAD DESC";
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
                    .addScalar("nombreEtapa", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(PojoMovilidadAdm.class));

            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<RptMovilidadesSegunEtapaPojo> getCantidadMovilidadesSegunEtapa(Integer anio) {
        String query = "SELECT tm.NOMBRE_TIPO_MOVILIDAD nombreMovilidad, COUNT(mv.ID_MOVILIDAD) cantidad  \n"
                + "FROM MOVILIDAD mv INNER JOIN TIPO_MOVILIDAD tm ON mv.ID_TIPO_MOVILIDAD = tm.ID_TIPO_MOVILIDAD   \n"
                + "WHERE mv.ID_ETAPA_MOVILIDAD =3 AND YEAR(mv.FECHA_INICIO)= " + anio + " GROUP BY tm.NOMBRE_TIPO_MOVILIDAD \n"
                + "UNION \n"
                + "SELECT eta.NOMBRE_ETAPA , COUNT(mov.ID_MOVILIDAD) \n"
                + "FROM MOVILIDAD mov INNER JOIN ETAPA_MOVILIDAD eta ON mov.ID_ETAPA_MOVILIDAD = eta.ID_ETAPA \n"
                + "WHERE (mov.ID_ETAPA_MOVILIDAD =3 OR mov.ID_ETAPA_MOVILIDAD =4)  AND  YEAR(mov.FECHA_INICIO)= " + anio + " \n"
                + "GROUP BY eta.NOMBRE_ETAPA ";

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

    public List<RptMovilidadesPorFacultadEjecutadasAnioPojo> getCantidadMovilidadesFacultad(Integer anio) {
        String query = "SELECT f.NOMBRE_FACULTAD nombreFacultad, COUNT(mv.ID_MOVILIDAD) cantidad \n"
                + "FROM MOVILIDAD mv INNER JOIN MOVILIDAD_FACULTAD mf ON mv.ID_MOVILIDAD = mf.ID_MOVILIDAD \n"
                + "                  INNER JOIN FACULTAD f ON mf.ID_FACULTAD = f.ID_FACULTAD \n"
                + "                  INNER JOIN TIPO_MOVILIDAD tm ON mv.ID_TIPO_MOVILIDAD = tm.ID_TIPO_MOVILIDAD \n"
                + "WHERE mv.ID_ETAPA_MOVILIDAD =3  AND YEAR(mv.FECHA_INICIO)= " + anio + " GROUP BY f.NOMBRE_FACULTAD \n"
                + "UNION \n"
                + "SELECT un.NOMBRE_UNIDAD,COUNT(mov.ID_MOVILIDAD) \n"
                + "FROM MOVILIDAD mov INNER JOIN MOVILIDAD_UNIDAD mu ON mov.ID_MOVILIDAD = mu.ID_MOVILIDAD \n"
                + "                  INNER JOIN UNIDAD un ON mu.ID_UNIDAD = un.ID_UNIDAD \n"
                + "                  INNER JOIN TIPO_MOVILIDAD tmov ON mov.ID_TIPO_MOVILIDAD = tmov.ID_TIPO_MOVILIDAD \n"
                + "WHERE mov.ID_ETAPA_MOVILIDAD= 3 AND YEAR(mov.FECHA_INICIO)= " + anio + " GROUP BY un.NOMBRE_UNIDAD";

        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                    .addScalar("nombreFacultad", new StringType())
                    .addScalar("cantidad", new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(RptMovilidadesPorFacultadEjecutadasAnioPojo.class));

            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<RptMovilidadesMesPojo> getMovilidadesMes(Integer anio) {
        String query = "SELECT 'Enero' nombreMes,(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=1,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 ) cantidad FROM MOVILIDAD \n"
                + "UNION\n"
                + "SELECT 'Febrero',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=2,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM MOVILIDAD\n"
                + "UNION\n"
                + "SELECT 'Marzo',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=3,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM MOVILIDAD\n"
                + "UNION\n"
                + "SELECT 'Abril',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=4,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM MOVILIDAD mv\n"
                + "UNION\n"
                + "SELECT 'Mayo',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=5,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM MOVILIDAD mv\n"
                + "UNION\n"
                + "SELECT 'Junio',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=6,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM MOVILIDAD mv\n"
                + "UNION\n"
                + "SELECT 'Julio',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=7,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM MOVILIDAD mv\n"
                + "UNION\n"
                + "SELECT 'Agosto',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=8,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM MOVILIDAD mv\n"
                + "UNION\n"
                + "SELECT 'Septiembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=9,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM MOVILIDAD mv\n"
                + "UNION\n"
                + "SELECT 'Octubre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=10,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM MOVILIDAD mv\n"
                + "UNION\n"
                + "SELECT 'Noviembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=11,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM MOVILIDAD mv\n"
                + "UNION\n"
                + "SELECT 'Diciembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=12,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM MOVILIDAD mv";

        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                    .addScalar("nombreMes", new StringType())
                    .addScalar("cantidad", new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(RptMovilidadesMesPojo.class));

            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<RptMovilidadSalientePaisDestinoPojo> getMovilidadesSalientesPaisDestino(Integer anio) {
        String query = "SELECT p.NOMBRE_PAIS nombrePais, COUNT(mv.ID_MOVILIDAD) cantidad \n"
                + "FROM MOVILIDAD mv INNER JOIN TIPO_MOVILIDAD tm ON mv.ID_TIPO_MOVILIDAD = tm.ID_TIPO_MOVILIDAD \n"
                + "                  INNER JOIN PAIS p ON mv.ID_PAIS_DESTINO = p.ID_PAIS \n"
                + "                  INNER JOIN ETAPA_MOVILIDAD et ON mv.ID_ETAPA_MOVILIDAD = et.ID_ETAPA  \n"
                + "WHERE tm.ID_TIPO_MOVILIDAD =2 AND mv.ID_ETAPA_MOVILIDAD = 3 AND YEAR(mv.FECHA_INICIO) = " + anio + " \n"
                + "GROUP BY p.NOMBRE_PAIS";
        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                    .addScalar("nombrePais", new StringType())
                    .addScalar("cantidad", new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(RptMovilidadSalientePaisDestinoPojo.class));

            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<RptMovilidadSalienteMesPojo> getMovilidadesSalientesMes(Integer anio) {
        String query = "SELECT 'Enero' nombreMes,(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=1,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) cantidad FROM MOVILIDAD \n"
                + "UNION \n"
                + "SELECT 'Febrero',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=2,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM MOVILIDAD \n"
                + "UNION \n"
                + "SELECT 'Marzo',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=3,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM MOVILIDAD \n"
                + "UNION \n"
                + "SELECT 'Abril',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=4,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM MOVILIDAD mv \n"
                + "UNION \n"
                + "SELECT 'Mayo',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=5,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM MOVILIDAD mv \n"
                + "UNION \n"
                + "SELECT 'Junio',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=6,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM MOVILIDAD mv \n"
                + "UNION \n"
                + "SELECT 'Julio',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=7,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM MOVILIDAD mv \n"
                + "UNION \n"
                + "SELECT 'Agosto',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=8,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM MOVILIDAD mv \n"
                + "UNION \n"
                + "SELECT 'Septiembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=9,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM MOVILIDAD mv \n"
                + "UNION \n"
                + "SELECT 'Octubre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=10,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM MOVILIDAD mv \n"
                + "UNION \n"
                + "SELECT 'Noviembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=11,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM MOVILIDAD mv \n"
                + "UNION \n"
                + "SELECT 'Diciembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=12,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM MOVILIDAD mv";

        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                    .addScalar("nombreMes", new StringType())
                    .addScalar("cantidad", new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(RptMovilidadSalienteMesPojo.class));
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<RptMovilidadSalienteFactBeneficiadaPojo> getMoviSalientesFactBenef(Integer anio) {
        String query = "SELECT f.NOMBRE_FACULTAD nombreFacultad, COUNT(mv.ID_MOVILIDAD) cantidad \n"
                + "FROM MOVILIDAD mv INNER JOIN MOVILIDAD_FACULTAD mf ON mv.ID_MOVILIDAD = mf.ID_MOVILIDAD \n"
                + "                                  INNER JOIN FACULTAD f ON mf.ID_FACULTAD = f.ID_FACULTAD \n"
                + "                                  INNER JOIN TIPO_MOVILIDAD tm ON mv.ID_TIPO_MOVILIDAD = tm.ID_TIPO_MOVILIDAD \n"
                + "WHERE mv.ID_ETAPA_MOVILIDAD =3 AND mv.ID_TIPO_MOVILIDAD =2     AND YEAR(mv.FECHA_INICIO)= " + anio + " GROUP BY f.NOMBRE_FACULTAD \n"
                + "UNION \n"
                + "SELECT un.NOMBRE_UNIDAD,COUNT(mov.ID_MOVILIDAD) \n"
                + "FROM MOVILIDAD mov INNER JOIN MOVILIDAD_UNIDAD mu ON mov.ID_MOVILIDAD = mu.ID_MOVILIDAD \n"
                + "                                  INNER JOIN UNIDAD un ON mu.ID_UNIDAD = un.ID_UNIDAD \n"
                + "                                  INNER JOIN TIPO_MOVILIDAD tmov ON mov.ID_TIPO_MOVILIDAD = tmov.ID_TIPO_MOVILIDAD \n"
                + "WHERE mov.ID_ETAPA_MOVILIDAD= 3 AND mov.ID_TIPO_MOVILIDAD =2 AND YEAR(mov.FECHA_INICIO)= " + anio + " GROUP BY un.NOMBRE_UNIDAD";

        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                    .addScalar("nombreFacultad", new StringType())
                    .addScalar("cantidad", new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(RptMovilidadSalienteFactBeneficiadaPojo.class));
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<RptMovilidadEntrantePaisPojo> getMovilEntrantesPaisOrigen(Integer anio) {
        String query = "SELECT p.NOMBRE_PAIS paisNombre, COUNT(mv.ID_MOVILIDAD) cantidad \n"
                + "FROM MOVILIDAD mv INNER JOIN TIPO_MOVILIDAD tm ON mv.ID_TIPO_MOVILIDAD = tm.ID_TIPO_MOVILIDAD \n"
                + "                  INNER JOIN PAIS p ON mv.ID_PAIS_ORIGEN = p.ID_PAIS \n"
                + "                  INNER JOIN ETAPA_MOVILIDAD et ON mv.ID_ETAPA_MOVILIDAD = et.ID_ETAPA  \n"
                + "WHERE tm.ID_TIPO_MOVILIDAD =1 AND mv.ID_ETAPA_MOVILIDAD = 3 AND YEAR(mv.FECHA_INICIO) = " + anio + " \n"
                + "GROUP BY p.NOMBRE_PAIS;";

        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                    .addScalar("paisNombre", new StringType())
                    .addScalar("cantidad", new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(RptMovilidadEntrantePaisPojo.class));

            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<RptMovilidadEntranteMesEjecucionPojo> getMovilidadEntranteMes(Integer anio) {
        String query = "SELECT 'Enero' nombreMes,(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=1,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) cantidad FROM MOVILIDAD \n"
                + "UNION \n"
                + "SELECT 'Febrero',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=2,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM MOVILIDAD \n"
                + "UNION \n"
                + "SELECT 'Marzo',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=3,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM MOVILIDAD \n"
                + "UNION \n"
                + "SELECT 'Abril',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=4,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM MOVILIDAD mv \n"
                + "UNION \n"
                + "SELECT 'Mayo',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=5,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM MOVILIDAD mv \n"
                + "UNION \n"
                + "SELECT 'Junio',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=6,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM MOVILIDAD mv \n"
                + "UNION \n"
                + "SELECT 'Julio',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=7,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM MOVILIDAD mv \n"
                + "UNION \n"
                + "SELECT 'Agosto',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=8,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM MOVILIDAD mv \n"
                + "UNION \n"
                + "SELECT 'Septiembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=9,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM MOVILIDAD mv \n"
                + "UNION \n"
                + "SELECT 'Octubre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=10,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM MOVILIDAD mv \n"
                + "UNION \n"
                + "SELECT 'Noviembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=11,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM MOVILIDAD mv \n"
                + "UNION \n"
                + "SELECT 'Diciembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=12,1,NULL)) FROM MOVILIDAD mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM MOVILIDAD mv";

        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                    .addScalar("nombreMes", new StringType())
                    .addScalar("cantidad", new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(RptMovilidadEntranteMesEjecucionPojo.class));

            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<RptMovilidadEntranteFactBeneficiadaPojo> getMovilidadEntranteFactBenef(Integer anio) {
        String query = "SELECT f.NOMBRE_FACULTAD nombreFacultad, COUNT(mv.ID_MOVILIDAD) cantidad \n"
                + "FROM MOVILIDAD mv INNER JOIN MOVILIDAD_FACULTAD mf ON mv.ID_MOVILIDAD = mf.ID_MOVILIDAD \n"
                + "                  INNER JOIN FACULTAD f ON mf.ID_FACULTAD = f.ID_FACULTAD \n"
                + "                  INNER JOIN TIPO_MOVILIDAD tm ON mv.ID_TIPO_MOVILIDAD = tm.ID_TIPO_MOVILIDAD \n"
                + "WHERE mv.ID_ETAPA_MOVILIDAD =3 AND mv.ID_TIPO_MOVILIDAD =1     AND YEAR(mv.FECHA_INICIO)= " + anio + " GROUP BY f.NOMBRE_FACULTAD \n"
                + "UNION \n"
                + "SELECT un.NOMBRE_UNIDAD,COUNT(mov.ID_MOVILIDAD) \n"
                + "FROM MOVILIDAD mov INNER JOIN MOVILIDAD_UNIDAD mu ON mov.ID_MOVILIDAD = mu.ID_MOVILIDAD \n"
                + "                   INNER JOIN UNIDAD un ON mu.ID_UNIDAD = un.ID_UNIDAD\n"
                + "                   INNER JOIN TIPO_MOVILIDAD tmov ON mov.ID_TIPO_MOVILIDAD = tmov.ID_TIPO_MOVILIDAD \n"
                + "WHERE mov.ID_ETAPA_MOVILIDAD= 3 AND mov.ID_TIPO_MOVILIDAD =1 AND YEAR(mov.FECHA_INICIO)= " + anio + " GROUP BY un.NOMBRE_UNIDAD";

        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                    .addScalar("nombreFacultad", new StringType())
                    .addScalar("cantidad", new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(RptMovilidadEntranteFactBeneficiadaPojo.class));

            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Movilidad> getMovilidadesAnio(Integer anio) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT mv FROM Movilidad mv WHERE year(mv.fechaInicio) =:anio");
            q.setParameter("anio", anio);
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void desvincularReferente(Integer idMovilidad, Integer idPersona) {
        try {
            String query = "DELETE FROM PERSONA_MOVILIDAD WHERE ID_MOVILIDAD = " + idMovilidad + " AND ID_PERSONA = " + idPersona;
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query);
            q.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<PojoMovilidadDocumentacion> getMovilidadDocumentacion(Integer idMovSearch) {
        String query = "SELECT mv.ID_MOVILIDAD idMovilidad, pm.NOMBRE_PROGRAMA_MOVILIDAD nombrePrograma,per.NOMBRE_PERSONA nombrePersona,per.APELLIDO_PERSONA apellidoPersona,tpmv.NOMBRE_TIPO_MOVILIDAD nombreTipoMovilidad,pa.NOMBRE_PAIS paisOrigen, pai.NOMBRE_PAIS paisDestino,mv.FECHA_INICIO fechaEntrada,mv.FECHA_FIN fechaSalida ,etm.NOMBRE_ETAPA nombreEtapa  \n"
                + "FROM MOVILIDAD mv INNER JOIN PROGRAMA_MOVILIDAD pm ON mv.ID_PROGRAMA_MOVILIDAD = pm.ID_PROGRAMA_MOVILIDAD\n"
                + "INNER JOIN PERSONA_MOVILIDAD prmov  ON mv.ID_MOVILIDAD =prmov.ID_MOVILIDAD\n"
                + "INNER JOIN PERSONA per  ON prmov.ID_PERSONA = per.ID_PERSONA\n"
                + "INNER JOIN TIPO_MOVILIDAD tpmv ON mv.ID_TIPO_MOVILIDAD = tpmv.ID_TIPO_MOVILIDAD\n"
                + "INNER JOIN PAIS pa ON mv.ID_PAIS_ORIGEN = pa.ID_PAIS\n"
                + "INNER JOIN PAIS pai ON mv.ID_PAIS_DESTINO = pai.ID_PAIS "
                + "INNER JOIN ETAPA_MOVILIDAD etm ON mv.ID_ETAPA_MOVILIDAD = etm.ID_ETAPA   \n"
                + "WHERE prmov.ID_TIPO_PERSONA = 10  ";
        if (idMovSearch > 0) {
            query = query + " AND mv.ID_MOVILIDAD =" + idMovSearch;
        }
        query = query + " ORDER BY mv.FECHA_INGRESO DESC";
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
                    .addScalar("nombreEtapa", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(PojoMovilidadDocumentacion.class));

            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PersonaMovilidad isVinculadoReferente(Integer idMovilidad, Integer idPersona) {
        try {
            //String query = "SELECT * FROM PERSONA_MOVILIDAD WHERE ID_MOVILIDAD = " + idMovilidad + " AND ID_PERSONA = " + idPersona;
            //Query q = getSessionFactory().getCurrentSession().createSQLQuery(query);

            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT pm  FROM PersonaMovilidad pm WHERE pm.movilidad.idMovilidad =:idmov AND pm.persona.idPersona =:idper ");
            q.setParameter("idmov", idMovilidad);
            q.setParameter("idper", idPersona);
            return (PersonaMovilidad) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
//metodo nuevo

    public List<PojoMapaMovilidad> getBecastListToCharts(Integer tipoMovilidad, List<String> paisSelected, List<String> categoriaSelected, String desde, String hasta) {
        String campoUnion = "";
        if (tipoMovilidad == 1) {
            campoUnion = campoUnion + "m.ID_PAIS_ORIGEN";
        } else {
            campoUnion = campoUnion + "m.ID_PAIS_DESTINO";
        }

        String query = "SELECT\n"
                + "  p.ID_PAIS idPais,\n"
                + "  p.CODIGO_PAIS codigoPais,"
                + "  p.NOMBRE_PAIS nombrePais,"
                + "  count(*) cantidadMovilidades,"
                + "  SUM(m.VIATICOS+m.PAGO_DE_CURSO+m.VOLETO_AEREO) montoMovilidades "
                + " FROM MOVILIDAD m INNER JOIN PAIS p ON " + campoUnion + " = p.ID_PAIS"
                + " WHERE m.ID_TIPO_MOVILIDAD = " + tipoMovilidad
                + " and m.ID_ETAPA_MOVILIDAD=3 "
                + " and YEAR(m.FECHA_INICIO) BETWEEN " + Integer.parseInt(desde) + " and " + Integer.parseInt(hasta)
                + " and " + campoUnion + " IN " + "(" + String.join(",", paisSelected) + ")"
                + " and  m.ID_CATEGORIA IN " + "(" + String.join(",", categoriaSelected) + ")"
                + "GROUP BY " + campoUnion;
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                .addScalar("idPais", new IntegerType())
                .addScalar("codigoPais", new StringType())
                .addScalar("nombrePais", new StringType())
                .addScalar("cantidadMovilidades", new IntegerType())
                .addScalar("montoMovilidades", new DoubleType())
                .setResultTransformer(Transformers.aliasToBean(PojoMapaMovilidad.class));
        return q.list();
    }

    public List<PojoMovilidadMapaCategoria> getBecastListToChartsCate(Integer tipoMovilidad, List<String> paisSelected, List<String> categoriaSelected, String desde, String hasta) {
        String campoUnion = "";
        if (tipoMovilidad == 1) {
            campoUnion = campoUnion + "m.ID_PAIS_ORIGEN";
        } else {
            campoUnion = campoUnion + "m.ID_PAIS_DESTINO";
        }

        String query = "SELECT \n"
                + " cm.NOMBRE_CATEGORIA categoria,\n"
                + " count(*) cantidad"
                + " FROM MOVILIDAD m INNER JOIN PAIS p ON " + campoUnion + " = p.ID_PAIS"
                + " INNER JOIN CATEGORIA_MOVILIDAD cm on cm.ID_CATEGORIA_MOVILIDAD=m.ID_CATEGORIA"
                + " WHERE m.ID_TIPO_MOVILIDAD = " + tipoMovilidad
                + " and m.ID_ETAPA_MOVILIDAD=3 "
                + " and YEAR(m.FECHA_INICIO) BETWEEN " + Integer.parseInt(desde) + " and " + Integer.parseInt(hasta)
                + " and " + campoUnion + " IN " + "(" + String.join(",", paisSelected) + ")"
                + " and  m.ID_CATEGORIA IN " + "(" + String.join(",", categoriaSelected) + ")"
                + " GROUP BY cm.ID_CATEGORIA_MOVILIDAD";
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                .addScalar("categoria", new StringType())
                .addScalar("cantidad", new IntegerType())
                .setResultTransformer(Transformers.aliasToBean(PojoMovilidadMapaCategoria.class));
        return q.list();
    }
}
