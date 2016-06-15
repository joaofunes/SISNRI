package com.sisrni.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.beanutils.BeanUtils;
import static com.sisrni.utils.Utils.assignableFrom;
import static com.sisrni.utils.Utils.createDate;
import static com.sisrni.utils.Utils.getFormattedDate;
import static com.sisrni.utils.Utils.getFormattedNumber;
import static com.sisrni.utils.Utils.indexOf;
import static com.sisrni.utils.Utils.isNullOrEmpty;
import static com.sisrni.utils.Utils.nvl;

@SuppressWarnings("unchecked")
public class Utils {

    public final static String APP_SHORT_NAME = "SISRNI";

    public static String validateSpecialCharacters(String str) {

        HashMap<String, String> invValid = new HashMap<String, String>();
        invValid.put("'", "''");
        invValid.put("\"", "");

        String temp = str;
        for (String inv : invValid.keySet()) {
            temp = temp.replace(inv, invValid.get(inv));
        }
        return temp;
    }

    public static String clearMaskedNit(String maskedNit) {
        if (Utils.isNullOrEmpty(maskedNit)) {
            return maskedNit;
        }
        String temp = maskedNit.replace("-", "");
        return temp;
    }

    public static String MaskedNit(String nit) {
        if (Utils.isNullOrEmpty(nit)) {
            return nit;
        }
        String temp = nit.substring(0, 4) + "-" + nit.substring(4, 10) + "-" + nit.substring(10, 13) + "-" + nit.substring(13);
        return temp;
    }

    public static String MaskedNrc(String nrc) {
        if (Utils.isNullOrEmpty(nrc)) {
            return nrc;
        }
        String temp = nrc.substring(0, nrc.length() - 1) + "-" + nrc.substring(nrc.length() - 1);
        return temp;
    }

    // Si viene nulo el objeto devuelve el valor de val
    // Si no devuelve el valor de other
    public static Object emptyOrOther(Object obj, Object val, Object other) {
        if (isNullOrEmpty(obj)) {
            return val;
        }
        return other;
    }

