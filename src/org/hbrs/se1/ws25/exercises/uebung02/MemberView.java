package org.hbrs.se1.ws25.exercises.uebung02;

import java.util.List;

public class MemberView {

    public void dump(List<Member> liste) {
        if (liste == null || liste.isEmpty()) {
            System.out.println("=== Keine Member vorhanden ===");
            return;
        }
        System.out.println("=== Member-Liste ===");
        for (Member m : liste) {
            // Minimal-Anforderung: Ausgabe basierend auf Interface Member (hat getID())
            System.out.println("Member-ID: " + m.getID());
        }
    }
}