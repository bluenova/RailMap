/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluenova.railmap.core;

/**
 *
 * @author BoneBoy
 */
public class Point {

    private int x;
    private int z;
    private String world;
   
    
    
    public Point(int x, int z, String world) {
        this.x = x;
        this.z = z;
        this.world = world;
    }   
    
    public int getX() {
        return this.x;
    }

    public int getZ() {
        return this.z;
    }
    
    public String getWorld() {
        return this.world;
    } 
    
    
    @Override
    public String toString() {
        return "(" + x + " | 65 | " + z + " | " + world + ")";
    }
}
