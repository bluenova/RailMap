/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluenova.railmap.event;

import bluenova.railmap.RailMap;
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

        if (RailMap.recordedPlayer.containsKey(evt.getPlayer())) {
            int index = RailMap.recordedPlayer.get(evt.getPlayer());
            if (evt.getPlayer().isInsideVehicle()) {

                if (rails.get(index).getPoints().isEmpty()) {

                    Date startTime = new Date();
                    rails.get(index).setStartTime(startTime.getTime());

                    rails.get(index).addPoint(new Point((int) loc.getX(), (int) loc.getZ(), loc.getWorld().getName()));
                }

                if (!rails.get(index).getPoints().isEmpty() && isCurve(rails.get(index).getPoints(), loc)) {
                    rails.get(index).addPoint(new Point((int) loc.getX(), (int) loc.getZ(), loc.getWorld().getName()));
                }
            } else {

                if (!rails.get(index).getPoints().isEmpty()) {

                    evt.getPlayer().sendMessage(ChatColor.GREEN + "The record of the rail has stopped!");

                    rails.get(index).addPoint(new Point((int) loc.getX(), (int) loc.getZ(), loc.getWorld().getName()));

                    Date endTime = new Date();
                    rails.get(index).setEndTime(endTime.getTime());

                    rails.get(index).setDriveTime(rails.get(index).getEndTime() - rails.get(index).getStartTime());

                    rc = new RailConfig(rails.get(index));
                    RailMap.recordedPlayer.remove(evt.getPlayer());
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
