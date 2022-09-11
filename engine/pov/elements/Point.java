package engine.pov.elements;

public class Point{

	protected double x,y,z;

	public Point(double x, double y,double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void addX(double x){
	  this.x += x;
	}

	public void addY(double y){
	  this.y += y;
	}

	public void addZ(double z){
	  this.z += z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public String toString(){
		return "position<" + this.x + "," + this.y + "," + this.z + ">";
	}

}
