package com.peiart99.main;

import com.peiart99.enums.NovelGenre;

public class Novel extends Book{

    private NovelGenre genre;

    public Novel(String name, String author, String publisher, int volume, NovelGenre genre) {
        super(name, author, publisher, volume);
        this.genre = genre;
    }

    public NovelGenre getGenre() {
        return genre;
    }
}
