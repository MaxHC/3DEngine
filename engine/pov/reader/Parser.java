package engine.pov.reader;

import java.util.ArrayList;

public class Parser{

	private String document;
	private ArrayList<String> itemList;

	public Parser(String document){
		this.document = document;
		this.itemList = new ArrayList<String>();
		parseItems(0);
	}

	public ArrayList<String> getItemList(){
		return this.itemList;
	}

	//parse the file into single object
	public void parseItems(int i){
		int open = 0; //counter of "<"
		int closed = 0; //counter of ">"
		String item = "";

		//get the start of the description
		while(!(Character.toString(this.document.charAt(i)).equals("<"))){ 
			item += Character.toString(this.document.charAt(i));
			i++;
		}
		item += Character.toString(this.document.charAt(i)); //add last caractere, or it'll be skip later 
		open ++; // "<" won't be iterate so add it to the counter 

		//get all the description for the items
		while(open != closed && i < this.document.length()){
			i++;
			if(Character.toString(this.document.charAt(i)).equals("<")){
				open ++; //if "<" encountered increment the counter
			} else if(Character.toString(this.document.charAt(i)).equals(">")){
				closed ++; //if ">" encountered increment the counter
			} 
			item += Character.toString(this.document.charAt(i));
		}

		//when the number of "<" = the number of ">" that's mean we got all of the descritption so add it to the list
		this.itemList.add(item);

		//redo while the document isn't totally parsed
		if(i < this.document.length()-1){
			this.parseItems(i+1);
		}
	}

	//parse the object into attribute
	public ArrayList<String> parseComponent(String item){
		ArrayList<String> parsed = new ArrayList<String>();
		String name = "";
		int i=0;
		//get name of the item 
		while(!(Character.toString(item.charAt(i)).equals("<"))){
			name += Character.toString(item.charAt(i));
			i++;
		}
		parsed.add(name); //add to the component array

		i++; //skip the "<"

		//while "<" encountered, that's mean this is the start of a new component so get it and add it the the list
		while(i<item.length() -1){
			if(Character.toString(item.charAt(i)).equals("<")){
				parsed.add(getComponent(item, i+1));
			}
			i++;
		}

		return parsed;
	}

	//get all the part between "<" and ">"
	public String getComponent(String component, int i){
		String output = "";

		//while ">" isn't encountered add the char to the string to get all the component
		while(!(Character.toString(component.charAt(i)).equals(">"))){
			output += Character.toString(component.charAt(i));
			i++;
		}

		return output;
	}
}
