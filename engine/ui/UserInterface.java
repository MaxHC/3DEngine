package engine.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.util.ArrayList;

import engine.pov.elements.*;
import engine.pov.reader.*;

public class UserInterface extends JFrame{

	public ArrayList<Actor> scene;
	public Camera camera, previewCamera, movingCamera;
	public Light light;
	public View view, preview;
	public ActorControl actorPanel;

	public UserInterface(){
		this.setDefaultValue();
		this.preview = new View(this.previewCamera, this.scene, this.light, this.previewCamera.getWidth(), this.previewCamera.getHeight());
		this.setMovingCamera();
		this.view = new View(this.movingCamera, this.scene, this.light, this.movingCamera.getWidth(), this.movingCamera.getHeight());
		this.actorPanel = new ActorControl(this.camera, this.light, this.scene, this);
		this.addComponents();
	}

	//set the scene to default value, create a empty scene
	public void setDefaultValue(){
		this.scene = new ArrayList<Actor>();
		this.camera = new Camera(new Point(0,0,0), 500, 500, 180);
		this.light = new Light(new Point(0,0,0));
		setPreviewCamera();
	}

	public void setActorPanel(){
		this.actorPanel.setValue(this.camera, this.light, this.scene);
		this.actorPanel.createPanel();
	}

	private void setPreviewCamera(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int camWidth = (int)Math.round(7*(screenSize.width/1.2)/10);
		int camHeight = (int)Math.round(7*(screenSize.height/1.2)/10);

		//don't have a larger/higher image in preview than set in the rt file and keep the scale 
		if(camWidth > this.camera.getWidth() || camHeight > this.camera.getHeight()){
			camWidth = this.camera.getWidth();
			camHeight = this.camera.getHeight();
		}

		this.previewCamera = new Camera(this.camera.getPosition(), camWidth, camHeight, camera.getAperture());
	}

	private void setMovingCamera(){
		this.movingCamera = new Camera(new Point(this.camera.getPosition().getX(), this.camera.getPosition().getY(),this.camera.getPosition().getZ()), this.camera.getWidth(), this.camera.getHeight(), this.camera.getAperture());
	}

	public void setView(){
		this.setMovingCamera();
		this.view.setView(this.movingCamera, this.scene, this.light, movingCamera.getWidth(), movingCamera.getHeight());
		this.setPreviewCamera();
		this.preview.setView(this.previewCamera, this.scene, this.light, this.previewCamera.getWidth(), this.previewCamera.getHeight());
	}

	public void addComponents(){
		//set dimension for the UI
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int)Math.round(screenSize.width/1.2);
		int screenHeight = (int)Math.round(screenSize.height/1.2);

		TaskBar taskBar = new TaskBar(this);
		taskBar.setPreferredSize(new Dimension(screenWidth, screenHeight/20));

		this.setTitle("RT Engine");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setLayout(new BorderLayout(15, screenHeight/15));
		this.add(taskBar, BorderLayout.NORTH);
		this.add(this.preview, BorderLayout.CENTER);
		this.add(this.actorPanel, BorderLayout.EAST);
		this.pack();
		this.setVisible(true);
	}

	//display the view in larger frame and add inputs to move in the scene
	public void fullView(){
		this.setView();
		Input frame = new Input(this.view);
		this.view.displayImage();

		frame.setTitle("View");
		frame.setPreferredSize(new Dimension(this.camera.getWidth(), this.camera.getHeight()));
		frame.add(view);
		frame.pack();
		frame.setVisible(true);
	}

	//load a new scene from a file
	public void loadScene(String filename){
		Creator creator = new Creator(new Loader().load(filename));
		this.scene = creator.getScene();
		this.camera = creator.getCamera();
		this.light = creator.getLight();
		this.setPreviewCamera();
		this.setView();
		this.updateView();
		this.setActorPanel();
	}

	//update the preview
	public void updateView(){
		this.actorPanel.repaint();
		this.preview.displayImage();
	}

	public void saveScene(String filename){
		Saver save = new Saver(this.scene, this.camera, this.light);
		save.save(filename);
	}

	public void removeActor(Actor actor){
		if(this.scene.remove(actor)){
			System.out.println("Actor (" + actor.toString() + ") removed sucessefully");
			this.setActorPanel();
			this.updateView();
		} else {
			System.out.println("Error trying to remove actor (" + actor.toString() + ")");
		}
	}

}