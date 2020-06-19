package csc403;

/*
 * Sai Santoshi Pravallika Devaguptapu
 * 
 */
import stdlib.*;
import algs13.Queue;
import algs31.SequentialSearchST;
/* ***********************************************************************
 *  Compilation:  javac SeparateChainingHashST.java
 *  Execution:    java SeparateChainingHashST
 *
 *  A symbol table implemented with a separate-chaining hash table.
 *
 *  % java SeparateChainingHashST
 *
 *************************************************************************/

public class A5SeparateChainingHashST<K, V> {
	private static final int INIT_CAPACITY = 4;

	// largest prime <= 2^i for i = 3 to 31
	// not currently used for doubling and shrinking
	// private static final int[] PRIMES = {
	//    7, 13, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381,
	//    32749, 65521, 131071, 262139, 524287, 1048573, 2097143, 4194301,
	//    8388593, 16777213, 33554393, 67108859, 134217689, 268435399,
	//    536870909, 1073741789, 2147483647
	// };

	private int N;                          // number of key-value pairs
	private int M;                          // hash table size
	private SequentialSearchST<K, V>[] st;  // array of linked-list symbol tables


	// create separate chaining hash table
	public A5SeparateChainingHashST() {
		this(INIT_CAPACITY);
	}

	// create separate chaining hash table with M lists
	@SuppressWarnings("unchecked")
	public A5SeparateChainingHashST(int M) {
		this.M = M;
		st = new SequentialSearchST[M];
		for (int i = 0; i < M; i++)
			st[i] = new SequentialSearchST<>();
	}

	// resize the hash table to have the given number of chains b rehashing all of the keys
	private void resize(int chains) {
		A5SeparateChainingHashST<K, V> temp = new A5SeparateChainingHashST<>(chains);
		for (int i = 0; i < M; i++) {
			for (K key : st[i].keys()) {
				temp.put(key, st[i].get(key));
			}
		}
		this.M  = temp.M;
		this.N  = temp.N;
		this.st = temp.st;
	}

	// hash value between 0 and M-1
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	// return number of key-value pairs in symbol table
	public int size() {
		return N;
	}

	// is the symbol table empty?
	public boolean isEmpty() { return size() == 0; }

	// is the key in the symbol table?
	public boolean contains(K key) { return get(key) != null; }

	// return value associated with key, null if no such key
	public V get(K key) {
		int i = hash(key);
		return st[i].get(key);
	}

	// insert key-value pair into the table
	public void put(K key, V val) {
		if (val == null) { delete(key); return; }

		// double table size if average length of list >= 10
		if (N >= 10*M) resize(2*M);

		int i = hash(key);
		if (!st[i].contains(key)) N++;
		st[i].put(key, val);
	}

	// delete key (and associated value) if key is in the table
	public void delete(K key) {
		int i = hash(key);
		if (st[i].contains(key)) N--;
		st[i].delete(key);

		// halve table size if average length of list <= 1
		if (M > INIT_CAPACITY && N <= 2*M) resize(M/2);
	}

	// return keys in symbol table as an Iterable
	public Iterable<K> keys() {
		Queue<K> queue = new Queue<>();
		for (int i = 0; i < M; i++) {
			for (K key : st[i].keys())
				queue.enqueue(key);
		}
		return queue;
	}


	/* *********************************************************************
	 *  Unit test client.
	 ***********************************************************************/
	public static void main(String[] args) {
		A5SeparateChainingHashST<String, Integer> st = new A5SeparateChainingHashST<>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}

		// print keys
		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));

	}
	
	public int sameValueCount(V value){
		int count = 0;
		for(K key : keys()) {
			if(key != null && get(key).equals(value)) {
				count ++;
			}
		}
		return count;
	}
	
	public int chainCount(){

		int chainCount = 0;
		for(SequentialSearchST<K,V> sq : st) {
			if(!sq.isEmpty()) {
				boolean isAChain = false;
				for(K key : sq.keys()) {
					if(key != null && sq.get(key) != null) {
						isAChain = true;
						break;
					}	
				}
				if(isAChain)
					chainCount ++;
			}
		}
		return chainCount;
	}
	
	public int maxChainSize(){
		
		int chainSize = 0;
		int maxChainSize = 0;
	
		for(SequentialSearchST<K,V> sq : st) {
			if(!sq.isEmpty()) {
				chainSize = 0;
				for(K key : sq.keys()) {
					chainSize ++;
				}
				maxChainSize = maxChainSize > chainSize ? maxChainSize : chainSize;
			}
		}
		return maxChainSize;

	}

}
