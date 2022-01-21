package com.peiart99.main;

import com.peiart99.interfaces.ClassName;

public abstract class DbObject implements ClassName {
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

    @Override
    public String getClassName() {
        return "DbObject";
    }
}
