
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @Author:  Dave Borncamp 0577058
 * @Version: date 02/11/14, COSC600 Assignment: project2
 *
 * Purpose of program:
 *  This is the main method for the account project. It also reads in the client
 * file. This is NOT a clean way to read things in, but the point of this 
 * assignment is to learn polymorphism and inheratence, not read in files.
 */


public class Driver {
   //set global variable. They must be static to be referenced by main method
   private static Scanner input;  
   private static Account[] accounts=new Account[6];
   
   //Do work
   public static void main(String[] args){
      popArray();
      
      //print the output in a polymorphic way
      for(int i=0;i<6;i++){
         //accounts[i].addInterest();
         accounts[i].printInfo();
      }
   }
   
/*
 * Parses the client.dat file and populates the accounts array.
 */
   private static void popArray(){
      int counter=0;
      //probably not the best way to read in teh data, but that's not what this 
      //assignment is about.
      openInFile("client.dat");
      while (input.hasNext()){
         String name=input.next();
         int number=Integer.parseInt(input.next());
         String phone=input.next();
         String ssn=input.next();
         int begin=Integer.parseInt(input.next());
         String type=input.next();
         
         /*
          * I know this is a really messed up way to set things but it shows 
          * that things are inherated and there is a subclass which is the point
          * of this assignment.
          */
         if ("C".equals(type)) {
            accounts[counter]= new CheckingAccount(name,number,begin,ssn,phone);
            ((CheckingAccount)accounts[counter]).addInterest();
         }
         if ("S".equals(type)) {
            accounts[counter]= new SavingsAccount(name,number,begin,ssn,phone);
            ((SavingsAccount)accounts[counter]).addInterest();
         }
         if ("B".equals(type)) {
            accounts[counter]= new BusinessAccount(name,number,begin,ssn,phone);
            ((BusinessAccount)accounts[counter]).addInterest();
         }
         counter++;
      }
      //make sure to close the file.
      input.close();
   }

/*
 * @param fileName
 * Open the file to read in.
 * This must be a static method in this class as it is referenced from main (a 
 * static method itself).
 */   
   private static void openInFile(String fileName){
      try{
         input=new Scanner(new File(fileName)); 
      }catch(SecurityException securityExemption){
         System.out.println("No permissions for you!");
         System.exit(1);
      }catch(FileNotFoundException fileNotFoundException){
         System.out.println("File not found");
         System.exit(1);
      }
   }
   
}
