package me.cutenyami.simplexclient.utils;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
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

    public static void drawRoundedRectangle(MatrixStack matrices, int x1, int y1, int x2, int y2, int radius, int color) {
        drawRoundedRec(matrices.peek().getPositionMatrix(), x1, y1, x2, y2, radius, color);
    }

    private static void drawRoundedRec(Matrix4f matrix, float x1, float y1, float x2, float y2, int radius, int color) {

        int i;
        if (x1 < x2) {
            i = (int) x1;
            x1 = x2;
            x2 = i;
        }

        if (y1 < y2) {
            i = (int) y1;
            y1 = y2;
            y2 = i;
        }

        final int i1 = 18;
        final float f1 = 90.0f / i1;

        float f = (float)(color >> 24 & 255) / 255.0F;
        float g = (float)(color >> 16 & 255) / 255.0F;
        float h = (float)(color >> 8 & 255) / 255.0F;
        float j = (float)(color & 255) / 255.0F;
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

        bufferBuilder.vertex(matrix, (float)x1, (float)y2, 0.0F).color(g, h, j, f).next();
        bufferBuilder.vertex(matrix, (float)x2, (float)y2, 0.0F).color(g, h, j, f).next();
        bufferBuilder.vertex(matrix, (float)x2, (float)y1, 0.0F).color(g, h, j, f).next();
        bufferBuilder.vertex(matrix, (float)x1, (float)y1, 0.0F).color(g, h, j, f).next();


        bufferBuilder.end();
        BufferRenderer.draw(bufferBuilder);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public static void _drawRoundedRect(Matrix4f matrix, float x, float y, float x1, float y1, float radius, int color) {

        x *= 2.0D;
        y *= 2.0D;
        x1 *= 2.0D;
        y1 *= 2.0D;

        float f = (float)(color >> 24 & 255) / 255.0F;
        float g = (float)(color >> 16 & 255) / 255.0F;
        float h = (float)(color >> 8 & 255) / 255.0F;
        float j = (float)(color & 255) / 255.0F;

        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.disableCull();
        RenderSystem.defaultBlendFunc();
        RenderSystem.blendFunc(770, 771);
        RenderSystem.blendFuncSeparate(770, 771, 1, 0);
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

        int i;

        for (i = 0; i <= 90; i += 3) {
            bufferBuilder.vertex( x + radius + (float) Math.sin(i * Math.PI / 180.0D) * radius * -1.0F, y + radius + (float) Math.cos(i * Math.PI / 180.0D) * radius * -1.0F, 0.0F).color(g, h, j, f).next();
        }
        for (i = 90; i <= 180; i += 3) {
            bufferBuilder.vertex( x + radius + (float) Math.sin(i * Math.PI / 180.0D) * radius * -1.0F, y1 - radius + (float) Math.cos(i * Math.PI / 180.0D) * radius * -1.0F, 0.0F).color(g, h, j, f).next();
        }
        for (i = 0; i <= 90; i += 3) {
            bufferBuilder.vertex(x1 - radius + (float) Math.sin(i * Math.PI / 180.0D) * radius, y1 - radius + (float) Math.cos(i * Math.PI / 180.0D) * radius, 0.0F).color(g, h, j, f).next();
        }
        for (i = 90; i <= 180; i += 3) {
            bufferBuilder.vertex( x1 - radius + (float) Math.sin(i * Math.PI / 180.0D) * radius, y + radius + (float) Math.cos(i * Math.PI / 180.0D) * radius, 0.0F).color(g, h, j, f).next();
        }
        bufferBuilder.end();
        BufferRenderer.draw(bufferBuilder);
        RenderSystem.enableCull();
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public static void _drawRoundedRectangle(MatrixStack matrices, int x1, int y1, int x2, int y2, int radius, int color) {
        drawRoundedRec(matrices.peek().getPositionMatrix(), x1, y1, x2, y2, radius, color);
    }

    private static void drawRoundedRec(Matrix4f matrix, int x1, int y1, int x2, int y2, int radius, int color) {

        int i;
        if (x1 < x2) {
            i = x1;
            x1 = x2;
            x2 = i;
        }

        if (y1 < y2) {
            i = (int) y1;
            y1 = y2;
            y2 = i;
        }

        final int i1 = 18;
        final float f1 = 90.0f / i1;

        float f = (float)(color >> 24 & 255) / 255.0F;
        float g = (float)(color >> 16 & 255) / 255.0F;
        float h = (float)(color >> 8 & 255) / 255.0F;
        float j = (float)(color & 255) / 255.0F;
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

        bufferBuilder.vertex(matrix, (float)x1, (float)y2, 0.0F).color(g, h, j, f).next();
        bufferBuilder.vertex(matrix, (float)x2, (float)y2, 0.0F).color(g, h, j, f).next();
        bufferBuilder.vertex(matrix, (float)x2, (float)y1, 0.0F).color(g, h, j, f).next();
        bufferBuilder.vertex(matrix, (float)x1, (float)y1, 0.0F).color(g, h, j, f).next();


        bufferBuilder.end();
        BufferRenderer.draw(bufferBuilder);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }
}
