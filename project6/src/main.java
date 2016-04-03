
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author:  Dave Borncamp 0577058
 * @Version: 1.0
 * @date , COSC600
 * @Assignment: Project6
 *
 * Purpose of program:
 *    Main program for populating and traversing the graph.
 *
 */


public class main {
   String borderFile="stateborders.txt";
   String stateFile="C:\\Users\\dave\\Documents\\classes\\COSC600\\project6\\states.txt";
   
   
   public static void main(String[] args){
      Driver driver=new Driver();
      driver.getMap("stateborders.txt","states.txt");
      //driver.graph.printNodes();
      //driver.graph.printEdges();
      driver.graph.color();
      
      driver.dfs(0);
      //System.out.println();
      driver.bfs(0);
      
      
      
      //System.out.println(driver.graph.nodes.elementAt(1).getColor());
      
      //System.out.println(driver.graph.getNumNeighbors(driver.graph.getNodeAt(0)));


//      driver.graph.printAdjMatrix();
//      Color tempColor = Color.YELLOW;//=node.getColor();
//      try {
//         tempColor.next();
//         System.out.println(tempColor.next().next().next().next().next());
//      } catch (NoMoreEnumException ex) {
//         Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//      }
//      System.out.println(tempColor);
   }
   
}
