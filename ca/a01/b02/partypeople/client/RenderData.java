package ca.a01.b02.partypeople.client;

import java.util.HashMap;

import ca.a01.b02.partypeople.PartyPlayer;

public class RenderData {

    private static RenderData           instance     = new RenderData();

    public HashMap<String, PartyPlayer> partyplayers = new HashMap<String, PartyPlayer>();

    public static RenderData instance() {
        return instance;
    }
}
