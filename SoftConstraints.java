//import java.awt.List;
import java.io.BufferedReader;
//import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//import java.io.PrintStream;
//import java.util.ArrayList;

public class SoftConstraints{
	//public int FinalPenaltyValue = 0;
	//reads too-near penalties
	public String[][] readNearPenalty(String fileName) throws Exception{
		
		String[] lineArray = new String[3];
		String[][] wholeNearArray = new String[10][3];
		String line = null;
		BufferedReader br = null;
		try {
	          br = new BufferedReader(new FileReader(fileName));
	            		}
		catch(Exception e){
			System.out.println("Could not find file");
			System.exit(0);
		}
		try {
			while((line = br.readLine()) != null) {
				if (line.equals("too-near penalties:")) {
					int i = 0;
					while((line = br.readLine()) != null){
						line = line.replace("(", "");
						line = line.replace(")", "");
					 	line = line.replaceAll("\\s", "");
						lineArray = line.split(",");
				
						if ((correctTask(lineArray[0]) != "True") | (correctTask(lineArray[1]) != "True")){
							throw new Exception("Invalid Task");
							}
	
							wholeNearArray[i] = lineArray;
							i++;
							//System.out.println("here & finished");		
					}
							
											
		            
		}}}
			
		catch(IOException ex) {
			System.out.println("Error while parsing input file");
		}br.close();
		return wholeNearArray;
		
	}
		
 
	public String correctTask(String task) {
		String[] useToCheck = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
		for (int i= 0; i < useToCheck.length; i++) {
			if (task.equals(useToCheck[i])) {
					return "True";
				} }
		return "False";
				}
	
	public int CalcFinalPenaltyValue(int[][] logicalArray, String fileName) throws Exception {
		//machine == wholeNearArray[i] or logicalArray[i]
		//task == wholeNearArray[i][i] or logicalArray[i][i]
	
		String[][] penaltyArray = readNearPenalty(fileName);
		int FinalPenaltyValue = CalcPenaltyFromLogical(logicalArray);
	 	
		String task1;
	 	String task2;
	 	//int additionalPenalty = 0;
	 	
	 	for (int j = 0; j < penaltyArray.length; j++){
		 	if (penaltyArray[j][0] != null){
		 	task1 = penaltyArray[j][0];
		 	task2 = penaltyArray[j][1];
		 	
		 	int posTask1 = changeStrtoInt(task1);
		 	int posTask2 = changeStrtoInt(task2);
		 	
		 	int additionalPenalty = Integer.parseInt(penaltyArray[j][2]);
		 	
			for(int i = 0; i < logicalArray.length; i++) {
				if (i == 0 ){
					if(((logicalArray[i][posTask1] != -1) && (logicalArray[i+1][posTask2] != -1)) ||((logicalArray[i][posTask1] != -1) && (logicalArray[logicalArray.length -1][posTask2] != -1)))
						FinalPenaltyValue = FinalPenaltyValue + additionalPenalty;
					}
				else if (i == penaltyArray.length){
					if(((logicalArray[i][posTask1] != -1) && (logicalArray[0][posTask2] != -1)) ||((logicalArray[i][posTask1] != -1) && (logicalArray[i-1][posTask2] != -1)))
						FinalPenaltyValue = FinalPenaltyValue + additionalPenalty;
				}
			else{
				if(((logicalArray[i][posTask1] != -1) && (logicalArray[i+1][posTask2] != -1)) ||((logicalArray[i][posTask1] != -1) && (logicalArray[i-1][posTask2] != -1))) {
					FinalPenaltyValue = FinalPenaltyValue + additionalPenalty;
				}}}}
		 	else {
		 		break;
		 	}
	}
	 	return FinalPenaltyValue;
	 	}
	
	public int CalcPenaltyFromLogical(int[][] logicalArray) {
		int FinalPenaltyValue = 0;
		for(int i = 0; i < logicalArray.length; i++){
			for(int j = 0; j < logicalArray[i].length; j++){
				if (logicalArray[i][j] != -1){
					FinalPenaltyValue = FinalPenaltyValue + logicalArray[i][j];}}}
		return FinalPenaltyValue;
	}
	
	public int changeStrtoInt(String str) {
		  char[] chvalue  = str.toCharArray();
		    for(char c : chvalue)
		    {
		        int temp = (int)c;
		        int temp_integer = 64; 
		if(temp<=90 & temp>=65)
		    return ((temp-temp_integer)-1);
		    }
		    return 0;
	}
}	