package me.cutenyami.simplexclient;

import me.cutenyami.simplexclient.ui.ModManager;
import me.cutenyami.simplexclient.ui.impl.FPSMod;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.hud.InGameHud;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimplexClient implements ModInitializer {

    private static SimplexClient instance;

    public final Logger LOGGER = LoggerFactory.getLogger("SimplexClient");
    public final String AUTHOR = "SimplexMC";
    public final String VERSION = "1.0";

    @Override
    public void onInitialize() {
        instance = this;
        LOGGER.info("Starting SimplexClient v" + VERSION + " by " + AUTHOR);
        ModManager.registerMod(new FPSMod());
    }

    public void start() {

    }

    public static SimplexClient getInstance() {
        return instance;
    }
}
