package csc403;

/*
 * Sai Santoshi Pravallika Devaguptapu
 * 
 */
import algs33.RedBlackBST;
import stdlib.StdIn;
import stdlib.StdOut;


public class DNAStatistics {

	public static void main(String[] args) {
		
		StdIn.fromFile("data/sequences.txt");
		
		while (StdIn.hasNextLine()) {
			String line = StdIn.readLine();
			String[] mammalDna = line.split("\\t");
			String speciesName = "";
			if(mammalDna.length == 2) {
				speciesName = mammalDna[0];
				String sequence = mammalDna[1];
				
				RedBlackBST<String, Integer> codons = new RedBlackBST<>();
				RedBlackBST<String, Integer> nucleotides = new RedBlackBST<>();

				for(int i=0;i<sequence.length()-2;i = i+3) {
					String codon = sequence.substring(i, i+3);
					if(codons.contains(codon)) {
						codons.put(codon, codons.get(codon)+1);
					}else {
						codons.put(codon,1);
					}
				}
				
				for(int i=0;i<sequence.length();i++) {
					String nucleotide = sequence.substring(i, i+1);
					if(nucleotides.contains(nucleotide)) {
						nucleotides.put(nucleotide, nucleotides.get(nucleotide)+1);
					}else {
						nucleotides.put(nucleotide,1);
					}
				}
				StdOut.println("species Name:"+speciesName);
				StdOut.println("Number of nucleotides:"+sequence.length());
				for(String nucleotide : nucleotides.keys()) {
					StdOut.println(nucleotide+":"+nucleotides.get(nucleotide)+"\t");
				}
				
				StdOut.println("\nNumber of codons:"+sequence.length()/3);
				StdOut.println("Number of different codons:"+codons.size());
				int i=1;
				for(String codon : codons.keys()) {
					StdOut.print(codon+":"+codons.get(codon)+"\t");
					if(i%8 == 0) {
						StdOut.println();
					}
					i++;
				}
			}
			
			StdOut.println("\n--------------------------------------------------------");
		}
	}
}
