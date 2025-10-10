package org.hbrs.se1.ws25.tests.uebung.uebung1;

import org.hbrs.se1.ws25.exercises.uebung1.control.GermanTranslator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GermanTranslatorTest {

    @Test
    public void aTest() {
        GermanTranslator translator = new GermanTranslator();

        assertEquals("Übersetzung der Zahl 0 nicht möglich v1.9", translator.translateNumber(0));
        assertEquals("Übersetzung der Zahl -5 nicht möglich v1.9" , translator.translateNumber(-5));
        assertEquals("fünf" , translator.translateNumber(5));
        assertEquals("Übersetzung der Zahl 15 nicht möglich v1.9" , translator.translateNumber(15));
    }


}