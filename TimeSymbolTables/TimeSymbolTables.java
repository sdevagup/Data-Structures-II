package csc403;

import algs31.SequentialSearchST;
import algs32.BST;
import stdlib.StdIn;
import stdlib.StdOut;
import stdlib.Stopwatch;

public class TimeSymbolTables {

	public static void main(String[] args) {

		//Reads in the tale.txt file.
		try{
			StdIn.fromFile("data/tale.txt");

			String[] allStrings = null;
			allStrings = StdIn.readAllStrings();
			
			if(allStrings != null && allStrings.length > 0){
				SequentialSearchST<String, Integer> sequentialSearchST = new SequentialSearchST<>();
				BST<String, Integer> bst = new BST<>();

				StdOut.println("Computing the processing time for SequentialSearchST, please wait...");
				double sstEndTime;
				Stopwatch sstStartTime = new Stopwatch();

				for(int i=0; i< allStrings.length ; i++){
					String word = allStrings[i];
					if(sequentialSearchST.get(word)==null){
						sequentialSearchST.put(word, 1);
					}else{
						sequentialSearchST.put(word, sequentialSearchST.get(word) + 1);
					}
				}

				sstEndTime = sstStartTime.elapsedTime();
				StdOut.println("SequentialSearchST Elapsed time is "+sstEndTime+" sec(s)");


				StdOut.println("\nComputing the processing time for BST, please wait...");
				double bstEndTime;
				Stopwatch bstStartTime = new Stopwatch();

				for(int i=0; i< allStrings.length ; i++){
					String word = allStrings[i];
					if(bst.get(word)==null){
						bst.put(word, 1);
					}else{
						bst.put(word, bst.get(word) + 1);
					}
				}

				bstEndTime = bstStartTime.elapsedTime();
				StdOut.println("BST Elapsed time is "+bstEndTime+" sec(s)");

			}else{
				StdOut.println("Invalid text file data/tale.txt");
			}
		}catch(Error | Exception e){
			StdOut.println("Oops! data/tale.txt file not found.");
		}
	}

}
