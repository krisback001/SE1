package org.hbrs.se1.ws25.exercises.uebung2;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private List<Member> memberList = new ArrayList<>();

    public void addMember(Member m) throws ContainerException {

        //Prüfe ob Objekt leer ist
        if (m == null) return;

        for(Member member: memberList ) {
            if(m.getID().equals(member.getID())) {
                throw new ContainerException(
                        "Der Member mit der ID " + member.getID() + " ist bereits vorhanden!"
                );
            }

        }
        memberList.add(m);
    }
    public String deleteMember(int id) {
        for(Member m: memberList) {
            if(m.getID().equals(id)) {
                memberList.remove(m);
                return "Member mit der ID " + id + " gelöscht.";
            }
        }
        return "Kein Member mit der ID " + id + " vorhanden.";
    }


    public void dump() {

        for (Member m: memberList) {
            System.out.println(m.toString());
        }
    }
    public int size() {
        return memberList.size();
    }
}
