/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 0.2
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
 */


public class List_1< T >{
   private ListNode< T > firstNode;
   private ListNode< T > lastNode;
   ListNode< T > currentNode;
   int currIndex;
   int num_lines;

   // constructor creates an empty List 
   public List_1(){
      firstNode = lastNode =currentNode = null;
      currIndex=0;
      num_lines=0;
   }
   
   public void insert(T insertItem){
      //System.out.println(currentNode.data);
      insertBef(currentNode.data,insertItem);
      //currentNode=currentNode.next();
      currIndex++;
   }
   
   public void setCurrent(int index){
      currentNode=firstNode;
      int num_test=0;
      for(int i=0;i==index;i++){
         currentNode=currentNode.next();
         num_test++;
      }
      if (num_test != index) System.out.println("there is a problem");
   }
   
      //a simple printing method. prints everything in the list.
   public void print(){
      if (isEmpty()){
         System.out.printf( "List is Empty \n" );
         return;
      } // end if

      System.out.printf( "The list contains: " );
      ListNode< T > current = firstNode;

      // while not at end of list, output current node's data
      while (current != null){
         System.out.printf( "%s ", current.data );
         current = current.nextNode;
      }
      //add some whitespace to make things look nice.
      System.out.println();
   }
      
   /*
    * @param index = the index to print everything after
    * Prints everything after the index specified.
    */
   public void pIndex(int index){
      currIndex=1;
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
         currIndex++;
      }
   }
   
   /*
    * @param index = the starting index to delete list contents after.
    * @param index2 = the ending index to delete list contents before.
    * Deletes everyhing between the 2 input indicies.
    */
   public void delete(int index, int index2){
      if (index > index2) {
         System.out.println("Cannot delete between these indicies! please check them.");
         return;
      }      
      if (index > num_lines){
         System.out.println("Cannot indicies that don't exist!");
         return;         
      }
      
      int tracker=0;
      ListNode temp=firstNode;
      //System.out.println("tracker: "+tracker);
      
      for(int i=0;i==index;i++) {
         tracker++;
         //System.out.println(tracker);
         temp=temp.next();
      }      
      
      Object first=temp.data;
      
      for(int i=tracker;i<index2-1;i++) {
         tracker++;
         //System.out.println("next loop: "+tracker);
         remove((T) temp.next().data);
      }
      
      Object second=temp.data;
      
      //System.out.println("first "+first);
      //System.out.println("second "+second);
      
   }
   
   /*
    * @param index = the starting index to print list contents after.
    * @param index2 = the ending index to print list contents before.
    * Prints the things between the 2 input indecies.
    */
   public void pIndex(int index, int index2){
      if (index > index2) {
         System.out.println("Cannot print between these indicies! please check them.");
         return;
      }
      currIndex=0;
      int end;
      int start;
      ListNode temp=firstNode;
      
      if (index >= 0) start=index;
      else start =0;
      
      if (index2 <=num_lines)end=index2;
      else end =num_lines;
      
      System.out.println("Line#   Line");
      
      
      for (int i=0;i<num_lines;i++){
         if(currIndex>=start && currIndex<=end){
            System.out.println("  "+currIndex+"     "+temp.data);
         }
         temp =temp.next();
         currIndex++;
      }
   }
   
   /*
    * @param searchPattern - a pattern to search for in the list.
    * Will search the list for the occurance of searchPattern.
    */
   public void search(String searchPattern){
      //currIndex=0; //does not change the current node.
      ListNode temp=firstNode;
      while( temp.next() != null ){
         String str=(String)temp.data;
         if( str.contains(searchPattern)){
            System.out.println("Found ");
            System.out.println(temp.data);
            return;

         }
         temp = temp.next();     
         //currIndex++;
      }
   }
      
      
   /*set the current node so we know where to insert.
    * @param setNode - the node to be set to the current.
    */
/*   public void setCurrent(T setNode){
      currentNode=(ListNode<T>) setNode;
      ListNode<T> cur  = firstNode;
      currIndex=0;
       
      while(cur != null ){
         cur = cur.next();
         currIndex++;
      } 
   }
Unnecessary and does not work the way I wanted.*/ 
   
   /*
    * @param ref - this is the reference in the string that the things will 
    * be inserted in fron of.
    * @param data - the data to be inserted into the list.
    * 
    * This will insert things the specified reference current node. It will link 
    * everything up and set the current to the inserted data point.
    */
   public void insertBef(T ref, T data){
      currIndex=0;
      //System.out.println("insertBef:");//debugging line
      if (firstNode.data.equals(ref)){
         insertAtFront(data);
         System.out.println("At front");
      } else {
         ListNode temp=firstNode;
         while( temp.next() != null ){
            if( temp.next().data == ref ){
               //System.out.println("inside");//debugging line
               ListNode n = new ListNode(data);
               n.nextNode = temp.nextNode;
               temp.nextNode = n;
               //System.out.println(temp.data);
               currentNode=temp.nextNode;
               return;
            }
            temp = temp.next();
            currIndex++;
         }
         num_lines++;
      }
   }
   
   /*
    * @param key - The link to be deleted.
    * 
    * Will remove the link that equals the key and set the current node to be 
    * the node following that.
    */
   public void remove(T key){
      if(firstNode == null) throw new RuntimeException("cannot delete");

      if(firstNode.data.equals(key)){
         removeFromFront();
         return;
      }

      ListNode<T> cur  = firstNode;
      ListNode<T> prev = null;
      currIndex=0;

      while(cur != null && !cur.data.equals(key) ){
         prev = cur;
         cur = cur.next();
         currIndex++;
      }

      if(cur == null) throw new RuntimeException("cannot delete");

      //delete curent node
      prev.nextNode = cur.nextNode;
      currentNode=cur.nextNode;
      num_lines--;
   }

   // insert item at end of List
   public void add( T insertItem ){
      if ( isEmpty() ){ // firstNode and lastNode refer to same object
         firstNode = lastNode = currentNode =new ListNode<  >( insertItem ); 
         currIndex=0;
         num_lines++;
      }
      else{ // lastNode's nextNode refers to new node
         lastNode =currentNode= lastNode.nextNode = new ListNode<  >( insertItem );
         num_lines++;
         
         //make sure to keep track of the correct index
         ListNode<T> cur  = firstNode;
         currIndex=0;

         while(cur != null ){
            cur = cur.next();
            currIndex++;
         }         
      }
   }
   
   //insert link at beginning of list
   public void insertAtFront( T insertItem ){
      if ( isEmpty() ){ // firstNode and lastNode refer to same object
         firstNode = lastNode = currentNode= new ListNode<  >( insertItem );
         currIndex=0;
         num_lines++;
      }
      else{ // firstNode refers to new node
         firstNode = currentNode=new ListNode<  >( insertItem, firstNode );
         currIndex=0;
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
         firstNode = lastNode = currentNode= null;
      else
         firstNode =currentNode= firstNode.nextNode;

      currIndex=0;
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
      currIndex=0;

      while(cur != null ){
         cur = cur.next();
         currIndex++;
      }       
      num_lines--;
      return removedItem;
   }

   // determine whether list is empty
   public boolean isEmpty(){ 
      return firstNode == null; // return true if list is empty
   } 
}




/*
 * The emply list exception I created.
 */
class EmptyListException extends RuntimeException{
   public EmptyListException(){
      this( "List" ); // call other EmptyListException constructor
   }

   public EmptyListException( String name ){
      super( name + " is empty" ); // call superclass constructor
   } 
} 




/*
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