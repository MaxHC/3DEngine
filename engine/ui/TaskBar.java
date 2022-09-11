package engine.ui;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class TaskBar extends JPanel{

	private UserInterface mainApps;

	public TaskBar(UserInterface mainApps){
		this.mainApps = mainApps;
		this.createButtons();
	}

	//create buttons
	private void createButtons(){
		String titleButton[] = {"New", "Load", "Save", "Full view", "Force scene update"};

		for(String title : titleButton){
			this.add(new TaskButton(title, this));
		}
	}

	public UserInterface getMainApps(){
		return this.mainApps;
	}

	//get new empty scene
	public void reset(){
		this.mainApps.setDefaultValue();
		this.mainApps.setView();
		this.mainApps.updateView();
		this.mainApps.setActorPanel();
	}

	//display a textbox to select a file to Load
	public void displayLoader(){
		JFrame frame = new JFrame();

		frame.setTitle("Load file");
		frame.setLocationRelativeTo(this.mainApps);
		frame.add(new LoadUI(this.mainApps));
		frame.pack();
		frame.setVisible(true);
	}

	//display a textbox to select a name and save
	public void save(){
		JFrame frame = new JFrame();

		frame.setTitle("Load file");
		frame.setLocationRelativeTo(this.mainApps);
		frame.add(new SaveUI(this.mainApps));
		frame.pack();
		frame.setVisible(true);
	}

	public void forceUpdate(){
		this.mainApps.updateView();
	}

}