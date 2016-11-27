/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Proyecto;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "proyectoDao")
public class ProyectoDao extends GenericDao<Proyecto, Integer> {

    public List<Proyecto> getProjectListToCharts(String codigoPais, int idTipoProyecto, String desde, String hasta) {
        String query = "SELECT pr FROM Proyecto as pr WHERE 1=1";
        if (idTipoProyecto > 0) {
            query = query + " and pr.idTipoProyecto.idTipoProyecto=:idTipoProyecto";
        }
        if (desde != null && hasta != null) {
            query = query + " and to_char(pr.fechaGestion,'YYYY') BETWEEN :desde and :hasta";
        }

        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        q.setParameter("idTipoProyecto", idTipoProyecto);
        q.setParameter("desde", desde);
        q.setParameter("hasta", hasta);
        return q.list();
    }

    public List<Proyecto> getProjectListToChartsByYear(String codigoPais, int idTipoProyecto, Date year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(year);
        int yeard = cal.get(Calendar.YEAR);
        String yearString = "" + yeard;

        String query = "SELECT pr FROM Proyecto as pr WHERE 1=1";
        if (idTipoProyecto > 0) {
            query = query + " and pr.idTipoProyecto.idTipoProyecto=:idTipoProyecto";
        }

        query = query + "to_chart(pr.fechaGestion,'YYYY')= :yearString";

        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        q.setParameter("idTipoProyecto", idTipoProyecto);
        q.setParameter("yearString", yearString);

        return q.list();
    }
}
