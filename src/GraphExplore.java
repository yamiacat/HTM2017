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

            
            //DEFINE NODES
            Node city = graph.getNode("City");
            Node aston = graph.getNode("Aston");           
            Node fiveways = graph.getNode("Fiveways");            
            Node smallheath = graph.getNode("Smallheath");           
            Node acocksgreen = graph.getNode("Acocksgreen");           
            Node solihull = graph.getNode("Solihull");
            Node sellyoak = graph.getNode("Sellyoak");
            Node redditch = graph.getNode("Redditch");
            Node erdington = graph.getNode("Erdington");
            Node lichfield = graph.getNode("Lichfield");
            Node perrybarr = graph.getNode("Perrybarr");
            Node walsall = graph.getNode("Walsall");
            
            
            //DEFINE LOCATIONS
            city.setAttribute("xy", 0,0);
            aston.setAttribute("xy", 1, 1);
            fiveways.setAttribute("xy", -1, -1);
            smallheath.setAttribute("xy", 1, -1);
            acocksgreen.setAttribute("xy", 2, -2);
            solihull.setAttribute("xy", 3, -3);
            sellyoak.setAttribute("xy", -2, -2);
            redditch.setAttribute("xy", -3, -3);
            erdington.setAttribute("xy", 2, 2);
            lichfield.setAttribute("xy", 3, 3);           
            perrybarr.setAttribute("xy", -2, 2);               
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
