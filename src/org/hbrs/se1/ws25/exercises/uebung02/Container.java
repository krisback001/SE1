package org.hbrs.se1.ws25.exercises.uebung02;

import org.hbrs.se1.ws25.exercises.uebung03.persistence.PersistenceException;
import org.hbrs.se1.ws25.exercises.uebung03.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.List;

public final class Container {

    private static Container INSTANCE = null; //CR1 --Speicherintensiv, dafür Thread-Safe
    private final List<Member> memberList = new ArrayList<>();


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
    public PersistenceStrategy<Member> strategy;

    public void setPersistenceStrategy(PersistenceStrategy<Member> p) {
        this.strategy = p;
    }

    public void addMember(Member m) throws ContainerException {

        //Prüfe ob Objekt leer ist
        if (m == null) throw new ContainerException("Null-Wert nicht erlaubt!");

        for (Member member : memberList) {
            if (m.getID().equals(member.getID())) {
                throw new ContainerException(
                        "Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!"
                );
            }

        }
        memberList.add(m);
    }

    public String deleteMember(int id) {
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getID().equals(id)) {
                memberList.remove(i);
                return "Member mit der ID " + id + " gelöscht.";
            }
        }
        return "Kein Member mit der ID " + id + " vorhanden.";
    }


    public int size() {
        return memberList.size();
    }

    //CR2:
    public void store() throws PersistenceException {
        if (strategy == null) {
            throw new PersistenceException(
                    PersistenceException.ExceptionType.ImplementationNotAvailable,
                    "Keine Persistence-Strategie gesetzt!");
        }
        List<Member> loaded = strategy.load();
        memberList.clear();
        if (loaded != null) {
            memberList.addAll(loaded);
        }
    }

    //CR3
    public List<Member> getCurrentList() {
        return memberList; //bewusst "live"
        //Sicherer: return java.util.Collections.unmodifiableList(memberList);
    }

    //CR4: Tests und Roundtrip-Aufräumen
    public void deleteAllMembers() {
        memberList.clear();
    }

    public void load() throws PersistenceException {
        if (strategy == null) {
            throw new PersistenceException(
                    PersistenceException.ExceptionType.NoStrategyIsSet,
                    "Keine Persistence-Strategie gesetzt!"
            );
        }
        try {
            List<Member> newList = strategy.load();
            memberList.clear(); // alte Liste überschreiben
            if (newList != null) {
                memberList.addAll(newList);
            }
        } catch (PersistenceException e) {
            throw e;
        } catch (Exception e) {
            throw new PersistenceException(
                    PersistenceException.ExceptionType.NoStrategyIsSet,
                    "Fehler beim Laden: " + e.getMessage()
            );
        }
    }
}
