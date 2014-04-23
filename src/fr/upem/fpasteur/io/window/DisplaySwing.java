package fr.upem.fpasteur.io.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.Timer;

import fr.upem.fpasteur.graph.Graph;

@SuppressWarnings("serial")
public class DisplaySwing extends JFrame implements ActionListener, Display {
	private final HashMap<Graph,ContentSwing> graphs = new HashMap<Graph, ContentSwing>();
	private final HashMap<Graph,JRadioButtonMenuItem> menus = new HashMap<Graph, JRadioButtonMenuItem>();
	private Timer timer;
	private JPanel home = new JPanel();
	private static String defaultTitle = "CateRer - Home";
	private JMenu menuGraph = new JMenu( "Graphes" );
	private JMenuItem closeGraph = new JMenuItem( "Fermer le graphe" ),
		openGraph = new JMenuItem( "Ouvrir" ),
		saveGraph = new JMenuItem( "Enregistrer" );
	private ButtonGroup groupGraphMenu = new ButtonGroup();

	public DisplaySwing() {
		setBackground( Color.BLACK );
		timer = new Timer( 30 , this );
		timer.setInitialDelay( 500 );
		timer.start();
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setPreferredSize( new Dimension( 500, 500 ) );
		setSize( new Dimension( 500, 500 ) );
		setBackground( Color.BLACK );
		setResizable( true );
		initMenu();
		initHome();
		home();
	}
	
	private void initHome() {
		home.setLayout( new BorderLayout() );

		Label label = new Label( "Pour commencer, veuillez ouvrir un nouveau graphe." );
		label.setAlignment( Label.CENTER );
		home.add( label, BorderLayout.CENTER );

		Dimension dimension = getSize();
/*
		JButton exit = new JButton( "Quitter" );
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				System.exit( 0 );
			}
		});
		exit.setSize( dimension.width / 3, 50 );
		exit.set
		home.add( exit, BorderLayout.SOUTH );
*/
		JButton open = new JButton( "Ouvrir un graphe" );
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				openGraph();
			}
		});
		open.setSize( dimension.width / 3, 50 );
		home.add( open, BorderLayout.SOUTH );
	}

	public void setRefresh( int refresh ) {
		timer.setDelay( refresh );
	}

	@Override
	public void add( final Graph graph, String title ) {
		ContentSwing content = new ContentSwing( graph, title );
		content.setBackground( getBackground() );
		graphs.put( graph, content );
		JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem( title );
		menuItem.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				swap( graph );
			}
		});
		if ( 0 == menuGraph.getItemCount() ) {
			menuGraph.setEnabled( true );
		}
		groupGraphMenu.add( menuItem );
		menus.put( graph, menuItem );
		menuGraph.add( menuItem );
		if ( isHome() ) {
			swap( graph );
		}
	}

	private void initMenu() {
		JMenu menuFichier = new JMenu( "Fichier" );
		JMenuBar menu = new JMenuBar();
		closeGraph.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				removeCurrentGraph();
			}
		} );
		openGraph.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				openGraph();
			}
		} );
		saveGraph.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				saveGraph();
			}
		} );
		JMenuItem exit = new JMenuItem( "Quitter" );
		exit.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit( 0 );
			}
		});
		menuFichier.add( openGraph );
		menuFichier.add( saveGraph );
		menuFichier.add( closeGraph );
		menuFichier.addSeparator();
		menuFichier.add( exit );

		menu.add( menuFichier );

		menuGraph.setEnabled( false );
		menu.add( menuGraph );

		setJMenuBar( menu );
	}
	
	private void saveGraph() {
		FileDialog fd = new FileDialog( this, "Test", FileDialog.LOAD );
		fd.setFilenameFilter( new FilenameFilter() {
			@Override
			public boolean accept(File arg0, String arg1) {
				// TODO Auto-generated method stub
				return false;
			}
		});
        fd.setVisible( true );
        System.out.println( fd.getFile() );
	}

	private void openGraph() {
		FileDialog fd = new FileDialog( this, "Test", FileDialog.LOAD );
		String home = System.getenv( "HOME" );
		if ( home == null ) {
			home = System.getenv( "HOMEDRIVE" ) + System.getenv( "HOMEPATH" );
			System.out.println( home );
			if ( home != null ) {
				File file = new File( home );
				if ( file.isDirectory() ) {
					fd.setDirectory( file.getAbsolutePath() );
				}
			}
		} else {
			File file = new File( home );
			if ( file.isDirectory() ) {
				fd.setDirectory( file.getAbsolutePath() );
			}
		}
		fd.setVisible( true );
		String filePath = fd.getFile();
		if ( filePath != null ) {
			File file = new File( filePath );
			Scanner stream;
			try {
				stream = new Scanner( new FileReader( file ) );
				try {
					Graph G = fr.upem.fpasteur.io.File.loadGraph(filePath);
					add( G, file.getName() );
					swap( G );
				} catch ( IllegalArgumentException ex ) {
					String msg = ex.getMessage();
					if ( msg == null ) {
						JOptionPane.showMessageDialog( this, "Impossible d'ouvrir le fichier." );
					} else {
						JOptionPane.showMessageDialog( this, "Impossible d'ouvrir le fichier :\n" + ex.getMessage() );
					}
				} finally {
					stream.close();
				}
			} catch ( FileNotFoundException ex ) {
				JOptionPane.showMessageDialog( this, "Impossible d'ouvrir le fichier :\nLe fichier est itnrouvable." );
			}
		}
	}

	private void removeCurrentGraph() {
		if ( graphDisplayed() ) {
			Graph graphToRemove = null;
			Container content = getContentPane();
			Entry<Graph,ContentSwing> entry;
			Iterator<Entry<Graph,ContentSwing>> it = graphs.entrySet().iterator();
			while ( it.hasNext() ) {
				entry = it.next();
				if ( entry.getValue().equals( content ) ) {
					graphToRemove = entry.getKey();
					break;
				}
			}
			if ( graphToRemove != null ) {
				remove( graphToRemove );
			}
		}
	}
	
	private boolean isHome() {
		return home.equals( getContentPane() );
	}
	
	private boolean graphDisplayed() {
		return graphs.containsValue( getContentPane() );
	}

	public void remove( Graph graph ) throws IllegalArgumentException {
		if ( ! graphs.containsKey( graph ) ) {
			throw new IllegalArgumentException( "Le graphe n'est pas ouvert." );
		}
		graphs.remove( graph );
		menuGraph.remove( menus.get( graph ) );
		if ( 0 == menuGraph.getItemCount() ) {
			menuGraph.setEnabled( false );
		}
		if ( graphs.isEmpty() ) {
			home();
		} else {
			graph = (Graph) graphs.keySet().toArray()[0];
			swap( graph );
		}
	}

	public void home() {
		closeGraph.setEnabled( false );
		saveGraph.setEnabled( false );
		setContentPane( home );
		setTitle( defaultTitle );
	}

	public boolean swap( Graph graph ) {
		if ( ! graphs.containsKey( graph ) ) {
			return false;
		}
		if ( isHome() ) {
			closeGraph.setEnabled( true );
			saveGraph.setEnabled( true );
		}
		menus.get( graph ).setSelected( true );
		ContentSwing content = graphs.get( graph );
		setContentPane( content );
		setTitle( content.getTitle() );
		validate();
		repaint();
		return true;
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		Container content = getContentPane();
		if ( content instanceof ContentSwing ) {
			((ContentSwing) content).actionPerformed( e );
		}
	}
}
