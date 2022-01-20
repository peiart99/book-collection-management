package com.peiart99.main;

import java.util.ArrayList;

public class Series extends DbObject{

    private int volumes;
    private ArrayList<Book> books;

    public Series(String name, int volumes) {
        super(name);
        this.books = new ArrayList<Book>();
        this.volumes = 0;
    }

    public void addVolume(int volume, Book book) {
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


    public int getVolumes() {
        return volumes;
    }
}
