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

import fr.upem.fpasteur.graph.Arc;
import fr.upem.fpasteur.graph.Graph;
import fr.upem.fpasteur.graph.Node;

@SuppressWarnings("serial")
public class ContentSwing extends Container implements MouseMotionListener {
	private final Graph graph;
	private int poidsMax, poidsMin, coutMax;
	private Point min = new Point( 0, 0 ), max = new Point( 1, 1 );
	private static Dimension dimensionSommet = new Dimension( 30, 20 );
	private String title;

	public ContentSwing( Graph graph, String title ) {
		this.graph = graph;
		this.title = title;
	}

	private void updateMinMaxCoords( Point position ) {
		if ( min == null || max == null ) {
			min = new Point( position );
			max = new Point( position );
		} else {
			min.x = Math.min( min.x, position.x );
			min.y = Math.min( min.y, position.y );
			max.x = Math.max( max.x, position.x );
			max.y = Math.max( max.y, position.y );
		}
	}

	private boolean hasSommet( Node S ) {
		for ( Component component: getComponents() ) {
			if ( component instanceof DisplaySommet && ((DisplaySommet) component).isSommet( S ) ) {
				return true;
			}
		}
		return false;
	}

	private DisplaySommet updateList( Node S ) {
		/*if ( ! hasSommet( S ) ) {
			DisplaySommet ds = new DisplaySommet( S, getSize() );
			ds.addMouseMotionListener( this );
			add( ds );
			ds.setSize( dimensionSommet );
			return ds;
		}*/
		return getDisplaySommet( S );
	}

	private void updateMinMaxPoids( int poids ) {
		if ( poidsMin > poidsMax ) {
			poidsMin = poids;
			poidsMax = poids;
		} else {
			poidsMin = Math.min( poidsMin, poids );
			poidsMax = Math.max( poidsMax, poids );
		}
	}

	public synchronized void actionPerformed( ActionEvent e ) {
		poidsMax = 0;
		poidsMin = 1;
		min = max = null;
		DisplaySommet ds;
		for ( Node S: graph.getNodes() ) {
			ds = updateList( S );
			updateMinMaxCoords( ds.getLocation() );
			updateMinMaxPoids( S.getValue() );
		}

		coutMax = 1;
		for ( Arc arc: graph.getArcs() ) {
			if ( coutMax < arc.getCost() ) {
				coutMax = arc.getCost();
			}
		}
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
		paintArcs( g, graph.getNodes() );
		super.paint( g );
	
		graphics.drawImage( img, 0, 0, this );
	}

	private DisplaySommet getDisplaySommet( Node S ) throws IllegalArgumentException {
		System.out.println(S);
		for ( Component component: getComponents() ) {
			if ( component instanceof DisplaySommet && ((DisplaySommet) component).isSommet( S ) ) {
				return (DisplaySommet) component;
			}
		}
		throw new IllegalArgumentException( "Le sommet n'a pas encore �t� enregistr�." );
	}

	private void paintArcs(
			Graphics graphics,
			List<Node> nodes
		) {
		DisplaySommet origine, destination;
		Dimension dimensionOri, dimensionDest;
		Point pointOri, pointDest;
		for ( Node arc: nodes ) {
			try {
				//getDisplaySommet(arc);
				//origine = getDisplaySommet( arc.getSrc() );
				//destination = getDisplaySommet( arc.getDest() );
				/*dimensionOri = origine.getSize();
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
				graphics.drawChars( name, 0, name.length, ( pointOri.x + pointDest.x ) / 2, ( pointOri.y + pointDest.y ) / 2 );*/
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
