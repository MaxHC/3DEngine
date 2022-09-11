package engine.pov.elements;

import engine.raytracing.Ray;
import engine.raytracing.Vector;


public class Cone extends Actor{

	private double radius;
	private double height;
	private	double alpha;


	public Cone(Point center, Color color, double radius,double height,float ambient, float broadcast, float specular, float bright){
		super(center,color, ambient, broadcast, specular, bright);
		this.radius = radius;
		this.height = height;
		double s = Math.sqrt(Math.pow(height,2)+Math.pow(radius,2)); //get slant height
		this.alpha = 2*Math.asin(radius/s); //get cone apperture in degree
		//this.alpha *= Math.PI/180; //get alpha froml degree to radian
	}


	public void isHit(Ray ray){
		Point intersection = null;
		Point intersection1, intersection2;
		Point rayIntersection = ray.getIntersectionPoint();
		Point rayInitPoint = ray.getInitPoint();
		Vector directorVector = ray.getVector();
		Vector vector;
		
		//get delta
		double t, t2, a, b, c, delta, d1, d2;
		a = Math.pow(directorVector.getX(),2) + Math.pow(directorVector.getY(),2) - (Math.pow(directorVector.getZ(),2)/Math.pow(Math.tan(this.alpha),2));
		b = 2*(rayInitPoint.getX()*directorVector.getX() + rayInitPoint.getY()*directorVector.getY() - (rayInitPoint.getZ()*directorVector.getZ()/Math.pow(Math.tan(this.alpha),2)));
		c = Math.pow(rayInitPoint.getX(),2) + Math.pow(rayInitPoint.getY(),2) - (Math.pow(rayInitPoint.getZ(),2)/Math.pow(Math.tan(this.alpha),2)) ;
		delta = Math.pow(b, 2) - 4*a*c;

		if(delta >= 0){

			if(delta == 0){
				t = -b/(2*a);
				intersection = calculateIntersection(rayInitPoint, t, directorVector);
			} else if(delta > 0){
				//get intersections points
				t = (-b-Math.sqrt(delta))/(2*a);
				intersection1 = calculateIntersection(rayInitPoint, t, directorVector);
				
				t2 = (-b+Math.sqrt(delta))/(2*a);
				intersection2 = calculateIntersection(rayInitPoint, t2, directorVector);

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

			vector = new Vector(intersection, rayInitPoint);

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

	public boolean isHit(Ray ray, Vector directorVector){

		Point initPoint = ray.getIntersectionPoint();
		Point intersection, intersection2;
		Vector vector, vector2;
		
		//get delta
		double t, t2, a, b, c, delta, d1, d2;
		double x = 0;
		double x2 = 0;

		a = Math.pow(directorVector.getX(),2) + Math.pow(directorVector.getY(),2) - (Math.pow(directorVector.getZ(),2)*Math.tan(Math.pow(alpha,2)));
		b = 2*(initPoint.getX()*directorVector.getX() + initPoint.getY()*directorVector.getY() - (initPoint.getZ()*directorVector.getZ()*Math.tan(Math.pow(alpha,2))));
		c = Math.pow(initPoint.getX(),2) + Math.pow(initPoint.getY(),2) - (Math.pow(initPoint.getZ(),2)*Math.tan(Math.pow(alpha,2))) ;
		delta = Math.pow(b, 2) - 4*a*c;

		if(delta == 0){
			t = -b/(2*a);
			intersection = calculateIntersection(initPoint, t, directorVector);
			vector = new Vector(intersection, initPoint);
			
			return getVectorMultiplier(directorVector, vector)>0; //if multiplier > 0 then the actor is obstructing the light so don't illuminate the pixel

		} else if(delta > 0){
			//get intersection point
			t = (-b-Math.sqrt(delta))/(2*a);
			intersection = calculateIntersection(initPoint, t, directorVector);
			t2 = (-b+Math.sqrt(delta))/(2*a);
			intersection2 = calculateIntersection(initPoint, t2, directorVector);

			//get distance between point and intersection to add margin of error
			d1 = calculateDistance(intersection, initPoint);
			d2 = calculateDistance(intersection2, initPoint);

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

	private double calculateDistance(Point a, Point b){
		return Math.sqrt(Math.pow((a.getX()-b.getX()),2) + Math.pow((a.getY()-b.getY()),2) + Math.pow((a.getZ()-b.getZ()),2));
	}

	private Point calculateIntersection(Point point, double t, Vector vector){
		return new Point((point.getX()+t*vector.getX()), (point.getY()+t*vector.getY()), (point.getZ()+t*vector.getZ()));
	}



}