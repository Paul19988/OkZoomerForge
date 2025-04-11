package com.matyrobbrt.okzoomer.mixin;

import com.matyrobbrt.okzoomer.ZoomKeyBinds;
import com.matyrobbrt.okzoomer.utils.ZoomUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MouseHandleMixin {

    @Inject(method = "turnPlayer", at = @At("HEAD"))
    private void turnPlayer(CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();
        if (ZoomKeyBinds.ZOOM_KEY.isDown() || ZoomUtils.CINEMATIC_CAMERA) {
            client.options.smoothCamera = true;
        } else {
            client.options.smoothCamera = !client.options.smoothCamera;
        }
    }
}