package bluenova.railmap;

import bluenova.railmap.commands.RailMapCommandExecutor;
import bluenova.railmap.core.Rails;
import bluenova.railmap.dynmap.DynmapHandler;
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
    
    /**
     * The dynmap Plugin
     */
    public static Plugin dynmap;
    
    /**
     * The dynmap API
     */
    public static DynmapAPI dynapi;
    
     /**
     * The dynmap API Handler
     */
    public static DynmapHandler dynhandler;
    
    
    @Override
    public void onEnable() { 
        RailMap.dynmap = pm.getPlugin("dynmap");
        if(RailMap.dynmap == null) {
            System.out.println("Dynmap not Found!");
            this.setEnabled(false);
            return;
        }  
        RailMap.dynapi = (DynmapAPI)RailMap.dynmap;
        
        RailMap.plugin = this;
        RailMap.pm = getServer().getPluginManager();
        RailMap.server = getServer();
        
        myExecutor = new RailMapCommandExecutor(this, rails);
	getCommand("rm").setExecutor(myExecutor);
        getCommand("railmap").setExecutor(myExecutor);
        
        RailMap.pm.registerEvents(new PlayerEvents(rails), this);
        
        RailMap.dynhandler = new DynmapHandler(rails);
        RailMap.dynhandler.run();        
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
