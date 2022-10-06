package com.example.project;

import java.util.ArrayList;

public class Folder {
    private String name;
    private ArrayList<Module> modules = new ArrayList<>();
    private Module currentModule = null;
    long dataCreate;

    public Folder(String name) {
        this.name = name;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(long dataCreate) {
        this.dataCreate = dataCreate;
    }

    public void setCurrentModule(Module currentModule) {
        this.currentModule = currentModule;
    }

    public Module getCurrentModule() {
        return currentModule;
    }
}
