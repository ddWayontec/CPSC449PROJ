import java.io.FileNotFoundException;

//package assignment1;

public class Main {

	public static void main(String[] args) throws Exception {
	
		String inputFileName = "minimalisticexample.txt";
		String outputFileName = "";
		int[][] array = new int[8][8];
		  int logicalArray[][] =
			    {
			        {82, -1, -1, -1},
			        {-1, 37, -1, -1},
			        {-1, -1,  5, -1},
			        { -1,  -1, -1, 23}
			    };
		try {
			inputFileName = args[0];
			outputFileName = args[1];
		} 
		catch(Exception e) {
			System.out.println("ERROR: retry with valid argument parameters INPUTFILE OUPUTFILE");
			System.exit(0);
		}
		
		//PenaltyMatrixRead setup = new PenaltyMatrixRead();
		//setup.openAndParse(array, inputFileName);
		SoftConstraints soft = new SoftConstraints();
		System.out.println(soft.CalcFinalPenaltyValue(logicalArray, inputFileName));
		
			
	}
}
		
