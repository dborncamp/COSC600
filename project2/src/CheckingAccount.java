/**
 * @Author:  Dave Borncamp 0577058
 * @Version: date 2/11/14, COSC600 Assignment: Project2
 *
 * Purpose of program:
 *  Extends the Account class to make a checking account. 
 * I generally like compact code so the lines are very succinct.
 * 
 */


public class CheckingAccount extends Account{
   //the rate will never change for this assignment, so it is private and final.
   //property of a checking account so it is set here.
   private final double rate = 0.025;//in %
   
   //constructor
   CheckingAccount(String name,int number,double amount,String ssn,String phone){
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
    * calculate the interest for a checking account.
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