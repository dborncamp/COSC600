/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 0.0
 * @date , COSC600
 * @Assignment: 
 *
 * Purpose of program:
 *
 */


public class OptionalTest {
   
   public static void main(String[] args){
      String str="10*(4-3)";
      //String str="(4 + 3) - 6";
      
      Convert_old convert =new Convert_old();
      
      //System.out.println("end is: "+convert.inToPost(str));
      //System.out.println(str.concat("a"));
      
      Convertor convert1 = new Convertor();
      System.out.println("Starting str: "+str);
      System.out.println("convert1 is: "+convert1.inToPost(str));
      str.concat("hi");
      System.out.println(str);
      System.out.println(str.concat("hi"));
      
   }
   
}
