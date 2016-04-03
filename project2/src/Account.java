/**
 * @Author:  Dave Borncamp 0577058
 * @Version: date 2/11/14 , COSC600 Assignment: project2
 *
 * Purpose of program:
 *  This class is the parent class for all other accounts. It will maintain some
 * of the attributes associated with all accounts.
 * 
 */

public class Account {
   //all properties of all account types so set here:
   private int number;
   private double openBalance;
   private double endBalance;
   private String name;
   private String SSN;
   private String phone;
   
   public Account(String name,int number,double amount,String ssn,String phone){
      setName(name);
      setNumber(number);
      setOpenBalance(amount);
      setEndBalance(amount);//initially this is true
      setSSN(ssn);
      setPhone(phone);
   }
   
   /**
    * Stubbed method that gets overridden by child classes.
    */
   public void printInfo(){

   }
   public void addInterest(){
   }
   
   /**
    * @return the number
    */
   public int getNumber() {
      return number;
   }

   /**
    * @param number the number to set
    */
   public void setNumber(int number) {
      this.number = number;
   }

   /**
    * @return the openBalance
    */
   public double getOpenBalance() {
      return openBalance;
   }

   /**
    * @param openBalance the openBalance to set
    * Also sets the ending balance. This probably should be handled in a 
    * constructor. - handled in new version!
    */
   public void setOpenBalance(double openBalance) {
      this.openBalance = openBalance;
   }

   /**
    * @return the name
    */
   public String getName() {
      return name;
   }

   /**
    * @param name the name to set
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * @return the SSN
    */
   public String getSSN() {
      return SSN;
   }

   /**
    * @param SSN the SSN to set
    */
   public void setSSN(String SSN) {
      this.SSN = SSN;
   }

   /**
    * @return the phone
    */
   public String getPhone() {
      return phone;
   }

   /**
    * @param phone the phone to set
    */
   public void setPhone(String pnone) {
      this.phone = pnone;
   }

   /**
    * @return the endBalance
    */
   public double getEndBalance() {
      return endBalance;
   }

   /**
    * @param endBalance the endBalance to set
    */
   public void setEndBalance(double endBalance) {
      this.endBalance = endBalance;
   }

}
