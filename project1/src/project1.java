
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Scanner;

/**
 *
 * @Author:  Dave Borncamp 
 * @Version: date , COSC-600 Assignment 1
 *
 * Purpose of program:
 *  Read in a data file and assign outstanding, satisfactory and unsatisfactory 
 * grades to them. The output should be a file that contains a labled list of 
 * the grades and the character assigned to them. 
 *  This is also a good opertunity to try out things in java and learn how to 
 * program in it.
 */


public class project1 {
   private static Formatter output;
   private static Scanner input;
   private static ArrayList<Integer> data = new ArrayList<>();
   
//   public static void main(String[] args){
//      int sum=0;
//      int counter=0;
//      double avg;
//      openInFile();
//      openOutFile();
//      //write the column headers
//      output.format("%s\n", "Precent Grade");
//      while (input.hasNext()){
//         int temp=input.nextInt();
//         //System.out.println(temp);
//         sum=sum+temp;
//         counter++;
//         avg=sum/counter;
//         data.add(temp);
//      }
//      //System.out.println(data);//.toArray());
//      input.close();
//      output.close();
//   }
   
   //open the data file, handle it's crashes
   private static void openInFile(){
      try{
         input=new Scanner(new File("DataValues.txt")); 
      }catch(SecurityException securityExemption){
         System.out.println("No permissions for you!");
         System.exit(1);
      }catch(FileNotFoundException fileNotFoundException){
         System.out.println("File not found");
         System.exit(1);
      }
   }
   private static void openOutFile(){
      try{
         output=new Formatter("matchedscores.txt");
         
      }catch(SecurityException securityExemption){
         System.out.println("No permissions for you");
         System.exit(1);
      }catch(FileNotFoundException fileNotFoundException){
         System.out.println("File not found");
         System.exit(1);
      }
   }
   //add a record to the output file
   private static void addRecords(int num, char grade){
      try{
         output.format("%4d%7c\n",num,grade);
         //output.format("%s5","Hello");
      }catch(FormatterClosedException e){
         System.out.println("error writing to text file");
      }
   }
}
