/**
 *
 * @Author:  Dave Borncamp 0577058
 * @Version: date 2/1/14 , COSC600 Assignment project1
 *
 * Purpose of program:
 *  This initializes and plays with the rather non-descript Worker class (I 
 * probably should have named it something to describe what it does).
 */

public class Driver {
   
   public static void main(String[] args){
      Worker work = new Worker();
      int[] values = work.getData("DataValues.txt");
      char[] character = work.getCharacter(values);
      work.makePretty(values, character);
   }
   
}
