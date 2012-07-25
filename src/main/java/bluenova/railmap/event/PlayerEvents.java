/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluenova.railmap.event;

import bluenova.railmap.config.RailConfig;
import bluenova.railmap.core.Point;
import bluenova.railmap.core.Rails;
import java.util.Date;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 *
 * @author BoneBoy
 */
public class PlayerEvents implements Listener {
    
    private List<Rails> rails;
    private RailConfig rc;
    
    
    public PlayerEvents(List<Rails> rails) {
        this.rails = rails;
    }
    
    
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent evt) {
        
        Location loc = evt.getPlayer().getLocation();
        
        if (bluenova.railmap.core.System.record == true) {
            if (evt.getPlayer().isInsideVehicle()) {
                
                if (rails.get(bluenova.railmap.core.System.railIndex).getPoints().isEmpty()) {
                    
                    Date startTime = new Date();               
                    rails.get(bluenova.railmap.core.System.railIndex).setStartTime(startTime.getTime());
                    
                    rails.get(bluenova.railmap.core.System.railIndex).addPoint(new Point((int) loc.getX(), (int) loc.getZ(), loc.getWorld().getName()));
                }
                
                if (!rails.get(bluenova.railmap.core.System.railIndex).getPoints().isEmpty() && isCurve(rails.get(bluenova.railmap.core.System.railIndex).getPoints(), loc)) {
                    rails.get(bluenova.railmap.core.System.railIndex).addPoint(new Point((int) loc.getX(), (int) loc.getZ(), loc.getWorld().getName()));
                }
            } else {
                
                if (!rails.get(bluenova.railmap.core.System.railIndex).getPoints().isEmpty()) {
                    
                    evt.getPlayer().sendMessage(ChatColor.GREEN + "The record of the rail has stopped!");
                    bluenova.railmap.core.System.record = false;
                    
                    rails.get(bluenova.railmap.core.System.railIndex).addPoint(new Point((int) loc.getX(), (int) loc.getZ(), loc.getWorld().getName()));
                    
                    Date endTime = new Date();               
                    rails.get(bluenova.railmap.core.System.railIndex).setEndTime(endTime.getTime());
                    
                    rails.get(bluenova.railmap.core.System.railIndex).setDriveTime(rails.get(bluenova.railmap.core.System.railIndex).getEndTime() - rails.get(bluenova.railmap.core.System.railIndex).getStartTime());
                
                    rc = new RailConfig(rails.get(bluenova.railmap.core.System.railIndex));
                }
            }          
        }
    }
    
    
    private boolean isCurve(List<Point> points, Location loc) {
        
        Point lastPoint = points.get(points.size() - 1);
        
        if (lastPoint.getX() != (int) loc.getX() && lastPoint.getZ() != (int) loc.getZ()) {
            return true;
        } else {
            return false;
        }
    }
}
