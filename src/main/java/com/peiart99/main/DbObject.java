package com.peiart99.main;

public abstract class DbObject {
    private String name;
    private int ID;
    private static int ID_numerator = 0;

    public DbObject(String name) {
        this.ID = ID_numerator++;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

}
