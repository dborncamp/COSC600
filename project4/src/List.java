/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 3.0
 * @date 03/10/14, COSC600
 * @Assignment: Project3
 *
 * Purpose of program:
 *    This is my custom implementation of the linked list class. It needs to be
 * able to function close to the same way that java's linked list class 
 * functions but I have to write it. 
 * 
 *    The way I am handling things that require an index feels very inefficient
 * and cumbersome. I don't know what else to do, I didn't really design it to 
 * handle index referencing from the beginning and now it is cumbersome.
 * 
 *    Updated to work the way the remarks section of homework4 want. There are 
 * still some unused methods here, but they work and I don't need to delete 
 * them. It also throws exceptions to correctly handle error handling. I have 
 * created my own EmptyListException class to facilitate this.
 * 
 */


public class List< T >{
   private ListNode< T > firstNode;
   private ListNode< T > lastNode;
   ListNode< T > currentNode;
   int currIndex;
   int num_lines;

   // constructor creates an empty List 
   public List(){
      firstNode = lastNode =currentNode = null;
      currIndex=0;
      num_lines=0;
   }
   
   public void insert(T insertItem){
      
      //System.out.println(currentNode.data);
      if (currentNode != null){
         //System.out.println(" Not inserted at end.");
         insertBef(currentNode.data,insertItem);
      }else
         add(insertItem);
      //currentNode=currentNode.next();
      //currIndex++;
   }
   
   public void line(int index) throws EmptyListException{
      if ( isEmpty() ) throw new EmptyListException( "list" );
//      if (firstNode.getData() ==null){
//         System.out.println("exception");
//         throw new EmptyListException( "list" );
//         //return;
//      }
      //System.out.println(" line.Index is: "+index);
      if (index ==1) currentNode=firstNode;
      else{
         if (index>num_lines) {
            System.out.println(" Invalid line");
            return;
         }
         currentNode=firstNode;
         //System.out.println("\n In line");
         //int num_test=0;
         for(int i=1;i<index;i++){
            currentNode=currentNode.next();
            //num_test++;
            //System.out.println("  "+i);
         }
         //if (num_test != index) System.out.println("there is a problem");
         //System.out.println("num data: "+num_test);
         //System.out.println("ind data: "+index);
         currIndex=index;
      }
      //System.out.println(" Current node is: "+currentNode.data);
   }
   
      //a simple printing method. prints everything in the list.
   public void print(){
      if (isEmpty()){
         System.out.println( "List is Empty." );
         return;
      } // end if

      System.out.println( "The list contains: " );
      ListNode< T > current = firstNode;

      // while not at end of list, output current node's data
      while (current != null){
         System.out.printf( "%s\n ", current.data );
         current = current.nextNode;
      }
      //add some whitespace to make things look nice.
      System.out.println();
   }
      
   /**
    * @param index = the index to print everything after
    * Prints everything after the index specified.
    */
   public void pIndex(int index){
      //currIndex=1;
      int end;
      int start;
      ListNode temp=firstNode;
      
      if (index-1 >= 0) start=index-1;
      else start =0;
      
      if (index+1 <=num_lines)end=index+1;
      else end =num_lines;
      
      System.out.println("Line#   Line");
      
      for (int i=0;i<num_lines;i++){
         if(currIndex>=start && currIndex<=end){
            System.out.println("  "+currIndex+"     "+temp.data);
            if (currIndex==start)currentNode=currentNode.next();
         }
         temp=temp.next();
         //currIndex++;
      }
   }
   
