package com.peiart99.main;

import com.peiart99.interfaces.DeleteObject;

import java.util.ArrayList;

public class Collection extends DbObject implements DeleteObject {
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

    public void setCollectionSize(int collectionSize) {
        this.collectionSize = collectionSize;
    }

    @Override
    public void deleteObject(int ID) {
        this.collection.removeIf(object -> object.getID() == ID);
    }
}
