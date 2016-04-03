/**
 * @Author:  Dave Borncamp 0577058
 * @Version: date 2/11/14, COSC600 Assignment: Project 2
 *
 * Purpose of program:
 *  Extends the Account class to make a Business account. This is very similar 
 * to the CheckingAccount except the rate is zero and so the interest 
 * calculation is a little different.
 * 
 */


public class BusinessAccount extends Account{
   //the rate will never change for this assignment, so it is private and final.
   private final double rate = 0.0; //in%
   
   //constructor
   BusinessAccount(String name,int number,double amount,String ssn,String phone){
      //set the inherited values.
      super(name,number,amount,ssn,phone);
   }

/**
    * @return the rate
    */
   public double getRate() {
      return rate;
   }
   
   /**
    * @return the calculated interest rate.
    * calculate the interest for a Business account, which is 0.0%, it should 
    * always return 0.
    */
   public double calcInterest(){
      return getOpenBalance()*rate;
   }

   /**
    * make the interest calculation and add it to the balance
    */
   public void addInterest(){
      setEndBalance(getOpenBalance()+calcInterest());
   }
   
   /**
    * May not want here until I know what kind of output is required.
    */
   @Override
   public void printInfo(){
      System.out.println(getName()+" "+getSSN()+" "+getNumber()+" "+
              getPhone()+" "+getOpenBalance()+" "+getEndBalance());
   }
}
