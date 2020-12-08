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
public class D1P2_ReportRepair {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> expenseReportValues = getInput();
		Collections.sort(expenseReportValues, Collections.reverseOrder());
		int expenseReportSize = expenseReportValues.size();
		
		for(int i = 0; i < expenseReportSize-1; i++) {
			for(int j = expenseReportSize-1; j > i+1; j--) {
				for(int k = j-1; k > i; k--) {
					if(expenseReportValues.get(i)+expenseReportValues.get(j) + expenseReportValues.get(k) == 2020) {
						System.out.printf("Answer: %d\n", expenseReportValues.get(i) * expenseReportValues.get(j) * expenseReportValues.get(k));
					} else if(expenseReportValues.get(i)+expenseReportValues.get(j)+expenseReportValues.get(k) > 2020) {
						k = 0;
					}
				}
			}
		}
	}
	
	public static List<Integer> getInput(){
		String filePath = "./input_files/d1p1_input.txt";
		File inputFile = new File(filePath);
		
		Scanner input;
		try {
			input = new Scanner(inputFile);
		} catch(Exception e) {
			System.out.println("An error occured while reading input file");
			throw new RuntimeException(e);
		}
		
		List<Integer> result = new ArrayList<Integer>();
		while(input.hasNextLine()) {
			String line = input.nextLine().trim();
			int value = Integer.parseInt(line);
			
			result.add(value);
		}
		input.close();
		
		return result;
	}

}
