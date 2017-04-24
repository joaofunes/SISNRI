/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.PersonaProyecto;
import com.sisrni.model.Proyecto;
import com.sisrni.pojo.rpt.PojoMapaInteractivo;
import com.sisrni.pojo.rpt.PojoProyectosByTipo;
import com.sisrni.pojo.rpt.RptProyectoPojo;
import com.sisrni.pojo.rpt.RptProyectosFinanciadosPojo;
import com.sisrni.pojo.rpt.RptProyectosPorPaisPojo;
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
 * @author Joao
 */
@Repository(value = "proyectoDao")
public class ProyectoDao extends GenericDao<Proyecto, Integer> {

    public List<PojoMapaInteractivo> getProjectListToCharts(List<String> paisSelected, List<String> tipoProyectoSelected, String desde, String hasta) {
        String wherePais = "";
        String whereTipoProyecto = "";
        String groupBy = " GROUP BY pr.ID_PAIS_COOPERANTE";
        String limite = "";
        List<String> paisesFinales = new ArrayList<String>();

        if (paisSelected.size() > 0) {
            wherePais = wherePais + " AND pa.ID_PAIS IN (" + String.join(",", paisSelected) + ")";
        } else {
            limite += " LIMIT 5";
        }

        if (tipoProyectoSelected.size() > 0) {
            whereTipoProyecto += " AND pr.ID_TIPO_PROYECTO IN (" + String.join(",", tipoProyectoSelected) + ")";
        }

        String query = "SELECT pa.ID_PAIS as idPais,pa.CODIGO_PAIS as codigoPais, pa.NOMBRE_PAIS as nombrePais, SUM(pr.MONTO_PROYECTO) as montoCooperacion,COUNT(pr.ID_PROYECTO) as cantidadProyectos\n"
                + " FROM PROYECTO pr INNER JOIN PAIS pa ON pr.ID_PAIS_COOPERANTE = pa.ID_PAIS\n"
                + " WHERE pr.ANIO_GESTION BETWEEN " + Integer.parseInt(desde) + " AND " + Integer.parseInt(hasta) + "\n"
                + wherePais + whereTipoProyecto + groupBy + limite;

        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                    .addScalar("idPais", new IntegerType())
                    .addScalar("codigoPais", new StringType())
                    .addScalar("nombrePais", new StringType())
                    .addScalar("montoCooperacion", new DoubleType())
                    .addScalar("cantidadProyectos", new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(PojoMapaInteractivo.class));

            List<PojoMapaInteractivo> listPojos = q.list();

            if (listPojos.size() > 0) {
                for (PojoMapaInteractivo pj : listPojos) {
                    paisesFinales.add(pj.idPais.toString().trim());
                }
                String qt = "SELECT tp.ID_TIPO_PROYECTO as idTipoProyecto,tp.NOMBRE_TIPO_PROYECTO as nombreTipoProyecto,count(pr.ID_PROYECTO) as cantidadProyectos FROM PROYECTO pr INNER JOIN TIPO_PROYECTO tp ON pr.ID_TIPO_PROYECTO = tp.ID_TIPO_PROYECTO\n"
                        + " WHERE pr.ANIO_GESTION BETWEEN " + Integer.parseInt(desde) + " AND " + Integer.parseInt(hasta) + "\n"
                        + "AND pr.ID_PAIS_COOPERANTE IN(" + String.join(",", paisesFinales) + ")" + whereTipoProyecto + " GROUP BY tp.ID_TIPO_PROYECTO";

                Query rtp = getSessionFactory().getCurrentSession().createSQLQuery(qt)
                        .addScalar("idTipoProyecto", new IntegerType())
                        .addScalar("nombreTipoProyecto", new StringType())
                        .addScalar("cantidadProyectos", new IntegerType())
                        .setResultTransformer(Transformers.aliasToBean(PojoProyectosByTipo.class));

                List<PojoProyectosByTipo> listTipos = rtp.list();

                for (PojoMapaInteractivo pj : listPojos) {
                    String qp = "SELECT * FROM PROYECTO pr \n"
                            + " WHERE pr.ANIO_GESTION BETWEEN " + Integer.parseInt(desde) + " AND " + Integer.parseInt(hasta) + "\n"
                            + "AND pr.ID_PAIS_COOPERANTE=" + pj.getIdPais() + whereTipoProyecto;

                    //String qp = "from Proyect pr Where pr.idPaisCooperante='" + pj.getCodigoPais() + "' and pr.idTipoProyecto in (" + String.join(",", tipoProyectoSelected) + ") and pr.anioGestion between " + Integer.parseInt(desde) + " AND " + Integer.parseInt(hasta);
                    Query r = getSessionFactory().getCurrentSession().createSQLQuery(qp).addEntity(Proyecto.class);
                    pj.setProjectList(r.list());
                    pj.setSeries(listTipos);
                }
            }

            return listPojos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Proyecto getProyectoByID(Integer id) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Proyecto a WHERE a.idProyecto =:id");
            q.setParameter("id", id);
            return (Proyecto) q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //metodo para cargar la tabla de gestion, todos los proyectos registrados

    public List<Proyecto> getAllProyecto() {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Proyecto a ORDER BY a.idProyecto DESC");
            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<RptProyectoPojo> getDataProyectosGestionadosReportes() {
        String query = "select b.NOMBRE_PROYECTO nombre, b.OBJETIVO objetivo FROM proyecto b";
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                .addScalar("nombre", new StringType())
                .addScalar("objetivo", new StringType())
                .setResultTransformer(Transformers.aliasToBean(RptProyectoPojo.class));
        return q.list();
    }

    public List<Proyecto> getProyectosDesdeHasta(Integer desde, Integer hasta) {
        String query = "select p from Proyecto p where p.anioGestion between :desde and :hasta";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        q.setParameter("desde", desde);
        q.setParameter("hasta", hasta);
        return q.list();

    }

    public List<RptProyectosFinanciadosPojo> getDataProyectosFinanciadosReportes(Integer desde, Integer hasta) {
        String query = "SELECT p.ANIO_GESTION as anioGestion, sum(p.MONTO_PROYECTO) as suma from PROYECTO p where p.ANIO_GESTION BETWEEN \n"
                + desde + " AND " + hasta + " GROUP BY p.ANIO_GESTION \n"
                + " ORDER BY p.ANIO_GESTION asc ";
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                .addScalar("anioGestion", new IntegerType())
                .addScalar("suma", new DoubleType())
                .setResultTransformer(Transformers.aliasToBean(RptProyectosFinanciadosPojo.class));
        return q.list();
    }

    public List<RptProyectosPorPaisPojo> getDataProyectosPorPais(Integer desde, Integer hasta) {
        String query = "SELECT pa.NOMBRE_PAIS as nombrePais, sum(p.MONTO_PROYECTO) as suma from PROYECTO p join PAIS pa on (p.ID_PAIS_COOPERANTE=pa.ID_PAIS) where p.ANIO_GESTION BETWEEN \n"
                + desde + " AND " + hasta + " GROUP BY pa.NOMBRE_PAIS \n"
                + " ORDER BY pa.NOMBRE_PAIS asc ";
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                .addScalar("nombrePais", new StringType())
                .addScalar("suma", new DoubleType())
                .setResultTransformer(Transformers.aliasToBean(RptProyectosPorPaisPojo.class));
        return q.list();
    }

    public List<RptProyectosFinanciadosPojo> getDataProyectosTotales(Integer desde, Integer hasta) {
        String query = "SELECT p.ANIO_GESTION as anioGestion, count(p.ID_PROYECTO) as suma from PROYECTO p where p.ANIO_GESTION BETWEEN \n"
                + desde + " AND " + hasta + " GROUP BY p.ANIO_GESTION \n"
                + " ORDER BY p.ANIO_GESTION asc ";
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                .addScalar("anioGestion", new IntegerType())
                .addScalar("suma", new DoubleType())
                .setResultTransformer(Transformers.aliasToBean(RptProyectosFinanciadosPojo.class));
        return q.list();
    }

    public void desvincularInterno(Integer proyectoId, Integer personaId) {
        try {
            String query = "Delete from PERSONA_PROYECTO WHERE ID_PERSONA=" + personaId + " AND ID_PROYECTO=" + proyectoId;
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(query);
            q.executeUpdate();
        } catch (Exception e) {
        }

    }

    //metodo que retorna si la persona esta vinculada al proyecto

    public PersonaProyecto isVinculadoPersona(Integer idProyecto, Integer idPersona) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT pp  FROM PersonaProyecto pp WHERE pp.proyecto.idProyecto =:idproyecto AND pp.persona.idPersona =:idpersona ");
            q.setParameter("idproyecto", idProyecto);
            q.setParameter("idpersona", idPersona);
            return (PersonaProyecto) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    //Elimina todos los objetos de las intermedia persona de proyecto

    public void eliminarIntermediaPersona(Proyecto proyecto) {
        String query = "Delete from PERSONA_PROYECTO WHERE ID_PROYECTO=" + proyecto.getIdProyecto();
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query);
        q.executeUpdate();
    }

    //Elimina todos los objetos de las intermedia facultad de proyecto

    public void eliminarIntermediaFacultad(Proyecto proyecto) {
        String query = "Delete from FACULTAD_PROYECTO WHERE ID_PROYECTO=" + proyecto.getIdProyecto();
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query);
        q.executeUpdate();
    }

    //Elimina todos los objetos de las intermedia area de conocimiento de proyecto

    public void eliminarIntermediaArea(Proyecto proyecto) {
        String query = "Delete from PROYECTO_AREA WHERE ID_PROYECTO=" + proyecto.getIdProyecto();
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query);
        q.executeUpdate();
    }

    //Elimina todos los objetos de las intermedia organismo de proyecto

    public void eliminarIntermediaOrganismo(Proyecto proyecto) {
        String query = "Delete from PROYECTO_ORGANISMO WHERE ID_PROYECTO=" + proyecto.getIdProyecto();
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query);
        q.executeUpdate();
    }
}
