package engine.pov.elements;

public class Light{
	
	public Point position;

	public Light(Point position){
		this.position = position;
	}

	public Point getPosition(){
		return this.position;
	}

	public void setCenter(Point position){
		this.position = position;
	}

	public String toString(){
		return "light<" + this.position.toString() + ">";
	}

}