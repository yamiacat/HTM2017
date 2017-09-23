/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yamiacat
 */
    import java.util.Iterator;
    import org.graphstream.graph.*;
    import org.graphstream.graph.implementations.*;

    public class GraphExplore {
        public static void main(String args[]) {
            System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
            new GraphExplore();
        }

        public GraphExplore() {
            Graph graph = new SingleGraph("tfwm");

            graph.addAttribute("ui.stylesheet", styleSheet);
            graph.setAutoCreate(true);
            graph.setStrict(false);
            graph.display(false);
            
            //DEFINE RELATIONSHIPS
            graph.addEdge("CityAston", "City", "Aston");
            graph.addEdge("AstonSmallheath", "Aston", "Smallheath");
            graph.addEdge("SmallheathCity", "Smallheath", "City");
            graph.addEdge("CityFiveways", "City", "Fiveways");
            graph.addEdge("FivewaysSmallheath", "Fiveways", "Smallheath");
            graph.addEdge("AstonPerrybarr", "Aston", "Perrybarr");
            graph.addEdge("PerrybarrCity", "Perrybarr", "City");
            graph.addEdge("PerrybarrWalsall", "Perrybarr", "Walsall");
            graph.addEdge("PerrybarrErdington", "Perrybarr", "Erdington");
            graph.addEdge("ErdingtonAston", "Erdington", "Aston");                        
            graph.addEdge("ErdingtonLichfield", "Erdington", "Lichfield");                                  
            graph.addEdge("ErdingtonAcocksgreen", "Erdington", "Acocksgreen");    
            graph.addEdge("SmallheathAcocksgreen", "Smallheath", "Acocksgreen");    
            graph.addEdge("AcocksgreenSolihull", "Acocksgreen", "Solihull");    
            graph.addEdge("FivewaysSelloak", "Fiveways", "Sellyoak");
            graph.addEdge("AcocksgreenSelloak", "Acocksgreen", "Sellyoak");
            graph.addEdge("RedditchSelloak", "Redditch", "Sellyoak");

            
            //DEFINE POSITIONS
            
            Node city = graph.getNode("City");
            city.setAttribute("xy", 0,0);
               
            Node aston = graph.getNode("Aston");
            aston.setAttribute("xy", 1, 1);
            
            Node fiveways = graph.getNode("Fiveways");
            fiveways.setAttribute("xy", -1, -1);
            
            Node smallheath = graph.getNode("Smallheath");
            smallheath.setAttribute("xy", 1, -1);
            
            Node acocksgreen = graph.getNode("Acocksgreen");
            acocksgreen.setAttribute("xy", 2, -2);
            
            Node solihull = graph.getNode("Solihull");
            solihull.setAttribute("xy", 3, -3);

            Node sellyoak = graph.getNode("Sellyoak");
            sellyoak.setAttribute("xy", -2, -2);

            Node redditch = graph.getNode("Redditch");
            redditch.setAttribute("xy", -3, -3);

            Node erdington = graph.getNode("Erdington");
            erdington.setAttribute("xy", 2, 2);
            
            Node lichfield = graph.getNode("Lichfield");
            lichfield.setAttribute("xy", 3, 3);           
  
            Node perrybarr = graph.getNode("Perrybarr");
            perrybarr.setAttribute("xy", -2, 2);               
            
            Node walsall = graph.getNode("Walsall");
            walsall.setAttribute("xy", -3, 3);               
            
            
            for (Node node : graph) {
                node.addAttribute("ui.label", node.getId());
            }

            explore(graph.getNode("City"));
        }

        public void explore(Node source) {
            Iterator<? extends Node> k = source.getBreadthFirstIterator();

            while (k.hasNext()) {
                Node next = k.next();
                next.setAttribute("ui.class", "marked");
                sleep();
            }
        }

        protected void sleep() {
            try { Thread.sleep(1000); } catch (Exception e) {}
        }

        protected String styleSheet =
            "node {" +
            "	fill-color: black;" +
            "   text-alignment: at-right;" +
            "}" +
            "node.marked {" +
            "	fill-color: blue;" +
            "}" 
    
                ;
    }
