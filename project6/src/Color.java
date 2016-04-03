/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 0.0
 * @date , COSC600
 * @Assignment: 
 *
 * Purpose of program:
 *
 */


   
public enum Color{NONE,RED,GREEN,BLUE,YELLOW;

   /**
    * 
    * @return- The next color in the enum.
    * @throws- NoMoreEnumException.
    * 
    *    Returns the next color in the enum class. will throw a NoMoreEnumException
    * if there are no more elements left in the enum.
    */
   public Color next() throws NoMoreEnumException {
      //System.out.println("Length is: "+values().length+" ordinal is: "+ordinal());
       if (ordinal()+1 == values().length ){
          //return this.RED;
          throw new NoMoreEnumException();
       }
       return values()[ordinal() + 1];
   }
   
}

   /**
    * 
    * No more elements in the enum exception class.
    */
   class NoMoreEnumException extends RuntimeException{
      public NoMoreEnumException(){
         //System.out.print("** No more enums! ");
      }
   }
