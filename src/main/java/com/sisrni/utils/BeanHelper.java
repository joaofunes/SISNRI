package com.sisrni.utils;

import com.sisrni.utils.Utils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.CharSetUtils;
import org.hibernate.collection.internal.AbstractPersistentCollection;
import org.hibernate.collection.spi.PersistentCollection;
import org.springframework.beans.FatalBeanException;

public class BeanHelper {

	//static Log log = LogFactory.getLog(BeanHelper.class);
	public static final String SET  = "set";
	public static final String GET  = "get";
	
	public static final String[] toExclude
		= {"getMultipartRequestHandler", "getServlet", "getServletWrapper", "getClass" , "getString", "getValidationKey"};
	public static final String[] propToExclude =
		{"multipartRequestHandler", 
						"servlet", "servletWrapper", 
						"class" , "string", "validationKey", 
						"resultValueMap",
						"validatorResults", "page"};
	
	
	/**
	 * copia las propiedades no nulas o vacias de <b>source a target</b><br>
	 * los booleanos y tipos nativos deben ser copiados aparte, pues no poseen
	 * valores nulos cuando van vacios
	 * @param target
	 * @param source
	 */
	public static void copyNonEmptyProperties(Object target, Object source){
		try {
			Map props = BeanUtils.describe(source);
			List<String> empty = new ArrayList<String>();			
			Set<String> keys = props.keySet();
			for (String key: keys) {
				Object val = props.get(key);
				if(Utils.isNullOrEmpty(val)){
					empty.add(key);
				}
			}	
					
			
			copyPropertyNoCollection(source,target, empty);
			
						
		} catch (Exception e) {
			throw new RuntimeException(e);			
		}
		
	}
	
	
	/**
	 * convenience method
	 * @param bean
	 * @param prefix
	 * @return A list of properties starting with the specified prefix
	 */	
	public static List<String> getAllSettersPrefix(Object bean, String prefix){
		return getAllPrefix(bean, prefix, false);
	}
	
