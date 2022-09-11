package engine.pov.elements;

import java.lang.Math;
import engine.raytracing.Ray;
import engine.raytracing.Vector;

public class Sphere extends Actor{

	private float radius;

	public Sphere(Point center, Color couleur, float radius, float ambient, float broadcast, float specular, float bright){
		super(center,couleur, ambient, broadcast, specular, bright);
		this.radius = radius;
	}

	public float getRadius(){
		return radius;
	}

	public void setRadius(float radius){
		this.radius = radius;
	}

	public String toString(){
		return "sphere<" + super.toString() + "radius<" + this.radius + ">" + super.getPhongComponent() + ">";
	}

	//set the actor hit (if there is one)
	public void isHit(Ray ray){
		Point intersection = null;
		Point intersection1, intersection2;
		Point rayIntersection = ray.getIntersectionPoint();
		Point rayInitPoint = ray.getInitPoint();
		Vector directorVector = ray.getVector();
		Vector vector;

		//get delta
		double t, t2, a, b, c, delta, d1, d2;
		a = Math.pow(directorVector.getX(),2) + Math.pow(directorVector.getY(),2) + Math.pow(directorVector.getZ(),2);
		b = 2*(rayInitPoint.getX()*directorVector.getX() + rayInitPoint.getY()*directorVector.getY() + rayInitPoint.getZ()*directorVector.getZ()) - 2*(directorVector.getX()*this.getCenter().getX() + directorVector.getY()*this.getCenter().getY() + directorVector.getZ()*this.getCenter().getZ());
		c = Math.pow(rayInitPoint.getX(),2) + Math.pow(rayInitPoint.getY(),2) + Math.pow(rayInitPoint.getZ(),2) + Math.pow(this.getCenter().getX(),2) + Math.pow(this.getCenter().getY(),2) + Math.pow(this.getCenter().getZ(),2) - 2*(rayInitPoint.getX()*this.getCenter().getX() + rayInitPoint.getY()*this.getCenter().getY() + rayInitPoint.getZ()*this.getCenter().getZ()) - Math.pow(this.getRadius(), 2);
		delta = Math.pow(b, 2) - 4*a*c;

		if(delta >= 0){

			if(delta == 0){ //if delta = 0 there is 1 root
				t = -b/(2*a); //get the root
				intersection = calculateIntersection(rayInitPoint, t, directorVector); //get intersection
			} else if(delta > 0){ //if delta > 0, there is 2 roots
				//get intersections points
				t = (-b-Math.sqrt(delta))/(2*a); //get  first root
				intersection1 = calculateIntersection(rayInitPoint, t, directorVector); //get first intersection

				t2 = (-b+Math.sqrt(delta))/(2*a);//get second root
				intersection2 = calculateIntersection(rayInitPoint, t2, directorVector); //get second intersection

				//get distance between init and intersection point to return the closer point
				d1 = calculateDistance(intersection1, rayInitPoint);
				d2 = calculateDistance(intersection2, rayInitPoint);

				//get closer intersection
				if(d1 < d2){
					intersection = intersection1;
				} else {
					intersection = intersection2;
				}
			}

			vector = new Vector(intersection, rayInitPoint); //get vector from intersection to the ray

			//if it's in front of the init point
			if(getVectorMultiplier(directorVector, vector) >= 0){

				if(rayIntersection != null){ //if actor already hit
					d1 = calculateDistance(intersection, rayInitPoint);//get distance from intersection with this actor
					d2 = calculateDistance(rayIntersection, rayInitPoint);//get distance with closer actor calculated

					//if new intersection is closer, get this new actor as hit actor
					if(d1 < d2){
						ray.setIntersectionPoint(intersection);
						ray.setActorHit(this);
					}
				} else { //set this actor as hit actor
					ray.setIntersectionPoint(intersection);
					ray.setActorHit(this);
				}
			}

		}

	}

