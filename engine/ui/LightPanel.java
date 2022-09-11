package engine.ui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.GridLayout;
import java.util.ArrayList;

import engine.pov.reader.Loader;
import engine.pov.elements.*;

public class LightPanel extends JPanel{

	public Light light;
	public ArrayList<JTextArea> center;
	public UserInterface mainApps;

	public LightPanel(Light light, UserInterface mainApps){
		this.light = light;
		this.mainApps = mainApps;

		this.center = new ArrayList<JTextArea>();

		this.setMinimumSize(new Dimension(0, 200));
		this.setLayout(new GridLayout(3,1,1,1));
		this.add(new Label("Light"));
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

		this.add(posPanel);
		this.add(new UpdateButton("Update", this));
	}

	private void setAreaValue(){
		Point position = this.light.getPosition();
		this.center.get(0).setText(String.valueOf(position.getX()));
		this.center.get(1).setText(String.valueOf(position.getY()));
		this.center.get(2).setText(String.valueOf(position.getZ()));
	}

	public void setValue(){
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
		this.light.setCenter(new Point(x, y, z));

		this.mainApps.updateView();
	}

}