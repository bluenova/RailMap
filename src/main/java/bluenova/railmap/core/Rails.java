/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluenova.railmap.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BoneBoy
 */
public class Rails {
    
    /**
     * Attribute
     */
    private String name;
    private int typ;
    
    private long startTime;
    private long endTime;
    private long driveTime;
    
    private List<Point> points = new ArrayList<Point>();
    
    
    
    /**
     * Konstruktor
     */
    public Rails() {
        
    }
    
    
    public void setName(String name) {
        this.name = name;
    } 
    
    public String getName() {
        return this.name;
    }
    
    public void setTyp(int typ) {
        this.typ = typ;
    }
    
    public int getTyp() {
        return this.typ;
    }
    
    
    
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    
    public long getStartTime() {
        return this.startTime;
    }
    
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    
    public long getEndTime() {
        return this.endTime;
    }
    
    public void setDriveTime(long driveTime) {
        this.driveTime = driveTime;
    }
    
    public long getDriveTime() {
        return this.driveTime;
    }
    
    
    
    public void addPoint(Point point) {
        this.points.add(point);
    }
    
    public Point getPoint(int index) {
        return points.get(index);
    }
    
    public List<Point> getPoints() {
        return this.points;
    }
}
