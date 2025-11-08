package org.hbrs.se1.ws25.exercises.uebung3;

import java.io.Serializable;

public class ConcreteMember implements Member, Serializable {

    //Instanzvariablen
    private Integer id = null;

    public ConcreteMember(int id) {this.id = id; }
    public ConcreteMember(int id, String str) {this.id = id; }

    public Integer getID() {
        return this.id;
    }
    @Override
    public String toString() {
        return "Member (ID = [" + this.id + "])";
    }
}
