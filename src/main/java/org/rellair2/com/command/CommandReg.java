package org.rellair2.com.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CommandReg {
    @SubscribeEvent
    public static void OnRegisterCommandEvent(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> commandSourceStackCommandDispatcher = event.getDispatcher();
        WetCommand.register(commandSourceStackCommandDispatcher);
    }
}
