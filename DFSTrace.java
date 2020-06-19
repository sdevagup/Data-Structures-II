package csc403;

/*
 * Sai Santoshi Pravallika Devaguptapu
 * 
 */
import algs41.Graph;
import stdlib.In;
import stdlib.StdOut;

public class DFSTrace{

	

	public static void dfsPrintTrace(Graph g){

		boolean marked[] = new boolean[g.V()];

		dfsPrintTrace(g, 0, marked, 0);
	}

	//pattern formed by the "First visit" and "Finished" messages is "%"+message.length+indent+"s". Ex : '%2s', i.,e 2 spaces.
	private static void dfsPrintTrace(Graph g, int start,boolean[] marked, int indent){

		String msg = "";
		int n = 0;
		if(!marked[start]) {
			marked[start] = true;
			msg = "First visit to " + start+".";
			StdOut.printf("%"+(msg.length()+indent)+"s",msg);
			StdOut.println();
		}

		for (int i : g.adj(start)){
			n = i;
			if (!marked[i]){
				dfsPrintTrace(g, i, marked, indent+1);
			}else{
				msg = "Visited " + i + " again.";				
				StdOut.printf("%"+(msg.length()+indent+1)+"s",msg);
				StdOut.println();
			}
			
		}
		
			msg = "Finished with " + start+".";
			StdOut.printf("%"+(msg.length()+indent)+"s",msg);
			StdOut.println();

	}


	public static void main(String args[]){
		In input = new In("data/tinyG.txt");
		Graph g = new Graph(input);
		dfsPrintTrace(g);
	}

}