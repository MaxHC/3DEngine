package engine.pov.elements;

import java.util.ArrayList;
import engine.raytracing.Ray;
import engine.raytracing.Vector;

public class Camera{

	//add rotation later

	private Point position;
	private int width, height, aperture;
	private ArrayList<Ray> rayList;

	public Camera(Point position, int width, int height, int aperture){
		this.position = position;
		this.aperture = aperture;
		this.width = width;
		this.height = height;
		this.createRayList();
	}

	public Point getPosition(){
		return this.position;
	}

	public int getWidth(){
		return this.width;
	}

	public int getHeight(){
		return this.height;
	}

	public int getAperture(){
		return this.aperture;
	}

	public ArrayList<Ray> getRayList(){
		return this.rayList;
	}

	public int getNumberRay(){
		return this.rayList.size();
	}

	public String toString(){
		return "camera<" + this.position.toString() + "width<" + this.width + ">height<" + this.height + ">apperture<" + this.aperture + ">>";
	}

	public void moveX(double x){
		this.position.addX(x);
	}

	public void moveY(double y){
		this.position.addY(y);
	}

	public void moveZ(double z){
		this.position.addZ(z);
	}

	public void setCenter(Point position){
		this.position = position;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public void setAperture(int aperture){
		this.aperture = aperture;
	}

	public void cast(ArrayList<Actor> scene,  Light light){
		System.out.println("refresh");
		this.refreshRay();
		this.castRay(scene, light);
	}

	public void createRayList(){
		this.rayList = new ArrayList<Ray>();
		int i = 0;
		int j = 0;
		for(float x= -1f; x < 1 && i<this.width; x+=(2f/this.width)){ //create the x value of the vectors
			j=0;
			for(float y= (this.height/(this.width*1f)); y > -1f*(this.height/(this.width*1f)) && j<this.height; y-=(2f*this.height/this.width)/this.height){ //create a the y value of the vectors scalled on the screen to don't deform the image
				int pixel[] = {i, j}; //set position in the image
				this.rayList.add(new Ray(new Vector(x, y, 1), this.position, pixel)); //ray(Vector, Point initial, pixel)
				j++;
			}
			i++;
		}
	}

	public void castRay(ArrayList<Actor> scene,  Light light){
		System.out.println("generating ray...");
		int lighted = 0;
		for(Ray ray : this.rayList){ //for each ray
			ray.tracing(scene); //get if actor is hit
			if(ray.getActorHit() != null){
				ray.illuminate(scene, light); //get illumination value
				ray.setFinalColor(); //set final color of the ray that will be used to create the pixel
			}
		}
		System.out.println("generated !");
	}

	//reset all value by default to don't recreate rays
	public void refreshRay(){
		for(Ray ray : this.rayList){
			if(ray.hasActorHit()){
				ray.setActorHit(null);
				ray.setIntersectionPoint(null);
				ray.setColor(new Color(255,255,255));
				ray.setIa(0);
				ray.setIb(0);
				ray.setIs(0);
			}
		}
	}

}
