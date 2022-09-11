package engine.ui;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import engine.pov.elements.Actor;

public class RemoveButton extends JButton implements ActionListener{
	
	public UserInterface mainApps;
	public Actor actor;

	public RemoveButton(String title, UserInterface mainApps, Actor actor){
		super(title);
		System.out.println("mdr");
		this.mainApps = mainApps;
		this.actor = actor;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){
		System.out.println("hi");
		this.mainApps.removeActor(this.actor);
	}

}