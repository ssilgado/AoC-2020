import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author ssilgado
 *
 */
public class D4P2_PassportProcessing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> passportsInfo = getInput();
		List<Map<String, String>> passports = formatInfo(passportsInfo);
		int numberOfValidPassports = 0;
		
		for(Map<String,String> passport : passports) {
			if(hasRequiredFields(passport) && areFieldsValid(passport)) numberOfValidPassports++;
		}
		
		System.out.printf("Answer: %d\n", numberOfValidPassports);
	}
	
	public static boolean hasRequiredFields(Map<String, String> passport) {
		String[] requiredFields = new String[] {
				"byr","iyr","eyr","hgt","hcl","ecl","pid"
		};
		
		for(String field : requiredFields) {
			if(!passport.containsKey(field)) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean areFieldsValid(Map<String, String> passport) {
		// Birth Year Validation
		int byr = Integer.parseInt(passport.get("byr"));
		if(byr < 1920 || byr > 2002) {
			return false;
		}
		
		// Issue Year Validation
		int iyr = Integer.parseInt(passport.get("iyr"));
		if(iyr < 2010 || iyr > 2020) {
			return false;
		}
		
		// Issue Year Validation
		int eyr = Integer.parseInt(passport.get("eyr"));
		if(eyr < 2020 || eyr > 2030) {
			return false;
		}
		
		// Height Validation
		String hgtStrValue = passport.get("hgt");
		String units = hgtStrValue.substring(hgtStrValue.length()-2, hgtStrValue.length());
		if(!units.equals("cm") && !units.equals("in")) {
			return false;
		}
		int hgt = Integer.parseInt(hgtStrValue.substring(0,hgtStrValue.length()-2));
		switch(units) {
		case "cm":
			if(hgt < 150 || hgt > 193) {
				return false;
			}
			break;
		case "in":
			if(hgt < 59 || hgt > 76) {
				return false;
			}
			break;
		}
		
		// Hair Color Validation
		char[] validChars = "0123456789abcdef".toCharArray();
		Arrays.sort(validChars);
		String hclStrValue = passport.get("hcl");
		if(hclStrValue.charAt(0) != '#') {
			return false;
		}
		char[] hcl = hclStrValue.substring(1, hclStrValue.length()).toCharArray();
		for(char c : hcl) {
			if(Arrays.binarySearch(validChars, c) < 0) {
				return false;
			}
		}
		
		// Eye Color Validation
		String[] validHairColors = new String[] {
				"amb","blu","brn","gry","grn","hzl","oth"
		};
		Arrays.sort(validHairColors);
		String ecl = passport.get("ecl");
		if(Arrays.binarySearch(validHairColors, ecl) < 0) {
			return false;
		}
		
		// Passport ID Validation
		String pidStrValue = passport.get("pid");
		if(pidStrValue.length() != 9) {
			return false;
		}
		char[] pid = pidStrValue.toCharArray();
		for(char c : pid) {
			if(!Character.isDigit(c)) {
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
				tempPassport = tempPassport + " " + line;
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
	
	public static List<Map<String, String>> formatInfo(List<String> passportsInfo){
		List<Map<String, String>> processedInfo = new ArrayList<Map<String, String>>();
		
		for(String passportInfo : passportsInfo) {
			Map<String, String> infoMapping = new HashMap<String,String>();
			String[] infoSplit = passportInfo.trim().split(" ");
			for(String info : infoSplit) {
				String[] parts = info.split(":");
				infoMapping.put(parts[0], parts[1]);
			}
			processedInfo.add(infoMapping);
		}
		
		return processedInfo;
	}
}
