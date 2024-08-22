package org.rellair2.com.gui;

import net.minecraft.resources.ResourceLocation;
import org.rellair2.com.Rellair2;
import org.rellair2.com.api.gui.IGuiRender;
import org.rellair2.com.temperature.manager.valuable.TemperatureDataValuable;
import org.rellair2.com.temperature.type.TypeTemperature;
import org.rellair2.com.temperature.manager.data.TemperatureDataManager;
import org.rellair2.com.api.gui.GuiInfo;
import org.rellair2.com.api.mixins.IRPlayer;

import java.util.Map;

public class GuiRenderTemperature implements IGuiRender {

    private static final Map<TypeTemperature.TempType, ResourceLocation> map_sprites = Map.of(
            TypeTemperature.TempType.ICY, ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/t0"),
            TypeTemperature.TempType.BITTER_COLD, ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/t1"),
            TypeTemperature.TempType.COLD, ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/t2"),
            TypeTemperature.TempType.CHILLY, ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/t3"),
            TypeTemperature.TempType.NORMAL, ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/t4"),
            TypeTemperature.TempType.LITTLE_WARM, ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/t5"),
            TypeTemperature.TempType.WARM, ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/t6"),
            TypeTemperature.TempType.VERY_WARM, ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/t7"),
            TypeTemperature.TempType.HOT, ResourceLocation.fromNamespaceAndPath(Rellair2.MODID, "hud/t8")
    );

    @Override
    public void tick(GuiInfo guiInfo) {
        IRPlayer irPlayer = (IRPlayer) guiInfo.player();
        if (guiInfo.player().getRandom().nextInt(100) == 0) {
            System.err.println("Rendering = " + irPlayer.getData(TemperatureDataManager.class).getData(TemperatureDataValuable.class).getValuable().getValue());
        }
        float temperature = irPlayer.getData(TemperatureDataManager.class).getData(TemperatureDataValuable.class).getValuable().getValue();
        guiInfo.graphics().blitSprite(map_sprites.get(TypeTemperature.TempType.findBy(temperature - 12)), ((guiInfo.width() / 2) - 8), (guiInfo.height() - 54), 16, 16);
    }
}
