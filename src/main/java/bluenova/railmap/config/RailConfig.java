/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluenova.railmap.config;

import bluenova.railmap.core.Rails;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author BoneBoy
 */
public class RailConfig {
    
    private Rails rail;
    private String fileString;
    private File file;
    private YamlConfiguration config;
    
    
    public RailConfig(Rails rail) {
        this.rail = rail;
        this.fileString = "plugins" + File.separator + "RailMap" + File.separator + "rails" + File.separator + this.rail.getName() + ".yml";
        this.file = new File(this.fileString);
        this.config = new YamlConfiguration();
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            this.createConfig();
        } else {
            try {
                this.config.load(this.file);
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(MainConfig.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                //Logger.getLogger(MainConfig.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidConfigurationException ex) {
                //Logger.getLogger(MainConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void createConfig() {
        this.config.set("name", this.rail.getName());
        this.config.set("typ", this.rail.getTyp());
        this.config.set("driveTime", this.rail.getDriveTime());
        this.config.set("points", this.rail.getPoints().toString());
        saveConfig();
    }

    private synchronized void saveConfig() {
        try {
            this.config.save(this.file);
        } catch (IOException ex) {
            Logger.getLogger(RailConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
