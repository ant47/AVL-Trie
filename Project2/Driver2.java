
public class Driver2 {

	public static void main(String[] args) {
		AVL<Integer> tree = new AVL<Integer>();
		Trie<String> tree2 = new Trie<String>();
		
		tree.insert(5);
		tree.insert(4);
		tree.insert(3);
		tree.insert(10);
		tree.insert(11);
		tree.insert(6);
		tree.insert(54);
		tree.insert(434);
		tree.insert(322);
		tree.insert(1232);
		tree.delete(5);
		
		tree.printInOrderTraversal();
		
		
		
		tree2.insert("a");
		tree2.insert("anthony");
		//System.out.println(tree2.find("anthony"));
		tree2.insert("basketball");
		tree2.delete("a");

		tree2.printInOrderTraversal();
		System.out.println("\n");
		System.out.println("Well Formed: "+tree2.isWellFormed());

		
		
	}
		

	}