	//return if actor is hit
	public boolean isHit(Ray ray, Vector directorVector){

		Point initPoint = ray.getIntersectionPoint(); //init point of the scattering
		Point intersection, intersection2;
		Vector vector, vector2;

		//get delta
		double t, t2, a, b, c, delta, d1, d2;
		double x = 0;
		double x2 = 0;
		a = Math.pow(directorVector.getX(),2) + Math.pow(directorVector.getY(),2) + Math.pow(directorVector.getZ(),2);
		b = 2*(initPoint.getX()*directorVector.getX() + initPoint.getY()*directorVector.getY() + initPoint.getZ()*directorVector.getZ()) - 2*(directorVector.getX()*this.getCenter().getX() + directorVector.getY()*this.getCenter().getY() + directorVector.getZ()*this.getCenter().getZ());
		c = Math.pow(initPoint.getX(),2) + Math.pow(initPoint.getY(),2) + Math.pow(initPoint.getZ(),2) + Math.pow(this.getCenter().getX(),2) + Math.pow(this.getCenter().getY(),2) + Math.pow(this.getCenter().getZ(),2) - 2*(initPoint.getX()*this.getCenter().getX() + initPoint.getY()*this.getCenter().getY() + initPoint.getZ()*this.getCenter().getZ()) - Math.pow(this.getRadius(), 2);
		delta = Math.pow(b, 2) - 4*a*c;

		if(delta == 0){ //same as the higher function
			t = -b/(2*a);
			intersection = calculateIntersection(initPoint, t, directorVector);
			vector = new Vector(intersection, initPoint); //get vector from intersection toi initPoint

			return getVectorMultiplier(directorVector, vector)>0; //if multiplier > 0 then the actor is obstructing the light so don't illuminate the pixel

		} else if(delta > 0){ //same as the higher function
			//get intersection point
			t = (-b-Math.sqrt(delta))/(2*a);
			intersection = calculateIntersection(initPoint, t, directorVector);
			t2 = (-b+Math.sqrt(delta))/(2*a);
			intersection2 = calculateIntersection(initPoint, t2, directorVector);

			//get distance between point and intersection to add margin of error
			d1 = calculateDistance(intersection, initPoint);
			d2 = calculateDistance(intersection2, initPoint);

			//get vectors from the intersections to initPoint
			vector = new Vector(intersection, initPoint);
			vector2 = new Vector(intersection2, initPoint);

			return ((getVectorMultiplier(directorVector, vector)>0 && d1 > 0.00000000001) || (getVectorMultiplier(directorVector, vector2)>0 && d2 >  0.00000000001)); //d > 0.00000000001 = margin of error for intersection, causing some bugs due to rounded || //if multiplier > 0 then the actor is obstructing the light so don't illuminate the pixel

		}

		return false;

	}

	//use to check if the vector is a >0 multiplier, so in front of the point by the director vector
	private double getVectorMultiplier(Vector directorVector, Vector vector){
		double x = 0;

		if(directorVector.getX() != 0){
			x = vector.getX()/directorVector.getX();
		} else if(directorVector.getY() != 0){
			x = vector.getY()/directorVector.getY();
		} else if(directorVector.getZ() != 0){
			x = vector.getZ()/directorVector.getZ();
		}

		return x;
	}

	//return the distance between 2 point
	private double calculateDistance(Point a, Point b){
		return Math.sqrt(Math.pow((a.getX()-b.getX()),2) + Math.pow((a.getY()-b.getY()),2) + Math.pow((a.getZ()-b.getZ()),2)); //mathematical function to get the distance between 2 points
	}

	//return the intersection with the root obtain by delta
	private Point calculateIntersection(Point point, double t, Vector vector){
		return new Point((point.getX()+t*vector.getX()), (point.getY()+t*vector.getY()), (point.getZ()+t*vector.getZ())); //function to calculate the point of intersection from a root
	}

}
