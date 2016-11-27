/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.ProyectoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Proyecto;
import com.sisrni.service.generic.GenericService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "proyectoService")
public class ProyectoService extends GenericService<Proyecto, Integer> {

    @Autowired
    private ProyectoDao proyectoDao;

    @Override
    public GenericDao<Proyecto, Integer> getDao() {
        return proyectoDao;
    }

    public List<Proyecto> getProjectListToCharts(String codigoPais, int idTipoProyecto, String desde, String hasta) {
        return proyectoDao.getProjectListToCharts(codigoPais, idTipoProyecto, desde, hasta);
    }

    public List<Proyecto> getProjectListToChartsByYear(String codigoPais, int idTipoProyecto, Date year) {
        return proyectoDao.getProjectListToChartsByYear(codigoPais, idTipoProyecto, year);
    }

}
