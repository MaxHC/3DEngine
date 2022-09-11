package engine.ui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import engine.raytracing.Ray;

public class ImageGenerator{

	//create and return the image 
	public BufferedImage setImage(int width, int height, ArrayList<Ray> rayList){

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); //create new image

		System.out.println("generating image ...");

		//for each ray
		for(Ray ray : rayList){ 
			int p = (255<<24) | (ray.getColor().getRGB().get(0)<<16) | (ray.getColor().getRGB().get(1)<<8) | ray.getColor().getRGB().get(2); //create the pixel in ARGB
			img.setRGB(ray.pixel[0], ray.pixel[1], p); //set the pixel to the image
		}

		System.out.println("generated !");

		return img; //return image created 
	}

}
