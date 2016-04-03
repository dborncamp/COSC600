
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 3.0
 * @date , COSC600
 * @Assignment: Project6
 *
 * Purpose of program:
 *    Show off the graph class I wrote. This will build and print the traversals 
 * of the graph.
 */


public class Driver {
   Graph graph = new Graph();
   public String[] state = new String[49];//1 indexed
   StringTokenizer tempToken;
   private int level=0;
   ArrayList<ArrayList<GraphNode>> neigh=new ArrayList<ArrayList<GraphNode>>();

   /**
    * 
    * @param borderFileName - text file containing the linkages between states.
    * must be in the format that project6.doc specifies
    * @param stateFileName - a text file containing the names of the states in 
    * the order that they are in the borderfile.
    * 
    * Will read in the borderfile and statefile to create a graph of the 
    * continental united states.
    */
   public void getMap(String borderFileName,String stateFileName){
      int i=1; //1 indexed
      int j=1;
      int num_elements=0; //debugging
      
      try{
         Scanner borderFile = new Scanner(new FileReader(borderFileName));
         Scanner stateFile = new Scanner(new FileReader(stateFileName));
         //populate the state array
         //System.out.println(stateFile.next());
         while(stateFile.hasNext()) {
            String temp=stateFile.next();
            state[i]=temp;
            //System.out.println(i+" "+state[i]);
            i++;
         }
         //populate the graph
         while(borderFile.hasNextLine()) {
             //GraphNode<Integer> a = new GraphNode<Integer>(j);
            GraphNode<String> a = new GraphNode<String>(state[j]);
             tempToken = new StringTokenizer(borderFile.nextLine()," ");
             //System.out.println("State is: "+state[j]+ " a is: "+a.data);
             
             //GraphNode<Integer> b = new GraphNode<Integer>(borderFile.nextInt());
             
             while(tempToken.hasMoreTokens()){
//                if(tempToken.nextToken()==null) {
//                   System.out.println("found a null at: "+j+" "+state[j]);
//                   continue;
//                }
                //GraphNode<Integer> b = new GraphNode<Integer>(Integer.parseInt(tempToken.nextToken()));
                int tempInd=Integer.parseInt(tempToken.nextToken());
                GraphNode<String> b = new GraphNode<String>(state[tempInd]);
                //System.out.println(b.toString());
                int aPos = graph.indexOf(a);
                int bPos = graph.indexOf(b);

                // If a does not exist in the graph yet.
                if(aPos == -1)
                   aPos = graph.addNode(a);

                // If b does not exist in the graph yet.
                if(bPos == -1)
                   bPos = graph.addNode(b);
                GraphEdge edge = new GraphEdge(graph.getNodeAt(aPos), graph.getNodeAt(bPos));

                graph.addEdge(edge);
                num_elements++;
                //j++;
                //System.out.println("tempInd "+tempInd+" j: "+j+" state is: "+state[tempInd]+" "+num_elements);
             }
             
             j++;
             //System.out.println("\n Next state "+j+" "+state[j]);
          }
       }catch (Exception e){
          System.out.println(e);
          e.printStackTrace();
          System.out.println("there is a problem");
       }
   }
   
   /**
    * Traverse the graph using a depth first traversal. starting at the first 
    * element in the graph.
    */
   public void dfs(){
      dfs(0);
   }
   
   /**
    * 
    * @param index - index to start traversal from.
    * 
    * Will print depth first traversal from any index in the graph.
    */
   public void dfs(int index){
      level=0;
      System.out.println("Printing depth first: ");
      Vector<GraphNode> nodes = graph.getNodes();
      System.out.println("Starting at: "+nodes.elementAt(index));
      GraphNode start = nodes.elementAt(index);
      //start.visit();
      findAllConnected(this.graph,start);
      System.out.print(" Level: "+level);
      System.out.println();
      graph.unvisitAllNodes();
   }
      
   /**
    * 
    * @param graph - the graph to compare node to
    * @param temp "root node" to find all nodes connected to it within the graph.
    * 
    * Will find the connected nodes for a graph. It will call recursively to 
    * find all nodes in the graph relative to the "root node"
    */
   private void findAllConnected(Graph graph, GraphNode temp) {
      if (temp.getData()==null){
         System.out.println("Found a null "+temp.getData());
      } else{
         //if (!temp.isVisited()) level++;
         temp.visit();
         System.out.print(" "+temp.getData() + " color: "+temp.getColor()+", ");
         //System.out.println("level: "+level);
         
         Vector<GraphNode> neighbors = graph.getNeighbors(temp);
 
         for(int i = 0; i < neighbors.size(); i++)//only on one line
             if(!neighbors.elementAt(i).isVisited()){
                //System.out.println("number of neighbors: "+neighbors.size());
                
                findAllConnected(graph, neighbors.elementAt(i));
                for(int j = 0; j < neighbors.size(); j++){
                   if (!neighbors.elementAt(j).isVisited()){
                      System.out.println(" Level: "+level+"\n");
                      
                      level++;
                      break;
                   }
                }
             }
         //System.out.print(temp.getData() + ", ");
      }//end else
   }
   
      
   /**
    * Traverse graph using breadth first traversal. 
    * Assumes that the starting node is the first node in the graph.
    */
   public void bfs(){
	//BFS uses Queue data structure, linked list is type of queue.
      bfs(0);
      }
   
