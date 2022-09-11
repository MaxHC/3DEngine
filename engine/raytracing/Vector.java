package engine.raytracing;

import engine.pov.elements.Point;

public class Vector{

	double x, y, z;

	public Vector(double x, double y, double z){ //create vector from know coord
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector(Point a, Point b){ //create vector between 2 point
		this.x = a.getX() - b.getX();
		this.y = a.getY() - b.getY();
		this.z = a.getZ() - b.getZ();
	}

	public double getX(){
		return this.x;
	}

	public double getY(){
		return this.y;
	}

	public double getZ(){
		return this.z;
	}

	public String toString(){
		return "(" + this.x + ";" + this.y + ";" + this.z +")";
	}

	//get Normalized value of a vector
	public Vector getNormalized(){
		double standard = Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
		return new Vector(this.x/standard, this.y/standard, this.z/standard);
	}

	public Vector getMultiplied(double multiplier){
		return new Vector(this.x*multiplier, this.y*multiplier, this.z*multiplier);
	}

	public Vector soustractVector(Vector vector){
		return new Vector(this.x - vector.getX(), this.y - vector.getY(), this.z - vector.getZ());
	}

	public Vector multiplieVector(Vector vector){
		return new Vector(this.x + vector.getX(), this.y + vector.getY(), this.z + vector.getZ());
	}

	public double getAngleWith(Vector vector){
		double uv = this.x*vector.getX() + this.y*vector.getY() + this.z*vector.getZ(); // u.v
		double u = Math.sqrt(Math.pow(this.x,2) + Math.pow(this.y,2) + Math.pow(this.z,2)); // |u|
		double v = Math.sqrt(Math.pow(vector.getX(),2) + Math.pow(vector.getY(),2) + Math.pow(vector.getZ(),2)); // |v|
		return uv/(u*v); // (u.v)/(|u|*|v|) = cos(teta)
	}

}
