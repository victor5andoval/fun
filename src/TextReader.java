import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;


public class TextReader {
	private static String OPEN_ATTRIBUTES_TAG = "\t<attributes>";
	private static String OPEN_ATTRIBUTE_TAG = 		"\t\t<attribute qualifier=\"XXXXXXXXXXXX\" ";
	private static String ATTRIBUTE_TYPE = 			"type=\"XXXXXXXXXXXX\">";
	private static String CLOSE_ATTRIBUTE_TAG = 	"<modifiers/> <persistence type=\"property\"/> </attribute>";
	private static String CLOSE_ATTRIBUTES_TAG = "\t</attributes>";
	
	private static String OPEN_ITEM_TAG = "<itemtype code=\"XXXXXXXXXXXX\" autocreate=\"true\" generate=\"true\" jaloclass=\"valvoline.core.jalo.XXXXXXXXXXXX\"> <description>Valvoline XXXXXXXXXXXX</description> <deployment table=\"XXXXXXXXXXXXs\" typecode=\"XXXXXXXXXXXX\"/>";
	private static String CLOSE_ITEM_TAG = "</itemtype>";
	    public static void main(String [] args) {
	    	spitTextToXml();
	    }
	    
	    public static void spitTextToXml(){
	    	// The name of the file to open.
	        String fileName = "C:/DEV/temp.txt";

	        // This will reference one line at a time
	        String line = null;

	        try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader(fileName);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);

	          //spit open-item-xml-tag
	            System.out.println(OPEN_ITEM_TAG);
	            //spit open-attributes-xml-tag
	            System.out.println(OPEN_ATTRIBUTES_TAG);
	            while((line = bufferedReader.readLine()) != null) {
	            	//for each line, spit out a complete xml attribute
	            	spitXmlAttribute(line);
	            }
	            //spit close-attributes-xml-tag
	            System.out.println(CLOSE_ATTRIBUTES_TAG);
	            //spit close-item-xml-tag
	            System.out.println(CLOSE_ITEM_TAG);
	            // Always close files.
	            bufferedReader.close();         
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println("Unable to open file '" + fileName + "'");                
	        }
	        catch(IOException ex) {
	            System.out.println("Error reading file '" + fileName + "'");
	        }
	    }
	    
	    public static void spitXmlAttribute(String replacement){
	    	StringBuilder xmlItem = new StringBuilder();
	    	
	    	//temp variables
	    	String openAttributeTag = OPEN_ATTRIBUTE_TAG;
	    	String attributeType = ATTRIBUTE_TYPE;
	    	
	    	//expecting "attributeName  attributeType" Split it
	    	String[] parts = replacement.split("\t");
	    	String hybrisFieldName = parts[0];
	    	String hybrisFieldType = parts[1];
	    	
	    	
	    	
	    	//Replace XXXXXXXXXXXX with hybrisFieldName
	    	openAttributeTag = StringUtils.replace(openAttributeTag, "XXXXXXXXXXXX", hybrisFieldName);
	    	//Replace XXXXXXXXXXXX with hybrisFieldType in lower case
	    	attributeType = StringUtils.replace(attributeType, "XXXXXXXXXXXX", hybrisFieldType.toLowerCase());
	    	
	    	//append strings
	    	xmlItem.append(openAttributeTag);
	    	xmlItem.append(attributeType);
	    	xmlItem.append(CLOSE_ATTRIBUTE_TAG);
	    	
	    	System.out.println( xmlItem.toString());
	    }
	    
}
