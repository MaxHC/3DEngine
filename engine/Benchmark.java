package engine;

import engine.pov.elements.*;
import engine.ui.ImageGenerator;
import engine.ui.View;
import java.util.ArrayList;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import java.time.Instant;
import java.time.Duration;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Benchmark{
	
	public static void main(String[] args) throws ParseException{

		ArrayList<Actor> scene = new ArrayList<Actor>();
		scene.add(new Sphere(new Point(0,0,20), new Color(255,0,0), 5, 0.5f, 0.5f, 0.5f, 10));
		scene.add(new Sphere(new Point(3,1,10), new Color(0,255,0), 3, 0.5f, 0.5f, 0.5f, 10));
		scene.add(new Sphere(new Point(-4,-2,10), new Color(0,0,255), 2, 0.5f, 0.5f, 0.5f, 10));

		Camera camera = new Camera(new Point(0,0,0), 1920, 1080, 180);
		Light light = new Light(new Point(0, 80, -500));
		ImageGenerator generator = new ImageGenerator();

		System.out.println("createRayList() :");
		Instant start = Instant.now();
 		camera.createRayList();
		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();  //in millis
		System.out.println(timeElapsed);

		System.out.println("castRay() :");
		start = Instant.now();
 		camera.castRay(scene, light);
		finish = Instant.now();
		timeElapsed = Duration.between(start, finish).toMillis();  //in millis
		System.out.println(timeElapsed);

		System.out.println("refreshRay() :");
		start = Instant.now();
 		camera.refreshRay();
		finish = Instant.now();
		timeElapsed = Duration.between(start, finish).toMillis();  //in millis
		System.out.println(timeElapsed);

		System.out.println("setImage :");
		start = Instant.now();
 		generator.setImage(camera.getWidth(), camera.getHeight(), camera.getRayList());
		finish = Instant.now();
		timeElapsed = Duration.between(start, finish).toMillis();  //in millis
		System.out.println(timeElapsed);

		/*JFrame frame = new JFrame();
		frame.setTitle("Image Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new View(camera, scene, light, camera.getWidth(), camera.getHeight()));
		frame.pack();
		frame.setVisible(true);*/
	}

}