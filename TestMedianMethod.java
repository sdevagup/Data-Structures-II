package csc403;

/*
 * Sai Santoshi Pravallika Devaguptapu
 * 
 */
import java.util.ArrayList;
import java.util.List;

import algs32.BST;
import algs33.RedBlackBST;
import stdlib.StdIn;
import stdlib.StdOut;
import stdlib.Stopwatch;

public class TestMedianMethod {

	public static void main(String[] args) {
		try{
			StdIn.fromFile("data/WordsCounts.txt");
			List<String> keys = new ArrayList<String>();
			List<Integer> values = new ArrayList<Integer>();
			
			BST<String,Integer> simpleBst = new BST<>();
			RedBlackBST<String, Integer> redBlackBst = new RedBlackBST<>();
			
			while (StdIn.hasNextLine()) {
				String line = StdIn.readLine();
				String[] lineData = line.split("\\s+");
				String key = lineData[0];
				Integer value = Integer.parseInt(lineData[1]);
				keys.add(key);
				values.add(value);
			}
			
			String[] keysArray = new String[keys.size()];
			int[] valuesArray = new int[values.size()];
			
			for(int i=0;i< keys.size();i++) {
				keysArray[i] = keys.get(i);
			}
			for(int i=0;i< values.size();i++) {
				valuesArray[i] = values.get(i);
			}
			
			Stopwatch sstStartTime = new Stopwatch();
			medianPuts(keysArray, valuesArray);
			double sstEndTime = sstStartTime.elapsedTime();
			StdOut.println("Elapsed Time for filling a BST with fill by median: "+sstEndTime);
			
			sstStartTime = new Stopwatch();
			for(int i=0;i<keysArray.length && i< valuesArray.length;i++) {				
				String key = keysArray[i];
				Integer value = valuesArray[i];
				simpleBst.put(key, value);
			}
			sstEndTime = sstStartTime.elapsedTime();
			StdOut.println("Elapsed Time for filling a BST with a sorted word list: "+sstEndTime);
			
			sstStartTime = new Stopwatch();
			for(int i=0;i<keysArray.length && i< valuesArray.length;i++) {				
				String key = keysArray[i];
				Integer value = valuesArray[i];
				redBlackBst.put(key, value);
			}
			sstEndTime = sstStartTime.elapsedTime();
			StdOut.println("Elapsed Time for filling a red-black tree with a sorted word list: "+sstEndTime);


		}catch(ArrayIndexOutOfBoundsException e){
			StdOut.println("Invalid data/WordsCounts.txt file");
		}catch(Error | Exception e){
			StdOut.println("Oops! "+e.getMessage());
		}
	}
	
	public static BST<String,Integer> medianPuts(String[] keyArray, int[] valueArray){
	  BST<String,Integer> bst =  new BST<String,Integer>();
	  bst = medianPuts(bst, keyArray, valueArray, 0, keyArray.length-1);
	  return bst;
	}
	
	public static BST<String,Integer> medianPuts(BST<String,Integer> bst, String[] keyArray, int[] valueArray, int left, int right){
	  if(left > right ){
		  return bst;
	  }else{
			  int middle = (left + right) / 2;
			  bst.put(keyArray[middle], valueArray[middle]);
			  bst = medianPuts(bst, keyArray, valueArray, left, middle-1);
			  bst = medianPuts(bst, keyArray, valueArray, middle+1, right);
			  return bst;
	  }
	}		

}
