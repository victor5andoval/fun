import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;


public class WordFinder {
	
	private String filePath;
	
	public WordFinder() {
		filePath="";
	}

	public BufferedReader getBufferedReaderFromFile(String pathToTextFile){
    	// The name of the file to open.
        filePath = pathToTextFile;
        FileReader fileReader = null;

        try {
            // FileReader reads text files in the default encoding.
           fileReader = new FileReader(filePath);
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filePath + "'");                
        }
        return new BufferedReader(fileReader);
    }
	
	public static void main (String args[]) {
		WordFinder wordFinder = new WordFinder();
		WordFinder wordFinder2 = new WordFinder();
		String sapField = null;
		String prodField = null;
		String[] sapFields = new String[100];
		String[] prodFields = new String [130];
		String sapTag = "<SAP>";
		
		BufferedReader sapBufferedReader = wordFinder.getBufferedReaderFromFile("C:/DEV/tempSAPFields.txt");
		BufferedReader productBufferedReader = wordFinder2.getBufferedReaderFromFile("C:/DEV/tempProductFields.txt");
		 
		int a = 0;
		int b = 0;
		try {
			while((sapField = sapBufferedReader.readLine()) != null) {
				sapFields[a] = sapField;
				a++;
			}
			while((prodField = productBufferedReader.readLine()) != null) {
				prodFields[b] = prodField;
				b++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < sapFields.length; i++) {
			for (int j = 0; j < prodFields.length; j++) {
				if (StringUtils.contains(prodFields[j], sapFields[i])){
					prodFields[j]= sapTag + prodFields[j];
				}
			}
		}
		
		for (int i = 0; i < prodFields.length; i++) {
			if(prodFields[i] != null) System.out.println(prodFields[i]);
		}	
	}
}