    /**
     *
     * @param obj object to be evaluated
     * @return true if object is null or
     * obj.getString.Equals(Constant.EmptyString)
     */
    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || obj.toString().trim().equals("") || obj.toString().length() < 1 || obj.toString().equals(Constants.EMPTY_STRING);
    }

    public static boolean isNullOrEmptyOrZero(Object obj) {
        return obj == null
                || obj.toString().length() < 1
                || obj.toString().equals(Constants.EMPTY_STRING)
                || ((obj instanceof Long && obj.equals(0L)) || (obj instanceof Integer && obj.equals(0)) || (obj instanceof Float && obj.equals(0.00)) || (obj instanceof Double && obj
                .equals(0.00)));
    }

    public static Object nvl(Object obj, Object val) {
        if (isNullOrEmpty(obj)) {
            return val;
        }
        return obj;
    }

    public static String emptyWhenNull(String str) {
        return (String) nvl(str, "N/A");
    }

    public static Boolean secureEqualsString(String str01, String str02) {
        if (isNullOrEmpty(str02)) {
            str02 = "";
        }
        if (isNullOrEmpty(str01)) {
            str01 = "";
        }
        return str01.trim().equalsIgnoreCase(str02.trim());
    }

    /**
     *
     * @param obj
     * @param listing
     * @return true if obj is contained into listing false otherwise
     */
    public static boolean in(Object obj, Object[] listing) {
        return indexOf(obj, listing) != -1;
    }

    public static int indexOf(Object obj, Object[] listing) {
        for (int i = 0; i < listing.length; i++) {
            if (obj.equals(listing[i])) {
                return i;
            }
        }

        if (obj instanceof java.lang.String) {
            for (int i = 0; i < listing.length; i++) {
                if (obj.toString().equals(listing[i])) {
                    return i;
                }
            }
        }// for String

        return -1;
    }

    public static int assignableFrom(Class clazz, Class[] classes) {
        for (int i = 0; i < classes.length; i++) {
            if (classes[i].isAssignableFrom(clazz)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isAssignableFrom(Class clazz, Class[] classes) {
        return assignableFrom(clazz, classes) != -1;
    }

    public static Object getFirst(List lst) {
        for (Iterator iter = lst.iterator(); iter.hasNext();) {
            return iter.next();
        }
        return null;
    }

    /**
     *
     * @param keys string list created with the value of the properties wich
     * represent the unique id wich represent one object
     * @return all the keys separated by Constnts.KEY_DELIMITER
     */
    public static String createKeyChain(List keys) {
        String keyChain = "";
        for (Iterator iter = keys.iterator(); iter.hasNext();) {
            String str = (String) iter.next();
            keyChain += str + Constants.KEY_DELIMITER;
        }
        return keyChain;
    }

    /**
     *
     * @param bean POJO who will be used for getting the values, must be
     * javaBean compliant
     * @param props properties of the bean who will be used to create the
     * keyChain, in the same order in wich it will be used
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public static String createKeyChain(Object bean, List props) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String keyChain = "";

        for (Iterator iter = props.iterator(); iter.hasNext();) {
            String prop = (String) iter.next();
            String value = BeanUtils.getProperty(bean, prop);
            keyChain += value + Constants.KEY_DELIMITER;
        }

        return keyChain;
    }

    /**
     *
     * @param bean POJO who will be used for getting the values, must be
     * javaBean compliant
     * @param props properties of the bean who will be used to create the string
     * quote separated
     *
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public static String createKeyChainQuoteSeparated(Object bean, List props) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String keyChain = "";

        for (Iterator iter = props.iterator(); iter.hasNext();) {
            String prop = (String) iter.next();
            String value = BeanUtils.getProperty(bean, prop);
            keyChain += Constants.QUOTATION_MARK + value + Constants.QUOTATION_MARK;
        }

        return keyChain;
    }

    /**
     * convenience method to create Date with specified format
     *
     * @param str
     * @param format
     * @return
     */
    public static Date createDate(String str, String format) {
        Date ret = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            ret = sdf.parse(str);
        } catch (ParseException e) {
        }
        return ret;
    }

    /**
     * convenience method to create Date with format specified in
     * Constants.DATE_PATTERN actually <b>dd/MM/yyyy</b>
     *
     * @param str
     * @return
     */
    public static Date createDate(String str) {
        return createDate(str, Constants.DATE_PATTERN);
    }

    public static String getFormattedDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getFormattedDate(Date date) {
        return getFormattedDate(date, Constants.DATE_PATTERN);
    }

    public static String getFormattedDateYear(Date date) {
        return getFormattedDate(date, Constants.YEAR_PATTERN);
    }

    public static String getFormattedNumber(Number value) {
        return getFormattedNumber(value, Constants.NUMBER_PATTERN);
    }

    public static Double getNumber(String value) {
        if (isNullOrEmpty(value)) {
            return null;
        } else {
            String temp = value.replace(",", "");
            temp = temp.replace("$", "");
            return new Double(temp);
        }
    }

    public static Integer getInteger(String value) {
        Integer ret = null;
        try {
            if (!isNullOrEmpty(value)) {
                String temp = value.replace(",", "");
                temp = temp.replace("$", "");
                ret = new Integer(temp);
            }
        } catch (NumberFormatException e) {
        }
        return ret;
    }

    public static Long getLong(String value) {
        Long ret = null;
        try {
            if (!isNullOrEmpty(value)) {
                String temp = value.replace(",", "");
                temp = temp.replace("$", "");
                ret = new Long(temp);
            }
        } catch (NumberFormatException e) {
        }
        return ret;
    }

    public static String getFormattedNumber(Number value, String pattern) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        DecimalFormat formatter = (DecimalFormat) format;
        formatter.applyPattern(pattern);
        return formatter.format(value);
    }

    public static List<String> createKeyList(String keyChain) {
        ArrayList keyList = new ArrayList<String>();
        StringTokenizer tok = new StringTokenizer(keyChain, Constants.KEY_DELIMITER);
        while (tok.hasMoreElements()) {
            keyList.add(tok.nextToken());
        }
        return keyList;

    }

    public static String createUrlParameterChain(Map<String, String> props) {
        String keyChain = "";
        String value;
        String key;
        if (!Utils.isNullOrEmpty(props)) {
            for (Object obj : props.keySet()) {
                if (obj instanceof String) {
                    key = (String) obj;
                    value = props.get(key);
                    keyChain += Constants.AMP + key + Constants.EQUAL + value;
                }
            }
        }

        return keyChain;
    }

    public static double roundx(double n, int d) {
        n = n - 0; // force number
        if (d == 0) {
            d = 2;
        }
        double f = Math.pow(10, d);
        n += Math.pow(10, -(d + 1)); // round first
        n = Math.round(n * f) / f;
        n += Math.pow(10, -(d + 1)); // and again
        // n += ""; // force string
        String nstr = String.valueOf(n);
        String out = d == 0 ? nstr.substring(0, nstr.indexOf('.')) : nstr.substring(0, nstr.indexOf('.') + d + 1);
        return Double.parseDouble(out);
    }

    public static Map<String, Date> calculatePeriodo(Date fecha) {
        Map<String, Date> ret = new HashMap<String, Date>();
        if (fecha != null) {
            String diaFinal;
            String sfinal;
            String sinicial;
            String speriodo = Utils.getFormattedDate(fecha, "MM/yyyy");
            Integer month = Integer.parseInt(speriodo.substring(0, 2).trim());
            Integer year = Integer.parseInt(speriodo.substring(3).trim());
            List<Integer> m31s = new ArrayList<Integer>(Arrays.asList(1, 3, 5, 7, 8, 10, 12));
            List<Integer> m30s = new ArrayList<Integer>(Arrays.asList(4, 6, 9, 11));

            if (m31s.contains(month)) {
                diaFinal = "31";
            } else if (m30s.contains(month)) {
                diaFinal = "30";
            } else {
                if (((year % 4 == 0) && !(year % 100 == 0))
                        || (year % 400 == 0)) {
                    diaFinal = "29";
                } else {
                    diaFinal = "28";
                }
            }
            sfinal = diaFinal + "/" + ((month >= 9) ? "" : "0") + month.toString() + "/" + year.toString();
            sinicial = "01/" + ((month >= 9) ? "" : "0") + month.toString() + "/" + year.toString();
            ret.put("fechaInicial", Utils.createDate(sinicial, "dd/MM/yyyy"));
            ret.put("fechaFinal", Utils.createDate(sfinal, "dd/MM/yyyy"));
        }
        return ret;
    }

    public static boolean isNumeric(String val) {
        boolean ret = false;
        try {
            Double.parseDouble(val);
            ret = true;
        } catch (NumberFormatException e) {
        }
        return ret;
    }

    public static String getStackTrace(Exception e) {
        StringWriter out = new StringWriter();
        e.printStackTrace(new PrintWriter(out));
        return out.toString();
    }
}// class
