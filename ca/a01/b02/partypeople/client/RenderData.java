package ca.a01.b02.partypeople.client;

import java.util.HashMap;

import ca.a01.b02.partypeople.PartyPlayer;
import ca.a01.b02.partypeople.client.renderer.EntityNameTag;

public class RenderData {

    private static RenderData           instance     = new RenderData();

    public HashMap<String, PartyPlayer> partyplayers = new HashMap<String, PartyPlayer>();
    public HashMap<String, EntityNameTag> nametags = new HashMap<String, EntityNameTag>();

    public static RenderData instance() {
        return instance;
    }
}
