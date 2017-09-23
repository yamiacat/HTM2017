/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yamiacat
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.graphicGraph.GraphicGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;
import org.graphstream.ui.view.util.DefaultMouseManager;
import org.graphstream.ui.view.util.MouseManager;

public class GraphExplore implements ViewerListener{
	protected boolean loop = true;
	protected Graph graph;
	
    public static void main(String args[]) {
        new GraphExplore();
    }

    public GraphExplore() {
        Graph graph = new SingleGraph("tfwm");

	    graph.addAttribute("ui.stylesheet", styleSheet);
	    graph.setAutoCreate(true);
	    graph.setStrict(false);
	    
	    Viewer viewer = graph.display();
	    viewer.getDefaultView().setMouseManager(new MouseClick());
	    
	    // Quit program when view is closed
	    viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
	    
	    ViewerPipe fromViewer = viewer.newViewerPipe();
        fromViewer.addViewerListener(this);
        fromViewer.addSink(graph);
	
	    graph.addEdge("CityAston", "City", "Aston");
	    
//	    Node city = graph.getNode("City");
//	    city.setAttribute("name", "City");
	    
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
	    
	    while(loop) {
            fromViewer.pump();
        }
	    
//	    explore(graph.getNode("City"));
	}
    
    public void viewClosed(String id) {
    	loop = false;
    }
    
    public void buttonPushed(String id) {
		System.out.println("Button pushed on node "+id);
	}

	public void buttonReleased(String id) {
		System.out.println("Button released on node "+id);
	}

	
//    public void explore(Node source) {
//        Iterator<? extends Node> k = source.getBreadthFirstIterator();
//
//        while (k.hasNext()) {
//            Node next = k.next();
//            next.setAttribute("ui.class", "marked");
//            sleep();
//        }
//    }
	
//	protected void sleep() {
//	    try { Thread.sleep(1000); } catch (Exception e) {}
//	}
	
	private static class MouseClick extends DefaultMouseManager {
		@Override
		public void mouseDragged(MouseEvent event) {};
	}
	
	protected String styleSheet =
	    "node {" +
	    "	fill-color: black;" +
	    "	text-size: 24;"+
	    "}" +
//		    "node.marked {" +
//			"	fill-color: red;" +
//		 	"}"
	    "node:clicked {" +
	    "	fill-color: red;" +
	    "}";
}
