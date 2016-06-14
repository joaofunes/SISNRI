/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.jasper.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marlon.andrade
 */
public class Autor implements Serializable {

    private String id;
    private String name;
    private List<Libro> libros;

    public Autor() {
    }

    public Autor(String id, String name, List<Libro> libros) {
        this.id = id;
        this.name = name;
        this.libros = libros;
    }

    public static Autor getSample() {
        Autor autor = new Autor();
        autor.libros = new ArrayList<Libro>();
        autor.setId("01");
        autor.setName("Autor 01");
        for (int i = 1; i <= 13; i++) {
            Libro l = new Libro((i < 10 ? "0" : "") + i, "Libro " + (i < 10 ? "0" : "") + i);
            autor.getLibros().add(l);
        }
        return autor;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the libros
     */
    public List<Libro> getLibros() {
        return libros;
    }

    /**
     * @param libros the libros to set
     */
    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
