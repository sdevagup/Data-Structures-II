package csc403;

import algs31.BinarySearchST;
import stdlib.StdAudio;
import stdlib.StdIn;
import stdlib.StdOut;

public class PlayChords {

	public static void main(String[] args) {
		BinarySearchST<String, Double> symbolTable = new BinarySearchST<>();
		try{
			StdIn.fromFile("data/notes_frequencies.txt");
		}catch(Error | Exception e){
			StdOut.println("Oops! data/notes_frequencies.txt file not found.");
		}
		while (StdIn.hasNextLine()) {
			String line = StdIn.readLine();
			String[] noteFrequency = line.split("\\s+");
			try{
				String noteName = noteFrequency[0];
				double frequency = Double.parseDouble(noteFrequency[1]);
				symbolTable.put(noteName, frequency);
			}catch(Exception e){
				StdOut.println("Invalid data/notes_frequencies.txt file");
				System.exit(0);
			}
		}
		try{
			StdIn.fromFile("data/3_note_chords.txt");
		}catch(Error | Exception e){
			StdOut.println("Oops! data/3_note_chords.txt file not found.");
		}

		while (StdIn.hasNextLine()) {
			String noteNamesLine = StdIn.readLine();
			String[] noteNames = noteNamesLine.split("\\s+");
			if(symbolTable.size()>0){
				try{
					String note1 = noteNames[0];
					double frequency1 = symbolTable.get(note1);
					String note2 = noteNames[1];
					double frequency2 = symbolTable.get(note2);
					String note3 = noteNames[2];
					double frequency3 =symbolTable.get(note3);

					playChordForOneSecond(frequency1, frequency2, frequency3);

					/*playChordForOneSecond(frequency1);
					playChordForOneSecond(frequency2);
					playChordForOneSecond(frequency3);*/
					
					/*double[] chord = new double[3];
					chord[0] = frequency1;
					chord[1] = frequency2;
					chord[2] = frequency3;			
					playChordForOneSecond(chord);*/
				}catch(Exception e){
					StdOut.println("Invalid data/3_note_chords.txt file");
					System.exit(0);
				}

			}else{
				StdOut.println("Symbol Table not loaded properly, please check data/notes_frequencies.txt file does exist or not");
			}

		}
	}

	public static void playChordForOneSecond(double... frequencies) {
		final int sliceCount = (int) (StdAudio.SAMPLE_RATE * 1);
		final double[] slices = new double[sliceCount+1];
		for (int i = 0; i <= sliceCount; i++) {
			for (double frequency: frequencies) {
				slices[i] += Math.sin(2 * Math.PI * i * frequency / StdAudio.SAMPLE_RATE);
			}
			slices[i] /= frequencies.length;
		}
		StdAudio.play(slices);
	}
}
