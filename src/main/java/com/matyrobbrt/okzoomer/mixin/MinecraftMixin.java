package com.matyrobbrt.okzoomer.mixin;

import com.matyrobbrt.okzoomer.ZoomKeyBinds;
import com.matyrobbrt.okzoomer.utils.ZoomUtils;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    private int counter = 0;
    private boolean hasPressedRecently = false;

    @Inject(method = "handleKeybinds", at = @At("RETURN"))
    private void handleKeybinds(CallbackInfo ci) {
        if (hasPressedRecently) {
            counter++;
            if (counter >= 20) {
                counter = 0;
                hasPressedRecently = false;
            }
        }

        if (!hasPressedRecently) {
            if (ZoomKeyBinds.CINEMATIC_KEY.isDown() && !ZoomKeyBinds.ZOOM_KEY.isDown()) {
                ZoomUtils.CINEMATIC_CAMERA = !ZoomUtils.CINEMATIC_CAMERA;
                hasPressedRecently = true;
            }
        }
    }
}
