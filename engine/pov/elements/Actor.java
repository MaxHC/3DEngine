package engine.pov.elements;

import engine.raytracing.Ray;
import engine.raytracing.Vector;

public abstract class Actor{

	public Point center;
	private Color color;
	private float ambient, broadcast, specular, bright;

	public Actor(Point center, Color color, float ambient, float broadcast, float specular, float bright){
		this.center = center;
		this.color = color;
		this.ambient = ambient;
		this.broadcast = broadcast;
		this.specular = specular;
		this.bright = bright;
	}

	public void setCenter(Point center){
		this.center = center;
	}

	public void setColor(Color color){
		this.color = color;
	}

	public void setPhong(float ambient, float broadcast, float specular, float bright){
		this.ambient = ambient;
		this.broadcast = broadcast;
		this.specular = specular;
		this.bright = bright;
	}

	public String toString(){
		return this.center.toString() + this.color.toString();
	}

	public String getPhongComponent(){
		return "component<" + this.ambient + "," + this.broadcast + "," + this.specular + "," + this.bright + ">";
	}

	public Color getColor(){
		return this.color;
	}

	public float getAmbient(){
		return this.ambient;
	}

	public float getBroadcast(){
		return this.broadcast;
	}

	public float getSpecular(){
		return this.specular;
	}

	public float getBright(){
		return this.bright;
	}

	public Point getCenter(){
		return this.center;
	}

	public abstract void isHit(Ray ray);

	public abstract boolean isHit(Ray ray, Vector vector);

}