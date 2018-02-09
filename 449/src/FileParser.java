import java.io.*;
import java.util.ArrayList;

public class FileParser {

	public void openAndParse(int[][] penaltyMatrix, int[] forcedPartialAssignment, int[] forbiddenMachine,
			ArrayList<String> tooNearTasks, String fileName) {

		String line = null;
		char[] lineArray;
		int penaltyColumn;
		String penaltyBuffer;
		int machine = 0;
		int task = 0;
		int machineidx;
		char taskLetter;
		String tooNear1;
		String tooNear2;

		// attempt to open and parse the penalty matrix of the file
		try {
			FileReader fileReader = new FileReader(fileName);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// continue to read lines until the end of the file
			while ((line = bufferedReader.readLine()) != null) {

				// replacing all white space on the line with the empty string
				line = line.replaceAll("\\s", "");

				if (line.equals("forcedpartialassignment:")) {

					for (int i = 0; i < 8; i++) {
						forcedPartialAssignment[i] = -1;
					}

					while (!(line = bufferedReader.readLine().replaceAll("\\s", "")).equals("forbiddenmachine:")) {

						if (line.equals("")) {
							continue;
						} else {
							lineArray = line.toCharArray();
							machineidx = Character.getNumericValue(lineArray[1] - 1);
							taskLetter = lineArray[3];
							if (forcedPartialAssignment[machineidx] == -1) {
								switch (taskLetter) {

								case 'A':
									forcedPartialAssignment[machineidx] = 0;
									break;
								case 'B':
									forcedPartialAssignment[machineidx] = 1;
									break;
								case 'C':
									forcedPartialAssignment[machineidx] = 2;
									break;
								case 'D':
									forcedPartialAssignment[machineidx] = 3;
									break;
								case 'E':
									forcedPartialAssignment[machineidx] = 4;
									break;
								case 'F':
									forcedPartialAssignment[machineidx] = 5;
									break;
								case 'G':
									forcedPartialAssignment[machineidx] = 6;
									break;
								case 'H':
									forcedPartialAssignment[machineidx] = 7;
									break;
								default:
									System.out.println("invalid machine/task");
									System.exit(0);
								}
							} else {
								System.out.println("partial assignment error");
								System.exit(0);
							}
							// forcedPartialAssignment[machineidx] = taskLetter;
						}
					}

					for (int i = 0; i < forcedPartialAssignment.length - 1; i++) {
						for (int j = i + 1; j < forcedPartialAssignment.length; j++) {
							if (forcedPartialAssignment[i] == forcedPartialAssignment[j]
									& forcedPartialAssignment[i] != -1 & forcedPartialAssignment[j] != -1) {
								System.out.println("partial assignment error");
								System.exit(0);
							}
						}
					}

				}

				if (line.equals("forbiddenmachine:")) {

					for (int i = 0; i < 8; i++) {
						forbiddenMachine[i] = -1;
					}

					while (!(line = bufferedReader.readLine().replaceAll("\\s", "")).equals("too-neartasks:")) {

						if (line.equals("")) {
							continue;
						} else {
							lineArray = line.toCharArray();
							machineidx = Character.getNumericValue(lineArray[1]) - 1;
							taskLetter = lineArray[3];
							switch (taskLetter) {

							case 'A':
								forbiddenMachine[machineidx] = 0;
								break;
							case 'B':
								forbiddenMachine[machineidx] = 1;
								break;
							case 'C':
								forbiddenMachine[machineidx] = 2;
								break;
							case 'D':
								forbiddenMachine[machineidx] = 3;
								break;
							case 'E':
								forbiddenMachine[machineidx] = 4;
								break;
							case 'F':
								forbiddenMachine[machineidx] = 5;
								break;
							case 'G':
								forbiddenMachine[machineidx] = 6;
								break;
							case 'H':
								forbiddenMachine[machineidx] = 7;
								break;
							default:
								System.out.println("invalid machine/task");
								System.exit(0);
							}

						}
					}
				}

				if (line.equals("too-neartasks:")) {

					while (!(line = bufferedReader.readLine().replaceAll("\\s", "")).equals("machinepenalties:")) {

						if (line.equals("")) {
							continue;
						} else {
							lineArray = line.toCharArray();
							if (lineArray[1] < 65 | lineArray[1] > 72 | lineArray[3] < 65 | lineArray[3] > 72) {
								System.out.println("invalid machine/task");
								System.exit(0);
							}
							tooNear1 = Character.toString(lineArray[1]);
							tooNear2 = Character.toString(lineArray[3]);
							tooNearTasks.add(tooNear1 + tooNear2);
						}

					}
				}

				// checking if we have reaches the line before the penalty
				// matrix
				if (line.equals("machinepenalties:")) {

					// row of the 2D array
					while (machine < 8) {

						line = bufferedReader.readLine();

						if (line.replaceAll("\\s", "").equals("")) {
							continue;
						}

						// convert the line to an array in order to access
						// individual penalty value
						lineArray = line.toCharArray();

						// reset column counter, penaltyBuffer, and task counter
						penaltyColumn = 0;
						penaltyBuffer = "";
						task = 0;
						// column of the 2D array
						while (penaltyColumn < lineArray.length) {

							// if the character read is a space and we have a
							// penalty value in the buffer, we can assume
							// that we have calculated the penalty for the
							// current task
							if (lineArray[penaltyColumn] == ' ' & !(penaltyBuffer.isEmpty())) {
								penaltyMatrix[machine][task] = Integer.parseInt(penaltyBuffer);
								penaltyBuffer = "";
								task++;
							}
							// if the column read is not empty, concatenate char
							// to current penalty string
							else if (lineArray[penaltyColumn] != ' ') {
								penaltyBuffer += lineArray[penaltyColumn];
							}
							penaltyColumn++;
						}
						// special case for last number
						if (penaltyBuffer != "") {
							penaltyMatrix[machine][task] = Integer.parseInt(penaltyBuffer);
						}
						machine++;

					}

				}

			}
			bufferedReader.close();
		} catch (ArrayIndexOutOfBoundsException exception) {
			System.out.println("invalid machine/task");
			exception.printStackTrace();
			System.exit(0);
		}

		catch (Exception e) {
			System.out.println("Error while parsing input file");
			e.printStackTrace();
			System.exit(0);
		}

	}
}
