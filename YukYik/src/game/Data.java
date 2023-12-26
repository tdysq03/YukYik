package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

public class Data {
	
	private ArrayList<ExerciseData> exerciseData;
	private ArrayList<String> dataFromFile;
	private int[] queue;   
	private String current;
	
	public Data() {
		current = System.getProperty("user.dir");
	}

	public ArrayList<String> readDataFromFile(String fileName) throws IOException {
		dataFromFile = new ArrayList<String>();
		File inFile = new File(current+"\\data\\"+fileName);
		FileReader fileReader = new FileReader(inFile);
		BufferedReader bufReader = new BufferedReader(fileReader);
		String line = bufReader.readLine();
		while(line!=null) {
			dataFromFile.add(line);
			line = bufReader.readLine();
			}
		bufReader.close();	
		return dataFromFile;
	}
	
	public ArrayList<ExerciseData> getExerciseData() throws IOException {		
		ArrayList<String> data = new ArrayList<String>();	
		data = readDataFromFile("ExerciseData.txt");		
		exerciseData = new ArrayList<ExerciseData>(); 
		String[] arrayOfStr;
		for(int i = 0;i<data.size();i++) {
			arrayOfStr = data.get(i).split("\t");
			exerciseData.add(new ExerciseData(arrayOfStr[0],arrayOfStr[1],arrayOfStr[2],arrayOfStr[3],arrayOfStr[4],arrayOfStr[5]));
		   }
		return exerciseData;			
		}
	
	public int[] getQueue() throws IOException {
		int quantity = getExerciseData().size();
		ArrayList<Integer> number = new ArrayList<Integer>();	  
		for(int i = 0;i<quantity;i++) {
			number.add(i);
		   }
		queue = new int[quantity]; 
		Random randNum = new Random();
		for(int i = 0;i<quantity;i++) {
			int index = randNum.nextInt(number.size());
			queue[i] = number.get(index);
			number.remove(index);
		   	}
		return queue;
	}
	
	public ArrayList<String> getHowtoplay() throws IOException {
		ArrayList<String> data = new ArrayList<String>();	
		data = readDataFromFile("HowToPlay.txt");	
		return data;
	}

	public void addPlayerData(String playerData) throws IOException {
		Writer output;
		output = new BufferedWriter(new FileWriter(current+"\\data\\"+"ScoreBoard.txt", true));
		output.append(playerData);
		output.close();
	}
	
	public  ArrayList<String> getPlayerData() throws IOException {
		ArrayList<String> data = new ArrayList<String>();	
		data = readDataFromFile("ScoreBoard.txt");
		return data;
	}
}