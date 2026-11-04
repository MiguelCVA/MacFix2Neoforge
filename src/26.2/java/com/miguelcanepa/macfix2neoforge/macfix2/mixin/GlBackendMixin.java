package com.miguelcanepa.macfix2neoforge.macfix2.mixin;

import com.mojang.blaze3d.opengl.GlBackend;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GlBackend.class)
public abstract class GlBackendMixin {

    @Inject(
            method = "setWindowHints",
            at = @At("HEAD")
    )
    private void macfix2$disableRetinaFramebuffer(
            CallbackInfo ci
    ) {
        GLFW.glfwWindowHint(
                GLFW.GLFW_COCOA_RETINA_FRAMEBUFFER,
                GLFW.GLFW_FALSE
        );
    }
}