	public static List<String> getAllGettersPrefix(Object bean, String prefix){
		return getAllPrefix(bean, prefix, true);
	}
	
	
	
	
	public static void setEmptyStringsAndZeroNumbersToNull(Object obj){
		try {
			Map map = BeanHelper.getProperties(obj);
			//Map map =  BeanUtils.describe(obj);
			
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				
				Object value = map.get(key);
				if (value instanceof String) {
					if(Utils.isNullOrEmpty(value)){
						BeanUtils.setProperty(obj, key, null);
					}					
				}//instance
				
				if(value instanceof Number){
					Number num = (Number)value;
					if(num.doubleValue() == 0){
						BeanUtils.setProperty(obj, key, null);
						//BeanUtils.setProperty(obj, key, null);
					}// val=0						
				}//instance
				
			}//for keySet			
		} catch (Exception e) {
			//log.error("exception while setting empty strings to null");
			throw new RuntimeException(e);
		}		
	}
	
	/**
	 * returns a list of properties exposed via  java bean standard via getter or setter specified 
	 * @param bean
	 * @param prefix
	 * @param getter <b>true</b> if you need the getters, otherwise the setters will be returned
	 * @return
	 */
	private static List<String> getAllPrefix(Object bean, String prefix, boolean getter){
		List<String> properties = new ArrayList<String>();		
		try {
			Method[] methods =  bean.getClass().getMethods();			
			String temp = null;
			String methodStr = null;
			
			for(int i =0; i< methods.length; i++){
				methodStr  = methods[i].getName();
				temp = getPropertyForBeanProp(methodStr);
				//is a valid prefix
				if(temp!= null && temp.startsWith(prefix)){
					//do I want the setters or the getters
					if(getter){
						if(isGetter(methodStr))
							properties.add(methodStr);
					}else{
						if(isSetter(methodStr)) 
							properties.add(methodStr);
					}//was it a getter or setter												
				}
			}
		} catch (Exception e) {			
			//log.error("getting properties with prefix problem", e);			
			throw new RuntimeException(e);
		} 
		return properties;
	}
	
	public static List getGetterMethod(Object bean){
		List<Method> properties = new ArrayList<Method>();		
		try {
			Method[] methods =  bean.getClass().getMethods();			
			String methodStr = null;
			
			for(int i =0; i< methods.length; i++){
				methodStr  = methods[i].getName();
				if(isGetter(methodStr)) {
					properties.add( methods[i]);
				}
			}
		} catch (Exception e) {			
			//log.error("getting properties with prefix problem", e);			
			throw new RuntimeException(e);
		} 
		return properties;
	}
	
	
	public static List mirrorList(List listado, Class clazz){
		List<Object> copies = new ArrayList<Object>();
		try {
			for (Object object : listado) {
				Object obj = clazz.newInstance();
				BeanHelper.copyProperties(obj, object);
				copies.add(obj);
			}
			return copies;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * copy the inclProps from target to source object
	 * @param target
	 * @param source
	 * @param inclProps
	 */
	public static void copyIncludingProperties(Object target, Object source, List inclProps){
		//List trgProps  = getAllPropertiesWithGetter(target);
		//trgProps - inclProps = no quiero
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		for (Iterator iter = inclProps.iterator(); iter.hasNext();) {
			String str = (String)iter.next();
			try {
				map.put(str, BeanUtils.getProperty(source, str));
			} catch (Exception e) {
				//log.error("error copiando propiedad >"+str+"<", e);
				throw new RuntimeException(e);
			}					
		}
		
		try {
			BeanUtils.populate(target, map);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * get a list of property names (Strings ) with getter method
	 * @param bean
	 * @return
	 */
	public static List getAllPropertiesWithGetter(Object bean){
		List<String> properties = new ArrayList<String>();		
		try {
			Method[] methods =  bean.getClass().getMethods();			
			String temp = null;
			String methodStr = null;
			
			for(int i =0; i< methods.length; i++){
				methodStr  = methods[i].getName();
				temp = getPropertyForBeanProp(methodStr);
				//is a valid prefix
				if(temp!= null){
					//do I want the setters or the getters					
					if(isGetter(methodStr) ){						
						properties.add(temp);					
					}//was it a getter 												
				}
			}
		} catch (Exception e) {			
			//log.error("getting properties problem", e);			
			throw new RuntimeException(e);
		} 
		return properties;	
	}
	
	/**
	 * get all properties Values with a bean getter standard interface
	 * @param bean
	 * @return
	 */
	public static List getAllPropertiesValueWithGetter(Object bean ){
		List<Object> properties = new ArrayList<Object>();		
		try {
			Method[] methods =  bean.getClass().getMethods();			
			String temp = null;
			String methodStr = null;
			
			for(int i =0; i< methods.length; i++){
				methodStr  = methods[i].getName();
				temp = getPropertyForBeanProp(methodStr);
				//is a valid prefix
				if(temp!= null){
					//do I want the setters or the getters					
					if(isGetter(methodStr) ){
						Object res = methods[i].invoke(bean, (Object[])null);
						properties.add(res);					
					}//was it a getter 												
				}
			}
		} catch (Exception e) {			
			//log.error("getting properties problem", e);			
			throw new RuntimeException(e);
		} 
		return properties;		
	}

	
	/**
	 * Convenience method to return all the properties values of an object via BeanUtils
	 * reflection
	 * @param target bean to Describe
	 * @return List with all properties for the specified object
	 */
	public static List getAllPropsValues(Object target){
		List<String> props  = new ArrayList<String>();
		Map map  = null;
		try {
			map = BeanUtils.describe(target);
			for (Iterator iter = map.values().iterator(); iter.hasNext();) {
				String prop = (String) iter.next();
				if(prop.startsWith("class")){
					continue;
				}
				props.add(prop);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return props;		
	}
	
	
	
	/**
	 * get all properties values with getter methods defined separated by 
	 * separator and enclosed by wrapper characters
	 * @param target
	 * @param separator
	 * @param wrapper
	 * @return
	 */
	public static String getPropertiesValues(Object target, String separator, String wrapper){
		List props  = getAllPropertiesValueWithGetter(target);		
		String temp = "";
		Object val  = null;
		String prop  = "";

		for (Iterator iter = props.iterator(); iter.hasNext();) {
			val = iter.next();			
			if(val == null){
				prop = "";
			}
			else{
				prop  = val.toString();
			}
			temp += wrapper + prop + wrapper + separator;
		}
		//not to use the last separator
		if(Utils.isNullOrEmpty(temp) || temp.length() > 1)
			temp = temp.substring(0, temp.length()-1);		
		return temp;
	}
	
	
	
	//-- -------------------------------------------------------------------------
	
	public static List getSimplePropertiesNames(Object target){
		Map map  = getPropertyVsTypeMap(target);
		Class[] valid = new Class[]{String.class, Number.class, java.util.Date.class};
		List<String> props  = new ArrayList<String>();
		
		//Ordering the properties' names
		Object[] objArr = map.keySet().toArray();
		Arrays.sort(objArr);
		List propsNames = Arrays.asList(objArr);
		
		for (Iterator iter = propsNames.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Class returnType  = (Class)map.get(key);
			if(Utils.isAssignableFrom(returnType, valid)){				
				props.add(key);
			}				
			//--if()
		}//keySet
		
				
		return props;
	}
	
	
	//-- -------------------------------------------------------------------------
	public static String getSimplePropertiesValues(Object target, String separator, String wrapper){
		
		Map<String, Object> map  = getPropertyVsTypeMap(target);
		
		if (target instanceof DynaBean) {
			map = new HashMap<String, Object>();
			DynaBean dyna = (DynaBean) target;
			DynaProperty[] dprops = dyna.getDynaClass().getDynaProperties();
			for (DynaProperty dprop : dprops) {
				map.put(dprop.getName(), dprop.getType());				
			}
		}
		
		
				
		//Ordering the properties names
		Object[] objArr = map.keySet().toArray();
		Arrays.sort(objArr);
		List lst = Arrays.asList(objArr);
		
		Class[] valid = new Class[]{String.class, Number.class, java.util.Date.class};
		List<Object> props = new ArrayList<Object>();
		
		String temp  = "";
		
		for (Iterator iter = lst.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Class returnType  = (Class)map.get(key);
			Field field = null;
			String strDate;
			try {				
				field = target.getClass().getDeclaredField(key);				
			} catch (SecurityException e1) {

			} catch (NoSuchFieldException e1) {				
			}			
			
			if(Utils.isAssignableFrom(returnType, valid)){
				try {
					
					if(returnType.isAssignableFrom(java.util.Date.class)){
						try {
							field.setAccessible(true);
							strDate = Utils.getFormattedDate((java.util.Date)field.get(target), "dd/MM/yyyy");
							props.add(strDate); 
							field.setAccessible(false);
							
						} catch (Exception e) {

						}
						
					}else if(returnType.isAssignableFrom(java.sql.Date.class)){
						try {
							field.setAccessible(true);
							java.sql.Date dateSql = (java.sql.Date)field.get(target);
							strDate = Utils.getFormattedDate(new java.util.Date(dateSql.getTime()),"dd/MM/yyyy");
							props.add(strDate);
							field.setAccessible(false);
							
							
						} catch (Exception e) {
							// TODO: handle exception
						}
						
					}else{
						props.add(BeanUtils.getProperty(target, key));
					}
					
				
					
				} catch (Exception e) {
					//log.error("Error getting value of >"+key+"<", e);
				}
				
			}				
			//--if()
		}//keySet
		
		Object val  = null;
		String prop  = "";
		
		for (Iterator iter = props.iterator(); iter.hasNext();) {
			val = iter.next();			
			if(val == null){
				prop = "";
			}
			else{
				prop  = val.toString();
			}
			
			prop = CharSetUtils.delete(prop, "\t\r\n\b");
			temp += wrapper + prop + wrapper + separator;
		}
		//not to use the last separator
		if(Utils.isNullOrEmpty(temp) || temp.length() > 1){
			temp = temp.substring(0, temp.length()-1);
		}
		return temp;
	}
	
	
	//-- -------------------------------------------------------------------------

	public static String getSimplePropertiesNamesValues(Object target, String separator, String wrapper){
		
		Map<String, Object> map  = getPropertyVsTypeMap(target);
		
		//Ordering the properties names
		Object[] objArr = map.keySet().toArray();
		Arrays.sort(objArr);
		List lst = Arrays.asList(objArr);
		
		Class[] valid = new Class[]{String.class, Number.class, java.util.Date.class};
		List<Object[]> props = new ArrayList<Object[]>();
		
		String temp  = "";
		
		for (Iterator iter = lst.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Class returnType  = (Class)map.get(key);
			Field field = null;
			String strDate;
			try {				
				field = target.getClass().getDeclaredField(key);				
			} catch (SecurityException e1) {

			} catch (NoSuchFieldException e1) {				
			}			
			
			if(Utils.isAssignableFrom(returnType, valid)){
				try {
					
					if(returnType.isAssignableFrom(java.util.Date.class)){
						try {
							field.setAccessible(true);
							strDate = Utils.getFormattedDate((java.util.Date)field.get(target), "dd/MM/yyyy");
							props.add(new String[]{key,strDate}); 
							field.setAccessible(false);
							
						} catch (Exception e) {

						}
						
					}else if(returnType.isAssignableFrom(java.sql.Date.class)){
						try {
							field.setAccessible(true);
							java.sql.Date dateSql = (java.sql.Date)field.get(target);
							strDate = Utils.getFormattedDate(new java.util.Date(dateSql.getTime()),"dd/MM/yyyy");
							props.add(new String[]{key,strDate});
							field.setAccessible(false);
							
							
						} catch (Exception e) {
							// TODO: handle exception
						}
						
					}else{
						props.add(new String[]{key,BeanUtils.getProperty(target, key)});
					}
					
				
					
				} catch (Exception e) {
					//log.error("Error getting value of >"+key+"<", e);
				}
				
			}				
			//--if()
		}//keySet
		
		Object[] val  = null;
		String prop  = "";
		String key = "";
		
		for (Iterator<Object[]> iter = props.iterator(); iter.hasNext();) {
			val = iter.next();			
			if(val == null){
				prop = "";
			}
			else{
				if(val[1]!=null)
					prop  = val[1].toString();
				else
					prop = "";
				if(val[0]!=null)
					key = val[0].toString();
				else
					key = "";
			}
			temp += key + wrapper + prop + separator;
		}
		//not to use the last separator
		if(!Utils.isNullOrEmpty(temp) || temp.length() > 1){
			temp = temp.substring(0, temp.length()-1);
		}
		return temp;
	}
	
	public static DynaBean getEmptyDynaBean(String clazzName, DynaProperty[] beanProperties){
		final String EMPTY ="";
		BasicDynaClass emtypDynaClazz = new BasicDynaClass(clazzName, BasicDynaBean.class, beanProperties);
		DynaBean dynaBean = new BasicDynaBean(emtypDynaClazz);
		try
		{
			for (DynaProperty dynaProp : beanProperties){
				Class type = dynaProp.getType();
				
				if (type.equals(String.class)) {
					PropertyUtils.setProperty( dynaBean, dynaProp.getName(),EMPTY);						
				} else if (type.equals(int.class) || type.equals(Integer.class)) {
					PropertyUtils.setProperty( dynaBean, dynaProp.getName(), 0);
				} else if (type.equals(long.class) || type.equals(Long.class)) {
					PropertyUtils.setProperty( dynaBean, dynaProp.getName(), 0);
				} else if (type.equals(float.class) || type.equals(Float.class)) {
					PropertyUtils.setProperty( dynaBean, dynaProp.getName(), 0);
				} else if (type.equals(double.class) || type.equals(Double.class)) {
					PropertyUtils.setProperty( dynaBean, dynaProp.getName(), new Double(0));
				} else if (type.equals(BigDecimal.class)) {
					PropertyUtils.setProperty( dynaBean, dynaProp.getName(), 0);
				} else if (type.equals(Timestamp.class)) {
					PropertyUtils.setProperty( dynaBean, dynaProp.getName(), 0);			
				}else if (type.equals(Boolean.class)) {
					PropertyUtils.setProperty( dynaBean, dynaProp.getName(), new Boolean(false));			
				}  
				
			}
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return dynaBean;
	}

	
	
	/**
	 * get all properties values with getter methods defined separated by 
	 * separator and enclosed by wrapper characters
	 * @param target
	 * @param separator
	 * @param wrapper
	 * @return
	 */
	public static String getPropertiesValuesAsHtml(Object target, String separator) throws Exception{
		List props  = getGetterMethod(target);		
		String temp = "";
		String prop = "";
		Object propValue ="";
				
		
		for (Iterator iter = props.iterator(); iter.hasNext();) {
				Method method  = (Method)iter.next();
				prop = getPropertyForBeanProp(method.getName());
				propValue = method.invoke(target, (Object[])null);
				if (!Utils.isNullOrEmpty(propValue) && isNativeOrString(propValue) ){
					temp += separator + prop + "=" + propValue;
				}
		}
		return temp;
	}
	
	public static String getPropsValsAsString(Object target, String separator) throws Exception{
		List props  = getGetterMethod(target);		
		String temp = "";
		String prop = "";
		Object propValue ="";
				
		
		for (Iterator iter = props.iterator(); iter.hasNext();) {
				Method method  = (Method)iter.next();
				prop = getPropertyForBeanProp(method.getName());
				propValue = method.invoke(target, (Object[])null);
				if (propValue!=null && !isCollectionClass(propValue) ){
					temp += separator + prop + "=" + propValue;
				}
		}
		return temp;
	}
	

	public static Object executeGetterMethod(Object target, String methodName) throws Exception{
		Object resultObject=null;
		List props  = getGetterMethod(target);
		for (Iterator iterator = props.iterator(); iterator.hasNext();) {
			Method method = (Method) iterator.next();
			if(method.getName().equals(methodName)){
				return method.invoke(target, (Object[])null);
			}
		}
		return resultObject;
	}
	
	public static void setValue(Object target,String fieldName, Object value) {
		Field field;
		try {
			field = target.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
	        field.set(target, value);
		} catch (Exception e) {
			//ignored
		} 	  
    }
	
	public static String getBeanStatusAsString(Object target, String separator){
		String result = "";
		Map map  = null;
		try {
			map = BeanUtils.describe(target);
			for (Iterator iter = map.values().iterator(); iter.hasNext();) {
				String prop = (String) iter.next();
				if(prop.startsWith("class")){
					continue;
				}
				result += separator + " " + prop + "=" + map.get(prop);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;		
	}
	
	/**
	 * get all the setters names representing properties with the specifieds prefixes
	 * @param bean
	 * @param prefixes
	 * @return
	 */
	public static List<String> getAllSettersPrefixes(Object bean, String[] prefixes){
		List<String> methodNames  = new ArrayList<String>();		
		for(int i=0; i<prefixes.length; i++){
			methodNames.addAll(getAllSettersPrefix(bean, prefixes[i]));
		}
		return methodNames;
		
	}
	
	/**
	 * get all the getters names representing properties with the specifieds prefixes
	 * @param bean
	 * @param prefixes
	 * @return
	 */
	public static List<String> getAllGettersPrefixes(Object bean, String[] prefixes){
		List<String> methodNames  = new ArrayList<String>();		
		for(int i=0; i<prefixes.length; i++){
			methodNames.addAll(getAllGettersPrefix(bean, prefixes[i]));
		}
		return methodNames;		
	}
	
	
	/**
	 * Uses getter method name to call a getter on target to obtain the real target
	 * then it calls the setter method with the parameter parameter so it set it on 
	 * the real target object
	 * @param getter
	 * @param target
	 * @param setter
	 * @param parameter
	 */
	public static void getObjectAndSetAProp(String getter, Object target, String setter, Object parameter){
		Class tClass  = target.getClass();
		//log.debug("target class <"+ tClass +">");
		try {
			Method getMethod  = tClass.getMethod(getter, (Class[])null);
			//log.debug("getMethod <"+ getMethod +">");
			Object realTarget  = getMethod.invoke(target, (Object[])null);
			//log.debug("realTarget <"+ realTarget +">");
			Class rtClass  = realTarget.getClass();
			Method setMethod  = null;
			//find the first method that matches
			Class interfaces[]  = parameter.getClass().getInterfaces();
			for(int i=0; i< interfaces.length; i++){
				try{
					setMethod  = rtClass.getMethod(setter, new Class[]{interfaces[i]});
					break;
				}catch (Exception e) {}
			}
			if(setMethod == null)
				rtClass.getMethod(setter, new Class[]{parameter.getClass()});			
						
			setMethod.invoke(realTarget, new Object[]{parameter});
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	/**
	 * substring the getterMethod name to obtain the property name
	 * @param getterName
	 * @return
	 */
	public static String getPropertyForBeanProp(String getterName){
		try{
			String noGet = getterName.substring(3);
			String init = noGet.substring(0,1).toLowerCase();
			String end = noGet.substring(1);
			return init + end;
		}catch(Exception e){
			return null;
		}
		
	}
	
	public static String getPropertyForBeanPropForBoolean(String isName){
		try{
			String noGet = isName.substring(2);
			String init = noGet.substring(0,1).toLowerCase();
			String end = noGet.substring(1);
			return init + end;
		}catch(Exception e){
			return null;
		}
		
	}
	
	/**
	 * does the method name represents a setter
	 * @param methodName
	 * @return
	 */
	private static boolean isSetter(String methodName){
		return methodName.startsWith(SET);			
	}
	
	private static boolean isNativeOrString(Object type){		
		return type instanceof String || type instanceof Number;
	}	
	
	public static boolean isNativeOrLang(Object type){
		boolean retval = false;
		if (type.equals(String.class)) {
			retval = true;						
		} else if (type.equals(int.class) || type.equals(Integer.class)) {
			retval = true;
		} else if (type.equals(long.class) || type.equals(Long.class)) {
			retval = true;
		} else if (type.equals(float.class) || type.equals(Float.class)) {
			retval = true;
		} else if (type.equals(double.class) || type.equals(Double.class)) {
			retval = true;
		} else if (type.equals(BigDecimal.class)) {
			retval = true;
		} else if (type.equals(Timestamp.class)) {
			retval = true;						
		} else if (type.equals(Date.class)){
			retval = true;
		}
		return retval;
	}
	
	
	/**
	 * does the method name represents a getter
	 * @param methodName
	 * @return
	 */
	private static boolean isGetter(String methodName){
		boolean excluded  = false;
		for (int i = 0; i < toExclude.length; i++) {
			if(methodName.equals(toExclude[i]))
				excluded = true;
		}		
		return methodName.startsWith(GET) && !excluded;			
	}
	
	private static boolean isGetterOrBoolean(String methodName){
		boolean excluded  = false;
		for (int i = 0; i < toExclude.length; i++) {
			if(methodName.equals(toExclude[i]))
				excluded = true;
		}		
		return (methodName.startsWith(GET) || methodName.startsWith("is")) && !excluded;			
	}
	
	
	
	
	
	/**
	 * 
	 * @param methods methods wich should be called to set the specified properties
	 * @param target target object on wich the properties will be set
	 * @param shell  object to be setted
	 */
	public static void setObjectIntoProperties(List methods, Object target, Object shell){		
		Class clazz  = target.getClass();
		Class[] paramType  = new Class[]{shell.getClass()};
		Object[] args  = new Object[]{shell};		
		try{
			for (Iterator iter = methods.iterator(); iter.hasNext();) {
				String prop = (String) iter.next();
				Method method  = clazz.getMethod(prop, paramType);
				method.invoke(target, args);				
			}					
		}catch(Exception e){
			//log.error("error populando el objeto con los metodos >" + methods + "<");
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * convenience method encapsulating bean util copy properties to use a runtime exception
	 * @param target
	 * @param source
	 */
	public static void copyProperties(Object target, Object source){
		try {

			//registerDefaultConverters();
			BeanUtils.copyProperties(target, source);			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}
	
//	private static void registerDefaultConverters(){
//		java.util.Date defaultValue = null;
//		DateConverter converter = new DateConverter(defaultValue);
//		ConvertUtils.register(converter, java.util.Date.class);
//	}
        
        static{
		java.util.Date defaultValue = null;
		DateConverter converter = new DateConverter(defaultValue);
		ConvertUtils.register(converter, java.util.Date.class);
	}

	public static void copyPropertiesExclude(Object target, Object source, String[] exclude){
		try {
			org.springframework.beans.BeanUtils.copyProperties(source, target, exclude);
		} catch (FatalBeanException e) {
			//log.debug("guacalaaaaaa !!!!", e);
		}
			
	}
	
	/**
	 * 
	 * @param bean
	 * @return Map with property name as key and the result of the getter
	 * 			as the object related to that key
	 */
	public static Map getProperties(Object bean){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Method[] methods = bean.getClass().getMethods();
			Object value = null;
			String methodStr = null;
			
			for(int i =0; i<methods.length; i++){
				if( !isGetter(methods[i].getName()) ){					
					continue;
				}
					
				methodStr = getPropertyForBeanProp(methods[i].getName());
				value = methods[i].invoke(bean, (Object[])null);
				map.put(methodStr, value);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return map;
		
	}//getProperties
	
	
	/**
	 * 
	 * @param bean
	 * @return Map with property name as key and the result of the getter
	 * 			as the object related to that key
	 */
	public static Map getPropertiesAll(Object bean){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Method[] methods = bean.getClass().getMethods();
			Object value = null;
			String methodStr = null;
			
			for(int i =0; i<methods.length; i++){
				if( !isGetterOrBoolean(methods[i].getName()) ){					
					continue;
				}
					
				if(methods[i].getName().startsWith(GET)){
					methodStr = getPropertyForBeanProp(methods[i].getName());	
				}else{
					methodStr = getPropertyForBeanPropForBoolean(methods[i].getName());
				}
				
				value = methods[i].invoke(bean, (Object[])null);
				map.put(methodStr, value);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return map;
		
	}//getProperties
	
	public static Map<String, Object> getPropertyVsTypeMap(Object bean){
		Map<String, Object> map = new HashMap<String, Object>(0);
		try {
			Method[] methods = bean.getClass().getMethods();
			Object value = null;
			String methodStr = null;
						
			for (Method method : methods) {
				if( !isGetter(method.getName()) ){					
					continue;
				}
				methodStr = getPropertyForBeanProp(method.getName());
				value = method.getReturnType();
				map.put(methodStr, value);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return map;
	}
	
	
	
	/**
	 * 
	 * @param bean
	 * @return list with all the properties names representing a collection
	 *  with MAP(propertyNameAsString, ReturnType)
	 */
	public static List<String> getAllCollectionProps(Object bean){
		Map map = null;
		List<String> props = new ArrayList<String>();
		
		//-- ----------------------------------------------------------
		// fill map with property, return type
		map = getPropertyVsTypeMap(bean);
		//validate if is the required type
		for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Class temp  = (Class)map.get(key);
			if( isCollectionClass(temp)){
				props.add(key);
			}
		}//for
		return props;
	}
	
	
	
	public static boolean isCollectionClass(Class clazz){
		return 	clazz.equals(Collection.class)   
				|| clazz.equals(Map.class)
				|| clazz.equals(Set.class);
	}
	
	
	public static boolean isCollectionClass(Object obj){
		return 
			obj instanceof List || 
			obj instanceof Set || 
			obj instanceof Map ||
			obj instanceof PersistentCollection ||
			obj instanceof AbstractPersistentCollection ;				
	}
	
	
	/**
	 * Copies all elements from source to target if the
	 * type is no Set / Collection / Map
	 * @param source
	 * @param target
	 * @param aditionalExcludes aditional ignore properties during copy
	 */
	public static void copyPropertyNoCollection(Object source, Object target, List<String> aditionalExcludes){
		try {			
			List<String> props  = getAllCollectionProps(target);
			if(aditionalExcludes != null){
				props.addAll(aditionalExcludes);	
			}			
			
			String[] temp = new String[props.size()];
			int i=0;
			for (Iterator iter = props.iterator(); iter.hasNext();i++) {
				temp[i] = (String)iter.next();				
			}	
			
		//	//logDebug(temp);
			
			copyPropertiesExclude(target, source, temp);
			
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	private static void //logDebug(Object[] obj){
		int i =0;
		/*for (Object object : obj) {			
			//log.debug( i++ + "-->" + object.toString());
		}
	}//*/
	
	
	/**
	 * convenience method, calls {@link #copyPropertyNoCollection(Object, Object, List)} 
	 * @param source
	 * @param target
	 * @param excludes
	 */
	public static void copyPropertyNoCollection(Object source, Object target, String[] excludes){
		copyPropertyNoCollection(source, target, Arrays.asList(excludes));
	}
	
	
	

	
	
}//class
