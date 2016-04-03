/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 0.0
 * @date , COSC600
 * @Assignment: 
 *
 * Purpose of program:
 *
 */


public class sTreeNode {
   
   protected int data; 
   protected TreeNode rightNode;
   protected TreeNode leftNode;
   
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
