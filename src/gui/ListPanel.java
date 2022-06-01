/*
 * Konstruerer innhold i venstre panel
 * - filliste
 * -- sender filnavn til høyre panel
 * */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logic.FileIO;

public class ListPanel extends JPanel implements ListSelectionListener {
	private InfoPanel infopanel;
	private JScrollPane scrollpane = new JScrollPane();

	protected ListPanel(InfoPanel activeinfopanel) {
		this.infopanel = activeinfopanel;
		setBackground(new Color(141, 189, 206));
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10));
		displayFileList(false);
	}

	// Vis listen med filer
	protected void displayFileList(boolean isupdated) {
		this.remove(scrollpane); // Workaround for å erstatte fillisten med en ny ved oppdatering
		FileList listfunctions = new FileList();
		JList<String> list = listfunctions.setUpList();
		if (new FileIO().doesFileListExist()) { // Kun ListSelectionListener hvis ting i listen finnes}
			list.addListSelectionListener(this);
		}
		scrollpane = new JScrollPane();
		scrollpane.setViewportView(list);
		scrollpane.setBorder(null);
		if (isupdated == true) // Sett fokus på riktig element i listen hvis ny er lagt til
			listfunctions.handleRecentUpdate(list);
		list = listfunctions.styleList(list);
		add(scrollpane, BorderLayout.CENTER);
		updateUI();
	}

	// Reagerer på klikk/bevegelser i fillisten
	public void valueChanged(ListSelectionEvent event) {
		if (event.getValueIsAdjusting())
			return;
		JList<String> eventsource = (JList<String>) event.getSource();
		infopanel.displayMetadata(eventsource.getSelectedValue().toString());
	}

}
