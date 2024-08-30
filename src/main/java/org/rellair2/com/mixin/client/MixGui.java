package org.rellair2.com.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import org.rellair2.com.api.gui.GuiInfo;
import org.rellair2.com.gui.GuiInvoker;
import org.rellair2.com.gui.*;
import org.rellair2.com.minecraft.HeartType;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(Gui.class)
public abstract class MixGui {

    @Shadow
    @Nullable
    protected abstract Player getCameraPlayer();

    @Shadow
    @Final
    private RandomSource random;

    @Unique
    private final GuiInvoker guiInvoker = new GuiInvoker()
            .invoke(GuiRenderTemperature::new)
            .invoke(GuiDebugRender::new);

    @Inject(method = "renderPlayerHealth", at = @At("TAIL"))
    private void onRenderPlayerHealth(GuiGraphics guiGraphics, CallbackInfo ci) {
        Player player = this.getCameraPlayer();
        if (player != null) {
            RenderSystem.enableBlend();
            guiInvoker.tickOn(new GuiInfo((Gui) (Object) this, guiGraphics, player, guiGraphics.guiWidth(), guiGraphics.guiHeight()));
            RenderSystem.disableBlend();
        }
    }

    /**
     * @author Devlink
     * @reason My render hearts
     */
    @Overwrite
    private void renderHearts(GuiGraphics p_282497_, Player p_168690_, int p_168691_, int p_168692_, int p_168693_, int p_168694_, float p_168695_, int p_168696_, int p_168697_, int p_168698_, boolean p_168699_) {
        HeartType gui$hearttype = HeartType.forPlayer(p_168690_);
        boolean flag = p_168690_.level().getLevelData().isHardcore();
        int i = Mth.ceil((double) p_168695_ / 2.0);
        int j = Mth.ceil((double) p_168698_ / 2.0);
        int k = i * 2;
        for (int l = i + j - 1; l >= 0; l--) {
            int i1 = l / 10;
            int j1 = l % 10;
            int k1 = p_168691_ + j1 * 8;
            int l1 = p_168692_ - i1 * p_168693_;
            if (p_168696_ + p_168698_ <= 4) {
                l1 += this.random.nextInt(2);
            }

            if (l < i && l == p_168694_) {
                l1 -= 2;
            }

            this.renderHeart(p_282497_, HeartType.CONTAINER, k1, l1, flag, p_168699_, false);
            int i2 = l * 2;
            boolean flag1 = l >= i;
            if (flag1) {
                int j2 = i2 - k;
                if (j2 < p_168698_) {
                    boolean flag2 = j2 + 1 == p_168698_;
                    this.renderHeart(p_282497_, gui$hearttype == HeartType.WITHERED ? gui$hearttype : HeartType.ABSORBING, k1, l1, flag, false, flag2);
                }
            }

            if (p_168699_ && i2 < p_168697_) {
                boolean flag3 = i2 + 1 == p_168697_;
                this.renderHeart(p_282497_, gui$hearttype, k1, l1, flag, true, flag3);
            }

            if (i2 < p_168696_) {
                boolean flag4 = i2 + 1 == p_168696_;
                this.renderHeart(p_282497_, gui$hearttype, k1, l1, flag, false, flag4);
            }
        }
    }
    @Unique
    private void renderHeart(GuiGraphics p_283024_, HeartType p_281393_, int p_283636_, int p_283279_, boolean p_283440_, boolean p_282496_, boolean p_301416_) {
        RenderSystem.enableBlend();
        p_283024_.blitSprite(p_281393_.getSprite(p_283440_, p_301416_, p_282496_), p_283636_, p_283279_, 9, 9);
        RenderSystem.disableBlend();
    }
}