   /**
    * @param index = the starting index to delete list contents after.
    * @param index2 = the ending index to delete list contents before.
    * Deletes everyhing between the 2 input indicies.
    */
   public void delete(int index, int index2){
      if ( isEmpty() ) throw new EmptyListException( "list" );
      
      if (index > index2) {
         System.out.println("Cannot delete between these indicies! please check them.");
         return;
      }      
      if (index2 > num_lines){
         System.out.println("Cannot delete these indicies that don't exist!");
         return;         
      }
      if (index < 1 || index2 < 1){
         System.out.println("Index cannot be less than 1");
      }
      
      int tracker=0;
      ListNode temp=firstNode;
      //System.out.println("tracker: "+tracker);
      
      for(int i=0;i==index;i++) {
         tracker++;
         //System.out.println(tracker);
         temp=temp.next();
      }      
      
      for(int i=tracker;i<index2-1;i++) {
         tracker++;
         //System.out.println("next loop: "+tracker);
         remove((T) temp.next().data);
      }
      
   }
   
   /**
    * @param index = the starting index to print list contents after.
    * @param index2 = the ending index to print list contents before.
    * Prints the things between the 2 input indecies.
    */
   public void print(int index, int index2){
      if ( isEmpty() ) throw new EmptyListException( "list" );
      if (index > index2) {
         System.out.println("Cannot print between these indicies! please check them.");
         return;
      }
      if (index2 > num_lines){
         System.out.println("Index2 is too high. Please check it.");
         return;
      }
      
      //System.out.println("print indicies: "+index+" "+index2);
      
      int curr=1;
      int end;
      int start;
      ListNode temp=firstNode;
      
      if (index > 0) start=index;
      else start =0;
      
      if (index2 <num_lines)end=index2;
      else end =num_lines;
      
      //System.out.println(" Start and end "+start+" "+end);
      System.out.println("Line#   Line");
      //System.out.println("num_lines "+num_lines);
      
      for (int i=1;i<num_lines+1;i++){
         //System.out.println("curr and i  "+curr+" "+i);
         if(curr>start-1 && curr<end+1){
            //System.out.println(" i "+i);
            System.out.println("  "+curr+"     "+temp.data);
         }
         //if (temp.next().data !=null) temp =temp.next();
         temp=temp.next();
         curr++;
      }
   }
   
   /**
    * @param searchPattern - a pattern to search for in the list.
    * Will search the list for the occurance of searchPattern.
    */
   public void search(String searchPattern){
      if ( isEmpty() ) throw new EmptyListException( "list" );
      ListNode temp=firstNode;
      //System.out.println(searchPattern);
      while( temp.next() != null ){
         String str=(String)temp.data;
         //System.out.println("The search string is: "+str);
         if( str.contains(searchPattern)){
            System.out.println("Search found: ");
            System.out.println(" "+temp.data);
            return;
         }
         temp = temp.next();     
      }
      System.out.println("Pattern not found, try again.");
   }
      
   /**
    * @param ref - this is the reference in the string that the things will 
    * be inserted in fron of.
    * @param data - the data to be inserted into the list.
    * 
    * This will insert things the specified reference current node. It will link 
    * everything up and set the current to the inserted data point.
    */
   public void insertBef(T ref, T data){
      //System.out.println("insertBef:");//debugging line
      if ( isEmpty() ) throw new EmptyListException( "list" );
      if (firstNode.data.equals(ref)){
         insertAtFront(data);
         //System.out.println("Inserted At front");
      } else {
         ListNode temp=firstNode;
         while( temp.next() != null ){
            if( temp.next().data == ref ){
               //System.out.println("inside");//debugging line
               ListNode n = new ListNode(data);
               n.nextNode = temp.nextNode;
               temp.nextNode = n;
               //System.out.println(temp.data);
               //currentNode=temp.nextNode;
               return;
            }
            temp = temp.next();
            //currIndex++;
         }
         num_lines++;
      }
   }
   
