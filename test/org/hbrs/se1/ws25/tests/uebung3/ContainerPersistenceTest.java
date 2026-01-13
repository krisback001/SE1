package org.hbrs.se1.ws25.tests.uebung3;

import org.hbrs.se1.ws25.exercises.uebung03.persistence.PersistenceException;
import org.hbrs.se1.ws25.exercises.uebung03.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws25.exercises.uebung03.persistence.PersistenceStrategyStream;
import org.hbrs.se1.ws25.exercises.uebung03.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerPersistenceTest {

    @BeforeEach
    void resetSingleton() {
        Container.getInstance().deleteAllMembers();
        // wichtige Vorsichtsmaßnahme: keine Strategy von hier „vergessen“
        // -> bewusst NICHT gesetzt; Tests setzen sie einzeln
    }

    // 1) Keine Strategie gesetzt
    @Test
    void store_withoutStrategy_throws() {
        var c = Container.getInstance();
        var ex = assertThrows(PersistenceException.class, c::store);
        assertEquals(PersistenceException.ExceptionType.ConnectionNotAvailable, ex.getExceptionType());
    }

    // 2) Mongo-Strategie (nicht implementiert)
    @Test
    void use_MongoStrategy_throws() {
        var c = Container.getInstance();
        c.setPersistenceStrategy(new PersistenceStrategyMongoDB<>());
        assertThrows(UnsupportedOperationException.class, c::store);
        // assertThrows(UnsupportedOperationException.class, c::load);
        // assertThrows(PersistenceException.class, c::store);
    }

    // 3) Fehlerhafte Location (Directory)
    @Test
    void stream_withDirectoryLocation_throws() throws Exception {
        var c = Container.getInstance();
        var s = new PersistenceStrategyStream<Member>();
        // temp directory als "Location"
        Path dir = Files.createTempDirectory("ps_dir_");
        s.setLocation(dir.toString()); // absichtlich ein Directory
        c.setPersistenceStrategy(s);

        c.addMember(new ConcreteMember(1));

        var ex = assertThrows(PersistenceException.class, c::store);
        assertEquals(PersistenceException.ExceptionType.ConnectionNotAvailable, ex.getExceptionType());
    }

    // 4) Round-Trip-Test
    @Test
    void roundTrip_saveClearLoad_ok() throws Exception {

        var c = Container.getInstance();
        var s = new PersistenceStrategyStream<Member>();
        File tmp = File.createTempFile("members", ".ser");
        tmp.deleteOnExit();
        s.setLocation(tmp.getAbsolutePath());
        c.setPersistenceStrategy(s);

        // Start: leer
        assertEquals(0, c.size());

        // Add
        c.addMember(new ConcreteMember(42));
        assertEquals(1, c.size());

        // Store
        c.store();
        assertTrue(tmp.exists());
        assertTrue(tmp.length() > 0);

        // Clear
        c.deleteAllMembers();
        assertEquals(0, c.size());

        // Load
        c.load();
        assertEquals(1, c.size());
        assertEquals(42, c.getCurrentList().get(0).getID());
    }
}
