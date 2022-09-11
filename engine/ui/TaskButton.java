package engine.ui;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TaskButton extends JButton implements ActionListener{

	private TaskBar taskBar;

	public TaskButton(String title, TaskBar taskBar){
		super(title);
		this.taskBar = taskBar;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("New")){
			taskBar.reset();
		} else if(e.getActionCommand().equals("Load")){
			taskBar.displayLoader();
		} else if(e.getActionCommand().equals("Save")){
			taskBar.save();
		} else if(e.getActionCommand().equals("Full view")){
			taskBar.getMainApps().fullView();
		} else if(e.getActionCommand().equals("Force scene update")){
			taskBar.forceUpdate();
		}
	}

}