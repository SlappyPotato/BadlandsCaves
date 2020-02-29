package me.fullpotato.badlandscaves.badlandscaves.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PowersTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("powers")) {
            if (sender.isOp()) {
                List<String> list = new ArrayList<>();

                if (args.length == 1) {
                    if (args[0].startsWith("g")) list.add("get");
                    else if (args[0].startsWith("s")) list.add("set");
                    else {
                        list.add("get");
                        list.add("set");
                    }
                }

                else if (args.length == 2) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (args[1].isEmpty() || player.getName().toUpperCase().startsWith(args[1].toUpperCase())) {
                            list.add(player.getName());
                        }
                    }
                }

                else if (args.length == 3) {
                    String[] strings = {
                            "supernatural",
                            "displace",
                            "enhanced_eyes",
                            "withdraw",
                            "possession",
                            "endurance",
                            "agility",
                    };

                    for (String str : strings) {
                        if (str.toUpperCase().startsWith(args[2].toUpperCase())) {
                            list.add(str);
                        }
                    }
                }
                return list;
            }
        }
        return null;
    }
}
