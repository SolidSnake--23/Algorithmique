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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.upem.fpasteur.graph.Graph;
import fr.upem.fpasteur.graph.Node;

@SuppressWarnings("serial")
public class DisplaySwing extends JFrame implements ActionListener {
	private JPanel home = new JPanel();
	private static String defaultTitle = "CateRer - Home";
	private JMenuItem openGraph, closeGraph, saveGraph;
	
	public DisplaySwing() {
		setBackground( Color.BLACK );
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
		home.add(new DisplaySommet(new Node(400)));

		Dimension dimension = getSize();

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

	private void initMenu() {
		JMenu menuFichier = new JMenu( "Fichier" );
		JMenuBar menu = new JMenuBar();
		closeGraph = new JMenuItem( "Fermer le graphe" );
		openGraph = new JMenuItem( "Ouvrir" );
		saveGraph = new JMenuItem( "Enregistrer" );

		closeGraph.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				// TODO Close
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
		// Création du menu déroulant "Fichier"
		menuFichier.add( openGraph );
		menuFichier.add( saveGraph );
		menuFichier.add( closeGraph );
		menuFichier.addSeparator();
		menuFichier.add( exit );

		// Ajout du menu déroulant à la bare de mneu
		menu.add( menuFichier );

		// Ajout des menus à la fenêtre
		setJMenuBar( menu );
	}
	
	private void saveGraph() {
		// TODO
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
			try {
				Graph G = fr.upem.fpasteur.io.File.loadGraph(filePath);
				displayGraph(G);
			} catch ( IllegalArgumentException ex ) {
				JOptionPane.showMessageDialog( this, "Impossible d'ouvrir le fichier" + (ex.getMessage()!=null?(" : \n"+ex.getMessage()):".") );
			}
		}
	}


	private void displayGraph(Graph g) {
		JPanel content = new JPanel();
		setContentPane( content );
		setTitle("Graphe");
		repaint();
	}

	public void home() {
		closeGraph.setEnabled( false );
		saveGraph.setEnabled( false );
		setContentPane( home );
		setTitle( defaultTitle );
	}


	@Override
	public void actionPerformed( ActionEvent e ) {
		Container content = getContentPane();
		if ( content instanceof ContentSwing ) {
			((ContentSwing) content).actionPerformed( e );
		}
	}

}
