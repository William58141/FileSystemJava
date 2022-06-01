/*
 * Henter inn metadata fra filnavn
 * - lager liste
 * - lager en tabell i parent-elementet (InfoPanel)
 * */
package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ooxml.POIXMLProperties.CoreProperties;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class FileMetaData {

	// Lager en liste med innholdet til tabellen med metadata
	public Object[][] setUpTableContents(String file) {
		Object[][] tabledata = null;
		XWPFDocument docx = null;
		try {
			docx = new XWPFDocument(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		XWPFWordExtractor we = new XWPFWordExtractor(docx);
		try {
			// Listen med metadata som skal overleveres
			CoreProperties metadata = we.getCoreProperties();
			tabledata = new Object[][] { { " Forfatter", showStrOrDash(metadata.getCreator()) },
					{ " Sist endret av", showStrOrDash(metadata.getLastModifiedByUser()) },
					{ " Emne", showStrOrDash(metadata.getSubject()) },
					{ " Tittel", showStrOrDash(metadata.getTitle()) },
					{ " Beskrivelse", showStrOrDash(metadata.getDescription()) },
					{ " Opprettet", showStrOrDash(metadata.getCreated()) },
					{ " Sist endret", showStrOrDash(metadata.getModified()) },
					{ " Revisjoner", showStrOrDash(metadata.getRevision()) },
					{ " Sist printet", showStrOrDash(metadata.getLastPrinted()) } };

		} finally {
			try {
				we.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return tabledata;
	}

	// Hvis objekt ikke er tomt -> returner som string (ellers vis "-")
	private String showStrOrDash(Object o) {
		return (o != null ? o.toString() : "-");
	}

}
