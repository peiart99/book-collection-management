package com.peiart99.main;

public abstract class Book extends DbObject{

    private String author;
    private String publisher;
    private int volume;

    public Book(String name, String author, String publisher, int volume) {
        super(name);
        this.author = author;
        this.publisher = publisher;
        this.volume = volume;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
