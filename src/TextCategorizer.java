import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;


/**
 * @author victor.sandoval
 * 
 * TODO This is not finished. This program should be able to process a text file in the following format:
 * 	A->B
	A->C
	D->E
	D->F->G
	D->F->H
	
	And create categories based on this text file. For example: A is a supercategory, while B is a subcategory of A.
	C is also a subcategory of C. D is separate supercategory. F is a subcategory that contains a third level subcategory G, and H.
 *
 */
public class TextCategorizer {
	
	private static String filePath = "C:/DEV/tempCategories.txt";
	private static String delimiter = "->"; 
	
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
		processLines(lines);
	}
	
	public static void processLines(String[] lines){
		ArrayList<MyCategory> categoriesList = new ArrayList<MyCategory>();
		MyCategory parentCategory = null;
		MyCategory previousCategory = null;
		int subcategoryLevel = 0;
		//Iterate all lines
		for (int i = 0; i < lines.length; i++) {
			//Split this line
			String[] parts = lines[i].split(delimiter);
			//Save the parent category
			String left = "";
			
			//Iterate all parts of this line
			for (int j = 0; j < parts.length; j++) {
				left = j>1 ? parts[parts.length-2] : parts[0+subcategoryLevel];
				if (null != parentCategory){
					//Parent name different than this category?
					if(!StringUtils.equals(parentCategory.getName(), parts[j])){
						//Is the parentTextCategory equal to current category?
						if(StringUtils.equals(parentCategory.getName(), left)){
								//add this category to this parent category
								previousCategory = parentCategory.addCategory(parts[j]);
						//found new parent category
						}else{
							if(j<2 && left != parts[j]){
								//add category structure to list
								categoriesList.add(parentCategory);
								//start new category structure
								parentCategory = new MyCategory(null, parts[j]);
							}else{
								//found new category but it is still part of this structure
								previousCategory.addCategory(parts[j]);
								subcategoryLevel++;
							}
							
						}
					}
				}else{
					//Very first time evaluating text file.
					parentCategory = new MyCategory(null, parts[j]);
				}
			}
			
		}
	}

}
