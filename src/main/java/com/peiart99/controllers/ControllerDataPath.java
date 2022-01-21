package com.peiart99.controllers;

import com.peiart99.main.DbObject;

public class ControllerDataPath {

    private DbObject entry;
    private final static ControllerDataPath INSTANCE = new ControllerDataPath();

    private ControllerDataPath() {}

    public static ControllerDataPath getInstance() {
        return INSTANCE;
    }

    public void setEntry(DbObject entry) {
        this.entry = entry;
    }

    public DbObject getEntry() {
        return entry;
    }

}
