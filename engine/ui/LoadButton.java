package engine.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoadButton extends JButton implements ActionListener{

	private UserInterface mainApps;

	public LoadButton(String title, UserInterface mainApps){
		super(title);
		this.mainApps = mainApps;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){
		this.mainApps.loadScene(e.getActionCommand()); //load selected scene
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this); //get frame where this button is
		topFrame.dispose(); //close it
	}

}