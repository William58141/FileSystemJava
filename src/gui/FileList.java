package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;

import logic.FileIO;

public class FileList {

	// Lager en ny liste
	protected JList<String> setUpList() {
		Scanner filereader = new FileIO().scanFile();
		JList<String> list = null;
		DefaultListModel<String> listmodel = new DefaultListModel<String>();

		if (filereader != null) { // Fillisten eksisterer, så vis den
			while (filereader.hasNextLine())
				listmodel.addElement(filereader.nextLine());
			list = new JList<>(listmodel);
			list.setFont(list.getFont().deriveFont(Font.PLAIN));

		} else { // Vis tom filliste
			list = new JList<>(listmodel);
			listmodel.addElement("(Listen er tom)");
			list.setFont(list.getFont().deriveFont(Font.ITALIC));
		}
		return list;
	}

	// Setter alle stilvalg på listen
	protected JList<String> styleList(JList<String> list){
		list.setSelectionBackground(new Color(230, 57, 70));
		list.setSelectionForeground(Color.WHITE);
		list.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		list.setFixedCellHeight(22);
		list.setBackground(new Color(255, 255, 255));
		list.setForeground(new Color(29, 53, 87));
		return list;
	}
	
	// Setter fokus på den nederste linjen
	protected JList<String> handleRecentUpdate(JList<String> list){
		list.getSelectionModel().setSelectionInterval(list.getModel().getSize() - 1, list.getModel().getSize() - 1);
		list.ensureIndexIsVisible(list.getSelectedIndex());
		return list;
	}

}
