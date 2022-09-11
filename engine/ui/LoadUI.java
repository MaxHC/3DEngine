package engine.ui;

import javax.swing.JPanel;
import java.util.ArrayList;

import engine.pov.reader.Loader;

public class LoadUI extends JPanel{

	public UserInterface mainApps;

	public LoadUI(UserInterface mainApps){
		this.mainApps = mainApps;
		this.createButtons();
	}

	//create buttons
	private void createButtons(){
		ArrayList<String> titleButton = new Loader().getFilesList();

		for(String title : titleButton){
			this.add(new LoadButton(title, this.mainApps));
		}
	}
}