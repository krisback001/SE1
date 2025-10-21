package org.hbrs.se1.ws25.exercises.uebung3.persistence;
import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "objects.ser";

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     * Look-up in Google for further help! Good source:
     * https://www.digitalocean.com/community/tutorials/objectoutputstream-java-write-object-file
     * (Last Access: Oct, 13th 2025)
     */
    public void save(List<E> member) throws PersistenceException  {
        if (member == null) return;
        File target = new File(location);
        if(target.isDirectory()) {
            throw new PersistenceException(
                    PersistenceException.ExceptionType.ConnectionNotAvailable,
                    "Location ist ein Verzeichnis: " + location);
        }
        try (FileOutputStream fos = new FileOutputStream(target);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(member);
            oos.flush();
        } catch (IOException ex) {
            throw new PersistenceException(
                    PersistenceException.ExceptionType.ConnectionNotAvailable,
                    "Fehler beim Speichern nach '" + location + "'");
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException  {
        // Some Coding hints ;-)

        // ObjectInputStream ois = null;
        // FileInputStream fis = null;
        // List<...> newListe =  null;
        //
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        // fis = new FileInputStream( " a location to a file" );

        // Tipp: Use a directory (ends with "/") to implement a negative test case ;-)
        // ois = new ObjectInputStream(fis);

        // Reading and extracting the list (try .. catch ommitted here)
        // Object obj = ois.readObject();

        // if (obj instanceof List<?>) {
        //       newListe = (List) obj;
        // return newListe

        // and finally close the streams

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
