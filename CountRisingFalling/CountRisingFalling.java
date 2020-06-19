package csc403;

import stdlib.StdIn;
import stdlib.StdOut;

public class CountRisingFalling {

	public static void main(String []args){

		try{
			StdIn.fromFile("data/100ints.txt");

			int[] intArray = StdIn.readAllInts();

			int increasingCount=0;
			int decreasingCount=0;

			for(int i=1;i<intArray.length ;i++){

				while(intArray[i] > intArray[i-1]){
					i++;
					if(i>=intArray.length){
						increasingCount= increasingCount+1;
						break;
					}
					if(intArray[i] < intArray[i-1]){
						increasingCount= increasingCount+1;
						i++;
					}
				}  // while loop end

				if(i<intArray.length){

					while(intArray[i] < intArray[i-1]){
						i++;
						if(i>=intArray.length){
							decreasingCount= decreasingCount+1;
							break;
						}
						if(intArray[i] > intArray[i-1]){
							decreasingCount= decreasingCount+1;
						}
					}  // while loop end
				}  // if condition end

			}  //for loop end
			StdOut.println("Rising sequence ::: "+ increasingCount +"\nfalling sequence :::"+ decreasingCount);
		}catch(Error | Exception e){
			StdOut.println("Oops! data/100ints file not found.");
		}
	}		
}
