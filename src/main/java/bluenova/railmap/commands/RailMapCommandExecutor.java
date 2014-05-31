/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluenova.railmap.commands;

import bluenova.railmap.RailMap;
import bluenova.railmap.core.Rails;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author BoneBoy
 */
public class RailMapCommandExecutor implements CommandExecutor {

    private RailMap plugin;
    private List<Rails> rails;

    public RailMapCommandExecutor(RailMap plugin, List<Rails> rails) {
        this.plugin = plugin;
        this.rails = rails;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player sent = (Player) sender;

            boolean isCommand = (command.getName().equalsIgnoreCase("rm") || command.getName().equalsIgnoreCase("railmap"));

            if (isCommand) {
                if (args.length == 0 &&/*Util.hasPermission(sent, "fairytail.general")*/ true) {
                    sent.sendMessage(ChatColor.YELLOW + "/rm start <railname>");
                    sent.sendMessage(ChatColor.YELLOW + "/rm stop");
                    sent.sendMessage(ChatColor.YELLOW + "/rm list");
                    return true;

                } else if (args[0].equalsIgnoreCase("start") && args.length == 2 && /*Util.hasPermission(sent, "fairytail.general")*/ true) {
                    if (!RailMap.recordedPlayer.contains(sent)) {
                        sent.sendMessage(ChatColor.GREEN + "The record of the rail is starting!");
                        this.rails.add(new Rails());
                        bluenova.railmap.core.System.railIndex = this.rails.size() - 1;
                        this.rails.get(bluenova.railmap.core.System.railIndex).setName(args[1]);
                        bluenova.railmap.core.System.record = true;
                        RailMap.recordedPlayer.add(sent);
                    } else {
                        sent.sendMessage(ChatColor.RED + "An other Player is recording!");
                    }
                    return true;

                } else if (args[0].equalsIgnoreCase("stop") && args.length == 1 && /*Util.hasPermission(sent, "fairytail.general")*/ true) {

                    if (RailMap.recordedPlayer.contains(sent)) {
                        sent.sendMessage(ChatColor.GREEN + "The record of the rail has stopped!");
                        bluenova.railmap.core.System.record = false;

                        if (rails.get(bluenova.railmap.core.System.railIndex).getPoints().isEmpty()) {
                            rails.remove(bluenova.railmap.core.System.railIndex);
                            bluenova.railmap.core.System.railIndex = bluenova.railmap.core.System.railIndex - 1;
                        }
                        RailMap.recordedPlayer.remove(sent);
                    } else {
                        sent.sendMessage(ChatColor.RED + "You are not Recording!");
                    }
                    return true;
                } else if (args[0].equalsIgnoreCase("rerender") && args.length == 1 && /*Util.hasPermission(sent, "fairytail.general")*/ true) {
                    RailMap.dynhandler.run();
                    sent.sendMessage(ChatColor.GREEN + "Rail Rerendered!");
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            sender.sendMessage("Don't run this Command from Console!");
            return true;
        }
    }
}
