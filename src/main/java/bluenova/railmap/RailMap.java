package bluenova.railmap;

import bluenova.railmap.commands.RailMapCommandExecutor;
import bluenova.railmap.core.Rails;
import bluenova.railmap.event.PlayerEvents;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import org.dynmap.DynmapAPI;
import org.dynmap.markers.AreaMarker;
import org.dynmap.markers.Marker;
import org.dynmap.markers.MarkerAPI;
import org.dynmap.markers.MarkerIcon;
import org.dynmap.markers.MarkerSet;
import org.dynmap.markers.PolyLineMarker;


/**
 * RailMap for Bukkit
 * 
 * @author BoneBoy
 */
public class RailMap extends JavaPlugin {
    
    public static Plugin plugin;
    
    /**
     * The Plugin Manager of the Bukkit Server
     */
    public static PluginManager pm;
    
    /**
     * The Bukkit Server
     */
    public static Server server;
    
    /**
     * Permission Handler for Permission Plugins
     */
    public static PermissionHandler Permissions;
    
    /**
     * Listener Object For commands
     */
    private RailMapCommandExecutor myExecutor;
    
    /**
     * System
     */
    public static System system;
    
    /**
     * Rails
     */
    private List<Rails> rails = new ArrayList<Rails>();
    
    
    @Override
    public void onEnable() {    
        
        RailMap.plugin = this;
        RailMap.pm = getServer().getPluginManager();
        RailMap.server = getServer();
        
        myExecutor = new RailMapCommandExecutor(this, rails);
	getCommand("rm").setExecutor(myExecutor);
        getCommand("railmap").setExecutor(myExecutor);
        
        RailMap.pm.registerEvents(new PlayerEvents(rails), this);
        
        double[] x = {0, 100};
        double[] y = {65, 65};
        double[] z = {0, 0};
        
        
    }
    
    
    
    private void setupPermissions() {
        
        Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
        
        if (RailMap.Permissions == null) {
            
            if (test != null) {
                RailMap.Permissions = ((Permissions) test).getHandler();
                System.out.println("[FairyTailCraft] Permission system detected!");
            } else {
                System.out.println("[FairyTailCraft] Permission system not detected, defaulting to OP");
            }
        }
    }
}
