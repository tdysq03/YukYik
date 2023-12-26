package game;

public class ExerciseData {
	
	private String	number,name,firstStepPath,firstDirection,secondStepPath,secondDirection;
	
	public ExerciseData(String number,String name,String firstStepPath, String firstDirection,String secondStepPath,String secondDirection) {
		this.number = number;
		this.name = name;
		this.firstStepPath = firstStepPath;
		this.firstDirection = firstDirection;
		this.secondStepPath = secondStepPath;
		this.secondDirection = secondDirection;
		}
	
	public int getNumber(){
		return Integer.parseInt(number);
		}
	
	public String getName(){
		return name;
		}
	
	public String getFirstStepPath(){
		return firstStepPath;
		}
	
	public String getFirstDirection(){
		return firstDirection;
		}
	
	public String getSecondStepPath(){
		return secondStepPath;
		}
	
	public String getSecondDirection(){
		return secondDirection;
		}
}