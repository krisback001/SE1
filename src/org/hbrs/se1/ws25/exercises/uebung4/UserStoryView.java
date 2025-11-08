package org.hbrs.se1.ws25.exercises.uebung4;

import java.util.Comparator;
import java.util.List;

public class UserStoryView {

    public static void dump() {
        List<UserStory> list = Container.getInstance().getCurrentList();

        System.out.println("ID | Titel | Akzeptanzkriterium | Projekt | M | S | R | A | Priorität");
        System.out.println("---------------------------------------------------------------------------");

        list.stream()
                .filter(UserStory::isValid)
                .sorted(Comparator.comparing(UserStory::getPrioritierung).reversed()) // Sortierung nach Priorität
                .map(us -> String.format("%-3d| %-10s| %-10s| %-13s| %2d | %2d | %2d | %2d | %7.2f",
                        us.getId(), us.getTitel(), us.getAkzeptanzkriterium(),
                        us.getProjekt(), us.getMehrwert(), us.getStrafe(),
                        us.getRisiko(), us.getAufwand(), us.getPrioritierung()))
                .forEach(System.out::println);

        System.out.println("-------------------------------------------------------------");
        System.out.println("Gesamtanzahl: " + list.size());
    }
}