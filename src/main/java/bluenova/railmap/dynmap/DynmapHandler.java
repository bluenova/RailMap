/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluenova.railmap.dynmap;

import bluenova.railmap.RailMap;
import bluenova.railmap.core.Point;
import bluenova.railmap.core.Rails;
import java.util.List;
import org.dynmap.markers.MarkerAPI;
import org.dynmap.markers.MarkerSet;

/**
 *
 * @author Anwender
 */
public class DynmapHandler {

    private MarkerAPI markerAPI;
    private List<Rails> rails;

    public DynmapHandler(List<Rails> rails) {
        this.rails = rails;
    }

    public void run() {
        this.markerAPI = RailMap.dynapi.getMarkerAPI();
        MarkerSet oldSet = this.markerAPI.getMarkerSet("railmap.dynmap");
        if(oldSet != null) {
            oldSet.deleteMarkerSet();
        }
        
        MarkerSet set = this.markerAPI.createMarkerSet("railmap.dynmap", "RailMap", null, false);
        for (Rails rail : this.rails) {
            List<Point> points = rail.getPoints();
            double[] x = new double[points.size()];
            double[] y = new double[points.size()];
            double[] z = new double[points.size()];
            int i = 0;
            String world = "";
            for(Point p : points) {
               x[i] = p.getX();
               y[i] = 64;
               z[i] = p.getZ();
               world = p.getWorld();
               i++;
            }
            set.createPolyLineMarker("railmap.dynmap." + rail.getName(), rail.getName(), true, world, x, y, z, false);
        }
    }
}
