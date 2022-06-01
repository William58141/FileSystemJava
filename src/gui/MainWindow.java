/*
 * Hovedvindu som lager elementer som vises og plasserer dem
 * Inneholder to paneler, meny og WindowListener
 * 
 * */
package gui;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

	private InfoPanel infopanel = new InfoPanel();
	private ListPanel listpanel = new ListPanel(infopanel);

	public MainWindow() {
		setLayout(new GridLayout());
		setTitle("Filutforsker");
		add(listpanel);
		add(infopanel);
		displayMenu();
		setSize(840, 420);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// Henter meny fra klassen VisMeny
	private void displayMenu() {
		Menu menubar = new Menu(listpanel);
		setJMenuBar(menubar.showMenuBar());

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

}
