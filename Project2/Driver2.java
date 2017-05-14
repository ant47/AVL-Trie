
public class Driver2<E> {

	public static void main(String[] args) {
		AVL<Integer> avl = new AVL<Integer>();
		Trie<String> trie = new Trie<String>();
		BST<Integer> bst = new BST<Integer>();
		Splay<Integer> splay = new Splay<Integer>();


		System.out.println("Welcome to Project 2: Balanced Trees");
		System.out.println("\nRunning Test");
		
		System.out.println("\n\tStart Time\tFinishTime\tTotal Time (In Nano Seconds)");
		
		//BST
		long start = System.nanoTime();
		bst.insert(5);
		bst.insert(3);
		bst.insert(6);
		bst.delete(3);
		bst.find(5);
		long end = System.nanoTime();
		long total = end-start;
		System.out.print("BST");
		System.out.print("\t"+start+"\t"+end+"\t"+total);
		
		//AVL
		long start2 = System.nanoTime();
		avl.insert(5);
		avl.insert(3);
		avl.insert(6);
		avl.delete(3);
		avl.find(5);
		long end2 = System.nanoTime();
		long total2 = end2-start2;
		System.out.print("\nAVL");
		System.out.print("\t"+start2+"\t"+end2+"\t"+total2);
		
		//Splay
		long start3 = System.nanoTime();
		splay.insert(5);
		splay.insert(3);
		splay.insert(6);
		splay.delete(3);
		splay.find(5);
		long end3 = System.nanoTime();
		long total3 = end3-start3;
		System.out.print("\nSplay");
		System.out.print("\t"+start3+"\t"+end3+"\t"+total3);
		
		//Trie
		long start4 = System.nanoTime();
		trie.insert("5");
		trie.insert("3");
		trie.insert("6");
		trie.delete("3");
		trie.find("5");
		long end4 = System.nanoTime();
		long total4 = end4-start4;
		System.out.print("\nTrie");
		System.out.print("\t"+start4+"\t"+end4+"\t"+total4);
		
		
	}
}