   /**
    * @param key - The link to be deleted.
    * 
    * Will remove the link that equals the key and set the current node to be 
    * the node following that.
    */
   public void remove(T key){
      if ( isEmpty() ) throw new EmptyListException( "list" );

      if(firstNode.data.equals(key)){
         removeFromFront();
         return;
      }

      ListNode<T> cur  = firstNode;
      ListNode<T> prev = null;
      //currIndex=0;

      while(cur != null && !cur.data.equals(key) ){
         prev = cur;
         cur = cur.next();
         //currIndex++;
      }

      if(cur == null) throw new RuntimeException("cannot delete");

      //delete curent node
      prev.nextNode = cur.nextNode;
      //currentNode=cur.nextNode;
      num_lines--;
   }

   // insert item at end of List
   public void add( T insertItem ){
      if ( isEmpty() ){ // firstNode and lastNode refer to same object
         firstNode = lastNode = new ListNode<  >( insertItem ); 
         //currIndex=0;
         num_lines++;
      }
      else{ // lastNode's nextNode refers to new node
         lastNode = lastNode.nextNode = new ListNode<  >( insertItem );
         num_lines++;
         
         //make sure to keep track of the correct index
         ListNode<T> cur  = firstNode;
         //currIndex=0;

         while(cur != null ){
            cur = cur.next();
            //currIndex++;
         }         
      }
   }
   
   //insert link at beginning of list
   public void insertAtFront( T insertItem ){
      if ( isEmpty() ){ // firstNode and lastNode refer to same object
         firstNode = lastNode = new ListNode<  >( insertItem );
         //currIndex=0;
         num_lines++;
      }
      else{ // firstNode refers to new node
         firstNode = new ListNode<  >( insertItem, firstNode );
         //currIndex=0;
         num_lines++;
      }
   }
   
   // remove first node from List
   public T removeFromFront() throws EmptyListException{
      if ( isEmpty() ) // throw exception if List is empty
         throw new EmptyListException( "list" );

      T removedItem = firstNode.data; // retrieve data being removed

      // update references firstNode and lastNode 
      if ( firstNode == lastNode )
         firstNode = lastNode = null;
      else
         firstNode = firstNode.nextNode;

      //currIndex=0;
      num_lines--;
      return removedItem;
   }

   // remove last node from List, may reset the current - new version does not
   public T removeFromBack() throws EmptyListException{
      if ( isEmpty() ) // throw exception if List is empty
         throw new EmptyListException( "list" );

      T removedItem = lastNode.data; // retrieve data being removed

      // update references firstNode and lastNode
      if ( firstNode == lastNode )
         firstNode = lastNode = null;
      else {
         ListNode< T > tempNode = firstNode;

         // loop while currentNode node does not refer to lastNode
         while ( tempNode.nextNode != lastNode )
            tempNode = tempNode.nextNode;
   
         lastNode = tempNode; // currentNode is new lastNode
         tempNode.nextNode = null;
      }
      
      //keep track of the current index
      ListNode<T> cur  = firstNode;
      //currIndex=0;

      while(cur != null ){
         cur = cur.next();
         //currIndex++;
      }       
      num_lines--;
      return removedItem;
   }

   // determine whether list is empty
   public boolean isEmpty(){ 
      return firstNode == null; // return true if list is empty
   } 
}



/**
 * The emply list exception I created.
 */
class EmptyListException extends RuntimeException{
   public EmptyListException(){
      this( "** List is empty" ); // call other EmptyListException constructor
      System.out.print("** The list is empty. ");
   }

   public EmptyListException( String name ){
      super("** "+name + " is empty" ); // call superclass constructor
      System.out.print("** "+name+" is empty. ");
   } 
} 




/**
 * The node class I created
 */
class ListNode<T> {
   protected T data;
   protected ListNode<T> nextNode;
   
   //will pass to 2 argument constructor
   ListNode(T object){
      this(object,null);
   }
   
   ListNode(T object, ListNode<T> node){
      data=object;
      nextNode=node;
   }
   
   //reurns the node data.
   T getData(){
      return data;
   }
   
   //looks at the next node in the link.
   ListNode<T> next(){
      return nextNode;
   }
}