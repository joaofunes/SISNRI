/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.jasper.test;

import java.io.Serializable;

/**
 *
 * @author marlon.andrade
 */
public class Libro implements Serializable {

    private String isbn;
    private String title;
    private Autor autor;

    public Libro() {
    }

    public Libro(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public Libro(String isbn, String title, Autor autor) {
        this.isbn = isbn;
        this.title = title;
        this.autor = autor;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the autor
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
