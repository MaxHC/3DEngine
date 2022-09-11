package engine.pov.reader;

import java.util.ArrayList;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Paths;

public class Loader{

	private ArrayList<String> filesList;
	private String dir;

	public Loader(){
		this.filesList = new ArrayList<String>();
		this.dir = "..\\saves\\";
		this.getAllFile();
	}

	public ArrayList<String> getFilesList(){
		return this.filesList;
	}

	public void getAllFile(){
		File folder = new File(this.dir);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) { //if it's a file, not a directory
				this.filesList.add(listOfFiles[i].getName()); //add filename in the arraylist
			}
		}
	}

	//return the file in String
	public String load(String filename){ //filename with the extension
		String contents = "";

		try{
			contents= new String(Files.readAllBytes(Paths.get(this.dir + filename))).replaceAll("[\r\n]", "").replaceAll("	", "").replaceAll("	", ""); //replaceAll() delete all the line jump, tabs and spaces
		} catch (IOException e){
			System.out.println(e);
		}

		return contents;
	}

}