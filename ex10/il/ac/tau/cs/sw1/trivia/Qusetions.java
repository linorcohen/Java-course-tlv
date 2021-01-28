package il.ac.tau.cs.sw1.trivia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Qusetions {

	
	/**
	 * 
	 * Read from the questions file and create a list of Arrays of each question and her answers.
	 */
	public static List<String[]> openFileToRead(String fileName) {
		File file = new File(fileName);
		BufferedReader br;
		String line;
		List<String[]> questionsList = new ArrayList<String[]>();
		try {
		br = new BufferedReader(new FileReader(file));
		while ((line = br.readLine()) != null) {
				String[] qusetionArr = new String[5];
				qusetionArr = line.split("\t");
				questionsList.add(qusetionArr);
			}
		br.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File Not Found Exception");
		}
		catch (IOException e) {
			System.out.println("IOException");
		}
//	shuffle the list so we can get random questions order:
		Collections.shuffle(questionsList);
		return questionsList;
}
}
