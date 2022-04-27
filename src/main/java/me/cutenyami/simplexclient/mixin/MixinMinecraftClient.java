package me.cutenyami.simplexclient.mixin;

import me.cutenyami.simplexclient.SimplexClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    /**
     * @author SimplexMC
     */
    @Overwrite
    private String getWindowTitle() {
        return "SimplexClient v1.0.0 / 1.18";
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void startClient(RunArgs args, CallbackInfo ci) {
        SimplexClient.getInstance().start();
    }
}
