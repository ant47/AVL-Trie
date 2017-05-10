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
		String holder = String.valueOf(item);//Converts generic to string
		char[] holderForItem = holder.toCharArray();
		
		for(char ch : holderForItem){
			TrieNode<E> child = current.getChild(ch);
			if(child!=null){
				current = child;
			}
			else{
				current.list.add(new TrieNode<E>(ch));
				current = current.getChild(ch);
			}
			current.count++;
		}
		current.isEndOfWord = true;
		
	}
	
			
@SuppressWarnings("unchecked")
public E find(E item) {
	TrieNode<E> current = root;
	String holder = String.valueOf(item);//Converts generic to string
	char[] holderForItem = holder.toCharArray();
		for(char ch : holderForItem){
			if(current.getChild(ch)==null){
				return null;
			}
			else
				current = current.getChild(ch);
		}
		if(current.isEndOfWord==true){
			//Found
			return  (E)holder;
		}
		return null;
}

	
	public void delete(E item) {
		if(find(item)==null){
			System.out.println("That item doesn't exist");
			return;
		}
		TrieNode<E> current = root;
		String holder = String.valueOf(item);//Converts generic to string
		char[] holderForItem = holder.toCharArray();
		for(char ch : holderForItem){
			TrieNode<E> child = current.getChild(ch);
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
			printAllWordsInTrie(root,"");
	}

    private  void printAllWordsInTrie(TrieNode<E> root,String s)
    {
      TrieNode<E> current = root;
      if(root.list==null || root.list.size()==0)
       return;
      Iterator<TrieNode<E>> iter=current.list.iterator();
     while(iter.hasNext())
     {
      TrieNode<E> node= iter.next();
      s+=node.item;
      printAllWordsInTrie(node,s);
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
