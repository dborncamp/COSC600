/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 1
 * @date 3/27/14, COSC600
 * @Assignment: project5
 *
 * Purpose of program:
 *   This program is to create my own tree and learn how trees work. I will have
 * to traverse the tree and create a TreeNode class for this tree class to use.
 * 
 * 
 * 
 *    This will be very similar to my list class from the last project. I will
 * try to design it to accept any objects in the list, Just like my list node 
 * would.
 * 
 */


public class Tree <T> {
   private TreeNode<T> root;
   int numberOfNodes;
   
   
   public Tree(){
      root=null;
   }
   
   public Tree(T node){
      root.data=node;
   }
   
   public void add(T data){
      //this will be added
      TreeNode addnode=new TreeNode(data);
      
      if (root ==null) root=addnode;
      
      TreeNode currentNode=root;
      
      findAdd(root,addnode);
   }
   
   //run recursively
   private void findAdd(TreeNode<T> node,TreeNode<T> addNode){
      //look at the left
      if(addNode.data.hashCode() < node.data.hashCode()){
         System.out.println(addNode.data+" smaller "+node.data+" -> left");
         if(node.leftNode==null) node.leftNode=addNode;
         else findAdd(node.leftNode,addNode);
      //look at the right//very risky
      }else if(addNode.data.hashCode() >node.data.hashCode()){
         System.out.println(addNode.data+" larger "+node.data+" -> right");
         if(node.rightNode==null) node.rightNode=addNode;
         else findAdd(node.rightNode,addNode);
      }
   }
   
   public void traverseInOrder(TreeNode node){
      if(node !=null){
         traverseInOrder(node.leftNode);
         System.out.print(node.data+" ");
         traverseInOrder(node.rightNode);
      }
   }
    
   public void traverseInPost(TreeNode node){
      if(node !=null){
         traverseInPost(node.leftNode);
         traverseInPost(node.rightNode);
         System.out.print(node.data+" ");
      }      
   }
   
   public TreeNode min(TreeNode node){
      if (node !=null) return null;
      else if (node.leftNode==null) return node;
      else{
         return min(node.leftNode);
      }
   }
   
   
   /*
    * Count the number of nodes in the tree.
    */
   public int count(){
      numberOfNodes = 0;
      if(root==null) return 0;
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
   
   

   /*
    * Get the tree treeNode
    */
   public TreeNode<T> getRoot() {
      return this.root;
   }
   
   /*
    * Return True if empty
    */
   public boolean isEmpty(){
      return root==null;
   }
}

/*
 * The emply tree exception I created.
 */
class EmptyTreeException extends RuntimeException{
   public EmptyTreeException(){
      System.out.print("** The tree is empty. ");
   }
}

//The node class
//find a way to change from int to T
class TreeNode <T> {
   protected T data; //should make getter/setter for java 
   protected TreeNode<T> rightNode;
   protected TreeNode<T> leftNode;
   
   //pass to 2 argument constructor
   TreeNode(T object){
      this(object,null,null);
   }   
   
   //pass to 2 argument constructor   
   TreeNode(T object,TreeNode<T> node){
      this(object,node,null);
   }
   
   TreeNode(T object,TreeNode<T> right, TreeNode<T> left){
      data=object;
      rightNode=right;
      leftNode=left;
   }
   
   @Override
   public boolean equals(Object obj) {
        if (this == obj) {
           return true;
        }
        if (obj == null) {
           return false;
        }
        if (getClass() != obj.getClass()) {
           return false;
        }
        TreeNode<?> other = (TreeNode<?>) obj;
        if (data == null) {
           if (other.data != null) {
              return false;
           }
        } else if (!data.equals(other.data)) {
           return false;
        }
        return true;
    }
}