package me.cutenyami.simplexclient.utils;

import com.mojang.blaze3d.platform.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * Doesn't work with LWJGL 3 :/
 */
public class GLRectUtils {
    /**
     * Doesn't work with LWJGL 3 :/
     */
    public static void drawRoundedRect(final float nameInt1, final float nameInt2, final float nameInt3, final float nameInt4, final float radius, final int color) {
        final float f1 = (color >> 24 & 0xFF) / 255.0f;
        final float f2 = (color >> 16 & 0xFF) / 255.0f;
        final float f3 = (color >> 8 & 0xFF) / 255.0f;
        final float f4 = (color & 0xFF) / 255.0f;
        GlStateManager._clearColor(f2, f3, f4, f1);
        drawRoundedRect(nameInt1, nameInt2, nameInt3, nameInt4, radius);
    }

    /**
     * Doesn't work with LWJGL 3 :/
     */
    private static void drawRoundedRect(final float nameFloat1, final float nameFloat2, final float nameFloat3, final float nameFloat4, final float nameFloat5) {
        final int i = 18;
        final float f1 = 90.0f / i;
        GlStateManager._disableTexture();
        GlStateManager._enableBlend();
        GlStateManager._disableCull();
        GlStateManager._blendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glBegin(5);
        GL11.glVertex2f(nameFloat1 + nameFloat5, nameFloat2);
        GL11.glVertex2f(nameFloat1 + nameFloat5, nameFloat4);
        GL11.glVertex2f(nameFloat3 - nameFloat5, nameFloat2);
        GL11.glVertex2f(nameFloat3 - nameFloat5, nameFloat4);
        GL11.glEnd();
        GL11.glBegin(5);
        GL11.glVertex2f(nameFloat1, nameFloat2 + nameFloat5);
        GL11.glVertex2f(nameFloat1 + nameFloat5, nameFloat2 + nameFloat5);
        GL11.glVertex2f(nameFloat1, nameFloat4 - nameFloat5);
        GL11.glVertex2f(nameFloat1 + nameFloat5, nameFloat4 - nameFloat5);
        GL11.glEnd();
        GL11.glBegin(5);
        GL11.glVertex2f(nameFloat3, nameFloat2 + nameFloat5);
        GL11.glVertex2f(nameFloat3 - nameFloat5, nameFloat2 + nameFloat5);
        GL11.glVertex2f(nameFloat3, nameFloat4 - nameFloat5);
        GL11.glVertex2f(nameFloat3 - nameFloat5, nameFloat4 - nameFloat5);
        GL11.glEnd();
        GL11.glBegin(6);
        float f2 = nameFloat3 - nameFloat5;
        float f3 = nameFloat2 + nameFloat5;
        GL11.glVertex2f(f2, f3);
        for (int j = 0; j <= i; ++j) {
            final float f4 = j * f1;
            GL11.glVertex2f((float) (f2 + nameFloat5 * Math.cos(Math.toRadians(f4))), (float) (f3 - nameFloat5 * Math.sin(Math.toRadians(f4))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        f2 = nameFloat1 + nameFloat5;
        f3 = nameFloat2 + nameFloat5;
        GL11.glVertex2f(f2, f3);
        for (int j = 0; j <= i; ++j) {
            final float f4 = j * f1;
            GL11.glVertex2f((float) (f2 - nameFloat5 * Math.cos(Math.toRadians(f4))), (float) (f3 - nameFloat5 * Math.sin(Math.toRadians(f4))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        f2 = nameFloat1 + nameFloat5;
        f3 = nameFloat4 - nameFloat5;
        GL11.glVertex2f(f2, f3);
        for (int j = 0; j <= i; ++j) {
            final float f4 = j * f1;
            GL11.glVertex2f((float) (f2 - nameFloat5 * Math.cos(Math.toRadians(f4))), (float) (f3 + nameFloat5 * Math.sin(Math.toRadians(f4))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        f2 = nameFloat3 - nameFloat5;
        f3 = nameFloat4 - nameFloat5;
        GL11.glVertex2f(f2, f3);
        for (int j = 0; j <= i; ++j) {
            final float f4 = j * f1;
            GL11.glVertex2f((float) (f2 + nameFloat5 * Math.cos(Math.toRadians(f4))), (float) (f3 + nameFloat5 * Math.sin(Math.toRadians(f4))));
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GlStateManager._enableCull();
        GlStateManager._disableBlend();
        GlStateManager._enableTexture();
    }
}
