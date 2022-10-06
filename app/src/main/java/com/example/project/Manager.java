package com.example.project;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;

public class Manager {
    private static Folder currentFolder = null;
    private static Module spacedModule = null;
    public static boolean isSpacedRepetition = false;
    private static ArrayList<Folder> folders = new ArrayList<Folder>();

    static {

        Folder eng = new Folder("Английский");
        eng.getModules().add(new Module("Слова к контрольной").addCard("drink", "пить").addCard("meet", "мясо").addCard("fruit", "фрукт"));
        eng.getModules().add(new Module("IELTS словосочетания").addCard("embed", "вставить").addCard("succeed", "преуспеть").addCard("continue", "продолжать"));

        folders.add(eng);
    }

    public static Module getSpacedModule() {
        return spacedModule;
    }

    public static void setSpacedModule(Module spacedModule) {
        Manager.spacedModule = spacedModule;
    }

    public static ArrayList<Folder> getFolders() {
        return folders;
    }

    public static Folder getCurrentFolder() {
        return currentFolder;
    }

    public static ArrayList<Module> getAllModules() {
        ArrayList<Module> allModules = new ArrayList<>();

        for (Folder folder : getFolders()) {
            for (Module m : folder.getModules()) {
                allModules.add(m);
            }
        }

        return allModules;
    }

    public static void setCurrentFolder(Folder currentFolder) {
        Manager.currentFolder = currentFolder;
    }

    public static Folder getFolderByName(String name) {
        for (Folder folder : folders) {
            if (name.toLowerCase().contains(folder.getName().toLowerCase())) {
                return folder;
            }
        }

        return null;
    }
}
