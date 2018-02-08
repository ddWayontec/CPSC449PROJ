import java.util.ArrayList;

import commons.Config;
import parse.FileParser;
import process.SoftConstraints;
import task.Tasker;

public class MySystem {

	public static void main(String[] args) throws Exception {
		
		String inputFileName = null;
		String outputFileName = null;
		
		try {
			inputFileName = args[0];
			outputFileName = args[1];
		} 
		catch(Exception e) {
			System.out.println("ERROR: Retry with valid argument for parameters INPUTFILE OUPUTFILE");
			System.exit(0);
		}

		int[][] penaltyMatrix = new int[Config.GLOBAL_SIZE][Config.GLOBAL_SIZE];
		int[] forcedPartialAssignment = new int[Config.GLOBAL_SIZE];
		int[] forbiddenMachine = new int[Config.GLOBAL_SIZE];	
		String[] nearlineArray = new String[3];
		String[][] wholeNearArray = new String[100][3];
		ArrayList<String> tooNearTasks = new ArrayList<String>();
		
		FileParser setup = new FileParser();
		setup.openAndParse(penaltyMatrix, forcedPartialAssignment, forbiddenMachine, tooNearTasks, nearlineArray, wholeNearArray, inputFileName);
	
		SoftConstraints soft = new SoftConstraints();
		soft.CalcFinalPenaltyValue(wholeNearArray, penaltyMatrix);
		Tasker.optimize(penaltyMatrix);
	    System.out.println(penaltyMatrix[0][0]);
	}

}
