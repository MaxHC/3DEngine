package engine.pov.reader;

import engine.pov.elements.*;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saver{

	public ArrayList<Actor> scene;
	public Camera camera;
	public Light light;
	private String dir;

	public Saver(ArrayList<Actor> scene, Camera camera, Light light){
		this.scene = scene;
		this.camera = camera;
		this.light = light;
		this.dir = "..\\saves\\";
	}

	public String RTFile(){
		String file = this.camera.toString() + this.light.toString();

		for(Actor actor : this.scene){
			file += actor.toString();
		}

		return file;
	}

	public void save(String filename){
		try{
			FileWriter save = new FileWriter(dir + filename + ".rt");
			save.write(this.RTFile());
			save.close();
			File f = new File(dir + filename + ".rt");
			System.out.println("Successfully wrote to the file in : " + f.getAbsolutePath());
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}