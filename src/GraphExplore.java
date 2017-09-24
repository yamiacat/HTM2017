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
import java.util.ArrayList;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;
import org.graphstream.ui.view.util.DefaultMouseManager;

public class GraphExplore {
	protected boolean loop = true;
	Graph graph;
	Viewer viewer;
	private ViewerPipe fromViewer;
	
    public static void main(String args[]) {
    	System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        new GraphExplore();
    }

    public GraphExplore() {
        Graph graph = new SingleGraph("tfwm");

	    graph.addAttribute("ui.stylesheet", styleSheet);
	    graph.setAutoCreate(true);
	    graph.setStrict(false);
	    
	    viewer = graph.display(false);
	    viewer.getDefaultView().setMouseManager(new MouseClick());
	    
	    // Quit program when view is closed
	    viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
	    
	    fromViewer = viewer.newViewerPipe();
        fromViewer.addViewerListener(new ViewerListener() {
        	
        	@Override
        	public void viewClosed(String id) {
            	loop = false;
            }
            
        	@Override
            public void buttonPushed(String id) {
        		System.out.println("Button pushed on node "+id);
        		
        	}

        	@Override
        	public void buttonReleased(String id) {
        		System.out.println("Button released on node "+id);
        		try{
        			Node clickedNode = graph.getNode(id);
        			for (Node station : graph) {
        				if (clickedNode.hasEdgeFrom(station) && (station.hasAttribute("hasTrain") == true)) {
        					System.out.println("Train could get to this station " + station.toString());
        					clickedNode.getEdgeFrom(station).setAttribute("ui.class", "occupied");
        					station.setAttribute("ui.class", "available");
        					sleep();
        					clickedNode.getEdgeFrom(station).setAttribute("ui.class", "available");
        					station.removeAttribute("hasTrain");
        					clickedNode.setAttribute("ui.class", "occupied");
        					clickedNode.setAttribute("hasTrain");
        				} else {
//        					System.out.println("No trains could get to this station.");
        				}
        			}
        		} catch (NullPointerException e) {
        			e.printStackTrace();
        			System.exit(1);
        		}
        	}
	
        });
        fromViewer.addSink(graph);
	
	    graph.addEdge("CityAston", "City", "Aston", true);
	    graph.addEdge("AstonCity","Aston", "City", true);
	    
	    graph.addEdge("AstonSmallheath", "Aston", "Smallheath", true);
	    graph.addEdge("SmallheathAston", "Smallheath", "Aston", true);
	    
	    graph.addEdge("SmallheathCity", "Smallheath", "City", true);
	    graph.addEdge("CitySmallheath", "City", "Smallheath", true);
	    
	    graph.addEdge("CityFiveways", "City", "Fiveways", true);
	    graph.addEdge("FivewaysCity", "Fiveways", "City", true);
	    
	    graph.addEdge("FivewaysSmallheath", "Fiveways", "Smallheath", true);
	    graph.addEdge("SmallheathFiveways", "Smallheath", "Fiveways", true);
	    
	    graph.addEdge("AstonPerrybarr", "Aston", "Perrybarr", true);
	    graph.addEdge("PerrybarrAston", "Perrybarr", "Aston", true);
	    
	    graph.addEdge("PerrybarrCity", "Perrybarr", "City", true);
	    graph.addEdge("CityPerrybarr", "City", "Perrybarr", true);
	    
	    graph.addEdge("PerrybarrWalsall", "Perrybarr", "Walsall", true);
	    graph.addEdge("WalsallPerrybarr", "Walsall", "Perrybarr", true);
	    
	    graph.addEdge("PerrybarrErdington", "Perrybarr", "Erdington", true);
	    graph.addEdge("ErdingtonPerrybarr", "Erdington", "Perrybarr", true);
	    
	    graph.addEdge("ErdingtonAston", "Erdington", "Aston", true);
	    graph.addEdge("AstonErdington", "Aston", "Erdington", true);
	    
	    graph.addEdge("ErdingtonLichfiel", "Erdington", "Lichfield", true);
	    graph.addEdge("LichfiellErdinton", "Lichfield", "Erdington", true);
	    
	    graph.addEdge("ErdingtonAcocksgreen", "Erdington", "Acocksgreen", true);
	    graph.addEdge("AcocksgreenErdington", "Acocksgreen", "Erdington", true);
	    
	    graph.addEdge("SmallheathAcocksgreen", "Smallheath", "Acocksgreen", true);    
	    graph.addEdge("AcocksgreenSmallheath", "Acocksgreen", "Smallheath", true);
	    
	    graph.addEdge("AcocksgreenSolihull", "Acocksgreen", "Solihull", true); 
	    graph.addEdge("SolihullAcocksgreen", "Solihull", "Acocksgreen", true);
	    
	    graph.addEdge("FivewaysSelloak", "Fiveways", "Sellyoak", true);
	    graph.addEdge("SelloakFiveways", "Sellyoak", "Fiveways", true);
	    
	    graph.addEdge("AcocksgreenSelloak", "Acocksgreen", "Sellyoak", true);
	    graph.addEdge("SellyoakAcocksgreen", "Sellyoak", "Acocksgreen", true);
	    
	    graph.addEdge("RedditchSellyoak", "Redditch", "Sellyoak", true);
	    graph.addEdge("SellyoakRedditch", "Sellyoak", "Redditch", true);
	        
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

        solihull.setAttribute("hasTrain");
        
	    for (Node node : graph) {
	        node.addAttribute("ui.label", node.getId());
	    }
	   
	    while(loop) {
	    	fromViewer.pump();
	    }
	    
	    walsall.setAttribute("hasTrain");
	    solihull.setAttribute("hasTrain");    
	}
    
    protected void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }
    
    private static class MouseClick extends DefaultMouseManager {
		@Override
		public void mouseDragged(MouseEvent event) {};
	}
	
	protected String styleSheet =
	    "node {" +
	    "	size: 24px;" +
	    "	fill-color: black;" +
	    "	text-size: 24;"+
	    "   text-alignment: above;" +
	    "	text-background-mode: plain;" +
	    "}" +
	    "node.occupied {" +
	    "	size: 32px;" +
	    "	fill-color: red;" +
	    "   text-alignment: above;" +
	    "	text-background-mode: plain;" +
	    "}" +
	    "node.available {" +
	    "	size: 32px;" +
	    "	fill-color: black;" +
	    "   text-alignment: above;" +
	    "	text-background-mode: plain;" +
	    "}" +
	    "node:clicked {" +
	    "	size: 32px;" +
	    "	fill-color: green;" +
	    "   text-alignment: above;" +
	    "	text-background-mode: plain;" +
	    "}" +
	    "edge {" +
	    "	size: 4px;" +
	    "	arrow-shape: arrow;" +
	    "	fill-color: black;" +
	    "	arrow-size: 10px;" +
	    "}" +
	    "edge.occupied {" +
	    "	size: 6px;" +
	    "	arrow-shape: arrow;" +
	    "	fill-color: red;" +
	    "	arrow-size: 10px;" +
	    "}" +
	    "edge.occupied {" +
	    "	size: 6px;" +
	    "	arrow-shape: arrow;" +
	    "	fill-color: red;" +
	    "	arrow-size: 10px;" +
	    "}";
}