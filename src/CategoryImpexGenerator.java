import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class CategoryImpexGenerator {
	private static String filePath = "C:/DEV/tempCategories.txt";
	private static String delimiter = "->"; 
	private static String NEXT_LINE = "\n";
	
	public static void main (String args[]) {
		WordFinder wf = new WordFinder();
		BufferedReader bf = wf.getBufferedReaderFromFile(filePath);
		String line = null;
		String[] lines = new String[60];
		int count=0;
		
		try {
			while((line = bf.readLine()) != null) {
				//remove whitespaces
				lines[count] = line.replaceAll("\\s", "");
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		spitHashSetImpEx(collectUniqueCategories(lines));
		
		//spitImpExLines(lines);
		
	}
	
	public static void spitImpExLines(String[] lines) {
		//Traverse lines
		for (int j = 0; j < lines.length; j++) {
			if (null != lines[j]) {
				String[] parts = lines[j].split(delimiter);
				String tempLine = "";
				//i has to be incremented parts.length-1 times. For example: For 4 categories, i would have to be incremented 3 times.
				//For example: A->B->C-D First: ;D;C Second: ;C;B Third: ;B;A
				int numberOfLevels = parts.length-1;
				if(numberOfLevels>0){
					//Impex is built out every two loops.
					int ranTwice = 0;
					//Traverse Line
					for (int i = parts.length-1; i >= 0; i--) {
						tempLine = tempLine.concat(";").concat(parts[i]);
						ranTwice++;
						if (2 == ranTwice){
							//took care of one impex line
							numberOfLevels--;
							//reset count
							ranTwice = 0;
							if (numberOfLevels>0){
								//not done yet, insert next line character
								tempLine = tempLine.concat(NEXT_LINE);
								i++;
							}
						}
					}
					System.out.println(tempLine);
				}
			}
		}
	}
	
	/**Iterates and spits out the entries in the HashSet
	 * @param hashSet
	 */
	public static void spitHashSetImpEx(Set<String> hashSet) {
		Iterator<String> it = hashSet.iterator();
		while (it.hasNext()){
			String category = it.next();
			System.out.println(";".concat(category).concat(";;;"));
		}
	}
	
	/**Converts entries in String[] to Set
	 * @param lines
	 * @return
	 */
	public static Set<String> collectUniqueCategories(String[] lines){
		HashSet<String> uniqueCategories = new HashSet<String>();
		//Traverse lines
		for (int i = 0; i < lines.length; i++) {
			if (null != lines[i]){
				String[] parts = lines[i].split(delimiter);
				//Traverse line
				for (int j = 0; j < parts.length; j++) {
					uniqueCategories.add(parts[j]);
				}
			}
		}
		return uniqueCategories;
	}
	
	
}
