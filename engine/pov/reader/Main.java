package engine.pov.reader;

import java.util.ArrayList;
import engine.ui.Input;
import engine.ui.View;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main{

	public static void main(String[] args){

		Loader loader = new Loader();
		String contents = loader.load("testParse.rt");

		for(String file : loader.getFilesList()){
			System.out.println(file);
		}

		Creator creator = new Creator(contents); //create the crator that will use the parser to create the scene
		//Saver save = new Saver(creator.scene, creator.getCamera(), creator.light);
		//save.save("testSave");
		
		//display the scene
		View panel = new View(creator.getCamera(), creator.getScene(), creator.getLight(), creator.getCamera().getWidth(), creator.getCamera().getHeight());
		Input frame = new Input(panel);

		frame.setTitle("RT Engine");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(creator.getCamera().getWidth(), creator.getCamera().getHeight()));
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
