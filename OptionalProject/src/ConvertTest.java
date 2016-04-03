/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 0.0
 * @date , COSC600
 * @Assignment: 
 *
 * Purpose of program:
 *
 */


public class ConvertTest {
   
   public static void main(String[] args){
      Convertor convert1 = new Convertor();
      String str="10*(5–3)+75/5^2*(2+3)";
      
      System.out.println("input is: "+str);
      System.out.println("output is: "+convert1.inToPost(str));
      
      str="8 / 4 + 7 * ( 5 + 2) – 33 * 2  ";
      System.out.println("\ninput is: "+str);
      System.out.println("output is: "+convert1.inToPost(str));
      
      str="(5 + 3) * 2 – 10 + 4 * (4-1)";
      System.out.println("\ninput is: "+str);
      System.out.println("output is: "+convert1.inToPost(str));
   }
   
}
