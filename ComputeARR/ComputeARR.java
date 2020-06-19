package csc403;

import algs31.BinarySearchST;
import stdlib.StdIn;
import stdlib.StdOut;

public class ComputeARR {

	public static void main(String[] args) {
		try{
			BinarySearchST<String, Integer> symbolTable = new BinarySearchST<>();
			symbolTable.put("Outstanding", 6);
			symbolTable.put("Excellent", 5);
			symbolTable.put("Better", 4);
			symbolTable.put("Average", 3);
			symbolTable.put("Worse", 2);
			symbolTable.put("Awful", 1);
			symbolTable.put("Avoid", 0);

			StdIn.fromFile("data/a1ratings.txt");
			String[] ratings = StdIn.readAllStrings();

			double totalRating = 0.0;

			for (String rating: ratings) {
				double score = 0.0;
				try{
					score = symbolTable.get(rating);
					totalRating += score;
				}catch(NullPointerException ne){

				}
			}

			double averageRating = totalRating / ratings.length;
			StdOut.println("The Average restaurant Rating is : " + averageRating);
		}catch(Exception e){
			StdOut.println("Unable to find the file specified. Please check for data/a1ratingss.txt file.");
		}
	}

}
