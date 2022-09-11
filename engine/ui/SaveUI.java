package engine.ui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.util.ArrayList;

import engine.pov.reader.Loader;

public class SaveUI extends JPanel{

	public UserInterface mainApps;
	public JTextArea text;

	public SaveUI(UserInterface mainApps){
		this.mainApps = mainApps;
		this.text = new JTextArea(1, 30);
		this.add(this.text);
		this.add(new SaveButton("Confirm", this.mainApps, this));
	}

}