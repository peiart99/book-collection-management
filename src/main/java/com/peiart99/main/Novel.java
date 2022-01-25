package com.peiart99.main;

import com.peiart99.enums.NovelGenre;
import com.peiart99.interfaces.ClassName;

public class Novel extends Book implements ClassName {

    private NovelGenre genre;

    public Novel(String name, String author, String publisher, int volume, NovelGenre genre) {
        super(name, author, publisher, volume);
        this.genre = genre;
    }

    public NovelGenre getGenre() {
        return genre;
    }

    @Override
    public String getClassName() {
        return "Novel";
    }
}
