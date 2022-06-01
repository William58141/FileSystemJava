/*
 * Inneholder logikk/filfunksjoner som benyttes til å hente innhold i tekstfiler
 * */

package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class FileIO {

	private String filelistname = "fileList.txt";
	private String lastdirectoryname = "fileDirectory.txt";

	public String getFilnavnSisteMappe() {
		return lastdirectoryname;
	}

	public String getNavnFilliste() {
		return filelistname;
	}

	public boolean doesFileListExist() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(getNavnFilliste()));
		} catch (FileNotFoundException e) {}
		if (scanner != null) return true;
		return false;
	}
	
	public String fetchLastDirectory() {
		String lastdirectory = null;
		try {
			Scanner filecontents = new Scanner(new File(this.lastdirectoryname));
			String activedirectory = filecontents.nextLine();
			if (activedirectory != null)
				lastdirectory = activedirectory;
			filecontents.close();
		} catch (IOException e) {
		}
		return lastdirectory;
	}

	public boolean writeDirectory(File file) {

		if (file.getAbsolutePath().endsWith(".docx")) {
			if (!checkIfFileExists(file.getAbsolutePath())) {
				writeToFile(lastdirectoryname, file.getParentFile().getAbsolutePath(), false);
				writeToFile(this.filelistname, file.getAbsolutePath(), true);
			} else {
				JOptionPane.showMessageDialog(null, "Filen finnes allerede i listen.", "Feilmelding", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Filen du valgte er ikke av type .docx", "Feilmelding",	JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void writeToFile(String filename, String content, Boolean append) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(filename, append));
			pw.println(content);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Forhindrer at samme fil kan legges til flere ganger
	private Boolean checkIfFileExists(String filename) {
		Scanner filecontents = null;
		try {
			filecontents = new Scanner(new File(filelistname));
			while (filecontents.hasNextLine()) {
				String line = filecontents.nextLine();
				if (filename.equals(line))
					return true;
			}
		} catch (FileNotFoundException e) {
		} finally {
			if (filecontents != null)
				filecontents.close();
		}
		return false;
	}

	public Scanner scanFile() {
		Scanner filereader = null;
		try {
			filereader = new Scanner(new File(getNavnFilliste()));
		} catch (FileNotFoundException e) {}
		return filereader;
	}
}
