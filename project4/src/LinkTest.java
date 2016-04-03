/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 0.0
 * @date , COSC600
 * @Assignment: 
 *
 * Purpose of program:
 *
 */


public class LinkTest {
   
   public static void main(String[] args){
      List list = new List();
      
      try{
         list.removeFromFront();
      }catch (EmptyListException e){
         e.getLocalizedMessage();
         e.toString();
         System.out.println("catch");
      }
      list.add("One point 1");
      list.add("two");
      list.add("three");
      list.add("four point five");
      list.add("five");
      list.add("six");
      list.line(1);
      list.search("One");
//      System.out.println(list.num_lines);
//      
//      list.print();
//      //System.out.println(list.getData().getClass());
//      //list.insertAtFront("hello");
//      
//      System.out.println();
//      System.out.println(list.num_lines);
//      list.insertBef("hi", "ho");
//      list.insertBef("would", "I");
//      System.out.println(list.num_lines);
//      
//      list.print();
//      
//      list.remove("ho");
//      list.print();
//      
//      list.search("would");
//      System.out.println("current index: "+list.currIndex);
//      System.out.println("number of lines: "+list.num_lines);
//      
//      //list.pIndex(3); 
//      System.out.println();
      list.print(1,6);
      
      
//      System.out.println();
//      list.print();
//      list.delete(2,4);
//      //list.remove("rather");
//      list.print();
//      System.out.println(list.currIndex);
//      System.out.println(list.num_lines);
//      
//      System.out.println();
//      list.insert("The-test");
//      System.out.println(list.currIndex);
//      System.out.println(list.num_lines);
//      list.insert("went");
//      
//      list.line(3);
//      System.out.println("data: "+list.currentNode.data);
//      list.print();
//      
//      list.insert(" 3 ");
//      list.print();
   }
   
}
