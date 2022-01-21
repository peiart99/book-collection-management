package com.peiart99.main;

import com.peiart99.interfaces.ClassName;

import java.util.ArrayList;

public class Series extends DbObject implements ClassName {

    private int volumes;
    private ArrayList<Book> books;

    public Series(String name) {
        super(name);
        this.books = new ArrayList<Book>();
        this.volumes = 0;
    }

    public void addVolume(Book book) {
        this.books.add(book);
        this.volumes++;
    }

    public void addVolumes(int volumes, Book book) {
        for(int i = 0; i < volumes; i++) {
            book.setVolume(i + 1);
            this.books.add(book);
        }
        this.volumes += volumes;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public int getVolumes() {
        return volumes;
    }

    @Override
    public String getClassName() {
        return "Series";
    }
}
