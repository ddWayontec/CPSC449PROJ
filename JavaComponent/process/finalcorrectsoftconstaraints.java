import java.util.ArrayList;

public class SoftConstraints{
	
	public void AddFinalPenaltyValue(ArrayList<String> nearArray, int[][] penaltyArray) throws Exception {
		
	 	//store (task1, task2,additionalPenalty)
		String task1;
	 	String task2;
	 	
	 	int index = 0;
	 	
		 	//keep track of the index value which should be number of triples read * 2
	 		if (index < nearArray.size()){
		 	
		 	//get the tasks from each triple
		 	task1 = nearArray.get(index);
		 	index++;
		 	task2 = nearArray.get(index);
		 	index++;
		 	//change the task alphabet value to an int 
		 	int posTask1 = changeStrtoInt(task1);
		 	int posTask2 = changeStrtoInt(task2);
		 	
		 	//get the value of the additional penalty from the triple
		 	int additionalPenalty = Integer.parseInt(nearArray.get(index));
		 	for( int i = 0; i < penaltyArray.length; i++) {
				//like a circular array, machine 1 and machine 8
		 		if (i == 0 ){
					//adds the additional penalty, if triple = (A,B, 8) adds additional penalty to any machinei and machinei+1
		 			//that uses task a and b, if it's - assnume the machine does not do the task in that index point
					if(((penaltyArray[i][posTask1] != -1) && (penaltyArray[i+1][posTask2] != -1))){
						int temp = penaltyArray[i][posTask1];
						penaltyArray[i][posTask1] = temp + additionalPenalty;
						temp = penaltyArray[i+1][posTask2];
						penaltyArray[i+1][posTask2] = temp + additionalPenalty;
					}	
					
					else if((penaltyArray[i][posTask1] != -1) && (penaltyArray[penaltyArray.length -1][posTask2] != -1)) {
						int temp = penaltyArray[i][posTask1];
						penaltyArray[i][posTask1] = temp + additionalPenalty;
						temp = penaltyArray[penaltyArray.length -1][posTask2];
						penaltyArray[penaltyArray.length -1][posTask2] = temp + additionalPenalty;
					}
					}
		 		//like a circular array, machine 1 and machine 8
				else if (i == (penaltyArray.length-1)){
					if(((penaltyArray[i][posTask1] != -1) && (penaltyArray[0][posTask2] != -1))){
						int temp = penaltyArray[i][posTask1];
						penaltyArray[i][posTask1] = temp + additionalPenalty;
						temp = penaltyArray[0][posTask2];
						penaltyArray[0][posTask2] = temp + additionalPenalty;
					}
					else if((penaltyArray[i][posTask1] != -1) && (penaltyArray[i-1][posTask2] != -1)) {
						int temp = penaltyArray[i][posTask1];
						penaltyArray[i][posTask1] = temp + additionalPenalty;
						temp = penaltyArray[i -1][posTask2];
						penaltyArray[i -1][posTask2] = temp + additionalPenalty;
				}}
		 		//all other values machine 2 to 7, like a regular array,
				else{
				if(((penaltyArray[i][posTask1] != -1) && (penaltyArray[i+1][posTask2] != -1))) {
					int temp = penaltyArray[i][posTask1];
					penaltyArray[i][posTask1] = temp + additionalPenalty;
					temp = penaltyArray[i+1][posTask2];
					penaltyArray[i+1][posTask2] = temp + additionalPenalty;
				}
				else if((penaltyArray[i][posTask1] != -1) && (penaltyArray[i-1][posTask2] != -1)) {
					int temp = penaltyArray[i][posTask1];
					penaltyArray[i][posTask1] = temp + additionalPenalty;
					temp = penaltyArray[i-1][posTask2];
					penaltyArray[i-1][posTask2] = temp + additionalPenalty;
				}
				
				}
		
		 	}
				
				}index++;
	 	}
	
		 	//gets the logically computed array with the best cases and calculates the final overall penalty value 
			//as long as one machine is only assigned to one task!!
	public int CalcPenaltyFromLogical(int[][] logicalArray) {
		int FinalPenaltyValue = 0;
		for(int i = 0; i < logicalArray.length; i++){
			for(int j = 0; j < logicalArray[i].length; j++){
				if (logicalArray[i][j] != -1){
					FinalPenaltyValue = FinalPenaltyValue + logicalArray[i][j];}}}
		return FinalPenaltyValue;
	}
	//changes the values A - Z to a string with values 0 - 25
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
