package me.cutenyami.simplexclient.elements;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class ClientButton extends ButtonWidget {
    private final int x, y;
    public ClientButton(int x, int y, int width, int height, Text message, PressAction onPress) {
        super(x, y, width, height, message, onPress);
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
