package me.cutenyami.simplexclient;

import net.fabricmc.api.ModInitializer;
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
    }

    public void start() {

    }

    public static SimplexClient getInstance() {
        return instance;
    }
}
