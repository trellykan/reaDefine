import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Parser {
	
	private BufferedReader reader;
	
	// Retrieves all the unique words from the input file by removing duplicates
	public Set<String> getUniqueWords(File filename) throws IOException {
		String line = null;
		Set<String> uniqueWords = new HashSet<>();
		try {
			reader = new BufferedReader(new FileReader(filename));
			while ((line = reader.readLine()) != null) {
				String [] words = line.toLowerCase()
							.replaceAll("[^a-zA-Z ]", "") 
							.trim().split(" ");
				uniqueWords.addAll(Arrays.asList(words));
			}
		} catch (IOException e) {
			System.out.println("File Not Found!");
		}
		reader.close();
		return uniqueWords;
	}
	public int[] frequencyTable(Set<String> words, File filename) throws IOException{
		String line = null;
		String[] unique = new String[words.size()];
		unique = words.toArray(unique);
		int[] dups = new int[unique.length];
		try {
			reader = new BufferedReader(new FileReader(filename));
			while ((line = reader.readLine()) != null) {
				String [] word = line.toLowerCase()
							.replaceAll("[^a-zA-Z ]", "") // takes in one line of text at time
							.trim().split(" ");
				for(int x=0;x<unique.length;x++){
					dups[x]=dups[x]+contains(unique[x],word);
				}
			}
		} catch (IOException e) {
			System.out.println("File Not Found!");
		}
		reader.close();
		return dups;
	}
	
	private int contains(String unique, String[] words){
		int numOfDup = 0;
		for(int x=0;x<words.length;x++){
			if(words[x].equals(unique)){
				numOfDup++;
			}
		}
		return numOfDup;
		
	}
	// Removes all stop words listed in the 1-1000.txt (most common English words) text file
	public void removeCommonWords(Set<String> words) throws IOException{
		File stopwords = new File("1-1000.txt");
		reader = new BufferedReader(new FileReader(stopwords));
		String line = null;
		while ((line = reader.readLine()) != null) {
			if (words.contains(line)) {
				words.remove(line);
			}
		}
		reader.close();
	}
}
