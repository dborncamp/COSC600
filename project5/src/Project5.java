/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 1.0
 * @date 4/12/14, COSC600
 * @Assignment: project 5
 *
 * Purpose of program:
 *    This is a driver program for project 5 on trees. It tests the trees we 
 * are assigned to build.
 */


public class Project5 {
   
   public static void main(String[] args){
      Tree t1=new Tree();
      
      t1.build("60, 20, 100, 35, 15, 200, 75, 150, 6, 17, 40");
      
      System.out.println("\nTraversing t1 in order:");  
      t1.inOrder();
      
      System.out.println("\nThe count of t1 is: "+t1.count());
      
      Tree t2=new Tree(t1.getRoot());
      t2.swap(); //want the swap of t1
      
      System.out.println("\nTraversing t2 post order");
      t2.postOrder();
      
      System.out.println("\nAre t1 and t2 equal(it is swapped)? "+t1.compare(t2));
      
      System.out.println();
      Tree t3 = new Tree();
      t3.build("60, 100, 20, 15, 35, 75, 200, 6, 17, 40, 150");
      
      System.out.println("\nAre t1 and t3 equal: "+t1.compare(t3));
      
      System.out.println("\nTraversing t3 in order"); 
      t3.inOrder();
      
      System.out.println();
      t3.inOrder(5);
      System.out.println();
   }
}
