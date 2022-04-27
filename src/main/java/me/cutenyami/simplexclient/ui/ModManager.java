package me.cutenyami.simplexclient.ui;

import java.util.ArrayList;

public class ModManager {

    private static final ArrayList<ModCreator> mods = new ArrayList<>();

    public static void registerMod(ModCreator mod) {
        mods.add(mod);
    }

    public static ArrayList<ModCreator> getMods() {
        return mods;
    }
}
