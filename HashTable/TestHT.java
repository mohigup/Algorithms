
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
/*import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;*/
import java.util.Scanner;

public class TestHT {
	

	public static String readTFile(File file) {
	    Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	    String text ="";
	    while(scanner.hasNext()){
	        text = text + " " + scanner.nextLine();
	    }
	    return text;
	}
	
	

	public static void main(String arg[]){
		
		
	
		
		String text = null;
		
		
		
		File file = new File("alice_in_wonderland.txt");
		BufferedReader br = null;;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}
		String line = null;
		String output_string = null;
		String [] splitStr = null;
		try {
			while( (line = br.readLine())!= null ){
			        
				//System.out.println(line);
				output_string = output_string + " " +line;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		    
		splitStr = output_string.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
		
	/*	for(String a :splitStr)
			System.out.println(splitStr.length);*/
		
		HashTable h = new HashTable();
		for(int i=0; i<splitStr.length; i++){
			if(splitStr[i]!=null && !splitStr[i].isEmpty()){
				h.increase(splitStr[i]);
			}
		}
		
		h.printKeyVal();
		
		// Write count in a file
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("output.txt");
			int c = 0;
			for(String k: h.listAllKeys()){
				pw.println(k + " -> " + h.get(k));
				c++;
			}
			System.out.println("TotalKeys = "+c);
		} catch (FileNotFoundException e) {
			System.out.println("Error writing to file");
			e.printStackTrace();
		}finally{
			pw.close();
		}
		
		
	}




	
}
