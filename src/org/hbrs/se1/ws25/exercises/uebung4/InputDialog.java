package org.hbrs.se1.ws25.exercises.uebung4;

import org.hbrs.se1.ws25.exercises.uebung4.persistence.PersistenceStrategyStream;
import org.hbrs.se1.ws25.exercises.uebung4.persistence.PersistenceException;

import java.util.Scanner;

public class InputDialog {

    private static final Container container = Container.getInstance();
    private static final UserStoryView view = new UserStoryView(); //Intensiation bewusst
    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {

        container.setPersistenceStrategy(new PersistenceStrategyStream<>());

        System.out.println("Willkommen zum User-Story-Tool der WeissNix GmbH!");
        System.out.println("Geben Sie 'help' ein, um alle Befehle anzuzeigen.");

        boolean run = true;
        while (run) {
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "enter":
                    handleEnter();
                    break;
                case "dump":
                    UserStoryView.dump();
                    break;
                case "store":
                    handleStore();
                    break;
                case "load":
                    handleLoad();
                    break;
                case "delete":  //hier einfach mal hinzugefügt damit ich es nicht umsonst implementiert hab
                    System.out.println("Welche ID soll gelöscht werden?");
                    System.out.println(Container.deleteUserStory(Integer.parseInt(scanner.nextLine())));
                    break;
                case "help":
                    printHelp();
                    break;
                case "exit":
                    System.out.println("Programm wird beendet...");
                    run = false;
                    break;
                default:
                    System.out.println("Unbekannter Befehl! Tippen Sie 'help' für eine Übersicht.");
            }
        }
    }

    // Eingabe einer neuen User Story über Konsole.
    private static void handleEnter() {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Titel: ");
            String titel = scanner.nextLine();

            System.out.print("Akzeptanzkriterium: ");
            String kriterium = scanner.nextLine();

            System.out.print("Projekt: ");
            String projekt = scanner.nextLine();

            System.out.print("Mehrwert (0–10): ");
            int mehrwert = Integer.parseInt(scanner.nextLine());

            System.out.print("Strafe (0–10): ");
            int strafe = Integer.parseInt(scanner.nextLine());

            System.out.print("Risiko (0–10): ");
            int risiko = Integer.parseInt(scanner.nextLine());

            System.out.print("Aufwand (>0): ");
            int aufwand = Integer.parseInt(scanner.nextLine());

            UserStory us = new UserStory(id, titel, kriterium, projekt, mehrwert, strafe, risiko, aufwand);

            if (us.isValid()) {
                container.addUserStory(us);
                System.out.println("User Story erfolgreich hinzugefügt.");
            } else {
                System.out.println("Ungültige Werte – User Story wurde nicht übernommen.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Fehlerhafte Zahleneingabe. Bitte erneut versuchen.");
        } catch (ContainerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleStore() {
        try {
            container.store();
            System.out.println("Daten wurden gespeichert.");
        } catch (PersistenceException e) {
            System.out.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    private static void handleLoad() {
        try {
            container.load();
            System.out.println("Daten wurden geladen.");
        } catch (PersistenceException e) {
            System.out.println("Fehler beim Laden: " + e.getMessage());
        }
    }

    private static void printHelp() {
        System.out.println("Verfügbare Befehle:");
        System.out.println(" enter  - Eingabe einer neuen User Story");
        System.out.println(" dump   - Ausgabe aller User Stories sortiert nach Priorität");
        System.out.println(" store  - Speichern aller User Stories auf Datenträger");
        System.out.println(" load   - Laden aller User Stories vom Datenträger");
        System.out.println(" delete - Löschen einzelner User Stories");
        System.out.println(" help   - Anzeige dieser Hilfe");
        System.out.println(" exit   - Beenden der Anwendung");
    }
}
