import java.io.FileNotFoundException;

//package assignment1;

public class Main {

	public static void main(String[] args) {
	
		String inputFileName = "minimalisticexample.txt";
		String outputFileName = "";
		int[][] penaltyMatrix = new int[8][8];
		int[] forcedPartialAssignment = new int[8];
		int[] forbiddenMachine = new int[8];
		
		try {
			inputFileName = args[0];
			outputFileName = args[1];
		} 
		catch(Exception e) {
			System.out.println("ERROR: retry with valid argument parameters INPUTFILE OUPUTFILE");
			System.exit(0);
		}
		
		PenaltyMatrixRead setup = new PenaltyMatrixRead();
		setup.openAndParse(penaltyMatrix, forcedPartialAssignment, forbiddenMachine,inputFileName);
		System.out.println("done");
			
	}
}
		


