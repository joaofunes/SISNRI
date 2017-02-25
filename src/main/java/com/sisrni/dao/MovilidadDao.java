/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Movilidad;
import com.sisrni.pojo.rpt.PojoMovilidadAdm;
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
                + "FROM movilidad mv INNER JOIN programa_movilidad pm ON mv.ID_PROGRAMA_MOVILIDAD = pm.ID_PROGRAMA_MOVILIDAD\n"
                + "INNER JOIN persona_movilidad prmov  ON mv.ID_MOVILIDAD =prmov.ID_MOVILIDAD\n"
                + "INNER JOIN persona per  ON prmov.ID_PERSONA = per.ID_PERSONA\n"
                + "INNER JOIN tipo_movilidad tpmv ON mv.ID_TIPO_MOVILIDAD = tpmv.ID_TIPO_MOVILIDAD\n"
                + "INNER JOIN pais pa ON mv.ID_PAIS_ORIGEN = pa.ID_PAIS\n"
                + "INNER JOIN pais pai ON mv.ID_PAIS_DESTINO = pai.ID_PAIS "
                + "INNER JOIN etapa_movilidad etm ON mv.ID_ETAPA_MOVILIDAD = etm.ID_ETAPA   \n"
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
                + "WHERE mv.ID_ETAPA_MOVILIDAD =3 AND YEAR(mv.FECHA_INICIO)= " + anio + " GROUP BY tm.NOMBRE_TIPO_MOVILIDAD \n"
                + "UNION \n"
                + "SELECT eta.NOMBRE_ETAPA , COUNT(mov.ID_MOVILIDAD) \n"
                + "FROM movilidad mov INNER JOIN etapa_movilidad eta ON mov.ID_ETAPA_MOVILIDAD = eta.ID_ETAPA \n"
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
                + "FROM movilidad mv INNER JOIN movilidad_facultad mf ON mv.ID_MOVILIDAD = mf.ID_MOVILIDAD \n"
                + "                  INNER JOIN facultad f ON mf.ID_FACULTAD = f.ID_FACULTAD \n"
                + "                  INNER JOIN tipo_movilidad tm ON mv.ID_TIPO_MOVILIDAD = tm.ID_TIPO_MOVILIDAD \n"
                + "WHERE mv.ID_ETAPA_MOVILIDAD =3  AND YEAR(mv.FECHA_INICIO)= " + anio + " GROUP BY f.NOMBRE_FACULTAD \n"
                + "UNION \n"
                + "SELECT un.NOMBRE_UNIDAD,COUNT(mov.ID_MOVILIDAD) \n"
                + "FROM movilidad mov INNER JOIN movilidad_unidad mu ON mov.ID_MOVILIDAD = mu.ID_MOVILIDAD \n"
                + "                  INNER JOIN unidad un ON mu.ID_UNIDAD = un.ID_UNIDAD \n"
                + "                  INNER JOIN tipo_movilidad tmov ON mov.ID_TIPO_MOVILIDAD = tmov.ID_TIPO_MOVILIDAD \n"
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
        String query = "SELECT 'Enero' nombreMes,(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=1,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 ) cantidad FROM movilidad \n"
                + "UNION\n"
                + "SELECT 'Febrero',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=2,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM movilidad\n"
                + "UNION\n"
                + "SELECT 'Marzo',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=3,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM movilidad\n"
                + "UNION\n"
                + "SELECT 'Abril',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=4,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM movilidad mv\n"
                + "UNION\n"
                + "SELECT 'Mayo',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=5,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM movilidad mv\n"
                + "UNION\n"
                + "SELECT 'Junio',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=6,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM movilidad mv\n"
                + "UNION\n"
                + "SELECT 'Julio',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=7,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM movilidad mv\n"
                + "UNION\n"
                + "SELECT 'Agosto',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=8,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM movilidad mv\n"
                + "UNION\n"
                + "SELECT 'Septiembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=9,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM movilidad mv\n"
                + "UNION\n"
                + "SELECT 'Octubre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=10,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM movilidad mv\n"
                + "UNION\n"
                + "SELECT 'Noviembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=11,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM movilidad mv\n"
                + "UNION\n"
                + "SELECT 'Diciembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=12,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3) FROM movilidad mv";

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
                + "FROM movilidad mv INNER JOIN tipo_movilidad tm ON mv.ID_TIPO_MOVILIDAD = tm.ID_TIPO_MOVILIDAD \n"
                + "                  INNER JOIN pais p ON mv.ID_PAIS_DESTINO = p.ID_PAIS \n"
                + "                  INNER JOIN etapa_movilidad et ON mv.ID_ETAPA_MOVILIDAD = et.ID_ETAPA  \n"
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
        String query = "SELECT 'Enero' nombreMes,(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=1,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) cantidad FROM movilidad \n"
                + "UNION \n"
                + "SELECT 'Febrero',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=2,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM movilidad \n"
                + "UNION \n"
                + "SELECT 'Marzo',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=3,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM movilidad \n"
                + "UNION \n"
                + "SELECT 'Abril',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=4,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM movilidad mv \n"
                + "UNION \n"
                + "SELECT 'Mayo',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=5,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM movilidad mv \n"
                + "UNION \n"
                + "SELECT 'Junio',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=6,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM movilidad mv \n"
                + "UNION \n"
                + "SELECT 'Julio',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=7,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM movilidad mv \n"
                + "UNION \n"
                + "SELECT 'Agosto',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=8,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM movilidad mv \n"
                + "UNION \n"
                + "SELECT 'Septiembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=9,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM movilidad mv \n"
                + "UNION \n"
                + "SELECT 'Octubre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=10,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM movilidad mv \n"
                + "UNION \n"
                + "SELECT 'Noviembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=11,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM movilidad mv \n"
                + "UNION \n"
                + "SELECT 'Diciembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=12,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + anio + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 2) FROM movilidad mv";

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
                + "FROM movilidad mv INNER JOIN movilidad_facultad mf ON mv.ID_MOVILIDAD = mf.ID_MOVILIDAD \n"
                + "                                  INNER JOIN facultad f ON mf.ID_FACULTAD = f.ID_FACULTAD \n"
                + "                                  INNER JOIN tipo_movilidad tm ON mv.ID_TIPO_MOVILIDAD = tm.ID_TIPO_MOVILIDAD \n"
                + "WHERE mv.ID_ETAPA_MOVILIDAD =3 AND mv.ID_TIPO_MOVILIDAD =2     AND YEAR(mv.FECHA_INICIO)= " + anio + " GROUP BY f.NOMBRE_FACULTAD \n"
                + "UNION \n"
                + "SELECT un.NOMBRE_UNIDAD,COUNT(mov.ID_MOVILIDAD) \n"
                + "FROM movilidad mov INNER JOIN movilidad_unidad mu ON mov.ID_MOVILIDAD = mu.ID_MOVILIDAD \n"
                + "                                  INNER JOIN unidad un ON mu.ID_UNIDAD = un.ID_UNIDAD \n"
                + "                                  INNER JOIN tipo_movilidad tmov ON mov.ID_TIPO_MOVILIDAD = tmov.ID_TIPO_MOVILIDAD \n"
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
                + "FROM movilidad mv INNER JOIN tipo_movilidad tm ON mv.ID_TIPO_MOVILIDAD = tm.ID_TIPO_MOVILIDAD \n"
                + "                  INNER JOIN pais p ON mv.ID_PAIS_ORIGEN = p.ID_PAIS \n"
                + "                  INNER JOIN etapa_movilidad et ON mv.ID_ETAPA_MOVILIDAD = et.ID_ETAPA  \n"
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
        String query = "SELECT 'Enero' nombreMes,(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=1,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + 2017 + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) cantidad FROM movilidad \n"
                + "UNION \n"
                + "SELECT 'Febrero',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=2,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + 2017 + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM movilidad \n"
                + "UNION \n"
                + "SELECT 'Marzo',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=3,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + 2017 + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM movilidad \n"
                + "UNION \n"
                + "SELECT 'Abril',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=4,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + 2017 + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM movilidad mv \n"
                + "UNION \n"
                + "SELECT 'Mayo',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=5,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + 2017 + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM movilidad mv \n"
                + "UNION \n"
                + "SELECT 'Junio',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=6,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + 2017 + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM movilidad mv \n"
                + "UNION \n"
                + "SELECT 'Julio',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=7,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + 2017 + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM movilidad mv \n"
                + "UNION \n"
                + "SELECT 'Agosto',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=8,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + 2017 + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM movilidad mv \n"
                + "UNION \n"
                + "SELECT 'Septiembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=9,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + 2017 + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM movilidad mv \n"
                + "UNION \n"
                + "SELECT 'Octubre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=10,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + 2017 + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM movilidad mv \n"
                + "UNION \n"
                + "SELECT 'Noviembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=11,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + 2017 + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM movilidad mv \n"
                + "UNION \n"
                + "SELECT 'Diciembre',(SELECT COUNT(IF(MONTH(mov.FECHA_INICIO)=12,1,NULL)) FROM movilidad mov WHERE YEAR(mov.FECHA_INICIO) = " + 2017 + " AND mov.ID_ETAPA_MOVILIDAD=3 AND mov.ID_TIPO_MOVILIDAD = 1) FROM movilidad mv";

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
                + "FROM movilidad mv INNER JOIN movilidad_facultad mf ON mv.ID_MOVILIDAD = mf.ID_MOVILIDAD \n"
                + "                  INNER JOIN facultad f ON mf.ID_FACULTAD = f.ID_FACULTAD \n"
                + "                  INNER JOIN tipo_movilidad tm ON mv.ID_TIPO_MOVILIDAD = tm.ID_TIPO_MOVILIDAD \n"
                + "WHERE mv.ID_ETAPA_MOVILIDAD =3 AND mv.ID_TIPO_MOVILIDAD =1     AND YEAR(mv.FECHA_INICIO)= " + anio + " GROUP BY f.NOMBRE_FACULTAD \n"
                + "UNION \n"
                + "SELECT un.NOMBRE_UNIDAD,COUNT(mov.ID_MOVILIDAD) \n"
                + "FROM movilidad mov INNER JOIN movilidad_unidad mu ON mov.ID_MOVILIDAD = mu.ID_MOVILIDAD \n"
                + "                   INNER JOIN unidad un ON mu.ID_UNIDAD = un.ID_UNIDAD\n"
                + "                   INNER JOIN tipo_movilidad tmov ON mov.ID_TIPO_MOVILIDAD = tmov.ID_TIPO_MOVILIDAD \n"
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

}
