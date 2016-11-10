package PocketGem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class logParse {
	  public static void main(String[] args)
	            throws FileNotFoundException, IOException {
	        String filename = "test.txt";
	        if (args.length > 0) {
	            filename = args[0];
	        }

	        String answer = parseFile(filename);
	        System.out.println(answer);
	    }

	    static String parseFile(String filename)
	            throws FileNotFoundException, IOException {
	        /*
	         *  Don't modify this function
	         */
	        BufferedReader input = new BufferedReader(new FileReader(filename));
	        List<String> allLines = new ArrayList<String>();
	        String line;
	        while ((line = input.readLine()) != null) {
	            allLines.add(line);
	        }
	        input.close();

	        return parseLines(allLines.toArray(new String[allLines.size()]));
	    }
}
