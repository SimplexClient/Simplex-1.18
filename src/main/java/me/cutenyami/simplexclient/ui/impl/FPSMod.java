package me.cutenyami.simplexclient.ui.impl;

import me.cutenyami.simplexclient.ui.ModCreator;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class FPSMod extends ModCreator {

    public FPSMod() {
        super("FPS", 100, 100);
    }

    @Override
    public void render(MatrixStack matrices) {
        drawStringWithShadow(matrices, mc.textRenderer, "FPS: " + MinecraftClient.currentFps, getX(), getY(), -1);
    }

    @Override
    public void renderDummy(MatrixStack matrices, int mouseX, int mouseY) {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
