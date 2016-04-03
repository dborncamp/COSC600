/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 1.0
 * @date 4/3/14, COSC600
 * @Assignment: Optional Assignment1
 *
 * Purpose of program:
 *    This is a queue implementation that is very similar to my linked list 
 * class of project 4 but much simpler. Also contains a node class and an 
 * empty queue exception.
 * 
 */

public class Queue {
    private Node first;
    private Node last;

    public Object peek() throws EmptyQueueException{
        if (isEmpty()) throw new EmptyQueueException();
        return first.data;
    }
  
    public void enq(Object item) {
        Node oldLast = last;
        last = new Node();
        last.data = item;
        last.nextNode = null;
        
        if (isEmpty()) first = last;
        else oldLast.nextNode = last;
    }
    
    
    public Object dnq() throws EmptyQueueException{
        if (isEmpty()) throw new EmptyQueueException();
        Object data = first.data;
        first = first.nextNode;
        if (isEmpty()) last = null;
        return data;      
    }
    
    
    public boolean isEmpty() {
        return first == null;
    }
}

class EmptyQueueException extends RuntimeException{
   public EmptyQueueException(){
      this( " Queue" ); // call other EmptyListException constructor
      System.out.print("** The list is empty. ");
   }

   public EmptyQueueException( String name ){
      super("** "+name + " is empty" ); // call superclass constructor
      System.out.print("** "+name+" is empty. ");
   } 
} 

class Node<T>{
   T data;
   Node<T> nextNode;
   
   Node(){
      this(null,null);
   }
   
   Node(T object){
      this(object,null);
   }
   
   Node(T object,Node<T> node){
      data=object;
      nextNode=node;
   }
}