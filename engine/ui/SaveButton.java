package engine.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;

public class SaveButton extends JButton implements ActionListener{

	private UserInterface mainApps;
	private SaveUI save;

	public SaveButton(String title, UserInterface mainApps, SaveUI save){
		super(title);
		this.mainApps = mainApps;
		this.save = save;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){
		this.mainApps.saveScene(save.text.getText()); //save the file with the name selected
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this); //get frame where this button is
		topFrame.dispose(); //close it
	}

}