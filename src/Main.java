import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
#########################################################################################
# -This is a template source code file given you to complete 4th quiz work.             #
#                                                                                       #
# -You are strongly expected to declare and define data structure and recursive         #
# method within the comment-lines dedicated below.                                      #
#                                                                                       #
# -DO-NOT modify the comment-lines (not even a letter in those lines) that determine    #
# the boundaries of the blocks in which you are to perform the expected behaviors.      #
# In the case you modify you are likely to get 0 points from this quiz!                 #
#                                                                                       #
# -You can expand the lines of these blocks as much as you wish.                        #
#                                                                                       #
# -DO-NOT rename this file!                                                             #
# -DO-NOT use any class file other than this!                                           #
# -Feel free to define functions as much as you wish but within this class!             #
#########################################################################################
*/
public class Main {
	
	 public static void main(String [ ] args) {
	        // Declare all the data structure(s) below that you need
	        // DO-NOT spoil the inside of the block with the code-snippets other than declarations!
		 
	        /////////////// DATA-STRUCTURE DECLARATION: BEGIN ///////////////
		 
		 	LinkedHashMap<String,String> bnfMap = new LinkedHashMap<String,String>();
		 	ArrayList<String> Keyset = new ArrayList<String>();
		 	
		 	/////////////// DATA-STRUCTURE DECLARATION: END   ///////////////
		 	
		 	File file = new File(args[0]);
		 
			try {
				Scanner scan= new Scanner(file);
				while (scan.hasNextLine()) {
					String line = scan.nextLine();
					String bnfKey = line.substring(0, line.indexOf("-"));
					Keyset.add(bnfKey);
					String bnfVal = "("+line.substring(line.indexOf(">")+1)+")";
					ArrayList<String> split = new ArrayList<String>();
					
					split.addAll(Arrays.asList(Pattern.compile("\\|").split(bnfVal)));
					bnfMap.put(bnfKey, bnfVal);

					
				}
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
		
			loop(bnfMap,Keyset);
			
			
	    }
	    
	    
	 
	 	public static void iterateMap(LinkedHashMap<String, String> map, ArrayList<String> keys) {
	 		String firstKey = "";
	 		if (!map.isEmpty()) {
	 			firstKey = (String) map.keySet().iterator().next();
	 			
	 			bnfHandle(keys,map,map.get(firstKey),firstKey);
	 			
	 			System.out.println(map.get(firstKey));
	 		}
	 	}
	 	// Define and fill recursive method below
	    // Note: The whole structure of the recursion (including the begin-end curly braces)
	    // should be placed within the block
	 	/////////////// RECURSION: BEGIN ///////////////
	 	
	 	public static void bnfHandle(ArrayList<String> keys,LinkedHashMap<String, String> map,String str, String first) {
	 		
	 		String var = "";
	 		String strtmp = "";
	 		
			for (int i = 0;i<str.length()-1;i++) {
				for (int j = 0;j<keys.size();j++) {
					if (str.substring(i, i+1).equals(keys.get(j))) {
						var = map.get(str.substring(i, i+1));
						strtmp = map.get(first);
						strtmp = strtmp.replace(str.substring(i, i+1), var);
						map.put(first,strtmp);
						bnfHandle(keys,map,map.get(str.substring(i, i+1)),first);
						
					}
					
				}
			}
	 		
	 		
	 	}
	    /////////////// RECURSION: END   ///////////////
	
}
