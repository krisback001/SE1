package org.hbrs.se1.ws25.tests.uebung2;

import org.hbrs.se1.ws25.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws25.exercises.uebung2.Container;
import org.hbrs.se1.ws25.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws25.exercises.uebung2.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    @Test
    public void testContainerFunktion() throws ContainerException {

        Container container = new Container();

        Member m1 = new ConcreteMember(1);
        Member m2 = new ConcreteMember(2);

        container.addMember(m1);
        container.addMember(m2);

        ContainerException ex = assertThrows(ContainerException.class,
                () -> container.addMember(new ConcreteMember(1)));
        assertEquals("Der Member mit der ID 1 ist bereits vorhanden!", ex.getMessage());


        //Löschen eines existierenden Members
        assertEquals("Member mit der ID 2 gelöscht.", container.deleteMember(2));

        //Löschen eines nicht existierenden Members
        assertEquals("Kein Member mit der ID 99 vorhanden.", container.deleteMember(99));

        //Größe nach Löschen prüfen
        assertEquals(1, container.size());

        //toString-Format prüfen
        assertEquals("Member (ID = [1])", m1.toString());
        }
    }

