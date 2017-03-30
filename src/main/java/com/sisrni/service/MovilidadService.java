package com.sisrni.service;

import com.sisrni.dao.MovilidadDao;
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
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service(value = "movilidadService")
public class MovilidadService extends GenericService<Movilidad, Integer> {

    @Autowired
    private MovilidadDao movilidadDao;

    @Override
    public GenericDao<Movilidad, Integer> getDao() {
        return movilidadDao;
    }

    public List<PojoMovilidadAdm> getMovilidadAdm(Integer idMovSearch) {
        return movilidadDao.getMovilidadAdm(idMovSearch);
    }

    public List<RptMovilidadesSegunEtapaPojo> getCantidadMovilidadesSegunEtapa(Integer anio) {
        return movilidadDao.getCantidadMovilidadesSegunEtapa(anio);
    }

    public List<RptMovilidadesPorFacultadEjecutadasAnioPojo> getCantidadMovilidadesFacultad(Integer anio) {
        return movilidadDao.getCantidadMovilidadesFacultad(anio);
    }

    public List<RptMovilidadesMesPojo> getMovilidadesMes(Integer anio) {
        return movilidadDao.getMovilidadesMes(anio);
    }

    public List<RptMovilidadSalientePaisDestinoPojo> getMovilidadesSalientesPaisDestino(Integer anio) {
        return movilidadDao.getMovilidadesSalientesPaisDestino(anio);
    }

    public List<RptMovilidadSalienteMesPojo> getMovilidadesSalientesMes(Integer anio) {
        return movilidadDao.getMovilidadesSalientesMes(anio);
    }

    public List<RptMovilidadSalienteFactBeneficiadaPojo> getMoviSalientesFactBenef(Integer anio) {
        return movilidadDao.getMoviSalientesFactBenef(anio);
    }

    public List<RptMovilidadEntrantePaisPojo> getMovilEntrantesPaisOrigen(Integer anio) {
        return movilidadDao.getMovilEntrantesPaisOrigen(anio);
    }

    public List<RptMovilidadEntranteMesEjecucionPojo> getMovilidadEntranteMes(Integer anio) {
        return movilidadDao.getMovilidadEntranteMes(anio);
    }

    public List<RptMovilidadEntranteFactBeneficiadaPojo> getMovilidadEntranteFactBenef(Integer anio) {
        return movilidadDao.getMovilidadEntranteFactBenef(anio);
    }

    public List<Movilidad> getMovilidadesAnio(Integer anio) {
        return movilidadDao.getMovilidadesAnio(anio);
    }

    public void desvincularReferente(Integer idMovilidad, Integer idPersona) {
        movilidadDao.desvincularReferente(idMovilidad, idPersona);
    }

    public List<PojoMovilidadDocumentacion> getMovilidadDocumentacion(Integer idMovSearch) {
        return movilidadDao.getMovilidadDocumentacion(idMovSearch);
    }

    public PersonaMovilidad isVinculadoReferente(Integer idMovilidad, Integer idPersona) {
        return movilidadDao.isVinculadoReferente(idMovilidad, idPersona);
    }

    public List<PojoMapaMovilidad> getBecastListToCharts(Integer tipoMovilidad, List<String> paisSelected, List<String> categoriaSelected, String desde, String hasta) {
        return movilidadDao.getBecastListToCharts(tipoMovilidad, paisSelected, categoriaSelected, desde, hasta);
    }

    public List<PojoMovilidadMapaCategoria> getBecastListToChartsCate(Integer tipoMovilidad, List<String> paisSelected, List<String> categoriaSelected, String desde, String hasta) {
        return movilidadDao.getBecastListToChartsCate(tipoMovilidad, paisSelected, categoriaSelected, desde, hasta);
    }
}
