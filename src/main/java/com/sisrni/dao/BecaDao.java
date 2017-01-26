/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Beca;
import com.sisrni.pojo.rpt.BecasGestionadasPojo;
import com.sisrni.pojo.rpt.PojoBeca;
import com.sisrni.pojo.rpt.PojoMapaInteractivoBecas;
import com.sisrni.pojo.rpt.RptDetalleBecasPojo;
import java.util.ArrayList;
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

    public List<PojoBeca> getBecas(Integer idBecaSearch) {
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
        if (idBecaSearch > 0) {
            query = query + " AND bec.ID_BECA=" + idBecaSearch;
        }

        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                    .addScalar("idBeca", new IntegerType())
                    .addScalar("anioGestion", new IntegerType())
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

    public List<BecasGestionadasPojo> getDataBecasGestionadasReportes(Integer desde, Integer hasta) {
        String query = "select b.ANIO_GESTION anio, count(b.ID_BECA) gestionadas,(SELECT COUNT(i.ID_BECA) FROM beca i WHERE i.OTORGADA=1 and i.ANIO_GESTION=b.ANIO_GESTION) becasOtorgadas,(SELECT SUM(a.MONTO_TOTAL) FROM beca a WHERE a.OTORGADA=1 and a.ANIO_GESTION=b.ANIO_GESTION) 'montoOtorgadas',(SELECT COUNT(c.ID_BECA) FROM beca c WHERE c.OTORGADA=0 and c.ANIO_GESTION=b.ANIO_GESTION) becasDenegadas,(SELECT SUM(r.MONTO_TOTAL) FROM beca r WHERE r.OTORGADA=0 and r.ANIO_GESTION=b.ANIO_GESTION) montoDenegadas  from beca b WHERE b.ANIO_GESTION BETWEEN\n"
                + desde + " and " + hasta + " \n"
                + "GROUP BY b.ANIO_GESTION ORDER BY b.ANIO_GESTION desc";
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                .addScalar("anio", new IntegerType())
                .addScalar("gestionadas", new IntegerType())
                .addScalar("becasOtorgadas", new IntegerType())
                .addScalar("montoOtorgadas", new DoubleType())
                .addScalar("becasDenegadas", new IntegerType())
                .addScalar("montoDenegadas", new DoubleType())
                .setResultTransformer(Transformers.aliasToBean(BecasGestionadasPojo.class));
        return q.list();
    }

    public List<BecasGestionadasPojo> getDataBecasGestionadasGroupPaisDestino(Integer desde, Integer hasta) {
        String query = "select p.NOMBRE_PAIS nombrePais,count(b.ID_BECA) gestionadas,\n"
                + "(SELECT COUNT(i.ID_BECA) FROM beca i WHERE i.OTORGADA=1 and i.ID_PAIS_DESTINO=b.ID_PAIS_DESTINO) becasOtorgadas,\n"
                + "(SELECT SUM(a.MONTO_TOTAL) FROM beca a WHERE a.OTORGADA=1 and a.ID_PAIS_DESTINO=b.ID_PAIS_DESTINO) montoOtorgadas,\n"
                + "(SELECT COUNT(c.ID_BECA) FROM beca c WHERE c.OTORGADA=0 and c.ID_PAIS_DESTINO=b.ID_PAIS_DESTINO) becasDenegadas,\n"
                + "(SELECT SUM(r.MONTO_TOTAL) FROM beca r WHERE r.OTORGADA=0 and r.ID_PAIS_DESTINO=b.ID_PAIS_DESTINO) montoDenegadas\n"
                + "from beca b INNER JOIN pais p ON b.ID_PAIS_DESTINO=p.ID_PAIS\n"
                + "WHERE b.ANIO_GESTION BETWEEN " + desde + " and " + hasta + "\n"
                + "GROUP BY b.ID_PAIS_DESTINO ORDER BY b.ID_PAIS_DESTINO asc";

        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                .addScalar("nombrePais", new StringType())
                .addScalar("gestionadas", new IntegerType())
                .addScalar("becasOtorgadas", new IntegerType())
                .addScalar("montoOtorgadas", new DoubleType())
                .addScalar("becasDenegadas", new IntegerType())
                .addScalar("montoDenegadas", new DoubleType())
                .setResultTransformer(Transformers.aliasToBean(BecasGestionadasPojo.class));
        return q.list();
    }

    public List<BecasGestionadasPojo> getDataBecasGestionadasGroupFacultad(Integer desde, Integer hasta) {
        String query = "SELECT f.NOMBRE_FACULTAD nombreFacultad, count(*) gestionadas,\n"
                + "SUM(if(b.OTORGADA=0,1,0)) becasDenegadas,\n"
                + "       SUM(if(b.OTORGADA=1,1,0)) becasOtorgadas,\n"
                + "       SUM(if(b.OTORGADA=1,b.MONTO_TOTAL,0)) montoOtorgadas\n"
                + "from BECA b\n"
                + "INNER JOIN persona_beca pb ON b.ID_BECA = pb.ID_BECA\n"
                + "INNER JOIN persona p ON pb.ID_PERSONA = p.ID_PERSONA\n"
                + "INNER JOIN carrera c ON p.ID_CARRERA = c.ID_CARRERA\n"
                + "INNER JOIN facultad f ON c.ID_FACULTAD = f.ID_FACULTAD\n"
                + "WHERE b.ANIO_GESTION BETWEEN " + desde + " AND " + hasta + "\n"
                + "GROUP BY f.ID_FACULTAD ORDER BY f.NOMBRE_FACULTAD ASC";

        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                .addScalar("nombreFacultad", new StringType())
                .addScalar("gestionadas", new IntegerType())
                .addScalar("becasOtorgadas", new IntegerType())
                .addScalar("montoOtorgadas", new DoubleType())
                .addScalar("becasDenegadas", new IntegerType())
                .setResultTransformer(Transformers.aliasToBean(BecasGestionadasPojo.class));
        return q.list();
    }

    public List<RptDetalleBecasPojo> getDetalleBecas(Integer desde, Integer hasta) {
        String query = "SELECT  p.NOMBRE_PERSONA nombre, p.APELLIDO_PERSONA apellido, f.NOMBRE_FACULTAD facultad,pro.NOMBRE_PROGRAMA programaBeca,b.ANIO_GESTION anio, x.NOMBRE_PAIS paisDestino,\n"
                + "org.NOMBRE_ORGANISMO universidadDestino,b.MONTO_TOTAL montoBeca\n"
                + "from beca b INNER JOIN persona_beca pb ON b.ID_BECA = pb.ID_BECA\n"
                + "INNER JOIN persona p ON pb.ID_PERSONA = p.ID_PERSONA\n"
                + "INNER JOIN carrera c ON p.ID_CARRERA = c.ID_CARRERA\n"
                + "INNER JOIN facultad f ON c.ID_FACULTAD = f.ID_FACULTAD\n"
                + "INNER JOIN programa_beca pro ON b.ID_PROGRAMA_BECA = pro.ID_PROGRAMA\n"
                + "INNER JOIN organismo org ON b.ID_UNIVERSIDAD = org.ID_ORGANISMO\n"
                + "INNER JOIN pais x ON org.ID_PAIS= x.ID_PAIS\n"
                + "WHERE pb.ID_TIPO_PERSONA = 6 AND b.OTORGADA=1\n"
                + "AND b.ANIO_GESTION BETWEEN " + desde + " AND " + hasta + "\n"
                + "ORDER BY p.NOMBRE_PERSONA,p.APELLIDO_PERSONA, b.ANIO_GESTION desc";

        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                .addScalar("nombre", new StringType())
                .addScalar("apellido", new StringType())
                .addScalar("facultad", new StringType())
                .addScalar("programaBeca", new StringType())
                .addScalar("anio", new IntegerType())
                .addScalar("paisDestino", new StringType())
                .addScalar("universidadDestino", new StringType())
                .addScalar("montoBeca", new DoubleType())
                .setResultTransformer(Transformers.aliasToBean(RptDetalleBecasPojo.class));
        return q.list();
    }

    public List<PojoMapaInteractivoBecas> getBecastListToCharts(List<String> paisSelected,  String desde, String hasta) {//List<String> tipoBecaSelected,
        String wherePais = "";
        String whereTipoBeca = "";
        String groupBy = " GROUP BY b.ID_PAIS_DESTINO";
        String limite = "";
        List<String> paisesFinales = new ArrayList<String>();

        if (paisSelected.size() > 0) {
            wherePais = wherePais + " AND pa.ID_PAIS IN (" + String.join(",", paisSelected) + ")";
        } else {
            limite += " LIMIT 5";
        }

//        if (tipoBecaSelected.size() > 0) {
//            whereTipoBeca += " AND b.ID_TIPO_BECA IN (" + String.join(",", tipoBecaSelected) + ")";
//        }

        String query = "SELECT pa.ID_PAIS idPais,\n"
                + "  pa.CODIGO_PAIS codigoPais,\n"
                + "  pa.NOMBRE_PAIS nombrePais,\n"
                + "  COUNT(b.ID_BECA) cantidadBecas,\n"
                + "  SUM(b.MONTO_TOTAL) montoCooperacion\n"
                + "FROM beca b INNER  JOIN pais pa   ON b.ID_PAIS_DESTINO= pa.ID_PAIS\n"
                + "WHERE b.ANIO_GESTION BETWEEN " + Integer.parseInt(desde) + " AND " + Integer.parseInt(hasta) + "\n"
                +wherePais +groupBy;//+whereTipoBeca
        
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                    .addScalar("idPais", new IntegerType())
                    .addScalar("codigoPais", new StringType())
                    .addScalar("nombrePais", new StringType())
                    .addScalar("montoCooperacion", new DoubleType())
                    .addScalar("cantidadBecas", new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(PojoMapaInteractivoBecas.class));
        
         List<PojoMapaInteractivoBecas> listPojos = q.list();

        return listPojos;
    }
}
