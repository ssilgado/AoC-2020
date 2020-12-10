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
public class D4P1_PassportProcessing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> passports = getInput();
		int numberOfValidPassports = 0;
		
		for(String passport : passports) {
			if(isValidPassport(passport)) numberOfValidPassports++;
		}
		
		System.out.printf("Answer: %d\n", numberOfValidPassports);
	}
	
	public static boolean isValidPassport(String passport) {
		String[] requiredFields = new String[] {
				"byr","iyr","eyr","hgt","hcl","ecl","pid"
		};
		
		for(String field : requiredFields) {
			if(!passport.contains(field)) {
				return false;
			}
		}
		
		return true;
	}

	public static List<String> getInput(){
		String filePath = "./input_files/d4p1_input.txt";
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
			String tempPassport = "";
			boolean end = false;
			while(!line.equals("") && !end) {
				tempPassport = tempPassport + line;
				if(input.hasNextLine()) {
					line = input.nextLine().trim();
				} else {
					end = true;
				}
				
			}
			
			result.add(tempPassport);
				
		}
		input.close();
		
		return result;
	}
}
