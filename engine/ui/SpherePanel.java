package engine.ui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Label;
import java.awt.GridLayout;
import java.util.ArrayList;

import engine.pov.reader.Loader;
import engine.pov.elements.*;

public class SpherePanel extends JPanel{

	public Sphere sphere;
	public ArrayList<JTextArea> center, color, phong;
	public JTextArea radiusArea;
	public UserInterface mainApps;

	public SpherePanel(Actor sphere, UserInterface mainApps){
		this.sphere = (Sphere)sphere;
		this.radiusArea = new JTextArea();
		this.mainApps = mainApps;

		this.center = new ArrayList<JTextArea>();
		this.color = new ArrayList<JTextArea>();
		this.phong = new ArrayList<JTextArea>();

		this.setLayout(new GridLayout(5,1,1,1));
		this.createTextArea();
		this.setAreaValue();
		this.setValue();
		this.createPanel();
	}

	private void createTextArea(){
		for(int i=0; i<3; i++){ //create position text area
			this.center.add(new JTextArea(1, 5));
		}
		for(int i=0; i<3; i++){ //create color text area
			this.color.add(new JTextArea(1, 5));
		}
		for(int i=0; i<4; i++){ //create phong text area
			this.phong.add(new JTextArea(1, 5));
		}
	}

	private void createPanel(){
		//first panel
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 2,1,1));
		topPanel.add(new Label("Sphere    Radius :"));
		topPanel.add(this.radiusArea);

		//position panel
		JPanel posPanel = new JPanel();
		posPanel.setLayout(new GridLayout(1,4,1,1));
		posPanel.add(new Label("Position :"));
		for(JTextArea area : this.center){
			posPanel.add(area);
		}

		//color panel
		JPanel colorPanel = new JPanel();
		colorPanel.setLayout(new GridLayout(1,4,1,1));
		colorPanel.add(new Label("Color :"));
		for(JTextArea area : this.color){
			colorPanel.add(area);
		}

		//phong panel
		JPanel phongPanel = new JPanel();
		phongPanel.setLayout(new GridLayout(1,5,1,1));
		phongPanel.add(new Label("Phong :"));
		for(JTextArea area : this.phong){
			phongPanel.add(area);
		}

		this.add(topPanel);
		this.add(posPanel);
		this.add(colorPanel);
		this.add(phongPanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.add(new UpdateButton("Update", this));
		buttonPanel.add(new RemoveButton("Remove", this.mainApps, (Actor)this.sphere));
		this.add(buttonPanel);
	}

	private void setAreaValue(){
		this.radiusArea.setText(String.valueOf(this.sphere.getRadius()));

		Point position = this.sphere.getCenter();
		this.center.get(0).setText(String.valueOf(position.getX()));
		this.center.get(1).setText(String.valueOf(position.getY()));
		this.center.get(2).setText(String.valueOf(position.getZ()));

		ArrayList<Integer> color = this.sphere.getColor().getRGB();
		this.color.get(0).setText(String.valueOf(color.get(0)));
		this.color.get(1).setText(String.valueOf(color.get(1)));
		this.color.get(2).setText(String.valueOf(color.get(2)));

		this.phong.get(0).setText(String.valueOf(this.sphere.getAmbient()));
		this.phong.get(1).setText(String.valueOf(this.sphere.getBroadcast()));
		this.phong.get(2).setText(String.valueOf(this.sphere.getSpecular()));
		this.phong.get(3).setText(String.valueOf(this.sphere.getBright()));
	}

	public void setValue(){
		float radius = Float.parseFloat(this.radiusArea.getText());
		/*if(radius == null){
			radius = 0;
		}*/
		this.sphere.setRadius(radius);

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
		this.sphere.setCenter(new Point(x, y, z));

		int r = Integer.parseInt(this.color.get(0).getText());
		int g = Integer.parseInt(this.color.get(1).getText());
		int b = Integer.parseInt(this.color.get(2).getText());
		/*if(r == null){
			r = 0;
		}
		if(g == null){
			g = 0;
		}
		if(b == null){
			b = 0;
		}*/
		this.sphere.setColor(new Color(r, g, b));

		float ambiant = Float.parseFloat(this.phong.get(0).getText());
		float broadcast = Float.parseFloat(this.phong.get(1).getText());
		float specular = Float.parseFloat(this.phong.get(2).getText());
		float bright = Float.parseFloat(this.phong.get(3).getText());
		/*if(ambiant == null){
			ambiant = 0;
		}
		if(broadcast == null){
			broadcast = 0;
		}
		if(specular == null){
			specular = 0;
		}
		if(bright == null){
			bright = 0;
		}*/
		this.sphere.setPhong(ambiant, broadcast, specular, bright);

		this.mainApps.updateView();
	}

}