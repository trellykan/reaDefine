import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set; 
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Main {
	
	public static void main (String [] args) throws IOException, SAXException, ParserConfigurationException, NullPointerException {
		//Reads in an input file
		System.out.print("Input a text file: ");
		Scanner sc = new Scanner(System.in);
		File fileName = new File(sc.nextLine());
		Parser parser = new Parser();
		
		try {
			Set<String> uniqueWords = parser.getUniqueWords(fileName);
			//make a frequency table
			int[] dups = parser.frequencyTable(uniqueWords, fileName);
			File table = new File("freqTable.txt");
			PrintWriter outAgain = new PrintWriter(table);
			for(int x=0;x<dups.length;x++){
				outAgain.println(dups[x]+" " +uniqueWords.toArray()[x]);
			}
			outAgain.close();
			//end frequency table
			
			//Removes all stopwords in "1-1000.txt"
			parser.removeCommonWords(uniqueWords);
			
			HttpUtility util = new HttpUtility();
			
			//Creates a text file of defined words
			File definedWords = new File("definedWords.txt");
			PrintWriter out = new PrintWriter(definedWords);
			
			//Gets definition for all words
			//If there is no definition, the user can input his/her own definition
			for (String word : uniqueWords) {
				String xml = util.get(word);
				String def = util.xmlDef(xml);
				if (def.equals("no definition")) {
					System.out.println("Please input a definition for: " + word);
					def = sc.nextLine();
					System.out.println();
				}
				out.println(word + " - " + def);
			}
			out.close();
		
			//Checks to see if there is at least one word to make a quiz
			if (uniqueWords.size() > 0) {
				ScrapPaper sp = new ScrapPaper(definedWords);
				sp.makeQuiz();
			}
			else {
				System.out.println("There are no unique words in the file");
			}
			sc.close();
		
		} catch (NullPointerException e) {
			System.out.println("Please enter a proper file");
		} catch (IllegalArgumentException e) {
			System.out.println("There are no unique words int he file");
		}
		
	}
}
