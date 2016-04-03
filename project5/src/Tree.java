
import java.util.StringTokenizer;

/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 3
 * @date 3/27/14, COSC600
 * @Assignment: project5
 *
 * Purpose of program:
 *   This program is to create my own tree and learn how trees work. I will have
 * to traverse the tree and create a TreeNode class for this tree class to use.
 * Th
 * 
 *    This will be very similar to my list class from the last project. I will
 * try to design it to accept any objects, Just like my list node would.
 * 
 */


public class Tree {
   private TreeNode root;//=new TreeNode();
   private int numberOfNodes,inOrderInt;
   private boolean equals,found;
   
   //constructors, can start with null, a single integer or a node.
   public Tree(){
      root=null;
   }
   
   /**
    * @param node - Integer to be the data element in the root.
    */
   public Tree(int node){
      root=new TreeNode(node);
   }
   
   /**
    * @param node - Node to become the root.
    */
   public Tree(TreeNode node){
      addInOrder(node);
   }
   
   /**
    * @param node - node whose data to add.
    * 
    * This is to create a tree from a given node, it basically copies the tree's
    * data into the current tree. It uses the same algorithm as the inOrder 
    * function to do it.
    */
   private void addInOrder(TreeNode node){
      if(node !=null){
         addInOrder(node.leftNode);
         add(node.data);
         addInOrder(node.rightNode);
      }
   }
   
   
  /**
    * @param data - string to be parsed to add to tree.
    * 
    * Will build a binary search tree given a string containing integers.
    */
   public void build(String data){
      System.out.println("Building a tree given: "+data);
      StringTokenizer token=new StringTokenizer(data,", ");
      while (token.hasMoreTokens()){
         add(Integer.parseInt(token.nextToken()));
      }
   }
   
   /**
    * @param data - integer to be added to the tree.
    * 
    *    This will add the integer to the data element of a node on the tree.
    * It will also use a search function to recursively search the tree to find
    * the correct location to add it.
    */
   public void add(int data){
      //this will be added
      TreeNode addnode=new TreeNode(data);
      if (root ==null) {
         root=addnode;
         //System.out.println("root is null so adding "+data+" as root");
      }
      //Find a place to add it. assumes binary search tree.
      findAdd(root,addnode);
   }
   //find the add spot, run recursively
   private void findAdd(TreeNode node,TreeNode addNode){
      //look at the left
      if(addNode.data < node.data){
         //System.out.println(addNode.data+" smaller than "+node.data+" -> left");
         if(node.leftNode==null) node.leftNode=addNode;
         else findAdd(node.leftNode,addNode);
      //look at the right
      }else if(addNode.data > node.data){
         //System.out.println(addNode.data+" larger than "+node.data+" -> right");
         if(node.rightNode==null) node.rightNode=addNode;
         else findAdd(node.rightNode,addNode);
      }
   }
   
   /**
    * @param search - Integer, data to search for in the tree.
    * @return node matching the search integer.
    * This will use a recursive function to search the tree and return the 
    * node that the data was found in.
    */
   public TreeNode find(int search){
      if (root==null) throw new EmptyTreeException("find "+search);
      if (root !=null) {
         TreeNode temp;
         temp=findNode(root,new TreeNode(search));
         if (temp==null) System.out.println("Could not find "+search+" in tree.");
         return temp;
      }
      return null;
      
   }
   //recursive searhc function.
   private TreeNode findNode(TreeNode search, TreeNode node){
      TreeNode returnNode;
      if (search ==null) return null;
      if (search.data==node.data) return search;
      else {
         returnNode=findNode(search.leftNode,node);
         if(returnNode==null) returnNode=findNode(search.rightNode,node);
      } 
      return returnNode;
   }
   
   /**
    * Prints the tree using InOrder.
    */
   public void inOrder(){
      if (root==null) throw new EmptyTreeException("Traverse using InOrder");
      //System.out.println("Traversing tree InOrder");
      traverseInOrder(root);
      System.out.println();
   }
   
    /**
    * @param node - Node to explore.
    * 
    * Will recursively print the tree in order.
    */
   private void traverseInOrder(TreeNode node){
      if(node !=null){
         traverseInOrder(node.leftNode);
         System.out.print(node.data+" ");
         traverseInOrder(node.rightNode);
      }
   }
    
   
   /**
    * @param nodeInt - The node number to print.
    * 
    * Prints a selected node of the tree using InOrder.
    */
   public void inOrder(int nodeInt){
      if (root==null) throw new EmptyTreeException("Traverse using InOrder");
      inOrderInt=0;
      found=false;
      //System.out.println("Traversing tree InOrder");
      traverseInOrder(root,nodeInt);
      if (!found)System.out.println("Node "+nodeInt+" not found.");
   }
   
