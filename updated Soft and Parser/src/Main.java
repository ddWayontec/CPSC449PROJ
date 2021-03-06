import java.io.FileNotFoundException;
import java.util.ArrayList;

//package assignment1;

public class Main {

	public static void main(String[] args) throws Exception {
	
		String inputFileName = "minimalisticexample.txt";
		String outputFileName = "";
		String[] nearlineArray = new String[3];
		String[][] wholeNearArray = new String[100][3];
		int[][] penaltyMatrix = new int[8][8];
		int[] forcedPartialAssignment = new int[8];
		int[] forbiddenMachine = new int[8];
		ArrayList<String> tooNearTasks = new ArrayList<String>();
		
		int logicalArray[][] = {
				{9, -1, -1, -1},
		        {-1, 4, -1, -1},
		        {-1, -1, 1, -1},
		        {-1, -1, -1, 4}
		};
		try {
			inputFileName = args[0];
			outputFileName = args[1];
		} 
		catch(Exception e) {
			System.out.println("ERROR: retry with valid argument parameters INPUTFILE OUPUTFILE");
			System.exit(0);
		}
		
		FileParser setup = new FileParser();
		setup.openAndParse(penaltyMatrix, forcedPartialAssignment, forbiddenMachine, tooNearTasks, nearlineArray, wholeNearArray, inputFileName);
		SoftConstraints soft = new SoftConstraints();
		System.out.println("Final Penalty Value/ Quality = " + soft.CalcFinalPenaltyValue(wholeNearArray, logicalArray));
		System.out.println("done");
			
	}
}
	
