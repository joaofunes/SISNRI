/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Organismo;
import com.sisrni.model.Persona;
import com.sisrni.model.Telefono;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "telefonoDao")
public class TelefonoDao extends GenericDao<Telefono, Integer> {
    
    
    public List<Telefono> getTelefonosByPersona(Persona persona) {
        try {
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM  Telefono a JOIN FETCH  a.idTipoTelefono tipo WHERE a.idPersona.idPersona = :idPersona ");
             q.setParameter("idPersona",persona.getIdPersona());
       
             return q.list();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
        public List<Telefono> getTelefonosByOrganismo(Organismo organismo) {
        try {
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM  Telefono a JOIN FETCH  a.idTipoTelefono tipo WHERE a.idOrganismo.idOrganismo = :idOrganismo ");
             q.setParameter("idOrganismo",organismo.getIdOrganismo());
       
             return q.list();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
        
        
        
    public  String getMask(String idPais) {
		try {
			PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
			// Obteniendo un numero de ejemplo
			PhoneNumber telefono = phoneUtil.getExampleNumber(idPais);
			// Formateando el numero
			String numeroTel = phoneUtil.format(telefono, PhoneNumberFormat.INTERNATIONAL);
			
			//System.out.println(numeroTel);
			
			// Extrayendo codigo y numero
			String cod = numeroTel.substring(0, numeroTel.indexOf(" "));
			String number = numeroTel.substring(numeroTel.indexOf(" "), numeroTel.length());

			// Reemplazando numeros por #
			number = number.replace('0', '#').replace('1', '#').replace('2', '#').replace('3', '#').replace('4', '#')
					.replace('5', '#').replace('6', '#').replace('7', '#').replace('8', '#').replace('9', '#');

			String fullNumber = cod + number;
			return fullNumber;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}
    

}
