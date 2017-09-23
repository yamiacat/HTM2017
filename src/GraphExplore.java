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
            new GraphExplore();
        }

        public GraphExplore() {
            Graph graph = new SingleGraph("tfwm");

            graph.addAttribute("ui.stylesheet", styleSheet);
            graph.setAutoCreate(true);
            graph.setStrict(false);
            graph.display();

            graph.addEdge("CityAston", "City", "Aston");
            
            Node city = graph.getNode("City");
            city.setAttribute("name", "City");
               
            
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
            "}" +
            "node.marked {" +
            "	fill-color: red;" +
            "}";
    }
