package me.cutenyami.simplexclient.ui.module;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class DraggableComponent {

    private int x, y, width, height, color, lastX, lastY;

    private boolean dragging;

    private final Screen screen;

    public DraggableComponent(int x, int y, int width, int height, int color, Screen screen) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
        this.screen = screen;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getxPosition() {
        return x;
    }

    public int getyPosition() {
        return y;
    }

    public void setxPosition(int x) {
        this.x = x;
    }

    public void setyPosition(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getColor() {
        return color;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void draw(MatrixStack matrices, int mouseX, int mouseY) {
        draggingFix(mouseX, mouseY);
        DrawableHelper.fill(matrices, this.getxPosition(), this.getyPosition(), this.getxPosition() + this.getWidth(), this.getyPosition() + this.getHeight(), this.getColor());
        boolean mouseOverX = (mouseX >= this.getxPosition() && mouseX <= this.getxPosition() + this.getWidth());
        boolean mouseOverY = (mouseY >= this.getyPosition() && mouseY <= this.getyPosition() + this.getHeight());
        if (mouseOverX && mouseOverY) {
            //  if (Mouse.isButtonDown(0)) {
            if (!this.dragging) {
                this.lastX = x - mouseX;
                this.lastY = y - mouseY;
                this.dragging = true;
            }
        }
    }

    private void draggingFix(int mouseX, int mouseY) {
        if (this.dragging) {
            this.x = mouseX + this.lastX;
            this.y = mouseY + this.lastY;
            // if (!Mouse.isButtonDown(0)) this.dragging = false;
        }
    }
}