    /**
    * @param node - Node to explore.
    * @param nodeInt - Node number to print.
    * 
    * Will run recursively and print the data of the noteInt's node in order.
    */
   private void traverseInOrder(TreeNode node,int nodeInt){
      //inOrderInt++;
      if(node !=null){
         if (inOrderInt == nodeInt && found==false){
            System.out.println("Data inorder node "+nodeInt+" is: "+node.data);
            found=true;
         }
         traverseInOrder(node.leftNode,nodeInt);
         //System.out.println("inOrderint: "+inOrderInt+" nodeInt: "+nodeInt);
         inOrderInt++;
         if (inOrderInt == nodeInt && found==false){
            System.out.println("Data inorder node "+nodeInt+" is: "+node.data);
            found=true;
         }
         traverseInOrder(node.rightNode,nodeInt);
      }
   }
   
   
   /**
    * Prints the tree using PostOrder.
    */
   public void postOrder(){
      if (root==null) throw new EmptyTreeException("Traverse using PostOrder");
      //System.out.println("Traversing tree PostOrder");
      traverseInPost(root);
      System.out.println();
   }
   /**
    * @param node - Node to explore.
    * 
    * Will recursively print the tree in post order.
    */
   private void traverseInPost(TreeNode node){
      if(node !=null){
         traverseInPost(node.leftNode);
         traverseInPost(node.rightNode);
         System.out.print(node.data+" ");
      }      
   }
   
   /**
    * @param node - Node that is root of the tree.
    * @return Node that contains the smallest item.
    * 
    * Finds the minimum element in a given tree. This could be given any section
    * of a tree and it will find the min. But it assumes that that part is a 
    * binary tree.
    */
   public TreeNode min(TreeNode node){
      if(root==null) throw new EmptyTreeException(" find minimum");
      if (node !=null) return null;
      else if (node.leftNode==null) return node;
      else{
         return min(node.leftNode);
      }
   }
   
   /**
    * @return Integer containing the number of elements in the tree.
    * 
    *    Count the number of nodes in the tree. This only uses one global variable
    * and no local variables. It will check for an empty root before sending it
    * to the recursive counter.
    */
   public int count(){
      numberOfNodes = 0;
      if(root==null) throw new EmptyTreeException("Count");
      count(root);
      return numberOfNodes;
   }
   //use a recursive function
   private void count(TreeNode node) {
      if(node != null){
         numberOfNodes++;
         count(node.leftNode);
         count(node.rightNode);
      }
   }
   
   /**
    * Swaps the right and left nodes of each node on the tree recursively.
    * Calls a recursive function that will do it. This method checks that the 
    * tree is not null before trying to swap.
    */
   public void swap(){
      if(root==null)throw new EmptyTreeException("Swap");
      
      //System.out.println("Swapping tree");
      swap(root);
   }
   /**
    * @param node - The node to swap.
    * 
    *    This function will recursively go through the tree and swap the node on 
    * the left for the node on the right. called by the other swap function.
    */
   private void swap(TreeNode node){
      if (node !=null){
         //System.out.println("Swapping "+node.data);
         TreeNode tempNode;
         tempNode=node.leftNode;
         node.leftNode=node.rightNode;
         node.rightNode=tempNode;
         swap(node.leftNode);
         swap(node.rightNode);
      }
   }
   
   /**
    * @param tree - Tree to be compared to this one.
    * @return equals boolean. True if the trees are equal.
    * 
    * This method checks for equality of this tree compared to another.
    */
   public boolean compare(Tree tree){
      if (tree.root.data !=root.data) return false;
      equals=true;
      return nodeComp(root,tree.root);
   }
   /**
    * @param one - The first node to compare.
    * @param two - The second node to compare.
    * @return - Returns true if the nodes are equal.
    * 
    * This method is called by the equals function to determine if 2 nodes in 
    * a tree are the same.
    */
   private boolean nodeComp(TreeNode one, TreeNode two){
      if (one == null && two != null) equals= false;
      if (one != null && two == null) equals= false;
      
      if (one !=null && two !=null && equals ){
         if (one.data != two.data) equals=false;
         nodeComp(one.leftNode,two.leftNode);
         nodeComp(one.rightNode,two.rightNode);
      }
      return equals;
   }

