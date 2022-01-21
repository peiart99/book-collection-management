package com.peiart99.main;

import com.peiart99.interfaces.ClassName;

public class Comicbook extends Book implements ClassName {

    private String illustrator;

    public Comicbook(String name, String author, String illustrator, String publisher, int volume) {
        super(name, author, publisher, volume);
        this.illustrator = illustrator;
    }

    public String getIllustrator() {
        return illustrator;
    }

    @Override
    public String getClassName() {
        return "Comicbook";
    }
}
