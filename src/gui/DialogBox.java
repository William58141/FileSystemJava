/*
 * Viser dialogboks for å hente fil fra brukerens maskin
 * - filtrerer på .docx-format
 * - setter knappen i dialogboksen til "open" i stedet for "save"
 * - (fjerner eventuelt eksisterende liste)
 * - genererer ny liste med filer
 * */

package gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import logic.FileIO;

public class DialogBox {

	// Viser dialogboks for å legge til nye filer og sender opplysningene videre

	public boolean saveDialogBoxSelection() {
		FileIO fileoperations = new FileIO();
		String lastdirectory = fileoperations.fetchLastDirectory();
		JFileChooser filedialog = new JFileChooser(lastdirectory);
		filedialog.setFileFilter(new FileNameExtensionFilter(".docx files (Microsoft Word)", "docx"));
		int returnVal = filedialog.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File valgtfil = filedialog.getSelectedFile();
			lastdirectory = valgtfil.getPath();
			if (fileoperations.writeDirectory(valgtfil)) { // Alt gikk som det skulle
				return true;
			}
		}
		return false;
	}

}
