package ca.a01.b02.partypeople;

import java.util.HashMap;

public class PartyModel {

    public final static String          CHANNEL_PPPARTY       = "PPParty";
    public final static byte            OPCODE_PARTY_JOIN     = 0;
    public final static byte            OPCODE_PARTY_LEAVE    = 1;
    public final static byte            OPCODE_PARTY_UPDATE   = 2;

    public final static byte            SUBCODE_PLAYER_UPDATE = 0;
    public final static byte            SUBCODE_PLAYER_JOIN   = 1;
    public final static byte            SUBCODE_PLAYER_LEAVE  = 2;

    public HashMap<Integer, Party>      parties               = new HashMap<Integer, Party>();
    public HashMap<String, PartyPlayer> partyplayer           = new HashMap<String, PartyPlayer>();
    public HashMap<String, Integer>     inviteplayer          = new HashMap<String, Integer>();
    public static int                   nextPartyId           = 1;
}
