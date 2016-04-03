/**
 * @Author:  Dave Borncamp 0577058
 * @Version: date 2/11/14, COSC600 Assignment: Project2
 *
 * Purpose of program:
 *  Extends the Account class to make a checking account. 
 * I generally like compact code so the lines are very succinct.
 * 
 */


public class SavingsAccount extends Account{
   //the rate will never change for this assignment, so it is private and final.
   //property of a checking account so it is set here.
   private double rate =0.0;  //in %
   
   //constructor
   SavingsAccount(String name,int number,double amount,String ssn,String phone){
      //set the inherited values.
      super(name,number,amount,ssn,phone);
      setRate();
   }

   /**
    * @return the rate
    */
   public double getRate() {
      if ( (int)rate ==0) setRate();
      return rate;
   }
   /**
    * @param number the number to set
    */
   private void setRate(){
      //dont need an if statement here can use ternary operators!
      rate=(getOpenBalance() < 5000.0) ? .04 :.05;
//      if (this.getOpenBalance() < 5000.0) rate=.04;
//      else rate=.05;
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