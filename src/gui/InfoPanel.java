/*
 * Konstruerer innholdet i høyre panel i vinduet
 * - viser navnet på valgt fil
 * - viser metadata om fil valgt i venstre panel
 * */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

public class InfoPanel extends JPanel {
	private JTable table;
	private JPanel tablecontainer;
	private JTextArea textarea;

	// Setter opp høyre side av vinduet
	public InfoPanel() {
		textarea = new JTextArea("Legg til en ny fil fra menyen,\neller klikk på en fil i listen for å se informasjon om den");
		textarea.setRows(3);
		textarea.setEditable(false);
		textarea.setOpaque(true);
		textarea.setBackground(new Color(230,57,70));
		textarea.setForeground(new Color(241,250,238));
		textarea.setBounds(0, 0, 370, 75);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setFont(textarea.getFont().deriveFont(Font.BOLD));
		textarea.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		JScrollPane infoarea = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		infoarea.setBorder(null);

		this.setLayout(new BorderLayout());
		this.setOpaque(true);
		this.setBackground(new Color(29, 53, 87));
		this.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 10, 0, 0, new Color(168, 218, 220)),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		this.add(infoarea, BorderLayout.NORTH);
		setVisible(true);
	}

	// Setter opp visningen av metadata (liste -> tabell)
	protected void displayMetadata(String filename) {
		FileMetaData fileinfo = new FileMetaData();
		createTable(fileinfo.setUpTableContents(filename));
		this.textarea.setText("Fil: " + filename);
	}

	// Lager tabellen metadata vises i
	private void createTable(Object[][] tabledata) {
		if (table != null) {
			this.remove(table); // Fjerner tabell hvis den allerede finnes
			this.remove(tablecontainer);
		}
		table = new JTable(tabledata, new String[] { "", "" });
		table.setRowHeight(26);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(false);
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(29,53,87));
		table.setFont(table.getFont().deriveFont(Font.BOLD));
		table.setEnabled(false);
		table.setVisible(true);
		TableColumnModel columnmodel = table.getColumnModel();
		columnmodel.getColumn(0).setPreferredWidth(110);
		columnmodel.getColumn(1).setPreferredWidth(260);
		
		tablecontainer = new JPanel(new GridLayout());
		tablecontainer.setBackground(new Color(29,53,87));
		tablecontainer.setBorder(new EmptyBorder(4, 0, 0, 0));
		this.add(tablecontainer, BorderLayout.CENTER);
		
		tablecontainer.add(table);
		this.updateUI();
		
	}

}
