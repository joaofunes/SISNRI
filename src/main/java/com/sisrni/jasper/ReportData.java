package com.sisrni.jasper;

import java.io.Serializable;
import java.util.Arrays;

public class ReportData implements Serializable {

    private static long serialVersionUID = -8697453654789555698L;

    private String format;
    private boolean downloading;
    private byte[] bytes;
    private String nombreLogico;

    public ReportData() {
    }

    public ReportData(String nombreLogico) {
        this.nombreLogico = nombreLogico;
    }

    public ReportData(boolean downloading, byte[] bytes) {
        this.downloading = downloading;
        this.bytes = bytes;
    }

    public ReportData(String contectType, byte[] bytes) {
        this.format = contectType;
        this.bytes = bytes;
    }

    public ReportData(String contectType, boolean downloading, byte[] bytes) {
        this.format = contectType;
        this.downloading = downloading;
        this.bytes = bytes;
    }

    public ReportData(String contectType, boolean downloading, byte[] bytes, String nombreLogico) {
        this.format = contectType;
        this.downloading = downloading;
        this.bytes = bytes;
        this.nombreLogico = nombreLogico;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isDownloading() {
        return downloading;
    }

    public void setDownloading(boolean downloading) {
        this.downloading = downloading;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 18 * hash + (this.format != null ? this.format.hashCode() : 0);
        hash = 29 * hash + (this.downloading ? 1 : 0);
        hash = 2 * hash + Arrays.hashCode(this.bytes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReportData other = (ReportData) obj;
        if ((this.format == null) ? (other.format != null) : !this.format.equals(other.format)) {
            return false;
        }
        if (this.downloading != other.downloading) {
            return false;
        }
        return Arrays.equals(this.bytes, other.bytes);
    }

    public String getNombreLogico() {
        return nombreLogico;
    }

    public void setNombreLogico(String nombreLogico) {
        this.nombreLogico = nombreLogico;
    }
}
