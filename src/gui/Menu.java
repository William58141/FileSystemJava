/*
 * Konstruerer vindumenyen for hovedvinduet
 * + fanger valg i menyene og utfører valgt handling
 * */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Menu implements ActionListener {
	private ListPanel listpanel;

	protected Menu(ListPanel listpanel) {
		this.listpanel = listpanel;
	}

	protected JMenuBar showMenuBar() {
		JMenu menu_file = new JMenu("Fil");
			JMenuItem option_selectfile = new JMenuItem("Velg fil");
				option_selectfile.addActionListener(this);
				menu_file.add(option_selectfile);
			JMenuItem option_close = new JMenuItem("Lukk");
				option_close.addActionListener(this);
				menu_file.add(option_close);

		JMenu menu_help = new JMenu("Hjelp");
			JMenuItem option_tip = new JMenuItem("Om programmet");
				option_tip.addActionListener(this);
				menu_help.add(option_tip);

		JMenuBar menubar = new JMenuBar();
		menubar.add(menu_file);
		menubar.add(menu_help);
		return menubar;
	}
	
	public void actionPerformed(ActionEvent event) {
		String arg = event.getActionCommand();
		if (arg.equals("Velg fil")) {
			boolean filesuccess = new DialogBox().saveDialogBoxSelection();
			if (filesuccess == true) { // Oppdaterer bare fillisten hvis filvalget var gyldig og vellykket
				listpanel.displayFileList(true);
			}
		}
		else if (arg.equals("Lukk")) {
			System.exit(0);
		}
		else if (arg.equals("Om programmet")) {
			JTextArea helptext = new JTextArea("Programmet kan vise metadata fra .docx-filer på datamaskinen.\n\n- Legg til nye filer under \"Fil\" -> \"Velg fil\".\n- Filer som er lagt til, vises i listen til venstre.\n- Metadata vises ved å klikke på et filnavn.");
			helptext.setEditable(false);
			helptext.setOpaque(false);
			JOptionPane.showMessageDialog(null, helptext, "Om programmet", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
