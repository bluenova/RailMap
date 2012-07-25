/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluenova.railmap.util;

import bluenova.railmap.RailMap;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author BoneBoy
 */
public class Util {
    
    /**
     * Checks if Player Has Permission
     * @param pl Player to Check
     * @param perm Permission to Check
     * @return Has Permission or Not
     */
    public static boolean hasPermission(Player pl, String perm) {
        if (RailMap.Permissions != null) {
            if (RailMap.Permissions.has(pl, perm)) {
                return true;
            } else {
                pl.sendMessage(ChatColor.RED + "No Permission!");
                return false;
            }
        } else {
            if (pl.hasPermission(perm) || pl.isOp()) {
                return true;
            } else {
                pl.sendMessage(ChatColor.RED + "No Permission!");
                return false;
            }
        }
    }
}
