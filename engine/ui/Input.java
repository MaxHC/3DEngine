package engine.ui;

import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Input extends JFrame implements KeyListener{
	
	public View preview;

	public Input(View preview){
		this.preview = preview;
		this.addKeyListener(this);
	}

	//if a configured key is press move the camera in the selected direction
	public void keyPressed(KeyEvent ke){
		if(ke.getKeyChar() == 'z'){ //go forward
			this.preview.camera.moveZ(1);
			this.preview.displayImage();;
		} else if(ke.getKeyChar() == 'q'){ //go left
			this.preview.camera.moveX(-1);
			this.preview.displayImage();
		} else if(ke.getKeyChar() == 's'){ //go backward
			this.preview.camera.moveZ(-1);
			this.preview.displayImage();
		} else if(ke.getKeyChar() == 'd'){ //go right
			this.preview.camera.moveX(1);
			this.preview.displayImage();
		} else if(ke.getKeyChar() == ' '){ //go up
			this.preview.camera.moveY(1);
			this.preview.displayImage();
		} else if(ke.getKeyChar() == 'c'){ //go down
			this.preview.camera.moveY(-1);
			this.preview.displayImage();
		}
	}

	public void keyReleased(KeyEvent ke){

	}

	public void keyTyped(KeyEvent ke){

	}

}