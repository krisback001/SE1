package org.hbrs.se1.ws25.exercises.uebung01.view;

import org.hbrs.se1.ws25.exercises.uebung01.TranslatorFactory;
import org.hbrs.se1.ws25.exercises.uebung01.control.Translator;

public class Client {

	/**
		 * Methode zur Ausgabe einer Zahl auf der Console
		 * (auch bezeichnet als CLI, Terminal)
		 * Factory Method(GoF, Kapitel 6)
		 * "ich gebe vor wie die Objekte erzeugt werden"
		 */
		 void display( int aNumber ){
			// In dieser Methode soll die Methode translateNumber
			// mit dem übergegebenen Wert der Variable aNumber
			// aufgerufen werden.
			//
			// Strenge Implementierung (nur) gegen das Interface Translator gewuenscht!


			 Translator translator = TranslatorFactory.createGermanTranslator();
			 String result = translator.translateNumber(aNumber);

			 System.out.println("Das Ergebnis der Berechnung: " + result );

		 }
}





