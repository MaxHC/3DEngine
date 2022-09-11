package engine.ui;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import engine.pov.elements.*;
import java.util.ArrayList;

public class View extends JPanel{

	public Camera camera;
	public ArrayList<Actor> scene;
	public Light light;
	public Image img;
	public int width, height;

	public View(Camera cam, ArrayList<Actor> scene, Light light, int width, int height){
		this.setView(cam,scene,light,width,height);
		this.displayImage();
	}

	public void setView(Camera cam, ArrayList<Actor> scene, Light light, int width, int height){
		this.camera = cam;
		this.scene = scene;
		this.light = light;
		this.width = width;
		this.height = height;
	}

	//function to cast ray, create the image, then display it
	public void displayImage(){
		this.camera.cast(this.scene, this.light);
		this.setImage();
		this.repaint();
	}

	//create a new image
	public void setImage(){
		System.out.println("img set");
		this.img = new ImageGenerator().setImage(this.width, this.height, this.camera.getRayList());
	}

	//paint the image on the screen 
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(this.img, 0, 0, this);
	}

}
