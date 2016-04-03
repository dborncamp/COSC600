
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Scanner;

/**
 * @Author:  Dave Borncamp 0577058
 * @Version: date , COSC600 Assignment: project 1
 *
 * Purpose of program:
 *  To read in a file containing test scores, assign outstanding, 
 * satisfactory and unsatisfactory grades to them and write that to a file.
 * 
 *  This class is to contains methods to read in a file, assign grades and 
 * write the file. 
 * 
 *  I used this as an excuse to play with java, so there are some unnecessary 
 * things in here, but it was good to learn.
 * 
 */


public class Worker {
   private static Formatter output;
   private   Scanner input;
   private   ArrayList<Integer> data = new ArrayList<>();
   
   //open the data file, handle it's crashes
   private   void openInFile(String fileName){
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

   public int[] getData(String fileName){
      openInFile(fileName);
      while (input.hasNext()){
         data.add(Integer.parseInt(input.next()));
      }
      //make sure to close the file nicely.
      input.close();
      
      //data.toArray method will just return an Object[], I want int[], so make it
      int[] values = new int[data.size()];
      for (int i=0;i<values.length;i++) values[i]=data.get(i);
      
      return values;
   }
   
   public char[] getCharacter(int[] values){
      char[] character = new char[values.length];
      int sum=0;
      for (int i=0;i<values.length;i++){
         sum=sum+values[i];
      }
      double avg=(double)sum/(double)values.length;
      
      for (int i=0;i<values.length;i++){
         if (values[i] > avg*1.1) character[i]='O';
         else if (values[i] < avg*.9) character[i]='U';
         else character[i]='S';
      }      
      return character;
   }
   
   private   void openOutFile(){
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
   private  static void addRecords(int num, char grade){
      try{
         
         output.format("%4d%7c\n",num,grade);
         //output.format("%s5","Hello");
      }catch(FormatterClosedException e){
         System.out.println("error writing to text file");
      } finally{
         //output.close();
      }
   }
   
   public void makePretty(int[] values, char[]grades){
      openOutFile();
      output.format("%s\n", "Precent Grade");
      for (int i=0;i<values.length;i++) {
         addRecords(values[i],grades[i]);
      }
      output.close();
   }
}