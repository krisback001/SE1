package org.hbrs.se1.ws25.exercises.uebung4;

import org.hbrs.se1.ws25.exercises.uebung4.persistence.PersistenceException;
import org.hbrs.se1.ws25.exercises.uebung4.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Container {

    private static Container INSTANCE = null; //CR1 --Speicherintensiv, dafür Thread-Safe
    private static List<UserStory> liste = new ArrayList<>();


    //CR1:
    private Container() {
    }

    public static synchronized Container getInstance() { //synchronized: Thread-Safe (darf nur einmalig aufgerufen werden)
        if (INSTANCE == null) {
            INSTANCE = new Container();
            return INSTANCE;
        }
        return INSTANCE;
    }

    //CR2:
    private PersistenceStrategy<UserStory> strategy;

    public void setPersistenceStrategy(PersistenceStrategy<UserStory> p) {
        this.strategy = p;
    }

    public void addUserStory(UserStory us) throws ContainerException {
        if (us == null) return;

        if (!us.isValid()) {
            throw new ContainerException("Ungültige Eingabewerte für User Story mit ID " + us.getId());
        }

        for (UserStory story : liste) {
            if (story.getId() == us.getId()) {
                throw new ContainerException("User Story mit der ID " + us.getId() + " existiert bereits!");
            }
        }

        liste.add(us);
    }

    public static String deleteUserStory(int id) {
        for (UserStory story : liste) {
            if (story.getId() == id) {
                liste.remove(story);
                return "User Story mit ID " + id + " gelöscht.";
            }
        }
        return "Keine User Story mit ID " + id + " gefunden.";
    }


    public int size() {
        return liste.size();
    }

    public List<UserStory> getCurrentList() {
        return Collections.unmodifiableList(liste);
    }

    public void store() throws PersistenceException {
        if (strategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Persistenzstrategie gesetzt!");
        }
        strategy.save(liste);
    }

    public void load() throws PersistenceException {
        if (strategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"Keine Persistenzstrategie gesetzt!");
        }
        List<UserStory> loaded = strategy.load();
        if (loaded != null) {
            liste = loaded;
        }
    }
}