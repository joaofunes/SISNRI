package com.sisrni.utils;

import java.math.BigDecimal;

/**
 *
 * @author cristian oswaldo fuentes
 */
public class FormulasHelper {

    static BigDecimal cien = new BigDecimal(100);
    static BigDecimal uno = BigDecimal.ONE;

    /**
     * Metodo para calcular el seguro de deuda
     *
     * @param montoCredito monto sobre el cual s va a calcular el seguro
     * @return seguro de deuda
     */
    public static BigDecimal seguroDeuda(BigDecimal montoCredito) {
      
        double montodc;
        double seguroDeuda;
        double mont;
        BigDecimal seguro;
        
        try {
            montodc=montoCredito.doubleValue();
            mont = montodc / 1000;
            seguroDeuda = mont * 0.58;  
            seguro=BigDecimal.valueOf(seguroDeuda);
        } catch (Exception e) {
            System.out.println("error calculando seguro deuda"+e);
            return BigDecimal.ZERO;
        }
       return seguro;

    }

    /**
     * Metodo para calcular el seguro de danio para una propiedad
     *
     * @param valorPropiedad valor de la propiedad a la cual se calcula el
     * seguro
     * @return seguro de danios
     */
    public static BigDecimal seguroDanios(BigDecimal valorPropiedad) {
        double montodc, mont, seguroDanios;
        BigDecimal seguro;
        
        try {
            montodc = valorPropiedad.doubleValue();
            mont = montodc / 1000;
            seguroDanios = mont * 0.3362;
            seguro = BigDecimal.valueOf(seguroDanios);
        } catch (Exception e) {
            System.out.println("error calculando el seguro de danios"+e);
            return BigDecimal.ZERO;
        }

        return seguro;

    }

    /**
     * Metodo para calcular la comision por otorgamiento de un credito
     *
     * @param porcentajeComision porcentaje de comision segun linea de Credito
     * @return comision por otorgamiento
     */
    public static BigDecimal comisionOtorgamiento(BigDecimal porcentajeComision) {
        return null;

    }

    /**
     * Metodo para calcular el impuesto de hipoteca cuando la garantia
     * presentada es hipoteca
     *
     * @param montoCredito monto del credito solicitado por el asociado
     * @return impuesto sobre la hipoteca
     */
    public static BigDecimal impuestoHipoteca(BigDecimal montoCredito) {
      
        double motcred=montoCredito.longValue();
        double porcenfijo=0.38;
        double ih;
    
        try {
            ih = (motcred / 100) * porcenfijo;
        } catch (Exception e) {
            System.out.println("error calculando el impuesto de hipoteca" + e);
            return BigDecimal.ZERO;
        }
         return BigDecimal.valueOf(ih);
    }

    /**
     * Metodo para calcular el impuesto de compra-venta cuando es por la compra
     * on venta de un bien raiz
     *
     * @param precioPropiedad precio de la propiedad
     * @return impuesto Compra-Venta
     */
    public static BigDecimal impuestoCompraVenta(BigDecimal precioPropiedad) {
        
        double pp = precioPropiedad.doubleValue();
        double icv;
        double porcint = 0.63;
        int ent = 100;
        
        try {
            icv = (pp / ent) * porcint;
        } catch (Exception e) {
            System.out.println("error calculando el impuesto a la tranferencia" + e);
            return BigDecimal.ZERO;
        }
        
        return BigDecimal.valueOf(icv);
    }

    /**
     * Metodo para calcular el impuesto por tranferencia aplicado a ciertas
     * lienas de credito
     *
     * @param precioPropiedad precio de la propiedad
     * @return impuesto por transferencia
     */
    public static BigDecimal impuestoTransferencia(BigDecimal precioPropiedad) {
        BigDecimal valorVariable = new BigDecimal(28571.43);
        BigDecimal porcentaje = new BigDecimal(0.03);
        BigDecimal impuesto;

        try {
            BigDecimal impuestoTrans = precioPropiedad.subtract(valorVariable);
            impuesto = impuestoTrans.multiply(porcentaje);
            if (impuesto.compareTo(BigDecimal.ZERO) == -1) {
                impuesto=BigDecimal.ZERO;
            }
        } catch (Exception e) {
            System.out.println("error calculando el impuesto a la tranferencia" + e);
            return BigDecimal.ZERO;
        }

        return impuesto;

    }

    /**
     * metodo para calcular el impuesto por cancelacion de Hipoteca
     *
     * @param montoHipoteca Monto de la hipoteca
     * @return valor de la canclacion de la hipoteca
     */
    public static BigDecimal cancelacionHipoteca(BigDecimal montoHipoteca) {
        // cancelacionHipoteca=((montoHipoteca-9000)/(1000*0.13))+8.91

        double mh = montoHipoteca.doubleValue();
        double mult = 1000 * 0.13;
        double formula;
        double resta = mh - 9000;

        try {
            formula = (resta / mult);
        } catch (Exception e) {
            System.out.println("Error Calculando la cancelacion " + e);
            return BigDecimal.ZERO;
        }

        return BigDecimal.valueOf(formula);

    }

