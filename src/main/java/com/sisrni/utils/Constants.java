package com.sisrni.utils;

public class Constants {
	public static final String ERROR = "failure";
	public static final String EXCEPTION = "exception";
	public static final String SUCCESS = "success";
	public static final String CANCEL = "cancel";
	public static final String COMMA = ",";
	public static final String BLANK = " ";
	public static final String EMPTY_STRING = "";

	public static final String SECURITY_MODULE = "SECURITY_MODULE";

	public static final String KEY_DELIMITER = "|";
	public static final String KEY_ATRIB = "key";
	public static final String QUOTATION_MARK = "'";
	public final static String AMP = "&";
	public final static String EQUAL = "=";

	public final static String DATE_PATTERN = "dd/MM/yyyy";
	public final static String DATE_HOUR_PATTERN = "dd/MM/yyyy HH:mm:ss";
	public final static String YEAR_PATTERN = "yyyy";

	public final static String NUMBER_PATTERN = "###,###,##0.00";
	public final static String INTEGER_PATTERN = "###,###,##0";
	public final static int DECIMAL_PLACES = 2;

	public final static String REG_EXP_NIT = "^\\d{4}[-| ]?\\d{6}[-| ]?\\d{3}[-| ]?\\d{1}$";
	public final static String REG_EXP_SIMPLE_NIT = "^\\d{14}$";
	public final static String REG_EXP_MONT_YEAR ="^\\d{2}[-| ]\\d{4}$";
	public final static String REG_EXP_POSITIVE_INTEGER ="^\\d*\\.{0,0}\\d+$";
	public final static String REG_EXP_POSITIVE_NUMBER ="^\\d*\\.{0,2}\\d+$";
	
	public static final int MAX_RESULTS = 1000;
	public static final int INIT_RESULTS = 0;
	public static final String SELECT_ALL = "select * from ( ";
	public static final String WHERE_MAX_ALL = "\n" + " ) where ROWNUM <= " + MAX_RESULTS ;

}
