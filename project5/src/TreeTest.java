/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 0.0
 * @date , COSC600
 * @Assignment: 
 *
 * Purpose of program:
 *
 */


public class TreeTest {
   
   public static void main(String[] args){
      Tree t3=new Tree();
      Tree t1=new Tree();
      
//      tree.add(9);
//      tree.add(6);
//      tree.add(4);
//      tree.add(50);
//      tree.add(45);
//      tree.add(8);
//      tree.add(7);
//      //tree.add("a");
//      Tree tree1=new Tree();
//      tree1.build("60, 20, 100, 35, 15, 200, 75");
//      
//      tree1.inOrder();
//      System.out.println(" inroder int");
//      tree1.inOrder(8);
      
      t3.build("60, 100, 20, 15, 35, 75, 200, 6, 17, 40, 150");
      t1.build("60, 20, 100, 35, 15, 200, 75, 150, 6, 17, 40");
      t1.inOrder();
      t3.inOrder();
      //Tree t2=new Tree(t1.getRoot());
      TreeNode node=t1.getRoot();
      Tree t2=new Tree(t1.getRoot());
      t2.inOrder();
      t2.remove(60);
      t1.inOrder();
      t2.inOrder();
   }
   
}
