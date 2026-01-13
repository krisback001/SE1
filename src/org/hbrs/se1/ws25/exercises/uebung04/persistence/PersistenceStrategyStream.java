package org.hbrs.se1.ws25.exercises.uebung04.persistence;

import org.hbrs.se1.ws25.exercises.uebung04.UserStory;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    private String location = "userstories.ser";

    public void setLocation(String location) {
        this.location = location;
    }

    @Override

    public void save(List<UserStory> us) throws PersistenceException {

        if (us == null) return;
        File target = new File(location);
        if(target.isDirectory()) {
            throw new PersistenceException(
                    PersistenceException.ExceptionType.ConnectionNotAvailable,
                    "Location ist ein Verzeichnis: " + location);
        }
        try (FileOutputStream fos = new FileOutputStream(target);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(us);
            oos.flush();
        } catch (IOException ex) {
            throw new PersistenceException(
                    PersistenceException.ExceptionType.ConnectionNotAvailable,
                    "Fehler beim Speichern nach '" + location + "'");
        }
    }

    @Override
    public List<E> load() throws PersistenceException {

        File source = new File(location);
        if (!source.exists()) {
            return new java.util.ArrayList<>();
        }
        try (
                FileInputStream fis = new FileInputStream(source);
                ObjectInputStream ois = new ObjectInputStream(fis);) {
                    Object obj = ois.readObject();
                    if (obj instanceof List<?>) {
                        @SuppressWarnings("unchecked")
                        List<E> list = (List<E>) obj;
                        return list;
                    }
                    throw new PersistenceException(
                            PersistenceException.ExceptionType.ImplementationNotAvailable,
                            "Datei enthält kein List-Objekt: " + location);
        } catch (IOException | ClassNotFoundException ex) {
                    throw new PersistenceException(
                            PersistenceException.ExceptionType.ConnectionNotAvailable,
                            "Fehler beim Laden aus '" + location + "'");
        }
    }
}
