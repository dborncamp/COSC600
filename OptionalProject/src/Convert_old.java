
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;




public class Convert_old {
   
   private enum Operator{
      add("+"),sub("-"),multy("*"),div("/"),power("^"),left("("),right(")"),unknown("");
      String value;
      private Operator(String s){
         this.value=s;
      }
   }
   
   //convert infix to postfix
   public String inToPost(String globalString){
      String postFix="";
      String string;
      int i=0;
      Stack stack=new Stack();
      
      StringTokenizer token=new StringTokenizer(globalString," ()");
      
      while(token.hasMoreTokens()){
         System.out.println("The number is "+i);
         string=token.nextToken();
         System.out.println("String is "+string);
         try{
            Integer number=Integer.parseInt(string);
            postFix=postFix.concat(string+" ");
            //System.out.println("Integer! ");
         }catch (Exception e){
            //System.out.println("not integer");
            if (stack.isEmpty()) stack.push(string);
            else if (string.equals("+")||string.equals("-")||string.equals("/")
                    ||string.equals("*")||string.equals("^")){//||string.equals("(")
                    //||string.equals(")")){
               stack.push(string);
            }
            else if (string.equals("("))stack.push(string);
            else if (string.equals(")")) while (!stack.peek().equals("(")){
               String test = "test";
               test= stack.pop().toString()+" ";
               System.out.println("Test is: "+test);
               postFix= postFix.concat(test);//stack.pop().toString()+" ");
            }
         }
         i++;
         System.out.println("  The post fix is: "+postFix);
         System.out.println("  The stack is: "+stack);
      }
      while(!stack.isEmpty()){
         System.out.println("The stack is "+stack);
         String next = stack.pop().toString();
         if (!next.equals("(")&&!next.equals(")")) postFix=postFix.concat(next);
      }
      System.out.println("stack is "+stack.toString());
      return postFix;
      }
     
}