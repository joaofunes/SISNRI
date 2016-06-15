/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALVARADO
 */
public class Md5Generator {

    public static String generar(String cadena) {
        String cadenaMd5 = "";
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");

            md.update(cadena.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            cadenaMd5 = sb.toString();


        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Md5Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cadenaMd5;
    }
}
