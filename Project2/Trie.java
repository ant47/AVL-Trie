import java.util.Iterator;
import java.util.LinkedList;

public class Trie<E extends Comparable <E>> implements BalancedTree<E>{
	private TrieNode<E> root;
	
	public Trie(){
		root = new TrieNode<E>(' ');
	}
	public void insert(E item) {
		if(find(item)!=null){
			return;//Already exists
		}
	
		TrieNode<E> current = root;
		String holder = String.valueOf(item);//Converts generic to string representation
		char[] word = holder.toCharArray();//Character array for each element in a word

		for(int i = 0; i<word.length;i++){
			TrieNode<E> child = current.getChild(word[i]);
			if(child!=null){
				current = child;
			}
			else{
				current.list.add(new TrieNode<E>(word[i]));
				current = current.getChild(word[i]);
			}
			current.count++;
		}
		current.isEndOfWord = true;
		
	}
	
			

public E find(E item) {
	TrieNode<E> current = root;
	String holder = String.valueOf(item);//Converts generic to string
	char[] word = holder.toCharArray();
	for(int i = 0; i<word.length;i++){
			if(current.getChild(word[i])==null){
				return null;
			}
			else
				current = current.getChild(word[i]);
		}
		if(current.isEndOfWord==true){
			//Found
			return  (E)holder;
		}
		return null;
}

	
	public void delete(E item) {
		if(find(item)==null){
			System.out.println("Can't delete an item that doesn't exist");
			return;
		}
		TrieNode<E> current = root;
		String holder = String.valueOf(item);//Converts generic to string
		char[] word = holder.toCharArray();
		for(int i = 0; i<word.length;i++){
			TrieNode<E> child = current.getChild(word[i]);
			if(child.count == 1){
				current.list.remove(child);
				return;
			}
			else{
				child.count--;
				current = child;
				
			}
		
		}
		current.isEndOfWord = false;
	}

	
	public void printInOrderTraversal() {
			print(root,"");
	}

    private  void print(TrieNode<E> root,String s)
    {
      TrieNode<E> current = root;
      if(root.list==null || root.list.size()==0)
       return;
      Iterator<TrieNode<E>> iter=current.list.iterator();
     while(iter.hasNext())
     {
      TrieNode<E> node= iter.next();
      s+=node.item;
      print(node,s);
      if(node.isEndOfWord==true)
      { 
       System.out.print(" "+s);
       s=s.substring(0,s.length()-1);
      }
      else
      {
       s=s.substring(0,s.length()-1);
      }
     }
    }   
    
	public int isWellFormed() {
		//As long as there is one element 
		if(root.list.size()>0){
			return 1;
		}
		return 0;
	}
	
class TrieNode<E extends Comparable<E>> {
		public boolean isEndOfWord;
		public char item;
		public int count;
		public LinkedList<TrieNode<E>> list;
		
		public TrieNode(char data){
			list = new LinkedList<TrieNode<E>>();
			isEndOfWord = false;
			item = data;
			count = 0;
		}
		
		public TrieNode<E> getChild(char ch){
			if(list!=null){

			for(TrieNode<E> eachChild : list){
				if(eachChild.item==ch){
					return eachChild;
				}
			}
		}
				return null;
		}


		
	}
}
