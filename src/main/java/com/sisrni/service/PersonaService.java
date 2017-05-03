/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.PersonaDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Persona;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "personaService")
public class PersonaService extends GenericService<Persona, Integer> {

    @Autowired
    private PersonaDao personaDao;

    @Override
    public GenericDao<Persona, Integer> getDao() {
        return personaDao;
    }

    public Persona getByID(int id) {
        return personaDao.findById(id);
    }

    public List<Persona> getReferenteInternoByName(String query) {
        return personaDao.getReferenteInternoByName(query);
    }

    public List<Persona> getReferenteInternoByEmail(String email) {
        return personaDao.getReferenteInternoByEmail(email);
    }

    public List<Persona> getBecarioByEmail(String email) {
        return personaDao.getBecarioByEmail(email);
    }

    public List<Persona> getReferenteExternoByName(String query) {
        return personaDao.getReferenteExternoByName(query);
    }

    public List<Persona> getReferenteExternoByEmail(String email) {
        return personaDao.getReferenteExternoByEmail(email);
    }

    public List<Persona> getSolicitanteByName(String doc) {
        return personaDao.getSolicitanteByName(doc);
    }

    public Persona getPersonaByProyectoTipoPersona(Integer idProy, Integer idTipoPer) {
        return personaDao.getPersonaByProyectoTipoPersona(idProy, idTipoPer);
    }

    public Persona getPersonaByID(Integer id) {
        return personaDao.getPersonaByID(id);
    }

    public List<Persona> getPersonaList(boolean extranjero) {
        return personaDao.getPersonaList(extranjero);
    }

    public List<Persona> getPersonaList2(boolean extranjero) {
        return personaDao.getPersonaList2(extranjero);
    }

    public Persona getPersonaByDui(String dui) {
        return personaDao.getPersonaByDui(dui);
    }

    public Persona getPersonaByEmail(String email) {
        return personaDao.getPersonaByEmail(email);
    }

    public Persona getPersonaByPasaporte(String pasaporte) {
        return personaDao.getPersonaByPasaporte(pasaporte);
    }

    public Persona getBecarioByDoc(String doc) {
        return personaDao.getBecarioByDoc(doc);
    }

    public List<Persona> getPersonasByIdOrganismo(Integer idOrg) {
        return personaDao.getPersonasByIdOrganismo(idOrg);
    }

    public Persona getRfteFacultadBeneficiadaByDoc(String doc) {
        return personaDao.getRfteFacultadBeneficiadaByDoc(doc);
    }

    public List<Persona> getPersonaMovilidadSalienteByName(String query) {
        return personaDao.getPersonaMovilidadSalienteByName(query);
    }

    public List<Persona> getPersonaMovilidadSalienteByEmail(String email) {
        return personaDao.getPersonaMovilidadSalienteByEmail(email);
    }

    public List<Persona> getPersonaMovilidadReferenteByName(String query) {
        return personaDao.getPersonaMovilidadReferenteByName(query);
    }

    public List<Persona> getPersonaMovilidadReferenteByEmail(String email) {
        return personaDao.getPersonaMovilidadReferenteByEmail(email);
    }

    public List<Persona> getPersonaMovilidadEntranteByName(String query) {
        return personaDao.getPersonaMovilidadEntranteByName(query);
    }

    public List<Persona> getPersonaMovilidadEntranteByEmail(String email) {
        return personaDao.getPersonaMovilidadEntranteByEmail(email);
    }

    public Persona existePersona(String name, String lastName, String email) {
        return personaDao.existePersona(name, lastName, email);
    }

    public Persona existePersonaByMail(String email) {
        return personaDao.existePersonaByMail(email);
    }

    public List<Persona> getAllByIdDesc() {
        return personaDao.getAllByIdDesc();
    }
}
