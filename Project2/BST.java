
public class BST<E extends Comparable<E>> implements BalancedTree<E>
{
	private TreeNode<E> root;
	 
    // Constructor
    public BST()
    {
        root = null;
    }
    // Function to check if tree is empty
    public boolean isEmpty()
    {
        return root == null;
    }
      
    public void insert(E data)
    {
        root = insert(root, data);
    }
    
    // Inserts data recursively
    private TreeNode<E> insert(TreeNode<E> node, E data)
    {
        if (node == null)
            node = new TreeNode<E>(data);
        else
        {
            if (data.compareTo(node.getData())<=0)
                node.left = insert(node.left, data);
            else
                node.right = insert(node.right, data);
        }
        return node;
    }
   
    public void delete(E k)
    {
        if (isEmpty())
            return;
        else if (find(k) ==null)
           return;
        else
        {
            root = delete(root, k);
        }
    }
    private TreeNode<E> delete(TreeNode<E> root, E k)
    {
        TreeNode<E> p, p2, n;
        if (root.getData() == k)
        {
            TreeNode<E> lt, rt;
            lt = root.getLeft();
            rt = root.getRight();
            if (lt == null && rt == null)
                return null;
            else if (lt == null)
            {
                p = rt;
                return p;
            }
            else if (rt == null)
            {
                p = lt;
                return p;
            }
            else
            {
                p2 = rt;
                p = rt;
                while (p.getLeft() != null)
                    p = p.getLeft();
                p.setLeft(lt);
                return p2;
            }
        }
        if (k.compareTo(root.getData())==-1)
        {
            n = delete(root.getLeft(), k);
            root.setLeft(n);
        }
        else
        {
            n = delete(root.getRight(), k);
            root.setRight(n);             
        }
        return root;
    }
    public int countNodes()
    {
        return countNodes(root);
    }
    private int countNodes(TreeNode<E> r)
    {
        if (r == null)
            return 0;
        else
        {
            int l = 1;
            l += countNodes(r.getLeft());
            l += countNodes(r.getRight());
            return l;
        }
    }
   
    public E find(E val)
    {
        if( search(root, val)==true)
        	return val;
        else return null;
    }
    private boolean search(TreeNode<E> r, E val)
    {
        boolean found = false;
        while ((r != null) && !found)
        {
            E rval = r.getData();
            if (val.compareTo(rval)==-1)
                r = r.getLeft();
            else if (val.compareTo(rval)==+1)
                r = r.getRight();
            else
            {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }
    public void printInOrderTraversal()
    {
        inorder(root);
    }
    private void inorder(TreeNode<E> r)
    {
        if (r != null)
        {
            inorder(r.getLeft());
            System.out.print(r.getData() +" ");
            inorder(r.getRight());
        }
    }
 
    public int isWellFormed(){
    	if(root!=null){
    		return 1;
    	}
    	return 0;
    }
 
}
class TreeNode<E extends Comparable<E>> 
{
    TreeNode<E> left, right;
    E data;

    public TreeNode()
    {
        left = null;
        right = null;
        data = null;
    }
    public TreeNode(E n)
    {
        left = null;
        right = null;
        data = n;
    }
    public void setLeft(TreeNode<E> n)
    {
        left = n;
    }
    public void setRight(TreeNode<E> n)
    {
        right = n;
    }
    public TreeNode<E> getLeft()
    {
        return left;
    }
    public TreeNode<E> getRight()
    {
        return right;
    }
    public void setData(E d)
    {
        data = d;
    }
    public E getData()
    {
        return data;
    }     
}