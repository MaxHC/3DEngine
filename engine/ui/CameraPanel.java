package engine.ui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Label;
import java.awt.GridLayout;
import java.util.ArrayList;

import engine.pov.reader.Loader;
import engine.pov.elements.*;

public class CameraPanel extends JPanel{

	public Camera camera;
	public ArrayList<JTextArea> center;
	public JTextArea widthArea, heightArea, apertureArea;
	public UserInterface mainApps;

	public CameraPanel(Camera camera, UserInterface mainApps){
		this.camera = camera;
		this.widthArea = new JTextArea();
		this.heightArea = new JTextArea();
		this.apertureArea = new JTextArea();
		this.mainApps = mainApps;

		this.center = new ArrayList<JTextArea>();

		this.setLayout(new GridLayout(6,1,1,1));
		this.add(new Label("Camera"));
		this.createTextArea();
		this.setAreaValue();
		this.createPanel();
	}

	private void createTextArea(){
		for(int i=0; i<3; i++){ //create position text area
			this.center.add(new JTextArea(1, 5));
		}
	}

	private void createPanel(){

		//position panel
		JPanel posPanel = new JPanel();
		posPanel.setLayout(new GridLayout(1,4,1,1));
		posPanel.add(new Label("Position :"));
		for(JTextArea area : this.center){
			posPanel.add(area);
		}

		//width panel
		JPanel widthPanel = new JPanel();
		widthPanel.setLayout(new GridLayout(1, 2,1,1));
		widthPanel.add(new Label("Width :"));
		widthPanel.add(this.widthArea);

		//height panel
		JPanel heightPanel = new JPanel();
		heightPanel.setLayout(new GridLayout(1, 2,1,1));
		heightPanel.add(new Label("Height :"));
		heightPanel.add(this.heightArea);

		//aperture panel
		JPanel aperturePanel = new JPanel();
		aperturePanel.setLayout(new GridLayout(1, 2,1,1));
		aperturePanel.add(new Label("Aperture :"));
		aperturePanel.add(this.apertureArea);

		this.add(posPanel);
		this.add(widthPanel);
		this.add(heightPanel);
		this.add(aperturePanel);
		this.add(new UpdateButton("Update", this));
	}

	private void setAreaValue(){
		this.widthArea.setText(String.valueOf(this.camera.getWidth()));
		this.heightArea.setText(String.valueOf(this.camera.getHeight()));
		this.apertureArea.setText(String.valueOf(this.camera.getAperture()));

		Point position = this.camera.getPosition();
		this.center.get(0).setText(String.valueOf(position.getX()));
		this.center.get(1).setText(String.valueOf(position.getY()));
		this.center.get(2).setText(String.valueOf(position.getZ()));

	}

	public void setValue(){
		int width = Integer.parseInt(this.widthArea.getText());
		int height = Integer.parseInt(this.heightArea.getText());
		int aperture = Integer.parseInt(this.apertureArea.getText());

		this.camera.setWidth(width);
		this.camera.setHeight(height);
		this.camera.setAperture(aperture);

		double x = Double.parseDouble(this.center.get(0).getText());
		double y = Double.parseDouble(this.center.get(1).getText());
		double z = Double.parseDouble(this.center.get(2).getText());
		/*if(x == null){
			x = 0;
		}
		if(y == null){
			y = 0;
		}
		if(z == null){
			z = 0;
		}*/
		this.camera.setCenter(new Point(x, y, z));
		this.camera.createRayList();

		this.mainApps.setView();
		this.mainApps.updateView();
	}

}