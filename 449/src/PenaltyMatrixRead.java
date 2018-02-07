import java.io.*;

public class PenaltyMatrixRead {
	
	public void openAndParse(int[][] penaltyMatrix, char[] forcedPartialAssignment, char[] forbiddenMachine,String fileName) {
		
		String line = null;
		char[] lineArray;
		boolean lastElement = false;
		int penaltyColumn;
		String penaltyBuffer;
		int machine = 0;
		int task = 0;
		int machineidx;
		char taskLetter;
		
		//attempt to open and parse the penalty matrix of the file
		try {
            FileReader fileReader = new FileReader(fileName);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            //continue to read lines until the end of the file
            while((line = bufferedReader.readLine()) != null) {
            	
            	//replacing all white space on the line with the empty string
            	line = line.replaceAll("\\s", "");
            	
            	if (line.equals("forcedpartialassignment:")) {
            		
            		while (!(line = bufferedReader.readLine().replaceAll("\\s", "")).equals("forbiddenmachine:")) {
            			
                		if (line.equals("")) {
                			continue;
                		}
                		else {
                			lineArray = line.toCharArray();
                			machineidx = Character.getNumericValue(lineArray[1]);
                			taskLetter = lineArray[3];
                			forcedPartialAssignment[machineidx] = taskLetter;
                		}
            		}
            		
            		
            	}
            	
            	if (line.equals("forbiddenmachine:")) {
            		
            		while (!(line = bufferedReader.readLine().replaceAll("\\s", "")).equals("too-neartasks:")) {
            			
            			if (line.equals("")) {
                			continue;
                		}
                		else {
                			lineArray = line.toCharArray();
                			machineidx = Character.getNumericValue(lineArray[1]);
                			taskLetter = lineArray[3];
                			forbiddenMachine[machineidx] = taskLetter;
            		}
            	}
            	
            	//checking if we have reaches the line before the penalty matrix
            	if (line.equals("machinepenalties:")) {
            		
            		//row of the 2D array
            		while (machine < 8) {
            			
            			line = bufferedReader.readLine();
            			
            			if (line.replaceAll("\\s", "").equals("")) {
            				continue;
            			}
            			
                		//convert the line to an array in order to access individual penalty value
                		lineArray = line.toCharArray();
            			
                		//reset column counter, penaltyBuffer, and task counter
                		penaltyColumn = 0;
                		penaltyBuffer = "";
                		task = 0;
                		//column of the 2D array
            			while (penaltyColumn < lineArray.length) {

            				//if the character read is a space and we have a penalty value in the buffer, we can assume
            				//that we have calculated the penalty for the current task
            				if (lineArray[penaltyColumn] == ' ' & !(penaltyBuffer.isEmpty())) {
            					penaltyMatrix[machine][task] = Integer.parseInt(penaltyBuffer);
            					penaltyBuffer = "";
            					task++;
            				}
            				//if the column read is not empty, concatenate char to current penalty string
            				else if (lineArray[penaltyColumn] != ' ') {
            					penaltyBuffer += lineArray[penaltyColumn];
            				}
            				penaltyColumn++;
            			}
            			//special case for last number
            			if (penaltyBuffer != "") {
            				penaltyMatrix[machine][task] = Integer.parseInt(penaltyBuffer);
            			}
            			machine++;
            			
            			
            		}
            		
            		
            	}
            	
            	}
            bufferedReader.close();
		}
		}
		catch (Exception e) {
			System.out.println("Error while parsing input file");
			System.exit(0);
		}
		
	

	}
}
