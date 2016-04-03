import java.util.Scanner;
import java.util.StringTokenizer;
import java.lang.NumberFormatException;

/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 2.0
 * @date 3/15/14, COSC600
 * @Assignment: Project 4
 *
 * Purpose of program:
 *  This program is to use linked lists to manipulate things. I am to have 
 * methods that can insert, delete, print, search, set the current line and 
 * terminate the execution based on user input. It is also supposed to error 
 * check the input from the user to make sure it is valid.
 * 
 *    This version now takes care of things in an efficient way. It uses an enum 
 * class to control the command structure of the program.
 * It can also correctly handle exceptions. and handle a comma between indeces.
 * 
 * **Some debugging lines are left commented out.** probably should have used 
 * some assert lines instead, but this was quicker.
 */


public class Editor {
   Scanner input=new Scanner(System.in); //user input
   StringTokenizer tempToken; //a temporary token that can be used in various places
   List list= new List(); //create the list.
   String globalString; //The string that can contain the command. This needs
   //to be global to work between insert and other decisions.
   Command com; //the command enum class.

   /*
    * Constructor. This si a class driven method. 
    * Set the innitial command of unknown and send to decision method.
    */
   Editor(){
      //set the starting command. It will start with unknown.
      com=Command.UNKNOWN;
      decision();
   }
   
   /*
    * The command line possibilities.
    */
   private enum Command{INSERT,DELETE,PRINT,LINE,SEARCH,DONE,UNKNOWN};
   
   /*
    * Method to make decisions. It will be based off the enum command.
    */
   private void decision(){

      int ind1;
      int ind2;
      //System.out.println(input.delimiter());
      //int lenI="$insert".length();
      String str;//,string; //= s.substring(0, lenI);
      //StringTokenizer token;//=new StringTokenizer(s);
      //globalString=input.next();
      System.out.println("Welcome to the line editor!");
      System.out.println("  Enter $done at anytime to finish the program.");
      System.out.println("  Operations are $insert, $delete, $print, $line, and $search.");
      
      //while the user does not enter done
      while( com !=Command.DONE){
         //make the decision:
         switch(com){
            case INSERT:
               com=Command.INSERT;
               insert();
               break;
               
            case DELETE:
               //try to insert into list, may be empty
               try{
                  ind1=0;
                  ind2=0;
                  //System.out.println("in delete case ");
                  //System.out.println("globalString "+globalString);
                  tempToken = new StringTokenizer(globalString,", ");
                  tempToken.nextToken();
                  //try to parse integers, may not be integers
                  try{
                     ind1=Integer.parseInt(tempToken.nextToken());
                     ind2=Integer.parseInt(tempToken.nextToken());
                     //System.out.println("    indicies:"+ind1+" "+ind2+"all");
                     list.delete(ind1,ind2);
                  }catch(NumberFormatException exp){
                     System.out.println("** Need integer for indicies");
                     System.out.println();
                  }

               } catch (EmptyListException e){
                  System.out.println("Cannot delete.");
               }
               setCom();
               break;
               
            case PRINT:
               //try to print the list, may be empty
               try{
                  ind1=0;
                  ind2=0;

                  tempToken = new StringTokenizer(globalString,", ");
                  tempToken.nextToken();
                  //System.out.println("print Token is "+tempToken);
                  if (tempToken.hasMoreTokens()){
                     //try to parse integers, may not be integers
                     try{
                        ind1=Integer.parseInt(tempToken.nextToken());
                        ind2=Integer.parseInt(tempToken.nextToken());
                        //System.out.println("indicies:"+ind1+" "+ind2);
                        list.print(ind1, ind2);
                     }catch(NumberFormatException exp){
                        System.out.println("** Need integer for indicies");
                        System.out.println();
                     }
                  }else list.print();

               } catch(EmptyListException e){
                  System.out.println(" Cannot print between indicies.");
               }
               setCom();
               break;
               
            case LINE:
               //Try to print the list, may be empty.
               try {
                  ind1=0;
                  //System.out.println("in line case");
                  //System.out.println("globalString "+globalString);
                  tempToken = new StringTokenizer(globalString);
                  tempToken.nextToken();
                  if (tempToken.hasMoreTokens()){
                     //try to parse integers, may not be integers.
                     try{
                        ind1=Integer.parseInt(tempToken.nextToken());
                        //System.out.println("indicies:"+ind1);
                        list.line(ind1);
                     }catch(NumberFormatException exp){
                        System.out.println("** Need integer for indicies");
                        System.out.println();
                     }
                  } 
               } catch(EmptyListException e){
                  System.out.println(" Cannot set line");
               }
               setCom();
               break;
               
            case SEARCH:
               try{
                  tempToken = new StringTokenizer(globalString);
                  tempToken.nextToken();
                  str=tempToken.nextToken("\n").toString().trim();
                  //System.out.println("search Token is |"+str+"|");
                  list.search(str);

               } catch(EmptyListException e){
                  System.out.println(" Cannot search");
               }
               setCom();
               break;
               
            case UNKNOWN:
               System.out.println("  Unknown command, please try again: ");
               setCom();
               break;
         }
      } 
      //close the scanner
      input.close();
   }
   
   /*
    * Sets the current command to be interpreted by decision method.
    * This will block until the user inputs something.
    */
   private void setCom(){
      //set the strings to be used in this method.
      String command,str;
      
      //special case of coming out of insert method
      if (com==Command.INSERT){
         //System.out.println();
         //System.out.println("current state is insert "+globalString);
         //System.out.println();
         tempToken = new StringTokenizer(globalString,", ");
         command=tempToken.nextToken();
         str=globalString;
         //System.out.println(command);
      }else{
         System.out.println();
         System.out.println("Please enter a command: ");
         
         str=input.nextLine();
         if (str.isEmpty()) str=input.nextLine();
         //System.out.println("  str is:"+str+"|");
         
         tempToken = new StringTokenizer(str);
         command=tempToken.nextToken();
      }
      
      //set the command
      switch (command) {
         case "$done":
            com=Command.DONE;
            break;
         case "$insert":
            com=Command.INSERT;
            break;
         case "$line":
            com=Command.LINE;
            globalString=str;
            break;
         case "$print":
            com=Command.PRINT;
            globalString=str;
            break;
         case "$search":
            com=Command.SEARCH;
            globalString=str;
            break;
         case "$delete":
            com=Command.DELETE;
            globalString=str;
            break;
         default:
            com=Command.UNKNOWN;
            break;
      }
   }
   
   /*
   //this method assumes that at lease one input will be given after the method
   //is entered. 
   */
   private void insert(){
      System.out.println("Please enter text to insert into list: \n");
      
      //I assume that there will be at least one line inserted
      while(com==Command.INSERT){
         //System.out.println("in while");
         globalString=input.nextLine(); 
         //System.out.println(globalString);
         //System.out.println("globalString: "+globalString);
         if (globalString.startsWith("$")) setCom();
         else{
            //System.out.println(globalString);
            list.insert(globalString);
            //System.out.println("globalString: "+globalString);
         }
         //System.out.println(com);
      }
      //System.out.println("globalString: "+globalString);
   }
   
}
