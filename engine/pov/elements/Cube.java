package engine.pov.elements;

import engine.raytracing.Ray;
import engine.raytracing.Vector;

public class Cube extends Actor{

    private double width;
    private double height;
    private double depth;

    public Cube(Point center, Color color, double width, double height, double depth, float ambient, float broadcast, float specular, float bright){
        super(center, color, ambient, broadcast, specular, bright);
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public double volume() {
        return width * height * depth;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDepth(){
        return depth;
    }

    public void setDimension(double l, double h, double p) {
        this.width = l;
        this.height = h;
        this.depth = p;
    }

    public String toString(){
        return "Width:"+this.width+" Height"+this.height+" Depth"+this.depth;
    }

    public void isHit(Ray ray){

    }

    public boolean isHit(Ray ray, Vector directorVector){
        return true;
    }
}