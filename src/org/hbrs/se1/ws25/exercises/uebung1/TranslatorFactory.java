package org.hbrs.se1.ws25.exercises.uebung1;

import org.hbrs.se1.ws25.exercises.uebung1.control.GermanTranslator;

public class TranslatorFactory {

        public static GermanTranslator createGermanTranslator(){
            return new GermanTranslator();
        }
}
