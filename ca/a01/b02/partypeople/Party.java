package ca.a01.b02.partypeople;

import java.util.HashSet;

public class Party {

    public int             id;
    public int             invites = 0;
    public String          leader;
    public HashSet<String> members = new HashSet<String>();

    public Party(int id, String leader) {
        this.id = id;
        this.leader = leader;
    }
}
