import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author ssilgado
 *
 */
public class D3P1_TobogganTrajectory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> forestMap = getInput();
		
		int totalTreesEncountered = 0;
		int currentXPosition = 0;
		int treeLineLength = forestMap.get(0).length();
		for(String treeLine: forestMap) {
			if(treeLine.charAt(currentXPosition) == '#') {
				totalTreesEncountered++;
			}
			
			currentXPosition = (currentXPosition + 3) % treeLineLength;
		}
		
		System.out.printf("Answer: %d", totalTreesEncountered);
	}

	public static List<String> getInput(){
		String filePath = "./input_files/d3p1_input.txt";
		File inputFile = new File(filePath);
		
		Scanner input;
		try {
			input = new Scanner(inputFile);
		} catch(Exception e) {
			System.out.println("An error occured while reading input file");
			throw new RuntimeException(e);
		}
		
		List<String> result = new ArrayList<String>();
		while(input.hasNextLine()) {
			String line = input.nextLine().trim();
			result.add(line);
		}
		input.close();
		
		return result;
	}
}
