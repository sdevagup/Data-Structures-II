package csc403;

/*
 * Sai Santoshi Pravallika Devaguptapu
 * 
 */

import stdlib.StdIn;
import stdlib.StdOut;

public class TestA3 {

	public static void main(String[] args) {
		//Reads in the tale.txt file.
		try{
			StdIn.fromFile("data/tale.txt");

			String[] allStrings = null;
			allStrings = StdIn.readAllStrings();
			if(allStrings != null && allStrings.length > 0){
				A3SimplerBST <String, Integer> a3SimplerBST = new A3SimplerBST <>();
				int uniqueWordsCounter = 0;

				for(int i=0; i< allStrings.length ; i++){
					String word = allStrings[i];
					if(!a3SimplerBST.contains(word)){
						a3SimplerBST.put(word, 1);
						uniqueWordsCounter++;
					}else{
						a3SimplerBST.put(word, a3SimplerBST.get(word) + 1);
					}
					
				}
				StdOut.println("Number of unique words: "+uniqueWordsCounter);
				StdOut.println("Number of two-child nodes: "+a3SimplerBST.twoChildrenCount());				
				StdOut.println("Number of words that appear once: "+a3SimplerBST.sameValueCount(1));
				StdOut.println("Number of words that appear 5 times: "+a3SimplerBST.sameValueCount(5));
			}
			
		}catch(Error e){
			StdOut.println("Oops! data/tale.txt file not found.");
		}catch(Exception e){
			StdOut.println("Exception while processing the counts.");
		}
	}

}
