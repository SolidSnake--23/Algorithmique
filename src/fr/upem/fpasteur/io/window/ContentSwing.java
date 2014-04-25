package fr.upem.fpasteur.io.window;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import fr.upem.fpasteur.graph.Arc;
import fr.upem.fpasteur.graph.Graph;
import fr.upem.fpasteur.graph.Node;

@SuppressWarnings("serial")
public class ContentSwing extends Container implements MouseMotionListener {
	private final Graph graph;
	private static Dimension dimensionSommet = new Dimension( 30, 20 );

	public ContentSwing( Graph graph) {
		this.graph = graph;
	}

	public void drawGraph() {
		java.util.Iterator<Node> it = this.graph.getNodes().iterator();
		
		while (it.hasNext()) {
			add(new DisplaySommet(it.next()));
			System.out.println("Node");
		}
		
	}

	public synchronized void actionPerformed( ActionEvent e ) {

		repaint();
	}

	@Override
	public synchronized void paint( Graphics graphics ) {
		Dimension dimension = getSize();
		BufferedImage img = new BufferedImage(
			dimension.width,
			dimension.height,
			BufferedImage.TYPE_INT_RGB
		);
		Graphics g = img.getGraphics();
		drawGraph();
		super.paint( g );
	
		graphics.drawImage( img, 0, 0, this );
	}

	private DisplaySommet getDisplaySommet( Node S ) throws IllegalArgumentException {
		for ( Component component: getComponents() ) {
			if ( component instanceof DisplaySommet && ((DisplaySommet) component).isSommet( S ) ) {
				return (DisplaySommet) component;
			}
		}
		throw new IllegalArgumentException( "Le sommet n'a pas encore �t� enregistr�." );
	}

	private void paintArcs(
			Graphics graphics,
			List<Arc> arcs
		) {
		DisplaySommet origine, destination;
		Dimension dimensionOri, dimensionDest;
		Point pointOri, pointDest;
		for ( Arc arc: arcs ) {
			try {
				origine = getDisplaySommet( arc.getSrc() );
				destination = getDisplaySommet( arc.getDest() );
				dimensionOri = origine.getSize();
				pointOri = origine.getLocation();
				pointOri.x += dimensionOri.width  / 2;
				pointOri.y += dimensionOri.height / 2;
				dimensionDest = destination.getSize();
				pointDest = destination.getLocation();
				pointDest.x += dimensionDest.width  / 2;
				pointDest.y += dimensionDest.height / 2;
				graphics.drawLine( pointOri.x, pointOri.y, pointDest.x, pointDest.y );
				String cout = new String( arc.getCost() + "" );
				char name[] = cout.toCharArray();
				graphics.drawChars( name, 0, name.length, ( pointOri.x + pointDest.x ) / 2, ( pointOri.y + pointDest.y ) / 2 );
			} catch ( IllegalArgumentException e ) {}
		}
	}

	@Override
	public boolean equals( Object o ) {
		if ( ! ( o instanceof ContentSwing ) ) {
			return false;
		}
		return super.equals( o );
	}

	@Override
	public void mouseDragged( MouseEvent e ) {
		Object o = e.getSource();
		if ( o instanceof DisplaySommet ) {
			DisplaySommet ds = (DisplaySommet) o;
			Dimension dim = ds.getSize();
			Point position = ds.getLocation();
			ds.setLocation( new Point(
				position.x + e.getX() - dim.width  / 2,
				position.y + e.getY() - dim.height / 2
			) );
		}
	}

	@Override
	public void mouseMoved (MouseEvent e ) {}
}
