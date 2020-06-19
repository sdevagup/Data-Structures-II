package csc403;

/*
 * Sai Santoshi Pravallika Devaguptapu
 * 
 */
import java.util.ArrayList;
import java.util.List;

import algs35.ST;
import stdlib.StdOut;

public class Compress {

	public List<Integer> compress(String s){

		String input = s;
		int symbolVal =128;

		List<Integer> list = new ArrayList<>();
		ST<String, Integer> st = new ST<>();
		
		for(int i=0x00;i<0x7f;i++) {
			st.put(((char) i)+"", i);
		}

		for(int i=0;i<input.length();i++) {
			
			String key = input.charAt(i)+"";
			String currVal = key;
			String oldCombination = "";
			if(st.contains(key)) {
				String unScan = input.substring(i+1);
				int j=0;
				if(unScan!=null && unScan!="" && unScan.length()>0){
					while(unScan.length() > j) {
						key = key +unScan.charAt(j);
						if(st.contains(key)) {
							oldCombination = key;
							j++;
						}else {
							st.put(key, ++symbolVal);
							i = i+j;
							if(oldCombination!="")
								list.add(st.get(oldCombination));
							else
								list.add(st.get(currVal));
							break;
						}
					}
				}else{
					list.add(st.get(currVal));
					list.add(128);//End character 0x80
				}
			}
		}

		return list;
	}

	public static void main(String[] args) {
		String input = "ABRACADABRABRABRA";
		Compress comp = new Compress();
		List<Integer> compressedVals = comp.compress(input);
		StdOut.printf("Results after Compression : ");
		for(int code : compressedVals) {
			StdOut.printf("%x",code);
			StdOut.printf(" ");
		}
	}

}
