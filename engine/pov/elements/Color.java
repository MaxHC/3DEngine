package engine.pov.elements;
import java.util.ArrayList;

public class Color{

	private ArrayList<Integer> rgb;

	public Color(ArrayList<Integer> rgb){
    this.rgb = rgb;
  }

  public Color(int r, int g, int b){
    this.rgb = new ArrayList<Integer>();
    this.rgb.add(r);
    this.rgb.add(g);
    this.rgb.add(b);
  }


	public void setRGB(ArrayList<Integer>rgb){
   	this.rgb = rgb ;
  }

 	public ArrayList<Integer>getRGB(){
 		return this.rgb;
 	}

 	public String toString(){
 	  return "color<" + this.rgb.get(0) + ","+ this.rgb.get(1) + ","+ this.rgb.get(2) + ">";
 	}
}
