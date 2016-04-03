import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 2
 * @date 4/1/14, COSC600
 * @Assignment: Optional Project 1
 *
 * Purpose of program:
 *    This is a GUI wrapper for the infix to postfix converter that I made for
 * the other portion of the project. It displays a GUI and works as described in
 * the project requirements. It contains its own main method as well as several
 * inner classes to implement action listeners.
 */


public class PRNCalcGUI extends javax.swing.JFrame{
   //set things up.
   private String inFix=" ";
   private String postFix=" ";
   private String temp="";
   Convertor convert = new Convertor();
   //get a nice font that is big and easy to read
   private final Font BIGGER_FONT = new Font("monspaced", Font.PLAIN, 22);
   //instantiate the display fields - they will be used later so need global.
   private JTextField in_displayField;
   private JTextField out_displayField;
   
    
   //Main method that runs the program
   public static void main(String[] args){
      PRNCalcGUI test=new PRNCalcGUI();
      test.initCommponents();
      test.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      test.setSize(500,500);
      test.setVisible(true);
   }
   
   
   /**
    * Initialize the GUI. Places things where they need to go and hooks up the
    * action listeners.
    */
   private void initCommponents(){

    //Set up default things in the gui
      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setTitle("InFix Convert");
      setPreferredSize(new java.awt.Dimension(300, 400));
      setResizable(false);
      setLocationRelativeTo(null);
      
      //set up the display fields
      in_displayField = new JTextField(" ", 20);
      in_displayField.setHorizontalAlignment(JTextField.RIGHT);
      in_displayField.setFont(BIGGER_FONT);
      in_displayField.setEditable(false);
      
      out_displayField = new JTextField(" ", 20);
      out_displayField.setHorizontalAlignment(JTextField.RIGHT);
      out_displayField.setFont(BIGGER_FONT); 
      out_displayField.setEditable(false);
        
      //lay out the buttons and set the action listeners
      String buttonOrder = "789+^456- 123* (0)/ CSQ."; //layout of buttons.
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));
      for (int i = 0; i < buttonOrder.length(); i++) {
         String keyTop = buttonOrder.substring(i, i+1);
         JButton b = new JButton(keyTop);
         switch (keyTop) {
            case " ":
               //Put a dummy button in this position.
               b.setEnabled(false);
               break;
            case "C":
               b= new JButton("Clear");
               b.setFont(BIGGER_FONT);
               b.addActionListener( new ClearListener());
               break;
            case "S":
               b= new JButton("Solve");
               b.setFont(BIGGER_FONT);
               b.addActionListener( new EvalListener());
               break;
            case "Q":
               b= new JButton("Quit");
               b.setFont(BIGGER_FONT);
               b.addActionListener( new ExitListener());
               break;
            default:
               //anything that is not the other 3 buttons have the same action listener
               b.addActionListener(new ButtonListener());
               b.setFont(BIGGER_FONT);
               break;
         }
      //add the button to the frame;
      buttonPanel.add(b);
      }      
      
      //stick things on the whole pannel
      JPanel content = new JPanel();
      content.setLayout(new BorderLayout(5, 5));
      content.add(in_displayField, BorderLayout.NORTH );
      content.add(buttonPanel   , BorderLayout.CENTER);
      content.add(out_displayField    , BorderLayout.SOUTH );
      
      //make it look nice with a border
      content.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
      setContentPane(content);
      //let it be seen
      pack();

   }
   
   /**
    * Action method for the clear button. Things are also cleared after a solve
    * so it was best to put it in a method here
    */
   private void actionClear() {
      //_startNumber = true;         // Expecting number, not op.
      in_displayField.setText(" ");
      out_displayField.setText(" ");
      inFix=" ";
      postFix=" ";
      temp="";
      //_previousOp  = "=";
   }
    
   /**
    * Exit listener inner class. This will exit the program when the exit button
    * is clicked.
    */
    class ExitListener implements ActionListener{
       @Override
       public void actionPerformed(ActionEvent e){
          System.exit(0);
       }
    }
       
   /**
    * Clear listener inner class. This will clear the display when the clear button
    * is clicked.
    */
   class ClearListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         actionClear();
      }
   }   

   /**
    * Eval listener inner class. This will evaluate the program when the solve
    * button is clicked. It calls the convert class I wrote before to get this
    * done.
    */
   class EvalListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         //this checks to make sure we are not currently building a decimil number
         //if we are, dump it into the infix and display it.
         if (!temp.isEmpty()) {
            inFix=inFix+temp;
            in_displayField.setText(inFix);
         }
         postFix=convert.inToPost(inFix);
         out_displayField.setText(postFix);
      }
   }
   
   /**
    * Button listener inner class. This builds the string to be fed to the 
    * convert class that i prevoisly made.
    */
   class ButtonListener implements ActionListener {
      String str;
      boolean digit=true;
      @Override
      public void actionPerformed(ActionEvent e) {
         if (!postFix.equals(" "))actionClear();
         Character ch = e.getActionCommand().charAt(0); // Get text from button
         //System.out.println("characher is "+ch);
         
         //make decimil and keep track of if it has happened before.
         if ((Character.isDigit(ch) && digit)||(ch.equals('.') &&digit)){
            temp=temp+ch;
            digit=true;
            //System.out.println("digit "+temp);
         }else{ //make the string
            str= e.getActionCommand();
            str=temp.toString()+" "+str;
            digit=false;
            //System.out.println("not digit "+str);
            temp=""; //reset temp
         } 
         
         //push the string to the display
         if (!digit){
            inFix = inFix+" "+str+" ";
            in_displayField.setText(inFix);
            //System.out.println(str);
            //System.out.println("Infix is: "+inFix);
         }
      }
   }
}