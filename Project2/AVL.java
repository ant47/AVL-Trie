 
public class AVL<E extends Comparable<E>> implements BalancedTree<E> {

	   public AVLNode<E> root;

	   public AVL() { 
	      root = null;
	   }

	
	public void insert(E item) {
		root = insert(item, root);
	}

	
	public E find(E item){
	      AVLNode<E> t = root;
	      
	  while(t!=null){
	    if(item.compareTo(t.getItem()) == 0){
	       return t.getItem();
	  }
	    else if(item.compareTo(t.getItem()) < 0)
	       t = t.getLeft();
	    else
	       t = t.getRight();
	  }
	      return null;
	}  

	
	public void delete(E item) {
		root = delete(item,root);
	}
    
	
	public void printInOrderTraversal() {
		 printInorder(root);
	      System.out.println();
	}

	private void printInorder(AVLNode<E> t){
		if (t != null){
			printInorder(t.getLeft());
			System.out.print(t.getItem() + " ");
			printInorder(t.getRight());
		}
	} 
	
	
	public int isWellFormed() {
		if(root!=null){
			return 1;
		}
		return 0;
	}
	   


	public AVLNode<E> insert(E data, AVLNode<E> t){
	{
		if (t == null)
		t = new AVLNode<E>(data);
		        
		else if (data.compareTo(t.getItem())<0)
		{
		        	  
		  t.setLeft(insert(data,t.getLeft()));

		  if(t.getLevel(t.getLeft()) - t.getLevel(t.getRight()) == 2)
		        if(data.compareTo(t.getLeft().getItem())>0)
		            t = rotateLC(t);
		        else
		            t = doubleLC(t);
		}
		
		else if( data.compareTo(t.getItem())>0)
		{
			t.setRight(insert(data,t.getRight()));
	
		if(t.getLevel(t.getRight()) - t.getLevel(t.getLeft()) == 2)
			if(data.compareTo(t.getRight().getItem())>0)
		        t = rotateRC(t);
		    else
		    	t = doubleRC(t);
		}
		
		else
			t.setLevel(Math.max(t.getLevel(t.getLeft()),t.getLevel(t.getRight())));
		    return t;
		 }
   
	}
	   
	   public AVLNode<E> delete(E theItem, AVLNode<E> t)
	    {
	        if(t == null){
	        	//Item doesn't exist
	            return t;  
	        } 
	        if(theItem.compareTo(t.getItem()) < 0){
	            t.setLeft(delete(theItem,t.getLeft()));
	        }
	        
	        else if(theItem.compareTo(t.getItem()) > 0){
	        	 t.setRight(delete(theItem,t.getRight()));
	        }
	        //Two child
	        else if(t.getLeft() != null && t.getRight() != null)
	        {
	        	t.setItem(findMin(t.getRight()).getItem());
	        	t.setRight(delete(t.getItem(),t.getRight()));
	        }
	        else if(t.getLeft() != null){
	            t =   t.getLeft(); 
	        }
	        else
	        	t = t.getRight();
	       
	        return balance(t);
	    }
		
	   private AVLNode<E> balance(AVLNode<E> t)
	    {
	        if(t == null){
	            return t;
	        }
	        //Heights cannot be greater than 1
	        if(t.getLevel(t.getLeft()) - t.getLevel(t.getRight()) > 1){
	        	
	            if(t.getLevel(t.getLeft().getLeft()) >= t.getLevel(t.getLeft().getRight())){
	              
	            	t = rotateLC(t);
	            
	            }
	                else
	                	
	                t = doubleLC(t);
	        }
	        
	        else
	        if(t.getLevel(t.getRight()) - t.getLevel(t.getLeft()) > 1){
	            if(t.getLevel(t.getRight().getRight()) >= t.getLevel(t.getRight().getLeft())){
	                t = rotateRC(t);
	            }
	            else
	                t = doubleRC(t);
	        }
	        t.setLevel(Math.max( t.getLevel(t.getLeft()), t.getLevel(t.getRight()) ) + 1);
	        return t;
	    }
		
	    private AVLNode<E> rotateLC(AVLNode<E> c1)
	    {
	    	//Takes node c2 and rotates its left child 
	        AVLNode<E> c2 = c1.getLeft();
	        c1.setLeft(c2.getRight());
	        c2.getRight().setItem(c1.getItem());

	        c1.setLevel(Math.max(c1.getLevel(c1.getLeft()), c1.getLevel(c1.getRight())) + 1);

	        c2.setLevel(Math.max( c2.getLevel(c2.getLeft()), c1.getLevel(c1)) + 1);
	        
	        return c2;
	    }
	    
	    private AVLNode<E> rotateRC(AVLNode<E> c1)
	    {
	        AVLNode<E> c2 = c1.getRight();
	        c1.setRight(c2.getLeft());
	        c2.getLeft().setItem(c1.getItem());
	        		        
	        c1.setLevel(Math.max(c1.getLevel(c1.getLeft()), c1.getLevel(c1.getRight()) ) + 1);

	        c2.setLevel(Math.max(c2.getLevel(c2.getRight()), c1.getLevel(c1)) + 1);

	        
	        return c2;
	    }
	   
	    private AVLNode<E> doubleLC(AVLNode<E> k3)
	    {
	    	//Double rotate with the left child
	    	k3.setLeft(rotateRC(k3.getLeft()));
	    	
	        return rotateRC(k3);
	    }
	    
	    private AVLNode<E> doubleRC(AVLNode<E> k1)
	    {
	    	//Double rotate with right child
	        k1.setRight(rotateLC(k1.getRight()));
	        return rotateRC(k1);
	    }
	    
		private AVLNode<E> findMin(AVLNode<E> t)
		{
				//Finds the Minimum value in the tree
		        if(t == null)
		            return t;

		        while(t.getLeft()!= null)
		            t=t.getLeft();
		       
		        return t;
		}
}

class AVLNode<E extends Comparable<E>> {
	   private E item;
	   private AVLNode<E>  parent;
	   private AVLNode<E>  left;   
	   private AVLNode<E>  right; 
	   public int level;
	  
	   public AVLNode(E element, AVLNode<E> leftC, AVLNode<E> rightC){
		   this.item=element;
		   this.left = leftC;
		   this.right=rightC;
		   this.level=0;
	   }
	   
	   public AVLNode (E anItem){
		   this(anItem, null, null);
	   }
		   
	   public E getItem(){
		   return item;
	   }
		   
	   public void setItem(E item2){
		   this.item = item2;
	   }
		   
	   public void setLeft(AVLNode<E> pt) {
		   this.left = pt;
	   }
		   
	   public void setRight(AVLNode<E> pt) {
		   this.right = pt;
	   }
		
	   public void setParent(AVLNode<E> pt){
		   this.parent = pt;
	   }
		   
	   public AVLNode<E> getParent(){
		      return parent;
	   }
		   
	   public AVLNode<E> getLeft() {
		      return left;
	   }
		   
	   public AVLNode<E> getRight() {
		      return right;
	   }
		   
	   public int getLevel(AVLNode<E> pt){
			   return level;   
	   }
		   
	   public void setLevel(int l){
			   this.level=l;
	   }

}

