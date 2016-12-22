package com.sisrni.dao.generic;

import com.sisrni.utils.BeanHelper;
import com.sisrni.utils.PagedResult;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.sisrni.utils.Utils;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Property;

public class GenericDao<T, ID extends Serializable> {

    private Class<T> myclass;
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("rawtypes")
    protected Class clazz;

    public GenericDao() {
        myclass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public List findAll() {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(myclass);
        return c.list();
      
    }

    @SuppressWarnings("rawtypes")
    public Class getClazz() {
        return clazz;
    }
//     @Override
//    public List findAll() {
//        List ret = null;
//        Session session = sessionFactory.getCurrentSession();
//        Query q = session.createQuery("from " + getClazz().getName());
//        if (q != null) {
//            ret = q.list();
//        }
//        return ret;
//    }

    public T findById(ID id) {
        return (T) sessionFactory.getCurrentSession().get(myclass, id);
    }

    public Serializable save(T entity) {
        return sessionFactory.getCurrentSession().save(entity);
    }
    
    public void saveOrUpdate(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    public T merge(T entity) {
        return (T) sessionFactory.getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(myclass);
    }

    public Class getMyclass() {
        return myclass;
    }

    public void setMyclass(Class myclass) {
        this.myclass = myclass;
    }

    public PagedResult getPage(int page, int pageSize, String sortProperty, String sortDirection, Object sample) {
        PagedResult ret = new PagedResult();
        if (sample == null) {
            sample = new Object();
        }
        DetachedCriteria dc = createCriteria(sample, true, true, MatchMode.ANYWHERE);
        Criteria c = dc.getExecutableCriteria(sessionFactory.getCurrentSession());
        ClassMetadata meta = getSessionFactory().getClassMetadata(getMyclass());
        // count
        c.setProjection(Projections.rowCount());
        ret.setCounter(((Number) c.uniqueResult()).intValue());

        // all filtered
        c.setProjection(null);
        if (page > 0 && pageSize > 0) {
            c.setFirstResult(page);
            c.setMaxResults(pageSize);
        }

        if (Utils.isNullOrEmpty(sortProperty)) {
            sortProperty = meta.getIdentifierPropertyName();
        }
        if (Utils.isNullOrEmpty(sortDirection)) {
            sortDirection = "asc";
        }
        if ("desc".equalsIgnoreCase(sortDirection)) {
            c.addOrder(Order.desc(sortProperty));
        } else {
            c.addOrder(Order.asc(sortProperty));
        }
        ret.setList(c.list());
        return ret;
    }

    public PagedResult getPage(int page, int pageSize, String sortProperty, String sortDirection) {
        PagedResult ret = new PagedResult();

        DetachedCriteria dc = DetachedCriteria.forClass(myclass);
        Criteria c = dc.getExecutableCriteria(sessionFactory.getCurrentSession());
        ClassMetadata meta = getSessionFactory().getClassMetadata(getMyclass());
        // count
        c.setProjection(Projections.rowCount());
        ret.setCounter(((Number) c.uniqueResult()).intValue());

        // all filtered
        c.setProjection(null);
        if (page > 0 && pageSize > 0) {
            c.setFirstResult(page);
            c.setMaxResults(pageSize);
        }

        if (Utils.isNullOrEmpty(sortProperty)) {
            sortProperty = meta.getIdentifierPropertyName();
        }
        if (Utils.isNullOrEmpty(sortDirection)) {
            sortDirection = "asc";
        }
        if ("desc".equalsIgnoreCase(sortDirection)) {
            c.addOrder(Order.desc(sortProperty));
        } else {
            c.addOrder(Order.asc(sortProperty));
        }
        ret.setList(c.list());
        return ret;
    }

    public PagedResult getPage(int page, int pageSize, String sortProperty, String sortDirection, List criterions) {
        PagedResult ret = new PagedResult();
        List<String> projectionsDistinct = new ArrayList<String>();
        ProjectionList projectionsCount = Projections.projectionList().create();
        DetachedCriteria dc = DetachedCriteria.forClass(getMyclass());
        Criteria c = dc.getExecutableCriteria(sessionFactory.getCurrentSession());
        ClassMetadata meta = getSessionFactory().getClassMetadata(getMyclass());
        if (criterions != null && criterions.size() > 0) {
            for (Object o : criterions) {
                if (o instanceof Criterion) {
                    Criterion restriction;
                    restriction = (Criterion) o;
                    c.add(restriction);
                } else if (o instanceof Map) {
                    Map<String, Object> map = (Map<String, Object>) o;
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        String key = entry.getKey();
                        if (!Utils.isNullOrEmpty(key)) {
                            if (key.toLowerCase().contains("sql")) {
                                String strValue = (String) entry.getValue();
                                if (strValue != null && !strValue.trim().equals("")) {
                                    c.add(Restrictions.sqlRestriction(strValue));
                                }
                            } else if (entry.getValue() != null) {
                                if (key.contains("<>") || key.contains("!=")) {
                                    // key= "campo<>", "<>campo", "campo!=", "!=campo";
                                    String strKey = key.replaceAll("<>", "").replaceAll("!=", "");
                                    c.add(Restrictions.ne(strKey, entry.getValue()));
                                } else if (entry.getValue() instanceof String) {
                                    String strValue = (String) entry.getValue();
                                    if (Utils.isNumeric(strValue)) {
                                        Long islong = Utils.getLong(strValue);
                                        Integer isinteger = Utils.getInteger(strValue);
                                        Double isdouble = Utils.getNumber(strValue);
                                        if (isinteger != null) {
                                            c.add(Restrictions.eq(entry.getKey(), isinteger));
                                        } else if (islong != null) {
                                            c.add(Restrictions.eq(entry.getKey(), islong));
                                        } else if (isdouble != null) {
                                            c.add(Restrictions.eq(entry.getKey(), isdouble));
                                        } else {
                                            c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
                                        }
                                    } else {
                                        Date isdate = Utils.createDate(strValue);
                                        if (isdate != null) {
                                            Date maxDate = new Date(isdate.getTime() + TimeUnit.DAYS.toMillis(1));
                                            Conjunction btwn = Restrictions.conjunction();
                                            btwn.add(Restrictions.ge(entry.getKey(), isdate));
                                            btwn.add(Restrictions.lt(entry.getKey(), maxDate));
                                            c.add(btwn);
                                        } else {
                                            if (strValue != null && !Utils.isNullOrEmpty(strValue)) {
                                                strValue = strValue.trim();
                                                strValue = "%" + strValue + "%";
                                                if (strValue.trim().toLowerCase().equals("null")) {
                                                    c.add(Restrictions.isNull(key));
                                                } else if (key.trim().equalsIgnoreCase("order") || key.trim().equalsIgnoreCase("order by") || key.trim().equalsIgnoreCase("order-by")) {
                                                    if (!strValue.equals("")) {
                                                        if (strValue.toLowerCase().contains(" desc")) {
                                                            c.addOrder(Order.desc(strValue.toLowerCase().replaceAll(" desc", "")));
                                                        } else {
                                                            c.addOrder(Order.asc(strValue.toLowerCase()));
                                                        }
                                                    }
                                                } else if (key.trim().equalsIgnoreCase("not null") || key.trim().equalsIgnoreCase("not-null") || key.trim().equalsIgnoreCase("notnull")) {
                                                    if (!strValue.equals("")) {
                                                        c.add(Restrictions.isNotNull(strValue));
                                                    }
                                                } else if (strValue.toLowerCase().contains("distinct") || key.toLowerCase().contains("distinct")) {
                                                    projectionsDistinct.add(key.replaceAll("distinct", "").trim());
                                                } else if (strValue.contains("%")) {
                                                    if (strValue.trim().startsWith("%") && strValue.trim().endsWith("%")) {
                                                        c.add(Restrictions.ilike(key, strValue.replaceAll("%", ""), MatchMode.ANYWHERE));
                                                    } else if (strValue.trim().startsWith("%")) {
                                                        c.add(Restrictions.ilike(key, strValue.replaceAll("%", ""), MatchMode.END));
                                                    } else {
                                                        c.add(Restrictions.ilike(key, strValue.replaceAll("%", ""), MatchMode.START));
                                                    }
                                                } else {
                                                    c.add(Restrictions.or(Restrictions.eq(key, strValue.toLowerCase()), Restrictions.eq(key, strValue.toUpperCase())));
                                                }
                                            }
                                        }
                                    }
                                } else if (key.contains("between") && entry.getValue() instanceof Object[]) {
                                    Object[] params = (Object[]) entry.getValue();
                                    if (params.length == 3) {
                                        String field = (String) params[0];
                                        Object firstComparator = params[1];
                                        Object secondComparator = params[2];
                                        c.add(Restrictions.between(field, firstComparator, secondComparator));
                                    }
                                } else if (!key.contains("between") && entry.getValue() instanceof String[]) {
                                    c.add(Restrictions.in(key, (String[]) entry.getValue()));
                                } else if (entry.getValue() instanceof Date[]) {
                                    Date[] periodo = (Date[]) entry.getValue();
                                    if (periodo.length == 2) {
                                        c.add(Restrictions.between(key, periodo[0], periodo[1]));
                                    }
                                } else {
                                    c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
                                }
                            } else {
                                c.add(Restrictions.isNull(key));
                            }
                        }
                    }
                }
            }
        }
        if (projectionsDistinct.size() > 0) {
            for (String key : projectionsDistinct) {
                projectionsCount.add(Projections.countDistinct(key));
            }
        } else {
            projectionsCount.add(Projections.rowCount());
        }
        c.setProjection(projectionsCount);
        ret.setCounter(((Number) c.uniqueResult()).intValue());
        if (projectionsDistinct.size() > 0) {
            for (String key : projectionsDistinct) {
                c.setProjection(Projections.distinct(Property.forName(key)));
            }
        } else {
            c.setProjection(null);
        }
        if (page > 0 && pageSize > 0) {
            c.setFirstResult((page - 1) * pageSize);
            c.setMaxResults(pageSize);
        }

        if (Utils.isNullOrEmpty(sortProperty)) {
            sortProperty = meta.getIdentifierPropertyName();
        }
        if (Utils.isNullOrEmpty(sortDirection)) {
            sortDirection = "asc";
        }
        if ("desc".equalsIgnoreCase(sortDirection)) {
            c.addOrder(Order.desc(sortProperty));
        } else {
            c.addOrder(Order.asc(sortProperty));
        }
        ret.setList(c.list());
        return ret;
    }

    public ClassMetadata getMetadata() {
        return sessionFactory.getClassMetadata(
                getMyclass());
    }

    public Serializable getObjectId(Object obj) {
        ClassMetadata metadata = getMetadata();
        String idProp = metadata.getIdentifierPropertyName();
        Object id;
        try {
            char[] chars = idProp.toUpperCase().toCharArray();
            String methodName = "get" + chars[0]
                    + idProp.substring(1, idProp.length());
            Method method = obj.getClass()
                    .getMethod(methodName, (Class[]) null);
            id = method.invoke(obj, (Object[]) null);
        } catch (NoSuchMethodException e) {
            return (Serializable) obj;
        } catch (SecurityException e) {
            return (Serializable) obj;
        } catch (IllegalAccessException e) {
            return (Serializable) obj;
        } catch (IllegalArgumentException e) {
            return (Serializable) obj;
        } catch (InvocationTargetException e) {
            return (Serializable) obj;
        }
        return (Serializable) id;
    }

    public DetachedCriteria createCriteria(Object example, boolean ignoreCase, boolean ignoreZeroes, MatchMode mode) {
        Object id = getObjectId(example);
        if (ignoreZeroes && id instanceof Number) {
            Number num = (Number) id;
            if (num.doubleValue() == 0) {
                id = null;
            }
        }

        BeanHelper.setEmptyStringsAndZeroNumbersToNull(example);
        Example xample = Example.create(example);

        if (ignoreCase) {
            xample.ignoreCase();
        }
        if (ignoreZeroes) {
            xample.excludeZeroes();
        }
        if (!Utils.isNullOrEmpty(mode)) {
            xample.enableLike(mode);
        }

        xample.ignoreCase().excludeZeroes();
        DetachedCriteria dc = DetachedCriteria.forClass(getMyclass());
        if (!Utils.isNullOrEmpty(id)) {
            if (BeanUtils.isSimpleProperty(id != null ? id.getClass() : null)) {

                dc.add(Restrictions.idEq(id));
            } else {
                String idProp = getMetadata().getIdentifierPropertyName();
                Map props = BeanHelper.getProperties(id);
                Object objValue;
                String key;
                for (Iterator iter = props.keySet().iterator(); iter.hasNext();) {
                    key = (String) iter.next();
                    objValue = props.get(key);
                    if (!Utils.isNullOrEmpty(objValue)) {

                        dc.add(Restrictions.eq(idProp + "." + key, objValue));
                    }
                }
            }
        } else {
            dc.add(xample);
        }
        return dc;
    }

    

}
