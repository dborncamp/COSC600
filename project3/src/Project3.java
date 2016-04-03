/**
 * @Author:  Dave Borncamp 0577058
 * @Version: date 2/27/14, COSC600 Assignment: Project3
 *
 * Purpose of program:
 *   This contains the various algorithms that are required for project 3.
 *  
 */


public class Project3 {
   
   public static void main(String[] args){
      double[] input={1,2,4,6,7,8,9.5,2.5,3.4};
      p4(input);
      p5(input,12);
      System.out.println(func(5));
      //toTheN(30);
      //justN(100000000);
   }
   
   /*O(n)
    * My answer for question 4. It is O(n) and assumes that it is a sorted 
    * sequence.
   */
   public static void p4(double[] in){
      double sum=0;
      for(int i=0;i<in.length;i++){
         sum=sum+in[i];
      }
      System.out.println("Number that is not insequence: "+sum);
   }
   
   //O(nlogn)
   public static void p5(double[] S, double x){
      double d1;
      boolean eq=false;
      for (int i=0;i<S.length;i++){
         d1=S[i];
         for (int j=i;j<S.length;j++){
            if (S[j]*d1==x){
               System.out.println(d1+" * "+S[j]+" equals "+x);
               eq=true;
            }
         }
      }
      if (!eq) System.out.println("Nothing equals "+x); 
   }
   
   public static int func(int n){ 
      //System.out.print("\n");
	if (n == 0 || n == 1){ 
         return 1;
      } else{
         //System.out.print(n+" ");
	   return 2*func(n-2) + 3*func(n-1);
      }
   }
   

   public static void toTheN(int n){
      final long startTime = System.currentTimeMillis();
      double power =1;
	for(int j=1; j< n; j++){
	   power = power * 2;
	   for(double k=1; k< power; k++){
            System.out.println(j);
         }
      }
      final long endTime = System.currentTimeMillis();
      System.out.println("Total execution time: " + (endTime - startTime)/1000 );
   }
   
   public static void justN(int n){
      for (int i=0; i<n; i++){
         System.out.print(0);
      }
      System.out.println();
   }
}