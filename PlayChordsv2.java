package csc403;

import stdlib.StdAudio;
import stdlib.StdIn;
import stdlib.StdOut;

public class PlayChordsv2 {

	public static void main(String[] args) {

		SimplerBST<String, Double> symbolTable = new SimplerBST<>();
		//Reads in the notes and frequencies file.
		try{
			StdIn.fromFile("data/notes_frequencies.txt");

			//Building symbol table
			while (StdIn.hasNextLine()) {
				String line = StdIn.readLine();
				String[] noteFrequency = line.split("\\s+");
				String noteName = noteFrequency[0];
				double frequency = Double.parseDouble(noteFrequency[1]);
				symbolTable.put(noteName, frequency);
			}

			//Reads in a chords file
			StdIn.fromFile("data/a2chords.txt");

			while (StdIn.hasNextLine()) {
				String noteNamesLine = StdIn.readLine();
				String[] noteNames = noteNamesLine.split("\\s+");

				if(noteNames != null && noteNames.length > 0){
					double[] chord = new double[noteNames.length];

					for(int i = 0; i< noteNames.length ; i++ ){
						String note = noteNames[i];
						double frequency = symbolTable.get(note);

						chord[i] = frequency;

					}
					playChordForOneSecond(chord);
				}else{
					StdOut.println("Invalid data/a2chords.txt file");
				}
			}
		}catch(NullPointerException e){
			StdOut.println("Unable to find played notes , please check your notes_frequencies.txt and a2chords.txt file");
		}catch(ArrayIndexOutOfBoundsException e){
			StdOut.println("Invalid data/notes_frequencies.txt file");
		}catch(Error | Exception e){
			StdOut.println("Oops! "+e.getMessage());
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
