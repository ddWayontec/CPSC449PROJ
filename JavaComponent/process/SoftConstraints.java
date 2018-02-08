package process;


public class SoftConstraints{
	
	public int CalcFinalPenaltyValue(String[][] penaltyArray, int[][] logicalArray) throws Exception {
		
		int FinalPenaltyValue = CalcPenaltyFromLogical(logicalArray);
	 	
		String task1;
	 	String task2;
	 	
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
	
	private int CalcPenaltyFromLogical(int[][] logicalArray) {
		int FinalPenaltyValue = 0;
		for(int i = 0; i < logicalArray.length; i++){
			for(int j = 0; j < logicalArray[i].length; j++){
				if (logicalArray[i][j] != -1){
					FinalPenaltyValue = FinalPenaltyValue + logicalArray[i][j];}}}
		return FinalPenaltyValue;
	}
	
	private int changeStrtoInt(String str) {
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
