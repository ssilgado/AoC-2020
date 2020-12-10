import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.math.BigInteger;

/**
 * 
 */

/**
 * @author ssilgado
 *
 */
public class D3P2_TobogganTrajectory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> forestMap = getInput();
		BigInteger answer = BigInteger.ONE;
		
		List<Integer> slopes = new ArrayList<Integer>(List.of(1,1,3,1,5,1,7,1,1,2));
		for(int i = 0; i < slopes.size()-1; i = i+2) {
			BigInteger treesEncountered = new BigInteger(Integer.toString(getTotalTreesEncountered(forestMap, slopes.get(i), slopes.get(i+1))));
			
			answer = answer.multiply(treesEncountered);
		}
		
		System.out.printf("Answer: %s", answer.toString());
	}
	
	public static int getTotalTreesEncountered(List<String> forestMap, int xSlope, int ySlope) {		
		int totalTreesEncountered = 0;
		int currentXPosition = 0;
		int treeLineLength = forestMap.get(0).length();
		
		for(int i = 0; i < forestMap.size(); i = i + ySlope) {
			String treeLine = forestMap.get(i);
			
			if(treeLine.charAt(currentXPosition) == '#') {
				totalTreesEncountered++;
			}
			
			currentXPosition = (currentXPosition + xSlope) % treeLineLength;
		}
		
		return totalTreesEncountered;
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
