package com.peiart99.main;

public class Comicbook extends Book{

    private String illustrator;

    public Comicbook(String name, String author, String illustrator, String publisher, int volume) {
        super(name, author, publisher, volume);
        this.illustrator = illustrator;
    }

    public String getIllustrator() {
        return illustrator;
    }
}
