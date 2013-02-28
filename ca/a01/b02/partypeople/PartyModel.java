package ca.a01.b02.partypeople;

import java.util.HashMap;

public class PartyModel {

    public HashMap<Integer, Party> parties     = new HashMap<Integer, Party>();
    public static int              nextPartyId = 1;
}
