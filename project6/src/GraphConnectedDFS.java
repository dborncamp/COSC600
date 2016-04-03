
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 0.0
 * @date , COSC600
 * @Assignment: 
 *
 * Purpose of program:
 *
 */

 
public class GraphConnectedDFS {
 
    public static void main(String[] args) throws Exception {
       try (Scanner iFile = new Scanner(new FileReader("connectedDFS.in"))) {
          Graph graph = new Graph();
          //graph.setUndirected();
          //graph.setSortedNeighbors(true);
   
          while(iFile.hasNext()) {
              GraphNode<String> a = new GraphNode<String>(iFile.next());
              GraphNode<String> b = new GraphNode<String>(iFile.next());
   
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
          }
   
          //graph.printNodes();
          //graph.printEdges();
   
          Vector<GraphNode> nodes = graph.getNodes();
   
          // Use all the different nodes as the root or source node.
          for(int i = 0; i < nodes.size(); i++) {
              // Do a Depth-First Search.
             if (!nodes.elementAt(i).isVisited()){
                GraphNode start = nodes.elementAt(i);
   
                System.out.println("Starting From: " + start + ", you can visit: ");
                System.out.print("\t");
   
                findAllConnected(graph, start);
   
                System.out.println(i);
   
              // Remove all the visited flags.
              //graph.unvisitAllNodes();
             }
          }
       }
    }
 
    public static void findAllConnected(Graph graph, GraphNode temp) {
        temp.visit();
 
        System.out.print(temp + ", ");
 
        Vector<GraphNode> neighbors = graph.getNeighbors(temp);
 
        for(int i = 0; i < neighbors.size(); i++)
            if(!neighbors.elementAt(i).isVisited())
                findAllConnected(graph, neighbors.elementAt(i));
    }
 
}
