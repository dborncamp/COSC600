
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 1
 * @date , COSC600
 * @Assignment: Project6
 *
 * Purpose of program:
 *    Custom graph class for project 6. I assume we have to build our own for 
 * this assignment even though the instructions do not explicitly say so. This
 * graph can accept nodes of any type of object. It uses the vertex and edge 
 * method for graphs.
 */


public class Graph {
   //vectors that hold the nodes and edges of the graph.
    protected Vector<GraphNode> nodes = new Vector<>();
    protected Vector<GraphEdge> edges = new Vector<>();
    private Queue<GraphNode> q=new LinkedList<GraphNode>();
    
    
    /**
     * 
     * @return - the adjacency matrix of the graph.
     * 
     * create an adjacency matrix of the graph.
     */
    public int[][] getAdjacencyMatrix() {
        int[][] adjMatrix = new int[nodes.size()][nodes.size()];
         
        for(int i = 0; i < nodes.size(); i++)
            for(int j = 0; j < nodes.size(); j++)
               adjMatrix[i][j] = 0;
//                if(i == j)
//                    adjMatrix[i][j] = 0;
//                else
//                    adjMatrix[i][j] = Double.POSITIVE_INFINITY;
                 
        for(int i = 0; i < nodes.size(); i++) {
            GraphNode node = nodes.elementAt(i);
            //System.out.println("Current node: " + node);
             
            for(int j = 0; j < edges.size(); j++) {
                GraphEdge edge = edges.elementAt(j);
                 
                if(edge.a == node) {
                    int indexOfNeighbor = nodes.indexOf(edge.b);
                    int weight=edge.getWeight();
                    //System.out.println(weight);
                    adjMatrix[i][indexOfNeighbor] = weight;
                }
            }
        }
         
        return adjMatrix;
    }
    
    /**
     * Prints the adjacency matrix.
     */
    public void printAdjMatrix(){
       int[][] matrix = getAdjacencyMatrix();
       for (int i=0;i<matrix.length;i++){
          for (int j=0;j<matrix.length;j++)
          System.out.print(matrix[i][j]+"");
          System.out.println();
       }
    }
     

    /**
     * 
     * @param a - node to get index of
     * @return - index of node of interest.
     * 
     * returns the index of a given node.
     */
    public int indexOf(GraphNode a) {
        if (nodes.size() > 0){
        for(int i = 0; i < nodes.size(); i++)
            if(nodes.elementAt(i).getData().equals(a.getData()))
                return i;
        }
        return -1;
        //}
    }
     
    /**
     * 
     * @return - vector containing all of the nodes
     * 
     * return all of the nodes in the graph.
     */
    public Vector<GraphNode> getNodes() {
        return nodes;
    }
     
    /**
     * 
     * @return - vector containing all of the edges.
     * 
     * get all of the edges of the graph.
     */
    public Vector<GraphEdge> getEdges() {
        return edges;
    }
     
    /**
     * 
     * @param i - index of node of interest
     * @return - node to return
     * 
     * return the node at a certain index
     */
    public GraphNode getNodeAt(int i) {
        return nodes.elementAt(i);
    }
     
    /**
     * Make all nodes un-visited.
     */
    public void unvisitAllNodes() {
        for(int i = 0; i < nodes.size(); i++)
            nodes.elementAt(i).unvisit();
    }
     
    /**
     * 
     * @param a - node to get notVisited of
     * @return - notVisited in a vector.
     * 
     * find all of the notVisited of the specified node.
     */
    public Vector<GraphNode> getNeighbors(GraphNode a) {
       Vector<GraphNode> neighbors = new Vector<GraphNode>();
         
       for(int i = 0; i < edges.size(); i++) {
          GraphEdge edge = edges.elementAt(i);
             
          if(edge.a == a)
             neighbors.add(edge.b);
       }
         
      return neighbors;
    }
    
    public int getNumNeighbors(GraphNode a){
       int num=0;
         
       for(int i = 0; i < edges.size(); i++) {
          GraphEdge edge = edges.elementAt(i);
             
          if(edge.a == a)
             num++;
       }
       return num;
    }
    
    public int getNumVisited(){
       int num=0;
       
       for(int i = 0; i < nodes.size(); i++) {
          GraphNode node=nodes.elementAt(i);
          
          if (node.isVisited())
             num++;
       }
       return num;
       
    }
    
    public int getNumNeighborsNotVisited(GraphNode a){
       int num=0;
       Vector<GraphNode> neighbors = getNeighbors(a);
       
        for(int i = 0; i < neighbors.size(); i++) {
           if(!neighbors.elementAt(i).isVisited())
              num++;
        }
       
       return num;
    }
    
    public Vector<GraphNode> getNotVisitedNodes() {
       Vector<GraphNode> notVisited = new Vector<GraphNode>();
         
       for(int i = 0; i < nodes.size(); i++) {
          GraphNode node = nodes.elementAt(i);
             
          if(!node.isVisited())
             notVisited.add(node);
       }
         
      return notVisited;
    }
    
