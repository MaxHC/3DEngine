package engine.pov.reader;

import engine.pov.elements.*;
import java.util.ArrayList;

public class Creator{

	private Parser parse;
	private ArrayList<Actor> scene;
	private Camera camera;
	private Light light;

	public Creator(String document){
		this.parse = new Parser(document);
		this.scene = new ArrayList<Actor>();
		this.createScene();
	}

	public ArrayList<Actor> getScene(){
		return this.scene;
	} 

	public Camera getCamera(){
		return this.camera;
	}

	public Light getLight(){
		return this.light;
	}

	public String toString(){
		return "camera : " + this.camera + ", light :" + this.light + " with : " + this.scene.size() + " actor(s)";
	}

	//create the scene
	private void createScene(){
		for(String item : this.parse.getItemList()){//iterate on all object of the scene
			ArrayList<String> component = this.parse.parseComponent(item); //parse the object into component
			if(component.get(0).equals("camera")){ //create camera if it is 
				this.createCamera(component);
			} else if (component.get(0).equals("sphere")){//create sphere if it is 
				this.createSphere(component);
			} else if (component.get(0).equals("light")){//create light if it is 
				this.createLight(component);
			}
		}
	}

	//create camera
	private void createCamera(ArrayList<String> component){
		double position[] = new double[3]; //init array to convert the position 
		int width = Integer.parseInt(component.get(2)); //get width in pixel 
		int height = Integer.parseInt(component.get(3)); //get height in pixel
		int aperture = Integer.parseInt(component.get(4)); //get aperture 
		
		int i = 0; //is used to iterate on the array to save a the correct place the values
		for(String coord : component.get(1).split(",")){ //iterate on the coord array created by split()
			position[i] = Double.parseDouble(coord); //convert the coord to double
			i++;
		}

		this.camera = new Camera(new Point(position[0], position[1], position[2]), width, height, aperture); //create object
	}

	//create sphere
	private void createSphere(ArrayList<String> component){
		double position[] = new double[3]; //init array to convert the position
		int color[] = new int[3]; //init array to convert the color
		float radius = Float.parseFloat(component.get(3)); //get radius
		float phong[] = new float[4]; //init array to convert the phong's ombrage component
		
		int i = 0; //is used to iterate on the array to save a the correct place the values
		for(String coord : component.get(1).split(",")){ //iterate on the coord array created by split()
			position[i] = Double.parseDouble(coord); //convert the coord to double
			i++;
		}
		i=0; //is used to iterate on the array to save a the correct place the values
		for(String rgb : component.get(2).split(",")){ //iterate on the color's component array created by split()
			color[i] = Integer.parseInt(rgb); //convert the coord to double
			i++;
		}
		i=0; //is used to iterate on the array to save a the correct place the values
		for(String phongComponent : component.get(4).split(",")){ //iterate on the phong's ombrage array created by split()
			phong[i] = Float.parseFloat(phongComponent); //convert the coord to double
			i++;
		}

		this.scene.add(new Sphere(new Point(position[0], position[1], position[2]), new Color(color[0], color[1], color[2]), radius, phong[0], phong[1], phong[2], phong[3])); //create object
	}

	//create light
	private void createLight(ArrayList<String> component){
		double position[] = new double[3];
		
		int i = 0; //is used to iterate on the array to save a the correct place the values
		for(String coord : component.get(1).split(",")){ //iterate on the coord array created by split()
			position[i] = Double.parseDouble(coord); //convert the coord to double
			i++;
		}

		this.light = new Light(new Point(position[0], position[1], position[2])); //create object
	}
}
