package me.cutenyami.simplexclient.ui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.Font;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;

public abstract class ModCreator extends DrawableHelper {

    private final String name, description;

    private final int x, y;

    private boolean enabled = true;

    public MinecraftClient mc = MinecraftClient.getInstance();

    public ModCreator(String name, String description, int x, int y) {
        this.name = name;
        this.description = description;
        this.x = x;
        this.y = y;
    }

    public ModCreator(String name, int x, int y) {
        this.name = name;
        this.description = "This is the default description :/";
        this.x = x;
        this.y = y;
    }

    public abstract void render(MatrixStack matrices);

    public abstract void renderDummy(MatrixStack matrices, int mouseX, int mouseY);

    public abstract int getWidth();

    public abstract int getHeight();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
