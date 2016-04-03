/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 3.0
 * @date 3/30/14, COSC600
 * @Assignment: Optional Assignment 1
 *
 * Purpose of program:
 *    This program is supposed to take an infix expression and return a postfix.
 * This follows the algorithm laid out in class in the linked list presentation 
 * and uses a queue class similar to my linked list class. It also uses the 
 * built in stack, which is allowed in the assignment instructions.
 * 
 */

import java.util.StringTokenizer;
import java.util.Stack;

public class Convertor {

   /**
    * @param - inExpression, the infix expression to be changed to a postfix 
    * expression.
    * @return - Postfix expression of inExpresion as a string.
    * 
    * Converts an infix expression to a postfix expression. This follows the 
    * algorithm given in the linked list lecture. It is pretty simple, and it 
    * works well.
    */
   public String inToPost(String inExpression) {
      Stack stack = new Stack();
      Queue queue = new Queue();
      String returnString;
        
      // Split the expression into tokens by operators and whitespaces
      StringTokenizer token = new StringTokenizer(inExpression, "+-*/^() ", true);        
       
      do {
         String x = token.nextToken();
         char c = x.charAt(0);
           
         if (!x.equals(" ")) {
            // Manipulate stack and queue based on token
            if (Character.isDigit(c)) queue.enq(x);
            else if (c == '('||stack.isEmpty()) stack.push(x);
            else if (c == ')') {
               while (((String) stack.peek()).charAt(0) != '(') {
                  queue.enq(stack.pop());
                }
                stack.pop();
             } else{
                while (!stack.isEmpty() && priority(c) <= 
                        priority(((String) stack.peek()).charAt(0))) {
                   queue.enq(stack.pop());
                }
                stack.push(x);
             }
         }   
       } while (token.hasMoreTokens());
        
       //if the stack is not empty, stick it in the queue.
       if (!stack.isEmpty()) queue.enq(stack.pop());
       
       returnString = getString(queue);
        
       return returnString;
    }
    
   /**
    * @param c - character to find the priority of. Must be an operator.
    * @return the priority of the character. It is an integer ranged 0 to 3
    * 
    * Very simple method that returns the priority of the character.
    */
   private int priority(char c) {
      if (c == '^') {return 3;} 
      else if (c == '*' || c == '/') {return 2;} 
      else if (c == '+' || c == '-') {return 1;} 
      else {return 0;}
   }
    
    /**
     * @param queue - The queue to feed it, this contains the postfix expression.
     * @return - The queue as a single string. All of the elements will be 
     * separated by white space.
     * 
     * This function returns the string that is contained as elements of a queue.
     * It assumes that the queue is built using strings, which is not a 
     * constraint for my queue class, but is built this way in this program.
     */
   private String getString(Queue queue) {
      String returnString = "";
        
      // build a string with the contents of the queue separated by whitespace
      while (!queue.isEmpty()) {
         returnString += queue.peek();
         returnString += " ";
         queue.dnq();
      }
      return returnString;  
    }
}