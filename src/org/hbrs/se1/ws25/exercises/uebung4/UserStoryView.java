package org.hbrs.se1.ws25.exercises.uebung4;

import java.util.List;

public class UserStoryView {

    public List dump() {
        List list = Container.getInstance().getCurrentList();
        return list;
    }
}