   /**
    * @param remove - integer to be removed
    * @return true if node was successfully removed
    * 
    *    This method uses the remove function in the TreeNode class. It will 
    * check for an empty root before calling that recursive call. 
    * It will also check the second case of the root being the removed node.
    * 
    * This function assumes a Binary Search Tree. Do not swap before removing.
    */
   public boolean remove(int value) {
      if (root == null) throw new EmptyTreeException("Remove "+value);
      else {
         if (root.getValue() == value) {
            TreeNode auxRoot = new TreeNode(0);
            auxRoot.setLeft(root);
            boolean result = root.remove(value, auxRoot);
            root = auxRoot.getLeft();
            return result;
         } else {
            return root.remove(value, null);
         }
      }
   }

   /**
    * @return - The root node
    * Get the root of the tree.
    */
   public TreeNode getRoot() {
      if (root==null) throw new EmptyTreeException("getRoot");
      return this.root;
   }
   
   /**
    * @return returns True if tree is empty
    * Tests if the tree is empty. 
    */
   public boolean isEmpty(){
      return root==null;
   }
}

/**
 * The empty tree exception I created. A very simple exception class.
 */
class EmptyTreeException extends RuntimeException{
   public EmptyTreeException(){
      System.out.print("** The tree is empty. ");
   }
   /**
    * @param name - Usually just function name, will print, 
    * "** The tree is empty, cannot "+name+"."
    */
   public EmptyTreeException(String name){
      System.out.println("** The tree is empty, cannot "+name+".");
   }
}

//The node class
//want find a way to change from int to T
class TreeNode {
   protected int data; 
   protected TreeNode rightNode;
   protected TreeNode leftNode;
   
//   TreeNode(){
//      this(0);
//   }
   //pass to 3 argument constructor
   TreeNode(int object){
      this(object,null,null);
   }   
   
   //pass to 3 argument constructor   
   TreeNode(int object,TreeNode node){
      this(object,node,null);
   }
   
   /**
    * @param object - Data integer. I want to make this able to handle all 
    * objects, not just integers but that is unlikely to happen before the 
    * due date of this assignment.
    * @param right - The right node.
    * @param left - The left node.
    * 
    */
   TreeNode(int object,TreeNode right, TreeNode left){
      data=object;
      rightNode=right;
      leftNode=left;
   }
   
   /**
    * @return - The node on the left side of the parent node.
    * 
    * Returns the left node.
    */
   public TreeNode getLeft() { 
      return leftNode; 
   }
   
   /**
    * @return - The node on the right side of the parent node.
    * 
    * Returns the right node.
    */    
   public TreeNode getRight() { 
      return rightNode; 
   }
    
   /**
    * @return - The value of the node's data.
    * 
    * Returns the data the node contains.
    */
   public int getValue() { 
      return data; 
   }
    
   /**
    * @param n - The node to set the left as.
    * 
    * This method sets the left node to a given TreeNode.
    */
   public void setLeft(TreeNode n) { 
      leftNode = n; 
   }

   /**
    * @param n - The node to set the right as.
    * 
    * This method sets the right node to a given TreeNode.
    */   
   public void setRight(TreeNode n) {
      rightNode = n; 
   }
    
   /**
    * @return Returns true if the node has children.
    * 
    * Will return true if the node has left and right nodes that are not null.
    */
   public boolean hasKids() {
      return (leftNode==null && rightNode==null);
   }
   
   /**
    * @param value - The value to be deleted.
    * @param parent - The node to delete it from
    * @return Boolean to return true if deleted. Will return false if the value
    * is not found and deleted.
    * 
    *    This method will look through the nodes that are linked through this node
    * and try to find the one to be deleted.  
    * This method assumes a Binary Search Tree!
    */
   public boolean remove(int value, TreeNode parent) {
      if (value < this.data) {
         if (leftNode != null)
            return leftNode.remove(value, this);
         else return false;
      } else if (value > this.data) {
         if (rightNode != null) return rightNode.remove(value, this);
         else return false;
      } else {
         if (leftNode != null && rightNode != null) {
            this.data = rightNode.minValue();
            rightNode.remove(this.data, this);
         } else if (parent.leftNode == this) {
            parent.leftNode = (leftNode != null) ? leftNode : rightNode;
         } else if (parent.rightNode == this) {
            parent.rightNode = (leftNode != null) ? leftNode : rightNode;
            }
            return true;
            }
      }

   /**
    * @return - Minimum value of all nodes linked after this.
    * 
    *    Will find the minimum value of a node's data that is linked after this.
    * one. This method assumes the tree is a binary tree!
    */
   public int minValue() {
      if (leftNode == null)return data;
      else return leftNode.minValue();
   }
}