package engine.raytracing;

import java.util.ArrayList;
import engine.pov.elements.Color;
import engine.pov.elements.Point;
import engine.pov.elements.Actor;
import engine.pov.elements.Light;

public class Ray{

	private Point initPoint, intersectionPoint;
	public int pixel[];
	private Color color;
	private Vector vector; //vector, set by the rayCreator class ([x, y, z])
	private Actor actorHit;
	private float ia, ib, is;

	public Ray(Vector vector, Point initPoint, int pixel[]){
		this.setInitPoint(initPoint);
		this.setVector(vector);
		this.pixel = pixel;
		this.setColor(new Color(255, 255, 255)); //set to white by default
	}

	public void setInitPoint(Point newInitPoint){
		this.initPoint = newInitPoint;
	}

	public void setIntersectionPoint(Point endPoint){
		this.intersectionPoint = endPoint;
	}

	public void setVector(Vector newVector){
		this.vector = newVector;
	}

	public void setColor(Color color){
		this.color = color;
	}

	public void setActorHit(Actor actor){
		this.actorHit = actor;
	}

	public boolean hasActorHit(){
		return this.actorHit != null;
	}

	public void setIa(float ia){
		this.ia = ia;
	}

	public void setIb(float ib){
		this.ib = ib;
	}

	public void setIs(float is){
		this.is = is;
	}

	public Color getColor(){
		return this.color;
	}

	public Point getInitPoint(){
		return this.initPoint;
	}

	public Point getIntersectionPoint(){
		return this.intersectionPoint;
	}

	public Vector getVector(){
		return this.vector;
	}

	public Actor getActorHit(){
		return this.actorHit;
	}

	public float getIa(){
		return this.ia;
	}

	public float getIb(){
		return this.ib;
	}

	public float getIs(){
		return this.is;
	}

	//get is actor is hit or not
	public void tracing(ArrayList<Actor> scene){

		for(Actor actor : scene){
			actor.isHit(this); //for each actor get the closer actor
		}

		if(this.getActorHit() != null){
			this.setIa(this.getActorHit().getAmbient()); //set ambient component of phong's ombrage : ambiant
		}
	}

	public boolean illuminate(ArrayList<Actor> scene, Light light){

		Point intersection = this.getIntersectionPoint();
		Point lightPos = light.getPosition();

		Vector intersection2light = new Vector(lightPos, intersection); //vector between intersection and light

		for(Actor actor : scene){
			if(actor.isHit(this, intersection2light)){ //if actor hit by the ray don't illuminate
				return true;
			}
		}

		Vector n = new Vector(intersection, this.getActorHit().getCenter());

		Vector normalN = n.getNormalized();
		Vector normalI2L = intersection2light.getNormalized();

		//get broadcast component of phong's ombrage : broadcast*(N.L)

		double scalaire = normalN.getAngleWith(normalI2L); //(N.L)

		float ib = 0;
		float is = 0;

		if(scalaire < 0){ //if cos(teta) < 0, then not lighted
			ib = 0;
		} else {
			ib = this.getActorHit().getBroadcast()*Double.valueOf(scalaire).floatValue();

			//get specular component of phong's ombrage : specular*(R.V)*alpha, R = 2*(N.L)N-L

			Vector r1 = normalN.getMultiplied(2*scalaire); //2*(N.L)N
			Vector r = r1.soustractVector(normalI2L).getNormalized(); //2*(N.L)N-L : R

			double scalaireRV = r.getAngleWith(this.vector); // (R.V)

			is = this.getActorHit().getSpecular()*Double.valueOf(Math.pow(scalaireRV, this.getActorHit().getBright())).floatValue(); //specular*(R.V)*alpha
		}

		this.setIb(ib);
		this.setIs(is);

		return false;

	}

	public void setFinalColor(){
		Color actorColor = this.getActorHit().getColor();
		ArrayList<Integer> finalColor = new ArrayList<Integer>();
		int finalComponent;

		for(int component : actorColor.getRGB()){
			finalComponent = Math.round(component*(this.getIa() + this.getIb() + this.getIs())); //apply phong's ombrage : color*(ambient + broadcast + specular)

			if(finalComponent > 255){
				finalComponent = 255;
			}

			finalColor.add(finalComponent);
		}
		this.setColor(new Color(finalColor)); //ambiant component
	}

}
