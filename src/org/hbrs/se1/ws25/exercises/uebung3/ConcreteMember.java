package org.hbrs.se1.ws25.exercises.uebung3;

public class ConcreteMember implements Member {

    //Instanzvariablen
    private int id;

    public ConcreteMember(int id) {
        this.id = id;

    }

    @Override
    public Integer getID() {
        return this.id;
    }
    @Override
    public String toString() {
        return "Member (ID = [" + this.id + "])";
    }
}
