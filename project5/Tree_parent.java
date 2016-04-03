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


public class Tree {
   private TreeNode root;
   private int numberOfNodes;
   
   
   public Tree(){
      root=null;
   }
   
   public Tree(int node){
      root.data=node;
   }
   
   public Tree(TreeNode node){
      root=node;
   }
   
   public void add(int data){
      //this will be added
      TreeNode addnode=new TreeNode(data);
      
      if (root ==null) root=addnode;
      
      TreeNode currentNode=root;
      
      findAdd(root,addnode);
   }
   
   //run recursively
   private void findAdd(TreeNode node,TreeNode addNode){
      //look at the left
      if(addNode.data < node.data){
         System.out.println(addNode.data+" smaller "+node.data+" -> left");
         if(node.leftNode==null) {
            node.leftNode=addNode;
            addNode.parent=node;
         }
         else findAdd(node.leftNode,addNode);
      //look at the right
      }else if(addNode.data > node.data){
         System.out.println(addNode.data+" larger "+node.data+" -> right");
         if(node.rightNode==null) {
            node.rightNode=addNode;
            addNode.parent=node;
         }
         else findAdd(node.rightNode,addNode);
      }
   }
   
   public boolean remove(int remove){
      //find the node to delete
      TreeNode removeNode=find(remove);
      if (removeNode != null){
         System.out.println(remove+" found, removing");
         if (removeNode.leftNode==null && removeNode.rightNode==null){
            if (removeNode.parent.leftNode==removeNode) removeNode.parent.leftNode=null;
            else if (removeNode.parent.rightNode==removeNode) removeNode.parent.rightNode=null;
         //look at left node
         } else if(removeNode.parent.leftNode!=null){// || removeNode.parent.rightNode!=null){
            if (removeNode.parent.leftNode==removeNode) {
               removeNode.parent.leftNode=removeNode.leftNode;
               removeNode.parent.rightNode=removeNode.rightNode;
            }
            if (removeNode.parent.rightNode==removeNode) {
               removeNode.parent.rightNode=removeNode.leftNode;
            }
         }
                     System.out.println("case2");
            System.out.println("parrent's data: "+removeNode.parent.data);
            System.out.println("parrent's right data: "+removeNode.parent.rightNode.data);
            System.out.println("remove's data: "+removeNode.data);
            System.out.println("remove left's data: "+removeNode.leftNode.data);
      }
      return false;
   }
   
   public TreeNode find(int search){
      if (root !=null) return findNode(root,new TreeNode(search));
      return null;
   }
   
   public TreeNode findNode(TreeNode search, TreeNode node){
      TreeNode returnNode;
      if (search ==null) return null;
      if (search.data==node.data) return search;
      else {
         returnNode=findNode(search.leftNode,node);
         if(returnNode==null) returnNode=findNode(search.rightNode,node);
      } 
      
      return returnNode;
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
   
   public boolean equals(Tree tree){
      boolean comp=true;
      if (tree == null) return false;
      if (tree.root !=root) return false;
      
      return comp;
   }
   public boolean nodeComp(TreeNode one, TreeNode two){
      
      if (one.equals(two)) return true;
      else return false;
   }

   /*
    * Get the tree treeNode
    */
   public TreeNode getRoot() {
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
class TreeNode {
   protected int data; //should make getter/setter for java 
   protected TreeNode rightNode;
   protected TreeNode leftNode;
   protected TreeNode parent;
   
   //pass to 3 argument constructor
   TreeNode(int object){
      this(object,null,null);
   }   
   
   //pass to 3 argument constructor   
   TreeNode(int object,TreeNode node){
      this(object,node,null);
   }
   
   TreeNode(int object,TreeNode right, TreeNode left){
      data=object;
      rightNode=right;
      leftNode=left;
   }
   
   //@Override
   public boolean equals(TreeNode obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      TreeNode other = (TreeNode) obj;
//      if (data == null) {
//         if (other.data != null) {
//            return false;
//         }
//      } else 
      if (data !=other.data) {
         return false;
      }
      return true;
   }
   
   
//   public boolean isEmpty() {
//      return (parent == null);
//   }
}