package engine.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import engine.pov.elements.*;

public class ActorButton extends JButton implements ActionListener{

	private ActorControl actorPanel;

	public ActorButton(String title, ActorControl actorPanel){
		super(title);
		this.actorPanel = actorPanel;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Add")){
			this.actorPanel.displayAdder();
		} else if(e.getActionCommand().equals("Sphere")){
			this.actorPanel.addActor(new Sphere(new Point(0,0,0), new Color(255,255,255), 1, 0.5f, 0.5f, 0.5f, 10));
			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this); //get frame where this button is
			topFrame.dispose(); //close it
		}
	}

}