package bluenova.railmap;

import bluenova.railmap.commands.RailMapCommandExecutor;
import bluenova.railmap.core.Rails;
import bluenova.railmap.dynmap.DynmapHandler;
import bluenova.railmap.event.PlayerEvents;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.dynmap.DynmapAPI;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;


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
     * The Permision Manager
     */
    public static PermissionManager Permissions;

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
    
    /**
    *The Recorded Player to RailIndex
    */
    public static HashMap<Player, Integer> recordedPlayer = new HashMap<Player, Integer>();
    
    @Override
    public void onEnable() { 
        RailMap.plugin = this;
        RailMap.pm = getServer().getPluginManager();
        RailMap.server = getServer();
        
        RailMap.dynmap = pm.getPlugin("dynmap");
        if(RailMap.dynmap == null) {
            System.out.println("[RailMap] Dynmap not Found!  Disableing Plugin!");
            this.setEnabled(false);
            return;
        }  
        RailMap.dynapi = (DynmapAPI)RailMap.dynmap;
               
        myExecutor = new RailMapCommandExecutor(this, rails);
	getCommand("rm").setExecutor(myExecutor);
        getCommand("railmap").setExecutor(myExecutor);
        
        RailMap.pm.registerEvents(new PlayerEvents(rails), this);
        
        if(!RailMap.dynmap.isEnabled())
            pm.enablePlugin(RailMap.dynmap);
        
        //Setting up PermissionEx if plugin is installed
        this.setupPermissions();   
        
        RailMap.dynhandler = new DynmapHandler(rails);
        RailMap.dynhandler.run(); 
    }
    
    
    
    private void setupPermissions() {
        
        Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
        
        if(Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")){
            RailMap.Permissions = PermissionsEx.getPermissionManager();
            System.out.println("[RailMap] Permissions Enabled");
        } else {
            System.out.println("[RailMap] Permission System not found! Using native Permissions or OP!");
        }
    }
}
