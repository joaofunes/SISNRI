/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.PropuestaConvenio;
import com.sisrni.model.TipoPropuestaConvenio;
import com.sisrni.pojo.rpt.PojoConvenioEstado;
import com.sisrni.pojo.rpt.PojoPropuestaConvenio;
import com.sisrni.pojo.rpt.RptBitacoraEstadosPojo;
import com.sisrni.pojo.rpt.RptConteoConveniosPorTipoPojo;
import com.sisrni.pojo.rpt.RptConveniosPorAnioPojo;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "propuestaConvenioDao")
public class PropuestaConvenioDao extends GenericDao<PropuestaConvenio, Integer> {

    public List<PropuestaConvenio> getPropuestaConvenioByTipoPropuesta(TipoPropuestaConvenio tipoPropuestaConvenio) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM PropuestaConvenio a JOIN FETCH a.idTipoPropuestaConvenio tipo WHERE tipo.nombrePropuestaConvenio =:name");
            q.setParameter("name", tipoPropuestaConvenio.getNombrePropuestaConvenio());
            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PropuestaConvenio> getConvenios() {
        try {
            String sql = "SELECT PRO.ID_PROPUESTA idPropuesta,NOMBRE_PROPUESTA nombrePropuesta,FINALIDAD_PROPUESTA finalidadPropuesta,VIGENCIA vigencia,ID_TIPO_PROPUESTA_CONVENIO\n"
                    + "FROM PROPUESTA_CONVENIO PRO \n"
                    + "INNER JOIN PROPUESTA_ESTADO P_ESTADO \n"
                    + "ON PRO.ID_PROPUESTA=P_ESTADO.ID_PROPUESTA \n"
                    + "INNER JOIN ESTADO \n"
                    + "ON ESTADO.ID_ESTADO=P_ESTADO.ID_ESTADO\n"
                    + "WHERE PRO.VIGENCIA IS NOT NULL\n"
                    + "AND ESTADO.NOMBRE_ESTADO='FIRMADO'"
                    + "AND PRO.ACTIVO IS TRUE";

            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql)
                    .addScalar("idPropuesta", new IntegerType())
                    .addScalar("nombrePropuesta", new StringType())
                    .addScalar("finalidadPropuesta", new StringType())
                    .addScalar("vigencia", new DateType())
                    //.addScalar("ID_TIPO_PROPUESTA_CONVENIO",new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(PropuestaConvenio.class));

            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * listdo convenio marcos
     *
     * @return
     */
    public List<PropuestaConvenio> getConveniosMarcos() {
        try {
            String sql = "SELECT PRO.ID_PROPUESTA idPropuesta,NOMBRE_PROPUESTA nombrePropuesta,FINALIDAD_PROPUESTA finalidadPropuesta,VIGENCIA vigencia,ID_TIPO_PROPUESTA_CONVENIO\n"
                    + "FROM PROPUESTA_CONVENIO PRO \n"
                    + "INNER JOIN PROPUESTA_ESTADO P_ESTADO \n"
                    + "ON PRO.ID_PROPUESTA=P_ESTADO.ID_PROPUESTA \n"
                    + "INNER JOIN ESTADO \n"
                    + "ON ESTADO.ID_ESTADO=P_ESTADO.ID_ESTADO\n"
                    + "WHERE PRO.VIGENCIA IS NOT NULL\n"
                    + "AND ESTADO.NOMBRE_ESTADO='FIRMADO' "
                    + "AND PRO.ID_TIPO_PROPUESTA_CONVENIO=2 "
                    + "AND PRO.ACTIVO IS TRUE";

            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql)
                    .addScalar("idPropuesta", new IntegerType())
                    .addScalar("nombrePropuesta", new StringType())
                    .addScalar("finalidadPropuesta", new StringType())
                    .addScalar("vigencia", new DateType())
                    //.addScalar("ID_TIPO_PROPUESTA_CONVENIO",new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(PropuestaConvenio.class));

            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * obtiene todos las propuestas convenios SQL sin id solicitante
     *
     * @return
     */
    public List<PojoPropuestaConvenio> getAllPropuestaConvenioSQL() {

        String sql = "SELECT * FROM  \n"
                + "(SELECT P_CONVENIO.NOMBRE_PROPUESTA,P_CONVENIO.FINALIDAD_PROPUESTA,\n"
                + "T_PRO_CONVE.NOMBRE_PROPUESTA_CONVENIO AS TIPO_CONVENIO,STA.NOMBRE_ESTADO,P_CONVENIO.VIGENCIA,\n"
                + "P_CONVENIO.ID_PROPUESTA,\n"
                + "P_ESTADO.ID_ESTADO,\n"
                + "P_CONVENIO.FECHA_INGRESO FECHA_INGRESO\n"
                + "FROM PROPUESTA_CONVENIO P_CONVENIO\n"
                + "INNER JOIN TIPO_PROPUESTA_CONVENIO T_PRO_CONVE\n"
                + "ON P_CONVENIO.ID_TIPO_PROPUESTA_CONVENIO = T_PRO_CONVE.ID_TIPO_PROPUESTA \n"
                + "INNER JOIN PROPUESTA_ESTADO P_ESTADO \n"
                + "ON P_CONVENIO.ID_PROPUESTA = P_ESTADO.ID_PROPUESTA\n"
                + "INNER JOIN ESTADO STA\n"
                + "ON P_ESTADO.ID_ESTADO=STA.ID_ESTADO\n"
                + "WHERE  P_CONVENIO.VIGENCIA IS NULL\n"
                + "AND STA.NOMBRE_ESTADO <> 'FIRMADO'  ORDER BY FECHA_INGRESO DESC ) TB_CONVENIO\n"
                + "\n"
                + "LEFT JOIN\n"
                + "\n"
                + "(SELECT TB_SOLICITANTE.SOLICITANTE,TB_INTERNO.INTERNO,TB_EXTERNO.EXTERNO,TB_SOLICITANTE.PROPUESTA ,TB_SOLICITANTE.ID_SOLICITANTE,TB_INTERNO.ID_REF_INTERNO,TB_EXTERNO.ID_REF_EXTERNO \n"
                + "FROM \n"
                + "(SELECT CONCAT(PRS.NOMBRE_PERSONA,' ',PRS.APELLIDO_PERSONA) AS SOLICITANTE,PRS_PROP.ID_PROPUESTA AS PROPUESTA,PRS.ID_PERSONA AS ID_SOLICITANTE\n"
                + "FROM PERSONA_PROPUESTA PRS_PROP \n"
                + "INNER JOIN PERSONA PRS\n"
                + "ON PRS_PROP.ID_PERSONA=PRS.ID_PERSONA\n"
                + "INNER JOIN TIPO_PERSONA TP_PRS\n"
                + "ON PRS_PROP.ID_TIPO_PERSONA=TP_PRS.ID_TIPO_PERSONA\n"
                + "WHERE TP_PRS.NOMBRE_TIPO_PERSONA='SOLICITANTE') TB_SOLICITANTE\n"
                + "LEFT JOIN\n"
                + "(SELECT CONCAT(PRS.NOMBRE_PERSONA,' ',PRS.APELLIDO_PERSONA) AS INTERNO,PRS_PROP.ID_PROPUESTA AS PROPUESTA, PRS.ID_PERSONA AS ID_REF_INTERNO\n"
                + "FROM PERSONA_PROPUESTA PRS_PROP \n"
                + "INNER JOIN PERSONA PRS\n"
                + "ON PRS_PROP.ID_PERSONA=PRS.ID_PERSONA\n"
                + "INNER JOIN TIPO_PERSONA TP_PRS\n"
                + "ON PRS_PROP.ID_TIPO_PERSONA=TP_PRS.ID_TIPO_PERSONA\n"
                + "WHERE TP_PRS.NOMBRE_TIPO_PERSONA='REFERENTE INTERNO') TB_INTERNO\n"
                + "ON TB_SOLICITANTE.PROPUESTA=TB_INTERNO.PROPUESTA\n"
                + "LEFT JOIN\n"
                + "(SELECT CONCAT(PRS.NOMBRE_PERSONA,' ',PRS.APELLIDO_PERSONA) AS EXTERNO,PRS_PROP.ID_PROPUESTA AS PROPUESTA,PRS.ID_PERSONA AS ID_REF_EXTERNO\n"
                + "FROM PERSONA_PROPUESTA PRS_PROP \n"
                + "INNER JOIN PERSONA PRS\n"
                + "ON PRS_PROP.ID_PERSONA=PRS.ID_PERSONA\n"
                + "INNER JOIN TIPO_PERSONA TP_PRS\n"
                + "ON PRS_PROP.ID_TIPO_PERSONA=TP_PRS.ID_TIPO_PERSONA\n"
                + "WHERE TP_PRS.NOMBRE_TIPO_PERSONA='REFERENTE EXTERNO') TB_EXTERNO\n"
                + "ON TB_SOLICITANTE.PROPUESTA=TB_EXTERNO.PROPUESTA) TB_PERSONAS\n"
                + "ON TB_CONVENIO.ID_PROPUESTA=TB_PERSONAS.PROPUESTA";

        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql)
                    .addScalar("NOMBRE_PROPUESTA", new StringType())
                    .addScalar("FINALIDAD_PROPUESTA", new StringType())
                    .addScalar("TIPO_CONVENIO", new StringType())
                    .addScalar("NOMBRE_ESTADO", new StringType())
                    .addScalar("ID_PROPUESTA", new IntegerType())
                    .addScalar("SOLICITANTE", new StringType())
                    .addScalar("INTERNO", new StringType())
                    .addScalar("EXTERNO", new StringType())
                    .addScalar("PROPUESTA", new IntegerType())
                    .addScalar("VIGENCIA", new StringType())
                    .addScalar("ID_SOLICITANTE", new IntegerType())
                    .addScalar("ID_REF_INTERNO", new IntegerType())
                    .addScalar("ID_REF_EXTERNO", new IntegerType())
                    .addScalar("ID_ESTADO", new IntegerType())
                    .addScalar("FECHA_INGRESO", new DateType())
                    .setResultTransformer(Transformers.aliasToBean(PojoPropuestaConvenio.class));

            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * obtiene todos las propuestas convenios SQL por id solicitante
     */
    public List<PojoPropuestaConvenio> getAllPropuestaConvenioSQL(int idSolicitante) {

        String sql = "SELECT * FROM  \n"
                + "(SELECT P_CONVENIO.NOMBRE_PROPUESTA,P_CONVENIO.FINALIDAD_PROPUESTA,\n"
                + "T_PRO_CONVE.NOMBRE_PROPUESTA_CONVENIO AS TIPO_CONVENIO,STA.NOMBRE_ESTADO,P_CONVENIO.VIGENCIA,\n"
                + "P_CONVENIO.ID_PROPUESTA,\n"
                + "P_ESTADO.ID_ESTADO,\n"
                + "P_CONVENIO.FECHA_INGRESO FECHA_INGRESO\n"
                + "FROM PROPUESTA_CONVENIO P_CONVENIO\n"
                + "INNER JOIN TIPO_PROPUESTA_CONVENIO T_PRO_CONVE\n"
                + "ON P_CONVENIO.ID_TIPO_PROPUESTA_CONVENIO = T_PRO_CONVE.ID_TIPO_PROPUESTA \n"
                + "INNER JOIN PROPUESTA_ESTADO P_ESTADO \n"
                + "ON P_CONVENIO.ID_PROPUESTA = P_ESTADO.ID_PROPUESTA\n"
                + "INNER JOIN ESTADO STA\n"
                + "ON P_ESTADO.ID_ESTADO=STA.ID_ESTADO\n"
                + "WHERE  P_CONVENIO.VIGENCIA IS NULL\n"
                + "AND STA.NOMBRE_ESTADO <> 'FIRMADO'  ORDER BY FECHA_INGRESO DESC ) TB_CONVENIO\n"
                + "\n"
                + "LEFT JOIN\n"
                + "\n"
                + "(SELECT TB_SOLICITANTE.SOLICITANTE,TB_INTERNO.INTERNO,TB_EXTERNO.EXTERNO,TB_SOLICITANTE.PROPUESTA ,TB_SOLICITANTE.ID_SOLICITANTE,TB_INTERNO.ID_REF_INTERNO,TB_EXTERNO.ID_REF_EXTERNO \n"
                + "FROM \n"
                + "(SELECT CONCAT(PRS.NOMBRE_PERSONA,' ',PRS.APELLIDO_PERSONA) AS SOLICITANTE,PRS_PROP.ID_PROPUESTA AS PROPUESTA,PRS.ID_PERSONA AS ID_SOLICITANTE\n"
                + "FROM PERSONA_PROPUESTA PRS_PROP \n"
                + "INNER JOIN PERSONA PRS\n"
                + "ON PRS_PROP.ID_PERSONA=PRS.ID_PERSONA\n"
                + "INNER JOIN TIPO_PERSONA TP_PRS\n"
                + "ON PRS_PROP.ID_TIPO_PERSONA=TP_PRS.ID_TIPO_PERSONA\n"
                + "WHERE TP_PRS.NOMBRE_TIPO_PERSONA='SOLICITANTE') TB_SOLICITANTE\n"
                + "LEFT JOIN\n"
                + "(SELECT CONCAT(PRS.NOMBRE_PERSONA,' ',PRS.APELLIDO_PERSONA) AS INTERNO,PRS_PROP.ID_PROPUESTA AS PROPUESTA, PRS.ID_PERSONA AS ID_REF_INTERNO\n"
                + "FROM PERSONA_PROPUESTA PRS_PROP \n"
                + "INNER JOIN PERSONA PRS\n"
                + "ON PRS_PROP.ID_PERSONA=PRS.ID_PERSONA\n"
                + "INNER JOIN TIPO_PERSONA TP_PRS\n"
                + "ON PRS_PROP.ID_TIPO_PERSONA=TP_PRS.ID_TIPO_PERSONA\n"
                + "WHERE TP_PRS.NOMBRE_TIPO_PERSONA='REFERENTE INTERNO') TB_INTERNO\n"
                + "ON TB_SOLICITANTE.PROPUESTA=TB_INTERNO.PROPUESTA\n"
                + "LEFT JOIN\n"
                + "(SELECT CONCAT(PRS.NOMBRE_PERSONA,' ',PRS.APELLIDO_PERSONA) AS EXTERNO,PRS_PROP.ID_PROPUESTA AS PROPUESTA,PRS.ID_PERSONA AS ID_REF_EXTERNO\n"
                + "FROM PERSONA_PROPUESTA PRS_PROP \n"
                + "INNER JOIN PERSONA PRS\n"
                + "ON PRS_PROP.ID_PERSONA=PRS.ID_PERSONA\n"
                + "INNER JOIN TIPO_PERSONA TP_PRS\n"
                + "ON PRS_PROP.ID_TIPO_PERSONA=TP_PRS.ID_TIPO_PERSONA\n"
                + "WHERE TP_PRS.NOMBRE_TIPO_PERSONA='REFERENTE EXTERNO') TB_EXTERNO\n"
                + "ON TB_SOLICITANTE.PROPUESTA=TB_EXTERNO.PROPUESTA) TB_PERSONAS\n"
                + "ON TB_CONVENIO.ID_PROPUESTA=TB_PERSONAS.PROPUESTA\n"
                + "WHERE ID_SOLICITANTE=" + idSolicitante;

        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql)
                    .addScalar("NOMBRE_PROPUESTA", new StringType())
                    .addScalar("FINALIDAD_PROPUESTA", new StringType())
                    .addScalar("TIPO_CONVENIO", new StringType())
                    .addScalar("NOMBRE_ESTADO", new StringType())
                    .addScalar("ID_PROPUESTA", new IntegerType())
                    .addScalar("SOLICITANTE", new StringType())
                    .addScalar("INTERNO", new StringType())
                    .addScalar("EXTERNO", new StringType())
                    .addScalar("PROPUESTA", new IntegerType())
                    .addScalar("VIGENCIA", new StringType())
                    .addScalar("ID_SOLICITANTE", new IntegerType())
                    .addScalar("ID_REF_INTERNO", new IntegerType())
                    .addScalar("ID_REF_EXTERNO", new IntegerType())
                    .addScalar("ID_ESTADO", new IntegerType())
                    .addScalar("FECHA_INGRESO", new DateType())
                    .setResultTransformer(Transformers.aliasToBean(PojoPropuestaConvenio.class));

            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para obtener todos los Convenios estado Firmados
     *
     * @return
     */
    public List<PojoPropuestaConvenio> getAllConvenioSQL() {

        String sql = "SELECT * FROM  \n"
                + "(SELECT P_CONVENIO.NOMBRE_PROPUESTA,P_CONVENIO.FINALIDAD_PROPUESTA,\n"
                + "T_PRO_CONVE.NOMBRE_PROPUESTA_CONVENIO AS TIPO_CONVENIO,STA.NOMBRE_ESTADO,P_CONVENIO.VIGENCIA,\n"
                + "P_CONVENIO.ID_PROPUESTA,\n"
                + "P_ESTADO.ID_ESTADO,P_CONVENIO.ACTIVO,\n"
                + "P_CONVENIO.FECHA_INGRESO FECHA_INGRESO\n"
                + "FROM PROPUESTA_CONVENIO P_CONVENIO\n"
                + "INNER JOIN TIPO_PROPUESTA_CONVENIO T_PRO_CONVE\n"
                + "ON P_CONVENIO.ID_TIPO_PROPUESTA_CONVENIO = T_PRO_CONVE.ID_TIPO_PROPUESTA \n"
                + "INNER JOIN PROPUESTA_ESTADO P_ESTADO \n"
                + "ON P_CONVENIO.ID_PROPUESTA = P_ESTADO.ID_PROPUESTA\n"
                + "INNER JOIN ESTADO STA\n"
                + "ON P_ESTADO.ID_ESTADO=STA.ID_ESTADO\n"
                + "WHERE  P_CONVENIO.VIGENCIA IS NOT NULL\n"
                + "AND STA.NOMBRE_ESTADO = 'FIRMADO'"
                + "ORDER BY FECHA_INGRESO DESC  ) TB_CONVENIO\n"
                + "\n"
                + "LEFT JOIN\n"
                + "\n"
                + "(SELECT TB_SOLICITANTE.SOLICITANTE,TB_INTERNO.INTERNO,TB_EXTERNO.EXTERNO,TB_SOLICITANTE.PROPUESTA ,TB_SOLICITANTE.ID_SOLICITANTE,TB_INTERNO.ID_REF_INTERNO,TB_EXTERNO.ID_REF_EXTERNO \n"
                + "FROM \n"
                + "(SELECT CONCAT(PRS.NOMBRE_PERSONA,' ',PRS.APELLIDO_PERSONA) AS SOLICITANTE,PRS_PROP.ID_PROPUESTA AS PROPUESTA,PRS.ID_PERSONA AS ID_SOLICITANTE\n"
                + "FROM PERSONA_PROPUESTA PRS_PROP \n"
                + "INNER JOIN PERSONA PRS\n"
                + "ON PRS_PROP.ID_PERSONA=PRS.ID_PERSONA\n"
                + "INNER JOIN TIPO_PERSONA TP_PRS\n"
                + "ON PRS_PROP.ID_TIPO_PERSONA=TP_PRS.ID_TIPO_PERSONA\n"
                + "WHERE TP_PRS.NOMBRE_TIPO_PERSONA='SOLICITANTE') TB_SOLICITANTE\n"
                + "LEFT JOIN\n"
                + "(SELECT CONCAT(PRS.NOMBRE_PERSONA,' ',PRS.APELLIDO_PERSONA) AS INTERNO,PRS_PROP.ID_PROPUESTA AS PROPUESTA, PRS.ID_PERSONA AS ID_REF_INTERNO\n"
                + "FROM PERSONA_PROPUESTA PRS_PROP \n"
                + "INNER JOIN PERSONA PRS\n"
                + "ON PRS_PROP.ID_PERSONA=PRS.ID_PERSONA\n"
                + "INNER JOIN TIPO_PERSONA TP_PRS\n"
                + "ON PRS_PROP.ID_TIPO_PERSONA=TP_PRS.ID_TIPO_PERSONA\n"
                + "WHERE TP_PRS.NOMBRE_TIPO_PERSONA='REFERENTE INTERNO') TB_INTERNO\n"
                + "ON TB_SOLICITANTE.PROPUESTA=TB_INTERNO.PROPUESTA\n"
                + "LEFT JOIN\n"
                + "(SELECT CONCAT(PRS.NOMBRE_PERSONA,' ',PRS.APELLIDO_PERSONA) AS EXTERNO,PRS_PROP.ID_PROPUESTA AS PROPUESTA,PRS.ID_PERSONA AS ID_REF_EXTERNO\n"
                + "FROM PERSONA_PROPUESTA PRS_PROP \n"
                + "INNER JOIN PERSONA PRS\n"
                + "ON PRS_PROP.ID_PERSONA=PRS.ID_PERSONA\n"
                + "INNER JOIN TIPO_PERSONA TP_PRS\n"
                + "ON PRS_PROP.ID_TIPO_PERSONA=TP_PRS.ID_TIPO_PERSONA\n"
                + "WHERE TP_PRS.NOMBRE_TIPO_PERSONA='REFERENTE EXTERNO') TB_EXTERNO\n"
                + "ON TB_SOLICITANTE.PROPUESTA=TB_EXTERNO.PROPUESTA) TB_PERSONAS\n"
                + "ON TB_CONVENIO.ID_PROPUESTA=TB_PERSONAS.PROPUESTA";

        try {
            getSessionFactory().getCurrentSession().flush();
            getSessionFactory().getCurrentSession().clear();
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql)
                    .addScalar("NOMBRE_PROPUESTA", new StringType())
                    .addScalar("FINALIDAD_PROPUESTA", new StringType())
                    .addScalar("TIPO_CONVENIO", new StringType())
                    .addScalar("NOMBRE_ESTADO", new StringType())
                    .addScalar("ID_PROPUESTA", new IntegerType())
                    .addScalar("SOLICITANTE", new StringType())
                    .addScalar("INTERNO", new StringType())
                    .addScalar("EXTERNO", new StringType())
                    .addScalar("PROPUESTA", new IntegerType())
                    .addScalar("VIGENCIA", new DateType())
                    .addScalar("ID_SOLICITANTE", new IntegerType())
                    .addScalar("ID_REF_INTERNO", new IntegerType())
                    .addScalar("ID_REF_EXTERNO", new IntegerType())
                    .addScalar("ID_ESTADO", new IntegerType())
                    .addScalar("FECHA_INGRESO", new DateType())
                    .addScalar("ACTIVO", new BooleanType())
                    .setResultTransformer(Transformers.aliasToBean(PojoPropuestaConvenio.class));

            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PojoPropuestaConvenio getAllPropuestaConvenioSQLByID(Integer id) {

        String sql = "SELECT * FROM  \n"
                + "\n"
                + "(SELECT P_CONVENIO.NOMBRE_PROPUESTA,P_CONVENIO.FINALIDAD_PROPUESTA,\n"
                + "T_PRO_CONVE.NOMBRE_PROPUESTA_CONVENIO AS TIPO_CONVENIO,STA.NOMBRE_ESTADO,P_CONVENIO.VIGENCIA,\n"
                + "P_CONVENIO.ID_PROPUESTA,\n"
                + "P_ESTADO.ID_ESTADO,\n"
                + "P_CONVENIO.FECHA_INGRESO FECHA_INGRESO\n"
                + "FROM PROPUESTA_CONVENIO P_CONVENIO\n"
                + "INNER JOIN TIPO_PROPUESTA_CONVENIO T_PRO_CONVE\n"
                + "ON P_CONVENIO.ID_TIPO_PROPUESTA_CONVENIO = T_PRO_CONVE.ID_TIPO_PROPUESTA \n"
                + "INNER JOIN PROPUESTA_ESTADO P_ESTADO \n"
                + "ON P_CONVENIO.ID_PROPUESTA = P_ESTADO.ID_PROPUESTA\n"
                + "INNER JOIN ESTADO STA\n"
                + "ON P_ESTADO.ID_ESTADO=STA.ID_ESTADO) TB_CONVENIO\n"
                + "\n"
                + "LEFT JOIN\n"
                + "\n"
                + "(SELECT TB_SOLICITANTE.SOLICITANTE,TB_INTERNO.INTERNO,TB_EXTERNO.EXTERNO,TB_SOLICITANTE.PROPUESTA ,TB_SOLICITANTE.ID_SOLICITANTE,TB_INTERNO.ID_REF_INTERNO,TB_EXTERNO.ID_REF_EXTERNO FROM \n"
                + "(SELECT CONCAT(PRS.NOMBRE_PERSONA,' ',PRS.APELLIDO_PERSONA) AS SOLICITANTE,PRS_PROP.ID_PROPUESTA AS PROPUESTA,PRS.ID_PERSONA AS ID_SOLICITANTE\n"
                + "FROM PERSONA_PROPUESTA PRS_PROP \n"
                + "INNER JOIN PERSONA PRS\n"
                + "ON PRS_PROP.ID_PERSONA=PRS.ID_PERSONA\n"
                + "INNER JOIN TIPO_PERSONA TP_PRS\n"
                + "ON PRS_PROP.ID_TIPO_PERSONA=TP_PRS.ID_TIPO_PERSONA\n"
                + "WHERE TP_PRS.NOMBRE_TIPO_PERSONA='SOLICITANTE') TB_SOLICITANTE\n"
                + "LEFT JOIN\n"
                + "(SELECT CONCAT(PRS.NOMBRE_PERSONA,' ',PRS.APELLIDO_PERSONA) AS INTERNO,PRS_PROP.ID_PROPUESTA AS PROPUESTA, PRS.ID_PERSONA AS ID_REF_INTERNO\n"
                + "FROM PERSONA_PROPUESTA PRS_PROP \n"
                + "INNER JOIN PERSONA PRS\n"
                + "ON PRS_PROP.ID_PERSONA=PRS.ID_PERSONA\n"
                + "INNER JOIN TIPO_PERSONA TP_PRS\n"
                + "ON PRS_PROP.ID_TIPO_PERSONA=TP_PRS.ID_TIPO_PERSONA\n"
                + "WHERE TP_PRS.NOMBRE_TIPO_PERSONA='REFERENTE INTERNO') TB_INTERNO\n"
                + "ON TB_SOLICITANTE.PROPUESTA=TB_INTERNO.PROPUESTA\n"
                + "LEFT JOIN\n"
                + "(SELECT CONCAT(PRS.NOMBRE_PERSONA,' ',PRS.APELLIDO_PERSONA) AS EXTERNO,PRS_PROP.ID_PROPUESTA AS PROPUESTA,PRS.ID_PERSONA AS ID_REF_EXTERNO\n"
                + "FROM PERSONA_PROPUESTA PRS_PROP \n"
                + "INNER JOIN PERSONA PRS\n"
                + "ON PRS_PROP.ID_PERSONA=PRS.ID_PERSONA\n"
                + "INNER JOIN TIPO_PERSONA TP_PRS\n"
                + "ON PRS_PROP.ID_TIPO_PERSONA=TP_PRS.ID_TIPO_PERSONA\n"
                + "WHERE TP_PRS.NOMBRE_TIPO_PERSONA='REFERENTE EXTERNO') TB_EXTERNO\n"
                + "ON TB_INTERNO.PROPUESTA=TB_EXTERNO.PROPUESTA) TB_PERSONAS\n"
                + "\n"
                + "ON TB_CONVENIO.ID_PROPUESTA=TB_PERSONAS.PROPUESTA WHERE ID_PROPUESTA=" + id;

        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql)
                    .addScalar("NOMBRE_PROPUESTA", new StringType())
                    .addScalar("FINALIDAD_PROPUESTA", new StringType())
                    .addScalar("TIPO_CONVENIO", new StringType())
                    .addScalar("NOMBRE_ESTADO", new StringType())
                    .addScalar("ID_PROPUESTA", new IntegerType())
                    .addScalar("SOLICITANTE", new StringType())
                    .addScalar("INTERNO", new StringType())
                    .addScalar("EXTERNO", new StringType())
                    .addScalar("PROPUESTA", new IntegerType())
                    .addScalar("VIGENCIA", new DateType())
                    .addScalar("ID_SOLICITANTE", new IntegerType())
                    .addScalar("ID_REF_INTERNO", new IntegerType())
                    .addScalar("ID_REF_EXTERNO", new IntegerType())
                    .addScalar("ID_ESTADO", new IntegerType())
                    .addScalar("FECHA_INGRESO", new DateType())
                    .setResultTransformer(Transformers.aliasToBean(PojoPropuestaConvenio.class));

            return (PojoPropuestaConvenio) q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo que devuelve la entidad de propuesta de convenio en base al id
     *
     * @return
     */
    public PropuestaConvenio getPropuestaCovenioByID(int idPropuesta) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT p FROM PropuestaConvenio p WHERE p.idPropuesta=:id");
            q.setParameter("id", idPropuesta);
            return (PropuestaConvenio) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo que devuelve los convenios con sus estados.
     *
     * @return
     */
    public List<PojoConvenioEstado> getConveioWithEstado() {
        try {
            String sql = "SELECT \n"
                    + "P_CONV.ID_PROPUESTA id_propuesta,\n"
                    + "P_CONV.NOMBRE_PROPUESTA nombre_propuesta,\n"
                    + "P_CONV.VIGENCIA vigencia,\n"
                    + "P_ESTAD.FECHA fecha_cambio_estado,\n"
                    + "EST.ID_ESTADO id_estado,\n"
                    + "EST.NOMBRE_ESTADO nombre_estado,\n"
                    + "EST.TIPO_ESTADO tipo_estado\n"
                    + "FROM PROPUESTA_CONVENIO P_CONV \n"
                    + "INNER JOIN PROPUESTA_ESTADO P_ESTAD \n"
                    + "ON P_ESTAD.ID_PROPUESTA=P_CONV.ID_PROPUESTA\n"
                    + "INNER JOIN ESTADO EST\n"
                    + "ON P_ESTAD.ID_ESTADO=EST.ID_ESTADO\n"
                    + "AND P_CONV.VIGENCIA IS NOT NULL";

            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql)
                    .addScalar("id_propuesta", new IntegerType())
                    .addScalar("nombre_propuesta", new StringType())
                    .addScalar("vigencia", new DateType())
                    .addScalar("fecha_cambio_estado", new DateType())
                    .addScalar("id_estado", new IntegerType())
                    .addScalar("nombre_estado", new StringType())
                    .addScalar("tipo_estado", new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(PojoConvenioEstado.class));

            if (q.list() != null) {
                return q.list();
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo que devuelve las propuestas convenios con sus estados.
     *
     * @return
     */
    public List<PojoConvenioEstado> getPropuestasConvenioWithEstado() {
        try {
            String sql = "SELECT \n"
                    + "P_CONV.ID_PROPUESTA id_propuesta,\n"
                    + "P_CONV.NOMBRE_PROPUESTA nombre_propuesta,\n"
                    + "P_CONV.VIGENCIA vigencia,\n"
                    + "P_ESTAD.FECHA fecha_cambio_estado,\n"
                    + "EST.ID_ESTADO id_estado,\n"
                    + "EST.NOMBRE_ESTADO nombre_estado,\n"
                    + "EST.TIPO_ESTADO tipo_estado\n"
                    + "FROM PROPUESTA_CONVENIO P_CONV \n"
                    + "INNER JOIN PROPUESTA_ESTADO P_ESTAD \n"
                    + "ON P_ESTAD.ID_PROPUESTA=P_CONV.ID_PROPUESTA\n"
                    + "INNER JOIN ESTADO EST\n"
                    + "ON P_ESTAD.ID_ESTADO=EST.ID_ESTADO\n "
                    + "AND P_CONV.VIGENCIA IS NULL";

            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql)
                    .addScalar("id_propuesta", new IntegerType())
                    .addScalar("nombre_propuesta", new StringType())
                    .addScalar("vigencia", new DateType())
                    .addScalar("fecha_cambio_estado", new DateType())
                    .addScalar("id_estado", new IntegerType())
                    .addScalar("nombre_estado", new StringType())
                    .addScalar("tipo_estado", new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(PojoConvenioEstado.class));

            if (q.list() != null) {
                return q.list();
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<RptConveniosPorAnioPojo> getconveniosPorAnio(Integer desde, Integer hasta) {
        String query = "SELECT tp.NOMBRE_PROPUESTA_CONVENIO as tipoConvenio,p.NOMBRE_PROPUESTA as nombreConvenio, \n"
                + "p.FINALIDAD_PROPUESTA as finalidad, p.FECHA_INGRESO as fechaIngreso, p.VIGENCIA as vigencia from \n"
                + "PROPUESTA_CONVENIO p join TIPO_PROPUESTA_CONVENIO tp on (p.ID_TIPO_PROPUESTA_CONVENIO=tp.ID_TIPO_PROPUESTA) \n"
                + "join PROPUESTA_ESTADO pe on (p.ID_PROPUESTA=pe.ID_PROPUESTA) where p.VIGENCIA IS NOT NULL AND pe.ID_ESTADO=10 \n"
                + "AND YEAR(p.FECHA_INGRESO) BETWEEN \n"
                + desde + " AND " + hasta 
                + " ORDER BY p.FECHA_INGRESO asc";
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query)
                .addScalar("tipoConvenio", new StringType())
                .addScalar("nombreConvenio", new StringType())
                .addScalar("finalidad", new StringType())
                .addScalar("fechaIngreso", new DateType())
                .addScalar("vigencia", new DateType())
                .setResultTransformer(Transformers.aliasToBean(RptConveniosPorAnioPojo.class));
        return q.list();
    }

    public List<PojoConvenioEstado> getPropuestasConvenioWithEstado(Integer desde, Integer hasta) {
        try {
            String sql = "SELECT \n"
                    + "P_CONV.ID_PROPUESTA id_propuesta,\n"
                    + "P_CONV.NOMBRE_PROPUESTA nombre_propuesta,\n"
                    + "P_CONV.VIGENCIA vigencia,\n"
                    + "P_ESTAD.FECHA fecha_cambio_estado,\n"
                    + "EST.ID_ESTADO id_estado,\n"
                    + "EST.NOMBRE_ESTADO nombre_estado,\n"
                    + "EST.TIPO_ESTADO tipo_estado\n"
                    + "FROM PROPUESTA_CONVENIO P_CONV \n"
                    + "INNER JOIN PROPUESTA_ESTADO P_ESTAD \n"
                    + "ON P_ESTAD.ID_PROPUESTA=P_CONV.ID_PROPUESTA\n"
                    + "INNER JOIN ESTADO EST\n"
                    + "ON P_ESTAD.ID_ESTADO=EST.ID_ESTADO\n "
                    + "AND P_CONV.VIGENCIA IS NULL";
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql)
                    .addScalar("id_propuesta", new IntegerType())
                    .addScalar("nombre_propuesta", new StringType())
                    .addScalar("vigencia", new DateType())
                    .addScalar("fecha_cambio_estado", new DateType())
                    .addScalar("id_estado", new IntegerType())
                    .addScalar("nombre_estado", new StringType())
                    .addScalar("tipo_estado", new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(PojoConvenioEstado.class));
            if (q.list() != null) {
                return q.list();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo que devuelve la entidad de propuesta de convenio en base al id
     *
     * @return
     */
    public PropuestaConvenio getByIDPropuestaWithPersona(int idPropuesta) {
        try {
            getSessionFactory().getCurrentSession().flush();
            getSessionFactory().getCurrentSession().clear();
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT p FROM PropuestaConvenio p JOIN FETCH p.personaPropuestaList pe WHERE p.idPropuesta=:id");
            q.setParameter("id", idPropuesta);
            return (PropuestaConvenio) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer conteoPropuestasEnRevision() {

        String query = "Select p From PropuestaEstado p where p.estado.idEstado=11";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        return q.list().size();

    }

    // conteo de todos los tipos de propuestas
    public List<RptConteoConveniosPorTipoPojo> conteoPropuestaConvenioByTipoPropuesta(Integer desde, Integer hasta) {
        try {
            String sql = "select tipo.NOMBRE_PROPUESTA_CONVENIO as tipoConvenio, sum(pc.ID_PROPUESTA) as suma from PROPUESTA_CONVENIO pc inner join TIPO_PROPUESTA_CONVENIO tipo on (pc.ID_TIPO_PROPUESTA_CONVENIO=ID_TIPO_PROPUESTA) WHERE YEAR(pc.FECHA_INGRESO) BETWEEN \n"
                    + desde + " AND " + hasta + " GROUP BY tipo.ID_TIPO_PROPUESTA,pc.ID_PROPUESTA";
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql)
                    .addScalar("tipoConvenio", new StringType())
                    .addScalar("suma", new IntegerType())
                    .setResultTransformer(Transformers.aliasToBean(RptConteoConveniosPorTipoPojo.class));
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // retorna los estados que tiene cada convenio o propuesta
    public List<RptBitacoraEstadosPojo> estadosPropuestaConvenioBitacora(Integer desde, Integer hasta) {
        try {
            String sql = "SELECT pc.NOMBRE_PROPUESTA AS Nombre,\n"
                    + "(SELECT be.FECHA_DE_CAMBIO FROM BITACORA_ESTADO be WHERE be.ID_PROPUESTA=pc.ID_PROPUESTA AND be.ID_ESTADO=6) AS Fiscalia,\n"
                    + "(SELECT be.FECHA_DE_CAMBIO FROM BITACORA_ESTADO be WHERE be.ID_PROPUESTA=pc.ID_PROPUESTA AND be.ID_ESTADO=7) AS CSU,\n"
                    + "(SELECT be.FECHA_DE_CAMBIO FROM BITACORA_ESTADO be WHERE be.ID_PROPUESTA=pc.ID_PROPUESTA AND be.ID_ESTADO=8) as AGU,\n"
                    + "(SELECT be.FECHA_DE_CAMBIO FROM BITACORA_ESTADO be WHERE be.ID_PROPUESTA=pc.ID_PROPUESTA AND be.ID_ESTADO=10) AS Firmado,\n"
                    + "pc.FINALIDAD_PROPUESTA AS Finalidad\n"
                    + "FROM `PROPUESTA_CONVENIO` pc WHERE YEAR(pc.FECHA_INGRESO) BETWEEN \n"
                    + desde + " AND " + hasta + " ORDER BY pc.ID_PROPUESTA DESC";
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql)
                    .addScalar("Nombre", new StringType())
                    .addScalar("Fiscalia", new DateType())
                    .addScalar("CSU", new DateType())
                    .addScalar("AGU", new DateType())
                    .addScalar("Firmado", new DateType())
                    .addScalar("Finalidad", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(RptBitacoraEstadosPojo.class));
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PropuestaConvenio> getConvenioVigentes() {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM PropuestaConvenio a  WHERE a.vigencia is not null");
            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deletePropuestas(int propuesta) {
        try {
            String sql = "DELETE FROM DOCUMENTO WHERE ID_PROPUESTA =" + propuesta;
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql);
            getSessionFactory().getCurrentSession().flush();
            getSessionFactory().getCurrentSession().clear();
            int executeUpdate = q.executeUpdate();
            return executeUpdate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
