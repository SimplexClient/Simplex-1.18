package me.cutenyami.simplexclient.mixin;

import me.cutenyami.simplexclient.ui.ModCreator;
import me.cutenyami.simplexclient.ui.ModManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinIngameHud {

    @Inject(method = "render", at = @At("TAIL"))
    public void renderMods(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        for (ModCreator mod : ModManager.getMods()) {
            if (mod.isEnabled()) {
                mod.render(matrices);
            }
        }
        if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), InputUtil.GLFW_KEY_0)) {
            System.out.println("TEST");
        }
    }
}
