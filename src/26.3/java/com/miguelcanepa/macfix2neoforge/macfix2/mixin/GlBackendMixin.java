package com.miguelcanepa.macfix2neoforge.macfix2.mixin;

import com.mojang.renderpearl.backend.opengl.GlBackend;
import org.lwjgl.sdl.SDLVideo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GlBackend.class)
public abstract class GlBackendMixin {

    @ModifyArg(
            method = "createWindow",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/sdl/SDLVideo;SDL_CreateWindow(Ljava/lang/CharSequence;IIJ)J"
            ),
            index = 3
    )
    private long macfix2$disableRetinaFramebuffer(long flags) {
        return flags & ~SDLVideo.SDL_WINDOW_HIGH_PIXEL_DENSITY;
    }
}