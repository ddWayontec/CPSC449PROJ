package commons;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class OutputWriter {
	// File IO
	private static PrintWriter pw = null;
	private static FileWriter fw = null;

	// Write to a file if player has achieved a highScore
	public static void writeFile(int[] solutionArray, int quality) {
		try {
			// Open file we are going to write to
			fw = new FileWriter("myoutput.txt");
			pw = new PrintWriter(fw);

			pw.print("\"Solution\"");
			for (int j = 0; j < 4; j++)
				pw.print(" " + solutionArray[j]);

			pw.print("\"; Quality:\" " + quality);
			fw.close(); // Closes file once finished writing
			
		} catch (IOException e) {
			System.out.println("Could not write to file");
		}
	}
}
