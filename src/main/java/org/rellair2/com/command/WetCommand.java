package org.rellair2.com.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.server.command.ForgeCommand;

public class WetCommand implements Command<CommandSourceStack> {
    public static void register(CommandDispatcher<CommandSourceStack> commandDispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("temperatures").requires(commandSourceStack -> commandSourceStack.hasPermission(0))
                .executes(new WetCommand()).build().createBuilder();
        commandDispatcher.register(builder);
    }
    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        System.err.println("val = " + context.getArgument("wet", int.class));
        //Minecraft.getInstance().player.sendSystemMessage(Component.literal("World temperature = "));
        return 0;
    }
}
