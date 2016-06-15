package com.sisrni.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;

/**
 * Static convenience methods for JavaBeans: for instantiating beans, checking
 * bean property types, copying bean properties, etc.
 * 
 * <p>
 * Mainly for use within the framework, but to some degree also useful for
 * application classes.
 * 
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Rob Harrop
 */

public abstract class BeanUtils extends org.springframework.beans.BeanUtils {

	public static void setProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

		BeanUtilsBean.getInstance().setProperty(bean, name, value);
	}

}
