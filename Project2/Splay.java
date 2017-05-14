 class SplayNode<E>
 {    
     SplayNode<E> left, right, parent;
     E element;
 
  
     public SplayNode()
     {
         this(null, null, null, null);
     }          
    
     public SplayNode(E ele)
     {
         this(ele, null, null, null);
     } 
   
     public SplayNode(E ele, SplayNode<E> left, SplayNode<E> right, SplayNode<E> parent)
     {
         this.left = left;
         this.right = right;
         this.parent = parent;
         this.element = ele;         
     }    
 }
 

 public class Splay<E extends Comparable<E>> implements BalancedTree<E>
 {
     private SplayNode<E> root;
     private int count = 0;
 
   
     public Splay()
     {
         root = null;
     }
   
 	
 	public void insert(E item) {
 		insert2(item);
 	}

 	
 	public E find(E item) {
 		if(search(item)==true){
 			return item;
 		}
 		return null;
 	}

 	
 	public void delete(E item) {
 		remove(item);
 	}

 	
 	public void printInOrderTraversal() {
 		inOrder();
 	}

 	
 	public int isWellFormed() {
 		if(root!=null){
 			return 1;
 		}
 		return 0;
 	}     
     


     public boolean isEmpty()
     {
         return root == null;
     }
 

     public void clear()
     {
         root = null;
     }
 
  
     public void insert2(E ele)
     {
         SplayNode<E> z = root;
         SplayNode<E> p = null;
         while (z != null)
         {
             p = z;
             if (ele.compareTo(p.element) == 1)
                 z = z.right;
             else
                 z = z.left;
         }
         z = new SplayNode<E>();
         z.element = ele;
         z.parent = p;
         if (p == null)
             root = z;
         else if (ele.compareTo(p.element) == 1)
             p.right = z;
         else
             p.left = z;
         splayOp(z);
         count++;
     }
   
     public void makeLeftChildParent(SplayNode<E> c, SplayNode<E> p)
     {
         if ((c == null) || (p == null) || (p.left != c) || (c.parent != p))
             throw new RuntimeException("WRONG");
 
         if (p.parent != null)
         {
             if (p == p.parent.left)
                 p.parent.left = c;
             else 
                 p.parent.right = c;
         }
         if (c.right != null)
             c.right.parent = p;
 
         c.parent = p.parent;
         p.parent = c;
         p.left = c.right;
         c.right = p;
     }
 

     public void makeRightChildParent(SplayNode<E> c, SplayNode<E> p)
     {
         if (p.parent != null)
         {
             if (p == p.parent.left)
                 p.parent.left = c;
             else
                 p.parent.right = c;
         }
         if (c.left != null)
             c.left.parent = p;
         c.parent = p.parent;
         p.parent = c;
         p.right = c.left;
         c.left = p;
     }
 
    
     private void splayOp(SplayNode<E> x)
     {
         while (x.parent != null)
         {
             SplayNode<E> Parent = x.parent;
             SplayNode<E> grandParent = Parent.parent;
             if (grandParent == null)
             {
                 if (x == Parent.left)
                     makeLeftChildParent(x, Parent);
                 else
                     makeRightChildParent(x, Parent);                 
             } 
             else
             {
                 if (x == Parent.left)
                 {
                     if (Parent == grandParent.left)
                     {
                         makeLeftChildParent(Parent, grandParent);
                         makeLeftChildParent(x, Parent);
                     }
                     else 
                     {
                         makeLeftChildParent(x, x.parent);
                         makeRightChildParent(x, x.parent);
                     }
                 }
                 else 
                 {
                     if (Parent == grandParent.left)
                     {
                         makeRightChildParent(x, x.parent);
                         makeLeftChildParent(x, x.parent);
                     } 
                     else 
                     {
                         makeRightChildParent(Parent, grandParent);
                         makeRightChildParent(x, Parent);
                     }
                 }
             }
         }
         root = x;
     }
 
     /** function to remove element **/
     public void remove(E ele)
     {
         SplayNode<E> node = findNode(ele);
        remove(node);
     }
 
     /** function to remove node **/
     private void remove(SplayNode<E> node)
     {
         if (node == null)
             return;
 
         splayOp(node);
         if( (node.left != null) && (node.right !=null))
         { 
             SplayNode<E> min = node.left;
             while(min.right!=null)
                 min = min.right;
 
             min.right = node.right;
             node.right.parent = min;
             node.left.parent = null;
             root = node.left;
         }
         else if (node.right != null)
         {
             node.right.parent = null;
             root = node.right;
         } 
         else if( node.left !=null)
         {
             node.left.parent = null;
             root = node.left;
         }
         else
         {
             root = null;
         }
         node.parent = null;
         node.left = null;
         node.right = null;
         node = null;
         count--;
     }
 
     /** Functions to count number of nodes **/
     public int countNodes()
     {
         return count;
     }
 
     /** Functions to search for an element **/
     public boolean search(E val)
     {
         return findNode(val) != null;
     }
     private SplayNode<E> findNode(E ele)
     {
         SplayNode<E> z = root;
         while (z != null)
         {
             if (ele.compareTo(z.element)==1)
                 z = z.right;
             else if (ele.compareTo(z.element) == -1)
                 z = z.left;
             else
                 return z;
         }
         return null;
     }
 
     public void inOrder()
     {
         inorder(root);
     }
     private void inorder(SplayNode<E> r)
     {
         if (r != null)
         {
             inorder(r.left);
             System.out.print(r.element +" ");
             inorder(r.right);
         }
     }
 

 }
 