   /**
    * 
    * @param index - index to start traversal from.
    * 
    * Will print breadth first traversal from any index in the graph.
    */
   public void bfs(int index){
	//BFS uses Queue data structure, linked list is type of queue.
      ArrayList temparr=new ArrayList();

      System.out.println("\nPrinting breadth first: ");
      System.out.println("Starting at: "+graph.nodes.elementAt(index));
      
      level=0;
      int counter=0;
      System.out.println(" Level: "+level);
      System.out.println("  "+graph.nodes.elementAt(index)+" Color: "+graph.nodes.elementAt(index).getColor());
      
      graph.unvisitAllNodes();
      graph.getNodeAt(index).visit();
      //q.add(graph.getNodeAt(0));
      
      temparr.add(graph.getNodeAt(index));
      neigh.add(temparr);
      neighborFind(temparr,counter);
      
      }
   
   /**
    * 
    * @param arr - ArrayList containing the nodes on the level to print and find
    * next level of
    * @param counter - keeps track of the how many nodes have been visited. Makes
    * sure the program will stop.
    * 
    * Recursive call for the breadth first traversal. Will keep track of levels 
    * and can start at any node in the graph and correctly find the levels.
    */
   private void neighborFind(ArrayList<GraphNode> arr,int counter){
      //use counter to make sure I find all of the elements.
      //System.out.println(arr.get(0).toString()+arr.get(0).isVisited());
      GraphNode tempNode=new GraphNode();
      ArrayList<GraphNode> newList=new ArrayList();
      //get neighbors of each element in the list and stich into next index of neigh
      for(int i=0;i<arr.size();i++){
         
         tempNode=arr.get(i);
         //System.out.println("* tempnode: "+tempNode.getData()+" "+tempNode.isVisited());
         //System.out.println(i+" "+tempNode.toString());
         for(int j=0;j<graph.getNeighbors(tempNode).size();j++){
            if(!graph.getNeighbors(tempNode).elementAt(j).isVisited()){
               //if(counter==0) graph.getNodeAt(0).visit();
               newList.add(graph.getNeighbors(tempNode).elementAt(j));
               //System.out.println("  "+j+" "+graph.getNeighbors(tempNode).elementAt(j).toString());
               graph.getNeighbors(tempNode).elementAt(j).visit();
               counter++;
            }
         }
      }
      level++;
      neigh.add(newList);
      //System.out.println(" last element of neigh: "+neigh.get(neigh.size()-1).toString()+" Counter: "+counter+" Level: "+level);
      //print the levels
      System.out.println(" Level: "+level);
      for (int k =0;k<neigh.get(neigh.size()-1).size();k++ )
         System.out.print("  "+neigh.get(neigh.size()-1).get(k)+": "+neigh.get(neigh.size()-1).get(k).getColor());
      System.out.println("");
      if (counter != graph.getNodes().size()-1)neighborFind(neigh.get(neigh.size()-1),counter);
   }
   
//   private Color tempColor=Color.NONE;//= Color.NONE;//=node.getColor();
//   private void color(){
//      
//      tempColor=tempColor.next();
//      node.setColor(tempColor);
//      Vector<GraphNode> neighbors = graph.getNeighbors(node);
//      
//      for(int i = 0; i < neighbors.size(); i++){
//         if (tempColor==Color.NONE)return false;
//         if (neighbors.elementAt(i).getColor() == node.getColor()){
//            color(node);
//         }
//      }
//      return true;
//   }
   
   /**
    * obsolete levelFind method.
    */
//   private int levelFind(GraphNode temp){
//      int num=0;
//      
//      System.out.println("**  looking for second neighbors of:"+temp.getData());
//      
//      Vector<GraphNode> neighbors = graph.getNeighbors(temp);
//      for(int j = 0; j < neighbors.size(); j++){
//         Vector<GraphNode> secondNeighbors = graph.getNeighbors(neighbors.elementAt(j));
//         for(int i=0;i < secondNeighbors.size(); i++){
//            if(!secondNeighbors.elementAt(i).isVisited()){
//               System.out.println("**  found:"+secondNeighbors.elementAt(i).getData());
//               num++;
//            }
//         }
//      }
//      
//      return num;
//   }
   
}

