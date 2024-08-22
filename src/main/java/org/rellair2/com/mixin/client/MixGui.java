package org.rellair2.com.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import org.rellair2.com.gui.GuiInvoker;
import org.rellair2.com.gui.GuiRenderTemperature;
import org.rellair2.com.api.gui.GuiInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(Gui.class)
public abstract class MixGui {

    @Shadow @Nullable protected abstract Player getCameraPlayer();

    @Inject(method="renderPlayerHealth", at = @At("TAIL"))
    private void onRenderPlayerHealth(GuiGraphics guiGraphics, CallbackInfo ci) {
        Player player = this.getCameraPlayer();
        if (player != null) {
            //System.err.println("Render Player Health");
            RenderSystem.enableBlend();
            GuiInvoker.invoke(new GuiRenderTemperature(), new GuiInfo((Gui) (Object) this, guiGraphics,player, guiGraphics.guiWidth(), guiGraphics.guiHeight()));
            RenderSystem.disableBlend();
        }
    }
}