    /**
     * Metodo para calcular el complemento de aportaciones requerido por la
     * linea de credito
     *
     * @param aportacion aportaciones que posee el asociado en la cooperativa
     * @param aportacionRequeridaLineaCrdito aportaciones requeridas por la
     * linea de credito
     * @return complemento de aportaciones
     */
    public static BigDecimal complemetoAportaciones(BigDecimal aportacion, BigDecimal aportacionRequeridaLineaCrdito) {
        return null;
    }

    /**
     * Metodo para calcular la cuota de un credito solicitado por un asociado
     *
     * @param monto monto deseado por el cual se requiere hacer el prestamos
     * @param interes tasa de interes segun linea de credito
     * @param plazo periodo de tiempo en meses para el cual s quiere obtener el
     * credito
     * @return cuota del credito
     */
    public static BigDecimal cuotaCredito(BigDecimal monto, BigDecimal interes, Integer plazo) {

        double montod = monto.doubleValue();
        double interesd;
        int plazod;
        BigDecimal cuotaCredito;
        try {
            interesd = interes.doubleValue();
            plazod = plazo.intValue();
            double interesMensual = (interesd / 12);

            double factorDeDescuento = (Math.pow((1 + interesMensual), plazod) - 1) / (interesMensual * Math.pow((1 + interesMensual), plazod));
            double cuota = montod / factorDeDescuento;
            cuotaCredito=BigDecimal.valueOf(cuota);
           
        } catch (Exception e) {
            System.out.println("error N/A calculando cuota de credito "+e);
            return BigDecimal.ZERO;
        }
          return cuotaCredito;
    }

    /**
     * Metodo para calcular la RentaNneta Mensual de un asociado
     *
     * @param ingresos Suma total de todos los ingresos de un asociado
     * @param egresos Suma total de Todos los egresos d un asociado
     * @return renta neta mensual
     */
    public static BigDecimal rentaNetaMensual(BigDecimal ingresos, BigDecimal egresos) {
        return null;
    }

    public static void provisionDiariaPrestamo() {

    }

    public static void tasaEfectiva() {

    }

    /**
     * Metodo para calcular el plazo del credito
     *
     * @param monto Monto solicitado
     * @param cuota Cuota Solicitada
     * @param interes tasa de interes en base a la linea de credito
     * @return plazo
     */
    public static Integer calcularPlazo(BigDecimal monto, BigDecimal cuota, BigDecimal interes) {
        return 10;
    }

    /**
     * Metodo para calcular el monto que se puede realizar el prestamo
     *
     * @param cuota cuota solicitada
     * @param plazo plazo solicitado
     * @param interes tasa de interes en base a la linea de credito
     * @return monto
     */
    public static BigDecimal calcularMonto(BigDecimal cuota, Integer plazo, BigDecimal interes) {
        return BigDecimal.TEN;
    }
    
    /**
     * Metodo para calcular el complemeto de aportaciones
     * @param monto
     * @param aportacion
     * @return 
     */
    public static BigDecimal calculoComplementoAportacion(BigDecimal monto,BigDecimal aportacion) {
        int num=15;
        BigDecimal mont = BigDecimal.ZERO;
        double montod=monto.doubleValue();
        double aportac=aportacion.doubleValue();
        double compapor;
        

        if (monto.intValue() < 12000) {
            compapor = montod / num;
            mont = BigDecimal.valueOf(compapor);
        } else if (monto.intValue() < 20000) {
            mont = BigDecimal.valueOf(1000);
        } else if (monto.intValue() < 30000) {
            mont = BigDecimal.valueOf(1200);
        } else if (monto.intValue() < 40000) {
            mont = BigDecimal.valueOf(1400);
        } else if (monto.intValue() < 50000) {
            mont = BigDecimal.valueOf(1500);
        } else if (monto.intValue() < 60000) {
            mont = BigDecimal.valueOf(1800);
        } else if (monto.intValue() < 70000) {
            mont = BigDecimal.valueOf(1000);
        }
        
        if(mont.compareTo(aportacion)==-1){
             mont=BigDecimal.ZERO;
        }else{
            mont=aportacion.subtract(mont);
        }
        
        return mont.abs();
    }

    
     public static BigDecimal calculoGastosTotales(
            BigDecimal complementoAportacion,
            BigDecimal comisionOtorgamiento,
            BigDecimal impuestoHipoteca,
            BigDecimal cancelacionHipoteca,
            BigDecimal impuestoCompraVenta,
            BigDecimal impuestoTransferencia) {

        BigDecimal gastoTotal = BigDecimal.ZERO;

        if (complementoAportacion != null) {
            gastoTotal = gastoTotal.add(complementoAportacion);
        }
        if (comisionOtorgamiento != null) {
            gastoTotal = gastoTotal.add(comisionOtorgamiento);
        }
        if (impuestoHipoteca != null) {
            gastoTotal = gastoTotal.add(impuestoHipoteca);
        }
        if (cancelacionHipoteca != null) {
            gastoTotal = gastoTotal.add(cancelacionHipoteca);
        }
        if (impuestoCompraVenta != null) {
            gastoTotal = gastoTotal.add(impuestoCompraVenta);
        }
        if (impuestoTransferencia != null) {
            gastoTotal = gastoTotal.add(impuestoTransferencia);
        }
        return gastoTotal;
    }


}
