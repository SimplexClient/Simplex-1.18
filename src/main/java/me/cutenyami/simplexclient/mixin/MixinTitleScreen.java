package me.cutenyami.simplexclient.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.sun.jna.platform.win32.Sspi;
import me.cutenyami.simplexclient.utils.GLRectUtils;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.awt.*;

@Mixin(TitleScreen.class)
public abstract class MixinTitleScreen extends Screen {

    @Shadow
    private long backgroundFadeStart;

    @Shadow
    @Final
    private boolean doBackgroundFade;

    @Shadow
    @Final
    private RotatingCubeMapRenderer backgroundRenderer;

    @Shadow
    @Final
    private static Identifier PANORAMA_OVERLAY;

    @Shadow
    @Final
    private static Identifier MINECRAFT_TITLE_TEXTURE;

    @Shadow
    @Final
    private boolean isMinceraft;

    @Shadow
    @Final
    private static Identifier EDITION_TITLE_TEXTURE;

    @Shadow
    @Nullable
    private String splashText;

    @Shadow
    protected abstract boolean areRealmsNotificationsEnabled();

    @Shadow
    private Screen realmsNotificationGui;

    protected MixinTitleScreen() {
        super(new TranslatableText("narrator.screen.title"));
    }

    /**
     * @author SimplexMC
     */
    @Overwrite
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (this.backgroundFadeStart == 0L && this.doBackgroundFade) {
            this.backgroundFadeStart = Util.getMeasuringTimeMs();
        }

        float f = this.doBackgroundFade ? (float) (Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0F : 1.0F;
        this.backgroundRenderer.render(delta, MathHelper.clamp(f, 0.0F, 1.0F));
        int j = this.width / 2 - 137;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, PANORAMA_OVERLAY);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.doBackgroundFade ? (float) MathHelper.ceil(MathHelper.clamp(f, 0.0F, 1.0F)) : 1.0F);
        drawTexture(matrices, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
        float g = this.doBackgroundFade ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
        int l = MathHelper.ceil(g * 255.0F) << 24;
        if ((l & -67108864) != 0) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, MINECRAFT_TITLE_TEXTURE);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, g);
            if (this.isMinceraft) {
                this.drawWithOutline(j, 30, (x, y) -> {
                    this.drawTexture(matrices, x, y, 0, 0, 99, 44);
                    this.drawTexture(matrices, x + 99, y, 129, 0, 27, 44);
                    this.drawTexture(matrices, x + 99 + 26, y, 126, 0, 3, 44);
                    this.drawTexture(matrices, x + 99 + 26 + 3, y, 99, 0, 26, 44);
                    this.drawTexture(matrices, x + 155, y, 0, 45, 155, 44);
                });
            } else {
                this.drawWithOutline(j, 30, (x, y) -> {
                    this.drawTexture(matrices, x, y, 0, 0, 155, 44);
                    this.drawTexture(matrices, x + 155, y, 0, 45, 155, 44);
                });
            }

            RenderSystem.setShaderTexture(0, EDITION_TITLE_TEXTURE);
            drawTexture(matrices, j + 88, 67, 0.0F, 0.0F, 98, 14, 128, 16);

            if (this.splashText != null) {
                matrices.push();
                matrices.translate((float) this.width / 2 + 90, 70.0D, 0.0D);
                matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(-20.0F));
                float h = 1.8F - MathHelper.abs(MathHelper.sin((float) (Util.getMeasuringTimeMs() % 1000L) / 1000.0F * 6.2831855F) * 0.1F);
                h = h * 100.0F / (float) (this.textRenderer.getWidth(this.splashText) + 32);
                matrices.scale(h, h, h);
                drawCenteredText(matrices, this.textRenderer, this.splashText, 0, -8, 16776960 | l);
                matrices.pop();
            }

            String string = "Minecraft " + SharedConstants.getGameVersion().getName();
            assert this.client != null;
            if (this.client.isDemo()) {
                string = string + " Demo";
            } else {
                string = string + ("release".equalsIgnoreCase(this.client.getVersionType()) ? "" : "/SimplexClient");
            }

            if (MinecraftClient.getModStatus().isModded()) {
                string = string + I18n.translate("menu.modded");
            }

            drawStringWithShadow(matrices, this.textRenderer, string, 2, this.height - 10, 16777215 | l);

            for (Element element : this.children()) {
                if (element instanceof ClickableWidget) {
                    ((ClickableWidget) element).setAlpha(g);
                }
            }

            super.render(matrices, mouseX, mouseY, delta);
            if (this.areRealmsNotificationsEnabled() && g >= 1.0F) {
                this.realmsNotificationGui.render(matrices, mouseX, mouseY, delta);
            }
        }
    }
}
