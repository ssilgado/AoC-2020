import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author ssilgado
 *
 */
public class D2P2_PasswordPhilosophy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String[]> passwordList = getInput();
		int validPasswords = 0;

		for(String[] currentPasswordLine: passwordList) {
			int indexOne = Integer.parseInt(currentPasswordLine[0])-1;
			int indexTwo = Integer.parseInt(currentPasswordLine[1])-1;
			char targetCharacter = currentPasswordLine[2].charAt(0);
			String password = currentPasswordLine[3];
			
			if(isPasswordValid(indexOne, indexTwo, targetCharacter, password)) {
				validPasswords++;
			}
		}
		
		System.out.printf("Answer: %d", validPasswords);
	}
	
	public static boolean isPasswordValid(int indexOne, int indexTwo, char target, String password) {
		if(password.charAt(indexOne) == password.charAt(indexTwo)) {
			return false;
		} else if(password.charAt(indexOne) == target || password.charAt(indexTwo) == target) {
			return true;
		}
		
		return false;
	}

	public static List<String[]> getInput(){
		String filePath = "./input_files/d2p1_input.txt";
		File inputFile = new File(filePath);
		
		Scanner input;
		try {
			input = new Scanner(inputFile);
		} catch(Exception e) {
			System.out.println("An error occured while reading input file");
			throw new RuntimeException(e);
		}
		
		List<String[]> result = new ArrayList<String[]>();
		while(input.hasNextLine()) {
			String line = input.nextLine().trim();
			
			String[] tempResults = line.split("-|\\s");
			result.add(tempResults);
		}
		input.close();
		
		return result;
	}
}
