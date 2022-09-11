package engine.ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateButton extends JButton implements ActionListener{

	private SpherePanel spherePanel;
	private CameraPanel cameraPanel;
	private LightPanel lightPanel;

	public UpdateButton(String title, SpherePanel spherePanel){
		super(title);
		this.spherePanel = spherePanel;
		this.addActionListener(this);
	}

	public UpdateButton(String title, CameraPanel cameraPanel){
		super(title);
		this.cameraPanel = cameraPanel;
		this.addActionListener(this);
	}

	public UpdateButton(String title, LightPanel lightPanel){
		super(title);
		this.lightPanel = lightPanel;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){
		if(this.spherePanel != null){
			this.spherePanel.setValue();
		} else if(this.cameraPanel != null){
			this.cameraPanel.setValue();
		} else if(this.lightPanel != null){
			this.lightPanel.setValue();
		}
	}

}