    public void color(){
       boolean reloop=false;
       //System.out.println();
       unvisitAllNodes();
       colorRecurse();
       GraphNode tempNode;
       Color tempColor;
       //System.out.println(q.peek())
       while(!q.isEmpty()){
       //for(int i=0;i<q.size()-1;i++){
          //System.out.println(i);
          tempNode = q.remove();
          //System.out.println(tempNode.getData());
          tempColor = Color.RED;
          Vector<GraphNode> notNeigh = getNeighbors(tempNode);
          for(int j=0;j<notNeigh.size();j++){
             //System.out.print(j);
             if (tempColor==notNeigh.elementAt(j).getColor()){ 
                reloop=true;
                //System.out.print("same color ==>"+tempColor);
                tempColor=tempColor.next();
             }
             //System.out.print('\n');
          }
          while (reloop){
             //System.out.println("reloop");
             reloop=false;
             for(int j=0;j<notNeigh.size();j++){
                //System.out.print(j);
                if (tempColor==notNeigh.elementAt(j).getColor()){ 
                   reloop=true;
                   //System.out.print("same color ==>"+tempColor);
                   try{
                      tempColor=tempColor.next();
                   } catch (NoMoreEnumException ex){
                      //System.out.print("** No more enums! ");
                      tempColor=tempColor.NONE;
                      Vector<GraphNode>neigh = getNeighbors(tempNode);
                      for (int i=0;i<neigh.size();i++){
                         GraphNode temp1;
                         temp1=neigh.elementAt(i);
                         //System.out.println("adding "+temp1.getData());
                         temp1.setColor(tempColor.NONE);
                         q.add(temp1);
                      }
                   }
                }
                //System.out.print('\n');
             }
          }
          
          tempNode.setColor(tempColor);
          //System.out.println();
       }
       unvisitAllNodes();
    }
    

    private void colorRecurse(){
       if(getNumVisited()!=nodes.size()){
          Vector<GraphNode> nodes = getNotVisitedNodes();
          
          for(int i = 0; i < nodes.size(); i++) {
             if(getNumNeighborsNotVisited(nodes.elementAt(i))<4){
                nodes.elementAt(i).visit();
                q.add(nodes.elementAt(i));
                //System.out.println(nodes.elementAt(i));
                
             }
          }
          colorRecurse();
       } else return;
    }
    
     
    /**
     * 
     * @param a the node to add
     * @return size of node. determines if added successfully.
     * 
     * add a node.
     */
    public int addNode(GraphNode a) {
        nodes.add(a);
         
        return nodes.size() - 1;
    }
     
    /**
     * 
     * @param a an edge to add
     * 
     * Add an edge
     */
    public void addEdge(GraphEdge a) {
        edges.add(a);
    }
     
    /**
     * print the nodes
     */
    public void printNodes() {
        System.out.println(nodes);
    }
     
    /**
     * print the edges
     */
    public void printEdges() {
       System.out.println("Number of edges: "+edges.size());
       for(int i=0;edges.size()>i;i++){
          System.out.println(edges.elementAt(i));
       }
        //System.out.println(edges);
    }
    
//    public void DFS (GraphNode p) {
//	//System.out.println(p.data+" -> data");
//	for (p.all vertices, q, adjacent to p){
//	    if q is an unvisited vertex
//              p.toString();
//              p.visit();
//		  DFS (q);
//      }
//    }

}



/**
 * @author dave
 * @param <T>  The node to "link"
 * 
 * This is a graph node class.
 */
class GraphNode<T> implements Comparable<GraphNode<T>> {
     
    private T data;
    private boolean visited;
    private Color color;//=new Color();
    public Integer index = null;
    public Integer lowlink = null;
    public double distance = Double.POSITIVE_INFINITY;
    public GraphNode<T> predecessor = null;
     
    public GraphNode(T data) {
        this.data = data;
    }
     
    public GraphNode() {
         
    }
     
    /**
     * 
     * @return - if the node has been visited or not.
     */
    public boolean isVisited() {
        return visited;
    }
    
    public T getData(){
       return data;
    }
     
    /**
     * visit the node.
     */
    public void visit() {
        visited = true;
    }
     
    /**
     * un-visit the node.
     */
    public void unvisit() {
        visited = false;
    }
     
    /**
     * 
     * @param ob node to compare current node to
     * @return True if same node
     * 
     * compares the nodes.
     */
    public int compareTo(GraphNode<T> ob) {
        String tempA = this.toString();
        String tempB = ob.toString();
         
        return tempA.compareTo(tempB);
    }
    
    public boolean compareColor(GraphNode<T> node){
       return this.color==node.color;
    }
    
    public Color getColor(){
       return this.color;
    }

    /**
     * 
     * @return data in the node as a string.
     */
    @Override
    public String toString() {
        return data.toString();
    }
     
    public void setColor(Color col){
       //col.toString();
       color=col;
    }
}



/**
 * @author dave
 * 
 * Create an edge for the graphs
 */
 class GraphEdge {
    protected GraphNode a, b;
    protected int weight;
     
    public GraphEdge(GraphNode a, GraphNode b) {
        this(a, b, 1);
    }
     
    /**
     * 
     * @param a - node to link from.
     * @param b - node to link to.
     * @param weight - weight to assign link.
     * 
     * create an edge between nodes in a graph.
     */
    public GraphEdge(GraphNode a, GraphNode b, int weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }
     
    /**
     * 
     * @return - weight of current edge
     * 
     * gets the weight of the edge
     */
    public int getWeight() {
        return weight;
    }
     
    /**
     * 
     * @return - the link between the nodes as a string.
     */
    @Override
    public String toString() {
        return a + " ==> " + b;
    }
 
}
