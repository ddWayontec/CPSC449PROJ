package commons;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OutputWriter {
	
	public static void writeFile(int[] solutionArray, int quality) {
		try {
			
			FileWriter fw = new FileWriter("myoutput.txt");
			PrintWriter pw = new PrintWriter(fw);

			pw.print("\"Solution\"");
			for (int j = 0; j < Config.GLOBAL_SIZE; j++)
				pw.print(" " + solutionArray[j]);
			pw.print("\"; Quality:\" " + quality);
			
			fw.close(); 
			
		} catch (IOException e) {
			System.out.println("Could not write to file");
		}
	}
}
