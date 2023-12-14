package com.Nxer.TwistSpaceTechnology.command;

import java.util.Map;
import java.util.Arrays;
import java.util.ArrayList;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import com.Nxer.TwistSpaceTechnology.combat.PlayerExtendedProperties;
import com.Nxer.TwistSpaceTechnology.combat.StatsDefination;

public class CombatRework_Command extends CommandBase {

    @Override
    public String getCommandName() {
        return "combatstats";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Twist Space Technology Mod Commands";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length < 1) {
            Map<String, Integer> Stats = PlayerExtendedProperties.getStats(getCommandSenderAsPlayer(sender));
            for (String statName : Stats.keySet())
                sender.addChatMessage(new ChatComponentText(statName + ':' + Stats.get(statName)));
            return;
        }

        switch (args[0]) {
            case "help": {
                TST_CommandMethods.INSTANCE.printHelp(sender);
                break;
            }
            case "set": {
                if (args.length != 3 || !args[2].matches("[+-]?\\d+") || !StatsDefination.AllStats.contains(args[1])) {
                    TST_CommandMethods.INSTANCE.printHelp(sender);
                    break;
                }
                PlayerExtendedProperties
                    .setPlayerStat(getCommandSenderAsPlayer(sender), args[1], Integer.parseInt(args[2]));
                break;
            }
            default: {
                TST_CommandMethods.INSTANCE.printHelp(sender);
                break;
            }
        }
    }
}
