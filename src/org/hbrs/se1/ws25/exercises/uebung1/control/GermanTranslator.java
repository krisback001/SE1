package org.hbrs.se1.ws25.exercises.uebung1.control;
import org.hbrs.se1.ws25.exercises.uebung1.view.Client;

public class GermanTranslator implements Translator {

	public String date = "Okt/2025"; // Default-Wert
	public String version = "v1.9";
	/**
	 * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
	 */
	//Das Initiale Setzen der Strings
	String[] Zahlen = {
			"eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun", "zehn"
	};


	 public String translateNumber(int number) {

		 try {
			 return Zahlen[number-1];
		 	}
		 	catch(IndexOutOfBoundsException e) {

			 	String s = "Übersetzung der Zahl " + number + " nicht möglich " + version;
				return s;
			}
	}

	/**
	 * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
	 */
	void printInfo(){
		System.out.println( "GermanTranslator v1.9, erzeugt am " + this.date );
	}

	/**
	 * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: Monat/Jahr (Beispiel: "Okt/2024"))
	 * Das Datum sollte system-intern durch eine Factory-Klasse gesetzt werden und nicht von externen View-Klassen
	 */
	public void setDate( String date ) {
		this.date = date;
	}

}
