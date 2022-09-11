package engine.ui;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import engine.pov.elements.Actor;
import engine.pov.elements.Camera;
import engine.pov.elements.Light;

public class ActorControl extends JPanel{
	
	public Camera camera;
	public Light light;
	public ArrayList<Actor> scene;
	public ActorButton button;
	public UserInterface mainApps;

	public ActorControl(Camera camera, Light light, ArrayList<Actor> scene, UserInterface mainApps){

		this.mainApps = mainApps;
		this.setValue(camera, light, scene);
		this.button = new ActorButton("Add", this);
		this.setBorder(BorderFactory.createTitledBorder("Actor's control panel"));
		createPanel();
	}

	public void setValue(Camera camera, Light light, ArrayList<Actor> scene){
		this.camera = camera;
		this.light = light;
		this.scene = scene;
	}

	public void createPanel(){
		this.removeAll();
		this.setLayout(new GridLayout(this.scene.size()+3,1));

		//camera panel
		CameraPanel cam = new CameraPanel(this.camera, this.mainApps);
		cam.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(cam);

		//light panel
		LightPanel light = new LightPanel(this.light, this.mainApps);
		light.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(light);

		for(Actor actor : this.scene){
			if(actor.getClass().getSimpleName().equals("Sphere")){
				SpherePanel panel = new SpherePanel(actor, mainApps);
				panel.setBorder(BorderFactory.createLineBorder(Color.black));
				this.add(panel);
			}
		}

		this.add(this.button);
		this.validate();
	}

	public void addActor(Actor actor){
		this.scene.add(actor);
		this.createPanel();
		this.mainApps.updateView();
	}

	public void displayAdder(){
		String possibleActor[] = {"Sphere"};
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(150, 100));

		for(String actor : possibleActor){
			frame.add(new ActorButton(actor, this));
		}

		frame.setTitle("Add actor");
		frame.setLocationRelativeTo(this.mainApps);
		frame.pack();
		frame.setVisible(true);
	}

}