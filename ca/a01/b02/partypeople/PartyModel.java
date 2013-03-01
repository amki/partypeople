package ca.a01.b02.partypeople;

import java.util.HashMap;

public class PartyModel {

    public HashMap<Integer, Party>  parties      = new HashMap<Integer, Party>();
    public HashMap<String, Integer> partyplayer  = new HashMap<String, Integer>();
    public HashMap<String, Integer> inviteplayer = new HashMap<String, Integer>();
    public static int               nextPartyId  = 1;
}
