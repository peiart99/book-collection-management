package com.peiart99.main;

import java.util.ArrayList;

public class Collection extends DbObject{
    private ArrayList<DbObject> collection;
    private int collectionSize;

    public Collection(String name) {
        super(name);
        this.collection = new ArrayList<DbObject>();
        this.collectionSize = 0;
    }

    public int getCollectionSize() {
        return collectionSize;
    }

    public ArrayList<DbObject> getCollection() {
        return collection;
    }

    public void addBook(DbObject book) {
        this.collection.add(book);
        if(book instanceof Series) {
            this.collectionSize += ((Series)book).getVolumes();
        }else {
            this.collectionSize++;
        }
    }
}
