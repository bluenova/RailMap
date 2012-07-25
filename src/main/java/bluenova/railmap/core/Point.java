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
   
    
    
    public Point(int x, int z) {
        this.x = x;
        this.z = z;
    }   
    
    public int getX() {
        return this.x;
    }

    public int getZ() {
        return this.z;
    } 
    
    
    @Override
    public String toString() {
        return "(" + x + " | 65 | " + z + ")";
    }
}
